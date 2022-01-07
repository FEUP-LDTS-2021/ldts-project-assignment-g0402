import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.ArrayList;
import java.util.List;


public class Level {

    private int height;
    private int width;
    private Player player;
    protected MonsterWave wave;
    protected int maxDownMovements = 6;
    private boolean isMovingToRight = true;
    private List<Bullet> bullets;
    public Level(int width, int height){
        this.height = height;
        this.width = width;
        int[][] sprite = new int[][]{{0,0,1,0}, {1,0,1,1}, {1,1,0,1}, {0,1,0,0}};
        int heightPlayer = 2;
        int widthPlayer = 2;
        int distanceFromConsoleFloor = 4;
        this.player = new Player("Gabriel Coelho", width/2-widthPlayer,
                height-widthPlayer-distanceFromConsoleFloor, heightPlayer,
                widthPlayer, true, 1, 1, sprite, 5);

        this.wave = new MonsterWave(3,3 ,12, 8);
        this.wave.populateWave();
        this.bullets = new ArrayList<Bullet>();
    }

    public void draw(TextGraphics screen) {
        screen.setBackgroundColor(new TextColor.RGB(15,20,45));
        screen.fillRectangle(new TerminalPosition(0,0), new TerminalSize(width, height), ' ');

        player.draw(screen);
        wave.draw(screen);
        for (Bullet bull: bullets) {
            bull.draw(screen);
        }
    }

    public void movePlayer(boolean moveToRight){
        if(moveToRight){
            this.player.moveRight(this.width);
        }
        else{
            this.player.moveLeft();
        }
    }
    public void moveBullets(){
        for (Bullet bull: this.bullets) {
            bull.move();
        }
    }
    public void doAttackPlayer(){
        this.bullets.add(this.player.doAttack());
    }

    public boolean moveWave(){
        int yMin = this.wave.getPosLeft();
        int yMax = this.wave.getPosRight();
        System.out.println(yMax);
        System.out.println(yMin);
        System.out.println(this.isMovingToRight);

        if(yMin < 2 && !this.isMovingToRight){
            this.isMovingToRight = true;
            wave.moveDown();
            --maxDownMovements;
            System.out.println("oi");

        }
        else if(yMax > this.width-4 && this.isMovingToRight) {
            this.isMovingToRight = false;
            this.wave.moveDown();
            --maxDownMovements;
            System.out.println("oi");
        }

        else if(isMovingToRight){
            wave.moveRight();
        }

        else{
            wave.moveLeft();
        }


        if(maxDownMovements<0){
            return false;
        }
        else{
<<<<<<< HEAD
            return true;
=======
            endMoveReached = true;
            if(ytop > 3)
                isMovingToRight = false;
            else{
                isMovingToRight = true;
                endMoveReached = false;

                if(maxDownMovements > 0) {
                    this.wave.setPosDown(offset);
                    --maxDownMovements;
                }

                try {
                    //after going down->just wait a few ms before move backwards
                    TimeUnit.MILLISECONDS.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
>>>>>>> c0ac190 (Created Test Class for Player AND Created Class Position)
        }
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

}