import Game.Console;
import Objects.*;

import net.jqwik.api.*;
import net.jqwik.api.constraints.*;

import java.awt.*;
import java.net.URISyntaxException;

public class testPBPlayer {

    @Property
    public void testMovementRight(@ForAll @Positive @IntRange(max = 20) int a) throws URISyntaxException, FontFormatException {
        Console game = new Console();

        game.run();

        Player p1 = new Player("Player 1", new Position(game.screen.newTextGraphics()), 1, 3, true, 1, 1, "abc", 5);

        //Excepted result
        Position expectedPos = new Position(p1.position.getxPos() + a, p1.position.getyPos());

        //Moving the player 3 units to the right
        for(int i=0; i<a; i++){
            p1.moveRight(game.screen.getTerminalSize().getColumns());
        }

        assert(expectedPos.getxPos() == p1.position.getxPos());
        assert(expectedPos.getyPos() == p1.position.getyPos());
    }
}
