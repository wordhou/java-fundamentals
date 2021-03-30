package inheritance;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class Review {
    User user;
    int rating;
    @Nonnull
    String text;
    @Nullable
    String movie;

    public Review(User user, String text) throws InvalidInputException {
        this(user, text, -1, null);
    }

    public Review(User user, String text, int rating) throws InvalidInputException {
        this(user, text, rating, null);
    }

    public Review(User user, String text, int rating, String movie) throws InvalidInputException {
        this.user = user;
        this.text = text;
        validateRating(rating);
        this.rating = rating;
    }

    public int getRating() {
        return rating;
    }

    public void updateText(String text) {
        this.text = text;
    }

    public void updateRating(int rating) throws InvalidInputException {
        validateRating(rating);
        this.rating = rating;
    }

    private void validateRating(int num) throws InvalidInputException {
        if (num != -1 && num < 0 || num > 5) throw new InvalidInputException("rating must be between 0 and 5");
    }

    public void removeRating() {
        rating = -1;
    }

    public boolean hasRating() {
        return rating != -1;
    }

    public long getUserId() {
        return this.user.getId();
    }

    public String getText() {
        return this.text;
    }
}