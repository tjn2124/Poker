//Creates a player
//
//Tiffany Neumann


import java.util.ArrayList;
import java.util.Collections;


public class Player {
    
    private ArrayList<Card> hand;
    private double bankroll;
    private double bet;
    
    public Player(){
        hand = new ArrayList<Card>();
        bankroll = 100.0;
        bet = 0.0;
	}
    // add card to the arraylist hand
    public void addCard(Card c){
        hand.add(c);
	}

    //remove cards
	public void removeCard(Card c){
        hand.remove(c);
        }
    
    // set the instance variable bet to the players bet
    // and adjust bankroll by how much the player bets
    public void bets(double amt){
        bet = amt;
        bankroll = bankroll - amt;
    }
    // Adjust winnings if the player places a bet
    // And in command line testing
    // if the player does not bet then add the
    // odds associated with their hand to their bankroll
    public void winnings(double odds){
        if (bet>0){
            bankroll += bet*odds;
        }
        else{
            bankroll+=odds;
        }
    }
	
    //return current balance of bankroll
    public double getBankroll(){
        return bankroll;
    }
	
    //returns the hand as an arraylist
    public ArrayList<Card> getHand(){
        return hand;
    }
    
    //converts the hand to a string
    public String toString(){
        String yourHand = "Your hand: \n\n";
        for (Card each: hand){
            yourHand += each.toString() + "\n";
        }
        return yourHand;
    }
    //sorts the hand
    public void sort(){
        Collections.sort(hand);
    }
	
    //this method clears the hand for a second round
    public void reset(){
        hand.clear();
    }
}


