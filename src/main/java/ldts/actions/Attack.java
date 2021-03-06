package ldts.actions;

import ldts.game.Game;
import ldts.objects.attributes.Position;
import ldts.objects.Bullet;
import ldts.objects.GameObject;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/** This class coordinates the relation between the objects of
 * the Game and the bullets, so that the attack can occur
 **/
public class Attack {
    public ArrayList<Bullet> bullets = new ArrayList<>();

    /**
     * This initiates a new instance of the class Attack, adding a bullet
     **/
    public Attack(){
        for (int i = 0; i < 100; i++) {
            Position outside = new Position(-1, -1);
            bullets.add(new Bullet(outside, "z", false));
        }
    }

    /**
     * This method updates the position of the bullet on the screen, so it seems that the bullet is moving.
     **/
    public void move(int width) {
        for (Bullet bullet : this.bullets) {
            bullet.moveBullet();
            if(bullet.position.getyPos()<0){
                bullet.notUsed();
            }
            else if(bullet.position.getyPos()>width){
                bullet.notUsed();
            }
        }
        try {
            TimeUnit.MILLISECONDS.sleep(Game.refreshTime / 20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method draws the bullets on the screen.
     **/
    public void draw(TextGraphics screen) {
        for (Bullet bullet : bullets) {
            screen.setForegroundColor(bullet.color);
            bullet.draw(screen);
        }
    }

    /**
     * This method checks if a bullet reached the position of the given GameObject.
     **/
    public void checkCollision(GameObject object) {
        for (Bullet bullet : bullets) {
            if (bullet.position.getxPos() >= object.position.getxPos() &&
                    bullet.position.getxPos() <= object.position.getxPos() + object.getWidth() - 1 &&
                    bullet.position.getyPos() >= object.position.getyPos() &&
                    bullet.position.getyPos() <= object.position.getyPos() + object.getHeight() - 1
                    && object.life.isAlive() && object.isMonster() != bullet.isMonster() && bullet.isValid()) {
                object.kill();
                bullet.notUsed();
            }
        }
    }

    /**
     * This method gets the first bullet that's not valid and uses it for the game.
     * This allows the bullets that were already shot and are out of the game, to be reused.
     **/
    public void doAttack(int xPos, int yPos, boolean isFromMonster){
        for (Bullet bullet : bullets) {
            if (!bullet.isValid()) {
                if (isFromMonster) {
                    bullet.used(new Position(xPos + 1, yPos + 1), "z", true);
                } else {
                    bullet.used(new Position(xPos + 1, yPos - 1), "z", false);
                }
                break;
            }
        }
    }
}

