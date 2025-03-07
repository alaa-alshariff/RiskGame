package Controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Unit tests for the MapEditor class.
 * This class contains test methods for various functionalities of the MapEditor class,
 */
public class MapEditorTest {
    @org.junit.jupiter.api.Test
    void testMapEditorFunctionality() {
        // Initialize MapEditor and verify basic functionality
        MapEditor editor = new MapEditor();
        assertNotNull(editor);
    }
}
