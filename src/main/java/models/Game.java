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
    public String winner;
    private boolean active;
    public int bet;

    public Game(){
        active=false;
    }

    public boolean isActive(){
        return active;
    }

    public void newHand(){
        winner = "";
        bet = 2;
        user.hand.clear();
        user.sum = 0;
        dealer.hand.clear();
        dealer.sum = 0;
        user.dough -= bet;
        active = true;
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
        //if(deck.isEmpty())
        //    shuffle();

        p.hand.add(deck.get(deck.size() - 1));
        p.sum += deck.get(deck.size() - 1).getValue();
        deck.remove(deck.size() - 1);

        for (int i = 0; i < p.hand.size(); i++) {
            if (p.hand.get(i).getValue() == 11 && p.sum > 21) {
                p.hand.get(i).setAce(1);
                p.sum -= 10;
            }
        }
    }

    public void doubleDown(){
        if(bet<4) {
            user.dough -= bet;
            bet += bet;
        }
    }

    public void customDeal(Player p, int c){
        p.hand.add(deck.get(c));
        p.sum+=deck.get(c).getValue();
        deck.remove(c);
    }

    public void WhoWins(){
        active = false;
        if((user.sum>dealer.sum || dealer.sum>22)&&user.sum<22) {
            winner = "user";
            user.dough=user.dough+(2*bet);
        }
        else if((user.sum<dealer.sum || user.sum>22)&& dealer.sum<22)
            winner = "dealer";
        else{
            winner = "tie";
            user.dough+=bet;
        }
    }

}
