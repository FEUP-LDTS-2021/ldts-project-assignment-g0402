public class Bullet extends GameObject {
    private boolean isMonsterBullet;
    public Bullet(String myName, Position position,
                  int height, int width, boolean destructible, int life,
                  int level, String sprite, int speed, boolean isMonsterBullet) {
        super(myName, position, height, width, destructible, life, level, sprite, speed);
        this.isMonsterBullet = isMonsterBullet;
    }

    public void move(){
        if(isMonsterBullet){
            this.position.setyPos(this.position.getyPos() + 1);
        }
        else {
            this.position.setyPos(this.position.getyPos() - 1);
        }
    }
}
