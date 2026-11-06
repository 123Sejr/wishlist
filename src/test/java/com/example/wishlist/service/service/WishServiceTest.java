package com.example.wishlist.service.service;

import com.example.wishlist.exception.InvalidInputException;
import com.example.wishlist.repository.WishRepository;
import com.example.wishlist.service.WishService;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.junit.jupiter.api.Assertions.*;

class WishServiceTest {
    private final WishService service = new WishService(new WishRepository(new JdbcTemplate()));

    @Test
    void testValidateLink() {

        //tester om et link, der bør være validt, rent faktisk er det (altså ikke smider en exception)
        String goodLink = "https://www.ek.dk";
        assertDoesNotThrow(() -> service.validateLink(goodLink));

        //
        String badLink = "1læ1kwklmel1ej330";
        assertThrows(InvalidInputException.class, () -> service.validateLink(badLink));
    }
}