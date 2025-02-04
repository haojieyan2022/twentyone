package card.entity;
import java.util.HashMap;
import java.util.Map;

public enum FaceCard {
    TWO("Two"),
    THREE( "Three"),
    FOUR("Four"),
    FIVE( "Five"),
    SIX("Six"),
    SEVEN("Seven"),
    EIGHT("Eight"),
    NINE("Nine"),
    TEN( "Ten"),
    JACK("Jack"),
    QUEEN("Queen"),
    KING("King"),
    ACE("Ace");

    private String camelCase;

    protected static final Map<String, FaceCard> lookup = new HashMap<String, FaceCard>();

    static {
        for (FaceCard rank : FaceCard.values()) {
            lookup.put(rank.shortDisplay(), rank);
        }
    }

    FaceCard(String camelCase){
        this.camelCase = camelCase;
    }

    public int getValue(){
        int value = getRank();
        if (value == FaceCard.ACE.getRank()){
            value = 11;
        } else if (value > 10) {
            return 10;
        }
        return value;
    }
    public int getRank(){
        return ordinal() + 2;
    }

    public int getRankAceLow(){
        int value = getRank();
        if (value == FaceCard.ACE.getRank()){
            value = 1;
        }
        return value;
    }
    
    public String displayCamelCase(){
        return camelCase;
    }

    public String shortDisplay(){
        String display;
        int rank = getRank();
        if (rank <= FaceCard.TEN.getRank()) {
            display = Integer.toString(rank);
        } else {
            display = camelCase.substring(0,1);
        }
        return display;
    }

    public String toString(){
        return shortDisplay();
    }

    public static FaceCard getCardRank(String shortCode){
        return lookup.get(shortCode);
    }
}
