package inheritance;

public class Restaurant extends Place {
    public Restaurant(String name, String description, int priceRating) throws InvalidInputException {
        super(name, description, priceRating);
        if (priceRating == -1) throw new InvalidInputException("Restaurant must have a price rating.");
        this.priceRating = priceRating;
    }
}