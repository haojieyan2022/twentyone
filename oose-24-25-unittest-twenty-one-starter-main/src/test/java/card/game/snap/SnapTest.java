package card.game.snap;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import card.game.snap.Snap;
import card.entity.Card;
import card.entity.FaceCard;
import card.entity.Hand;
import card.entity.Player;
import card.entity.PlayerType;
import card.entity.Suit;
import communication.YesOrNo;

public class SnapTest {

    Snap snap = new Snap();

    private void initializeSnapWithPlayer() {
        snap = new Snap();
        snap.addPlayer(new Player(PlayerType.USER, "Alice"));
    }

    @Test
    void testDealCards(){
        snap.createComputerCompetitors(4);
        snap.dealCards();
        assertEquals(13, snap.getUser().getHand().size());
    }

    @Test
    void testSnapOverride(){
        Snap snap = new Snap("S3,S4,S5");
        assertEquals(3, snap.getDeck().size());
    }

    @Test
    void testSnapNoOverride(){
        assertEquals(52, snap.getDeck().size());
    }

    @Test
    void testHasSnappedCorrectMatch() {
        initializeSnapWithPlayer(); 
        Hand discardPile = new Hand();
        discardPile.add(Card.getInstance(Suit.HEARTS, FaceCard.KING));
        discardPile.add(Card.getInstance(Suit.HEARTS, FaceCard.KING));

        snap.hasSnapped(YesOrNo.YES, discardPile);
        assertEquals(1, snap.getUser().getScore());
    }

    @Test
    void testHasSnappedIncorrectMatch() {
        initializeSnapWithPlayer();
        Hand discardPile = new Hand();
        discardPile.add(Card.getInstance(Suit.HEARTS, FaceCard.KING));
        discardPile.add(Card.getInstance(Suit.HEARTS, FaceCard.QUEEN));

        snap.hasSnapped(YesOrNo.YES, discardPile);
        assertEquals(-1, snap.getUser().getScore());
    }

    @Test
    void testHasSnappedNoSnap() {
        initializeSnapWithPlayer();
        Hand discardPile = new Hand();
        discardPile.add(Card.getInstance(Suit.HEARTS, FaceCard.KING));
        discardPile.add(Card.getInstance(Suit.HEARTS, FaceCard.KING));

        snap.hasSnapped(YesOrNo.NO, discardPile);
        assertEquals(0, snap.getUser().getScore());
    }

    @Test
    void testPlayerWinsBySnap() {
        snap.addPlayer(new Player(PlayerType.USER, "Alice")); 
        Hand discardPile = new Hand();

        discardPile.add(Card.getInstance(Suit.HEARTS, FaceCard.KING));
        discardPile.add(Card.getInstance(Suit.HEARTS, FaceCard.KING));
        snap.hasSnapped(YesOrNo.YES, discardPile);

        discardPile.add(Card.getInstance(Suit.DIAMONDS, FaceCard.QUEEN));
        discardPile.add(Card.getInstance(Suit.DIAMONDS, FaceCard.QUEEN));
        snap.hasSnapped(YesOrNo.YES, discardPile);

        boolean hasWon = snap.getUser().getScore() >= 2;

        assertEquals(2, snap.getUser().getScore()); 
        assertTrue(hasWon);
    }
}

