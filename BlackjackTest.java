import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class BlackjackTest {
    Deck deck;
    Player player;
    
    @Before
    public void setUp() {
    	deck = new Deck();
    	player = new Player();
    }
    
    @Test
    public void testDeck() {
    	assertEquals(52, deck.getSize());
    	deck.drawCard();
    	assertEquals(51, deck.getSize());
    	deck.drawCard();
    	assertEquals(50, deck.getSize());
    	deck.resetDeck();
    	assertEquals(52, deck.getSize());
    }
    
    @Test
    public void testCard() {
    	Card aceOfDiamonds = new Card(Value.ACE, Suit.DIAMONDS);
    	assertEquals(Value.ACE, aceOfDiamonds.getValue());
    	assertEquals("ACE of DIAMONDS", aceOfDiamonds.toString());
    }
    
    @Test
    public void testPlayerTwoCards() {
    	player.addToHand(new Card(Value.EIGHT, Suit.CLUBS));
    	player.addToHand(new Card(Value.EIGHT, Suit.DIAMONDS));
    	assertEquals(16, player.getPoints());
    	assertFalse(player.isBust());
    	assertFalse(player.hasBlackjack());
    	assertEquals(2, player.getHand().size());
    	assertEquals(0, player.getNumAces());
    	assertEquals(0, player.getOneAces());
    	player.resetPlayer();
    	assertEquals(0, player.getPoints());
    	assertFalse(player.isBust());
    	assertFalse(player.hasBlackjack());
    	assertEquals(0, player.getHand().size());
    	assertEquals(0, player.getNumAces());
    	assertEquals(0, player.getOneAces());
    }
    
    @Test
    public void testPlayerBlackjack() {
    	player.addToHand(new Card(Value.TEN, Suit.CLUBS));
    	player.addToHand(new Card(Value.ACE, Suit.DIAMONDS));
    	assertEquals(21, player.getPoints());
    	assertFalse(player.isBust());
    	assertTrue(player.hasBlackjack());
    	assertEquals(2, player.getHand().size());
    	assertEquals(1, player.getNumAces());
    	assertEquals(0, player.getOneAces());
    }
    
    @Test
    public void testPlayer21NoBlackjack() {
    	player.addToHand(new Card(Value.TEN, Suit.CLUBS));
    	player.addToHand(new Card(Value.FIVE, Suit.DIAMONDS));
    	player.addToHand(new Card(Value.SIX, Suit.DIAMONDS));

    	assertEquals(21, player.getPoints());
    	assertFalse(player.isBust());
    	assertFalse(player.hasBlackjack());
    	assertEquals(3, player.getHand().size());
    	assertEquals(0, player.getNumAces());
    	assertEquals(0, player.getOneAces());
    }
    
    @Test
    public void testPlayerBust() {
    	player.addToHand(new Card(Value.TEN, Suit.CLUBS));
    	player.addToHand(new Card(Value.FIVE, Suit.DIAMONDS));
    	player.addToHand(new Card(Value.SEVEN, Suit.DIAMONDS));

    	assertEquals(22, player.getPoints());
    	assertTrue(player.isBust());
    	assertFalse(player.hasBlackjack());
    	assertEquals(3, player.getHand().size());
    	assertEquals(0, player.getNumAces());
    	assertEquals(0, player.getOneAces());
    }
    
    @Test
    public void testPlayerDrawTwoAces() {
    	player.addToHand(new Card(Value.ACE, Suit.CLUBS));
    	player.addToHand(new Card(Value.ACE, Suit.DIAMONDS));

    	assertEquals(12, player.getPoints());
    	assertFalse(player.isBust());
    	assertFalse(player.hasBlackjack());
    	assertEquals(2, player.getHand().size());
    	assertEquals(2, player.getNumAces());
    	assertEquals(1, player.getOneAces());
    }
    
    @Test
    public void testPlayerDrawAceFirstThenChange() {
    	player.addToHand(new Card(Value.ACE, Suit.CLUBS));
    	player.addToHand(new Card(Value.FIVE, Suit.DIAMONDS));
    	
    	assertEquals(16, player.getPoints());
    	assertEquals(1, player.getNumAces());
    	assertEquals(0, player.getOneAces());
    	
    	player.addToHand(new Card(Value.EIGHT, Suit.DIAMONDS));

    	assertEquals(14, player.getPoints());
    	assertFalse(player.isBust());
    	assertFalse(player.hasBlackjack());
    	assertEquals(3, player.getHand().size());
    	assertEquals(1, player.getNumAces());
    	assertEquals(1, player.getOneAces());
    }
    
    @Test
    public void testPlayerDrawAceFirstThenNoChange() {
    	player.addToHand(new Card(Value.ACE, Suit.CLUBS));
    	player.addToHand(new Card(Value.SIX, Suit.DIAMONDS));

    	assertEquals(17, player.getPoints());
    	assertFalse(player.isBust());
    	assertFalse(player.hasBlackjack());
    	assertEquals(2, player.getHand().size());
    	assertEquals(1, player.getNumAces());
    	assertEquals(0, player.getOneAces());
    }
    
    @Test
    public void testPlayerDrawAceLaterThenChange() {
    	player.addToHand(new Card(Value.FIVE, Suit.CLUBS));
    	player.addToHand(new Card(Value.EIGHT, Suit.CLUBS));
    	
    	assertEquals(13, player.getPoints());
    	
    	player.addToHand(new Card(Value.ACE, Suit.DIAMONDS));

    	assertEquals(14, player.getPoints());
    	assertFalse(player.isBust());
    	assertFalse(player.hasBlackjack());
    	assertEquals(3, player.getHand().size());
    	assertEquals(1, player.getNumAces());
    	assertEquals(1, player.getOneAces());
    }
    
    @Test
    public void testPlayerDrawAceLaterThenNoChange() {
    	player.addToHand(new Card(Value.FIVE, Suit.CLUBS));
    	player.addToHand(new Card(Value.FOUR, Suit.CLUBS));
    	
    	assertEquals(9, player.getPoints());    	
    	player.addToHand(new Card(Value.ACE, Suit.DIAMONDS));

    	assertEquals(20, player.getPoints());
    	assertFalse(player.isBust());
    	assertFalse(player.hasBlackjack());
    	assertEquals(3, player.getHand().size());
    	assertEquals(1, player.getNumAces());
    	assertEquals(0, player.getOneAces());
    }
    
    @Test
    public void testPlayerDrawFaceAndAceNoBlackjack() {
    	player.addToHand(new Card(Value.FIVE, Suit.CLUBS));
    	player.addToHand(new Card(Value.KING, Suit.CLUBS));
    	
    	assertEquals(15, player.getPoints());    	
    	player.addToHand(new Card(Value.ACE, Suit.DIAMONDS));

    	assertEquals(16, player.getPoints());
    	assertFalse(player.isBust());
    	assertFalse(player.hasBlackjack());
    	assertEquals(3, player.getHand().size());
    	assertEquals(1, player.getNumAces());
    	assertEquals(1, player.getOneAces());
    }
    
    @Test
    public void testPlayerMultipleAces() {
    	player.addToHand(new Card(Value.THREE, Suit.CLUBS));
    	player.addToHand(new Card(Value.ACE, Suit.CLUBS));

    	assertEquals(14, player.getPoints());
    	assertFalse(player.isBust());
    	assertFalse(player.hasBlackjack());
    	assertEquals(2, player.getHand().size());
    	assertEquals(1, player.getNumAces());
    	assertEquals(0, player.getOneAces());
    	
    	player.addToHand(new Card(Value.ACE, Suit.DIAMONDS));
    	assertEquals(15, player.getPoints());
    	assertFalse(player.isBust());
    	assertFalse(player.hasBlackjack());
    	assertEquals(3, player.getHand().size());
    	assertEquals(2, player.getNumAces());
    	assertEquals(1, player.getOneAces());
    	
    	player.addToHand(new Card(Value.ACE, Suit.HEARTS));
    	assertEquals(16, player.getPoints());
    	assertFalse(player.isBust());
    	assertFalse(player.hasBlackjack());
    	assertEquals(4, player.getHand().size());
    	assertEquals(3, player.getNumAces());
    	assertEquals(2, player.getOneAces());
    	
    	player.addToHand(new Card(Value.ACE, Suit.SPADES));
    	assertEquals(17, player.getPoints());
    	assertFalse(player.isBust());
    	assertFalse(player.hasBlackjack());
    	assertEquals(5, player.getHand().size());
    	assertEquals(4, player.getNumAces());
    	assertEquals(3, player.getOneAces());
    	
    	player.addToHand(new Card(Value.KING, Suit.SPADES));
    	assertEquals(17, player.getPoints());
    	assertFalse(player.isBust());
    	assertFalse(player.hasBlackjack());
    	assertEquals(6, player.getHand().size());
    	assertEquals(4, player.getNumAces());
    	assertEquals(4, player.getOneAces());
    	
    	player.addToHand(new Card(Value.FOUR, Suit.SPADES));
    	assertEquals(21, player.getPoints());
    	assertFalse(player.isBust());
    	assertFalse(player.hasBlackjack());
    	assertEquals(7, player.getHand().size());
    	assertEquals(4, player.getNumAces());
    	assertEquals(4, player.getOneAces());
    	
    	player.addToHand(new Card(Value.TWO, Suit.SPADES));
    	assertEquals(23, player.getPoints());
    	assertTrue(player.isBust());
    	assertFalse(player.hasBlackjack());
    	assertEquals(8, player.getHand().size());
    	assertEquals(4, player.getNumAces());
    	assertEquals(4, player.getOneAces());
    }
    
    @Test
    public void testPlayerToString() {
    	player.addToHand(new Card(Value.TEN, Suit.CLUBS));
    	player.addToHand(new Card(Value.ACE, Suit.DIAMONDS));
    	assertEquals("21: TEN of CLUBS ACE of DIAMONDS", player.toString());
    	player.addToHand(new Card(Value.FOUR, Suit.HEARTS));
    	assertEquals("15: TEN of CLUBS ACE of DIAMONDS FOUR of HEARTS", player.toString());
    }
}
