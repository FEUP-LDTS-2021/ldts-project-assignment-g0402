package Game;

import java.awt.*;
import java.net.URISyntaxException;

public class Play extends State{

    public Console console;

    public Play() throws URISyntaxException, FontFormatException {
        console = new Console();
    }

    public void run(){
        console.run();
    }
}
