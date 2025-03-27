package Phases;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import Controller.GameEngine;

public class PlayPhaseTest {

    private GameEngine gameEngine;

    @Before
    public void setUp() {
        gameEngine = GameEngine.getInstance();
        gameEngine.setPhase(new MainMenu(gameEngine));
        gameEngine.get_PlayersList().clear();
    }

    @Test
    public void testPlayPhaseTransition() throws IOException {
        gameEngine.setCurrentInput("loadmap europe.map");
        gameEngine.getPhase().next();
        assertEquals("Startup", gameEngine.getPhase().getClass().getSimpleName());
        gameEngine.setCurrentInput("goback");
        gameEngine.getPhase().next();
        assertEquals("MainMenu", gameEngine.getPhase().getClass().getSimpleName());
        gameEngine.setCurrentInput("loadmap europe.map");
        gameEngine.getPhase().next();
        assertEquals("Startup", gameEngine.getPhase().getClass().getSimpleName());
        gameEngine.setCurrentInput("gameplayer -add player1 -add player2");
        gameEngine.getPhase().setPlayers();
        gameEngine.setCurrentInput("assigncountries");
        gameEngine.getPhase().assignCountries();
        assertEquals("AssignReinforcements", gameEngine.getPhase().getClass().getSimpleName());
        gameEngine.getPhase().next(); //moves to second player
        assertEquals("AssignReinforcements", gameEngine.getPhase().getClass().getSimpleName());
        gameEngine.getPhase().next(); //moves to issue order phase
        assertEquals("IssueOrders", gameEngine.getPhase().getClass().getSimpleName());
        gameEngine.setCurrentInput("execute");
        gameEngine.getPhase().next();
        assertEquals("IssueOrders", gameEngine.getPhase().getClass().getSimpleName());
        gameEngine.getPhase().next();
        assertEquals("OrderExecution", gameEngine.getPhase().getClass().getSimpleName());
        gameEngine.getPhase().next();
        assertEquals("AssignReinforcements", gameEngine.getPhase().getClass().getSimpleName());
    }

    @Test
    public void testStartUpValidations() throws IOException {
        gameEngine.setCurrentInput("loadmap");
        gameEngine.getPhase().loadMap();
        assertEquals("MainMenu", gameEngine.getPhase().getClass().getSimpleName()); //Stays main menu without a proper map loaded
        gameEngine.setCurrentInput("loadmap europe.map");
        gameEngine.getPhase().loadMap();
        assertEquals("Startup", gameEngine.getPhase().getClass().getSimpleName()); //Goes to startup with proper map
        gameEngine.setCurrentInput("assigncountries");
        gameEngine.getPhase().assignCountries();
        assertEquals("Startup", gameEngine.getPhase().getClass().getSimpleName()); //Stays start up as not enough players added
        gameEngine.setCurrentInput("gameplayer -add player1 -add player2");
        gameEngine.getPhase().setPlayers();
        gameEngine.setCurrentInput("assigncountries");
        gameEngine.getPhase().assignCountries();
        assertEquals("AssignReinforcements", gameEngine.getPhase().getClass().getSimpleName()); //Goes to assignreinforcements after adding two players
    }
}