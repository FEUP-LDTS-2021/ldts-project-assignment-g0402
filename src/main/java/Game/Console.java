package Game;

import Objects.MonsterWave;
import Objects.Monster;
import Objects.Player;
import Objects.Position;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
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

    protected Screen screen;
    protected Level level;
    private int width = 64;
    private int height = 36;
    private boolean exitThread = false;
    private int sizeFont = 20;

    /**
     * This method is the constructor for the class Game.Console.
     * It changes the font of the game, and assign a size to the terminal of the game.
     * It calls the class Game.Level to create the map for the game.
     * In the future, we intend to be able to have more than one level, using an
     * array with the different levels (and their characteristics)
     */
    public Console() throws FontFormatException, URISyntaxException {
        try {
            /*Import font for the game*/
            URL resource = getClass().getClassLoader().getResource("invaderv2.ttf");
            File fontFile = new File(resource.toURI());
            Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile);

            TerminalSize terminalSize = new TerminalSize(width, height);
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);

            terminalFactory.setTerminalEmulatorTitle("Lonely Earth Invader");

            Font loadedFont = font.deriveFont(Font.PLAIN, sizeFont);
            AWTTerminalFontConfiguration fontConfig = AWTTerminalFontConfiguration.newInstance(loadedFont);
            terminalFactory.setTerminalEmulatorFontConfiguration(fontConfig);
            terminalFactory.setForceAWTOverSwing(true);

            Terminal terminal = terminalFactory.createTerminal();

            this.screen = new TerminalScreen(terminal);
            this.screen.setCursorPosition(null);
            this.screen.startScreen();
            this.screen.doResizeIfNecessary();

        } catch (IOException e) {
            e.printStackTrace();
        }
        Player player = new Player("Player1", new Position(screen.newTextGraphics()),
                            3, 1, 1, 1, "def", 1000);

        Monster monster = new Monster("Gabriel Coelho", 1, "pq", 4);

        MonsterWave wave = new MonsterWave(3, 3, 10, 5, 5, 3, monster);

        this.level = new Level(screen.newTextGraphics(), player, wave);
    }

    /**
     * This method clears and refresh the screen, and commands
     * Game.Level to draw the game.
     */
    private void draw() throws IOException {
        this.screen.clear();
        this.level.draw();
        this.screen.refresh();
    }

    /**
     * This method processes the key pressed by the user.
     * And proceeds to move the Player accordingly.
     */
    private void processKey(KeyStroke key) {
        try{
            if (key.getKeyType() == KeyType.ArrowUp || (key.getKeyType() == KeyType.Character && key.getCharacter() == ' ')) {
                level.doAttackPlayer();
            }
            else if (key.getKeyType() == KeyType.Character && key.getCharacter() == ('q')) {
                exitThread = true;
                screen.close();
            }
            else if(key.getKeyType() == KeyType.ArrowLeft) {
                level.movePlayer(false);
            }
            else if(key.getKeyType() == KeyType.ArrowRight){
                level.movePlayer(true);
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * This method commands each Object in the Game to move by its own speed.
     * When an Object moves, it refreshes the console.
     */
    public void run() {
        MovePlayerThread movePlayerThread = new MovePlayerThread();
        AttackPlayerThread attackPlayerThread = new AttackPlayerThread();
        WaveThread waveThread = new WaveThread();
        BulletsThread bulletsThread = new BulletsThread();
        movePlayerThread.start();
        bulletsThread.start();
        attackPlayerThread.start();
        waveThread.start();
    }

    /**
     * This class creates an independent thread to move the player
     */
    class MovePlayerThread extends Thread {
        KeyStroke key;

        @Override

        public void run() {
            try {
                while (!exitThread) {
                    draw();
                    update();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /**
         * This method is used for updating the location of the player
         * after the user presses keys on the keyboard
         */
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
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * This class creates an independent thread to move the player
     */
    class AttackPlayerThread extends Thread {
        KeyStroke key;

        @Override
        public void run() {
            try {
                while (!exitThread) {
                    draw();
                    update();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /**
         * This method is used for updating the location of the player
         * after the user presses keys on the keyboard
         */
        protected void update() {
            try {
                key = screen.readInput();
                processKey(key);

                if (key.getKeyType() == KeyType.EOF) {
                    exitThread = true;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**This class creates an independent thread to move the wave*/
    class WaveThread extends Thread{

        /**This method is used by the super class Thread in a cycle to draw objects and update positions*/
        @Override
        public void run(){
            try {
                while(!exitThread) {
                    draw();
                    update();
                }
                screen.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        }

        /**This method is used for updating the location of the wave automatically*/
        protected void update() {
            boolean looseGame = level.wave.moveWave(width);
            if(looseGame) {
                exitThread = true;
            }
        }
    }

    /**This class creates an independent thread to move all the bullets*/
    class BulletsThread extends Thread{
        /**This method is used by the super class Thread in a cycle to draw objects and update positions*/
        @Override
        public void run(){

            try {
                while(!exitThread) {
                    try {
                        TimeUnit.MILLISECONDS.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    draw();
                    update();
                }
            } catch (IOException e){
                e.printStackTrace();
            }
        }

        /**This method is used for updating the location of the bullets automatically*/
        protected void update() {
            level.updateBullets();
        }
    }

}