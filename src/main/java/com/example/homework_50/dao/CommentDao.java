package com.example.homework_50.dao;

import com.example.homework_50.entity.Comment;
import com.example.homework_50.entity.Publication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.PreparedStatement;
@Component
public class CommentDao extends BaseDao{
    public CommentDao(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super(jdbcTemplate, namedParameterJdbcTemplate);
    }

    @Override
    public void createTable() {
        jdbcTemplate.execute("create table if not exists comments\n" +
                "(\n" +
                "    id    bigserial primary key," +
                "    author varchar,\n" +
                "    foreign key (author) references usr (email),\n" +
                "    text  varchar not null,\n" +
                "    timeOfComment  DATE not null\n" +
                ");");
    }
    public void deleteAll() {
        String sql = "delete from comments";
        jdbcTemplate.update(sql);
    }
    public void save(Comment comment) {
        String sql = "insert into comments(author, text, timeOfComment) " +
                "values(?,?,?)";
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, comment.getAuthor());
            ps.setString(2, comment.getText());
            ps.setDate(3, (Date) comment.getTimeOfComment());
            return ps;
        });
    }
}
