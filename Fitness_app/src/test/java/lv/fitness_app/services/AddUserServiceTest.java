package lv.fitness_app.services;

import lv.fitness_app.core.database.jpa.JpaUserRepository;
import lv.fitness_app.core.requests.AddUserRequest;
import lv.fitness_app.core.responses.AddUserResponse;
import lv.fitness_app.core.responses.CoreError;
import lv.fitness_app.core.services.AddUserService;
import lv.fitness_app.core.services.validators.AddUserRequestValidator;
import lv.fitness_app.matchers.UserMatcher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AddUserServiceTest {

    @Mock
    private JpaUserRepository userRepository;
    @Mock
    private AddUserRequestValidator validator;
    @InjectMocks
    private AddUserService service;

    @Test
    public void shouldReturnResponseWithErrorsWhenValidationFails() {
        AddUserRequest notValidRequest = new AddUserRequest(null, "Olga", "12345");
        when(validator.validate(notValidRequest)).thenReturn(List.of(new CoreError("email", "Must not be empty!")));
        AddUserResponse response = service.execute(notValidRequest);
        assertTrue(response.hasErrors());
    }

    @Test
    public void shouldReturnResponseWithErrorsReceivedFromValidator() {
        AddUserRequest notValidRequest = new AddUserRequest(null, "Olga", "12345");
        when(validator.validate(notValidRequest)).thenReturn(List.of(new CoreError("email", "Must not be empty!")));
        AddUserResponse response = service.execute(notValidRequest);
        assertEquals(response.getErrors().size(), 1);
        assertEquals(response.getErrors().get(0).getField(), "email");
        assertEquals(response.getErrors().get(0).getMessage(), "Must not be empty!");
    }

    @Test
    public void shouldNotInvokeDatabaseWhenRequestValidationFails() {
        AddUserRequest notValidRequest = new AddUserRequest(null, "Olga", "12345");
        when(validator.validate(notValidRequest)).thenReturn(List.of(new CoreError("email", "Must not be empty!")));
        service.execute(notValidRequest);
        verifyNoInteractions(userRepository);
    }

    @Test
    public void shouldAddUserToDatabaseWhenRequestIsValid() {
        AddUserRequest validRequest = new AddUserRequest("Email","Olga", "12345");
        when(validator.validate(validRequest)).thenReturn(List.of());
        service.execute(validRequest);
        verify(userRepository).save(argThat(new UserMatcher("Email", "Olga", "12345")));
    }
    @Test
    public void shouldReturnResponseWithoutErrorsWhenRequestIsValid() {
        AddUserRequest validRequest = new AddUserRequest("Email","Olga", "12345");
        when(validator.validate(validRequest)).thenReturn(List.of());
        AddUserResponse response = service.execute(validRequest);
        assertFalse(response.hasErrors());
    }

    @Test
    public void shouldReturnResponseWithBookWhenRequestIsValid() {
        AddUserRequest validRequest = new AddUserRequest("Email","Olga", "12345");
        when(validator.validate(validRequest)).thenReturn(List.of());
        AddUserResponse response = service.execute(validRequest);
        assertNotNull(response.getNewUser());
        assertEquals(response.getNewUser().getEmail(), validRequest.getEmail());
        assertEquals(response.getNewUser().getUsername(), validRequest.getUsername());
        assertEquals(response.getNewUser().getPassword(), validRequest.getPassword());
    }
}
