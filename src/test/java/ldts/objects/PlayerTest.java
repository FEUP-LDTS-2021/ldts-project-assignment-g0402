package ldts.objects;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import ldts.actions.Attack;
import ldts.objects.attributes.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class PlayerTest {

    protected final int width = 60, height = 60;
    TerminalSize terminalSize = new TerminalSize(width, height);
    DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
    Terminal terminal = terminalFactory.createTerminal();
    Screen screen = new TerminalScreen(terminal);

    public PlayerTest() throws IOException {
    }

    @Test
    public void testPlayerMovementRight() {

        Player p1 = new Player("Player 1", new Position(screen.newTextGraphics()), 1, 3, 1, "abc", 5);

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
    public void testPlayerMovementLeft() {

        Player p1 = new Player("Player 1", new Position(screen.newTextGraphics()), 1, 3, 1, "abc", 5);

        //Excepted result
        Position expectedPos = new Position(p1.position.getxPos() - 3, p1.position.getyPos());

        //Moving the player 3 units to the left
        for(int i=0; i<3; i++){
            p1.moveLeft();
        }

        assert(expectedPos.getxPos() == p1.position.getxPos());
        assert(expectedPos.getyPos() == p1.position.getyPos());
    }

    @Test
    public void testPlayerCanShoot(){
        Player p1 = new Player("Player 1", new Position(screen.newTextGraphics()), 1, 3, 1, "abc", 5);
        Attack attack = new Attack();

        p1.doAttack(attack);

        for(Bullet bullet : attack.bullets){
            Assertions.assertFalse(bullet.isMonster());
        }
    }

}
