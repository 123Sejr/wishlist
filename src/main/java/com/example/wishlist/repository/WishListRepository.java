package com.example.wishlist.repository;

import com.example.wishlist.model.Wishlist;
import com.example.wishlist.model.WishlistRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class WishListRepository {
    private final JdbcTemplate jdbcTemplate;

    public WishListRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Wishlist> showAllWishlistByUserId(int userId) {
        String sql = """
                SELECT id, name, user_ID
                FROM list
                WHERE user_id = ?
                ORDER BY id
                """;
        return jdbcTemplate.query(sql, new WishlistRowMapper(), userId);
    }


    //Opretter en ny ønskeliste, tilknyttet et bestemt bruger id.
    public void createWishlist(Wishlist wishlist, int user_id) {
        String sql = """
                INSERT INTO list (name, user_id)
                VALUES (?, ?)
                """;

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, wishlist.getName());
            ps.setInt(2, user_id);

            return ps;
        });

    }


    //Finder en specifikt liste ud fra dens id.
    public Wishlist findWishListById(int wishlistId) {
        String sql = """
                SELECT * FROM list
                WHERE id = ?
                """;
        return jdbcTemplate.queryForObject(sql, new WishlistRowMapper(), wishlistId);
    }


    //Sletter en liste, samt dens ønsker, ud fra listens id.
    public void deleteWishlist(int wishlistId) {
        Wishlist wishlist = findWishListById(wishlistId);

        if(wishlist != null) {
            jdbcTemplate.update("DELETE FROM wish WHERE list_id = ?", wishlistId);
            jdbcTemplate.update("DELETE FROM list WHERE id = ?", wishlistId);
        } else {
            System.out.println("Ingen liste med det id.");
        }
    }
}
