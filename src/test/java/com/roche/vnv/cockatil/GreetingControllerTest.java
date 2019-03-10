package com.roche.vnv.cockatil;

import com.roche.vnv.cocktail.GreetingController;
import com.roche.vnv.cocktail.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


public class GreetingControllerTest {

    private GreetingController greetingController; //?? Why private?

    @Mock
    private UserJpaRepository userJpaRepository;

    @Before
    public void setUp() {
        /**
         * Generates a clean instance for each @Test we will have
         */
        MockitoAnnotations.initMocks(this);
        this.greetingController = new GreetingController(userJpaRepository);
    }

    /**
     *
     *  When writing tests, it is common to find that several tests need similar objects created before they can run.
     *  Annotating a public void method with @Before causes that method to be run before the Test method.
     */

    /**
     * No productive code yet (GreetingController)
     * At the same time, no instance its been created (will throw NullPointerException)
     */

    @Test //Unit test. The class GreetingController and the method sayHello don't exist yet
    public void should_give_full_user_info() { //Give a proper test name - it should be used for project's documentation
//        assertEquals("Patri", greetingController.sayHello("Patri")); //based on JUnit assertEquals
        final String name = "Patri";
        final User expectedUser = new User(0, name, "Welcome back!");

        when(userJpaRepository.findByName(name)).thenReturn(Optional.of(expectedUser));

        assertThat(greetingController.sayHello(name)).isEqualTo(expectedUser); //based on assertJ

    }

    /**
     * Asserts that two objects are equal. If they are not, an AssertionError without a message is thrown.
     * If expected and actual are null , they are considered equal.
     * assertEquals(expected, actual);
     * assertThat(actual, is(equalTo(expected)));
     */

    @Test
    /**
     * public void shouldGiveAMessageWhenUserIsUnknown() {}
     */
    public void should_give_a_message_when_user_is_unknown() {
        /**
         * Follow A-A-A (Arrange-Act-Assert)
         */
        final String name = "a-not-existing-name";
        final User unknownUser = new User(-1, name, "Who are you " + name + "?");

        when(userJpaRepository.findByName(anyString())).thenReturn(Optional.empty()); //TODO: Discuss anyString() - Why should we avoid anyBlaBla() methods? Patri: Matcher methods like anyObject(), eq() do not return matchers. Internally, they record a matcher on a stack and return a dummy value (usually null). This implementation is due static type safety imposed by java compiler. The consequence is that you cannot use anyObject(), eq() methods outside of verified/stubbed method. The any family methods *don't do any type checks*, those are only here to avoid casting in your code. If you want to perform type checks use the isA(Class) method. This might however change (type checks could be added) in a future major release.

        final User actualUser = greetingController.sayHello(name);

        assertThat(actualUser).isEqualTo(unknownUser);
    }

}



