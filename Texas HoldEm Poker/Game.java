//Play a game
//
//Tiffany Neumann
//<tjn2124>
//

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


//Game is dealing, running the game and updating
//the variables in the player class 

public class Game {
    
    private Player p;
    private Deck cards;
    private Scanner input;
    private int playAgain;
    private double bet;
    private int handLength;
    
    public Game(String[] testHand){
        p = new Player();
        handLength = testHand.length;
        for (int i=0; i<testHand.length; i++){
            String card = testHand[i];
            String suitString = card.substring(0, 1);
            String rankString = card.substring(1, card.length());
            int suit = 0;
            int rank = Integer.parseInt(rankString);
            if (suitString.equals("c")){
                suit = 1;
            }       
            else if (suitString.equals("d")){
                suit = 2;
            }
            else if (suitString.equals("h")){
                suit = 3;
            }
            else if (suitString.equals("s")){
                suit = 4;
            }
            p.addCard(new Card(suit, rank));
        }
        p.sort();
        System.out.println(" ");
        System.out.println(checkHand(p.getHand())+"\n");
        System.out.println("Your balance: "+p.getBankroll());
        System.out.println("\n"+p.toString());
    }
	public Game(){
        p = new Player();
        cards = new Deck();
        int handLength = p.getHand().size();
        this.bet = 0.0;
    }
	
    //Allows the user to play a game in the interactive mode by
    //making sure the length of the hand is zero. if the play 
    //again instance variable is set to zero after one round the 
    //program terminates, else the user can opt to play again when
    //prompted.
    public void play(){
        playAgain = 1;
        while (playAgain == 1){
            if (handLength==0){
                cards.shuffle();
                Card a = cards.deal();
                Card b = cards.deal();
                Card c = cards.deal();
                Card d = cards.deal();
                Card e = cards.deal();
                p.addCard(a);
                p.addCard(b);
                p.addCard(c);
                p.addCard(d);
                p.addCard(e);
                p.sort();

                input = new Scanner(System.in);
                System.out.println("How many tokens would you like "+
                                   "to bet? Enter a number 1-5:");
                bet = input.nextDouble();
                if (bet>5 || bet<0){
                    input = new Scanner(System.in);
                    System.out.println("Try again. How many tokens would you like "+
                                       "to bet? Enter a number 1-5:");
                    bet = input.nextDouble();
                }
                p.bets(bet);
                System.out.println("Your balance:"+p.getBankroll());
                System.out.println(p.toString());

                input = new Scanner(System.in);
                System.out.println("Would you like to trade in your 1st Card? " +
                                   "Enter 1 for yes and 0 for no: ");
                int remove1 = input.nextInt();
                input = new Scanner(System.in);
                System.out.println("Would you like to trade in your 2nd Card? " + 
                                   "Enter 1 for yes and 0 for no: ");
                int remove2 = input.nextInt();
                input = new Scanner(System.in);
                System.out.println("Would you like to trade in your 3rd Card? " + 
                                   "Enter 1 for yes and 0 for no: ");
                int remove3 = input.nextInt();
                input = new Scanner(System.in);
                System.out.println("Would you like to trade in your 4th Card? " + 
                                   "Enter 1 for yes and 0 for no: ");
                int remove4 = input.nextInt();
                input = new Scanner(System.in);
                System.out.println("Would you like to trade in your 5th Card? " + 
                                   "Enter 1 for yes and 0 for no: ");
                int remove5 = input.nextInt();
                if (remove1 == 1){
                    p.getHand().set(0, cards.deal());
                }
                if (remove2 == 1){
                    p.getHand().set(1, cards.deal());
                    }
                if (remove3 == 1){
                    p.getHand().set(2, cards.deal());
                }
                if (remove4 == 1){
                    p.getHand().set(3, cards.deal());
                }
                if (remove5 == 1){
                    p.getHand().set(4, cards.deal());
                }
                p.sort();                
                System.out.println(" ");
                System.out.println(checkHand(p.getHand())+"\n");
                System.out.println("Your balance: "+p.getBankroll());
                System.out.println("\n"+p.toString());
                input = new Scanner(System.in);
                System.out.println("Would you like to play again? " + 
                                   "Enter 1 for yes and 0 for no: ");
                playAgain = input.nextInt();
                if (playAgain == 1){
                    p.reset();
                    }
            }
            else{
                playAgain = 0;
            }
    }
    }
    //this method calls each helper method to evaluate the hand 
    //and returns a string that corresponds to whichever method
    // returns true
    public String checkHand(ArrayList<Card> hand){
        String yourHand = "\n";
        if (royalFlush()==true){
            double odds = 250.0;
            p.winnings(odds);
            yourHand = "You got a royal flush!";
            return yourHand;
        }
        if (straightFlush()==true){
            p.winnings(50.0);
            yourHand += "You got a straight flush!";
            return yourHand;
        }
        if (fourOfAKind()==true){
            p.winnings(25.0);
            yourHand = "You got four of a kind";
            return yourHand;
        }
        if (fullHouse()==true){
            p.winnings(6.0);
            yourHand = "You got a full house";
            return yourHand;
        }
        if (flush()==true){
            p.winnings(5.0);
            yourHand = "You got a flush";
            return yourHand;
        }
        if (straight()==true){
            p.winnings(4.0);
            yourHand = "You got a straight";
            return yourHand;
        }
        if (threeOfAKind()==true){
            p.winnings(3.0);
            yourHand = "You got three of a kind";
            return yourHand;
        }
        if (twoPairs()==true){
            p.winnings(2.0);
            yourHand = "You got two pairs!";
            return yourHand;
        }
        if (onePair()==true){
            double odds = 1.0;
            p.winnings(1.0);
            yourHand = "You got a pair!";
            return yourHand;
        }
        else{
            yourHand+= highCard();
        }
        return yourHand;
    }
        //calls the flush() method and if thats true 
       //checks that the ranks are ordered ace-13
        private boolean royalFlush(){
        boolean royalFlush = false;
        for (int i = 0; i<4;i++){
            Card card1 = p.getHand().get(0);
            int rankOne = card1.getRank();
            Card card2 = p.getHand().get(1);
            int rankTwo = card2.getRank();
            Card card3 = p.getHand().get(2);
            int rankThree = card3.getRank();
            Card card4 = p.getHand().get(3);
            int rankFour  = card4.getRank();
            Card card5 = p.getHand().get(4);
            int rankFive  = card5.getRank();
            if ((flush() == true) && ((rankOne==1 && rankTwo == 10)
                                      && (rankThree == 11 && rankFour == 12)
                                     && (rankFive == 13))){ 
                royalFlush = true;
            }
        }
            return royalFlush;
        }
        //checks for a straight and a flush
        private boolean straightFlush(){
            boolean straightFlush = false;
            boolean straight = false;
            boolean flush = false;
            int counter = 0;
            for (int i = 0; i < 4; i++){
                Card card = p.getHand().get(i);//first card
                int rankOne = card.getRank();
                Card nextCard = p.getHand().get(i+1);//second card
                int rankTwo  = nextCard.getRank();
                if (rankOne+1 == rankTwo){
                    counter++;
                }
            }
            if (counter == 4){
                straight= true;
            } 
            for (int i = 1; i < 5; i++){
                Card firstCard = p.getHand().get(0);
                int suitOne  = firstCard.getSuit();
                Card nextCard = p.getHand().get(i);
                int suitTwo  = nextCard.getSuit();
                if (suitOne==suitTwo){
                    flush = true;
                    }
                else {
                    flush = false;
                    break;
                }
            }
            if (straight==true && flush==true){
                straightFlush = true;
            }
            return straightFlush;
        }
        //checks if the ranks match the 3 times which 
        // is then four of a kind
        private boolean fourOfAKind(){
            boolean fourOfAKind = false;
            int counter = 0;
            for (int i = 0; i < 5; i++){
                counter = 0;
                for (int j = 0; j <5; j++){
                    Card card = p.getHand().get(i);
                    int rankOne = card.getRank();
                    Card nextCard = p.getHand().get(j);
                    int rankTwo  = nextCard.getRank();
                    if (rankOne == rankTwo && (j != i)){
                        counter++;
                     }
                if (counter==3){
                    fourOfAKind = true;
                    }
                }
               }
            return fourOfAKind;
        }
       //checks if the hand has a pair and three of a kind
        private boolean fullHouse(){
            boolean fullHouse = false;
            boolean threeOfAKind = false;
            boolean pair = false;
            int counter = 0;
            int counter2 = 0;
            for (int i = 0; i <5; i++){//checks for 3 of a kind
                counter=0;
                for (int j = 0; j <5; j++){
                    Card card = p.getHand().get(i);
                    int rankOne = card.getRank();
                    Card nextCard = p.getHand().get(j);
                    int rankTwo  = nextCard.getRank();
                    if (rankOne == rankTwo && (j != i)){
                        counter++;
                    }
            if (counter==2){
                threeOfAKind = true;
                }
              }
            }
            for (int i = 0; i < 5; i++) {//checks for pair
                for (int j = i + 1; j < 5; j++){
                    Card card = p.getHand().get(i);
                    int rankOne = card.getRank();
                    Card nextCard = p.getHand().get(j);
                    int rankTwo  = nextCard.getRank();
                    if (rankOne == rankTwo) {
                        counter2++;
                    }
                }
            if (counter2 == 1){
                pair=true;
//             }
            }
            if (threeOfAKind==true && pair==true){
                fullHouse = true;
            }
            }
            return fullHouse;
//             }
        }
        //Checks if all the cards have the same suit
        private boolean flush(){
            boolean flush = false;
            for (int i = 1; i < 5; i++){
                Card firstCard = p.getHand().get(0);
                int suitOne  = firstCard.getSuit();
                Card nextCard = p.getHand().get(i);
                int suitTwo  = nextCard.getSuit();
                if (suitOne==suitTwo){
                    flush = true;
                    continue;
                    }
                else {
                    flush = false;
                    break;
                      }
                }
            return flush;    
        }
    //checks if the first card's rank is one less than the second
    //and accounts for ace as a high card
        private boolean straight(){
            boolean straight = false;
            int counter = 0;
            for (int i = 0; i < 4; i++){
                    Card card = p.getHand().get(i);
                    int rankOne = card.getRank();
                    Card nextCard = p.getHand().get(i+1);
                    int rankTwo  = nextCard.getRank();
                    if ((rankOne+1 == rankTwo)
                        || rankOne ==13 && rankTwo ==1){
                        counter++;
                    }
             if (counter == 4){
                 straight= true;
             }            
             }
             return straight;
            }
        // checks that the ranks match 2 times, which
        // results in three of a kind
        private boolean threeOfAKind(){
            boolean threeofAKind = false;
            int counter = 0;
            for (int i = 0; i < 5; i++){
                counter = 0;
                for (int j = 0; j <5; j++){
                    Card card = p.getHand().get(i);
                    int rankOne = card.getRank();
                    Card nextCard = p.getHand().get(j);
                    int rankTwo  = nextCard.getRank();
                    if (rankOne == rankTwo && (j != i)){
                        counter++;
                     }
            if (counter==2){
                threeofAKind = true;
                    }
            }
            }
             return threeofAKind;
         }
        // checks that the ranks match twice when comparing the 
        // the first card to the following card
        private boolean twoPairs(){
            boolean twoPair = false;
            int counter = 0;
            for (int i = 0; i < 4; i++) {
                Card card = p.getHand().get(i);
                int rankOne = card.getRank();
                Card nextCard = p.getHand().get(i+1);
                int rankTwo  = nextCard.getRank();
                if (rankOne == rankTwo){ 
                    counter++;
                }
            if (counter==2){
                twoPair =true;
                }
                }
            return twoPair;
        }
        //checks that the ranks match once 
        private boolean onePair(){
            int counter = 0;
            boolean onePair = false;
            for (int i = 0; i < 5; i++) {
                for (int j = i + 1; j < 5; j++) {
                    Card card = p.getHand().get(i);
                    int rankOne = card.getRank();
                    Card nextCard = p.getHand().get(j);
                    int rankTwo  = nextCard.getRank();
                    if (rankOne == rankTwo){
                        counter++;
                    }
                }
            if (counter == 1){
                onePair=true;
            }
            }
            return onePair;
        }
       //returns string that says no pair
        private String highCard(){
            String highCard = "No pair";
            return highCard;
                    
        }
}
