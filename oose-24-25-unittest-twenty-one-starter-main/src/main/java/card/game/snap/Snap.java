package card.game.snap;

import communication.WriteInOut;
import communication.YesOrNo;
import card.CardGame;
import card.entity.Hand;
import card.entity.Player;
import card.entity.WriteDeck;

public class Snap extends CardGame{

    public final static String wantToSnap = "Do you want to Snap?";
    public final static String discardPileMessage = "Discard Pile";
    private final static int roundsToWin = 2;
    private final static int numberOfPlayers = 2;
    protected static int noOfCards = 0;

    public Snap(String override){
        super(override);
        setNO_OF_CARDS(noOfCards);
    }

    public Snap(){
        this("");
    }

    protected int getNumberOfPlayers(){
        return numberOfPlayers;
    }

    protected void hasSnapped(YesOrNo isSnap, Hand discardPile){
            if (isSnap == YesOrNo.YES && discardPile.getLastCard().getFaceCard() == discardPile.getSecondLastCard().getFaceCard()){
                getPlayer(USER_INDEX).incrementScore(1);
            } else if (isSnap == YesOrNo.YES) {
                getPlayer(USER_INDEX).incrementScore(-1);
            }
    }

    public void playerPlaysHand(Player player) {
        discardPile.add(player.getHand().playACard());
        displayCard(discardPileMessage, discardPile.getLastCard());
        YesOrNo isSnap = inOut.getYesOrNo(wantToSnap);
        hasSnapped(isSnap, discardPile);
        print("You have scored " + getUser().getScore());
        if (getUser().getScore() >= roundsToWin){
            getUser().setWinner(true);
            setFinishGame(true);    
        }
    }

    protected int getScore(Player player) {
        return player.getScore();
    }

    protected void beforeInitiate(){
        discardPile.add(playACard());
        displayCard(discardPileMessage, discardPile.getLastCard());
    }

    public static void main(String[] args) {
        String override = "S3,H3,D3,C3";
        Snap snap = new Snap(override);
        snap.setDeck(new WriteDeck(override, snap.getClassName()));
        snap.setInOut(new WriteInOut(snap.getClassName()));
        snap.play();
    }
}
