package ldts.game;

import ldts.objects.attributes.Position;
import ldts.objects.Monster;
import ldts.objects.MonsterWave;
import ldts.objects.Player;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFrame;
import ldts.game.states.Play;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Console implements KeyBoardListener {

    enum Action{
        QUIT,
        LEFT,
        RIGHT,
        SHOOT
    }

    protected Level level;
    protected boolean exitThread = false;
    private boolean waveFinishWalk;

    /**
     * This method is the constructor for the class Game.Console.
     * It changes the font of the game, and assign a size to the terminal of the game.
     * It calls the class Game.Level to create the map for the game.
     * In the future, we intend to be able to have more than one level, using an
     * array with the different levels (and their characteristics)
     */

    public Console(TextGraphics graphics) {

        Player player = new Player("Player1", new Position(graphics),
                            3, 1, 3, "def", 1000);

        Monster monster = new Monster("Gabriel Coelho", 1, "pq", 4);

        MonsterWave wave = new MonsterWave(3, 3, 10, 5, 5, 3, monster);

        this.level = new Level(graphics, player, wave);

        this.waveFinishWalk = false;
    }

    @Override
    public void keyPressed(Action action) {
        switch (action) {
            case QUIT:
                Game.state = 1;
                exitThread = true;
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
                    int fireRate = 500;
                    Game.fireDelay = System.currentTimeMillis() + fireRate;
                }
                break;
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
            }catch (InterruptedException | IOException e){
                e.printStackTrace();
            }

        }).start();
    }



    /**This method is used for updating the location of the wave automatically*/
    protected void updateWave() {
        level.waveAttack();

        waveFinishWalk = level.wave.moveWave(Play.width, Play.height);

    }


    /**This method is used for updating the location of the bullets automatically*/
    protected void updateBullets() {
        level.updateBullets(Play.width);
    }

    private void gameOver(){

        try{
            clear();
            TextGraphics graphics = Play.getScreen().newTextGraphics();

            graphics.setBackgroundColor(Game.colorScenario);
            graphics.setForegroundColor(Game.colorPlayer);
            graphics.fillRectangle(new TerminalPosition(0,0), new TerminalSize(Play.width, Play.height), ' ');
            graphics.putString(Play.width/2-5,Play.height/2, "GAME OVER");

            //Reminder of how to quit the game
            graphics.putString(Play.width-9, Play.height-2, "Q: QUIT");

            Play.getScreen().refresh();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    private void gameWin(){

        try{
            clear();
            TextGraphics graphics = Play.getScreen().newTextGraphics();

            graphics.setBackgroundColor(Game.colorScenario);
            graphics.setForegroundColor(Game.colorPlayer);
            graphics.fillRectangle(new TerminalPosition(0,0), new TerminalSize(Play.width, Play.height), ' ');
            graphics.putString(Play.width/2-5,Play.height/2, "YOU WIN!");

            //Reminder of how to quit the game
            graphics.putString(Play.width-9, Play.height-2, "Q: QUIT");

            Play.getScreen().refresh();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void checkGameStatus() throws IOException {
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
        ((AWTTerminalFrame) Play.getScreen().getTerminal()).getComponent(0).addKeyListener(obs);
    }

    public void clear() {
        Play.getScreen().clear();
    }

    public void refresh() throws IOException {
        Play.getScreen().refresh();
        Play.getScreen().doResizeIfNecessary();
    }

}