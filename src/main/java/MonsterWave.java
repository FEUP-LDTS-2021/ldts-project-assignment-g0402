import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MonsterWave{
    protected Monster[][] wave;
    protected int lineSize;       //number of aliens per line
    protected int waveLength;     //number of lines per wave
    protected int xPos;           //initial x position
    protected int yPos;           //initial y position
    protected int waveSpeed = 1;
    protected int yOffset;
    protected int xOffset;


    public MonsterWave(int xPos, int yPos, int lineSize, int waveLength, int xOffset, int yOffset, Monster monster){
        this.lineSize = lineSize;
        this.waveLength = waveLength;
        this.wave = new Monster[waveLength][lineSize];
        this.xPos = xPos;
        this.yPos = yPos;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        populateWave(monster);
    }

    public void populateWave(Monster monster) {
        int xPosTmp;
        int yPosTmp = this.yPos;
        for(int i = 0; i < this.waveLength; ++i) {
            xPosTmp = this.xPos;
            for (int j = 0; j < this.lineSize; ++j) {
                this.wave[i][j] = new Monster(monster, xPosTmp, yPosTmp);
                xPosTmp += this.xOffset;
            }
            yPosTmp += this.yOffset;
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

