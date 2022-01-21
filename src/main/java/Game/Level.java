package Game;

import Actions.Attack;
import Objects.*;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Level {

    private int height;
    private int width;
    protected Player player;
    protected MonsterWave wave;
    protected Actions.Attack attack = new Attack();
    public static TextGraphics screen;

    public Level(TextGraphics screen, Player player, MonsterWave monsterWave){
        this.screen = screen;
        this.height = screen.getSize().getRows();
        this.width = screen.getSize().getColumns();
        this.player = player;
        this.wave = monsterWave;
    }

    public void draw() {
        this.screen.setBackgroundColor(Game.colorScenario);
        this.screen.fillRectangle(new TerminalPosition(0,0), new TerminalSize(width, height), ' ');
        drawInfo();
        attack.draw(screen);
        player.draw(screen);
        wave.draw(screen);
    }

    /** This method draws the info of the game (like how many lives the player
     * has at the moment) on the screen of game.
     * */
    private void drawInfo(){
        int livesInit = player.life.getLives(), lives = player.life.getCurrentLives(), pos = 7;

        this.screen.setForegroundColor(Game.colorPlayer);
        this.screen.putString(1, height-2, "LIVES:");

        for(int i =0; i < livesInit; i++){
            if(lives > 0) {
                this.screen.drawLine(pos, height - 2, pos, height - 2, '*');
                lives = lives - 1;
            }
            else this.screen.drawLine(pos, height - 2, pos, height - 2, '#');
            pos = pos + 2;
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

    public void updateBullets(int width){
        attack.move(width);
        player.checkCollision(attack);
        wave.checkCollision(attack);

    }

}