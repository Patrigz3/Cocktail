package com.roche.vnv.cockatil;

@RestController
@RequestMapping("/api")

public class CocktailControllerTest {

    @Autowired
    private ControllerService controllerService;

    @GetMapping("/cocktails")
    public List<Cocktails> getAllCocktails() {
        return cocktailService.getAllCocktails();
    }
}

//Since we are only focused on the Controller code, it is natural to mock the Service layer code for our unit tests: