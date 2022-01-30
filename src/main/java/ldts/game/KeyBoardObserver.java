package ldts.game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import static ldts.game.Console.Action.*;


public class KeyBoardObserver extends KeyAdapter {

    private KeyBoardListener listener;
    public KeyBoardObserver() {}

    @Override
    public void keyPressed(KeyEvent e) {
        super.keyPressed(e);

        switch (e.getKeyCode()) {
            case KeyEvent.VK_Q, KeyEvent.VK_ESCAPE -> listener.keyPressed(QUIT);
            case KeyEvent.VK_LEFT -> listener.keyPressed(LEFT);
            case KeyEvent.VK_RIGHT -> listener.keyPressed(RIGHT);
            case KeyEvent.VK_UP, KeyEvent.VK_SPACE -> listener.keyPressed(SHOOT);
        }
    }

    public void setListener(KeyBoardListener listener) {
        this.listener = listener;
    }
}
