package basiclibrary;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MapsPracticeTest {
    @Test
    public void weatherDataTest() throws Exception {
        int[][] input = {
                {66, 64, 58, 65, 71, 57, 60},
                {57, 65, 65, 70, 72, 65, 51},
                {55, 54, 60, 53, 59, 57, 61},
                {65, 56, 55, 52, 55, 62, 57}
        };
        String expected = "High: 72\n" +
                "Low: 51\n" +
                "Never saw temperature: 63\n" +
                "Never saw temperature: 67\n" +
                "Never saw temperature: 68\n" +
                "Never saw temperature: 69\n";

        String result = MapsPractice.weatherData(input);
        assertEquals("result is in the right format with correct numbers", expected, result);
    }

    @Test
    public void tallyTest() {
        String[] input = {"A", "A", "B", "C", "A", "A", "C", "B"};
        String result = MapsPractice.tally(input);
        assertEquals("returns the string that occurs the most", "A", result);

        String[] input2 = {"A", "A", "B", "B", "A", "A", "B", "B"};
        String result2 = MapsPractice.tally(input);
        assertEquals("returns the first string that occurs the most", "A", result2);
    }
}
