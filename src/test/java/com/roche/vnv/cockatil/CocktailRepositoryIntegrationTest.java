package com.roche.vnv.cockatil;
import org.junit.Test;
import org.junit.Before;
import org.mockito.Mock;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class) //@RunWith(SpringRunner.class) is used to provide a bridge between Spring Boot test features and JUnit. Whenever we are using any Spring Boot testing features in out JUnit tests, this annotation will be required.
@DataJpaTest //@DataJpaTest provides some standard setup needed for testing the persistence layer:

public class CocktailRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager; //To carry out some DB operation, we need some records already setup in our database. To setup such data, we can use TestEntityManager.

    @Autowired
    private CocktailRepository cocktailRepository; //cocktailRepository is what we are going to test

    // write test cases here

    @Before
    public void setUp() {
        Cocktail margarita = new Cocktail("margarita");

        Mockito.when(employeeRepository.findByName(margarita.getName()))
                .thenReturn(margarita);
    }

    @Test
    public void whenValidName_thenCocktailShouldBeFound() {
        String name = "margarita";
        Cocktail found = cocktailService.getEmployeeByName(name);

        assertThat(found.getName())
                .isEqualTo(name);
    }


}


}

