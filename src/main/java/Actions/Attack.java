package Actions;

import Objects.Bullet;
import Objects.GameObject;
import Objects.Player;
import Objects.Position;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.ArrayList;
import java.util.List;
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
        for (Bullet bull : this.bullets) {
            bull.moveBullet();
        }
    }

    public void draw(TextGraphics screen) {
        for (Bullet bull : bullets) {
            screen.setForegroundColor(this.color);
            bull.draw(screen);
        }
    }

    public void addBullet(GameObject attacker){
        this.bullets.add(attacker.doAttack());
    }
}

