package inheritance;

public class Shop extends Place {
    public Shop(String name, String description, int priceRating) throws InvalidInputException {
        super(name, description, priceRating);
        if (priceRating == -1) throw new InvalidInputException("Shop must have a price rating.");
        this.priceRating = priceRating;
    }
}
