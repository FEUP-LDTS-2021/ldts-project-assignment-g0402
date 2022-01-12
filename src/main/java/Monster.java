import com.googlecode.lanterna.graphics.TextGraphics;

public class Monster extends GameObject {
    public Monster(String myName, Position position,
                  int height, int width, boolean destructible,
                  int life, int level, int[][] sprite, int speed) {
        super(myName, position, height, width, destructible, life, level, sprite, speed);
    }

    public void moveLeft(){
        this.position.setxPos(this.position.getxPos() - 1);
    }

    public void moveDown(){
        this.position.setyPos(this.position.getyPos() + 1);
    }

    public void moveRight(){
        this.position.setxPos(this.position.getxPos() + 1);
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
}
