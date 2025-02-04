package card.game.twenty_one;
import java.util.Random;

import card.entity.Player;
import card.entity.PlayerType;

public class TwentyOneCompetitor extends Player{

    protected int levelOfRisk;

    public TwentyOneCompetitor(PlayerType competitorType, String name){
        super(competitorType, name);
    }
    public TwentyOneCompetitor(PlayerType competitorType, String name, int levelOfRisk){
        super(competitorType, name);
        int risk;
        Random rand = new Random();
        if (levelOfRisk == 0){
            risk = 11 + rand.nextInt() % 8;
        } else {
            risk = levelOfRisk;
        }
        this.levelOfRisk = risk;
    }

    public int getLevelOfRisk() {
        return levelOfRisk;
    }   
}