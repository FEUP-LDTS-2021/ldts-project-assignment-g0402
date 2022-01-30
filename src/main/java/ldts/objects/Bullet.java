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

    /** This constructor creates a new instance of Bullet.   */
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

    /** This method draws the Bullet only if it's valid.  */
    public void draw(TextGraphics screen) {
        if(valid) screen.putString(position.getxPos(), position.getyPos(), Character.toString(this.sprite.charAt(0)));
    }

    /** This method moves the Bullet depending on who shot it.  */
    public void moveBullet(){
        if(valid && isMonsterBullet){
            this.position.setyPos(this.position.getyPos() + 1);
        }
        else {
            this.position.setyPos(this.position.getyPos() - 1);
        }

    }

    /** This method return true if the Bullet is valid.
     * A Bullet is valid when it's in the game. */
    public boolean isValid() {
        return valid;
    }

    /** This method updates the status of the Bullet to enter the game.  */
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

    /** This method updates the status of the Bullet when
     * it's no longer inside the game. */
    public void notUsed() {
        this.position = outside;
        this.valid = false;

    }

    /** This method returns true if the Bullet is shot by a Monster.  */
    public boolean isMonster() {
        return isMonsterBullet;
    }
}
