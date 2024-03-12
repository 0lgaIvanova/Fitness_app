package lv.fitness_app.matchers;

import lv.fitness_app.core.domain.User;
import org.mockito.ArgumentMatcher;

public class UserMatcher implements ArgumentMatcher<User> {

    private String email;
    private String username;
    private String password;

    public UserMatcher(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;

    }

    @Override
    public boolean matches(User user) {
        return user.getEmail().equals(email)&& user.getUsername().equals(username)
                && user.getPassword().equals(password);
    }
}
