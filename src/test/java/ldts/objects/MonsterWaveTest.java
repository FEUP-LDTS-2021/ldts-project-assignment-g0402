package ldts.objects;

import ldts.objects.attributes.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MonsterWaveTest {
    Monster monster;
    MonsterWave monsterWave;
    int width = 64;
    int height = 36;

    @BeforeEach
    public void setupMonster() {
        this.monster = new Monster("monster", new Position(0, 0), 2, 1, 1, "pq", 4);
    }

    @Test
    public void testGetPosRight(){
        this.monsterWave = new MonsterWave(3, 3, 5, 5, 5, 3, monster);
        this.monsterWave.populateWave(monster);

        Assertions.assertEquals(23, this.monsterWave.getPosRight());
    }

    @Test
    public void testGetPosDown(){
        this.monsterWave = new MonsterWave(3, 3, 5, 5, 5, 3, monster);
        this.monsterWave.populateWave(monster);

        Assertions.assertEquals(15, this.monsterWave.getPosDown());
    }

    @Test
    public void testMonsterWaveMovement(){
        this.monsterWave = new MonsterWave(3, 3, 5, 5, 5, 3, monster);
        this.monsterWave.populateWave(monster);

        this.monsterWave.moveWave(width, height);

        Assertions.assertTrue(this.monsterWave.getPosRight() <= width);
        Assertions.assertTrue(this.monsterWave.getPosDown() <= height);
    }
}
