import java.util.LinkedList;

public class Player {
    private LinkedList<Card> hand;
    private int points;
    private int numAces;
    private int oneAces;
    private String handAsString;

    public Player() {
        hand = new LinkedList<Card>();
        points = 0;
        numAces = 0;
        oneAces = 0;
        handAsString = "";
    }

    public void resetPlayer() {
        hand = new LinkedList<Card>();
        points = 0;
        numAces = 0;
        oneAces = 0;
        handAsString = "";
    }

    public int getPoints() {
        return points;
    }

    public boolean isBust() {
        return points > 21;
    }
    
    public boolean hasBlackjack() {
        if (hand.size() != 2) {
            return false;
        }
        Value firstCardValue = hand.peekFirst().getValue();
        Value lastCardValue = hand.peekLast().getValue();
        boolean containsAce = (firstCardValue.equals(Value.ACE) ||
                lastCardValue.equals(Value.ACE));
        boolean containsFaceCard = (firstCardValue.equals(Value.TEN) ||
                lastCardValue.equals(Value.TEN) || firstCardValue.equals(Value.JACK) ||
                lastCardValue.equals(Value.JACK) || firstCardValue.equals(Value.QUEEN) ||
                lastCardValue.equals(Value.QUEEN) || firstCardValue.equals(Value.KING) ||
                lastCardValue.equals(Value.KING));
        return containsAce && containsFaceCard;
    }

    public LinkedList<Card> getHand() {
        return hand;
    }

    public void addToHand(Card card) {
        hand.add(card);
        points += card.getPoints();
        handAsString += (" " + card.toString());
        
        if (card.getValue().equals(Value.ACE)) {
            numAces++;
        }
        // change ace value from 11 to 1 if hand value is over 21
        if (points > 21 && numAces > 0 && oneAces < numAces) {
            oneAces++;
            points -= 10;
        }
    }
    
    public String toString() {
        String output = Integer.toString(getPoints()) + ":";
        output += handAsString;
        return output;
    }
}
