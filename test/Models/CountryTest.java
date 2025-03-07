package Models;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for the Country class.
 * This class contains test methods for various functionalities of the Country class,
 */
public class CountryTest {
    @org.junit.jupiter.api.Test
    void testCountryProperties() {
        Country country = new Country(1, "France", 1);
        assertEquals("France", country.get_countryName());
        assertEquals(1, country.getContinentID());
    }
}