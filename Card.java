public class Card {	
    private final Value value;
    private final Suit suit;

    public Card(Value value, Suit suit) {
        this.value = value;
        this.suit = suit;
    }

	public Value getValue() {
        return value;
    }
    
    public int getPoints() {
        int points;
        switch (value) {
            case TWO: 
                points = 2;
                break;
            case THREE: 
                points = 3;
                break;
            case FOUR:
                points = 4;
                break;
            case FIVE: 
                points = 5;
                break;
            case SIX: 
                points = 6;
                break;   
            case SEVEN: 
                points = 7;
                break; 
            case EIGHT: 
                points = 8;
                break; 
            case NINE: 
                points = 9;
                break; 
            case ACE: 
                points = 11;
                break; 
            default:
                points = 10;
                break;
        }
        return points;
    }

    public String toString() {
        return value.toString() + " of " + suit.toString();
    }
}
