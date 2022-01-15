package Objects.Attributes;

/**
 * This Class deals with the living status of certain Game Objects.
 * It manages how many lives does the object has. It also decides
 * whether an Object should die or only suffer some damage, according
 * to how many lives it has left.
 * */
public class Life {
    private boolean isAlive;
    private int lives;

    /** This constructor sets how many lives the Game Object
     * has at the point of creation.*/
    public Life(int lives){
        this.lives = lives;
        updateStatus();
    }

    /** This method TRIES to kill the GameObject, but it only
     * succeeds if the GameObject select only has 1 life left.*/
    public void kill(){
        this.lives = this.lives - 1;
        updateStatus();
    }

    /**This method updates the living status of the GameObject
     * depending on how many lives it has at the moment the method is called.*/
    private void updateStatus(){
        if(lives >= 1)
            isAlive = true;
        else if (lives == 0)
            isAlive = false;
    }

    public int getLives(){
        return lives;
    }

    public boolean isAlive(){
        return isAlive;
    }
}
