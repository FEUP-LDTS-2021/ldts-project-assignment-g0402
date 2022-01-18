package Actions;

import Objects.Bullet;
import Objects.GameObject;
import Objects.Attributes.Position;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.ArrayList;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;


public class Attack {
    CopyOnWriteArrayList<Bullet> bullets = new CopyOnWriteArrayList<>();
    private TextColor.RGB color = new TextColor.RGB(255,255,255);

    public void move() {
        for (Bullet bullet : this.bullets) {
            bullet.moveBullet();
        }
    }

    public void draw(TextGraphics screen) {
        for (Bullet bullet : bullets) {
            screen.setForegroundColor(this.color);
            bullet.draw(screen);

        }
    }
    public void checkCollision(GameObject object) {
        ArrayList<Integer> bulletsToDestroy = new ArrayList<Integer>();
        int i = 0;
        for (Bullet bullet: bullets) {
            if (bullet.position.getxPos() >= object.position.getxPos() &&
                    bullet.position.getxPos() <= object.position.getxPos()+object.getWidth()-1 &&
                    bullet.position.getyPos() >= object.position.getyPos() &&
                    bullet.position.getyPos() <= object.position.getyPos()+object.getHeight()-1
                    && object.life.isAlive()){
                object.kill();
                bulletsToDestroy.add(i);
            }
            i++;
        }
        for (int j = bulletsToDestroy.size()-1; j >= 0; j--) {
            bullets.remove(j);
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

