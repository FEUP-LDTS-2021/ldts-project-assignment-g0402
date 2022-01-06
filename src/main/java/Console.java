import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import javax.swing.*;
import java.io.IOException;

public class Console {

    private Screen screen;
    private Level level;

    public Console(){
        try {
            TerminalSize terminalSize = new TerminalSize(50, 70);
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
            Terminal terminal = terminalFactory.createTerminal();
            screen = new TerminalScreen(terminal);

            screen.setCursorPosition(null);
            screen.startScreen();
            screen.doResizeIfNecessary();
        } catch (IOException e){
            e.printStackTrace();
        }

        level = new Level(50, 70);
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