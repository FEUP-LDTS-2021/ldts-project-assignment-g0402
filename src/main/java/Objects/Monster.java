package Objects;

import Objects.Attributes.Position;

public class Monster extends GameObject {

    public Monster(String myName, Position position,
                   int width, int height, int life, String sprite, int speed) {
        super(myName, position, width, height, life, sprite, speed);
        setIsMonster(true);
    }

    public Monster(String myName, int life, String sprite, int speed) {
        super(myName, new Position(0,0), 2, 1, life, sprite, speed);
        setIsMonster(true);
    }
    
    public Monster(Monster other, int xPosic, int yPosic) {
        super(other.myName, new Position(xPosic, yPosic), other.width,
                other.height, other.life.getLives(),
                other.sprite, other.speed);
        setIsMonster(true);
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


}
