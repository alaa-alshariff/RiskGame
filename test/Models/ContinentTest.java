package Models;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

/**
 * Unit tests for the Continent class.
 * This class contains test methods for various functionalities of the Continent class,
 * including edge scenarios.
 */
public class ContinentTest {

    @Test
    @DisplayName("Test default constructor values")
    void testDefaultConstructor() {
        // Arrange & Act
        Continent continent = new Continent();
        
        // Assert
        assertEquals(0, continent.get_continentID(), "Default continent ID should be 0");
        assertEquals("Default name", continent.get_continentName(), "Default name should be 'Default name'");
        assertEquals(0, continent.get_armyBonus(), "Default army bonus should be 0");
    }

    @Test
    @DisplayName("Test constructor with parameters")
    void testParameterizedConstructor() {
        // Arrange & Act
        Continent continent = new Continent(1, "Europe", 5);
        
        // Assert
        assertEquals(1, continent.get_continentID(), "Continent ID should be 1");
        assertEquals("Europe", continent.get_continentName(), "Continent name should be 'Europe'");
        assertEquals(5, continent.get_armyBonus(), "Army bonus should be 5");
    }

    @Test
    @DisplayName("Test setters and getters")
    void testSettersAndGetters() {
        // Arrange
        Continent continent = new Continent();
        
        // Act
        continent.set_continentID(2);
        continent.set_continentName("Asia");
        continent.set_armyBonus(10);
        
        // Assert
        assertEquals(2, continent.get_continentID(), "Continent ID should be 2");
        assertEquals("Asia", continent.get_continentName(), "Continent name should be 'Asia'");
        assertEquals(10, continent.get_armyBonus(), "Army bonus should be 10");
    }

    @Test
    @DisplayName("Test handling of negative values")
    void testNegativeValues() {
        // Arrange
        Continent continent = new Continent();
        
        // Act
        continent.set_continentID(-1);
        continent.set_armyBonus(-5);
        
        // Assert
        assertEquals(-1, continent.get_continentID(), "Continent ID should accept negative values");
        assertEquals(-5, continent.get_armyBonus(), "Army bonus should accept negative values");
    }

    @Test
    @DisplayName("Test setting an empty string as continent name")
    void testEmptyStringForName() {
        // Arrange
        Continent continent = new Continent();
        
        // Act
        continent.set_continentName("");
        
        // Assert
        assertEquals("", continent.get_continentName(), "Continent name should allow empty strings");
    }

    @Test
    @DisplayName("Test the toString() method output")
    void testToStringMethod() {
        // Arrange
        Continent continent = new Continent(3, "Africa", 7);
        
        // Act
        String result = continent.toString();
        
        // Assert
        assertTrue(result.contains("continentID=3"), "toString() should contain the correct continent ID");
        assertTrue(result.contains("continentName='Africa'"), "toString() should contain the correct continent name");
    }
}
