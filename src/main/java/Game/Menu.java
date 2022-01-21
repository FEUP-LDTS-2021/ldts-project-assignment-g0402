package Game;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

public class Menu extends State {
    TextGraphics graphics;
    int pointer = 1;
    int column = (width/2)-3, row = height/4;

    public Menu() {
        createTerminal();
        graphics = screen.newTextGraphics();
    }

    public void run() throws IOException {

        while(Game.state == 1){
            draw();
            KeyStroke key = screen.readInput();
            if(key.getKeyType() == KeyType.Character) {
                switch (key.getCharacter()) {
                    case 'q', 'Q' -> {
                        Game.exit = true;
                    }
                }
            }
            else{
                switch (key.getKeyType()) {
                    case Enter -> select();
                    case ArrowDown -> moveDown();
                    case ArrowUp -> moveUp();
                }
            }
        }
    }

    @Override
    protected void drawText(){
        graphics.setBackgroundColor(Game.colorScenario);
        graphics.setForegroundColor(Game.colorPlayer);
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');

        /*Actual Menu*/

        graphics.putString(column, row, "MENU", SGR.BOLD);
        update();
    }

    private void update(){
        switch (this.pointer) {
            case 1 -> {     //PLAY
                graphics.putString(column - 2, row + 4, "> PLAY");
                graphics.putString(column - 4, row + 6, "INSTRUCTIONS");
                graphics.putString(column, row + 8, "EXIT");
            }
            case 2 -> {     //INSTRUCTIONS
                graphics.putString(column, row + 4, "PLAY");
                graphics.putString(column - 6, row + 6, "> INSTRUCTIONS");
                graphics.putString(column, row + 8, "EXIT");
            }
            case 3 -> {     //QUIT
                graphics.putString(column, row + 4, "PLAY");
                graphics.putString(column - 4, row + 6, "INSTRUCTIONS");
                graphics.putString(column - 2, row + 8, "> EXIT");
            }
        }
    }

    private void moveUp(){
        if(this.pointer > 1) this.pointer = pointer - 1;
    }

    private void moveDown(){
        if(this.pointer < 3) this.pointer = pointer + 1;
    }

    private void select() throws IOException {
        switch (this.pointer) {
            case 1 -> {
                Game.state = 2;
                Game.exit = true;
            }
            case 2 -> Game.state = 3;
            case 3 -> Game.exit = true;
        }
    }
}
