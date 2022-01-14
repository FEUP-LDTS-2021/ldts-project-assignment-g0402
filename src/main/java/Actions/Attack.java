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
    private final TextColor.RGB MonsterColor = new TextColor.RGB(0, 200, 50);
    private final TextColor.RGB PlayerColor = new TextColor.RGB(255, 255, 255);

    public static Bullet doAttack(int xPos, int yPos, boolean isFromMonster){

        Bullet bullet = new Bullet("bala", new Position(xPos, yPos), 1,
                1, true, 1, 1, "z", 2, isFromMonster);
        return bullet;
    }

    public void move() {
        for (Bullet bull : this.bullets) {
            bull.move();
        }
    }

    public void draw(TextGraphics screen) {
        for (Bullet bull : bullets) {
            if (bull.isBulletFromMonster()) {
                screen.setForegroundColor(this.MonsterColor);
            } else {
                screen.setForegroundColor(this.PlayerColor);
            }

            bull.draw(screen);
        }
    }

    public void addBullet(Player player){
        this.bullets.add(player.doAttack());
    }
}

