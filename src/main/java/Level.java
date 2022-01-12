import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

import java.util.ArrayList;
import java.util.List;


public class Level {

    private int height;
    private int width;
    private Player player;
    protected MonsterWave wave;
    protected int maxDownMovements = 6;
    private boolean isMovingToRight = true;
    private List<Bullet> bullets;
    private TextGraphics screen;
    public Level(TextGraphics screen){
        this.screen = screen;
        this.height = screen.getSize().getRows();
        this.width = screen.getSize().getColumns();
        int heightPlayer = 2;
        int widthPlayer = 2;
        int distanceFromConsoleFloor = 4;
        this.player = new Player("Player1", new Position(screen), heightPlayer,
                widthPlayer, true, 1, 1, "abcd", 5);

        Monster monster = new Monster("Gabriel Coelho", true, 1, "abcd", 5);
        this.wave = new MonsterWave(3,3 ,12, 8,
                3, 4, monster);
        this.bullets = new ArrayList<Bullet>();
    }

    public void draw() {
        this.screen.setBackgroundColor(new TextColor.RGB(15,20,45));
        this.screen.fillRectangle(new TerminalPosition(0,0), new TerminalSize(width, height), ' ');

        player.draw(screen);
        wave.draw(screen);
        for (Bullet bull: bullets) {
            bull.draw(screen);
        }
    }

    public void movePlayer(boolean moveToRight){
        if(moveToRight){
            this.player.moveRight(this.width);
        }
        else{
            this.player.moveLeft();
        }
    }
    public void moveBullets(){
        for (Bullet bull: this.bullets) {
            bull.move();
        }
    }
    public void doAttackPlayer(){
        this.bullets.add(this.player.doAttack());
    }

    public boolean moveWave(){
        int yMin = this.wave.getPosLeft();
        int yMax = this.wave.getPosRight();

        if(yMin < 2 && !this.isMovingToRight){
            this.isMovingToRight = true;
            wave.moveDown();
            --maxDownMovements;

        }
        else if(yMax > this.width-4 && this.isMovingToRight) {
            this.isMovingToRight = false;
            this.wave.moveDown();
            --maxDownMovements;
        }

        else if(isMovingToRight){
            wave.moveRight();
        }

        else{
            wave.moveLeft();
        }


        if(maxDownMovements<0){
            return false;
        }
        else{
            return true;

        }
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

}