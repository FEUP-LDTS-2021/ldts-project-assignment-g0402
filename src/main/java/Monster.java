import com.googlecode.lanterna.graphics.TextGraphics;

public class Monster extends Object{
    private String myName;
    private int xPosic;
    private int yPosic;
    private int height;
    private int width;
    private boolean destructible;
    private int life;
    private int level;
    private int[][] sprite;
    private int speed;
    public Monster(String myName, int xPosic, int yPosic,
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
        this.sprite = sprite;
        this.speed = speed;
    }

    public void move(int xBias, int yBias){
        xPosic = xPosic + xBias*speed;
        yPosic = yPosic + yBias*speed;
    }

    public void Draw(TextGraphics myGuy){
        for (int[] dot: sprite) {
            myGuy.drawLine(xPosic+ dot[0]*10,
                    yPosic + dot[1]*10,
                    xPosic + dot[2]*10,
                    yPosic + dot[3]*10,
            'c');
        }
    }

    public String getMyName() {
        return myName;
    }

    public int getxPosic() {
        return xPosic;
    }

    public int getyPosic() {
        return yPosic;
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
