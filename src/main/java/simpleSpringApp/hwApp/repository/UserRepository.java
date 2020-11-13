package simpleSpringApp.hwApp.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import simpleSpringApp.hwApp.model.User;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {


    private final JdbcTemplate jdbcTemplate;

    private final String FIND_ALL = "SELECT * FROM users";
    private final String INSERT_USER = "INSERT INTO users (FIRST_NAME, LAST_NAME)   values (?,?) ";
    private final String UPDATE_USER = "UPDATE users set FIRST_NAME=?, LAST_NAME =? WHERE id = ?";
    private final String DELETE_USER = "DELETE users WHERE id = ?";
    private final String BY_ID = "select * from Users where id=?";


    private RowMapper<User> rowMapper = (ResultSet rs, int rowNum) -> {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));
        return user;
    };

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<User> findAll() {
        return jdbcTemplate.query(FIND_ALL, rowMapper);
    }

    public boolean addUser(User user) {
        if (jdbcTemplate.update(INSERT_USER, user.getFirstName(), user.getLastName()) > 0) return true;
        else return false;
    }

    public boolean updateUserById(User user, int id) {
        if (jdbcTemplate.update(UPDATE_USER, user.getFirstName(), user.getLastName(), id) > 0) return true;
        else return false;
    }

    public boolean updateUser(User user) {
        if (jdbcTemplate.update(UPDATE_USER, user.getFirstName(), user.getLastName(), user.getId()) > 0) return true;
        else return false;
    }

    public void deleteUserById(int id) {
        jdbcTemplate.update(DELETE_USER, id);
    }

    public void deleteUser(User user) {
        jdbcTemplate.update(DELETE_USER, user.getId());
    }

    public User findByUserId(int id) {

        User user = (User) jdbcTemplate.queryForObject(
                BY_ID, new Object[]{id},
                new BeanPropertyRowMapper(User.class));

        return user;
    }
}
