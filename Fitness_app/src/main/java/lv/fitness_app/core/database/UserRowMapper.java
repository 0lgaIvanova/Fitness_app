package lv.fitness_app.core.database;

import lv.fitness_app.core.domain.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setEmail(rs.getString("email"));
        user.setUsername(rs.getString("user_name"));
        user.setPassword(rs.getString("password"));
        user.setSubscription(rs.getString("subscription"));
        user.setEndOfSubscriptionDate(rs.getDate("subscription_ends"));
        return user;
    }
}
