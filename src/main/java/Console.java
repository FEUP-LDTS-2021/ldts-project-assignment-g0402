import com.googlecode.lanterna.TerminalSize;
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
    private int width = 77;
    private int height = 55;

    private boolean exitThread = false;
    /**This method is the constructor for that class.
     * They change the font for that game, and assign and change the size of terminal for the game.
     * Also, set the map for the game, in the class called level.
    * we pretend in the future has more than one level, setting a array the levels */
    public Console() throws FontFormatException, URISyntaxException {
        try {
            /*Import font for the game*/
            URL resource = getClass().getClassLoader().getResource("pixel8bit.ttf");
            File fontFile = new File(resource.toURI());
            Font font =  Font.createFont(Font.TRUETYPE_FONT, fontFile);

            TerminalSize terminalSize = new TerminalSize(width, height);
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);

            terminalFactory.setTerminalEmulatorTitle("Space Invaders");

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

        level = new Level( width, height);
    }

    /**This method clears and refresh the screen, and commands
     * Level to draw the game.*/
    private void draw() throws IOException{
        screen.clear();
        level.draw(screen.newTextGraphics());
        screen.refresh();
    }

    /**This method processes if the key pressed by the user is ArrowLeft or Arrow Right.
     * If so, proceeds to move the Player accordingly.*/
    private void processKey(com.googlecode.lanterna.input.KeyStroke key){
        System.out.println(key.getKeyType());
        switch (key.getKeyType()) {
            case ArrowLeft -> level.movePlayer(false);
            case ArrowRight -> level.movePlayer(true);
        }
    }
    /**This method assign each object in the game to move by his own speed.
     * Also, when any object moves, they refresh the console always.*/
    public void run() {
        PlayerThread playerThread = new PlayerThread();
        WaveThread waveThread = new WaveThread();
        while(true){
            playerThread.start();
            waveThread.start();
        }
    }

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
            level.moveWave( 2);
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    class PlayerThread extends Thread{
        com.googlecode.lanterna.input.KeyStroke key;
        @Override
        public void run(){
            try {
                while(!exitThread) {
                    draw();
                    key = screen.readInput();
                    processKey(key);
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

}