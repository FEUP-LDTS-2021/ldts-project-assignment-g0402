package ldts.game;

import ldts.game.states.Menu;
import org.junit.jupiter.api.Test;

import java.io.IOException;


class MenuTest {

    @Test
    public void menuCreation () throws IOException {
        Menu menu = new Menu();
        menu.run();
    }

}