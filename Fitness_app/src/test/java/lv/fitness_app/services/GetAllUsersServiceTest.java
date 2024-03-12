package lv.fitness_app.services;

import lv.fitness_app.core.database.jpa.JpaUserRepository;
import lv.fitness_app.core.domain.User;
import lv.fitness_app.core.requests.GetAllUsersRequest;
import lv.fitness_app.core.responses.GetAllUsersResponse;
import lv.fitness_app.core.services.GetAllUsersService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(MockitoJUnitRunner.class)
public class GetAllUsersServiceTest {

    @Mock
    private JpaUserRepository userRepository;
    @InjectMocks
    private GetAllUsersService service;

    @Test
    public void shouldGetUsersFromDb() {
        List<User> users = new ArrayList<>();
        users.add(new User("Email","Username", "Password"));
        Mockito.when(userRepository.findAll()).thenReturn(users);

        GetAllUsersRequest request = new GetAllUsersRequest();
        GetAllUsersResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getUsers().size(), 1);
        assertEquals(response.getUsers().get(0).getEmail(), "Email");
        assertEquals(response.getUsers().get(0).getUsername(), "Username");
        assertEquals(response.getUsers().get(0).getPassword(), "Password");
    }
}
