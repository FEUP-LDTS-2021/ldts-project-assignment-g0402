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
                    case Enter -> select();
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

        graphics.putString(column, row, "MENU", title);
        update();
    }

    private void update(){
        switch (this.pointer) {
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
        if(this.pointer > 1) this.pointer = pointer - 1;
    }

    private void moveDown(){
        if(this.pointer < 3) this.pointer = pointer + 1;
    }

    private void select() throws IOException {
        switch(this.pointer){
            case 2:
                screen.close();
                screen.getTerminal().close();
                Instructions instructions = new Instructions();
                instructions.run();
            case 3:
                screen.stopScreen();
        }
    }
}
