package ldts.game;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

import java.io.IOException;

public class Instructions extends State{
    TextGraphics graphics;
    protected final int width = 32;
    protected final int height = 18;
    int column = (width/2)-7, row = (height/4)-1;


    public void run() throws IOException {

        graphics = screen.newTextGraphics();

        while(Game.state == 3){
            draw();
            KeyStroke key = screen.readInput();
            if(key.getKeyType() == KeyType.Character) {
                Character c = key.getCharacter();
                if (c == 'm' || c == 'M' || c == 'q' || c == 'Q') {
                    Game.state = 1;
                }
            }
        }
    }


    @Override
    protected void drawText(){
        graphics.setBackgroundColor(Game.colorScenario);
        graphics.setForegroundColor(Game.colorPlayer);
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');

        /*Actual Instructions*/

        graphics.putString(column, row, "INSTRUCTIONS:", SGR.BOLD);

        graphics.putString(2, row+3, "KILL ALL THE ENEMIES TO WIN");
        graphics.putString(1, row+4, "CAREFUL: YOU ONLY HAVE 3 LIVES");
        graphics.putString(3, row+7, "1. PRESS = TO SHOOT");
        graphics.putString(3, row+9, "2. PRESS < AND > TO MOVE");

        graphics.setForegroundColor(Game.colorMonster);
        graphics.putString(width - 19, height-2, "PRESS M TO GO BACK");
    }
}
