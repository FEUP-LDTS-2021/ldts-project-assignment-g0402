public class Attack {
    public static Bullet doAttack(int xPos, int yPos,boolean isFromMonster){
        Bullet bullet = new Bullet("teste", new Position(xPos, yPos), 2,
                2, true, 1, 1, "a", 2, isFromMonster);
        return bullet;
    }
}
