import java.util.Scanner;

public class Blackjack {
    private static String input;
    private static Scanner scanner;
    private static boolean playing;


    public static void main(String[] args) throws InterruptedException {
        scanner = new Scanner(System.in);
        playing = true;
        Deck deck = new Deck();
        Player player = new Player();
        Player dealer = new Player();
        String input;
        
        // amount to pause
        int pause = 1000;
        
        while (playing) {
            deck.resetDeck();
            player.resetPlayer();
            dealer.resetPlayer();
            System.out.println("Welcome to the Blackjack Casino! It'll be you versus our dealer.");
            Thread.sleep(pause);
            System.out.println("Dealing out cards...");
            Thread.sleep(pause);
            player.addToHand(deck.drawCard());
            dealer.addToHand(deck.drawCard());
            player.addToHand(deck.drawCard());
            dealer.addToHand(deck.drawCard());
            System.out.println("You have " + player.toString());
            Thread.sleep(pause);
            System.out.println("You see one of the dealer's cards: " +
            		dealer.getHand().peekFirst().toString());
            Thread.sleep(pause);
            
            if (!player.hasBlackjack()) {   
	            System.out.println("Will you hit [0] or stay [1]?");
	            while (true) {
                    input = scanner.nextLine();
                    System.out.println(input);
                    if (input.equals("0")) {
                    	// draw card
                    	System.out.println("Hitting. Drawing a card...");
                        Thread.sleep(pause);
                    	Card drawnCard = deck.drawCard();
                    	player.addToHand(drawnCard);
                    	System.out.println("You drew " + drawnCard.toString());
                        Thread.sleep(pause);
                        System.out.println("You have " + player.toString());
                        Thread.sleep(pause);
                        if (player.isBust()) {
                        	System.out.println("Uh oh, you bust. Dealer wins!");
                            Thread.sleep(pause);
                        	break;
                        } else {
                            System.out.println("Will you hit [0] or stay [1]?");
                            Thread.sleep(pause);
                        }
                    } else if (input.equals("1")) {
                    	System.out.println("Staying. Let's see what the dealer gets.");
                        Thread.sleep(pause);
                        break;
                    } else {
                    	System.out.println("Please enter either a 0 if you want to hit or a 1 if"
                    			+ " you want to stay");
                    }
	            }
	        // you have blackjack
            } else {
            	// tie if dealer has blackjack too
            	if (dealer.hasBlackjack()) {
            		System.out.println("You have blackjack, but so does the dealer. You tie!");
                // else you win
            	} else {
            		System.out.println("You win on a blackjack!");
            	}
            	if (askPlayAgain()) {
            		continue;
            	} else {
            		System.out.println("Nice playing blackjack with you!");
            		System.exit(0);
            	};
            }
            
            if (player.isBust()) {
            	if (askPlayAgain()) {
            		continue;
            	} else {
            		System.out.println("Nice playing blackjack with you!");
            		System.exit(0);
            	};
            }
            
            if (dealer.hasBlackjack()) {
            	System.out.println("Dealer has " + dealer.toString());
            	System.out.println("Dealer has blackjack, you lose!");
            	if (askPlayAgain()) {
            		continue;  
            	} else {
                		System.out.println("Nice playing blackjack with you!");
                		System.exit(0);
            	}
            }
            
            // dealer's turn. Dealer always hits until hand is 17 or over, or busts
            System.out.println("Dealer has " + dealer.toString());
            Thread.sleep(pause);
            boolean dealerPlaying = true;
            while (dealerPlaying) {
            	if (dealer.getPoints() < 17) {
                	System.out.println("Hitting. Drawing a card...");
                    Thread.sleep(pause);
                	Card drawnCard = deck.drawCard();
                	dealer.addToHand(drawnCard);
                	System.out.println("Dealer drew " + drawnCard.toString());
                    Thread.sleep(pause);
                    System.out.println("Dealer has " + dealer.toString());
                    Thread.sleep(pause);
                } else {
                	if (dealer.isBust()) {
                		System.out.println("Dealer busts. You win!");
                        Thread.sleep(pause);
                    	if (askPlayAgain()) {
                    		dealerPlaying = false;
                    		continue;  
                    	} else {
                        		System.out.println("Nice playing blackjack with you!");
                        		System.exit(0);
                    	}
                	} else {
                		System.out.println("Dealer stays.");
                        Thread.sleep(pause);
                	}
                	break;
                }
            }
            if (!dealerPlaying) {
            	continue;
            }
            int playerPoints = player.getPoints();
            int dealerPoints = dealer.getPoints();
            String finalScore = "You: " + Integer.toString(playerPoints) +  ", Dealer: " +
            		Integer.toString(dealerPoints);
            		          
            if (playerPoints > dealerPoints) {
            	System.out.println("You win! " + finalScore);
            } else if (playerPoints < dealerPoints) {
            	System.out.println("Dealer wins! " + finalScore);
            } else {
            	System.out.println("You tie! " + finalScore);
            }
            Thread.sleep(pause);
            
        	if (askPlayAgain()) {
        		continue;  
        	} else {
            		System.out.println("Nice playing blackjack with you!");
            		System.exit(0);
        	}        
        }
    }
    
    private static boolean askPlayAgain() {
    	System.out.println("Hit [0] if you'd like to play again or [1] if you'd like to exit");
        while (true) {
            input = scanner.nextLine();
            if (input.equals("0")) {
                return true;
            } else if (input.equals("1")) {
            	return false;
            } else {
            	System.out.println("Please enter either a 0 if you want to play again or a 1 if"
            			+ " you want to exit");
            }
        }
    }

}
