package models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by michaelhilton on 1/25/16.
 */

public class Card implements Serializable {
    public final int value;
    public final Suit suit;
    private int AceValue = 11;

    @JsonCreator
    public Card(@JsonProperty("value") int value, @JsonProperty("suit") Suit suit) {
        this.value = value;
        this.suit = suit;

    }

    public Suit getSuit() {
        return suit;
    }

    public void setAce(int val){
        AceValue = val;
    }

    public int getValue() {
        switch(value) {
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10: return value;
            case 11:
            case 12:
            case 13: return 10;
            case 14: return AceValue;
            default: return 0;
        }
    }

    public String toString() {
        return this.value + this.suit.toString();
    }
}
