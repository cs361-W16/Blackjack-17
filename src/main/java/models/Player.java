package models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Created by Cameron on 3/7/2016.
 */
public abstract class Player {

    public int dough;

    public java.util.List<Card> hand = new ArrayList<>();

    public int sum;

    public Player(){}

    public abstract boolean willHit();

}
