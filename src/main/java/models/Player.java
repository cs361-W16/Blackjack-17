package models;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Created by Cameron on 3/7/2016.
 */

@JsonTypeInfo(use=JsonTypeInfo.Id.CLASS, include=JsonTypeInfo.As.PROPERTY, property="@class")
public abstract class Player {

    public int dough;

    public java.util.List<Card> hand = new ArrayList<>();

    public int sum;

    public Player(){}

    public abstract boolean willHit();//{return true;}

}
