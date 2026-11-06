package com.example.wishlist.repository;

import com.example.wishlist.model.User;
import com.example.wishlist.model.UserRowMapper;
import com.example.wishlist.model.Wishlist;
import com.example.wishlist.model.WishlistRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class UserRepository {
    private final JdbcTemplate jdbcTemplate;
    private WishListRepository wishListRepository;

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

    //Finder en specifikt user ude fra dens id
    public User findUserbyId(int id) {
        String sql = """
                SELECT * FROM user
                WHERE id = ?
                """;
        return jdbcTemplate.queryForObject(sql, new UserRowMapper(), id);
    }


    public List<Wishlist> getAllWishlistByUserId(int userId) {
        String sql = """
                SELECT id, name, user_id
                FROM list
                WHERE user_id = ?
                """;
        return jdbcTemplate.query(sql, new WishlistRowMapper(), userId);
    }



    //Sletter en specifikt user ud fra dens id.
    public void deleteUser(int id) {
        User user = findUserbyId(id);
        List<Wishlist> wishlists = getAllWishlistByUserId(id);

        if(user != null) {
            for(Wishlist wishlist : wishlists) {
                jdbcTemplate.update("DELETE FROM wish WHERE list_id = ?", wishlist.getId());
            }
            jdbcTemplate.update("DELETE FROM list WHERE user_id = ?", id);
            jdbcTemplate.update("DELETE FROM user WHERE id = ?", id);
        } else {
            System.out.println("Ingen bruger med det id.");
        }

    }

    public void editUser(User user) {

        jdbcTemplate.update("UPDATE user SET name = ? WHERE id = ?", user.getName(), user.getId());


    }

}
