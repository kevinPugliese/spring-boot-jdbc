package com.jdbc.kevin.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PersonJdbcRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    class PersonRowMapper implements RowMapper<Person> {
        @Override
        public Person mapRow(ResultSet rs, int i) throws SQLException {
            Person person = new Person();
            person.setId(rs.getLong("id"));
            person.setName(rs.getString("name"));
            person.setEmail(rs.getString("email"));
            return person;
        }
    }

    public List<Person> findAll() {
        return jdbcTemplate.query("select * from person", new PersonRowMapper());
    }

    public Person findById(long id) {
        return jdbcTemplate.queryForObject("select * from person where id=?", new Object[] { id },
                new BeanPropertyRowMapper<Person>(Person.class));
    }

    public int deleteById(long id) {
        return jdbcTemplate.update("delete from person where id=?", new Object[] { id });
    }

    public int insert(Person person) {
        return jdbcTemplate.update("insert into person(id, name, email) " + "values(?, ?, ?)",
                new Object[] { person.getId(), person.getName(), person.getEmail()});
    }

    public int update(Person person) {
        return jdbcTemplate.update("update person " + " set name= ?, email = ? " + " where id = ?",
                new Object[] { person.getName(), person.getEmail(), person.getId() });
    }
}
