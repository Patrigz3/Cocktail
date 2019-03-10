package com.roche.vnv.cocktail;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

/**
HTTP requests are handled by a controller.
These components are easily identified by the @RestController annotation, and the GreetingController below handles GET requests for /greeting
by returning a new instance of the Greeting class
 **/

@RestController //Marks the class as a controller where every method returns a domain object instead of a view.
public class GreetingController { //TODO: Discuss class naming - is this correct: HelloWorldController? Patri: No, because of the 'HelloWorld' -> the application do not return always 'Hello world', the message will depend if the user exists or not in the database. Something more general like GreetingController is better.

    private static final String template = "Hello, %s!";
    private final AtomicLong counter;
    private final UserJpaRepository userJpaRepository;

    public GreetingController(UserJpaRepository userJpaRepository) {
        counter = new AtomicLong();
        this.userJpaRepository = userJpaRepository;
    }

    //GetMapping is a composed annotation that acts as a shortcut for @RequestMapping
    @GetMapping("/greeting") //The @RequestMapping annotation ensures that HTTP requests to /greeting are mapped to the greeting() method.
    public User sayHello(@RequestParam(value="name"/*,defaultValue="Patri"*/) String name) { //We don't need default values to retrieve user info
        Optional<User> foundUser = userJpaRepository.findByName(name);
        return foundUser
                .map(user -> new User(user.getId(), user.getName(), "Welcome back!"))
                .orElse(new User(counter.decrementAndGet(), name, "Who are you " + name + "?"));
    }
}

/**
 * @RequestParam binds the value of the query string parameter name into the name parameter of the greeting() method.
 * If the name parameter is absent in the request, the defaultValue of "World" is used.
 * The implementation of the method body creates and returns a new Greeting object with id and content attributes based on the next value
 * from the counter, and formats the given name by using the greeting template.
 */

