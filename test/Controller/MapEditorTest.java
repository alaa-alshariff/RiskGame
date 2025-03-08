package Controller;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import java.io.IOException;
import Models.WarMap;

/**
 * Unit tests for the MapEditor class.
 * This class contains test methods for various functionalities of the MapEditor class.
 */
public class MapEditorTest {
    
    /**
     * The instance of MapEditor.
     */
    private MapEditor mapEditor;
    
    /**
     * The instance of WarMap.
     */
    private WarMap warMap;
    
    /**
     * Sets up the test environment before each test case.
     * Initializes the MapEditor and WarMap objects.
     */
    @BeforeEach
    void setUp() {
        mapEditor = new MapEditor();
        warMap = new WarMap();
    }
    
    /**
     * Tests the initialization of the MapEditor.
     */
    @Test
    @DisplayName("Test MapEditor initialization")
    void testMapEditorInitialization() {
        assertNotNull(mapEditor, "MapEditor instance should be created successfully");
    }
    
    /**
     * Tests reading a valid map file.
     */
    @Test
    @DisplayName("Test reading a valid map file")
    void testReadMapValid() throws IOException {
        boolean result = MapEditor.readMap("europe.map", warMap);
        assertTrue(result, "Reading a valid map file should return true");
    }
    
    /**
     * Tests reading a non-existent map file.
     */
    @Test
    @DisplayName("Test reading a non-existent map file")
    void testReadMapInvalid() {
        assertThrows(IOException.class, () -> {
            MapEditor.readMap("invalidMap.map", warMap);
        }, "Reading an invalid file should throw an IOException");
    }
    
    /**
     * Tests editing an existing map file.
     */
    @Test
    @DisplayName("Test editing an existing map file")
    void testEditMapExistingFile() throws IOException {
        boolean result = MapEditor.editMap("europe.map", warMap);
        assertTrue(result, "Editing an existing map should return true");
    }
    
    /**
     * Tests showing all maps.
     */
    @Test
    @DisplayName("Test showing all maps")
    void testShowAllMaps() {
        assertDoesNotThrow(() -> MapEditor.showAllMaps(), "showAllMaps should not throw an exception");
    }
}