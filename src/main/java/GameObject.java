import com.googlecode.lanterna.graphics.TextGraphics;
import java.util.*;

public class GameObject{
    protected int xPosic;
    protected int yPosic;
    protected int height;
    protected int width;
    protected String myName;
    protected boolean destructible;
    protected int life;
    protected int level;
    protected List<List<Integer>> sprite;
    protected int speed;
    private boolean isAlive;
    public GameObject(String myName, int xPosic, int yPosic,
                      int height, int width, boolean destructible,
                      int life, int level, int[][] sprite, int speed) {
        this.myName = myName;
        this.xPosic = xPosic;
        this.yPosic = yPosic;
        this.height = height;
        this.width = width;
        this.destructible = destructible;
        this.life = life;
        this.level = level;
        for (int[] line: sprite) {
            this.sprite.add(Arrays.asList(line));
        }
        this.speed = speed;
        this.isAlive = true;
    }

    public void dies(){
        this.isAlive = false;
    }
    public boolean isLive(){
        return this.isAlive;
    }

    public void draw(TextGraphics myGuy){
        for (List<int> dot: sprite) {
            myGuy.drawLine(xPosic+ dot.get(0),
                    yPosic + dot.get(1),
                    xPosic + dot.get(2),
                    yPosic + dot.get(3),
                    'u');
        }
    }

    public void moveAttack(){
        return;
    }
    public String getMyName() {
        return myName;
    }

    public int getX() {
        return this.xPosic;
    }

    public int getY() {
        return this.yPosic;
    }

    public void setX(int newPos) {
        this.xPosic += newPos;
    }

    public void setY(int newPos) {
        this.yPosic += newPos;
    }

    public void run(TextGraphics myGuy) {
        this.draw(myGuy);
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
    public List<List<Integer>> setSprite(String myStringSprite){
        List<String> listOfStringLines = Arrays.asList(myStringSprite.split(" .. "));
        List<String> toBeConvertToInt;
        List<int> ConvertToInt = new ArrayList<int>();
        List<List<Integer>> sprite = new ArrayList<ArrayList<int>>();
        for (String StringLine: listOfStringLines) {
            toBeConvertToInt = Arrays.asList(myStringSprite.split(","));
            for (String s: toBeConvertToInt) {
                ConvertToInt.add(Integer.valueOf(s));
            }
            sprite.add(ConvertToInt);

        }
        return sprite;
    }


    public int getLevel() {
        return level;
    }
}
