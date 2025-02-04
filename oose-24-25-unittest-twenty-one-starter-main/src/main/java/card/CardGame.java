package card;

import card.entity.Card;
import card.entity.PlayerType;
import card.entity.Deck;
import card.entity.Hand;
import card.entity.Player;

public class CardGame extends Game {

    protected static int NO_OF_CARDS = 2;
    protected static String ASK_CARD_TO_PLAY = "Select card by number starting with zero";
    protected static String DEALER_NAME = "Dealer";
    protected static String ASK_DISCARD_PILE = "Discarded:";
    protected static String COMPUTER_PLAYER_PREFIX = "Comp - ";
    
    protected Hand discardPile = new Hand();
    protected Deck deck;

    public CardGame() {
        this("");
    }

    public CardGame(String override) {
        this.deck = new Deck(override);
    }

    public void setDeck(Deck deck){
        this.deck = deck;
    }

    public void setNO_OF_CARDS(int noOfCards) {
        CardGame.NO_OF_CARDS = noOfCards;
    }

    public Deck getDeck() {
        return deck;
    }

    public void displayCard(String name, Card card){
        inOut.displayCard(name, card);
    }

    public void displayHand(String name, Hand hand){
        inOut.displayHand(name, hand);
    }

    protected void displayDiscardPile(){
        if (discardPile.size() > 0){
            displayCard(ASK_DISCARD_PILE, getLastDiscardedCard());
        } else {
            print(ASK_DISCARD_PILE + " Empty");
        }
    }

    public void createDealer(String name){
        createPlayer(PlayerType.DEALER, name);
    }

    public Card getPlayersCard(Player player){
        return inOut.getPlayersCard(player);
    }

    public void initiatePlayers(int numberOfPlayers, String name) {
        super.initiatePlayers(numberOfPlayers -1, name);
        createDealer(getNextComputerName(computerNames));
    }

    protected int getCardToPlay(){
        return getInputInteger(ASK_CARD_TO_PLAY);
    }

    protected int scoreHand(Hand hand){
        int score = 0;
        return score;
    }

    protected int getScore(Player player) {
        int score = 0;
        for (int counter = 0; counter < player.getNumberOfHands(); counter++) {
            score += scoreHand(player.getHand(counter));
        }
        return score;
    }

    protected void userPlays(Player competitor){
    }
    
    protected void computerPlays(Player competitor){

    }

    public void setDiscardPile(Hand hand){
        this.discardPile = hand;
    }

    public Hand getDiscardPile(){
        return discardPile;
    }

    @Override
    protected void afterPlayOfRound() {
        // TODO Auto-generated method stub
        
    }

    @Override
    protected void beforePlayOfRound() {
        // TODO Auto-generated method stub
        
    }

    protected void moveCardsExceptLast(Hand toHand, Hand fromHand){
        while (fromHand.size() > 1){
            toHand.add(fromHand.playACard());
        }

    }

    protected Card playACard(Player player, Hand hand){
        print(hand.toString());
        Card userCard = null;
        String userCardRaw = getInputString("Please enter a card to play.");
        if (userCardRaw != ""){
            userCard = hand.findACard(userCardRaw);
        }
        if (userCard == null) {
            if (userCardRaw.length() >= 2) {
                print("Card not found in your hand");
            }
        } else {
            boolean cardPlayed = player.getHand().playACard(userCard);
        }
        return userCard;
    }

    protected Card playACard(){
        if (deck.size() == 0 && discardPile.size() > 0){
            moveCardsExceptLast(deck, discardPile);
        }
        return deck.playACard();
    }

    public void dealCards() {
        boolean allCards = false;
        int noOfCards;
        Player player = null;
        if (CardGame.NO_OF_CARDS == 0) {
            noOfCards = (int) Math.floor(deck.size() / getPlayersSize());
            allCards = true;
        } else {
            noOfCards = CardGame.NO_OF_CARDS;
        }
        for (int counter = 0; counter< getPlayersSize(); counter++ ) {
            player = getPlayer(counter);
            player.addHand(dealHand(noOfCards));
        }
        if (allCards) {
            for (int counter = 0; counter< getPlayersSize(); counter++ ) {
                player = getPlayer(counter);
                if (deck.size() > 0) {
                    player.getHand().add(deck.playACard());
                }
            }
        }
    }

    protected Hand dealHand(int noOfCardsToDeal, boolean visibility){
        Hand hand = new Hand(visibility);
        for (int cardCounter = 0; cardCounter < noOfCardsToDeal; cardCounter++) {
            if (deck.size() > 0) {
                hand.add(deck.playACard());
            }
        }
        return hand;
    }

    protected Hand dealHand(int noOfCardsToDeal) {
        return dealHand(noOfCardsToDeal, true);
    }

    public Card playerSelectCard(Player player, Deck deck){
        displayCard("Discard Pile", getLastDiscardedCard());
        displayPlayerWithVisibility(player);
        CardSelectHelp userChoice = inOut.getEnumIndex(CardSelectHelp.class);
        Card cardSelected = null;
        if (userChoice == CardSelectHelp.DISCARD){
            cardSelected = discardPile.playACard();
        } else {
            cardSelected = deck.playACard();
        } 
        return cardSelected;
    }

    public void addToDiscarded(Card card){
        discardPile.add(card);
    }

    public Card getLastDiscardedCard(){
        return discardPile.getLastCard();
    }

    protected void afterInitiate() {

    }

    protected void beforeInitiate(){

    }

    protected void initiate() {
        beforeInitiate();
        String name = getInputString(ASK_NAME);
        int numberOfPlayers = getNumberOfPlayers();
        initiatePlayers(numberOfPlayers, name);
        dealCards();
        afterInitiate();
    }

    public static void main(String[] args) {
        CardGame cardGame = new CardGame();
        cardGame.play();
    }
}
