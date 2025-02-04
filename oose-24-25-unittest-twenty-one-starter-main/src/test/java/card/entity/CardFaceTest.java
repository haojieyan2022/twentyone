package card.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CardFaceTest {

    @Test
    void getValueAce() { assertEquals(11, FaceCard.ACE.getValue());
    }

    @Test
    void getValueKing() { assertEquals(10, FaceCard.KING.getValue());
    }

    @Test
    void getValueQueen() { assertEquals(10, FaceCard.QUEEN.getValue());
    }

    @Test
    void getValueJack() { assertEquals(10, FaceCard.JACK.getValue());
    }

    @Test
    void getValueTen() { assertEquals(10, FaceCard.TEN.getValue());
    }

    @Test
    void getValueNine() { assertEquals(9, FaceCard.NINE.getValue());
    }

    @Test
    void getRankTen() {
        assertEquals(10, FaceCard.TEN.getRank());
    }

    @Test
    void getRankACe() {
        assertEquals(14, FaceCard.ACE.getRank());
    }

    @Test
    void displayCamelCase() {assertEquals("Three", FaceCard.THREE.displayCamelCase());
    }

    @Test
    void shortDisplayTen() {
        assertEquals("10", FaceCard.TEN.shortDisplay());
    }

    @Test
    void testToStringThree() { assertEquals("3", FaceCard.THREE.toString());
    }

    @Test
    void testCardRankTwo(){
        assertEquals(FaceCard.TWO, FaceCard.getCardRank("2"));
    }
    @Test
    void testCardRankTen(){
        assertEquals(FaceCard.TEN, FaceCard.getCardRank("10"));
    }
    @Test
    void testCardRankJack(){
        assertEquals(FaceCard.JACK, FaceCard.getCardRank("J"));
    }
    @Test
    void testCardRankQueen(){
        assertEquals(FaceCard.QUEEN, FaceCard.getCardRank("Q"));
    }
    @Test
    void testCardRankKing(){
        assertEquals(FaceCard.KING, FaceCard.getCardRank("K"));
    }
    @Test
    void testCardRankAce(){
        assertEquals(FaceCard.ACE, FaceCard.getCardRank("A"));
    }
}