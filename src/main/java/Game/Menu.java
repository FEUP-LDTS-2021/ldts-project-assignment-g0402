package Game;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

public class Menu extends State {
    TextGraphics graphics;
    Collection<SGR> title = new ArrayList<>();
    Collection<SGR> text = new ArrayList<>();
    Collection<SGR> selected = new ArrayList<>();
    int n = 1;
    int column = (width/2)-3, row = height/4;

    public Menu() {
        createTerminal();
        graphics = screen.newTextGraphics();

        title.add(SGR.CIRCLED);
        title.add(SGR.BOLD);
        text.add(SGR.BORDERED);
        selected.add(SGR.BLINK);
    }

    public void run() throws IOException {
        boolean exit = false;
        while(!exit){
            draw();
            KeyStroke key = screen.readInput();
            if(key.getKeyType() == KeyType.Character) {
                switch (key.getCharacter()) {
                    case 'q', 'Q' -> exit = true;
                }
            }
            else{
                switch (key.getKeyType()) {
                    case ArrowDown -> moveDown();
                    case ArrowUp -> moveUp();
                }
            }
        }
    }

    private void drawMenu(){
        graphics.setBackgroundColor(Game.colorScenario);
        graphics.setForegroundColor(Game.colorPlayer);
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');

        /*Actual Menu*/

        graphics.putString(column, row, "MENU", title);
        update();
    }

    private void draw(){
        try {
            clear();
            drawMenu();
            refresh();
            TimeUnit.MILLISECONDS.sleep(20);
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }

    private void update(){
        switch (this.n) {
            case 1 -> {     //PLAY
                graphics.putString(column, row + 4, "PLAY", selected);
                graphics.putString(column - 4, row + 6, "INSTRUCTIONS", text);
                graphics.putString(column, row + 8, "EXIT", text);
            }
            case 2 -> {     //INSTRUCTIONS
                graphics.putString(column, row + 4, "PLAY", text);
                graphics.putString(column - 4, row + 6, "INSTRUCTIONS", selected);
                graphics.putString(column, row + 8, "EXIT", text);
            }
            case 3 -> {     //QUIT
                graphics.putString(column, row + 4, "PLAY", text);
                graphics.putString(column - 4, row + 6, "INSTRUCTIONS", text);
                graphics.putString(column, row + 8, "EXIT", selected);
            }
        }
    }

    private void moveUp(){
        if(this.n > 1) this.n = n - 1;
    }

    private void moveDown(){
        if(this.n < 3) this.n = n + 1;
    }
}
