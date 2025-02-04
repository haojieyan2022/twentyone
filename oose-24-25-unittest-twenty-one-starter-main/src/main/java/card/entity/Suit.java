package card.entity;
import java.util.HashMap;
import java.util.Map;

public enum Suit{
    CLUBS( "Clubs"),
    DIAMONDS("Diamonds"),
    HEARTS("Hearts"),
    SPADES( "Spades");

    private String camelCase;
    private static final Map<String,Suit> lookup = new HashMap<String,Suit>();
    public static final int noOfCardsInSuite = 13;

    static {
        for (Suit suit : Suit.values()) {
            lookup.put(suit.camelCase.substring(0,1),suit);
        }
    }

    Suit(String camelCase){
        this.camelCase = camelCase;
    }

    public String displayCamelCase(){
        return camelCase;
    }

    public String displayFirstLetter() { return this.camelCase.substring(0,1);}

    public String toString(){
        return this.displayFirstLetter();
    }

    public static Suit getSuit(String shortCode){
        return lookup.get(shortCode);
    }

}
