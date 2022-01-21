package Objects;

import Objects.Attributes.Life;
import Objects.Attributes.Position;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import Actions.Attack;
import java.util.concurrent.TimeUnit;
import Game.Game;
import Game.Level;

public class GameObject{
    public Position position;
    public Life life;
    protected int height;
    protected int width;
    protected String myName;
    String sprite;
    protected int speed;
    private int actualFireRate = 10000;
    private TextColor color;
    private boolean isMonster;


    /**This constructor defines a new GameObject*/
    public GameObject(String myName, Position position,
                      int width, int height, int lives,
                      String sprite, int speed, boolean isMonster) {

        this.myName = myName;
        this.position = position;
        this.height = height;
        this.width = width;
        this.sprite = sprite;
        this.speed = speed;
        this.life = new Life(lives);
        this.isMonster = isMonster;
        if (this.isMonster){
            this.color =  Game.colorMonster;
        }
        else{
            this.color = Game.colorPlayer;
        }
    }

    /**This method unlives (kills) the object*/
    public void kill(){
        this.life.kill();

    }

    public void checkCollision(Attack attack){
        attack.checkCollision(this);
    }


    /**This method draws the object only if its alive*/
    public void draw(TextGraphics screen) {
        if (this.life.isAlive()){
            int offset;
            for (int i = 0; i < this.width; i++) {
                offset = i * this.height;
                for (int j = 0; j < this.height; j++) {
                    screen.setForegroundColor(this.color);
                    screen.putString(position.getxPos() + i, position.getyPos() + j,
                                    Character.toString(this.sprite.charAt(offset+j)));
                }
            }
        }
        if(this.life.isDeadRecently()){
            screen.setForegroundColor(this.color);
            screen.putString(position.getxPos(), position.getyPos(), "@");
        }

    }

    public void moveRight(int width){
        if((this.position.getxPos() + this.width) < width && life.isAlive())
        this.position.setxPos(this.position.getxPos() + 1);
        try {
            TimeUnit.MILLISECONDS.sleep(Game.refreshTime/speed);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void moveLeft(){
        if(this.position.getxPos() > 0 && this.life.isAlive())
        this.position.setxPos(this.position.getxPos() - 1);
        try {
            TimeUnit.MILLISECONDS.sleep(Game.refreshTime/speed);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void moveDown(){
        if(this.life.isAlive())
        this.position.setyPos(this.position.getyPos() + 1);
        try {
            TimeUnit.MILLISECONDS.sleep(Game.refreshTime/speed);
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

    public void doAttack(Attack attack){
        int fireRate = 10000;
        if(fireRate == actualFireRate) {
            attack.doAttack(this.position.getxPos(), this.position.getyPos(), this.isMonster());
        }
        else if(actualFireRate == 0){
            actualFireRate = fireRate;
        }
        else{
            actualFireRate--;
        }

    }

}
