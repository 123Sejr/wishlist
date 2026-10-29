package com.example.wishlist.repository;

import com.example.wishlist.model.Wishlist;
import com.example.wishlist.model.WishlistRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WishListRepository {
    private final JdbcTemplate jdbcTemplate;

    public WishListRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Wishlist> showAllWishlistByUserId(int userId) {
        String sql = """
                SELECT id, name, wishes 
                FROM list
                WHERE list.userId = ?
                """;
        return jdbcTemplate.query(sql, new WishlistRowMapper(), userId);
    }



    public void createWishlist(Wishlist wishlist) {
        String sql = """
                INSERT INTO list (name, userId)
                VALUES (?, ?)
                """;
        jdbcTemplate.update(
                sql,
                wishlist.getName(),
                wishlist.getUserId()
        );

    }
}
