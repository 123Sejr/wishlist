// Dette er en integrationstest.
// Denne testklasse tester UserRepository i samspil med Spring Boot
// og databasen.
// Vi bruger det rigtige repository og JdbcTemplate til at udføre
// SQL-forespørgsler mod databasen.
// Vi tester dermed, om repository-metoderne kan indsætte og hente
// brugere fra databasen.



package com.example.wishlist.service.repository;

import com.example.wishlist.model.User;
import com.example.wishlist.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;


import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

// @SpringBootTest starter Spring Boot-applikationens
// applikationskontekst, så vi kan teste de rigtige Spring-komponenter.
@SpringBootTest

// Angiver, at vi skal bruge dev-profilen og dermed konfigurationen
// fra application-dev.properties.
@ActiveProfiles("dev")
class WishlistRepositoryTest {

    // Henter den rigtige UserRepository-instans fra Spring,
    // så vi kan teste repository-metoderne.
    @Autowired
    private UserRepository repo;

    @Test
    void addUserTest() {
        // Opretter en testbruger.
        User user = new User();
        user.setName("Lilja");

        // Kalder repository-metoden, som indsætter brugeren i databasen.
        repo.addUser(user);

        // Henter alle brugere fra databasen.
        List<User> allUsers = repo.getAllUsers();

        // Kontrollerer, at den nye bruger findes i resultatet.
        assertThat(allUsers).extracting(User::getName).contains("Lilja");
    }

    @Test
    void getAllUsersTest() {
         List<User> allUsers = repo.getAllUsers();

        assertThat(allUsers).isNotNull();
        assertThat(allUsers.get(0).getName()).isEqualTo("Amanda");
        assertThat(allUsers.get(1).getName()).isEqualTo("Sejr");
    }


}
