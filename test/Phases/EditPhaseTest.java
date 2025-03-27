package Phases;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import Controller.GameEngine;

public class EditPhaseTest {

    private GameEngine gameEngine;

    @Before
    public void setUp() {
        gameEngine = GameEngine.getInstance();
        gameEngine.setPhase(new MainMenu(gameEngine));
        gameEngine.get_PlayersList().clear();

    }

    @Test
    public void testEditPhaseTransition() throws IOException {
        gameEngine.setCurrentInput("editmap");
        gameEngine.getPhase().next();
        assertEquals("Preload", gameEngine.getPhase().getClass().getSimpleName());
        gameEngine.setCurrentInput("quit");
        gameEngine.getPhase().next();
        assertEquals("MainMenu", gameEngine.getPhase().getClass().getSimpleName());
        gameEngine.setCurrentInput("editmap");
        gameEngine.getPhase().next();
        gameEngine.getPhase().next();
        assertEquals("Postload", gameEngine.getPhase().getClass().getSimpleName());
        gameEngine.getPhase().next();
        assertEquals("MainMenu", gameEngine.getPhase().getClass().getSimpleName());


    }
}