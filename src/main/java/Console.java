import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import com.googlecode.lanterna.terminal.swing.TerminalEmulatorAutoCloseTrigger;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Console {

    public Screen screen;
    public TextGraphics game;
    public Level level;
    private int width = 77;
    private int height = 55;

    private boolean exitThread = false;

    /**This method is the constructor for the class Console.
     * It changes the font of the game, and assign a size to the terminal of the game.
     * It calls the class Level to create the map for the game.
     * In the future, we intend to be able to have more than one level, using an
     * array with the different levels (and their characteristics)*/
    public Console() throws FontFormatException, URISyntaxException {
        try {
            /*Import font for the game*/
            URL resource = getClass().getClassLoader().getResource("invader.ttf");
            File fontFile = new File(resource.toURI());
            Font font =  Font.createFont(Font.TRUETYPE_FONT, fontFile);

            TerminalSize terminalSize = new TerminalSize(width, height);
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);

            terminalFactory.setTerminalEmulatorTitle("Lonely Earth Invader");

            Font loadedFont = font.deriveFont(Font.PLAIN, 11);
            AWTTerminalFontConfiguration fontConfig = AWTTerminalFontConfiguration.newInstance(loadedFont);
            terminalFactory.setTerminalEmulatorFontConfiguration(fontConfig);
            terminalFactory.setForceAWTOverSwing(true);

            Terminal terminal = terminalFactory.createTerminal();

            screen = new TerminalScreen(terminal);
            screen.setCursorPosition(null);
            screen.startScreen();
            screen.doResizeIfNecessary();

        } catch (IOException e){
            e.printStackTrace();
        }

        level = new Level(screen.newTextGraphics());
    }

    /**This method clears and refresh the screen, and commands
     * Level to draw the game.*/
    private void draw() throws IOException{
        screen.clear();
        level.draw();
        screen.refresh();
    }

    /**This method processes if the key pressed by the user is ArrowLeft or Arrow Right.
     * If so, proceeds to move the Player accordingly.*/
    private void processKey(com.googlecode.lanterna.input.KeyStroke key){
        if(key.getKeyType() != KeyType.Character){
            switch (key.getKeyType()) {
                case ArrowLeft -> level.movePlayer(false);
                case ArrowRight -> level.movePlayer(true);
                case ArrowUp -> level.doAttackPlayer();
            }
        }
    }
    /**This method commands each Object in the Game to move by its own speed.
     * When an Object moves, it refreshes the console.*/
    public void run() {
        PlayerThread playerThread = new PlayerThread();
        WaveThread waveThread = new WaveThread();
        BulletsThread bulletsThread = new BulletsThread();
        while(true){
            playerThread.start();
            waveThread.start();
            bulletsThread.start();
        }
    }

    /**This class creates a independent thread to move the player*/
    class PlayerThread extends Thread{
        com.googlecode.lanterna.input.KeyStroke key;
        @Override
        public void run(){
            try {
                while(!exitThread) {
                    draw();
                    key = screen.readInput();
                    processKey(key);
                    //System.out.println(key.getCharacter());
                    if (key.getKeyType() == KeyType.Character && key.getCharacter() == ('q')) {
                        exitThread = true;
                        screen.close();
                    }
                    if (key.getKeyType() == KeyType.EOF) {
                        exitThread = true;
                        break;
                    }
                }
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }


    /**This class creates a independent thread from player to move the local*/
    class WaveThread extends Thread{
        @Override
        public void run(){
            try {
                while(!exitThread) {
                    draw();
                    update();
                }
            } catch (IOException e){
                e.printStackTrace();
            }
        }

        protected void update() {
            level.moveWave();
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    class BulletsThread extends Thread{
        @Override
        public void run(){
            try {
                while(!exitThread) {
                    draw();
                    update();
                }
            } catch (IOException e){
                e.printStackTrace();
            }
        }

        protected void update() {
            level.moveBullets();
            try {
                TimeUnit.MILLISECONDS.sleep(250);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}