import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.gui2.Window;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import javax.swing.*;
import java.io.IOException;

import static com.googlecode.lanterna.TerminalPosition.OFFSET_1x1;

public class Console {

    private Screen screen;
    private Level level;

    public Console(){
        try {
            TerminalSize terminalSize = new TerminalSize(60, 30);
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
            terminalFactory.setTerminalEmulatorTitle("Space Invaders");

            Terminal terminal = terminalFactory.createTerminal();

            screen = new TerminalScreen(terminal);
            screen.setCursorPosition(null);
            screen.startScreen();
            screen.doResizeIfNecessary();
        } catch (IOException e){
            e.printStackTrace();
        }

        level = new Level(60, 30);
    }

    private void draw() throws IOException{
        screen.clear();
        level.draw(screen.newTextGraphics());
        screen.refresh();
    }

    private void processKey(com.googlecode.lanterna.input.KeyStroke key){
        System.out.println(key);
        switch (key.getKeyType()) {
            case ArrowLeft  -> level.moveHero(false);
            case ArrowRight -> level.moveHero(true);
        }
    }

    public void run() {
        try {
            while(true) {
                draw();
                com.googlecode.lanterna.input.KeyStroke key = screen.readInput();
                processKey(key);

                if (key.getKeyType() == KeyType.Character && key.getCharacter() == ('q'))
                    screen.close();
                if (key.getKeyType() == KeyType.EOF)
                    break;
            }
        } catch (IOException e){
            e.printStackTrace();
        }

    }

}