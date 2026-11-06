package com.example.wishlist.service.repository;


import com.example.wishlist.model.User;
import com.example.wishlist.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

@SpringBootTest
@ActiveProfiles("test")
@Sql(scripts = "classpath:h2init.sql", executionPhase = BEFORE_TEST_METHOD)
class UserRepositoryH2Test {

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
        assertThat(allUsers)
                .extracting(User::getName)
                .contains("Maja", "Oliver", "Freja");
    }
}
