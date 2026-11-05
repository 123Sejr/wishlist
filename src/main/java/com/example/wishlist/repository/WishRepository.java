package com.example.wishlist.repository;

import com.example.wishlist.model.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WishRepository {
    private final JdbcTemplate jdbcTemplate;

    public WishRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createWish(Wish wish) {

        String sql = """
                BEGIN TRANSACTION;
                INSERT INTO wish (?, ?, ?, ?, ?, ?)
                COMMIT;
                """;

        // Simple update, der virker, når vi ikke har behov for at få returneret det autogen id
        // Connection og PreparedStatemnt oprettes implicit af JdbcTemplate, derfor behøver vi ikke deklarere det
        jdbcTemplate.update(
                sql,
                wish.getName(),
                wish.getDesc(),
                wish.getPrice(),
                wish.getLink(),
                wish.getImage(),
                wish.isPriority());
                //wish.getListId() ?

        /* Metoden forneden er *kun* relevant, hvis vi skal bruge det auto genererede id.
           Så fremt det viser sig vi skal bruge det, kan det kommenteres ind igen og erstatte metoden ovenfor.

           Hvis/Når vi er sikre på, at vi ikke skal bruge id'et, kan alt i denne kommentar rolig slettes.

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
                    PreparedStatement ps = connection.prepareStatement(
                            sql, Statement.RETURN_GENERATED_KEYS
                    );
                    ps.setString(1, wish.getName());
                    ps.setString(2, wish.getDesc());
                    ps.setFloat(3, wish.getPrice());
                    ps.setString(4, wish.getLink());
                    ps.setString(5, wish.getImage());
                    ps.setBoolean(6, wish.isPriority());
                    // foreing key wishlist?
                    return ps;
                }, keyHolder);
        */
    }

    public List<Wish> getAllWishesByListId(int wishlistId) {
        String sql = """
                SELECT *
                FROM wish
                WHERE list_id = ?
                ORDER BY id
                """;
        return jdbcTemplate.query(sql, new WishRowMapper(), wishlistId);
    }

    //Finder den liste som ønske er tilknyttet, så metoden getUserIdFromWishlist() kan finde brugeren, som listen er tilknyttet.
    public Wishlist getListFromWishlistId(int wishlistId) {
        String sql = """
                SELECT * FROM list
                WHERE id = ?
                """;
        return jdbcTemplate.queryForObject(sql, new WishlistRowMapper(), wishlistId);
    }

    //Finder den user som listen er tilknyttet.
    public int getUserIdFromWishlist(int wishlistId) {
        Wishlist wishlist = getListFromWishlistId(wishlistId);
        return wishlist.getUser_id();

    }


}
