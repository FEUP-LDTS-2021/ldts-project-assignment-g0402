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
    public Level(int width, int height){
        this.height = height;
        this.width = width;
        int[][] sprite = new int[][]{{0,0,1,0}, {1,0,1,1}, {1,1,0,1}, {0,1,0,0}};
        this.player = new Player("Gabriel Coelho", 0, 0,
                2, 2, true, 1, 1, sprite, 5);
    }

    public void draw(TextGraphics screen) {
        screen.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        screen.fillRectangle(new TerminalPosition(0,0), new TerminalSize(width, height), ' ');

        player.draw(screen);
    }


    public void moveHero(boolean isToRight){
        if(isToRight){
            player.moveRight(width);
        }
        else{
            player.moveLeft(width);
        }

    }


    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

}