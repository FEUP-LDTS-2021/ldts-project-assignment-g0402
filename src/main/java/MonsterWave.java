import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MonsterWave{

    protected Monster[][] wave;
    protected int lineSize;       //number of aliens per line
    protected int waveLength;     //number of lines per wave
    protected int[][] sprite = new int[][]{{0,0,1,0}, {1,0,1,1}, {1,1,0,1}, {0,1,0,0}};
    protected int xPos;           //initial x position
    protected int yPos;           //initial y position
    protected int waveSpeed = 1;

    public MonsterWave(int xPos, int yPos, int lineSize, int waveLength){
        this.lineSize = lineSize;
        this.waveLength = waveLength;
        this.wave = new Monster[waveLength][lineSize];
        this.xPos = xPos;
        this.yPos = yPos;
    }



    public void populateWave() {
        int originalXPos = xPos;
        for(int i = 0; i < waveLength; ++i){
            xPos = originalXPos;
            for (int j = 0; j < lineSize; ++j) {
                wave[i][j] = new Monster("teste", new Position(xPos, yPos), 2,
                        2, true, 1, 1, sprite, waveSpeed);
                //System.out.println("xPos " + xPos + " yPos " + yPos);
                xPos += 4;
            }

            yPos += 4;
        }
    }

    public void moveAttack(){
        for(int i = 0; i < waveLength; ++i){
            for (int j = 0; j < lineSize; ++j) {
                wave[i][j].moveAttack();
            }
        }
    }

    public void draw(TextGraphics screen){
        for(int i = 0; i < waveLength; ++i){
            for (int j = 0; j < lineSize; ++j) {
                wave[i][j].draw(screen);
            }
        }
    }

    public int getPosLeft(){
        int x = wave[0][0].position.getxPos();
        for (int j = 0; j < lineSize; j++) {
            for(int i = 0; i < waveLength; ++i){
                if(wave[i][j].isLive() && x> wave[i][j].position.getxPos()){
                    x = wave[i][j].position.getxPos();
                };
            }
        }
        return x;
    }

    public int getPosRight(){
        int x = wave[0][0].position.getxPos();
        for (int j = 0; j < lineSize; j++) {
            for(int i = 0; i < waveLength; ++i){
                if(wave[i][j].isLive() && x < wave[i][j].position.getxPos()){
                    x = wave[i][j].position.getxPos();
                };
            }
        }
        return x;
    }


    public void moveLeft(){
        for(int i = 0; i < waveLength; ++i){
            for (int j = 0; j < lineSize; ++j) {
                wave[i][j].moveLeft();
            }
        }
    }
    public void moveRight(){
        for(int i = 0; i < waveLength; ++i){
            for (int j = 0; j < lineSize; ++j) {
                wave[i][j].moveRight();
            }
        }
    }
    public void moveDown(){
        for(int i = 0; i < waveLength; ++i){
            for (int j = 0; j < lineSize; ++j) {
                wave[i][j].moveDown();
            }
        }
    }

}

