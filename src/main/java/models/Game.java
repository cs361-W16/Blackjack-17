package models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Created by michaelhilton on 1/25/16.
 */
public class Game {

    public java.util.List<Card> deck = new ArrayList<>();

    public Player user = new User();
    public Player dealer = new Dealer();

    public Game(){
    }

    public void newHand(){
        user.hand.clear();
        user.sum = 0;
        dealer.hand.clear();
        dealer.sum = 0;
        user.dough-=2;
    }

    public void buildDeck() {
        for(int i = 2; i < 15; i++){
            deck.add(new Card(i,Suit.Clubs));
            deck.add(new Card(i,Suit.Hearts));
            deck.add(new Card(i,Suit.Diamonds));
            deck.add(new Card(i,Suit.Spades));
        }
    }

    public void shuffle() {
        long seed = System.nanoTime();
        Collections.shuffle(deck, new Random(seed));
    }

    public void deal(Player p) {
        p.hand.add(deck.get(deck.size() - 1));
        p.sum+=deck.get(deck.size()-1).getValue();
        deck.remove(deck.size()-1);

    }

    public void customDeal(Player p, int c){
        p.hand.add(deck.get(c));
        p.sum+=deck.get(c).getValue();
        System.out.println(deck.get(c).getValue());
        deck.remove(c);
    }

    public String getWinner(){
        if(user.sum>dealer.sum &&user.sum<22)
            return "user";
        else if(user.sum==dealer.sum)
            return "tie";
        else
            return "dealer";
    }

}
