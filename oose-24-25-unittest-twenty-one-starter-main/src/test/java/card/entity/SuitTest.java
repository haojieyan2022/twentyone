package card.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SuitTest {

    @Test
    void displayCamelCaseHearts() {
        assertEquals("Hearts", Suit.HEARTS.displayCamelCase());
    }

    @Test
    void displayCamelCaseDiamonds() {
        assertEquals("Diamonds", Suit.DIAMONDS.displayCamelCase());
    }

    @Test
    void displayCamelCaseClubs() {
        assertEquals("Clubs", Suit.CLUBS.displayCamelCase());
    }

    @Test
    void displayCamelCaseSpades() {
        assertEquals("Spades", Suit.SPADES.displayCamelCase());
    }

    @Test
    void displayFirstLetterHearts() {
        assertEquals("H", Suit.HEARTS.displayFirstLetter());
    }

    @Test
    void displayFirstLetterDiamonds() {
        assertEquals("D", Suit.DIAMONDS.displayFirstLetter());
    }

    @Test
    void displayFirstLetterClubs() {
        assertEquals("C", Suit.CLUBS.displayFirstLetter());
    }

    @Test
    void displayFirstLetterSpades() {
        assertEquals("S", Suit.SPADES.displayFirstLetter());
    }

    @Test
    void testToString() {
        assertEquals("S", Suit.SPADES.toString());
    }

    @Test
    void getSuitHearts() {
        assertEquals(Suit.HEARTS,Suit.getSuit("H"));
    }

    @Test
    void getSuitDiamonds() {
        assertEquals(Suit.DIAMONDS,Suit.getSuit("D"));
    }

    @Test
    void getSuitSpades() {
        assertEquals(Suit.SPADES,Suit.getSuit("S"));
    }

    @Test
    void getSuitClubs() {
        assertEquals(Suit.CLUBS,Suit.getSuit("C"));
    }

    @Test
    void getSuitOrdinalHearts() {
        assertEquals(2 , Suit.HEARTS.ordinal());
    }

    @Test
    void getSuitOrdinalDiamond() {
        assertEquals(1 , Suit.DIAMONDS.ordinal());
    }

    @Test
    void getSuitOrdinalSpades() {
        assertEquals(3 , Suit.SPADES.ordinal());
    }

    @Test
    void getSuitOrdinalClubs() {
        assertEquals(0 , Suit.CLUBS.ordinal());
    }

}