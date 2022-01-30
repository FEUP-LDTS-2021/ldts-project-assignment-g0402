package ldts.game;

import ldts.game.states.Instructions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class InstructionsTest {

    @Test
    public void instructionsCreation () throws IOException {
        Instructions instructions = new Instructions();
        instructions.run();
    }

}