import com.googlecode.lanterna.graphics.TextGraphics;

public class Monster extends com.googlecode.lanterna.tutorial.GameObject {
    public Monster(String myName, int xPosic, int yPosic,
                  int height, int width, boolean destructible,
                  int life, int level, int[][] sprite, int speed) {
        super(myName, xPosic, yPosic, height, width, destructible, life, level, sprite, speed);
    }



}