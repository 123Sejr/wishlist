// Dette er en unit test (enhedstest).
// Denne testklasse tester UserService isoleret fra resten af applikationen.
// UserRepository erstattes af et mockobjekt ved hjælp af Mockito,
// så vi kan kontrollere, hvordan UserService reagerer på forskellige
// svar fra repository uden at kommunikere med en database.
// Vi tester dermed servicens logik og håndtering af repository-kald.
// Der testes ikke controllere, og der anvendes ikke Spring MVC Test
// eller Spring Boot til at starte applikationskonteksten.



package com.example.wishlist.service.service;

import com.example.wishlist.model.User;
import com.example.wishlist.repository.UserRepository;
import com.example.wishlist.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.*;


//@ExtendWith er en JUnit annotation,
//den fortæller Junit at bruge en extension når den kører den klasse.
//MockitoExtension.class definerer at den extension skal være Mockito's JUnit integration.
@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    // Opretter et mockobjekt af UserRepository.
    // Mockobjektet simulerer det rigtige repository, men udfører ikke
    // SQL-forespørgsler eller kommunikerer med databasen.
    @Mock
    private UserRepository userRepository;

    // Opretter en instans af UserService og injicerer de mockobjekter,
    // som klassen er afhængig af (i dette tilfælde UserRepository).
    // Dermed kan vi teste den rigtige serviceklasse uden at være
    // afhængige af repository eller database.
    @InjectMocks
    private UserService userService;

    @Test
    void shouldGetAllUsers() {
        User user1 = new User();
        User user2 = new User();

        user1.setName("Amanda");
        user2.setName("Sejr");

        List<User> users = List.of(user1, user2);

        // Fortæller Mockito, at når getAllUsers() kaldes på det mockede
        // repository, skal metoden returnere vores liste med testbrugere.
        when(userRepository.getAllUsers()).thenReturn(users);

        // Kald metoden i den rigtige serviceklasse
        List<User> result = userService.getAllUsers();

        // Kontroller resultatet
        assertThat(result).containsExactlyElementsOf(users);

        // Kontroller at repository-metoden blev kaldt
        verify(userRepository).getAllUsers();
    }

    @Test
    void shouldAddUser() {
        User user1 = new User();

        user1.setName("Dan");

        userService.addUser(user1);

        verify(userRepository).addUser(user1);
    }


}