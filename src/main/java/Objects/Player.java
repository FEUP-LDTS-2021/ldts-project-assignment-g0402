package Objects;

import Game.Game;
import Objects.Attributes.Position;
import com.googlecode.lanterna.TextColor;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class Player extends GameObject {


    public Player(String myName, Position position,
                  int width, int height, int life,
                  String sprite, int speed) {

        super(myName, position, width, height, life, sprite, speed);
        this.color = new TextColor.RGB(255,255,255);
        this.isMonster = false;
    }

}
