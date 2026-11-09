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

    //Finder et specifikt ønske ud fra dens id.
    public Wish getWishById(int id) {
        String sql = """
                SELECT * FROM wish
                WHERE id = ?
                """;

        return jdbcTemplate.queryForObject(sql, new WishRowMapper(), id);
    }

    public void createWish(Wish wish, int wishlistId) {

        String sql = """
                INSERT INTO wish (name, description, list_id, price, link, image, isPriority)
                VALUES (?, ?, ?, ?, ?, ?, ?)
                """;

        // Simple update, der virker, når vi ikke har behov for at få returneret det autogen id
        // Connection og PreparedStatemnt oprettes implicit af JdbcTemplate, derfor behøver vi ikke deklarere det
        jdbcTemplate.update(
                sql,
                wish.getName(),
                wish.getDesc(),
                wishlistId,
                wish.getPrice(),
                wish.getLink(),
                wish.getImage(),
                wish.getIsPriority());
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


    //Sletter et ønske ud fra dens id.
    public void deleteWish(int wishId) {
        Wish wish = getWishById(wishId);

        if(wish != null) {
            jdbcTemplate.update("DELETE FROM wish WHERE id = ?", wishId);
        } else {
            System.out.println("Ingen ønsker med det id.");
        }
    }

    public void editWish(Wish wish){

        String sql = """
                UPDATE wish
                SET name = ?,
                description = ?,
                price = ?,
                link = ?,
                image = ?,
                isPriority = ?
                WHERE id = ?
                """;

        jdbcTemplate.update(
                sql,
                wish.getName(),
                wish.getDesc(),
                wish.getPrice(),
                wish.getLink(),
                wish.getImage(),
                wish.getIsPriority(),
                wish.getId());
    }

    public String findWishListNameById(int wishlistId){
        String sql = """
                SELECT name FROM list
                WHERE id = ?
                """;

        return jdbcTemplate.queryForObject(sql, String.class, wishlistId);
    }
}
