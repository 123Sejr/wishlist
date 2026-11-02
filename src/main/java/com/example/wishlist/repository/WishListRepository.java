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
                SELECT id, name
                FROM list
                WHERE list.user_ID = ?
                ORDER BY id
                """;
        return jdbcTemplate.query(sql, new WishlistRowMapper(), userId);
    }



    public void createWishlist(Wishlist wishlist) {
        String sql = """
                INSERT INTO list (name, user_ID)
                VALUES (?, ?)
                """;

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, wishlist.getName());
            ps.setInt(2, wishlist.getUserId());

            return ps;
        });

    }
}
