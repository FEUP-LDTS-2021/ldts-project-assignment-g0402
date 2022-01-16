package Game;

import Objects.MonsterWave;
import Objects.Monster;
import Objects.Player;
import Objects.Attributes.Position;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFrame;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Console implements KeyBoardListener{
    @Override
    public void keyPressed(Action action) {
        try {
            switch (action) {
                case QUIT:
                    exitThread = true;
                    close();
                    Game.getInstance().exit=true;
                    break;
                case LEFT:
                    level.movePlayer(false);
                    break;
                case RIGHT:
                    level.movePlayer(true);
                    break;
                case SHOOT:
                    level.doAttackPlayer();
                    break;
                default:
                    break;
            }
        }
        catch (IOException | URISyntaxException | FontFormatException e){
            e.printStackTrace();
        }
    }

    enum Action{
        QUIT,
        LEFT,
        RIGHT,
        SHOOT
    }

    protected TerminalScreen screen;
    protected Level level;
    private final int width = 64;
    private final int height = 36;
    private boolean exitThread = false;
    private final int sizeFont = 20;

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
                            3, 1, 1, "def", 1000);

        Monster monster = new Monster("Gabriel Coelho", 1, "pq", 4);

        MonsterWave wave = new MonsterWave(3, 3, 10, 5, 5, 3, monster);

        this.level = new Level(screen.newTextGraphics(), player, wave);
    }


    /**
     * This method clears and refresh the screen, and commands
     * Level to draw the game.
     */
    private void draw() throws IOException {
        clear();
        this.level.draw();
        refresh();
    }


    /**
     * This method commands each Object in the Game to move by its own speed.
     * When an Object moves, it refreshes the console.
     */
    public void run() {
        MovePlayerThread movePlayerThread = new MovePlayerThread();
        WaveThread waveThread = new WaveThread();
        BulletsThread bulletsThread = new BulletsThread();
        movePlayerThread.start();
        bulletsThread.start();
        waveThread.start();
    }


    /**
     * This class creates an independent thread to move the player
     */
    class MovePlayerThread extends Thread {

        @Override

        public void run() {
            try {
                while (!exitThread) {
                    draw();
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
            //level.MonsterAttacks(level.player, level.wave);   //this for some reason is not working HELP :(
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

    public void addKeyBoardListener(KeyBoardObserver obs) {
        ((AWTTerminalFrame) screen.getTerminal()).getComponent(0).addKeyListener(obs);
    }

    public void clear() {
        screen.clear();
    }

    public void refresh() throws IOException {
        screen.refresh();
        screen.doResizeIfNecessary();
    }

    public void close() throws IOException {
        screen.close();
    }

}