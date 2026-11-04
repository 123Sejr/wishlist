package com.example.wishlist.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WishRowMapper implements RowMapper<Wish> {
    @Override
    public Wish mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Wish(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("description"),
                rs.getInt("list_id"),
                rs.getFloat("price"),
                rs.getString("link"),
                rs.getString("image"),
                rs.getBoolean("isPriority")
        );
    }
}