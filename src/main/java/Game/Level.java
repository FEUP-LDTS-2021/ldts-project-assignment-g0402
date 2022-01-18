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
        drawInfo();
        player.draw(screen);
        wave.draw(screen);
        attack.draw(screen);
    }

    private void drawInfo(){
        int livesInit = 3, lives = player.life.getLives(), pos = 7;

        this.screen.setForegroundColor(new TextColor.RGB(255,255,255));
        this.screen.putString(1, height-2, "LIVES:");

        for(int i =0; i < livesInit; i++){
            if(lives > 0) {
                this.screen.drawLine(pos, height - 2, pos, height - 2, '*');
                lives = lives - 1;
            }
            else this.screen.drawLine(pos, height - 2, pos, height - 2, '#');
            pos = pos +2;
        }

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