package Objects;

import Actions.Attack;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class GameObject{
    public Position position;
    protected int height;
    protected int width;
    protected String myName;
    protected boolean destructible  = true;
    protected int life;
    protected int level;
    String sprite;
    protected int speed = 8;
    protected final int refreshTime = 1000;
    private boolean isAlive;
    private int fireRate = 8;
    protected boolean isMonster;
    protected TextColor.RGB color;
    /**This constructor defines a new Objects.GameObject*/
    public GameObject(String myName, Position position,
                      int height, int width, int life, int level,
                      String sprite, int speed) {

        this.myName = myName;
        this.position = position;
        this.height = height;
        this.width = width;
        this.life = life;
        this.level = level;
        this.sprite = sprite;
        this.speed = speed;
        this.isAlive = true; //When a new object is created, is always alive.
    }

    /**This method unlives (kills) the object*/
    public void dies(){
        this.isAlive = false;
    }

    /**This method return the live state*/
    public boolean isLive(){
        return this.isAlive;
    }

    /**This method draws the object only if it's alive*/
    public void draw(TextGraphics myGuy) {
        if (this.isAlive){
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

    public int getLife() {
        return life;
    }

    public int getLevel() {
        return level;
    }
}
