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

    public Level(TextGraphics screen, Player player, MonsterWave monsterWave){
        this.screen = screen;
        this.height = screen.getSize().getRows();
        this.width = screen.getSize().getColumns();
        this.player = player;
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