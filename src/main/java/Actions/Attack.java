package Actions;

import Objects.Bullet;
import Objects.Player;
import Objects.Position;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.ArrayList;
import java.util.List;


public class Attack {
    private List<Bullet> bullets = new ArrayList<>();
    private TextColor.RGB color;

    public static Bullet doAttack(int xPos, int yPos, boolean isFromMonster){
        Bullet bullet;
        if (isFromMonster){
            bullet = new Bullet("bala", new Position(xPos, yPos+1), 1,
                    1, true, 1, 1, "z", 2, isFromMonster);
        }
        else{
            bullet = new Bullet("bala", new Position(xPos, yPos-1), 1,
                    1, true, 1, 1, "z", 2, isFromMonster);

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

    public void addBullet(Player player){
        this.bullets.add(player.doAttack());
    }
}

