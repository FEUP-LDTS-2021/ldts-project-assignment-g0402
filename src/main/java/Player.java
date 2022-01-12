public class Player extends GameObject {

    public Player(String myName, int xPosic, int yPosic,
                  int height, int width, boolean destructible,
                  int life, int level, String sprite, int speed) {

        super(myName, xPosic, yPosic, height, width, destructible, life, level, sprite, speed);
    }

    public void moveLeft(){
        if(this.xPosic > 1){
            this.xPosic = this.xPosic - 1;
        }
    }

    public void moveRight(int width){
        if(this.xPosic < (width - this.width - 1)){
            this.xPosic = this.xPosic + 1;
        }
    }
    public Bullet doAttack(){
        return Attack.doAttack(this.xPosic, this.yPosic, false);
    }
}
