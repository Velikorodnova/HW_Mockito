import org.example.User;
import org.example.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

public class UserRepositoryTest {
    User user = new User("Masha7", "987654");
    User user1 = new User("Sasha9", "123456");

    UserRepository userRepository = new UserRepository();

    @Test
    public void getEmptyUsersList() {
        Assertions.assertIterableEquals(null, null);
    }
    @Test
    public void gettingAListOfUsersWhenTheServiceIsFull() {
        userRepository.addUser(user1);
        Assertions.assertIterableEquals(List.of(user1), userRepository.getAllUser());
    }
    @Test
    public void userSearchByLoginIfThereIsSuchAUser() {
        userRepository.addUser(user);
        Assertions.assertEquals(userRepository.addUserByLogin("Masha7"), Optional.of(user));
    }
    @Test
    public void searchForAUserByLoginInTheCaseWhenThereIsNoSuchUser() {
        userRepository.addUser(user1);
        Assertions.assertNull(null,"Maks");
    }
    @Test
    public void searchForAUserByLoginAndPasswordIfSuchAUserExists() {
        userRepository.addUser(user);
        Assertions.assertEquals(userRepository.addUserByLoginAndPassword("Masha7", "987654"),
                Optional.of(user));
    }
    @Test
    public void searchForAUserByLoginAndPasswordIfThePasswordMatchesOneOfTheExistingOnesButTheLoginDoesNot() {
        userRepository.addUser(user);
        userRepository.addUser(user1);
        Assertions.assertNotEquals(userRepository.addUserByLoginAndPassword("Masha7", "987654"),
                user1);
    }
    @Test
    public void searchForAUserByLoginAndPasswordIfTheLoginMtchesOneOfTheExistingOnesButThePasswordDoesNot() {
        userRepository.addUser(user1);
        userRepository.addUser(user);
        Assertions.assertNotEquals(userRepository.addUserByLoginAndPassword("Natasha", "123"),
                user);
    }
}
