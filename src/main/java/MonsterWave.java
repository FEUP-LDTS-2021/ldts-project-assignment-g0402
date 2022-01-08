import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MonsterWave{

    protected WaveLine waveLine;
    protected WaveLine[] wave;
    protected int lineSize;       //number of aliens per line
    protected int waveLength;     //number of lines per wave
    protected int[][] sprite = new int[][]{{0, 1, 1, 0}, {1, 0, 0, 1}, {1, 0, 0, 1}, {0, 1, 1, 0}};
    protected int xPos;           //initial x position
    protected int yPos;           //initial y position
    protected int waveSpeed = 1;

    public MonsterWave(int xPos, int yPos, int lineSize, int waveLength){
        this.lineSize = lineSize;
        this.waveLength = waveLength;
        this.wave = new WaveLine[waveLength];
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public void populateWave() {
        for(int i = 0; i < waveLength; ++i){
            waveLine = new WaveLine();
            waveLine.populateWaveLine(xPos, yPos);
            wave[i] = waveLine;
            //wave.add(waveLine.populateWaveLine(xPos, yPos));
            xPos += 2;
        }
    }

    public void destroy(){
        /*wave[0].destroyMonster(7);
        wave[7].destroyMonster(7);*/
    }

    public void draw(TextGraphics screen){
        for(int i = 0; i < waveLength; ++i){
            wave[i].drawLine(screen, wave[i].waveLine);
        }
    }

    public int getPosRight(){
        int y = yPos;
        for(int i = 0; i < waveLength; ++i){
            y = wave[i].getLast().getY();
        }
        //System.out.println("last right" + y + "," + x);
        return y;
    }

    public int getPosLeft(){
        int y = yPos;
        for(int i = 0; i < waveLength; ++i){
            y = wave[i].getFirst().getY();
        }
        //System.out.println("first left" + y + "," + x);
        return y;
    }

    public int getPosUp(){
        int x = xPos;
        for(int i = 0; i < waveLength; ++i){
            if(wave[i].getFirst().getX() < x) {
                x = wave[i].getFirst().getX();
            }
        }
        //System.out.println("first up" + y + "," + x);
        return x;
    }

    public int getPosDown(){
        int x = xPos;
        for(int i = 0; i < waveLength; ++i){
            x = wave[i].getLast().getX();
        }
        //System.out.println("last down" + y + "," + x);
        return x;
    }

    public void setPosY(int offset, boolean isMovingToRight){
        for(int i = 0; i < waveLength; ++i){
            if(isMovingToRight)
                wave[i].moveY(offset);
            else
                wave[i].moveY(-offset);
        }
    }

    public void setPosDown(int offset){
        for(int i = 0; i < waveLength; ++i){
            wave[i].moveDown(offset);
        }
    }

    class WaveLine{
        protected List<Monster> waveLine;     //list of monsters in line
        protected int numAlive;               //num of monsters alive in line
        protected boolean[] isAlive;          //where the monsters still alive

        public WaveLine(){
            waveLine = new ArrayList<>();
            this.numAlive = lineSize;
            isAlive = new boolean[lineSize];

            for(int i = 0; i < lineSize; ++i)
                isAlive[i] = true;
        }

        private void populateWaveLine(int xPos, int yPos){
            for (int i = 0; i < lineSize; ++i) {
                waveLine.add(new Monster("teste", xPos, yPos, 1,
                        2, true, 1, 1, sprite, waveSpeed));
                //System.out.println("xPos " + xPos + " yPos " + yPos);
                yPos += 3;
            }
        }

        private void destroyMonster(int monsterNum){
            waveLine.remove(monsterNum-1);
            isAlive[monsterNum-1] = false;
            --lineSize;
        }

        private void drawLine(TextGraphics screen, List<Monster> list){
            for(Monster monster:list){
                screen.drawLine(new TerminalPosition(monster.getY(), monster.getX()),
                        new TerminalPosition(monster.getY()+1, monster.getX()),
                        'T');
            }
        }

        private void moveY(int offset){
            for(int i = waveLine.size()-1; i >= 0; --i){
                waveLine.get(i).setY(offset);
                /*try {
                    TimeUnit.MILLISECONDS.sleep(700);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
            }
        }

        private void moveDown(int offset){
            for(int i = waveLine.size()-1; i >= 0; --i){
                waveLine.get(i).setX(offset);
            }
        }

        private Monster getLast(){
            int pos = 0;
            for(int i = 0; i < lineSize; ++i){
                if(isAlive[i])
                    pos = i;
            }
            return waveLine.get(pos);
        }

        private Monster getFirst(){
            int pos = 0;
            for(int i = 0; i < lineSize; ++i){
                if(isAlive[i]) {
                    pos = i;
                    break;
                }
            }
            return waveLine.get(pos);
        }
    }
}

