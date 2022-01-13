package Objects;

import com.googlecode.lanterna.graphics.TextGraphics;
import java.util.*;

public class GameObject{
    public Position position;
    protected int height;
    protected int width;
    protected String myName;
    protected boolean destructible;
    protected int life;
    protected int level;
    String sprite;
    protected int speed;
    private boolean isAlive;

    /**This constructor defines a new Objects.GameObject*/
    public GameObject(String myName, Position position,
                      int height, int width, boolean destructible,
                      int life, int level, String sprite, int speed) {
        this.myName = myName;
        this.position = position;
        this.height = height;
        this.width = width;
        this.destructible = destructible;
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


    public String getMyName() {
        return myName;
    }

    public Position getPos() {
        return this.position;
    }

    public void setPos(Position newPos) {
        this.position = newPos;
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
