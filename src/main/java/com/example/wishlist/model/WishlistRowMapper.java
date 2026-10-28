package com.example.wishlist.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WishlistRowMapper implements RowMapper<Wishlist> {
    @Override
    public Wishlist mapRow(ResultSet rs, int rowNum) throws SQLException {
        String wishNames = rs.getString("wish_names");

        ArrayList<String> wishes = new ArrayList<>(List.of(wishNames.split(",")));

        return new Wishlist(
              rs.getInt(""),
              rs.getString(""),
              rs.getInt(""),
              wishes
        );

    }
}
