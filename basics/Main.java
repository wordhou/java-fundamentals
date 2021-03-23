import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
  public static void main(String[] args) {
    // Test the pluralize function.
    System.out.println(pluralize("potato", 3));
    // I own 3 potatos.
    System.out.println(pluralize("leek", 1));
    // I own 1 leek.
    
    short num = 2;
    flipNHeads(num);
    // Should return a sequence that ends in two heads, and also does not contain two heads anywhere within the sequence.
    // Then it should return a line that describes how many flips it took to flip n heads in a row.

    clock();
  }

  public static String pluralize(String noun, int count) {
    String ending = count > 1 ? "s" : "";
    return String.format("I own %d %s%s.", count, noun, ending);
  }

  public static int flipNHeads(short n) {
    if (n < 0) throw new Error("Can't flip a negative number of heads");
    int heads = 0;
    int count;
    for (count = 0; ; count++) {
      if (flipCoin()) {
        heads++;
        if (heads >= n) break;
      } else {
        heads = 0;
      }
    }
    System.out.printf("It took %d flips to flip %d heads in a row.\n", count, n);
    return count;
  }

  /** Returns true half the time, and prints heads or tails to stdout */
  public static boolean flipCoin() {
    double num = Math.random();
    if (num < 0.5) {
      System.out.println("tails");
      return false;
    } else {
      System.out.println("heads");
      return true;
    }
  }

  public static void clock() {
    String current = "";
    //int count = 0;
    while (true) {
      String time = getTimeString();
      //count++;
      if (!time.equals(current)) {
        current = time;
        System.out.println(current);
      }
    }
  }

  private static String getTimeString() {
    return LocalDateTime
      .now()
      .format(DateTimeFormatter.ofPattern("HH:mm:ss"));
  }
}
