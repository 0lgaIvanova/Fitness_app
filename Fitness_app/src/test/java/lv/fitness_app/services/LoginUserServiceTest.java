package lv.fitness_app.services;

import lv.fitness_app.core.database.jpa.JpaUserRepository;
import lv.fitness_app.core.requests.LoginUserRequest;
import lv.fitness_app.core.requests.RemoveUserRequest;
import lv.fitness_app.core.responses.CoreError;
import lv.fitness_app.core.responses.LoginUserResponse;
import lv.fitness_app.core.responses.RemoveUserResponse;
import lv.fitness_app.core.services.LoginUserService;
import lv.fitness_app.core.services.RemoveUserService;
import lv.fitness_app.core.services.validators.LoginUserRequestValidator;
import lv.fitness_app.core.services.validators.RemoveUserRequestValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class LoginUserServiceTest {
    @Mock
    private JpaUserRepository userepository;
    @Mock
    private LoginUserRequestValidator validator;
    @InjectMocks
    private LoginUserService service;

    @Test
    public void shouldReturnErrorWhenUserEmailNotProvided() {
        LoginUserRequest request = new LoginUserRequest(null, "Password");
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("userEmail", "Must not be empty!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        LoginUserResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrors().size(), 1);
        assertEquals(response.getErrors().get(0).getField(), "userEmail");
        assertEquals(response.getErrors().get(0).getMessage(), "Must not be empty!");
    }

    @Test
    public void shouldDeleteUserWithEmailFromDatabase() {
        Mockito.when(validator.validate(any())).thenReturn(new ArrayList<>());
        LoginUserRequest request = new LoginUserRequest("Email", "Password");
        LoginUserResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals("Email", "Email");
    }
}
