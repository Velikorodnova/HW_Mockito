import org.example.User;
import org.example.UserRepository;
import org.example.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    User user = new User("Masha7", "987654");
    User user1 = new User("Sasha9", "123456");

    @Mock
    UserRepository userRepository;
    @InjectMocks
    UserService userService;

    @Test
    void getAnEmptyListOfUsers() {
        when(userRepository.getAllUser()).thenReturn(List.of());
        Assertions.assertEquals(userRepository.getAllUser().isEmpty(), List.of(user));
        assertThat(userService.userByLoginAndPassword("Masha7", "987654"));
    }

    @Test
    void whenTheUserIsCorrectlyAddedThenTheAddMethodIsCalledOnTheRepository() {
        when(userRepository.getAllUser()).thenReturn(List.of(user1));
        userService.createNewUser("Masha7", "987654");
        verify(userRepository).addUser(any());

    }
}
