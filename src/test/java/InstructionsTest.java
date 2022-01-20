import Game.Instructions;
import Game.Menu;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class InstructionsTest {

    @Test
    public void instructionsCreation () throws IOException {
        Instructions instructions = new Instructions();
        instructions.run();
    }

}