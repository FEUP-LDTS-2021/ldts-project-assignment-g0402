import Game.Console;
import Objects.*;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import org.junit.jupiter.api.Test;
import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class testPlayer {

    protected final int width = 60, height = 60;
    TerminalSize terminalSize = new TerminalSize(width, height);
    DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
    Terminal terminal = terminalFactory.createTerminal();
    Screen screen = new TerminalScreen(terminal);

    public testPlayer() throws IOException {
    }

    @Test
    public void testPlayerMovementRight() throws URISyntaxException, FontFormatException {

        Player p1 = new Player("Player 1", new Position(screen.newTextGraphics()), 1, 3, true, 1, 1, "abc", 5);

        //Excepted result
        Position expectedPos = new Position(p1.position.getxPos() + 3, p1.position.getyPos());

        //Moving the player 3 units to the right
        for(int i=0; i<3; i++){
            p1.moveRight(screen.getTerminalSize().getColumns());
        }

        assert(expectedPos.getxPos() == p1.position.getxPos());
        assert(expectedPos.getyPos() == p1.position.getyPos());
    }

    @Test
    public void testPlayerMovementLeft() throws URISyntaxException, FontFormatException {

        Player p1 = new Player("Player 1", new Position(screen.newTextGraphics()), 1, 3, true, 1, 1, "abc", 5);

        //Excepted result
        Position expectedPos = new Position(p1.position.getxPos() - 3, p1.position.getyPos());

        //Moving the player 3 units to the left
        for(int i=0; i<3; i++){
            p1.moveLeft();
        }

        assert(expectedPos.getxPos() == p1.position.getxPos());
        assert(expectedPos.getyPos() == p1.position.getyPos());
    }

}
