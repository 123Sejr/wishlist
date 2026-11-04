package com.example.wishlist.repository;

import com.example.wishlist.model.Wish;
import com.example.wishlist.model.WishRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WishRepository {
    private final JdbcTemplate jdbcTemplate;

    public WishRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
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
                wish.isPriority());

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
}
