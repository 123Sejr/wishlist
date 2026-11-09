package com.example.wishlist.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WishlistRowMapper implements RowMapper<Wishlist> {
    @Override
    public Wishlist mapRow(ResultSet rs, int rowNum) throws SQLException {

        return new Wishlist(
              rs.getInt("id"),
              rs.getString("name"),
              rs.getInt("user_id")
        );

    }
}
