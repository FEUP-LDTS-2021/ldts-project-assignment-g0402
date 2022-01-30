package ldts.objects;

import ldts.objects.attributes.Position;
import ldts.objects.Monster;
import net.jqwik.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MonsterTest {
    Monster monster;

    @Property()
    public void testMonsterMovement(@ForAll("intRange") int pos){
        this.monster = new Monster("monster", new Position(pos, pos), 2, 1, 1, "pq", 4);

        this.monster.moveDown();
        this.monster.moveLeft();
        this.monster.moveRight(30);

        assert(monster.position.getxPos() != pos || monster.position.getyPos() != pos);
    }

    @Provide
    Arbitrary<Integer> intRange(){
        return Arbitraries.integers().filter(n -> (n <= 30 && n >= 0));
    }

    @Test
    public void testMonsterDeath(){
        this.monster = new Monster("monster", new Position(20, 20), 2, 1, 1, "pq", 4);

        this.monster.kill();

        Assertions.assertFalse(this.monster.life.isAlive());
    }

    /*@Test
    public void testMonsterCanShoot(){
        Attack testAttack = new Attack();
        this.monster = new Monster("monster", new Position(20, 20), 2, 1, 1, "pq", 4);

        testAttack.doAttack(20, 20, true);
        this.monster.doAttack(testAttack);

        for(Bullet bullet: testAttack.bullets){
            assertTrue(bullet.isMonster());
        }
    }*/
}

