public class Attack {
    public static Bullet doAttack(int xPos, int yPos,boolean isFromMonster){
        Bullet bullet = new Bullet("teste", new Position(xPos, yPos), 1,
                1, true, 1, 1, "z", 2, isFromMonster);
        return bullet;
    }
}
