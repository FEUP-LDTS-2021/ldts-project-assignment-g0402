import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.concurrent.TimeUnit;

public class Level {

    private int height;
    private int width;
    private Player player;

    protected MonsterWave wave;
    protected boolean endMoveReached = false;
    protected int maxDownMovements = 6;

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
    }

    public void draw(TextGraphics screen) {
        screen.setBackgroundColor(new TextColor.RGB(15,15,50));
        screen.fillRectangle(new TerminalPosition(0,0), new TerminalSize(width, height), ' ');

        player.draw(screen);
        wave.draw(screen);
    }

    public void movePlayer(boolean moveToRight){
        if(moveToRight){
            this.player.moveRight(this.width);
        }
        else{
            this.player.moveLeft();
        }
    }

    public void moveWave(int offset){
        boolean isMovingToRight;
        int ytop = this.wave.getPosLeft();
        int ydown = this.wave.getPosRight();

        if(ydown < width-3 && !endMoveReached) {
            isMovingToRight = true;
        }
        else{
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
                    TimeUnit.MILLISECONDS.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        this.wave.setPosY(offset, isMovingToRight);
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

}