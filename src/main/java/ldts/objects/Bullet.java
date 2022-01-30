package ldts.objects;


import ldts.game.Game;
import ldts.objects.attributes.Position;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;


public class Bullet{
    public Position position;
    private String sprite;
    private boolean isMonsterBullet;
    private boolean valid;
    public TextColor color;
    private final Position outside = new Position(-1,-1);




    public Bullet(Position position, String sprite, boolean isMonsterBullet) {
        this.valid = false;

        this.position = position;
        this.sprite = sprite;
        this.isMonsterBullet = isMonsterBullet;
        if (this.isMonsterBullet){
            this.color = Game.colorMonster;
        }
        else{
            this.color = Game.colorPlayer;
        }
    }

    public void draw(TextGraphics screen) {
        if(valid) screen.putString(position.getxPos(), position.getyPos(), Character.toString(this.sprite.charAt(0)));
    }

    public void moveBullet(){
        if(valid && isMonsterBullet){
            this.position.setyPos(this.position.getyPos() + 1);
        }
        else {
            this.position.setyPos(this.position.getyPos() - 1);
        }

    }
    public boolean isValid() {
        return valid;
    }
    public void used(Position position, String sprite, boolean isFromMonster) {
        this.position = position;
        this.sprite = sprite;
        this.isMonsterBullet = isFromMonster;
        this.valid = true;
        if (this.isMonsterBullet){
            this.color = Game.colorMonster;
        }
        else{
            this.color = Game.colorPlayer;
        }
    }

    public void notUsed() {
        this.position = outside;
        this.valid = false;

    }
    public boolean isMonster() {
        return isMonsterBullet;
    }
}
