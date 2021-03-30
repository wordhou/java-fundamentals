package inheritance;

import javax.annotation.Nullable;
import java.util.*;

public class Place {
    String name;
    String description;
    int priceRating;
    Map<Long, Review> reviews = new HashMap<>();

    static final char FULL_STAR = '\u2605';
    static final char HALF_STAR = '\u00bd';
    static final char NO_STAR = '\u2606';

    public Place(String name, String description) throws InvalidInputException {
        this(name, description, -1);
    }

    public Place(String name, String description, int priceRating) throws InvalidInputException {
        validatePriceRating(priceRating);
        this.priceRating = priceRating;
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPriceRating() { return priceRating; }

    public void setPriceRating(int priceRating) throws InvalidInputException {
        validatePriceRating(priceRating);
        this.priceRating = priceRating;
    }

    protected void validatePriceRating(int priceRating) throws InvalidInputException {
        if (priceRating != -1 && priceRating < 1 || priceRating > 4)
            throw new InvalidInputException("Price rating must be a number between 1 and 4");
    }

    protected String getPriceRatingString() {
        return "$".repeat(getPriceRating());
    }

    public void addReview(Review review) throws ItemAlreadyExistsException {
        if (reviews.containsKey(review.getUserId()))
            throw new ItemAlreadyExistsException("");
        reviews.put(review.getUserId(), review);
    }

    public void modifyReview(Review review) throws NoSuchElementException {
        Review r = getReviewByUser(review.user);
        if (r == null) throw new NoSuchElementException("User has not submitted a review on this restaurant before");
        reviews.put(review.getUserId(), review);
    }

    public void deleteReview(Review review) throws NoSuchElementException {
        Review r = getReviewByUser(review.user);
        if (r == null) throw new NoSuchElementException("User has not submitted a review on this restaurant before");
        reviews.remove(review.getUserId());
    }

    @Nullable
    public Review getReviewByUser(User u) {
        return reviews.get(u.id);
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

    /**
     * Pretty prints the rating of a restaurant as a string of full, half and empty stars
     */
    protected String getStarRatingString() {
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

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(String.format("Name: %s", getName()));
        if (this.description != "")
            result.append(String.format(", Description: %s", getDescription()));
        if (this.priceRating != -1)
            result.append(String.format(", Price: %s", getPriceRatingString()));
        result.append(String.format(", Rating: %s", getStarRatingString()));
        return result.toString();
    }
}