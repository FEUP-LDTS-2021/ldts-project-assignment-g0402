package ldts.game.states;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;

import java.awt.*;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static ldts.game.Game.font;

abstract public class State {
    protected static TerminalScreen screen;

    protected void createTerminal(int sizeFont, TerminalSize terminalSize){
        try {
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);

            terminalFactory.setTerminalEmulatorTitle("Lonely Earth Invader");

            Font loadedFont = font.deriveFont(Font.PLAIN, sizeFont);
            AWTTerminalFontConfiguration fontConfig = AWTTerminalFontConfiguration.newInstance(loadedFont);
            terminalFactory.setTerminalEmulatorFontConfiguration(fontConfig);
            terminalFactory.setForceAWTOverSwing(true);

            Terminal terminal = terminalFactory.createTerminal();

            screen = new TerminalScreen(terminal);
            screen.setCursorPosition(null);
            screen.startScreen();
            screen.doResizeIfNecessary();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clear() {
        screen.clear();
    }

    public void refresh() throws IOException {
        screen.refresh();
        screen.doResizeIfNecessary();
    }

    public static void close() throws IOException {
        screen.close();
    }

    protected void draw(){
        try {
            clear();
            drawText();
            refresh();
            TimeUnit.MILLISECONDS.sleep(20);
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }

    protected void drawText(){}
}
