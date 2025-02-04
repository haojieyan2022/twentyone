package card;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import card.entity.PlayerType;
import card.entity.Player;

class CardGameTest {

    CardGame cardGame = new CardGame();
    List<String> computerNames = new ArrayList<>();

    @Test
    void testContructor(){
        assertEquals(52, cardGame.deck.size());
    }

    @Test
    void testContructorWithOverride(){
        CardGame cardGame = new CardGame("D3,D2,S5,S6");
        assertEquals(4, cardGame.deck.size());
    }

    @Test
    void testGetDeck() {
        assertEquals(52, cardGame.getDeck().size());
    }

    @Test
    void testCreatePlayerName(){
        String name = "Derek";
        Player player = cardGame.createPlayer(PlayerType.USER, name);
        assertEquals(name, player.getName());
    }

    @Test
    void testCreatePlayerType(){
        String name = "Derek";
        Player player = cardGame.createPlayer(PlayerType.USER, name);
        assertEquals(PlayerType.USER, player.getCompetitorType());
    }

    @Test
    void testAddPlayer(){
        String name = "Derek";
        Player player = cardGame.createPlayer(PlayerType.USER, name);
        assertEquals(1, cardGame.getPlayers().size());
    }


    @Test
    void testCreateComputerCompetitorsComputer(){
        cardGame.createComputerCompetitors(3);
        assertEquals(PlayerType.COMPUTER, cardGame.getPlayer(0).getCompetitorType());
    }

    @Test
    void testCreateComputerCompetitors(){
        cardGame.createComputerCompetitors(3);
        assertEquals(3, cardGame.getPlayersSize());
    }

    @Test
    void testInitiatePlayers(){
        String name = "Derek";
        cardGame.initiatePlayers(4, name);
        assertEquals(4, cardGame.getPlayersSize());
    }

    
}