package Objects;

import Objects.Attributes.Position;

public class Player extends GameObject {


    public Player(String myName, Position position,
                  int height, int width, int life,
                  int level, String sprite, int speed) {

        super(myName, position, height, width, life, sprite, speed);
    }


}
