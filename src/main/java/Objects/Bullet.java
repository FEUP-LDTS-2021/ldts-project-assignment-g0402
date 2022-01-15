package Objects;


import Objects.Attributes.Position;
import com.googlecode.lanterna.TextColor;

public class Bullet extends GameObject {

    public Bullet(String myName, Position position,
                  int height, int width, int life,
                  int level, String sprite, int speed, boolean isMonsterBullet) {

        super(myName, position, height, width, life, sprite, speed);
        this.isMonster = isMonsterBullet;
        if (this.isMonster){
            this.color =  new TextColor.RGB(0,200,50);
        }
        else{
            this.color = new TextColor.RGB(255,255,255);
        }
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
