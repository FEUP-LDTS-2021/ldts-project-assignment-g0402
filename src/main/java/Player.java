import com.googlecode.lanterna.graphics.TextGraphics;

public class Player extends GameObject {

    public Player(String myName, Position position,
                  int height, int width, boolean destructible,
                  int life, int level, int[][] sprite, int speed) {

        super(myName, position, height, width, destructible, life, level, sprite, speed);
    }

    public void moveLeft(){
        if(this.position.getxPos() > 1){
            this.position.setxPos(this.position.getxPos() - 1);
        }
    }

    public void moveRight(int width){
        if(this.position.getxPos() < (width - this.width - 1)){
            this.position.setxPos(this.position.getxPos() + 1);
        }
    }

    public void draw(TextGraphics game){
        for (int[] dot: sprite) {
            game.drawLine(position.getxPos() + dot[0],
                    position.getyPos() + dot[1],
                    position.getxPos() + dot[2],
                    position.getyPos() + dot[3],
                    'u');
        }
    }

    public Bullet doAttack(){
        return Attack.doAttack(this.position.getxPos(), this.position.getyPos(), false);
    }
}
