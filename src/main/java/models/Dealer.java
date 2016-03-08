package models;

/**
 * Created by Cameron on 3/7/2016.
 */
public class Dealer extends Player {

    Dealer(){}

    public boolean willHit(){
        if(sum>=17)
            return false;
        else
            return true;
    }
}
