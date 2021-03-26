# Lab 03: Maps, primitives, and file I/O

This lab covered the basics of the Map interface and file input/output.

## weatherData

We wrote a `weatherData()` method with the following method signature:

```
String weatherData(int[][] temperatureArrays)
```

This method takes a nested array of `int`s and returns a `String` containing information about the array, as specified in the requirements. The data points reported are the highest value, the lowest value, and all of the values in between the lowest and highest values that aren't represented.

This was accomplished by iterating through all of the values, collecting them into a HashSet, while also maintaining the maximum and the minimum value found. Then we iterated through integers between the minimum and maximum to find the values that weren't included in the HashSet.

## tally

The `tally` method accepts an array of strings and returns the string that occurs the most often in the array. If there are multiple strings that occur the most often, the first one is taken.

This was accomplished using a HashMap that counted the number of occurrences of every string in the array. Then we iterated through the HashMap and picked out the string that had the highest count.
