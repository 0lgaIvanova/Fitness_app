package lv.fitness_app.core.services;

import lv.fitness_app.core.database.jpa.JpaUserRepository;
import lv.fitness_app.core.domain.User;
import lv.fitness_app.core.requests.AddUserRequest;
import lv.fitness_app.core.responses.AddUserResponse;
import lv.fitness_app.core.responses.CoreError;
import lv.fitness_app.core.services.validators.AddUserRequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class AddUserService {

    @Autowired
    private JpaUserRepository userRepository;
    @Autowired private AddUserRequestValidator validator;

    public AddUserResponse execute(AddUserRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new AddUserResponse(errors);
        } else {
            User user = new User(request.getEmail(), request.getUsername(), request.getPassword());
            userRepository.save(user);
            return new AddUserResponse(user);
        }
    }
}
