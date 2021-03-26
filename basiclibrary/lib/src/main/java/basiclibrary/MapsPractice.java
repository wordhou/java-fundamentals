package basiclibrary;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MapsPractice {
    public static String weatherData(int[][] temperatureArrays) throws Exception {
        int[] temps = flatten(temperatureArrays);
        if (temps.length == 0) throw new Exception("Array must not be empty");

        int min = temps[0];
        int max = temps[0];
        Set<Integer> tallies = new HashSet();

        for (int t : temps) {
            if (min > t) min = t;
            if (max < t) max = t;
            tallies.add(t);
        }
        String result = String.format("High: %d\nLow: %d\n", max, min);
        for (int i = min; i <= max; i++) {
            if (!tallies.contains(i)) result += String.format("Never saw temperature: %d\n", i);
        }
        System.out.print(result);
        return result;
    }

    public static String tally(String[] names) {
        Map<String, Integer> counts = new HashMap();
        for (String name : names) {
            if (counts.containsKey(name)) counts.put(name, counts.get(name) + 1);
            else counts.put(name, 1);
        }

        int max = 0;
        String name = null;
        for (Map.Entry<String, Integer> entry : counts.entrySet()) {
            if (entry.getValue() > max) {
                name = entry.getKey();
                max = entry.getValue();
            }
        }
        return name;

    }

    private static int[] flatten(int[][] arrays) {
        int count = 0;
        for (int[] array : arrays) count += array.length;
        int[] result = new int[count];
        int k = 0;
        for (int i = 0; i < arrays.length; i++)
            for (int j = 0; j < arrays[i].length; j++, k++)
                result[k] = arrays[i][j];

        return result;
    }
}