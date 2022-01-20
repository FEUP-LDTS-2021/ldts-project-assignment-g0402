import Objects.Attributes.Life;
import Objects.Attributes.Position;
import Objects.Player;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.Positive;
import net.jqwik.api.lifecycle.BeforeProperty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class LifeTest {
    Player player;
    private final int tries = 10;

    @BeforeEach
    @BeforeProperty
    public void setUpPlayer(){
        this.player = new Player("p1", new Position(10, 10), 3, 1, 10, "abc", 5);
    }

    @Property
    public void testPlayerLives(@ForAll @Positive int lives){
        this.player.life = new Life(lives);

        int expectedLives;

        for (int i=0; i<tries; i++){
            player.kill();
        }

        if(tries>lives){
            expectedLives = 0;
        }
        else expectedLives = lives-tries;

        assertEquals(expectedLives, player.life.getLives());
    }

    @Test
    public void testPlayerIsAlive(){
        int lives = this.player.life.getLives();

        for(int i=0; i < lives; i++){
            this.player.kill();
        }

        assertFalse(this.player.life.isAlive());
    }
}
