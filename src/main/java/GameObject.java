package com.googlecode.lanterna.tutorial;
import com.googlecode.lanterna.graphics.TextGraphics;

public class GameObject {
    protected int xPosic;
    protected int yPosic;
    protected int height;
    protected int width;
    protected String myName;
    protected boolean destructible;
    protected int life;
    protected int level;
    protected int[][] sprite;
    protected int speed;

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
        this.sprite = sprite;
        this.speed = speed;
    }



    public void draw(TextGraphics myGuy){
        for (int[] dot: sprite) {
            myGuy.drawLine(xPosic+ dot[0],
                    yPosic + dot[1],
                    xPosic + dot[2],
                    yPosic + dot[3],
                    'u');
        }
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
