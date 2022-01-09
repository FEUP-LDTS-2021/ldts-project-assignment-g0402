
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class testPlayer {
    @Test
    public void testPlayerMovement() {
        int[][] sprite = new int[][]{{0, 0, 1, 0}, {1, 0, 1, 1}, {1, 1, 0, 1}, {0, 1, 0, 0}};
        int heightPlayer = 2;
        int widthPlayer = 2;
        int distanceFromConsoleFloor = 4;
        Player player = new Player("Gabriel Coelho", 10,
                10, heightPlayer,
                widthPlayer, true, 1, 1, sprite, 5);
        player.moveLeft();
        assertEquals(9, player.getX());
    }
}
