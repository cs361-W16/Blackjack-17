package models;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by michaelhilton on 1/26/16.
 */
public class testCard {
    @Test
    public void testGetSuit(){
        Card c = new Card(5,Suit.Clubs);
        assertEquals(Suit.Clubs,c.getSuit());
    }

    @Test
    public void testToString(){
        Card c = new Card(5,Suit.Clubs);
        assertEquals("5Clubs",c.toString());
    }

    @Test
    public void testValue(){
        Card c = new Card(13,Suit.Diamonds);
        assertEquals(10,c.getValue());

        Card Ace = new Card(14,Suit.Hearts);
        assertEquals(11,Ace.getValue());
        Ace.setAce(1);
        assertEquals(1,Ace.getValue());
    }

}
