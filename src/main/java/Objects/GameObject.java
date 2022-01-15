package Objects;

import Actions.Attack;
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
    protected boolean destructible  = true;
    String sprite;
    protected int speed;
    protected final int refreshTime = 1000;
    private final int fireRate = 8;
    protected boolean isMonster;
    protected TextColor.RGB color;


    /**This constructor defines a new GameObject*/
    public GameObject(String myName, Position position,
                      int height, int width, int lives,
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
    public void draw(TextGraphics myGuy) {
        if (this.life.isAlive()){
            myGuy.setForegroundColor(this.color);
            int offset;
            for (int i = 0; i < this.height; i++) {
                offset = i * this.width;
                for (int j = 0; j < this.width; j++) {
                    myGuy.putString(position.getxPos() + i, position.getyPos() + j,
                                    Character.toString(this.sprite.charAt(offset+j)));
                }
            }
        }
    }

    public Bullet doAttack() {
        try {
            TimeUnit.MILLISECONDS.sleep(refreshTime/speed);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Attack.doAttack(this.position.getxPos(), this.position.getyPos(), isMonster);
    }

    public void moveRight(int width){
        this.position.setxPos(this.position.getxPos() + 1);
        try {
            TimeUnit.MILLISECONDS.sleep(refreshTime/speed);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void moveLeft(){
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

    public boolean isDestructible() {
        return destructible;
    }

}
