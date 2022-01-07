import com.googlecode.lanterna.graphics.TextGraphics;

public class Position {
    private int xPos = 0;
    private int yPos = 0;
    private int xPlayerDefault;
    private int yPlayerDefault;

    public Position(TextGraphics game){
        int distanceFromConsoleFloor = 4;

        this.xPlayerDefault = (game.getSize().getColumns()/2);
                //width/2-widthPlayer
        this.yPlayerDefault = game.getSize().getRows() - distanceFromConsoleFloor;
                //height-widthPlayer-distanceFromConsoleFloor
    }

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

    public void setPlayerPosDefault(Player player){
        player.setX(this.xPlayerDefault - player.getWidth());
        player.setY(this.yPlayerDefault - player.getHeight());
    }

}
