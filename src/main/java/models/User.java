package models;

/**
 * Created by Cameron on 3/7/2016.
 */
public class User extends Player {


    public User(){
        dough = 100;
    }

    public boolean willHit(){
        if(sum>=21)
            return false;
        else
            return true;
    }

}
