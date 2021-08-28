# technical-challenge-202108

A technical challenge that consists of creating a REST API in the language you feel more comfortable with.

The service supports two endpoints

- An endpoint should display the typical "Hello, World."

- An endpoint is open to your consideration.

Add relevant unit testing following best practices from the selected language.

## How to run

This project is built with `Java 8, Spring Boot & Maven`.

Simply open this project with IntelliJ IDEA (or any other Java IDE).
Run `mvn clean install`, then run the main class TechnicalChallengeApplication.

## Usage

On execution, the application was initialized with port `8080` (HTTP).

You can now access the 2 endpoints in the requirements above

`- localhost:8080/hello` -> return "Hello, World."

`- localhost:8080/fact/trivia/{number}` -> return a trivia fact of the requested number.
This REST API pointing to a free API performed by [rapidapi.com](https://rapidapi.com/), you can figure out it [here](https://rapidapi.com/divad12/api/numbers-1/).

## Contributing
Pull requests and feedbacks are welcome. Feel free to contact me via my email thibault2705@gmail.com.