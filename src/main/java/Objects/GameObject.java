package Objects;

import Objects.Attributes.Life;
import Objects.Attributes.Position;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.concurrent.TimeUnit;

public class GameObject{
    public Position position;
    public Life life;
    protected int height;
    protected int width;
    protected String myName;
    String sprite;
    protected int speed;
    protected final int refreshTime = 1000;
    private final int fireRate = 8;
    protected boolean isMonster;
    protected TextColor.RGB color;




    /**This constructor defines a new GameObject*/
    public GameObject(String myName, Position position,
                      int width, int height, int lives,
                      String sprite, int speed) {

        this.myName = myName;
        this.position = position;
        this.height = height;
        this.width = width;
        this.sprite = sprite;
        this.speed = speed;

        this.life = new Life(lives);
    }

    /**This method unlives (kills) the object*/
    public void kill(){
        this.life.kill();
        if(!this.life.isAlive() && isMonster){
            this.sprite = "@";
        }
    }


    /**This method draws the object only if its alive*/
    public void draw(TextGraphics screen) {
        if (this.life.isAlive()){
            screen.setForegroundColor(this.color);
            int offset;
            for (int i = 0; i < this.width; i++) {
                offset = i * this.height;
                for (int j = 0; j < this.height; j++) {
                    screen.putString(position.getxPos() + i, position.getyPos() + j,
                                    Character.toString(this.sprite.charAt(offset+j)));
                }
            }
        }
    }

    public void moveRight(int width){
        if((this.position.getxPos() + this.width) < width)
        this.position.setxPos(this.position.getxPos() + 1);
        try {
            TimeUnit.MILLISECONDS.sleep(refreshTime/speed);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void moveLeft(){
        if(this.position.getxPos() > 0)
        this.position.setxPos(this.position.getxPos() - 1);
        try {
            TimeUnit.MILLISECONDS.sleep(refreshTime/speed);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void moveDown(){
        this.position.setyPos(this.position.getyPos() + 1);
        try {
            TimeUnit.MILLISECONDS.sleep(refreshTime/speed);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void moveUp(){
        this.position.setyPos(this.position.getyPos() - 1);
        try {
            TimeUnit.MILLISECONDS.sleep(refreshTime/speed);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
    public boolean isMonster() {
        return isMonster;
    }

}
