package inheritance;

import org.junit.Test;
import static org.junit.Assert.*;

public class ReviewTest {
    @Test
    public void constructorThrowsOnBadInput () {
        assertThrows("Review constructor throws on bad number of stars",
                InvalidInputException.class, () -> new Review(new User("Bob"), "Name", 100));
    }

    @Test
    public void getRatingGetsRating () throws InvalidInputException {
        assertEquals("getter gets rating",
                3, new Review(new User("Bob"), "Name", 3).getRating());
    }

    @Test
    public void getTextGetsText () throws InvalidInputException {
        assertEquals("getter gets rating",
                "Words", new Review(new User("Bob"), "Words", 3).getText());
    }

    @Test
    public void updateTextOnValidInput () throws InvalidInputException {
        Review r = new Review(new User("Bob"), "Bad restaurant", 4);
        String newText = "actually pretty good";
        r.updateText(newText);
        assertEquals("update text changes text",
                newText, r.getText());
    }

    @Test
    public void updateRatingThrowsOnBadInput() throws InvalidInputException {
        Review r = new Review(new User("Bob"), "Bad restaurant", 4);
        assertThrows("Review constructor throws on bad number of stars",
                InvalidInputException.class, () -> r.updateRating(-100));
    }

    @Test
    public void updateRatingOnValidInput () throws InvalidInputException {
        Review r = new Review(new User("Bob"), "Bad restaurant", 4);
        r.updateRating(5);
        assertEquals("update rating changes rating",
                5, r.getRating());
    }

    @Test
    public void hasRatingOnConstructorWithNoReview() throws InvalidInputException {
        Review r = new Review(new User("Bob"), "great restaurant");
        assertFalse("hasRating on a review without rating", r.hasRating());
    }

    @Test
    public void hasRatingOnConstructorWithReview() throws InvalidInputException {
        Review r = new Review(new User("Bob"), "great restaurant", 5);
        assertTrue("hasRating on a review without rating", r.hasRating());
    }

    @Test
    public void removeRating() throws InvalidInputException {
        Review r = new Review(new User("Bob"), "great restaurant", 5);
        r.removeRating();
        assertFalse("hasRating on a review without rating", r.hasRating());
    }
}
