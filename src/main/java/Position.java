import com.googlecode.lanterna.graphics.TextGraphics;

<<<<<<< HEAD
<<<<<<< HEAD
/**Class Position deals with the x coordinates and y coordinates
 * of any object. It can also set an Object Player to its
 * default position. */
<<<<<<< HEAD

=======
>>>>>>> 82827a4 (Added Documentation to Position)
=======
>>>>>>> c0ac190 (Created Test Class for Player AND Created Class Position)
=======
/**Class Position deals with the x coordinates and y coordinates
 * of any object. It can also set an Object Player to its
 * default position. */
>>>>>>> 6bdab29 (Added Documentation to Position)
public class Position {
    private int xPos = 0;
    private int yPos = 0;
    private int xPlayerDefault;
    private int yPlayerDefault;

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD

=======
>>>>>>> 82827a4 (Added Documentation to Position)
    /**This constructor defines the Default Coordinates for an
     * Object Player based on the TerminalSize of the game.*/
=======
>>>>>>> c0ac190 (Created Test Class for Player AND Created Class Position)
=======
    /**This constructor defines the Default Coordinates for an
     * Object Player based on the TerminalSize of the game.*/
>>>>>>> 6bdab29 (Added Documentation to Position)
    public Position(TextGraphics game){
        int distanceFromConsoleFloor = 4;

        this.xPlayerDefault = (game.getSize().getColumns()/2);
<<<<<<< HEAD
<<<<<<< HEAD

        this.yPlayerDefault = game.getSize().getRows() - distanceFromConsoleFloor;
    }

    /**This constructor defines a Position with  X and Y Coordinates for an
     * unspecified Object based on the values given in the parameters.*/
=======
                //width/2-widthPlayer
=======

>>>>>>> 6bdab29 (Added Documentation to Position)
        this.yPlayerDefault = game.getSize().getRows() - distanceFromConsoleFloor;
    }

<<<<<<< HEAD
>>>>>>> c0ac190 (Created Test Class for Player AND Created Class Position)
=======
    /**This constructor defines a Position with  X and Y Coordinates for an
     * unspecified Object based on the values given in the parameters.*/
>>>>>>> 6bdab29 (Added Documentation to Position)
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

<<<<<<< HEAD
<<<<<<< HEAD
    /**This method sets the coordinates in the Object Player given
     * to its Default place taking in consideration the Object Player Size.*/
=======
>>>>>>> c0ac190 (Created Test Class for Player AND Created Class Position)
=======
    /**This method sets the coordinates in the Object Player given
     * to its Default place taking in consideration the Object Player Size.*/
>>>>>>> 6bdab29 (Added Documentation to Position)
    public void setPlayerPosDefault(Player player){
        player.setX(this.xPlayerDefault - player.getWidth());
        player.setY(this.yPlayerDefault - player.getHeight());
    }

}
