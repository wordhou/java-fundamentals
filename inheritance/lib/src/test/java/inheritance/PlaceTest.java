package inheritance;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class PlaceTest {
    @Test
    public void constructorThrowsOnBadInput() {
        assertThrows("constructor throws when given a bad priceRating",
                InvalidInputException.class,
                () -> new Place("Alice's Restaurant","Cozy little diner", 0));
        assertThrows("constructor throws when given a bad priceRating",
                InvalidInputException.class,
                () -> new Place("Alice's Restaurant","Cozy little diner", -2));
        assertThrows("constructor throws when given a bad priceRating",
                InvalidInputException.class,
                () -> new Place("Alice's Restaurant","Cozy little diner", -5));
    }

    @Test
    public void getName() throws InvalidInputException {
        String name = "Alice's Restaurant";
        String description = "Place with food";
        Place p = new Place(name, description);
        assertEquals("getName gets the value name set by constructor",
                name, p.getName());
    }

    @Test
    public void setName() throws InvalidInputException {
        String name = "Alice's Restaurant";
        String description = "Place with food";
        Place p = new Place("no", "no");
        p.setName(name);
        assertEquals("setName sets the new value of name",
                name, p.getName());
    }

    @Test
    public void getDescription() throws InvalidInputException {
        String name = "Alice's Restaurant";
        String description = "Place with food";
        Place p = new Place(name, description);
        assertEquals("getName gets the value name set by constructor",
                description, p.getDescription());
    }

    @Test
    public void setDescription() throws InvalidInputException {
        String name = "Alice's Restaurant";
        String description = "Place with food";
        Place p = new Place("no", "no");
        p.setDescription(description);
        assertEquals("setDescription sets the new value of name",
                description, p.getDescription());
    }

    @Test
    public void getPriceRating() throws InvalidInputException {
        Place p = new Place("Alice's Restaurant", "Cozy little diner", 2);
        p.getPriceRating();
        assertEquals("getPriceRating gets the value of priceRating set by constructor",
                2, p.getPriceRating());
    }

    @Test
    public void getPriceRatingDefault() throws InvalidInputException {
        Place p = new Place("Alice's Restaurant", "Cozy little diner");
        assertEquals("getPriceRating gets the value of priceRating set by constructor",
                -1, p.getPriceRating());
    }

    @Test
    public void setPriceRating() throws InvalidInputException {
        Place p = new Place("Alice's Restaurant", "Cozy little diner", 2);
        p.setPriceRating(1);
        assertEquals("setPriceRating sets the value of priceRating",
                1, p.getPriceRating());
    }

    @Test
    public void setPriceRatingOnBadInput() throws InvalidInputException {
        Place p = new Place("Alice's Restaurant", "Cozy little diner", 2);
        p.setPriceRating(1);
        assertThrows("setPriceRating throws on invalid input",
                InvalidInputException.class, () -> p.setPriceRating(10));
    }

    @Test
    public void addReviewThrowsOnDuplicateReview() throws InvalidInputException, ItemAlreadyExistsException {
        Place p = new Place("Alice's Restaurant","Cozy little diner");
        User u = new User("Bob");
        p.addReview(new Review(u, "Great restaurant", 5));
        assertThrows("throws when user has already submitted review",
                ItemAlreadyExistsException.class, () ->
                        p.addReview(new Review(u, "Terrible", 1))
        );
    }

    @Test
    public void getReviewByUserWhenReviewDoesNotExist() throws InvalidInputException {
        Place p = new Place("Alice's Restaurant","Cozy little diner");
        User u = new User("Bob");
        assertNull("getReviewByUser(user) when user has not posted review",
                p.getReviewByUser(u));
    }


    @Test
    public void getReviewByUserWhenReviewExists() throws InvalidInputException, ItemAlreadyExistsException {
        Place p = new Place("Alice's Restaurant","Cozy little diner");
        User u = new User("Bob");
        Review review = new Review(u, "Great restaurant", 5);
        p.addReview(review);
        assertEquals("getReviewByUser(user) when user exists gets review",
                review, p.getReviewByUser(u));
    }

    @Test
    public void modifyReviewThatDoesNotExists() throws InvalidInputException {
        Place p = new Place("Alice's Restaurant","Cozy little diner");
        User u = new User("Bob");
        Review review = new Review(u, "actually", 3);
        assertThrows(NoSuchElementException.class, () ->
                p.modifyReview(review));
    }

    @Test
    public void modifyReviewThatExists() throws InvalidInputException, ItemAlreadyExistsException {
        Place p = new Place("Alice's Restaurant","Cozy little diner");
        User u = new User("Bob");
        p.addReview(new Review(u, "Bad", 1));
        p.modifyReview(new Review(u, "Actually pretty good", 4));
        assertEquals(p.getReviewByUser(u).getText(), "Actually pretty good");
        assertEquals(p.getReviewByUser(u).getRating(), 4);
    }

    @Test
    public void getStarRatingAveragesReviewRatings() throws InvalidInputException, ItemAlreadyExistsException {
        double delta = 1e-5;
        Place p = new Place("Alice's Restaurant", "Cozy little diner");
        p.addReview(new Review(new User("Bob"), "", 5));
        assertEquals("star rating averages review ratings", 5.0, p.getStarRating(), delta);
        p.addReview(new Review(new User("Alice"), "",4));
        p.addReview(new Review(new User("Carl"),"",3));
        p.addReview(new Review(new User("Darrel"),"",5));
        assertEquals("star rating averages review ratings", 4.25, p.getStarRating(), delta);
    }

    @Test
    public void modifyReviewIsReflectedInGetStarRating() throws InvalidInputException, ItemAlreadyExistsException {
        double delta = 1e-5;
        Place p = new Place("Alice's Restaurant", "Cozy little diner");
        User u = new User("Bob");
        Review review = new Review(u, "", 5);
        p.addReview(review);
        p.addReview(new Review(new User("Alice"), "",4));
        assertEquals("star rating averages review ratings", 4.5, p.getStarRating(), delta);
        p.getReviewByUser(u).updateRating(4);
        assertEquals("modifyReview is reflected in getStarRating",
                4, p.getStarRating(), delta);
    }

    @Test
    public void deleteReviewOnValidReview() throws Exception {
        Place p = new Place("Alice's Restaurant", "Cozy little diner");
        User u = new User("Bob");
        Review review = new Review(u, "Bad", 1);
        p.addReview(review);
        p.deleteReview(review);
        assertNull("deleted review does not exist", p.getReviewByUser(u));
    }

    @Test
    public void deleteReviewOnInvalidReview() throws Exception {
        Place p = new Place("Alice's Restaurant", "Cozy little diner");
        User u = new User("Bob");
        Review review = new Review(u, "Bad", 1);
        assertThrows("deleting review that doesn't exist throws",
                NoSuchElementException.class, () -> p.deleteReview(review));
    }

    @Test
    public void getStarRatingStringTest() throws InvalidInputException, ItemAlreadyExistsException {
        Place p = new Place("Alice's Restaurant", "Cozy little diner");
        assertEquals("star rating when restaurant has no reviews yet",
                "no reviews yet", p.getStarRatingString());
        p.addReview(new Review(new User("Alice"), "",5));
        p.addReview(new Review(new User("Bob"), "",4));
        p.addReview(new Review(new User("Carl"), "",3));
        p.addReview(new Review(new User( "Darrel"),"",5));
        p.addReview(new Review(new User("Emily"), "",3));
        assertEquals("star rating with four full stars",
                "★★★★☆", p.getStarRatingString());
        p.addReview(new Review(new User("Frank"), "",1));
        assertEquals("star rating with half stars",
                "★★★\u00bd☆", p.getStarRatingString());
    }


    @Test
    public void toStringOnPlaceWithReviews() throws InvalidInputException, ItemAlreadyExistsException {
        Place p = new Place("Hell's Kitchen", "Hip uptown diner", 4);
        p.addReview(new Review(new User("Alice"), "",5));
        p.addReview(new Review(new User("Bob"), "",4));
        p.addReview(new Review(new User("Carl"), "",3));
        p.addReview(new Review(new User( "Darrel"),"",5));
        p.addReview(new Review(new User("Emily"), "",3));
        assertEquals("star rating with full stars",
                "Name: Hell's Kitchen, Description: Hip uptown diner, Price: $$$$, Rating: ★★★★☆", p.toString());
        p.addReview(new Review(new User("Frank"), "",1));
        assertEquals("star rating with half stars",
                "Name: Hell's Kitchen, Description: Hip uptown diner, Price: $$$$, Rating: ★★★\u00bd☆", p.toString());
    }

    @Test
    public void toStringOnPlaceWithoutReviews() throws InvalidInputException {
        Place p = new Place("Alice's Restaurant", "Cozy little diner", 2);
        assertEquals("Name: Alice's Restaurant, Description: Cozy little diner, Price: $$, Rating: no reviews yet", p.toString());
    }

    @Test
    public void toStringOnPlaceWithEmptyDescription() throws InvalidInputException, ItemAlreadyExistsException {
        Place p = new Place("Hell's Kitchen", "", 4);
        assertFalse("toString has no description field when description is empty",
                p.toString().matches("Description:"));
    }

    @Test
    public void toStringOnPlaceWithNoStarRating() throws InvalidInputException, ItemAlreadyExistsException {
        Place p = new Place("Hell's Kitchen", "Hip uptown diner");
        assertFalse("toString has no rating field when description is empty",
                p.toString().matches("Rating:"));
        p = new Place("Hell's Kitchen", "Hip uptown diner", -1);
        assertFalse("toString has no rating field when description is empty",
                p.toString().matches("Rating:"));
    }

}