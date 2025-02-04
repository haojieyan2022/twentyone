package card.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    Player player = new Player(PlayerType.USER, "Derek");
    @Test
    void hasWon() {
        player.setWinner(true);
        assertTrue(player.hasWon());
    }

    @Test
    void hasWonNot() {
        player.setWinner(false);
        assertFalse(player.hasWon());
    }

    @Test
    void hasHand() {
        Hand hand = new Hand("S3,S4,D5");
        player.addHand(hand);
        assertTrue(player.hasHand());
    }


    @Test
    void getPlayerType() {
        assertEquals(PlayerType.USER, player.getCompetitorType());
    }

    @Test
    void getName() {
        assertEquals("Derek", player.getName());
    }

    @Test
    void getHand() {
        Hand hand = new Hand("S3,S4,D5");
        player.addHand(hand);
        assertEquals(hand, player.getHand());
    }
}