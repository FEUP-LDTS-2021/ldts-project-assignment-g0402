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

public class Instructions extends State{
    TextGraphics graphics;
    int column = (width/2)-7, row = (height/4)-1;

    public Instructions() {
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
                    case 'q', 'Q', 'm', 'M' -> exit = true;
                }
            }
        }
        screen.close();
        screen.getTerminal().close();
        Menu menu = new Menu();
        menu.run();
    }


    @Override
    protected void drawText() {
        graphics.setBackgroundColor(Game.colorScenario);
        graphics.setForegroundColor(Game.colorPlayer);
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');

        /*Actual Menu*/

        graphics.putString(column, row, "INSTRUCTIONS", title);

        graphics.putString(2, row+3, "KILL ALL THE ENEMIES TO WIN", text);
        graphics.putString(1, row+4, "CAREFUL: YOU ONLY HAVE 3 LIVES", text);
        graphics.putString(3, row+7, "1. PRESS = TO SHOOT", text);
        graphics.putString(3, row+9, "2. PRESS < AND > TO MOVE", text);

        graphics.putString(width - 19, height-2, "PRESS M TO GO BACK", text);
    }
}
