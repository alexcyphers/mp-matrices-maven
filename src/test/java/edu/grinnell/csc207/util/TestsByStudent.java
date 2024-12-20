package edu.grinnell.csc207.util;

import static edu.grinnell.csc207.util.MatrixAssertions.assertMatrixEquals;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

/**e
 * A variety of tests for the Matrix class written by the student.
 *
 * @author Alex Cyphers
 */
public class TestsByStudent {
    

    /**
     * Tests basic methods like, width, height,
     */
    @Test
    void testMatrixOperations() {
        MatrixV0<Integer> testMatrix = new MatrixV0<>(4, 4);

        // Test width and height
        assertEquals(4, testMatrix.width(), "Width should be 4");
        assertEquals(4, testMatrix.height(), "Height should be 4.");

        // Tests set and get
        testMatrix.set(3, 2, 5);
        assertEquals(5, testMatrix.get(3, 2), "Value should be 5.");
        assertEquals(null, testMatrix.get(2, 1), "Value should be null");

        // Tests cloned
        Matrix<Integer> clonedTestMatrix = testMatrix.clone();
        assertEquals(5, clonedTestMatrix.get(3, 2), "Value should be 5 for cloned.");
        assertEquals(null, clonedTestMatrix.get(2, 1), "Value should be null for");
    } // testMatrixOperations


    /**
     * Tests insert and delete.
     */
    @Test
    void testInsertDelete() {
        MatrixV0<String> testMatrix = new MatrixV0<>(4, 4, "a");

        String[] row = {"a", "b", "c", "d"};
        testMatrix.insertRow(1, row);
        assertEquals(5, testMatrix.height(), "Height should be 4");
        assertEquals("b", testMatrix.get(1, 1), "Should get ");

        testMatrix.insertCol(2, row);
        assertEquals(5, testMatrix.width(), "Width should be 5");
        assertEquals(5, testMatrix.get(3, 2), "Should get b");

        testMatrix.deleteRow(1);
        assertEquals(3, testMatrix.height(), "Value should be 3 for after delete.");
    } // testInsertDelete

    /**
     * Tests fill, equality
     */
    @Test
    void testFillandEquality() {
        MatrixV0<String> testMatrix = new MatrixV0<>(5, 5, "a");

        testMatrix.fillRegion(1, 1, 4, 4, "p");
        assertEquals("p", testMatrix.get(2, 2), "Value should be p");

        testMatrix.fillLine(0, 0, 1, 1, 3, 3, "z");
        assertEquals("z", testMatrix.get(0, 0), "Value should be z");
    } // testInsertDelete
}
