package Models;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test for functions concerning Player orders
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PlayerTest {

    /**
     * The player instance required to run the test case.
     */
    private Player player;

    /**
     * Initializing the player instance before each test.
     */
    @BeforeEach
    public void setUp() {
        player = new Player("John Doe");
    }

    /**
     * Testing if the next order returns null when no orders are issued.
     */
    @Test
    public void testNextOrderEmptyList() {
        Orders nextOrder = player.next_order();

        assertNull(nextOrder);
    }

    /**
     * Testing the next orders and the size of the order list upon adding.
     */
    @Test
    public void testNextOrder() {
        Orders order1 = new Orders(3, 1);
        Orders order2 = new Orders(2, 2);
        Orders order3 = new Orders(4, 3);

        player.get_playerOrder().add(order1);
        player.get_playerOrder().add(order2);
        player.get_playerOrder().add(order3);

        Orders nextOrder = player.next_order();

        assertEquals(order1, nextOrder);
        assertEquals(2, player.get_playerOrder().size());
        assertFalse(player.get_playerOrder().contains(order1));
    }

    /**
     * Test if the country deployed to is invalid.
     */
    @Test
    public void testInvalidCountry() {
        player.set_numOfReinforcements(5);

        List<Country> playerCountries = new ArrayList<>();
        playerCountries.add(new Country(1, "Country1", 1));
        playerCountries.add(new Country(2, "Country2", 1));
        playerCountries.add(new Country(3, "Country3", 2));
        player.set_playerCountries(playerCountries);

        // Set up the input for issue_order() method
        String[] commands = {
                "deploy 1 3",
                "deploy 4 2",
                "deploy 3 2"
        };
        player.issue_order(commands);

        List<Orders> playerOrders = player.get_playerOrder();
        assertEquals(2, playerOrders.size());

        Orders order1 = playerOrders.get(0);
        assertEquals(3, order1.getNumOfArmies());
        assertEquals(1, order1.getCountryID());

        Orders order2 = playerOrders.get(1);
        assertEquals(2, order2.getNumOfArmies());
        assertEquals(3, order2.getCountryID());
    }


    /**
     * Deploying more number of armies then there are reinforcements available.
     */
    @Test
    public void testCannotDeployMoreArmiesThanReinforcements() {
        // Set the player's initial reinforcement pool to 5
        player.set_numOfReinforcements(5);

        List<Country> playerCountries = new ArrayList<>();
        playerCountries.add(new Country(1, "Country1", 1));
        playerCountries.add(new Country(2, "Country2", 1));
        playerCountries.add(new Country(3, "Country3", 2));
        player.set_playerCountries(playerCountries);

        // Attempt to issue a deploy order with more armies than available reinforcements
        String[] invalidDeployCommand = {
                "deploy 1 10",
                "deploy 2 3",
                "deploy 3 2"
        }; // Deploying 10 armies with only 5 available
        player.issue_order(invalidDeployCommand);

        // Ensure that only valid orders were added to the player's order list
        assertEquals(2, player.get_playerOrder().size());

        // Ensure that the player's available reinforcements remain unchanged
        assertEquals(Integer.valueOf(0), player.get_numOfReinforcements());
    }
}