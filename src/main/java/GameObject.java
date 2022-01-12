import com.googlecode.lanterna.graphics.TextGraphics;

public class GameObject{
    protected Position position;
    protected int height;
    protected int width;
    protected String myName;
    protected boolean destructible;
    protected int life;
    protected int level;
    protected int[][] sprite;
    protected int speed;
    private boolean isAlive;
    public GameObject(String myName, Position position,
                      int height, int width, boolean destructible,
                      int life, int level, int[][] sprite, int speed) {
        this.myName = myName;
        this.position = position;
        this.height = height;
        this.width = width;
        this.destructible = destructible;
        this.life = life;
        this.level = level;
        this.sprite = sprite;
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
        for (int[] dot: sprite) {
            myGuy.drawLine(position.getxPos() + dot[0],
                    position.getyPos() + dot[1],
                    position.getxPos() + dot[2],
                    position.getyPos() + dot[3],
                    'u');
        }
    }

    public void moveAttack(){
        return;
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

    public int getLevel() {
        return level;
    }
}
