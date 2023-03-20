package com.example.homework_50.dao;

import com.example.homework_50.entity.User;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
@Component
public class UserDao extends BaseDao{
    public UserDao(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super(jdbcTemplate, namedParameterJdbcTemplate);
    }

    @Override
    public void createTable() {
        jdbcTemplate.execute("create table if not exists usr\n" +
                "(\n" +
                "    email varchar primary key,\n" +
                "    name  varchar not null,\n" +
                "    username  varchar not null,\n" +
                "    password  varchar not null,\n" +
                "    counter  int default 0\n" +
                ");");
    }

    public Optional<User> findByEmail(String email) {
        String sql = "select * " +
                "from usr " +
                "where email = ?";
        return Optional.ofNullable(DataAccessUtils.singleResult(
                jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class), email)
        ));
    }
    public Optional<User> findByName(String name) {
        String sql = "select * " +
                "from usr " +
                "where name = ?";
        return Optional.ofNullable(DataAccessUtils.singleResult(
                jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class), name)
        ));
    }
    public Optional<User> findByUserName(String username) {
        String sql = "select * " +
                "from usr " +
                "where username = ?";
        return Optional.ofNullable(DataAccessUtils.singleResult(
                jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class), username)
        ));
    }

    public void deleteAll() {
        String sql = "delete from usr";
        jdbcTemplate.update(sql);
    }

    public void saveAll(List<User> users) {
        String sql = "insert into usr(email, name,username, password,counter) " +
                "values(?,?,?,?,?)";

        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setString(1, users.get(i).getEmail());
                ps.setString(2, users.get(i).getName());
                ps.setString(3, users.get(i).getUsername());
                ps.setString(4,users.get(i).getPassword());
                ps.setInt(5,users.get(i).getCounter());
            }

            public int getBatchSize() {
                return users.size();
            }
        });
    }
}
