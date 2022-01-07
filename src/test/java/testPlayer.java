<<<<<<< HEAD

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class testPlayer {
    @Test
    public void testPlayerMovement() {
        int[][] sprite = new int[][]{{0, 0, 1, 0}, {1, 0, 1, 1}, {1, 1, 0, 1}, {0, 1, 0, 0}};
        int heightPlayer = 2;
        int widthPlayer = 2;
        int distanceFromConsoleFloor = 4;
        Player player = new Player("Gabriel Coelho", 10,
                10, heightPlayer,
                widthPlayer, true, 1, 1, sprite, 5);
        player.moveLeft(100);
        assertEquals(9, player.getX());
=======
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.net.URISyntaxException;

import static org.testng.AssertJUnit.assertEquals;


public class testPlayer {

    @Test
    public void testPlayerMovementRight() throws URISyntaxException, FontFormatException {
        Console game = new Console();
        Position p1Pos = new Position(game.screen.newTextGraphics());
        int[][] sprite = new int[][]{{0,0,1,0}, {1,0,1,1}, {1,1,0,1}, {0,1,0,0}};

        game.run();

        Player p1 = new Player("Player 1", p1Pos.getxPos(), p1Pos.getyPos(), 2, 2, true, 1, 1, sprite, 5);
        p1Pos.setPlayerPosDefault(p1);

        //Excepted result
        Position expectedPos = new Position(p1.getX()+3, p1.getY());

        //Moving the player 3 units to the right
        for(int i=0; i<3; i++){
            p1.moveRight(game.screen.getTerminalSize().getRows());
        }

        assertEquals(expectedPos.getxPos(), p1.getX());
        assertEquals(expectedPos.getyPos(), p1.getY());
    }

    @Test
    public void testPlayerMovementLeft() throws URISyntaxException, FontFormatException {
        Console game = new Console();
        Position p1Pos = new Position(game.screen.newTextGraphics());
        int[][] sprite = new int[][]{{0,0,1,0}, {1,0,1,1}, {1,1,0,1}, {0,1,0,0}};

        game.run();

        Player p1 = new Player("Player 1", p1Pos.getxPos(), p1Pos.getyPos(), 2, 2, true, 1, 1, sprite, 5);
        p1Pos.setPlayerPosDefault(p1);

        //Excepted result
        Position expectedPos = new Position(p1.getX()-3, p1.getY());

        //Moving the player 3 units to the left
        for(int i=0; i<3; i++){
            p1.moveLeft(game.screen.getTerminalSize().getRows());
        }

        assertEquals(expectedPos.getxPos(), p1.getX());
        assertEquals(expectedPos.getyPos(), p1.getY());
>>>>>>> c0ac190 (Created Test Class for Player AND Created Class Position)
    }
}
