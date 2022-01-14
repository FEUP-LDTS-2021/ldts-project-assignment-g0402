import Objects.*;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import net.jqwik.api.*;
import net.jqwik.api.arbitraries.IntegerArbitrary;

import java.io.IOException;
import java.util.ArrayList;

public class testPosition {

    @Property
    public void testPositionBasic(@ForAll int x, @ForAll int y){
        Position position = new Position(x, y);

        assert (position.getxPos()==x);
        assert (position.getyPos()==y);
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
