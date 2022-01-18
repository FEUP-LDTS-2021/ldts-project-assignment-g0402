package Objects;

import com.googlecode.lanterna.graphics.TextGraphics;
import Actions.Attack;
import java.util.concurrent.TimeUnit;
import java.util.Random;
public class MonsterWave{
    protected Monster[][] wave;
    protected int lineSize;       //number of aliens per line
    protected int waveLength;     //number of lines per wave
    protected int xPos;           //initial x position
    protected int yPos;           //initial y position
    protected int speed;
    protected int yOffset;
    protected int xOffset;
    private int refreshTime = 1000;
    private boolean isMovingToRight = true;
    Random random = new Random();


    public MonsterWave(int xPos, int yPos, int lineSize, int waveLength, int xOffset, int yOffset, Monster monster){
        this.lineSize = lineSize;
        this.waveLength = waveLength;
        this.wave = new Monster[waveLength][lineSize];
        this.xPos = xPos;
        this.yPos = yPos;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        populateWave(monster);
        this.speed = monster.speed;
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


    public boolean moveWave(int width){
        int yMin = getPosLeft();
        int yMax = getPosRight();
        int down = getPosDown();

        if(yMin < 2 && !isMovingToRight){
            isMovingToRight = true;
            moveDown();
        }
        else if(yMax > width-4 && this.isMovingToRight) {
            this.isMovingToRight = false;
            if(down < width-35)
                moveDown();
            else{
                return true;
            }
        }

        else if(isMovingToRight){
            moveRight();
        }

        else {
            moveLeft();
        }
        return false;
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
                if(wave[i][j].life.isAlive() && x> wave[i][j].position.getxPos()){
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
                if(wave[i][j].life.isAlive() && x < wave[i][j].position.getxPos()){
                    x = wave[i][j].position.getxPos();
                }
            }
        }
        return x;
    }

    public int getPosDown(){
        int y = yPos;
        for (int j = 0; j < lineSize; j++) {
            for(int i = 0; i < waveLength; ++i){
                if(wave[i][j].life.isAlive() && y < wave[i][j].position.getxPos()){
                    y = wave[i][j].position.getyPos();
                }
            }
        }
        return y;
    }


    public void moveLeft(){
        for(int i = 0; i < waveLength; ++i){
            for (int j = 0; j < lineSize; ++j) {
                wave[i][j].moveLeft();
            }
        }
        try {
            TimeUnit.MILLISECONDS.sleep(refreshTime/speed);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void moveRight(){
        for(int i = 0; i < waveLength; ++i){
            for (int j = 0; j < lineSize; ++j) {
                wave[i][j].moveRight(1000);
            }
        }
        try {
            TimeUnit.MILLISECONDS.sleep(refreshTime/speed);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void moveDown(){
        for(int i = 0; i < waveLength; ++i){
            for (int j = 0; j < lineSize; ++j) {
                wave[i][j].moveDown();
            }
        }
        try {
            TimeUnit.MILLISECONDS.sleep(refreshTime/speed);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Monster getMonsterX (int positionX){
        Monster monster = null;

        for(int i=waveLength-1; i>=0; i--){
            for (int j=0; j<lineSize; j++){
                if(wave[i][j].position.getxPos() == positionX){
                    monster = wave[i][j];
                }
            }
        }
        return monster;
    }

    public void checkCollision(Attack attack){
        for(int i = 0; i < waveLength; ++i){
            for (int j = 0; j < lineSize; ++j) {
                wave[i][j].checkCollision(attack);
            }
        }
    }

    public void doAttack(Attack attack){
        for(int i = 0; i < waveLength; ++i){
            for (int j = 0; j < lineSize; ++j) {
                if(random.nextInt(1000)<5){
                    if(wave[i][j].life.isAlive()) wave[i][j].doAttack(attack);
                }
            }
        }
    }

    public boolean isWaveAlive(){
        for(int i = 0; i < waveLength; ++i){
            for (int j = 0; j < lineSize; ++j) {
                if (wave[i][j].life.isAlive())
                    return true;
            }
        }
        return false;
    }
}

