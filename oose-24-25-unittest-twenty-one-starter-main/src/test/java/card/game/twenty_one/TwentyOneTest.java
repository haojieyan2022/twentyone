package card.game.twenty_one;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import card.entity.Card;
import card.entity.FaceCard;
import card.entity.Hand;
import card.entity.PlayerType;
import card.entity.Suit;
import card.game.twenty_one.TwentyOne;
import card.entity.Player;



public class TwentyOneTest {
    private TwentyOne twentyOne = new TwentyOne();

    @Test
    void testComputerPlaysLowRisk() {
        Player computer = new Player(PlayerType.COMPUTER, "Bot");
        computer.setScore(10);

        if (computer.getNumberOfHands() == 0) {
            computer.addHand(new Hand());
        }

        Hand hand = computer.getHand();
        hand.add(Card.getInstance(Suit.HEARTS, FaceCard.SIX));

        twentyOne.computerPlays(computer);

        assertTrue(twentyOne.scoreHand(computer.getHand()) >= 10);
    }

    @Test
    void testComputerPlaysMediumRisk() {
        Player computer = new Player(PlayerType.COMPUTER, "Bot");
        computer.setScore(15);

        if (computer.getNumberOfHands() == 0) {
            computer.addHand(new Hand());
        }

        Hand hand = computer.getHand();
        hand.add(Card.getInstance(Suit.HEARTS, FaceCard.SIX));

        twentyOne.computerPlays(computer);

        assertTrue(twentyOne.scoreHand(computer.getHand()) >= 15);
    }

    @Test
    void testComputerPlaysHighRisk() {
        Player computer = new Player(PlayerType.COMPUTER, "Bot");
        computer.setScore(18);

        if (computer.getNumberOfHands() == 0) {
            computer.addHand(new Hand());
        }

        Hand hand = computer.getHand();
        hand.add(Card.getInstance(Suit.HEARTS, FaceCard.SIX));

        twentyOne.computerPlays(computer);

        assertTrue(twentyOne.scoreHand(computer.getHand()) >= 18 );
    }

    @Test
    void testScoreHandWithAce() {
        Hand hand = new Hand();
        hand.add(Card.getInstance(Suit.SPADES, FaceCard.ACE));
        hand.add(Card.getInstance(Suit.HEARTS, FaceCard.NINE));
        assertEquals(20, twentyOne.scoreHand(hand));
    }

    @Test
    void testScoreHandBust() {
        Hand hand = new Hand();
        hand.add(Card.getInstance(Suit.SPADES, FaceCard.KING));
        hand.add(Card.getInstance(Suit.HEARTS, FaceCard.QUEEN));
        hand.add(Card.getInstance(Suit.CLUBS, FaceCard.TEN));
        assertEquals(0, twentyOne.scoreHand(hand)); // Bust condition
    }

    @Test
    void testScoreHandWithAceLow() {
        Hand hand = new Hand();
        hand.add(Card.getInstance(Suit.SPADES, FaceCard.ACE));
        hand.add(Card.getInstance(Suit.HEARTS, FaceCard.NINE));
        hand.add(Card.getInstance(Suit.CLUBS, FaceCard.KING));
        assertEquals(20, twentyOne.scoreHand(hand));
    }
}

