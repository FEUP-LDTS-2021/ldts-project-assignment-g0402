import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import com.googlecode.lanterna.terminal.swing.TerminalEmulatorAutoCloseTrigger;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Console {

    private Screen screen;
    private Level level;
    private int width = 77;
    private int height = 55;

    private boolean exitThread = false;

    public Console() throws FontFormatException, URISyntaxException {
        try {
            /*Import font for the game*/
            URL resource = getClass().getClassLoader().getResource("pixel8bit.ttf");
            File fontFile = new File(resource.toURI());
            Font font =  Font.createFont(Font.TRUETYPE_FONT, fontFile);

            TerminalSize terminalSize = new TerminalSize(width, height);
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);

            terminalFactory.setTerminalEmulatorTitle("Space Invaders");

            Font loadedFont = font.deriveFont(Font.PLAIN, 15);
            AWTTerminalFontConfiguration fontConfig = AWTTerminalFontConfiguration.newInstance(loadedFont);
            terminalFactory.setTerminalEmulatorFontConfiguration(fontConfig);
            terminalFactory.setForceAWTOverSwing(true);

            Terminal terminal = terminalFactory.createTerminal();

            screen = new TerminalScreen(terminal);
            screen.setCursorPosition(null);
            screen.startScreen();
            screen.doResizeIfNecessary();

        } catch (IOException e){
            e.printStackTrace();
        }

        level = new Level( width, height);
    }

    private void draw() throws IOException{
        screen.clear();
        level.draw(screen.newTextGraphics());
        screen.refresh();
    }

    private void processKey(com.googlecode.lanterna.input.KeyStroke key){
        System.out.println(key);
        switch (key.getKeyType()) {
            case ArrowLeft: level.movePlayer(false);
            case ArrowRight: level.movePlayer(true);
        }
    }

    public void run() {
        /*try {
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
        }*/

        GameThread thread1 = new GameThread();

        thread1.start();
    }

    class GameThread extends Thread{
        @Override
        public void run(){
            try {
                while(!exitThread) {
                    draw();
                    update();
                    /*try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }*/

                    PlayerThread thread2 = new PlayerThread();
                    thread2.start();
                }
            } catch (IOException e){
                e.printStackTrace();
            }
        }

        protected void update() {
            level.moveWave( 2);
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    class PlayerThread extends Thread{
        @Override
        public void run(){
            try {
                while(!exitThread) {
                    com.googlecode.lanterna.input.KeyStroke key = screen.readInput();
                    processKey(key);

                    if (key.getKeyType() == KeyType.Character && key.getCharacter() == ('q')) {
                        exitThread = true;
                        screen.close();
                    }
                    if (key.getKeyType() == KeyType.EOF) {
                        exitThread = true;
                        break;
                    }
                }
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

}