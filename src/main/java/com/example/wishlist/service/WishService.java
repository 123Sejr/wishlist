package com.example.wishlist.service;

import com.example.wishlist.exception.DatabaseOperationException;
import com.example.wishlist.exception.DuplicateIdException;
import com.example.wishlist.exception.InvalidInputException;
import com.example.wishlist.model.Wish;
import com.example.wishlist.repository.WishRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.regex.Pattern;

@Service
public class WishService {
    private final WishRepository repo;

    public WishService(WishRepository repo) {
        this.repo = repo;
    }

    // Denne annotation ruller alle ændringer tilbage, hvis en eneste del af metoden fejler
    // https://dev.to/gianfcop98/transactions-in-spring-boot-what-transactional-really-does-and-why-it-matters-56a6
    @Transactional
    public void createWish(Wish wish){
        if (wish.getLink() != null) {validateLink(wish.getLink());} // hvis link er udfyldt, tjekker om det er en url
        if (wish.getImage() != null) {validateLink(wish.getImage());} // hvis image er udfyldt, tjekker om det er en url

        try {
            repo.createWish(wish);
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateIdException("Id'et" + wish.getId() + "eksisterer allerede.", e);
        } catch (DataAccessException e){
            throw new DatabaseOperationException("Ønske kunne ikke oprettes.", e);

        }
    }

    // tjekker om et link rent faktisk er et link (eller i hvert fald om det er sandsynligt det er det)
    // regular expression fra https://www.geeksforgeeks.org/dsa/check-if-an-url-is-valid-or-not-using-regular-expression/
    public void validateLink(String link){
        final Pattern URL_PATTERN = Pattern.compile("((http|https)://)(www.)?"
                + "[a-zA-Z0-9@:%._+~#?&/=]"
                + "{2,256}\\.[a-z]"
                + "{2,6}\\b([-a-zA-Z0-9@:%"
                + "._+~#?&/=]*)");

        if (!URL_PATTERN.matcher(link).matches()) {
            throw new InvalidInputException("Link kan ikke genkendes.");
        }
    }

    public List<Wish> getAllWishesByListId(int wishlistId) {
        return repo.getAllWishesByListId(wishlistId);
    }
}
