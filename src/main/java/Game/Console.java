package Game;

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
    public Level level;
    private int width = 64;
    private int height = 36;

    private boolean exitThread = false;

    /**This method is the constructor for the class Game.Console.
     * It changes the font of the game, and assign a size to the terminal of the game.
     * It calls the class Game.Level to create the map for the game.
     * In the future, we intend to be able to have more than one level, using an
     * array with the different levels (and their characteristics)*/
    public Console() throws FontFormatException, URISyntaxException {
        try {
            /*Import font for the game*/
            URL resource = getClass().getClassLoader().getResource("invaderv2.ttf");
            File fontFile = new File(resource.toURI());
            Font font =  Font.createFont(Font.TRUETYPE_FONT, fontFile);

            TerminalSize terminalSize = new TerminalSize(width, height);
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);

            terminalFactory.setTerminalEmulatorTitle("Lonely Earth Invader");

            Font loadedFont = font.deriveFont(Font.PLAIN, 20);
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
     * Game.Level to draw the game.*/
    private void draw() throws IOException{
        screen.clear();
        level.draw();
        screen.refresh();
    }

    /**This method processes if the key pressed by the user is ArrowLeft or Arrow Right.
     * If so, proceeds to move the Objects.Player accordingly.*/
    private void processKey(com.googlecode.lanterna.input.KeyStroke key){
        if(key.getKeyType() != KeyType.Character){
            switch (key.getKeyType()) {
                case ArrowLeft -> level.movePlayer(false);
                case ArrowRight -> level.movePlayer(true);
                case ArrowUp -> level.doAttackPlayer();
            }
        }
        else{
            if(key.getCharacter() == (' ')){
                level.doAttackPlayer();
            }
        }
    }
    /**This method commands each Object in the Game to move by its own speed.
     * When an Object moves, it refreshes the console.*/
    public void run() {
        PlayerThread playerThread = new PlayerThread();
        WaveThread waveThread = new WaveThread();
        BulletsThread bulletsThread = new BulletsThread();
        playerThread.start();
        waveThread.start();
        bulletsThread.start();
    }

    /**This class creates a independent thread to move the player*/
    class PlayerThread extends Thread{
        com.googlecode.lanterna.input.KeyStroke key;
        @Override
        /**This method is used by super class Thread in a cycle to draw objects and update positions*/
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

        /**This method is used for update location for player after pressed some key*/
        protected void update() {
            try {
                key = screen.readInput();
                processKey(key);
                if (key.getKeyType() == KeyType.Character && key.getCharacter() == ('q')) {
                    exitThread = true;
                    screen.close();
                }
                if (key.getKeyType() == KeyType.EOF) {
                    exitThread = true;
                }
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
    }


    /**This class creates a independent thread to move all the waves*/
    class WaveThread extends Thread{
        /**This method is used by super class Thread in a cycle to draw objects and update positions*/
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

        /**This method is used for update location for wave automatic*/
        protected void update() {
            level.moveWave();
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    /**This class creates a independent thread to move all the bullets*/
    class BulletsThread extends Thread{
        /**This method is used by super class Thread in a cycle to draw objects and update positions*/
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
        /**This method is used for update location for bullets automatic*/
        protected void update() {
            level.moveBullets();
            try {
                TimeUnit.MILLISECONDS.sleep(150);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}