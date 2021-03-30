package inheritance;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class RestaurantTest {
    @Test
    public void constructorThrowsOnBadInput() {
        assertThrows("throws when given a bad star rating",
                InvalidInputException.class, () -> new Restaurant("Alice's Restaurant", 0));
    }

    @Test
    public void constructorWorksOnGoodInput() throws InvalidInputException {
        new Restaurant("Alice's Restaurant", 1);
    }

    @Test
    public void getNameGetsName() throws InvalidInputException {
        String name = "Alice's Restaurant";
        Restaurant r = new Restaurant(name,1);
        assertEquals("getName gets name", name, r.getName());
    }

    @Test
    public void addReviewThrowsOnDuplicateReview() throws InvalidInputException, ItemAlreadyExistsException {
        Restaurant r = new Restaurant("Alice's Restaurant",1);
        User u = new User("Bob");
        r.addReview(new Review(u, "Great restaurant", 5));
        assertThrows("throws when user has already submitted review",
                ItemAlreadyExistsException.class, () ->
                        r.addReview(new Review(u, "Terrible", 1))
        );
    }

    @Test
    public void getReviewByUserWhenReviewDoesNotExist() throws InvalidInputException {
        Restaurant r = new Restaurant("Alice's Restaurant",1);
        User u = new User("Bob");
        assertNull("getReviewByUser(user) when user has not posted review",
                r.getReviewByUser(u));
    }


    @Test
    public void getReviewByUserWhenReviewExists() throws InvalidInputException, ItemAlreadyExistsException {
        Restaurant restaurant = new Restaurant("Alice's Restaurant",1);
        User u = new User("Bob");
        Review review = new Review(u, "Great restaurant", 5);
        restaurant.addReview(review);
        assertEquals("getReviewByUser(user) when user exists gets review",
                review, restaurant.getReviewByUser(u));
    }

    @Test
    public void modifyReviewThatDoesNotExists() throws InvalidInputException {
        Restaurant r = new Restaurant("Alice's Restaurant",1);
        User u = new User("Bob");
        assertThrows(NoSuchElementException.class, () ->
                r.modifyReview(u, "Actually pretty good", 4));
    }
    @Test
    public void modifyReviewThatExists() throws InvalidInputException, ItemAlreadyExistsException {
        Restaurant r = new Restaurant("Alice's Restaurant",1);
        User u = new User("Bob");
        r.addReview(new Review(u, "Bad", 1));
        r.modifyReview(u, "Actually pretty good", 4);
        assertEquals(r.getReviewByUser(u).getText(), "Actually pretty good");
        assertEquals(r.getReviewByUser(u).getRating(), 4);
    }

    @Test
    public void modifyReviewJustText() throws InvalidInputException, ItemAlreadyExistsException {
        Restaurant r = new Restaurant("Alice's Restaurant",1);
        User u = new User("Bob");
        r.addReview(new Review(u, "Bad", 1));
        r.modifyReview(u, "Actually pretty good");
        assertEquals(r.getReviewByUser(u).getText(), "Actually pretty good");
        assertEquals(r.getReviewByUser(u).getRating(), 1);
    }

    @Test
    public void modifyReviewJustRating() throws InvalidInputException, ItemAlreadyExistsException {
        Restaurant r = new Restaurant("Alice's Restaurant",1);
        User u = new User("Bob");
        r.addReview(new Review(u, "Bad", 1));
        r.modifyReview(u, 4);
        assertEquals(r.getReviewByUser(u).getText(), "Bad");
        assertEquals(r.getReviewByUser(u).getRating(), 4);
    }

    @Test
    public void getStarRatingAveragesReviewRatings() throws InvalidInputException, ItemAlreadyExistsException {
        double delta = 1e-5;
        Restaurant r = new Restaurant("Alice's Restaurant", 1);
        r.addReview(new Review(new User("Bob"), "", 5));
        assertEquals("star rating averages review ratings", 5.0, r.getStarRating(), delta);
        r.addReview(new Review(new User("Alice"), "",4));
        r.addReview(new Review(new User("Carl"),"",3));
        r.addReview(new Review(new User("Darrel"),"",5));
        assertEquals("star rating averages review ratings", 4.25, r.getStarRating(), delta);
    }

    @Test
    public void modifyReviewIsReflectedInGetStarRating() throws InvalidInputException, ItemAlreadyExistsException {
        double delta = 1e-5;
        Restaurant r = new Restaurant("Alice's Restaurant", 1);
        User u = new User("Bob");
        r.addReview(new Review(u, "",5));
        r.addReview(new Review(new User("Alice"), "",4));
        assertEquals("star rating averages review ratings", 4.5, r.getStarRating(), delta);
        r.modifyReview(u, 4);
        assertEquals("modifyReview is reflected in getStarRating",
                4, r.getStarRating(), delta);
    }

    @Test
    public void toStringOnRestaurantWithReviews() throws InvalidInputException, ItemAlreadyExistsException {
        Restaurant r = new Restaurant("Alice's Restaurant", 4);
        r.addReview(new Review(new User("Alice"), "",5));
        r.addReview(new Review(new User("Bob"), "",4));
        r.addReview(new Review(new User("Carl"), "",3));
        r.addReview(new Review(new User( "Darrel"),"",5));
        r.addReview(new Review(new User("Emily"), "",3));
        assertEquals("star rating with full stars",
                "Name: Alice's Restaurant, Price: $$$$, Rating: ★★★★☆", r.toString());
        r.addReview(new Review(new User("Frank"), "",1));
        assertEquals("star rating with half stars",
                "Name: Alice's Restaurant, Price: $$$$, Rating: ★★★\u00bd☆", r.toString());
    }

    @Test
    public void toStringOnNewRestaurant() throws InvalidInputException {
        Restaurant r = new Restaurant("Alice's Restaurant", 1);
        assertEquals("Name: Alice's Restaurant, Price: $, Rating: no reviews yet", r.toString());
    }
}
