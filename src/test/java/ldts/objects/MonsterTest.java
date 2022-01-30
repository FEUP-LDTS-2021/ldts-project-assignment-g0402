package ldts.objects;

import ldts.actions.Attack;
import ldts.objects.attributes.Position;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    @Test
    public void testMonsterDeath(){
        this.monster = new Monster("monster", new Position(20, 20), 2, 1, 1, "pq", 4);

        this.monster.kill();

        assertFalse(this.monster.life.isAlive());
    }

    @Test
    public void testMonsterCanShoot(){
        Attack testAttack = new Attack();
        this.monster = new Monster("monster", new Position(20, 20), 2, 1, 1, "pq", 4);

        testAttack.doAttack(20, 20, true);
        this.monster.doAttack(testAttack);

        for(Bullet bullet: testAttack.bullets){
            assertTrue(bullet.isMonster());
        }
    }
}

