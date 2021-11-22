//Creates a card deck
//
//Tiffany Neumann 
//<tjn2124>
//


public class Card implements Comparable<Card>{
	
	private int suit; //use integers 1-4 to encode the suit
	private int rank; //use integers 1-13 to encode the rank

	//make a card with suit and value
	public Card(int s, int r){
		
        this.suit = s;//1=club, 2=diamonds, 3=hearts, 4=spades
        this.rank = r;//1-13
	}
	
    //compareTo is for the comparable interface, which will 
    //now allow me to compare cards and sort them. This is
    //done in the player class in the sort() method
	public int compareTo(Card c){
		if (this.rank != c.rank){
		    int difference = this.rank - c.rank;
		    return difference;
		}
		else{
		    int difference = this.suit - c.suit;
		    return difference;
		}
	}
    //This method converts ranks and suits into 
    //strings that are easier to read
	public String toString(){
		String myCard="";
		    if (this.rank<11 && this.rank>1){
		       myCard = this.rank+" of ";}
		    else if(this.rank ==1){
			myCard ="Ace of ";
		    }
		    else if (this.rank == 11){
			 myCard = "Jack of ";
		     }
		    else if (this.rank ==12){
			myCard = "Queen of ";
		    }
		    else if( this.rank ==13){
			myCard = "King of ";
		    }
			if (this.suit ==1){
			    myCard=myCard+"clubs";
			}
			else if(this.suit ==2){
			    myCard=myCard+"diamonds";
			}
			else if(this.suit ==3){
			    myCard=myCard+"hearts";
			}
			else if(this.suit ==4){
			    myCard=myCard+"spades";
			}
        	return myCard;
	}
    
    //returns the rank of the card
    public int getRank(){
        return rank;
        
}
     //returns the suit of the card
     public int getSuit(){
       return suit;
       
   }
}
