package card.entity;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Collections;
import java.util.Comparator;

public class Hand {

    protected List<Card> handOfCards = new ArrayList<Card>();;
    protected HashMap<Card, Boolean> handOfVisibileCards = new HashMap<Card, Boolean>();
    protected boolean visible = true;

    public Hand() {
    }

    public Hand(String[] listOfCards) {
        this(listOfCards, true);
    }

    public Hand(boolean visible) {
        this(new String[0], visible);
    }

    public Hand(String[] listOfCards, boolean visible) {
        this.visible = visible;
        for (String card : listOfCards) {
            if (card.length() > 0) {
                this.add(Card.getInstance(card), true);
            }
        }
    }

    public Hand(String listOfCards) {
        this(listOfCards, true);
    }

    public Hand(String listOfCards, boolean visibility) {
        this(listOfCards.replace(" ", "").split(","), visibility);
    }

    public List<Card> getHandOfCards() {
        return handOfCards;
    }

    public Card getFirstCard() {
        return handOfCards.get(0);
    }

    public Card getLastCard() {
        return handOfCards.get(size() - 1);
    }

    public Card getSecondLastCard(){
        return handOfCards.get(size() - 2);
    }

    public Card findACard(String cardShortCode) {
        Card card = Card.getInstance(cardShortCode);
        if (!hasCard(card)) {
            card = null;
        }
        return card;
    }

    public Boolean playACard(Card card) {
        boolean result = handOfCards.contains(card);
        if (result) {
            remove(card);
        }
        return result;
    }

    public Card playACard() {
        return playACard(size() - 1);
    }

    public Card playACard(int index) {
        return handOfCards.remove(index);
    }

    public void makeCardVisible(int index) {
        Card card = getCard(index);
        handOfVisibileCards.put(card, true);
    }

    public void add(Card card, Boolean visibility) {
        handOfCards.add(card);
        handOfVisibileCards.put(card, visibility);
    }

    public void add(Card card) {
        add(card, visible);
    }

    public void add(int place, Card card) {
        handOfCards.add(place, card);
        handOfVisibileCards.put(card, true);
    }

    public void addAtStart(Card card) {
        add(0, card);
    }

    public void set(int index, Card card) {
        playACard(index);
        add(index, card);
    }

    public Hand copy() {
        Hand newHand = new Hand();
        for (Map.Entry<Card, Boolean> entry : handOfVisibileCards.entrySet()) {
            newHand.add(entry.getKey(), entry.getValue());
        }
        return newHand;
    }

    public boolean isEmpty() {
        boolean result = false;
        if (handOfCards.size() == 0) {
            result = true;
        }
        return result;
    }

    public boolean isNotEmpty(){
        return !isEmpty();
    }

    public void remove(Card card) {
        handOfCards.remove(card);
        handOfVisibileCards.remove(card);
    }

    public void clear() {
        handOfCards.clear();
    }

    public Integer size() {
        return handOfCards.size();
    }

    public void sortHand() {
        Collections.sort(handOfCards, Comparator.comparing(Card::suitFaceOrder));
    }

    public void sortHandByFace() {
        try {
            Collections.sort(handOfCards, Comparator.comparing(Card::getFaceCard));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean hasCard(String codeShortCode) {
        boolean result = false;
        Card card = Card.getInstance(codeShortCode);
        if (card != null) {
            result = hasCard(card);
        }
        return result;
    }

    public boolean hasCard(Card card) {
        return handOfCards.contains(card);
    }

    public boolean hasCards(String cards) {
        String[] seperatedCards = cards.split(",");
        int counter = 0;
        boolean cardFound = true;
        while (cardFound && counter < seperatedCards.length) {
            cardFound = hasCard(seperatedCards[counter]);
            counter++;
        }
        return cardFound;
    }

    public Boolean playACard(String card) {
        return playACard(Card.getInstance(card));
    }

    public boolean playCards(String cards) {
        String[] seperatedCards = cards.split(",");
        int counter = 0;
        boolean cardFound = true;
        while (cardFound && counter < seperatedCards.length) {
            cardFound = playACard(seperatedCards[counter]);
            counter++;
        }
        return cardFound;
    }

    public boolean playCards(List<Card> cards) {
        int counter = 0;
        boolean cardFound = true;
        while (cardFound && counter < cards.size()) {
            cardFound = playACard(cards.get(counter));
            counter++;
        }
        return cardFound;
    }

    public void addCards(String cards) {
        String[] seperatedCards = cards.split(",");
        for (String card : seperatedCards) {
            add(Card.getInstance(card));
        }
    }

    public void addCards(List<Card> cards) {
        for (Card card : cards) {
            add(card);
        }
    }

    public Card highestCardOfASuit(Suit suit) {
        Card highestCard = null;
        for (Card card : getHandOfCards()) {
            if (card.getSuit() == suit) {
                if (highestCard == null || highestCard.getFaceCard().getValue() < card.getFaceCard().getValue()) {
                    highestCard = card;
                }
            }
        }
        return highestCard;
    }

    public Card lowestCardOfASuit(Suit suit) {
        Card highestCard = null;
        for (Card card : getHandOfCards()) {
            if (card.getSuit() == suit) {
                if (highestCard == null || highestCard.getFaceCard().getValue() > card.getFaceCard().getValue()) {
                    highestCard = card;
                }
            }
        }
        return highestCard;
    }

    public Card highestCardExceptSuit(Suit suit) {
        Card highestCard = null;
        for (Card card : getHandOfCards()) {
            if (card.getSuit() != suit) {
                if (highestCard == null || highestCard.getFaceCard().getValue() < card.getFaceCard().getValue()) {
                    highestCard = card;
                }
            }
        }
        return highestCard;
    }

    public Card lowestCardExceptSuit(Suit suit) {
        Card highestCard = null;
        for (Card card : getHandOfCards()) {
            if (card.getSuit() != suit) {
                if (highestCard == null || highestCard.getFaceCard().getValue() > card.getFaceCard().getValue()) {
                    highestCard = card;
                }
            }
        }
        return highestCard;
    }

    public Card getCard(int counter) {
        return handOfCards.get(counter);
    }

    public void mergeWithAHand(Hand hand){
        for (Card card : hand.getHandOfCards()){
            this.add(card);
        }
    }

    public String displayVisibility(int counter) {
        String display = "";
        String prefix = "";
        for (Card card : handOfCards) {
            if (handOfVisibileCards.containsKey(card) && handOfVisibileCards.get(card)) {
                display += prefix + counter + " - " + card;
            } else {
                display += prefix + counter + " - " + "*";
            }
            prefix = ", ";
            counter++;
        }
        return display;
    }

    public String toString() {
        String display = "";
        String prefix = "";
        for (Card card : handOfCards) {
            display += prefix + card;
            prefix = ", ";
        }
        return display;
    }
}
