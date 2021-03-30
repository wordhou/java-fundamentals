package inheritance;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class Restaurant {
    int priceRating;
    String name;
    Map<Long, Review> reviews;
    static final char FULL_STAR = '\u2605';
    static final char HALF_STAR = '\u00bd';
    static final char NO_STAR = '\u2606';

    public Restaurant (String name, int priceRating) throws InvalidInputException {
        validatePriceRating(priceRating);
        this.name = name;
        this.priceRating = priceRating;
        reviews = new HashMap<Long, Review>();
    }

    public Double getStarRating() {
        if (reviews.isEmpty()) return null;
        double sum = reviews.entrySet().stream()
                .map(Map.Entry::getValue)
                .filter(Review::hasRating)
                .mapToInt(Review::getRating)
                .sum();
        double count = reviews.entrySet().stream()
                .map(Map.Entry::getValue)
                .filter(Review::hasRating)
                .count();
        return sum / count;
    }

    public int getPriceRating() {
        return priceRating;
    }

    private void validatePriceRating(int priceRating) throws InvalidInputException {
        if (priceRating < 1 || priceRating > 4)
            throw new InvalidInputException("Price rating must be a number between 1 and 4");
    }

    @Override
    public String toString() {
        return String.format("Name: %s, Price: %s, Rating: %s",
                getName(),
                getPriceRatingString(),
                getStarRatingString()
        );
    }

    public String getName() {
        return name;
    }

    /**
     * Pretty prints the price rating of a restaurant as a string of dollar signs
     */
    private String getPriceRatingString() {
        return "$".repeat(getPriceRating());
    }

    /**
     * Pretty prints the rating of a restaurant as a string of full, half and empty stars
     */
    private String getStarRatingString() {
        if (getStarRating() == null) return "no reviews yet";
        char[] stars = new char[5];

        int r = (int) Math.round(getStarRating() * 2.0);
        for (int i = 0; i < 5; i++) {
            if (2*i <= r) stars[i] = FULL_STAR;
            if (2*i + 1 == r) stars[i] = HALF_STAR;
            if (2*i >= r) stars[i] = NO_STAR;
        }
        return new String(stars);
    }

    void addReview(Review review) throws ItemAlreadyExistsException {
        if (this.reviews.containsKey(review.getUserId()))
            throw new ItemAlreadyExistsException("");
        this.reviews.put(review.getUserId(), review);
    }

    @Nullable
    Review getReviewByUser(User u) {
        return reviews.get(u.id);
    }

    public void modifyReview(User u, String text, int i) throws NoSuchElementException, InvalidInputException {
        Review r = getReviewByUser(u);
        if (r == null) throw new NoSuchElementException("User has not submitted a review on this restaurant before");
        r.updateText(text);
        r.updateRating(i);
    }

    public void modifyReview(User u, String text) throws NoSuchElementException {
        Review r = getReviewByUser(u);
        if (r == null) throw new NoSuchElementException("User has not submitted a review on this restaurant before");
        r.updateText(text);
    }

    public void modifyReview(User u, int i) throws NoSuchElementException, InvalidInputException {
        Review r = getReviewByUser(u);
        if (r == null) throw new NoSuchElementException("User has not submitted a review on this restaurant before");
        r.updateRating(i);
    }
}