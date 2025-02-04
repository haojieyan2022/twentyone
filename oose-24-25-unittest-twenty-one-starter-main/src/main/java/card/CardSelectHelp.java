package card;

import communication.SelectFromList;

public enum CardSelectHelp implements SelectFromList {
    DISCARD("Pick from discarded pile"),
    DECK("Pick from Deck");

    private String description;
    
    CardSelectHelp(String description) {
        this.description = description;
    }    
    
}
