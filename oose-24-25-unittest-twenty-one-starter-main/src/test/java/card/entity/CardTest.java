package card.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CardTest {

    Card clubFour = Card.getInstance("C4");

    @Test
    void getInstanceShortCode() {
        Card newClubFour = Card.getInstance("C4");
        assertEquals(clubFour, newClubFour);
    }

    @Test
    void testGetInstanceSuitFace() {
        Card newClubFour = Card.getInstance(Suit.CLUBS, FaceCard.FOUR);
        assertEquals(clubFour, newClubFour);
    }

    @Test
    void getSuit() {
        assertEquals(Suit.CLUBS, clubFour.getSuit());
    }

    @Test
    void getCardFace() {
        assertEquals(FaceCard.FOUR, clubFour.getFaceCard());
    }

    @Test
    void displayCamelCase() {
        assertEquals("Clubs Four", clubFour.displayCamelCase());
    }

    @Test
    void displayOf() {
        assertEquals("Four of Clubs", clubFour.displayOf());
    }

    @Test
    void testToString() {
        assertEquals("C4", clubFour.toString());
    }
}