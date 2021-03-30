# Inheritance and Composition Lab: Part 1

In this lab we're asked to design the classes and interfaces to represent the structures in a reviewing platform.

## Review

A review contains a body, an author, and a optional rating from 1 through 5 stars.

The rating is represented by a `int` with values from 1 through 5, or `-1` to represent a review without a rating.

The author is a value of class `User`.

The API exposes a way to change the rating of a review or the body text. The class also enforces the constraint that the
rating is either an integer from 1 through 5 or -1 by throwing an exception when invalid input is given to either the
constructor, or the modify functions.

## Place

The Place class contains its name, a description, an optional price category (a number of dollar signs from 1 through 4,
with -1 representing a null value), and reviews of the restaurant.

The reviews are stored in a HashMap from userId values (`long`) to Reviews. This enforces the constraint that a user can
only post one review for a restaurant. The constructor also enforces the constraint that the price category is an integer from 1 through 4 by throwing an exception when an invalid input is given.

The API gives us ways to get the current average star rating based on its reviews, query reviews by author, post new
reviews, and modify an existing review by an author. It also currently implements a `toString` method that condenses the
name, description, price category, and star rating in a human-readable format.

### Restaurant
A restaurant is just a subclass over the Place class that enforces the constraint that the price rating is not empty. It
is constructed with a name, a description, and a price rating that takes a value from 1 through 4.

### Shop
A shop is defined the same exactly way as a restaurant: a subclass over the Place class that enforces the constraint that
the place rating is not empty.

### Theater
The Theater class extends the Place class but adds extra functionality. It stores a listing of movies that are currently
showing at the Theater, and its API exposes `addMovie`, `removeMovie`, and `listMoviesAsString` methods.

## User

A user contains a name field of type String and an id field of type long. The id is generated when a User is constructed
for the first time. Currently, this class has no behavior or public interface other than the constructor and a `getId()` method.
