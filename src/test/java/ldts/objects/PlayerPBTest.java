package ldts.objects;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import ldts.objects.attributes.Position;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.IntRange;
import net.jqwik.api.constraints.Positive;

import java.io.IOException;

public class PlayerPBTest {

    protected final int width = 60, height = 60, offset = 5;
    TerminalSize terminalSize = new TerminalSize(width, height);
    DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
    Terminal terminal = terminalFactory.createTerminal();
    Screen screen = new TerminalScreen(terminal);

    public PlayerPBTest() throws IOException {
    }

    @Property
    public void testMovementRight(@ForAll @Positive @IntRange(max = width/2 - offset) int a) {

        Player p1 = new Player("Player 1", new Position(screen.newTextGraphics()), 1, 3, 1, "abc", 5);

        //Excepted result
        Position expectedPos = new Position(p1.position.getxPos() + a, p1.position.getyPos());

        //Moving the player 3 units to the right
        for(int i=0; i<a; i++){
            p1.moveRight(screen.getTerminalSize().getColumns());
        }

        assert(expectedPos.getxPos() == p1.position.getxPos());
        assert(expectedPos.getyPos() == p1.position.getyPos());
    }

    @Property
    public void testMovementLeft(@ForAll @Positive @IntRange(max = width/2 - offset) int a) {

        Player p1 = new Player("Player 1", new Position(screen.newTextGraphics()), 1, 3, 1, "abc", 5);

        //Excepted result
        Position expectedPos = new Position(p1.position.getxPos() - a, p1.position.getyPos());

        //Moving the player 3 units to the right
        for(int i=0; i<a; i++){
            p1.moveLeft();
        }

        assert(expectedPos.getxPos() == p1.position.getxPos());
        assert(expectedPos.getyPos() == p1.position.getyPos());
    }
}
