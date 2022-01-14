package Game;

import Actions.Attack;
import Objects.*;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class Level {

    private int height;
    private int width;
    private Player player;
    protected MonsterWave wave;
    private Actions.Attack attack = new Attack();

    private TextGraphics screen;

    protected boolean canAttack = true;
    protected long lastShot;        //store time of last shot
    protected int fireDelay = 1000; //in millisec

    public Level(TextGraphics screen, Player player, MonsterWave monsterWave){
        this.screen = screen;
        this.height = screen.getSize().getRows();
        this.width = screen.getSize().getColumns();
        int heightPlayer = 3;
        int widthPlayer = 1;

        //this.player = new Player("Player1", new Position(screen), heightPlayer, widthPlayer, true, 1, 1, "def", 5);
        this.player = player;

        //Monster monster = new Monster("Gabriel Coelho", true, 1, "pq", 5);

        //this.wave = new MonsterWave(3,3 ,10, 5, 5, 3, monster);
        this.wave = monsterWave;

    }

    public void draw() {
        this.screen.setBackgroundColor(new TextColor.RGB(15,20,45));
        this.screen.fillRectangle(new TerminalPosition(0,0), new TerminalSize(width, height), ' ');

        this.screen.setForegroundColor(new TextColor.RGB(255,255,255));
        player.draw(screen);

        this.screen.setForegroundColor(new TextColor.RGB(0,200,50));
        wave.draw(screen);

        if(this.attack != null){
            attack.draw(screen);
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

    public void doAttackPlayer(){

        attack.addBullet(player);
        attack.draw(screen);
    }

    public void updateBullets(){
        if(this.attack != null){
            attack.move();
        }
    }



}