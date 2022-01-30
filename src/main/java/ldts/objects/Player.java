package ldts.objects;

import ldts.objects.attributes.Position;

public class Player extends GameObject {

    public Player(String myName, Position position,
                  int width, int height, int life,
                  String sprite, int speed) {

        super(myName, position, width, height, life, sprite, speed, false);
    }

}
