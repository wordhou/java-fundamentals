package inheritance;

import java.util.*;

public class Theater extends Place {
    Set<String> movies = new HashSet<>();

    public Theater(String name, String description) throws InvalidInputException {
        super(name, description);
    }

    public void addMovie(String movie) { movies.add(movie); }

    public void removeMovie(String movie) throws NoSuchElementException {
        if (!movies.contains(movie))
            throw new NoSuchElementException(String.format("Movie %s did not exist", movie));
        movies.remove(movie);
    }

    protected String listMoviesAsString() { return String.join(", ", movies); }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(super.toString()); // Start with the Place.toString()

        if (movies.isEmpty())
            result.append(", No showings today");
        else result.append(String.format(" , Showing Today: %s", listMoviesAsString()));

        return result.toString();
    }
}
