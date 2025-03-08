package Models;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInstance;

/**
 * Test cases for executing orders, ensuring that the Orders class functions correctly according to the specified test cases.
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class OrdersTest {
    
    /**
     * The instance of WarMap.
     */
    private WarMap warmap;
    
    /**
     * The instance of Country.
     */
    private Country country;
    
    /**
     * Sets up the test environment before each test case.
     * Initializes the WarMap and Country objects.
     */
    @BeforeEach
    void setUp() {
        warmap = new WarMap();
        country = new Country(1, "France", 2);
        warmap.get_countries().put(country.get_countryID(), country);
    }
    
    /**
     * Tests the execution of orders by the Orders class.
     * Ensures that armies are correctly deployed to the specified country.
     */
    @Test
    @DisplayName("Test order execution for deploying armies")
    void testExecute() {
        // Arrange
        Orders order = new Orders(5, country.get_countryID());
        
        // Act
        order.execute(warmap);
        
        // Assert
        assertEquals(5, warmap.get_countries().get(country.get_countryID()).get_numOfArmies(), "Armies should be correctly deployed");
    }
    
    /**
     * Tests order execution with zero armies.
     */
    @Test
    @DisplayName("Test order execution with zero armies")
    void testExecuteWithZeroArmies() {
        // Arrange
        Orders order = new Orders(0, country.get_countryID());
        
        // Act
        order.execute(warmap);
        
        // Assert
        assertEquals(0, warmap.get_countries().get(country.get_countryID()).get_numOfArmies(), "No armies should be deployed if order is zero");
    }
    
    /**
     * Tests order execution with negative armies.
     */
    @Test
    @DisplayName("Test order execution with negative armies")
    void testExecuteWithNegativeArmies() {
        // Arrange
        Orders order = new Orders(-3, country.get_countryID());
        
        // Act
        order.execute(warmap);
        
        // Assert
        assertEquals(-3, warmap.get_countries().get(country.get_countryID()).get_numOfArmies(), "Negative armies should be allowed but might represent a special case");
    }
    
    /**
     * Tests order execution with a non-existent country.
     */
    @Test
    @DisplayName("Test order execution with a non-existent country ID")
    void testExecuteWithInvalidCountryID() {
        // Arrange
        Orders order = new Orders(10, 999);
        
        // Act
        order.execute(warmap);
        
        // Assert
        assertNull(warmap.get_countries().get(999), "Country should not exist in the map");
    }
    
    /**
     * Tests setters and getters for Orders class.
     */
    @Test
    @DisplayName("Test setters and getters for Orders class")
    void testSettersAndGetters() {
        // Arrange
        Orders order = new Orders(5, country.get_countryID());
        
        // Act
        order.setNumOfArmies(10);
        order.setCountryID(2);
        
        // Assert
        assertEquals(10, order.getNumOfArmies(), "Number of armies should be updated");
        assertEquals(2, order.getCountryID(), "Country ID should be updated");
    }
}