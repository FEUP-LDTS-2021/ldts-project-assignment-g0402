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


public class Attack {
    public CopyOnWriteArrayList<Bullet> bullets = new CopyOnWriteArrayList<>();

    public void move() {
        for (Bullet bullet : this.bullets) {
            bullet.moveBullet();
        }
        try {
            TimeUnit.MILLISECONDS.sleep(Game.refreshTime/50);
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
        ArrayList<Integer> bulletsToDestroy = new ArrayList<>();
        int i = 0;
        for (Bullet bullet: bullets) {
            if (bullet.position.getxPos() >= object.position.getxPos() &&
                    bullet.position.getxPos() <= object.position.getxPos()+object.getWidth()-1 &&
                    bullet.position.getyPos() >= object.position.getyPos() &&
                    bullet.position.getyPos() <= object.position.getyPos()+object.getHeight()-1
                    && object.life.isAlive() && object.isMonster() != bullet.isMonster() && bullet.isValid()){
                object.kill();
                bulletsToDestroy.add(i);
                bullet.used();
            }
            i++;
        }
    }
    /**
     * This class creates an independent thread to move the player
     */
    public void doAttack(int xPos, int yPos, boolean isFromMonster){
        if (isFromMonster){
            bullets.add(new Bullet(new Position(xPos+1, yPos+1), "z",1, isFromMonster));
        }
        else{
            bullets.add(new Bullet(new Position(xPos+1, yPos-1), "z",1, isFromMonster));
        }
    }
}

