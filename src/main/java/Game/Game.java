package Game;

import java.awt.*;
import java.net.URISyntaxException;

public class App {
    private final KeyBoardObserver keyBoardObserver;
    public static void main(String[] args) throws URISyntaxException, FontFormatException {

        Console console = new Console();
        console.run();
    }
    public KeyBoardObserver getKeyBoardObserver() {
        return keyBoardObserver;
    }
}