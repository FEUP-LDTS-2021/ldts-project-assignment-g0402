import com.googlecode.lanterna.graphics.TextGraphics;

public class Bullet extends GameObject {
    private boolean isMonsterBullet;
    public Bullet(String myName, Position position,
                   int height, int width, boolean destructible,
                   int life, int level, int[][] sprite, int speed, boolean isMonsterBullet) {
        super(myName, position, height, width, destructible, life, level, sprite, speed);
        this.isMonsterBullet = isMonsterBullet;
    }

    public void move(){
        if(isMonsterBullet){
            this.position.setyPos(this.position.getyPos() + 1);
        }
        else {
            this.position.setyPos(this.position.getyPos() - 1);
        }
    }

    public void draw(TextGraphics game){
        for (int[] dot: sprite) {
            game.drawLine(position.getxPos() + dot[0],
                    position.getyPos() + dot[1],
                    position.getxPos() + dot[2],
                    position.getyPos() + dot[3],
                    'z');
        }
    }
}
