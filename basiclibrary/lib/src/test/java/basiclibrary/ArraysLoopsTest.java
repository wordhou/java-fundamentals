package basiclibrary;

import org.junit.Test;

import java.lang.reflect.Array;

import static org.junit.Assert.*;

public class ArraysLoopsTest {
    @Test
    public void testRoll() {
        int numberOfRolls = 5;
        int[] diceRolls = ArraysLoops.roll(numberOfRolls);
        assertEquals("result should have the correct number of rolls", numberOfRolls, diceRolls.length);
        for (int die : diceRolls) {
            assertTrue("dice roll should be a value from 1 to 6",
                    die >= 1 && die <= 6
            );
        }
    }

    @Test
    public void testContainsDuplicates() {
        int[] noDuplicates = {1, 2, 3, 4, 5, 6};
        int[] hasDuplicates = {1, 2, 3, 3, 4, 5, 6};
        int[] emptyArray = {};

        assertFalse("Returns false when no duplicates",
                ArraysLoops.containsDuplicates(noDuplicates));

        assertTrue("Returns true when there are duplicates",
                ArraysLoops.containsDuplicates(hasDuplicates));

        assertFalse("Should return false on empty array",
                ArraysLoops.containsDuplicates(emptyArray));
    }

    @Test
    public void testGetAverage() {
        double delta = 1e-5;

        int[] input1 = {11};
        int[] input2 = {1, 2, 3};
        int[] input3 = {-5, 5, 10, 20};
        int[] empty = {};

        assertEquals("average of a single element",
                ArraysLoops.getAverage(input1), 11.0, delta);
        assertEquals("gets average of multiple element",
                ArraysLoops.getAverage(input2), 2.0, delta);
        assertEquals("gets average when there's a negative number",
                ArraysLoops.getAverage(input3), 7.5, delta);
        assertThrows("throws on empty array", Exception.class, () -> {
            ArraysLoops.getAverage(empty);
        });
    }

    @Test
    public void testGetAverageOfAverages() {
       double delta = 1e-5;

        int[][] input1 = {
                {1},
                {3},
                {5}
        };

        int[][] input2 = {
                {0, 15, 30},
                {12, 24},
                {9}
        };

        int[][] bad = {
                {0, 15, 30},
                {12, 24},
                {}
        };

        int[][] empty = {};

        assertEquals("works on arrays of singletons",
                ArraysLoops.getAverageOfAverages(input1), 3.0, delta);
        assertEquals("works on arrays of singletons",
                ArraysLoops.getAverageOfAverages(input2), 14.0, delta);
        assertThrows("throws if array is empty", Exception.class, () -> {
            ArraysLoops.getAverageOfAverages(empty);
        });
        assertThrows("throws if any array inside is empty", Exception.class, () -> {
            ArraysLoops.getAverageOfAverages(bad);
        });
    }
}