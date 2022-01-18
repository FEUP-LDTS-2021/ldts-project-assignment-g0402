package Game;

import Actions.Attack;
import Objects.*;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Level {

    private int height;
    private int width;
    protected Player player;
    protected MonsterWave wave;
    protected Actions.Attack attack = new Attack();
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

        player.draw(screen);
        wave.draw(screen);
        attack.draw(screen);
    }

    public void movePlayer(boolean moveToRight){
        if(moveToRight)
            player.moveRight(this.width);
        else player.moveLeft();
    }

    public void doAttackPlayer(){
        this.player.doAttack(attack);
    }
    public void waveAttack(){
        this.wave.doAttack(attack);

    }

    public void updateBullets(){
        attack.move();
        player.checkCollision(attack);
        wave.checkCollision(attack);

    }

}