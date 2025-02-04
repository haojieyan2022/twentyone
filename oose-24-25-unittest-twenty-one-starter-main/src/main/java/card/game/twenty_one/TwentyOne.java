package card.game.twenty_one;

import card.CardGame;
import card.entity.Card;
import card.entity.FaceCard;
import card.entity.Hand;
import card.entity.Player;
import communication.WriteInOut;
import card.entity.WriteDeck;

public class TwentyOne extends CardGame {
    public static final int MAX_SCORE = 21;

    public TwentyOne(String override){
        super(override);
    }

    public TwentyOne(){
        this("");
    }

    public TwentyOneAction generateHelp() {
        return inOut.getEnumIndex(TwentyOneAction.class);
    }

    protected TwentyOneAction getPlayerAction(Player player) {

        TwentyOneAction userAction;
        if (player.hasHand()) {
            print(player.getHand().toString());
        }
        userAction = generateHelp();
        print("You choose " + userAction.display());
        return userAction;
    }

    protected void userPlays(Player player) {
        TwentyOneAction userAction = TwentyOneAction.TWIST;
        while (scoreHand(player.getHand()) <= MAX_SCORE && userAction != TwentyOneAction.STICK) {
            userAction = getPlayerAction(player);
            if (userAction == TwentyOneAction.TWIST) {
                player.getHand().add(playACard());
            }
        }
        setFinishGame(true);
    }

    protected void computerPlays(Player player) {
        Hand hand = player.getHand();
        while (scoreHand(hand) <= player.getLevelOfRisk()) {
            hand.add(playACard());
        }
    }

    

    public int scoreHand(Hand hand) {
        int score = 0;
        Boolean hasAnAce = false;
        for (Card card : hand.getHandOfCards()) {
            if (card.getFaceCard() == FaceCard.ACE) {
                hasAnAce = true;
            }
            score += card.getFaceCard().getValue();
        }
        if (score > MAX_SCORE && hasAnAce) {
            score -= FaceCard.ACE.getValue() - 1;
        }
        if (score > MAX_SCORE){
            score = 0;
        }
        return score;
    }

    public static void main(String[] args) {
        String override = "";
        TwentyOne twentyOne = new TwentyOne(override);
        twentyOne.setDeck(new WriteDeck(override, twentyOne.getClassName()));
        twentyOne.setInOut(new WriteInOut(twentyOne.getClassName()));
        twentyOne.play();
    }

}
