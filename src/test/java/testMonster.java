import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import com.googlecode.lanterna.terminal.swing.SwingTerminalFontConfiguration;
import java.util.concurrent.TimeUnit;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class testMonster {
    @Test
    public void MoveTest1() {
        int[][] sprite = new int[][]{{0,0,1,0}, {1,0,1,1}, {1,1,0,1}, {0,1,0,0}};
        Monster myLittleEvil = new Monster("Gabriel Coelho", 0, 0,
                                        1, 1, true, 1, 1, sprite, 5);
        myLittleEvil.move(1, 1);
        assertEquals(1, myLittleEvil.getxPosic());
        assertEquals(1, myLittleEvil.getyPosic());
    }
    @Test
    public void MoveTest2() {
        int[][] sprite = new int[][]{{0,0,1,0}, {1,0,1,1}, {1,1,0,1}, {0,1,0,0}};
        Monster myLittleEvil = new Monster("Gabriel Coelho", 0, 0,
                1, 1, true, 1, 1, sprite, 5);
        myLittleEvil.move(0, 1);
        assertEquals(0, myLittleEvil.getxPosic());
        assertEquals(1, myLittleEvil.getyPosic());
    }
    @Test
    public void MoveTest3() {
        int[][] sprite = new int[][]{{0,0,1,0}, {1,0,1,1}, {1,1,0,1}, {0,1,0,0}};
        Monster myLittleEvil = new Monster("Gabriel Coelho", 0, 0,
                1, 1, true, 1, 1, sprite, 5);
        myLittleEvil.move(-1, 1);
        assertEquals(-1, myLittleEvil.getxPosic());
        assertEquals(1, myLittleEvil.getyPosic());
    }
    public static void main(String[] args) throws IOException {

        Terminal terminal;
        Font font = new Font("WenQuanYi Zen Hei Mono", Font.BOLD, 7);
        AWTTerminalFontConfiguration cfg = new SwingTerminalFontConfiguration(true, AWTTerminalFontConfiguration.BoldMode.NOTHING, font);
        TerminalSize terminalSize = new TerminalSize(100, 100);
        terminal = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize)
                .setTerminalEmulatorFontConfiguration(cfg).createTerminal();
        Screen screen = new TerminalScreen(terminal);

        screen.startScreen();
        screen.clear();
        TextGraphics textGraphics = screen.newTextGraphics();
        int[][] sprite = new int[][]{{0,0,1,0}, {1,0,1,1}, {1,1,0,1}, {0,1,0,0}};
        Monster myLittleEvil = new Monster("Gabriel Coelho", 0, 0,
                1, 1, true, 1, 1, sprite, 5);
        myLittleEvil.Draw(textGraphics);
        screen.refresh();
        screen.clear();
        try
        {
            Thread.sleep(5000);
            System.out.println("oi");
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
        myLittleEvil.move(10,10);
        myLittleEvil.Draw(textGraphics);
        screen.refresh();

    }
}

