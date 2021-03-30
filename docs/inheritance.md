# Inheritance: Part 1

In this lab we're asked to design the classes and interfaces to represent reviews about restaurants.

## Review

A review contains a body, an author, and a optional rating from 1 through 5 stars.

The rating is represented by a `int` with values from 1 through 5, or `-1` to represent a review without a rating.

The author is a value of class `User`.

The API exposes a way to change the rating of a review or the body text. The class also enforces the constraint that the rating is either an integer from 1 through 5 or -1 by throwing an exception when invalid input is given to either the constructor or the modify functions.

## User

A user contains a name field of type String and an id field of type long. The id is generated when a User is constructed for the first time. Currently this class has no behavior or public interface other than the constructor and a `getId()` method.

## Restaurant

The Restaurant class contains its name, a price category (a number of dollar signs from 1 through 4), and reviews of the restaurant. The reviews are stored in a HashMap from userId values (`long`) to Reviews. This enforces the constraint that a user can only post one review for a restaurant. The constructor also enforces the constraint that the price category is an integer from 1 through 4 by throwing an exception when an invalid input is given.

The API gives us ways to get the current average star rating based on its reviews, query reviews by author, post new reviews, and modify an existing review by an author. It also currently implements a `toString` method that condenses the name, price category, and star rating in a human readable format.
