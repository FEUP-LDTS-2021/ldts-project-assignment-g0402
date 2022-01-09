public class Bullet extends com.googlecode.lanterna.tutorial.GameObject {
    private boolean isMonsterBullet;
    public Bullet(String myName, int xPosic, int yPosic,
                   int height, int width, boolean destructible,
                   int life, int level, int[][] sprite, int speed, boolean isMonsterBullet) {
        super(myName, xPosic, yPosic, height, width, destructible, life, level, sprite, speed);
        this.isMonsterBullet = isMonsterBullet;
    }

    public void move(){
        if(isMonsterBullet){
            this.yPosic = this.yPosic + 1;
        }
        else {
            this.yPosic = this.yPosic - 1;
        }
    }
}
