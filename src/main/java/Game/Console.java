package Game;

import Objects.MonsterWave;
import Objects.Monster;
import Objects.Player;
import Objects.Attributes.Position;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
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

public class Console implements KeyBoardListener {

    enum Action{
        QUIT,
        LEFT,
        RIGHT,
        SHOOT
    }

    protected TerminalScreen screen = Play.screen;
    protected Level level;
    private boolean exitThread = false;
    private final int fireRate = 500;
    private boolean waveFinishWalk;

    /**
     * This method is the constructor for the class Game.Console.
     * It changes the font of the game, and assign a size to the terminal of the game.
     * It calls the class Game.Level to create the map for the game.
     * In the future, we intend to be able to have more than one level, using an
     * array with the different levels (and their characteristics)
     */

    public Console() throws FontFormatException, URISyntaxException {

        /*try {
            /*Import font for the game*/    /*
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
        }*/



        Player player = new Player("Player1", new Position(screen.newTextGraphics()),
                            3, 1, 3, "def", 1000);

        Monster monster = new Monster("Gabriel Coelho", 1, "pq", 4);

        MonsterWave wave = new MonsterWave(3, 3, 10, 5, 5, 3, monster);

        this.level = new Level(screen.newTextGraphics(), player, wave);

        this.waveFinishWalk = false;
    }

    @Override
    public void keyPressed(Action action) {
        try {
            switch (action) {
                case QUIT:
                    exitThread = true;
                    close();
                    Game.state = 1;
                    break;
                case LEFT:
                    level.movePlayer(false);
                    break;
                case RIGHT:
                    level.movePlayer(true);
                    break;
                case SHOOT:
                    if(System.currentTimeMillis() >= Game.fireDelay){
                        level.doAttackPlayer();
                        Game.fireDelay = System.currentTimeMillis() + fireRate;
                    }
                    break;
                default:
                    break;
            }
        }
        catch ( IOException e){
            e.printStackTrace();
        }

    }

    /**
     * This method clears and refresh the screen, and commands
     * Level to draw the game.
     */
    private void draw(){
        try {
            clear();
            this.level.draw();
            refresh();
            TimeUnit.MILLISECONDS.sleep(60);
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * This method commands each Object in the Game to move by its own speed.
     * When an Object moves, it refreshes the console.
     */
    public void run() {
        Thread DrawnThread = new Thread(() -> {
            while(!exitThread) {
                draw();
            }
        });

        Thread waveThread = new Thread(() -> {
            while(!exitThread) {
                updateWave();
            }
        });

        Thread bulletsThread = new Thread(() -> {
            while(!exitThread){
                updateBullets();
            }
        });

        DrawnThread.start();
        bulletsThread.start();
        waveThread.start();

        new Thread(() -> {
            try {
                while (!exitThread){
                    checkGameStatus();
                    Thread.sleep(800);
                }
            }catch (InterruptedException e){
                e.printStackTrace();
            }

        }).start();
    }



    /**This method is used for updating the location of the wave automatically*/
    protected void updateWave() {
        level.waveAttack();

        waveFinishWalk = level.wave.moveWave(Play.width);

        boolean looseGame = level.wave.moveWave(Play.width);
        if(looseGame) {
            exitThread = true;
        }

    }


    /**This method is used for updating the location of the bullets automatically*/
    protected void updateBullets() {
        level.updateBullets(Play.width);
    }

    private void gameOver(){

        try{
            TextGraphics graphics = screen.newTextGraphics();
            clear();

            graphics.setBackgroundColor(Game.colorScenario);
            graphics.setForegroundColor(Game.colorPlayer);
            graphics.fillRectangle(new TerminalPosition(0,0), new TerminalSize(Play.width, Play.height), ' ');
            graphics.putString(Play.width/2-5,Play.height/2, "GAME OVER");

            screen.refresh();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    private void gameWin(){

        try{
            TextGraphics graphics = screen.newTextGraphics();
            clear();

            graphics.setBackgroundColor(Game.colorScenario);
            graphics.setForegroundColor(Game.colorPlayer);
            graphics.fillRectangle(new TerminalPosition(0,0), new TerminalSize(Play.width, Play.height), ' ');
            graphics.putString(Play.width/2-5,Play.height/2, "YOU WIN!");

            screen.refresh();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void checkGameStatus(){
        if(!this.level.player.life.isAlive()){
            exitThread = true;
            gameOver();
        }
        else if(!this.level.wave.isWaveAlive()){
            exitThread = true;
            gameWin();
        }
        else if(waveFinishWalk){
            exitThread = true;
            gameOver();
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