package Models;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

/**
 * Unit tests for the Country class.
 * This class contains test methods for various functionalities of the Country class,
 * including edge scenarios.
 */
public class CountryTest {

    @Test
    @DisplayName("Test default constructor values")
    void testDefaultConstructor() {
        // Arrange & Act
        Country country = new Country();
        
        // Assert
        assertEquals(0, country.get_countryID(), "Default country ID should be 0");
        assertEquals("Default Name", country.get_countryName(), "Default country name should be 'Default Name'");
        assertEquals(0, country.getContinentID(), "Default continent ID should be 0");
        assertEquals(0, country.get_numOfArmies(), "Default number of armies should be 0");
        assertTrue(country.getNeighbouringCountries().isEmpty(), "Default neighbouring countries should be empty");
    }

    @Test
    @DisplayName("Test constructor with parameters")
    void testParameterizedConstructor() {
        // Arrange & Act
        Country country = new Country(1, "France", 2);
        
        // Assert
        assertEquals(1, country.get_countryID(), "Country ID should be 1");
        assertEquals("France", country.get_countryName(), "Country name should be 'France'");
        assertEquals(2, country.getContinentID(), "Continent ID should be 2");
        assertEquals(0, country.get_numOfArmies(), "Number of armies should be 0 by default");
        assertTrue(country.getNeighbouringCountries().isEmpty(), "Neighbouring countries should be empty");
    }

    @Test
    @DisplayName("Test setters and getters")
    void testSettersAndGetters() {
        // Arrange
        Country country = new Country();
        
        // Act
        country.set_countryID(5);
        country.set_countryName("India");
        country.setContinentID(3);
        country.set_numOfArmies(20);
        
        // Assert
        assertEquals(5, country.get_countryID(), "Country ID should be 5");
        assertEquals("India", country.get_countryName(), "Country name should be 'India'");
        assertEquals(3, country.getContinentID(), "Continent ID should be 3");
        assertEquals(20, country.get_numOfArmies(), "Number of armies should be 20");
    }

    @Test
    @DisplayName("Test adding and removing neighboring countries")
    void testNeighbouringCountries() {
        // Arrange
        Country country = new Country(1, "USA", 4);
        Country neighbour1 = new Country(2, "Canada", 4);
        Country neighbour2 = new Country(3, "Mexico", 4);
        
        // Act
        country.addNeighbouringCountry(neighbour1);
        country.addNeighbouringCountry(neighbour2);
        
        // Assert
        assertEquals(2, country.getNeighbouringCountries().size(), "Neighbouring countries should contain 2 countries");
        assertTrue(country.getNeighbouringCountries().containsKey(2), "Neighbouring countries should include Canada");
        assertTrue(country.getNeighbouringCountries().containsKey(3), "Neighbouring countries should include Mexico");

        // Act (remove one neighbour)
        country.removeNeighbouringCountry(neighbour1);

        // Assert
        assertEquals(1, country.getNeighbouringCountries().size(), "Only one neighbouring country should remain");
        assertFalse(country.getNeighbouringCountries().containsKey(2), "Canada should be removed");
    }

    @Test
    @DisplayName("Test handling of negative values")
    void testNegativeValues() {
        // Arrange
        Country country = new Country();
        
        // Act
        country.set_countryID(-3);
        country.set_numOfArmies(-10);
        country.setContinentID(-2);
        
        // Assert
        assertEquals(-3, country.get_countryID(), "Country ID should accept negative values");
        assertEquals(-10, country.get_numOfArmies(), "Number of armies should accept negative values");
        assertEquals(-2, country.getContinentID(), "Continent ID should accept negative values");
    }

    @Test
    @DisplayName("Test setting an empty string as country name")
    void testEmptyStringForName() {
        // Arrange
        Country country = new Country();
        
        // Act
        country.set_countryName("");
        
        // Assert
        assertEquals("", country.get_countryName(), "Country name should allow empty strings");
    }

    @Test
    @DisplayName("Test the toString() method output")
    void testToStringMethod() {
        // Arrange
        Country country = new Country(6, "Germany", 3);
        
        // Act
        String result = country.toString();
        
        // Assert
        assertTrue(result.contains("countryID=6"), "toString() should contain the correct country ID");
        assertTrue(result.contains("countryName='Germany'"), "toString() should contain the correct country name");
    }
}
