import com.googlecode.lanterna.graphics.TextGraphics;

public class Monster extends GameObject {
    public Monster(String myName, Position position,
                  int height, int width, boolean destructible,
                   int life, int level, String sprite, int speed) {
        super(myName, position, height, width, destructible, life, level, sprite, speed);
    }

    public Monster(String myName, boolean destructible,
                   int life, String sprite, int speed) {
        super(myName, new Position(0,0), 2, 1,  destructible, life, 0, sprite, speed);
    }
    
    public Monster(Monster other, int xPosic, int yPosic) {
        super(other.myName, new Position(xPosic, yPosic), other.height,
                other.width, other.destructible, other.life, other.level,
                other.sprite, other.speed);
    }


    public void moveLeft(){
        this.position.setxPos(this.position.getxPos() - 1);
    }

    public void moveDown(){
        this.position.setyPos(this.position.getyPos() + 1);
    }

    public void moveRight(){
        this.position.setxPos(this.position.getxPos() + 1);
    }

}
