//Create a deck, shuffle and deal
//
//Tiffany Neumann 


import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Deck {
	private Card[] cards;
	private int top; 

    //make a 52 card deck of card objects
        public Deck(){
		cards = new Card[52];
		top = 0;
		int counter = 0;
		for (int rank = 1; rank<=13; rank++){
		    for (int suit = 1; suit<=4; suit++){
			cards[counter] = new Card(suit, rank);
			counter++;
		    }
		}
	    }
	
    //shuffles the deck by swapping the cards and 
    //using a random number between 0-52 to set the index
	public void shuffle(){
	       int  counter = 0;
	       Card temp;
	       while (counter<1000){
		  for (int i = 0; i < cards.length; i++){
		      int random = ((int) (Math.random() * 52));
		      temp = cards[i];
		      cards[i] = cards[random];
		      cards[random] = temp;
		      counter++;
		      }
		  }
	    }
    //deals the top card of the deck and checks that 
    //the top is less than 52, if it's over, sets top back to zero
    //and shuffles the deck
	public Card deal(){
		Card nextCard = cards[top]; 
		if (top>cards.length){
		    top=0;
		    shuffle();
		}
		else if (top<cards.length){
		    top++;
		    nextCard = cards[top-1];        
		}
		return nextCard;
	}
}
