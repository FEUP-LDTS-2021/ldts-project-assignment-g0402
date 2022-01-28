import Game.Level;
import Objects.Attributes.Position;
import Objects.Monster;
import Objects.MonsterWave;
import Objects.Player;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import net.jqwik.api.*;
import net.jqwik.api.constraints.IntRange;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class PositionTest {
    private final int width = 64;
    private final int height = 36;


    @Property
    public void testPositionBasic(@ForAll int x, @ForAll int y){
        Position position = new Position(x, y);

        assert (position.getxPos()==x);
        assert (position.getyPos()==y);
    }

    @Property(tries = 10)
    public void testMapBoundaries(@ForAll @IntRange(min = 1, max = width) int rangeWidth, @ForAll @IntRange(min = 1, max = height) int rangeWeight) throws IOException {
        Player p1 = new Player("player", new Position(1, 1), 1, 3, 3, "abc", 5);
        Monster m1 = new Monster("monster", new Position(2, 2), 2, 1, 1, "pq", 4);
        MonsterWave monsterWave = new MonsterWave(0, 0, 1, 1, 0, 0, m1);

        TerminalSize terminalSize = new TerminalSize(rangeWidth, rangeWeight);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
        Terminal terminal = terminalFactory.createTerminal();
        Screen screen = new TerminalScreen(terminal);

        Level level = new Level(screen.newTextGraphics(), p1, monsterWave);

        for(int i = 1; i < rangeWidth+10; ++i){
            level.movePlayer(true);
        }

        //As the player only moves to left and right
        Assertions.assertTrue(p1.position.getyPos() <= rangeWidth);
        Assertions.assertTrue(p1.position.getyPos() >= 0);
    }

    /*@Example
    public void testPositionPlayer(@ForAll("examples")Player player) throws IOException {
        TextGraphics screen = new TerminalScreen(new DefaultTerminalFactory().createTerminal()).newTextGraphics();
        Position position = new Position(screen);
        player.position.setPlayerPosDefault(player);

        assert(position.getxPos() == player.position.getxPos());
        assert(position.getyPos() == player.position.getyPos());
    }*/
}
