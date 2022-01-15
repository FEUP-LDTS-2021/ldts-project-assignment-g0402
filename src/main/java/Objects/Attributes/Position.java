package Objects.Attributes;

import Objects.Player;
import com.googlecode.lanterna.graphics.TextGraphics;


/**Class Objects.Position deals with the x coordinates and y coordinates
 * of any object. It can also set an Object Objects.Player to its
 * default position. */
public class Position {
    private int xPos;
    private int yPos;
    private int xPlayerDefault;
    private int yPlayerDefault;


    /**This constructor defines the Default Coordinates for an
     * Object Objects.Player based on the TerminalSize of the game, and
     * sets it to its default position*/
    public Position(TextGraphics game){
        int distanceFromConsoleFloor = 4;

        this.xPlayerDefault = (game.getSize().getColumns()/2);
        this.yPlayerDefault = game.getSize().getRows() - distanceFromConsoleFloor;

        this.xPos = this.xPlayerDefault;
        this.yPos = this.yPlayerDefault;
    }



    /**This constructor defines a Objects.Position with  X and Y Coordinates for an
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

    /**This method sets the coordinates of the Object Objects.Player
     * to its default coordinates according to the Object Objects.Player Size.*/
    public void setPlayerPosDefault(Player player){
        this.xPos = this.xPlayerDefault - player.getWidth();
        this.yPos = this.yPlayerDefault - player.getHeight();
    }

}
