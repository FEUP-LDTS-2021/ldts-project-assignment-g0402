import com.googlecode.lanterna.graphics.TextGraphics;

public class Monster extends GameObject {
    public Monster(String myName, int xPosic, int yPosic,
                  int height, int width, boolean destructible,
                  int life, int level, int[][] sprite, int speed) {
        super(myName, xPosic, yPosic, height, width, destructible, life, level, sprite, speed);
    }

    public void moveLeft(){
        this.xPosic = this.xPosic - 1;
    }

    public void moveDown(){
        this.yPosic = this.yPosic +1;
    }

    public void moveRight(){
        this.xPosic = this.xPosic + 1;
    }

}
