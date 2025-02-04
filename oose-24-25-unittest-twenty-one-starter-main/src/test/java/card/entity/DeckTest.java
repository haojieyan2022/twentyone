package card.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DeckTest {

    Deck deck = new Deck();

    @Test
    void generateDeck() {
        assertEquals(52, deck.size());
    }

    @Test
    void playACard() {
        Deck deck = new Deck("C5,C6,CA");
        assertEquals("CA",deck.playACard().toString());
    }

}