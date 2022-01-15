package Objects;

import com.googlecode.lanterna.TextColor;

import java.util.concurrent.TimeUnit;

public class Monster extends GameObject {
    public Monster(String myName, Position position,
                  int height, int width, int life, int level, String sprite, int speed) {
        super(myName, position, height, width, life, level, sprite, speed);
        this.color =  new TextColor.RGB(0,200,50);
        this.isMonster = true;
    }

    public Monster(String myName, int life, String sprite, int speed) {
        super(myName, new Position(0,0), 2, 1, life, 0, sprite, speed);
        this.color =  new TextColor.RGB(0,200,50);
    }
    
    public Monster(Monster other, int xPosic, int yPosic) {
        super(other.myName, new Position(xPosic, yPosic), other.height,
                other.width, other.life, other.level,
                other.sprite, other.speed);
        this.color =  new TextColor.RGB(0,200,50);
    }

    @Override
    public void moveRight(int width){
        this.position.setxPos(this.position.getxPos() + 1);
    }

    @Override
    public void moveLeft(){
        this.position.setxPos(this.position.getxPos() - 1);
    }

    @Override
    public void moveDown(){
        this.position.setyPos(this.position.getyPos() + 1);
    }

    @Override
    public void moveUp(){
        this.position.setyPos(this.position.getyPos() - 1);
    }



}
