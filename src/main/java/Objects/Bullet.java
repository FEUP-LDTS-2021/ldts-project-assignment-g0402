package Objects;


public class Bullet extends GameObject {

    public Bullet(String myName, Position position,
                  int height, int width, boolean destructible, int life,
                  int level, String sprite, int speed, boolean isMonsterBullet) {

        super(myName, position, height, width, destructible, life, level, sprite, speed);

        this.isMonster = isMonsterBullet;
    }

    public void moveBullet(){
        if(isMonster){
            this.moveDown();
        }
        else {
            this.moveUp();
        }
    }


    public boolean isBulletFromMonster (){
        return isMonster;
    }
}
