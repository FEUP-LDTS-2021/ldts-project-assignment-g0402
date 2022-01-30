package ldts.objects;

import com.googlecode.lanterna.graphics.TextGraphics;
import ldts.actions.Attack;
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
    private final int refreshTime = 1000;
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

    /** This method populates the Wave of Monsters, creating multiple Monsters
     *  based of the given Monster.  */
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

    /** This method controls the movements of the Wave, deciding when to
     *  move to the left/right or down, according to the screen width provided. */
    public boolean moveWave(int width){
        int xMin = getPosLeft();
        int xMax = getPosRight();
        int down = getPosDown();
        if(xMin < 2 && !isMovingToRight){
            this.isMovingToRight = true;
            if(down < width-35)
                moveDown();
            else{
                return true; //not moving anymore
            }
        }
        else if(xMax > width-4 && this.isMovingToRight) {
            this.isMovingToRight = false;
            if(down < width-35)
                moveDown();
            else{
                return true; //not moving anymore
            }
        }
        else if(isMovingToRight){
            moveRight();
        }

        else{
            moveLeft();
        }
        return false;   //wave moved
    }

    /** This method draws each Monster on the Wave.  */
    public void draw(TextGraphics screen){
        for(int i = 0; i < waveLength; ++i){
            for (int j = 0; j < lineSize; ++j) {
                wave[i][j].draw(screen);
            }
        }
    }

    /** This method gets the x coordinate of the far most Monster on the left.  */
    public int getPosLeft(){
        int x = wave[0][lineSize-1].position.getxPos();
        for (int j = 0; j < lineSize; j++) {
            for(int i = 0; i < waveLength; ++i){
                if(wave[i][j].life.isAlive() && x> wave[i][j].position.getxPos()){
                    x = wave[i][j].position.getxPos();
                }
            }
        }
        return x;
    }

    /** This method gets the x coordinate of the far most Monster on the right.  */
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

    /** This method gets the y coordinate of the far most Monster on the bottom.  */
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

    /** This method moves each Monster on the Wave to the left, as a whole.  */
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

    /** This method moves each Monster on the Wave to the right, as a whole.  */
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

    /** This method moves each Monster on the Wave down, as a whole.  */
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

    /** This method checks if any of the bullets shot by the Player hit a Monster of the Wave.  */
    public void checkCollision(Attack attack){
        for(int i = 0; i < waveLength; ++i){
            for (int j = 0; j < lineSize; ++j) {
                wave[i][j].checkCollision(attack);
            }
        }
    }

    /** This method does the Attack of the Monsters, randomizing which Monster is going to attack.  */
    public void doAttack(Attack attack){
        for(int i = 0; i < waveLength; ++i){
            for (int j = 0; j < lineSize; ++j) {
                if(random.nextInt(1000)<5){
                    if(wave[i][j].life.isAlive()) wave[i][j].doAttack(attack);
                }
            }
        }
    }

    /** This method return false only when all the Monsters on the Wave are dead.  */
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

