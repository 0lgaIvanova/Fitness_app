package lv.fitness_app.core.services;

import lv.fitness_app.core.database.jpa.JpaUserRepository;
import lv.fitness_app.core.domain.User;
import lv.fitness_app.core.requests.RemoveUserRequest;
import lv.fitness_app.core.responses.CoreError;
import lv.fitness_app.core.responses.RemoveUserResponse;
import lv.fitness_app.core.services.validators.RemoveUserRequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class RemoveUserService {

    @Autowired
    private JpaUserRepository userRepository;
    @Autowired private RemoveUserRequestValidator validator;


    public RemoveUserResponse execute(RemoveUserRequest request) {
        List<CoreError> errors = validator.validate(request);
        boolean isUserRemoved = false;
        if (!errors.isEmpty()) {
            return new RemoveUserResponse(errors);
        } else if (userRepository.findUserByEmail(request.getEmail())!= null) {
            User user = userRepository.findUserByEmail(request.getEmail());
            if (user.getEmail().equals(request.getEmail()) && user.getPassword().equals(request.getPassword())) {
                userRepository.delete(user);
                isUserRemoved = true;
            }
        }
        return new RemoveUserResponse(isUserRemoved);
    }
}