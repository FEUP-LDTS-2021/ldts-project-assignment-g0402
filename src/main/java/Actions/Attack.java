package Actions;

import Game.Game;
import Objects.Bullet;
import Objects.GameObject;
import Objects.Attributes.Position;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

//Object Pool
public class Attack {
    public ArrayList<Bullet> bullets = new ArrayList<>();
    private Position outside = new Position(-1,-1);

    public Attack(){
        for (int i = 0; i < 100; i++) {
            bullets.add(new Bullet(outside, "z",1,false));
        }
    }

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

    public void draw(TextGraphics screen) {
        for (Bullet bullet : bullets) {
            screen.setForegroundColor(bullet.color);
            bullet.draw(screen);
        }
    }
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
     * This class creates an independent thread to move the player
     */
    public void doAttack(int xPos, int yPos, boolean isFromMonster){
        for (Bullet bullet : bullets) {
            if (!bullet.isValid()) {
                if (isFromMonster) {
                    bullet.used(new Position(xPos + 1, yPos + 1), "z", 1, isFromMonster);
                    break;
                } else {
                    bullet.used(new Position(xPos + 1, yPos - 1), "z", 1, isFromMonster);
                    break;
                }
            }
        }
    }
}

