import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
<<<<<<< HEAD
<<<<<<< HEAD
import java.awt.*;
import java.net.URISyntaxException;

import static org.junit.Assert.assertEquals;
=======

import java.awt.*;
import java.net.URISyntaxException;

<<<<<<< HEAD
import static org.testng.AssertJUnit.assertEquals;
>>>>>>> c0ac190 (Created Test Class for Player AND Created Class Position)

=======
>>>>>>> 6bdab29 (Added Documentation to Position)
=======
import java.awt.*;
import java.net.URISyntaxException;
>>>>>>> d2139a700b0dd6a9b33d7154a4d32a8cba16cbaf

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

        Assertions.assertEquals(expectedPos.getxPos(), p1.getX());
        Assertions.assertEquals(expectedPos.getyPos(), p1.getY());
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
<<<<<<< HEAD
<<<<<<< HEAD
            p1.moveLeft();
=======
            p1.moveLeft(game.screen.getTerminalSize().getRows());
>>>>>>> c0ac190 (Created Test Class for Player AND Created Class Position)
=======
            p1.moveLeft(game.screen.getTerminalSize().getRows());
>>>>>>> d2139a700b0dd6a9b33d7154a4d32a8cba16cbaf
        }

        Assertions.assertEquals(expectedPos.getxPos(), p1.getX());
        Assertions.assertEquals(expectedPos.getyPos(), p1.getY());
    }
}
