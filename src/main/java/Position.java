import com.googlecode.lanterna.graphics.TextGraphics;

/**Class Position deals with the x coordinates and y coordinates
 * of any object. It can also set an Object Player to its
 * default position. */
public class Position {
    private int xPos = 0;
    private int yPos = 0;
    private int xPlayerDefault;
    private int yPlayerDefault;

    /**This constructor defines the Default Coordinates for an
     * Object Player based on the TerminalSize of the game.*/
    public Position(TextGraphics game){
        int distanceFromConsoleFloor = 4;

        this.xPlayerDefault = (game.getSize().getColumns()/2);

        this.yPlayerDefault = game.getSize().getRows() - distanceFromConsoleFloor;
    }

    /**This constructor defines a Position with  X and Y Coordinates for an
     * unspecified Object based on the values given in the parameters.*/
    public Position(int xPos, int yPos){
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    /**This method sets the coordinates in the Object Player given
     * to its Default place taking in consideration the Object Player Size.*/
    public void setPlayerPosDefault(Player player){
        player.setX(this.xPlayerDefault - player.getWidth());
        player.setY(this.yPlayerDefault - player.getHeight());
    }

}
