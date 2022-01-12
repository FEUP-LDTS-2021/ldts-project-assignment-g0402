import com.googlecode.lanterna.graphics.TextGraphics;
import java.util.*;

public class GameObject{
    protected Position position;
    protected int height;
    protected int width;
    protected String myName;
    protected boolean destructible;
    protected int life;
    protected int level;
    String sprite;
    protected int speed;
    private boolean isAlive;

    /**This method is the constructor for the class Console.*/
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
    /**This method checks if object is still alive.*/
    public void dies(){
        this.isAlive = false;
    }
    /**This method return live state*/
    public boolean isLive(){
        return this.isAlive;
    }

    /**This method draw object only if they are alive*/
    public void draw(TextGraphics myGuy) {
        if (this.isAlive){
            int offset;
            for (int i = 0; i < this.height; i++) { /**That for loop is used for create,*/
                offset = i * this.width;      /**using a plane string, objects with more than one line*/
                for (int j = 0; j < this.width; j++) {
                    myGuy.putString(position.getxPos() + i, position.getyPos() + j,
                                    Character.toString(this.sprite.charAt(offset+j)));
                }
            }
        }
    }

    //Below we have gets. Is auto explained by the code and name.
    //I refuse to a specific documentation for each getter. It gets stuff.

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
