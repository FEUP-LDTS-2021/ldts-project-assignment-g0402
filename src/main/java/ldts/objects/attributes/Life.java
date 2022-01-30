package ldts.objects.attributes;

/**
 * This Class deals with the living status of certain Game Objects.
 * It manages how many lives does the object has. It also decides
 * whether an Object should die or only suffer some damage, according
 * to how many lives it has left.
 * */
public class Life {
    private boolean isAlive;
    private final int lives;
    private int current;
    private int deadRecently = -1;

    /** This constructor sets how many lives the Game Object
     * has at the point of creation.*/
    public Life(int lives){
        this.lives = lives;
        this.current = lives;
        updateStatus();
    }

    /** This method TRIES to kill the GameObject, but it only
     * succeeds if the GameObject select only has 1 life left.*/
    public void kill(){
        if(this.current > 0){
            this.current = this.current - 1;
            updateStatus();
        }
        if(this.current==0){
            deadRecently = 0;
        }
    }

    /**This method updates the living status of the GameObject
     * depending on how many lives it has at the moment the method is called.*/
    private void updateStatus(){
        if(current >= 1)
            isAlive = true;
        else if (current == 0)
            isAlive = false;
    }

    /**This method returns the INITIAL amount of lives this object had.*/
    public int getLives(){
        return lives;
    }

    /**This method returns the CURRENT amount of lives this object has.*/
    public int getCurrentLives(){
        return current;
    }

    public boolean isAlive(){
        return isAlive;
    }

    public boolean isDeadRecently(){
        if(deadRecently > -1 && deadRecently < 3) {
            deadRecently++;
            return true;
        }
        return false;
    }
}
