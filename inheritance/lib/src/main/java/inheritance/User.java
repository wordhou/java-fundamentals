package inheritance;

import java.util.Date;

import static java.lang.Long.parseLong;

public class User {
    String name;
    long id;

    public User(String name) {
        id = newId();
        this.name = name;
    }

    /** Generates a (probably) unique ID */
    static private long newId() {
        Date now = new Date();
        String idString = "" + Math.round(10000 * Math.random()) + now.getTime();
        return parseLong(idString);
    }

    public long getId() {
        return id;
    }
}
