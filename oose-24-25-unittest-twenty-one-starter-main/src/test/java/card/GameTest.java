package card;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;
import java.util.ArrayList;

import card.entity.PlayerType;
import card.entity.Player;
import card.Game;
enum DummyEnum { PLACEHOLDER }

public class GameTest {

    private Game<DummyEnum> game = new Game<DummyEnum>(){
        @Override
        protected void beforePlayOfRound(){

        }
        @Override
        protected void afterPlayOfRound(){

        }
        @Override
        protected void userPlays(Player player){

        }
        @Override
        protected void computerPlays(Player player){

        }
        @Override
        protected void initiate(){

        }
    };

    @Test
    void testAddPlayerCount() {
        Player player = new Player(PlayerType.USER, "Derek");
        game.addPlayer(player);
        assertEquals(1, game.getPlayersSize());
    }

    @Test
    void testAddPlayerName() {
        Player player = new Player(PlayerType.USER, "Alice");
        game.addPlayer(player);
        assertEquals("Alice", game.getPlayer(Game.USER_INDEX).getName());
    }

    @Test
    void testClearPlayers() {
        game.addPlayer(new Player(PlayerType.USER, "Charlie"));
        game.clearPlayers();
        assertEquals(0, game.getPlayersSize());
    }

    @Test
    void testCreatePlayerCount(){
        game.createPlayer(PlayerType.USER, "Bob");
        assertEquals(1, game.getPlayersSize());
    }

    @Test
    void testCreatePlayerName(){
        game.createPlayer(PlayerType.USER, "David");
        assertEquals("David", game.getPlayer(Game.USER_INDEX).getName());
    }

    @Test
    void testCreateHumanPlayer(){
        game.createHumanPlayer("Eve");
        assertEquals("Eve", game.getPlayer(Game.USER_INDEX).getName());
    }

    @Test
    void testGetNextComputerNameFirst(){
        List<String> computerNames = List.of("Bot1", "Bot2", "Bot3");
        assertEquals("Bot1", game.getNextComputerName(new ArrayList<>(computerNames)));
    }

    @Test
    void testGetNextComputerNameSecond(){
        List<String> computerNames = new ArrayList<>(List.of("Bot1", "Bot2", "Bot3"));
        game.getNextComputerName(computerNames);
        assertEquals("Bot2", game.getNextComputerName(computerNames));
    }

    @Test
    void testInitiatePlayers() {
        game.initiatePlayers(3, "Alice");
        assertEquals(3, game.getPlayersSize());
    }

    @Test
    void testInitiatePlayersComputer() {
        game.initiatePlayers(3, "Alice");
        assertEquals(PlayerType.COMPUTER, game.getPlayer(1).getCompetitorType());
    }

    @Test
    void testResetPlayers(){
        game.initiatePlayers(3, "Alice");
        game.getUser().setWinner(true);
        game.resetPlayers();
        assertFalse(game.getUser().hasWon());
    }

    @Test
    void testDetermineWinnerByScoreDecrease(){
        Player p1 = new Player(PlayerType.USER, "Alice");
        Player p2 = new Player(PlayerType.COMPUTER, "Bob");
        Player p3 = new Player(PlayerType.COMPUTER, "Charlie");

        p1.setScore(30);
        p2.setScore(20);
        p3.setScore(10);

        game.addPlayer(p1);
        game.addPlayer(p2);
        game.addPlayer(p3);

        assertEquals(p1, game.determineWinner());
    }   

    @Test
    void testDetermineWinnerByScoreIncrease(){
        Player p1 = new Player(PlayerType.USER, "Alice");
        Player p2 = new Player(PlayerType.COMPUTER, "Bob");
        Player p3 = new Player(PlayerType.COMPUTER, "Charlie");

        p1.setScore(10);
        p2.setScore(20);
        p3.setScore(30);

        game.addPlayer(p1);
        game.addPlayer(p2);
        game.addPlayer(p3);

        assertEquals(p3, game.determineWinner());
    }   

    @Test
    void testDetermineWinnerByScoreDecreaseWithWinner(){
        Player p1 = new Player(PlayerType.USER, "Alice");
        Player p2 = new Player(PlayerType.COMPUTER, "Bob");
        Player p3 = new Player(PlayerType.COMPUTER, "Charlie");

        p1.setScore(30);
        p2.setScore(20);
        p3.setScore(10);
        p3.setWinner(true);

        game.addPlayer(p1);
        game.addPlayer(p2);
        game.addPlayer(p3);

        assertEquals(p3, game.determineWinner());
    }   

    @Test
    void testDetermineWinnerByScoreIncreaseWithWInner(){
        Player p1 = new Player(PlayerType.USER, "Alice");
        Player p2 = new Player(PlayerType.COMPUTER, "Bob");
        Player p3 = new Player(PlayerType.COMPUTER, "Charlie");

        p1.setScore(10);
        p2.setScore(20);
        p3.setScore(30);
        p1.setWinner(true);

        game.addPlayer(p1);
        game.addPlayer(p2);
        game.addPlayer(p3);

        assertEquals(p1, game.determineWinner());
    }   

    @Test
    void testSetFinishGame() {
        game.setFinishGame(true);
        assertTrue(game.getFinishGame());
    }
}

