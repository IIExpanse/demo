package com.example.demo.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MyRepository {
    private final JdbcTemplate template;
    private final String INSERT = "INSERT INTO strings(string) VALUES (?)";
    private final String SELECT = "SELECT string FROM strings WHERE id = ?";

    public int storeString(String s) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        template.update(con -> {
            PreparedStatement ps = con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, s);
            return ps;
        }, keyHolder);

        if (keyHolder.getKey() == null) {
            throw new RuntimeException();
        }
        return keyHolder.getKey().intValue();
    }

    public Optional<String> getStoredString(int id) {
        List<String> res = new ArrayList<>();

        template.query(con -> {
            PreparedStatement ps = con.prepareStatement(SELECT);
            ps.setInt(1, id);
            return ps;
        }, rs -> {
            res.add(rs.getString(1));
        });

        return res.isEmpty() ? Optional.empty() : Optional.of(res.get(0));
    }
}
