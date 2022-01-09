public class Player extends com.googlecode.lanterna.tutorial.GameObject {

    public Player(String myName, int xPosic, int yPosic,
                  int height, int width, boolean destructible,
                  int life, int level, int[][] sprite, int speed) {

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

}
