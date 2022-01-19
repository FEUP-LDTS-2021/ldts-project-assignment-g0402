package Objects;

import Objects.Attributes.Position;

public class Player extends GameObject {

    public Player(String myName, Position position,
                  int width, int height, int life,
                  String sprite, int speed) {

        super(myName, position, width, height, life, sprite, speed);
        setIsMonster(false);
    }

}
