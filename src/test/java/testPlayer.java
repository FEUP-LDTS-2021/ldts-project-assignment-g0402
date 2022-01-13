import Game.Console;
import Objects.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.awt.*;
import java.net.URISyntaxException;

public class testPlayer {

    @Test
    public void testPlayerMovementRight() throws URISyntaxException, FontFormatException {
        Console game = new Console();

        game.run();

        Player p1 = new Player("Player 1", new Position(game.screen.newTextGraphics()), 1, 3, true, 1, 1, "abc", 5);

        //Excepted result
        Position expectedPos = new Position(p1.position.getxPos() + 3, p1.position.getyPos());

        //Moving the player 3 units to the right
        for(int i=0; i<3; i++){
            p1.moveRight(game.screen.getTerminalSize().getColumns());
        }

        Assertions.assertEquals(expectedPos.getxPos(), p1.position.getxPos());
        Assertions.assertEquals(expectedPos.getyPos(), p1.position.getyPos());
    }

    @Test
    public void testPlayerMovementLeft() throws URISyntaxException, FontFormatException {
        Console game = new Console();

        game.run();

        Player p1 = new Player("Player 1", new Position(game.screen.newTextGraphics()), 1, 3, true, 1, 1, "abc", 5);

        //Excepted result
        Position expectedPos = new Position(p1.position.getxPos() - 3, p1.position.getyPos());

        //Moving the player 3 units to the left
        for(int i=0; i<3; i++){
            p1.moveLeft();
        }

        Assertions.assertEquals(expectedPos.getxPos(), p1.position.getxPos());
        Assertions.assertEquals(expectedPos.getyPos(), p1.position.getyPos());
    }

}
