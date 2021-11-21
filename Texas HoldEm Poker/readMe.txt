
Tiffany Neumann


//////////////////
Card Class

The card class creates a deck of cards and allows sorts the cards. 
The card class also allows the user to get the rank and suit of a 
specific card.

///////////////////

Deck Class 

The deck class creates an array of 52 card objects, which can be
shuffled and dealt. Each time the deck is shuffled it swaps card 
positions 999 times. 

///////////////////

Player Class

The user starts with 100 tokens, and the bankroll will adjust
according to the odds associated with their hand. If the user plays
in the interactive mode, the bankroll is adjusted based on the user's 
bet. The player class gives the game class access to the bank roll
and the hand.

///////////////////

Game Class

The game can be played with or without entering command line arguments.
To enter a command line argument and test each function enter the first 
letter of the suit you would like to have in your hand followed by a number.
In the command line version of the play() method, since the user 
doesn't place a bet, the bankroll only adjusts based on their
starting balance and the odds of the hand that they got.

I used the instance variable HandLength in order to extract the 
length of the testHand arguments class wide, so that if HandLength
is equal to zero then the loop in the play() method would execute an 
interactive game. The command line prompts the user to enter a bet
which then adjusts the bankroll from the player class. If the user
wants to play again the while loop will continue as long as they 
enter 1 when the command line prompts them at the end of one game. 

Both the interactive play() method and the command line version 
of the play method call checkHand() and use the helper methods 
to evaluate the player's hand. The hand is returned to the user 
as a string along with their bankroll balance. 



///////////////////

