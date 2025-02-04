package card.game.twenty_one;
import java.util.HashMap;
import java.util.Map;

public enum TwentyOneAction {
    TWIST("Twist"),
    STICK("Stick");

    private String fullName;
    private static final Map<String, TwentyOneAction> lookup = new HashMap<String, TwentyOneAction>();

    TwentyOneAction(String fullName) {
        this.fullName = fullName;
    }

    static {
        for (TwentyOneAction action : TwentyOneAction.values()) {
            lookup.put(action.fullName.substring(0, 1), action);
        }
    }

    public String display() {
        return fullName;
    }

    public static TwentyOneAction getAction(String shortCode) {
        return lookup.get(shortCode);
    }

}
