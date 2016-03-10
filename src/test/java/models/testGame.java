package models;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Cameron on 3/7/2016.
 */
public class testGame {

    @Test
    public void testGameCreation(){
        Game g = new Game();
        assertNotNull(g);
    }

    @Test
    public void testGameBuildDeck(){
        Game g = new Game();
        g.buildDeck();
        assertEquals(52,g.deck.size());
    }

    @Test
    public void testGameInit(){
        Game g = new Game();
        g.buildDeck();
        g.shuffle();
        assertNotEquals(2,g.deck.get(0).getValue());
    }

    @Test
    public void testGameStart(){
        Game g = new Game();
        g.buildDeck();
        g.shuffle();
        g.deal(g.user);
        g.deal(g.dealer);
        g.deal(g.user);
        g.deal(g.dealer);
        assertEquals(2,g.dealer.hand.size());
        assertEquals(2,g.user.hand.size());
    }

    @Test
    public void testValue(){
        Game g = new Game();
        g.buildDeck();
        g.newHand();
        g.customDeal(g.user,0);
        g.customDeal(g.dealer,3);
        g.WhoWins();
        assertEquals("dealer",g.winner);
        g.newHand();
        g.customDeal(g.user,3);
        g.customDeal(g.dealer,0);
        g.WhoWins();
        assertEquals("user",g.winner);
        g.newHand();
        g.customDeal(g.user,0);
        g.customDeal(g.dealer,0);
        g.WhoWins();
        assertEquals("tie",g.winner);

        g.newHand();
        g.dealer.hand.add(new Card(15,Suit.Clubs));
        assertEquals(0,g.dealer.hand.get(0).getValue());
    }

    @Test
    public void testAceChange(){
        Game g = new Game();
        g.buildDeck();
        g.newHand();

        g.deal(g.dealer);
        assertEquals(11,g.dealer.sum);
        g.deal(g.dealer);
        assertEquals(12,g.dealer.sum);
        g.deal(g.dealer);
        assertEquals(13,g.dealer.sum);
        g.deal(g.dealer);
        assertEquals(14,g.dealer.sum);

    }

    @Test
    public void testDoubleDown(){
        Game g = new Game();
        g.buildDeck();
        g.newHand();
        assertEquals(true,g.isActive());
        g.doubleDown();
        assertEquals(96,g.user.dough);

    }

}
