public class Attack {
    public static Bullet doAttack(int xPos, int yPos,boolean isFromMonster){
        int[][] sprite = new int[][]{{0,0,0,0}, {0,0,0,0}};
        Bullet bullet = new Bullet("teste", xPos, yPos, 2,
                2, true, 1, 1, sprite, 2, isFromMonster);
        return bullet;
    }
}
