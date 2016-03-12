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
    public Player p2 = new User();
    public Player dealer = new Dealer();
    public String winner;
    private boolean active;
    private boolean sphand;
    private boolean doubled;
    public int bet;

    public Game(){
        active=false;
    }

    public boolean isActive(){
        return active;
    }

    public boolean canSplit() {
        return sphand;
    }

    public boolean canDouble() {
        return doubled;
    }

    public void newHand(){
        winner = "";
        bet = 2;
        user.hand.clear();
        p2.hand.clear();
        user.sum = 0;
        dealer.hand.clear();
        dealer.sum = 0;
        user.dough -= bet;
        active = true;
        doubled = false;
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
        if (p.hand.size() == 2) {
            if (p.hand.get(0).getValue() == p.hand.get(1).getValue()) {
                sphand=true;
            }
        }
    }

    public void splithand(Player p) {
        Player p2 = new User();
        p2.hand.set(0, p.hand.get(1));
        p.hand.remove(1);
    }


    public void doubleDown(){
        doubled=true;
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
        if((user.sum>dealer.sum || dealer.sum>21)&&user.sum<22) {
            winner = "USER";
            user.dough=user.dough+(2*bet);
        }
        else if((user.sum<dealer.sum || user.sum>21)&& dealer.sum<22)
            winner = "DEALER";
        else{
            winner = "TIE";
            user.dough+=bet;
        }

    }

}
