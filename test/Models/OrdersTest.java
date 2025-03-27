package Models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import Models.Orders.DeployOrder;

/**
 * Tests cases for executing orders, it ensures that the Orders class functions correctly according to the specified test cases.
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class OrdersTest {
    /**
     * The Instance of warmap.
     */
    private WarMap warmap;
    /**
     * The Instance of Country.
     */
    private Country country;

    /**
     * Sets up the test environment before each test case.
     * It initializes the WarMap and Country objects.
     */
    @BeforeEach
    public void setUp() {
        warmap = new WarMap();
        country = new Country(1, "DestCountry");
        warmap.get_countries().put(country.get_countryID(), country);

    }

    /**
     * Tests the execution of orders by the Orders class.
     * It creates an Orders object, executes it, and then asserts that the expected result is achieved
     */
    @Test
    public void testExecute() {
        DeployOrder order = new DeployOrder(5, country.get_countryID());
        order.execute(warmap);

        assertEquals(5, warmap.get_countries().get(country.get_countryID()).get_numOfArmies());

    }
}