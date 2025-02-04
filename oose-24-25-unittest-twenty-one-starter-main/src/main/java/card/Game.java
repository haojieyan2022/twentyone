package card;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import communication.ConsoleInOut;
import communication.InOutInterface;
import utility.LoadCSV;
import card.entity.Player;
import card.entity.PlayerType;

public abstract class Game <ExtendedENum extends Enum<ExtendedENum>> {

    protected static int winningScore = 0;
    public final static String ASK_NAME = "What is your name";
    private final static int MINIMUM_PLAYERS = 2;
    private final static int MAXIMUM_PLAYERS = 8;
    public final static String NUMBER_OF_PLAYERS = "How many competitors, minimum of " + MINIMUM_PLAYERS + " and maximum " + MAXIMUM_PLAYERS + "?";
    public final static String PLAYER_NAME_FILE = "src/main/resources/card_player.csv";
    public final static int USER_INDEX = 0;
    protected List<String> computerNames = new ArrayList<String>();
    protected int extraComputerNameCounter = 1;
    
    protected InOutInterface inOut = new ConsoleInOut();
    protected List<Player> players = new ArrayList<Player>();
    protected LoadCSV loadCSV = new LoadCSV();
    protected boolean finishGame = false;
    protected Class<ExtendedENum> helpClass = null;

    public String getClassName(){
        return this.getClass().getName();
    }

    public void setInOut(InOutInterface inOut){
        this.inOut = inOut;
    }

    public void setInOut(InOutInterface inOut, boolean on){
        setInOut(inOut);
        setOutputOn(on);
    }

    public void setOutputOn(boolean on){
        inOut.setOutputOn(on);
    }

    public void setLoadScanner(Scanner scanner, String path){
        loadCSV.setCSVReader(scanner, path);
    }

    public void setConsoleScanner(Scanner scanner){
        inOut.setScanner(scanner);
    }

    protected void setFinishGame(boolean finshGame) {
        this.finishGame = finshGame;
    }

    public Player getPlayer(int index) {
        return players.get(index);
    }

    public Player getUser(){
        return getPlayer(USER_INDEX);
    }

    public List<Player> getPlayers(){
        return players;
    }

    public boolean getFinishGame(){
        return finishGame;
    }

    public int getPlayersSize(){
        return players.size();
    }

    public void clearPlayers(){
        players.clear();
    }

    protected void print(String message) {
        inOut.print(message);
    }

    protected ExtendedENum generateHelp() {
        return inOut.getEnumIndex("", helpClass);
    }

    protected String getInputString(String message) {
        return inOut.getInputString(message);
    }

    protected int getInputInteger(String message) {
        return inOut.getInputInteger(message);
    }

    protected double getInputDouble(String message) {
        return inOut.getInputInteger(message);
    }

    protected void displayPlayer(Player player){
        inOut.displayPlayer(player);
    }

    protected void displayPlayerWithVisibility(Player player){
        inOut.displayPlayerWithVisibility(player);
    }

    protected void resetPlayers() {
        for (Player competitor : players) {
            competitor.setWinner(false);
        }
    }

    protected int getNumberOfPlayers(){
        return inOut.getInputIntegerBetween(NUMBER_OF_PLAYERS, MINIMUM_PLAYERS, MAXIMUM_PLAYERS);
    }

    public Player createPlayer(PlayerType competitorType, String name){
        Player player = new Player(competitorType, name);
        addPlayer(player);
        return player;
    }

    protected void initiatePlayers(int numberOfPlayers, String name) {
        players.clear();
        createHumanPlayer(name);
        List<String> computerNames = getComputerPlayersNames();
        createComputerCompetitors(numberOfPlayers - 1);
    }

    public void addPlayer(Player competitor) {
        players.add(competitor);
    }

    public void createHumanPlayer(String name){
        addPlayer(new Player(PlayerType.USER, name));
    }

    protected List<String> getComputerPlayersNames(){
        if (computerNames.size() == 0){
            List<String[]> rawPlayerNames = loadCSV.getCSVRows(PLAYER_NAME_FILE);
            for (String[] row : rawPlayerNames){
                computerNames.add(row[1]);
            }
        }
        return computerNames;
    }

    protected String getNextComputerName(List<String> computerNames){
        String name = null;
        if (computerNames.size() > 0){
            name = computerNames.remove(0);
        } else {
            name = "Computer - " + extraComputerNameCounter;
            extraComputerNameCounter += 1;
        }
        return name;
    }

    public void createComputerCompetitors(int noOfComputerCompetitors) {
        List<String> computerNames = getComputerPlayersNames();
        for (int counter = 0; counter < noOfComputerCompetitors; counter++) {
            addPlayer(new Player(PlayerType.COMPUTER, getNextComputerName(computerNames)));
        }
    }

    protected int getScore(Player player){
        return player.getScore();
    };

    public Player determineWinner(){
        winningScore = -1;
        Player winningPlayer = null;
        int currentScore = 0;
        boolean individualHasWon = false;
        for (Player player : players){
            currentScore = getScore(player);
            if (player.hasWon()){
                winningPlayer = player;
                individualHasWon =  true;
            }
            if (!individualHasWon && currentScore > winningScore) {
                winningScore = currentScore;
                winningPlayer = player;
            }
            player.setScore(currentScore);
        }
        winningPlayer.setWinner(true);
        return winningPlayer;
    }

    protected abstract void beforePlayOfRound();

    protected abstract void afterPlayOfRound();

    protected abstract void userPlays(Player competitor);

    protected abstract void computerPlays(Player competitor);

    public void playerPlaysHand(Player competitor) {
        if (competitor.getCompetitorType() == PlayerType.USER) {
            userPlays(competitor);
        } else {
            computerPlays(competitor);
        }
    }

    protected void beforePlayerPlays(Player player){

    }

    protected void afterPlayerPlays(Player player){

    }

    public void playerPlaysARound(Player player){
        beforePlayerPlays(player);
        playerPlaysHand(player);
        afterPlayerPlays(player);
    }

    public void playARound() {
        int counter = 0;
        Player player = null;
        do {
            player = players.get(counter);
            playerPlaysARound(player);
            counter ++;
        } while (counter < players.size() && !player.hasWon() && !getFinishGame() && player.getHand().size() > 0);
    }

    protected void showPlayers() {
        for (Player player : players) {
            displayPlayerWithVisibility(player);
        }
    }

    protected abstract void initiate();

    public void playTillFinish(){
        while (!finishGame && !getUser().hasWon()) {
            beforePlayOfRound();
            playARound();
            afterPlayOfRound();
        }
    }

    protected void afterWinnerDeclared(Player player){

    }

    public void play() {
        print("Start " + getClassName());
        initiate();
        playTillFinish();
        Player winner = determineWinner();
        if (winner != null){
            winner.setWinner(true);
            afterWinnerDeclared(winner);    
        }
        showPlayers();
        inOut.close();
    }

}
