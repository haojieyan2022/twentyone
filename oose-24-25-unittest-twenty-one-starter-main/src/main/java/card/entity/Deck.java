package card.entity;

import java.util.Collections;

public class Deck extends Hand{

    private Boolean override = false;

    public Deck(String deckOverride){
        super(deckOverride);
        if (deckOverride == ""){
            generateDeck();
            shuffleDeck();
        } else {
            this.override = true;
        }
    }
    
    public Deck(){
        this("");
    }

    public void setHandOfCards(Hand hand){
        this.handOfCards = hand.getHandOfCards();
    }

    public void generateDeck() {
        this.clear();
        for (Suit suit: Suit.values()){
            for (FaceCard rank: FaceCard.values()){
                Card card = Card.getInstance(suit,rank);
                this.add(card);
            }
        }
    }

    public void shuffleDeck(){
        if (! override) {
            Collections.shuffle(this.handOfCards);
        }
    }

    public Card playACard(){
        if (size() == 0){
            generateDeck();
            shuffleDeck();
        }
        return super.playACard();
    }

    public static void main(String[ ] args) {
        Deck deck = new Deck();
        System.out.println(deck.toString());
    }
}


