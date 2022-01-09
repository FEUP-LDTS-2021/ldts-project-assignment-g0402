import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;


public class Level {

    private int height;
    private int width;
    private Player player;
    protected MonsterWave wave;
    protected int maxDownMovements = 6;
    private boolean isMovingToRight = true;

    public Level(int width, int height){
        this.height = height;
        this.width = width;
        int[][] sprite = new int[][]{{0,0,1,0}, {1,0,1,1}, {1,1,0,1}, {0,1,0,0}};
        int heightPlayer = 2;
        int widthPlayer = 2;
        int distanceFromConsoleFloor = 4;
        this.player = new Player("Gabriel Coelho", width/2-widthPlayer,
                height-widthPlayer-distanceFromConsoleFloor, heightPlayer,
                widthPlayer, true, 1, 1, sprite, 5);

        this.wave = new MonsterWave(3,3 ,12, 8);
        this.wave.populateWave();
    }

    public void draw(TextGraphics screen) {
        screen.setBackgroundColor(new TextColor.RGB(0,0,0));
        screen.fillRectangle(new TerminalPosition(0,0), new TerminalSize(width, height), ' ');

        player.draw(screen);
        wave.draw(screen);
    }

    public void movePlayer(boolean moveToRight){
        if(moveToRight){
            this.player.moveRight(this.width);
        }
        else{
            this.player.moveLeft(this.width);
        }
    }

    public boolean moveWave(){
        int yMin = this.wave.getPosLeft();
        int yMax = this.wave.getPosRight();
        System.out.println(yMax);
        System.out.println(yMin);
        System.out.println(this.isMovingToRight);
        if(yMin < 2 && !this.isMovingToRight){
            this.isMovingToRight = true;
            wave.moveDown();
            --maxDownMovements;
            System.out.println("oi");

        }
        else if(yMax > this.width-4 && this.isMovingToRight) {
            this.isMovingToRight = false;
            this.wave.moveDown();
            --maxDownMovements;
            System.out.println("oi");
        }
        else if(isMovingToRight){
            wave.moveRight();
        }
        else{
            wave.moveLeft();
        }
        if(maxDownMovements<0){
            return false;
        }
        else{
            return true;
        }
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

}