import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Level {

    private int height;
    private int width;
    private Player player;

    protected MonsterWave wave;

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
        screen.setBackgroundColor(new TextColor.RGB(0,51,102));
        screen.fillRectangle(new TerminalPosition(0,0), new TerminalSize(width, height), ' ');

        player.draw(screen);
        wave.drawWave(screen);
    }

    public void movePlayer(boolean moveToRight){
        if(moveToRight){
            player.moveRight(width);
        }
        else{
            player.moveLeft(width);
        }
    }

    public void moveWave(int offset){
        boolean isMovingToRight;
        int ytop = this.wave.getPosLeft();
        /*int xtop = this.wave.getPosUp();
        int ydown = this.wave.getPosRight();
        int xdown = this.wave.getPosDown();*/

        if(ytop < width-3) {
            isMovingToRight = true;
        }
        else{
            isMovingToRight = false;
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