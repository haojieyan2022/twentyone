package card;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.List;

import card.entity.FaceCard;
import card.entity.Hand;

public class ScoreTrickHandTest {
    
    Hand hand = new Hand("C7,C3,D8,D7,D3");
    ScoreTrickHand scoreTrickHand = new ScoreTrickHand();

    @Test
    void testGetSameFaceCardTwoPairs(){
        HashMap<FaceCard, Hand> sameFaceCard = scoreTrickHand.getSameFaceCards(hand);
        assertEquals(2, sameFaceCard.keySet().size());
    }

    @Test
    void testGetSameFaceCardArePairs(){
        HashMap<FaceCard, Hand> sameFaceCard = scoreTrickHand.getSameFaceCards(hand);
        assertEquals(2, sameFaceCard.get(FaceCard.THREE).size());
    }

    @Test
    void testGetSameFaceCardAreFourOfAKind(){
        Hand hand = new Hand("C7,S7,H7,D7,D3");
        HashMap<FaceCard, Hand> sameFaceCard = scoreTrickHand.getSameFaceCards(hand);
        assertEquals(4, sameFaceCard.get(FaceCard.SEVEN).size());
    }

    @Test
    void testGetSameFaceCardAreTwoOfAKindSeven(){
        Hand hand = new Hand("C2,S3,H7,D7,D3");
        HashMap<FaceCard, Hand> sameFaceCard = scoreTrickHand.getSameFaceCards(hand);
        assertEquals(2, sameFaceCard.get(FaceCard.SEVEN).size());
    }

    @Test
    void testGetSameFaceCardAreOnePair(){
        Hand hand = new Hand("C2,S6,H7,D7,D3");
        HashMap<FaceCard, Hand> sameFaceCard = scoreTrickHand.getSameFaceCards(hand);
        assertEquals(1, sameFaceCard.keySet().size());
    }

    @Test
    void testGetSameFaceCardArTwoOfAKindPairs(){
        Hand hand = new Hand("C2,S3,H7,D7,D3");
        HashMap<FaceCard, Hand> sameFaceCard = scoreTrickHand.getSameFaceCards(hand);
        assertEquals(2, sameFaceCard.keySet().size());
    }

    @Test
    void testGetStraightSameSuitFive(){
        Hand hand = new Hand("C7,C6,C4,C5,C8");
        List<Hand> straightSuit = scoreTrickHand.getStraightSameSuit(hand);
        assertEquals("C4, C5, C6, C7, C8", straightSuit.get(0).toString());
    }
    
    @Test
    void testGetStraightSameSuitTwoHands(){
        Hand hand = new Hand("C7,C6,D4,H5,H6");
        List<Hand> straightSuit = scoreTrickHand.getStraightSameSuit(hand);
        assertEquals(2, straightSuit.size());
    }

    @Test
    void testGetStraightSameSuitTwoHandsToString(){
        Hand hand = new Hand("C7,C6,D4,H5,H6");
        List<Hand> straightSuit = scoreTrickHand.getStraightSameSuit(hand);
        assertEquals("[C6, C7, H5, H6]", straightSuit.toString());
    }

    @Test
    void testGetStraightSameSuitRoyalFlush(){
        assertEquals(1, scoreTrickHand.getStraightSameSuit(new Hand("SA,SK,SQ,SJ,S10")).size());
    }

    @Test
    void testGetSameFaceCards() {
        
    }

    @Test
    void testGetStraightSameSuit() {
        
    }


}
