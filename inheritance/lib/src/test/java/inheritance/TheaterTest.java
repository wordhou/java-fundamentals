package inheritance;

import org.junit.Test;

import java.util.Arrays;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class TheaterTest {

    @Test
    public void addMovie() throws InvalidInputException {
        Theater t = new Theater("Skyway Cinema", "From the classics to the blockbusters");
        t.addMovie("ABC");
        t.listMoviesAsString().contains("ABC");
        assertTrue("added movie is listed",
                t.listMoviesAsString().contains("ABC"));
    }

    @Test
    public void addManyMovies() throws InvalidInputException {
        String[] movies = {"ABC", "DEF", "GHI", "JKL", "MNO"};
        Theater t = new Theater("Skyway Cinema", "From the classics to the blockbusters");
        Arrays.stream(movies).forEach(t::addMovie);
        assertTrue("all added movies are listed",
                Arrays.stream(movies).allMatch(m -> t.listMoviesAsString().contains(m)));
    }

    @Test
    public void removeMovie() throws InvalidInputException {
        String[] movies = {"ABC", "DEF", "GHI", "JKL", "MNO"};
        Theater t = new Theater("Skyway Cinema", "From the classics to the blockbusters");
        Arrays.stream(movies).forEach(t::addMovie);
        t.removeMovie("GHI");
        assertFalse("deleted movie is not listed",
                t.listMoviesAsString().contains("GHI"));
    }

    @Test
    public void removeMovieNotInMovies() throws InvalidInputException {
        String[] movies = {"ABC", "DEF", "GHI", "JKL", "MNO"};
        Theater t = new Theater("Skyway Cinema", "From the classics to the blockbusters");
        Arrays.stream(movies).forEach(t::addMovie);
        assertThrows("deleting movie that doesn't exist throws",
                NoSuchElementException.class, () -> t.removeMovie("PQR"));
    }
}