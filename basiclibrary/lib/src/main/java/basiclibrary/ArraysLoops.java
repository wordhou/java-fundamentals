package basiclibrary;

import java.util.Random;

public class ArraysLoops {
    public static int[] roll(int numberOfRolls) {
        Random rand = new Random();
        int[] result = new int[numberOfRolls];

        for (int i = 0; i < numberOfRolls; i++) {
            result[i] = rand.nextInt(6) + 1;
        }

        return result;
    }

    public static boolean containsDuplicates(int[] array) {
        // We are comparing every the element in the array with every element that
        // comes after it in the array, because every element has already been compared
        // to the element before it.
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] == array[j]) return true;
            }
        }

        return false;
    }

    public static double getAverage(int[] array) {
        if (array.length == 0) throw new IndexOutOfBoundsException("Array can't be empty");
        double sum = 0;
        for (int value : array) {
            sum = sum + value;
        }

        return sum / array.length;
    }

    public static int[] findLeastAverageArray(int[][] arrayOfArrays) {
        if (arrayOfArrays.length == 0) throw new IndexOutOfBoundsException("Array can't be empty");

        int[] leastAverageArray = arrayOfArrays[0];
        double leastAverage = getAverage(arrayOfArrays[0]); // Initialize least average to the first array's average
        double avg;
        for (int[] array : arrayOfArrays) {
            avg = getAverage(array);
            if (avg < leastAverage) {
               leastAverage = avg;
               leastAverageArray = array;
            }
        }

        return leastAverageArray;
    }
}
