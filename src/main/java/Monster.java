import com.googlecode.lanterna.graphics.TextGraphics;

public class Monster extends GameObject {
    public Monster(String myName, int xPosic, int yPosic,
                  int height, int width, boolean destructible,
                  int life, int level, String sprite, int speed) {
        super(myName, xPosic, yPosic, height, width, destructible, life, level, sprite, speed);
    }

    public Monster(String myName, boolean destructible,
                   int life, String sprite, int speed) {
        super(myName, 0, 0, 0, 0, destructible, life, 0, sprite, speed);
    }
    public Monster(Monster other, int xPosic, int yPosic) {
        super(other.myName, xPosic, yPosic, other.height,
                other.width, other.destructible, other.life, other.level,
                other.sprite, other.speed);
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
