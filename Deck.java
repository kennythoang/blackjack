import java.util.Collections;
import java.util.LinkedList;

public class Deck {
    private LinkedList<Card> deck;

    public Deck() {
        deck = new LinkedList<Card>();
        for (Value value : Value.values()) { 
            for (Suit suit : Suit.values()) {
                deck.add(new Card(value, suit));
            }
        }
        Collections.shuffle(deck);
    }

    public Card drawCard() {
        return deck.poll();
    }

    public void resetDeck() {
        deck = new LinkedList<Card>();
        for (Value value : Value.values()) { 
            for (Suit suit : Suit.values()) {
                deck.add(new Card(value, suit));
            }
        }
        Collections.shuffle(deck);
    }
}
