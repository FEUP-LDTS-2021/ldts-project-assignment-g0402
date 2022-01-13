package Objects;

import Actions.*;

public class Player extends GameObject {

    public Player(String myName, Position position,
                  int height, int width, boolean destructible,
                  int life, int level, String sprite, int speed) {

        super(myName, position, height, width, destructible, life, level, sprite, speed);
    }

    public void moveLeft(){
        if(this.position.getxPos() > 1){
            this.position.setxPos(this.position.getxPos() - 1);
        }
    }

    public void moveRight(int width){
        if(this.position.getxPos() < (width - this.width - 1)){
            this.position.setxPos(this.position.getxPos() + 1);
        }
    }


    public Bullet doAttack() {
        return Attack.doAttack(this.position.getxPos()+1, this.position.getyPos(), false);
    }
}
