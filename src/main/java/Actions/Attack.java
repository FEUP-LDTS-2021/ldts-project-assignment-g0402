package Actions;

import Objects.Bullet;
import Objects.GameObject;
import Objects.Attributes.Position;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.Vector;


public class Attack {
    private Vector<Bullet> bullets = new Vector<>();
    private TextColor.RGB color = new TextColor.RGB(255,255,255);

    public static Bullet doAttack(int xPos, int yPos, boolean isFromMonster){
        Bullet bullet;
        if (isFromMonster){
            bullet = new Bullet("bala", new Position(xPos+1, yPos+1), 1,
                    1, 1, 1, "z", 30, isFromMonster);
        }
        else{
            bullet = new Bullet("bala", new Position(xPos+1, yPos-1), 1,
                    1,  1, 1, "z", 30, isFromMonster);

        }
        return bullet;
    }

    public void move() {
        for (Bullet bullet : this.bullets) {
            bullet.moveBullet();
        }
    }

    public void draw(TextGraphics screen) {
        for (Bullet bullet : bullets) {
            screen.setForegroundColor(this.color);
            bullet.draw(screen);

            if(bullet.position.getyPos() <= 0){
                eliminateBullet();
            }

        }
    }

    public void addBullet(GameObject attacker){
        this.bullets.add(attacker.doAttack());
    }

    public void eliminateBullet(){
        this.bullets.remove(this.bullets.firstElement());
    }
}

