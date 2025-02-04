package card;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import card.entity.Hand;
import card.entity.Card;
import card.entity.FaceCard;
import card.entity.Suit;

public class ScoreTrickHand {  

    public List<Hand> getStraightSameSuit(Hand hand) {
        List<Hand> straightHands = new ArrayList<Hand>();
        Hand straightHand = new Hand();
        hand.sortHand();;
        Card previousCard = null;
        for (Card cardNext : hand.getHandOfCards()) {
            if (previousCard != null && previousCard.getSuit() == cardNext.getSuit() &&
            previousCard.getFaceCard().ordinal() == cardNext.getFaceCard().ordinal() -1) {
                if (!straightHand.getHandOfCards().contains(previousCard)){
                    straightHand.add(previousCard);
                }
                straightHand.add(cardNext);
            } else {
                if (straightHand.size() > 0){
                    straightHands.add(straightHand);
                    straightHand = new Hand();
                }
            }
            previousCard = cardNext;
        }
        if (straightHand.size() > 0){
            straightHands.add(straightHand);
        }
        return straightHands;
    }

    public List<Hand> getStraight(Hand hand) {
        List<Hand> straightHands = new ArrayList<Hand>();
        Hand straightHand = new Hand();
        hand.sortHandByFace();
        Card previousCard = null;
        for (Card cardNext : hand.getHandOfCards()) {
            if (previousCard != null &&
            previousCard.getFaceCard().ordinal() == cardNext.getFaceCard().ordinal() -1) {
                if (!straightHand.getHandOfCards().contains(previousCard)){
                    straightHand.add(previousCard);
                }
                straightHand.add(cardNext);
            } else {
                if (straightHand.size() > 0){
                    straightHands.add(straightHand);
                    straightHand = new Hand();
                }
            }
            previousCard = cardNext;
        }
        if (straightHand.size() > 0){
            straightHands.add(straightHand);
        }
        return straightHands;
    }

    public HashMap<FaceCard, Hand> getSameFaceCards(Hand hand) {
        HashMap<FaceCard, Hand> sameFaceCards = new HashMap<FaceCard, Hand>();
        hand.sortHandByFace();;
        Card previousCard = null;
        for (Card cardNext : hand.getHandOfCards()) {
            if (previousCard != null && previousCard.getFaceCard() == cardNext.getFaceCard()) {
                if (!sameFaceCards.containsKey(previousCard.getFaceCard())) {
                    sameFaceCards.put(previousCard.getFaceCard(), new Hand());
                    sameFaceCards.get(previousCard.getFaceCard()).add(previousCard);
                }
                sameFaceCards.get(cardNext.getFaceCard()).add(cardNext);
            }
            previousCard = cardNext;
        }
        return sameFaceCards;
    }

    public boolean sameSuite(Hand hand){
        int counter = 0;
        boolean hasSameSuit = true;
        Suit previousSuit = null;
        while (hasSameSuit && counter < hand.size()){
            if (previousSuit == null){
                previousSuit = hand.getCard(counter).getSuit();
            }
            if (previousSuit != hand.getCard(counter).getSuit()){
                hasSameSuit = false;
            }
            counter ++;
        }
        return hasSameSuit;
    }

}
