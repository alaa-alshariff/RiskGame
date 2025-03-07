package Models;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for the Continent Model class.
 * This class contains test methods for various functionalities of the Continent class,
 */
public class ContinentTest {
    @org.junit.jupiter.api.Test
    void testContinentProperties() {
        Continent continent = new Continent(1, "Europe", 5);
        assertEquals("Europe", continent.get_continentName());
        assertEquals(5, continent.get_armyBonus());
    }
}
