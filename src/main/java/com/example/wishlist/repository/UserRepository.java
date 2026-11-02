package com.example.wishlist.repository;

import com.example.wishlist.model.User;
import com.example.wishlist.model.UserRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class UserRepository {
    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addUser(User user) {

        String sql = "INSERT INTO user (name) VALUES (?)";

        jdbcTemplate.update(connection -> {

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, user.getName());

            return ps;
        });

    }

    public List<User> getAllUsers() {
        String sql = "SELECT * FROM user";

        return jdbcTemplate.query(sql, new UserRowMapper());
    }
}
