package card.entity;
import java.util.List;
import java.util.ArrayList;

public class Player {

    protected static final int levelOfRisk = 14;
    protected List<Hand> hands = new ArrayList<Hand>();
    private PlayerType competitorType;
    private String firstName;
    protected boolean winner = false;
    private int score = 0;
    private double amount = 0;

    public Player(PlayerType competitorType, String firstName){
        this.competitorType = competitorType;
        this.firstName = firstName;
        this.winner = false;
    }

    public double getAmount(){
        return amount;
    }

    public void incrementAmount(double amount){
        this.amount += amount;
    }

    public void decementAmount(double amount){
        this.amount -= amount;
    }

    public void incrementScore(int score){
        this.score += score;
    }

    public int getLevelOfRisk(){
        return levelOfRisk;
    }

    public int getScore(){
        return score;
    }

    public void setScore(int score){
        this.score = score;
    }

    public void setWinner(boolean status){
        this.winner = status;
    }

    public boolean hasWon(){
        return winner;
    }

    public Card getCard(int index){
        return hands.get(0).getCard(index);
    }

    public boolean hasHand(){
        return hands.size() >0;
    }

    public PlayerType getCompetitorType(){
        return competitorType;
    }

    public void addCard(Card card){
        hands.get(0).add(card);
    }

    public void addCard(Card card, boolean show){}

    public void addHand(Hand hand){
        this.hands.add(hand);
    }

    public String getName() { return this.firstName;}

    public Hand getHand(){
        return getHand(0);
    }

    public int getNumberOfHands(){
        return hands.size();
    }

    public Hand getHand(int index){
        return this.hands.get(index);
    }

    public String displayHand(){
        return hands.get(0).toString();
    }

    public String displayHandWithVisibility(){
        String display = baseToString();
        int counter = 0;
        for (Hand hand : hands){
            display += " " + hand.displayVisibility(counter);
            counter += hand.size();
        }
        return display.trim();
    }

    public Card playACard(int index){
        return getHand().playACard(index);
    }

    protected String baseToString(){
        String display = "";
        if (winner){
            display = "The winner is ";
        }
        display += getName();
        if (getScore() > 0 ){
            display += " with score: " + getScore(); 
        }
        if (getAmount() > 0 ){
            display += " with amount: " + getAmount(); 
        }
        return display;
    }

    public String toString(){
        String display = baseToString();
        for (Hand hand : hands){
            display += " + " + hand.toString();
        }
        return display;
    }

}
