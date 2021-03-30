package inheritance;

import org.junit.Test;

import static org.junit.Assert.*;

public class ShopTest {
    @Test
    public void constructorThrowsOnBadInput() {
        assertThrows("throws when given a bad price rating",
                InvalidInputException.class, () -> new Shop("Alice's Shop", "Cozy little thrift store", 100));
        assertThrows("throws when given -1 (representing no price rating)",
                InvalidInputException.class, () -> new Shop("Alice's Shop", "Cozy little thrift store", -1));
    }

    @Test
    public void constructorWorksOnGoodInput() throws InvalidInputException {
        new Shop("Alice's Shop", "Cozy little thrift store", 4);
    }
}