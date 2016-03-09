package models;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by Cameron on 3/7/2016.
 */
public class testPlayer {


    @Test
    public void testPlayers(){
        Game g = new Game();
        g.buildDeck();
        assertEquals(100,g.user.dough);
        g.customDeal(g.dealer,45);
        assertEquals(true,g.dealer.willHit());
        g.customDeal(g.dealer,45);
        assertEquals(false,g.dealer.willHit());

        assertEquals(true,g.user.willHit());
    }
}
