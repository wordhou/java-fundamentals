package inheritance;

import org.junit.Test;
import static org.junit.Assert.*;

public class RestaurantTest {
    @Test
    public void constructorThrowsOnBadInput() {
        assertThrows("throws when given a bad price rating",
                InvalidInputException.class, () -> new Restaurant("Alice's Restaurant", "Cozy little diner", 100));
        assertThrows("throws when given -1 (representing no price rating)",
                InvalidInputException.class, () -> new Restaurant("Alice's Restaurant", "Cozy little diner", -1));
    }

    @Test
    public void constructorWorksOnGoodInput() throws InvalidInputException {
        new Restaurant("Alice's Restaurant", "Cozy little diner", 4);
    }
}
