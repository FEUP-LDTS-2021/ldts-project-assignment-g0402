package Actions;

import Objects.Bullet;
import Objects.GameObject;
import Objects.Attributes.Position;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
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

