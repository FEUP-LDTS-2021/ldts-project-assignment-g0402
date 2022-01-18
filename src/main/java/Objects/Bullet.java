package Objects;


import Objects.Attributes.Position;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Bullet{
    public Position position;
    private String sprite;
    private int speed;
    private boolean isMonsterBullet;
    TextColor.RGB color;
    public Bullet(Position position, String sprite, int speed, boolean isMonsterBullet) {

        this.position = position;
        this.sprite = sprite;
        this.speed = speed;
        this.isMonsterBullet = isMonsterBullet;
        if (this.isMonsterBullet){
            this.color =  new TextColor.RGB(0,200,50);
        }
        else{
            this.color = new TextColor.RGB(255,255,255);
        }
    }

    public void draw(TextGraphics screen) {
        screen.putString(position.getxPos(), position.getyPos(), Character.toString(this.sprite.charAt(0)));
    }

    public void moveBullet(){
        if(isMonsterBullet){
            this.position.setyPos(this.position.getyPos() + 1);
        }
        else {
            this.position.setyPos(this.position.getyPos() - 1);
        }
    }


    public boolean isBulletFromMonster (){
        return isMonsterBullet;
    }
}
