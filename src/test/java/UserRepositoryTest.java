package test.java;

import main.java.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.time.LocalDate;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(JUnit4.class)
public class UserRepositoryTest {

    private UserRepository userRepository;

    @Before
    public void setup () {
        userRepository = new UserRepository();
    }


    @Test (expected = UnsupportedOperationException.class)
    public void getUsersShouldReturnUnmodifiableList () {
        userRepository.getUsers().clear();
    }

    @Test
    public void addUsersShouldDoNothingWhenArgumentIsNull () {
        userRepository.addAllUsers(null);
        assertTrue(userRepository.getUsers().isEmpty());
    }

    @Test
    public void addUserShouldNotAddUserIfDataIsInvalid () {
        userRepository.addUser("invalidData");
        assertTrue(userRepository.getUsers().isEmpty());
    }

    @Test
    public void addUserShouldAddUserSuccessfuly () {
        userRepository.addUser("Jan,Kowalski,1999-12-12");
        assertEquals(1, userRepository.getUsers().size());
    }

    @Test
    public void getOldestUserWithPhoneNumberShouldReturnNullIfListIsEmpty () {
        assertNull(userRepository.getOldestUserWithPhoneNumber());
    }

    @Test
    public void getOldestUserWithPhoneNumberShouldReturnNullIfNoUserHavePhoneNumber () {
        userRepository.addUser("Jan,Kowalski,1222-11-11");
        userRepository.addUser("Jan,Niekowalski,1223-12-12");
        userRepository.addUser("Jan,Bardzokowalski,1224-09-14");
        assertNull(userRepository.getOldestUserWithPhoneNumber());
    }

    @Test
    public void getOldestUserWithPhoneNumberShouldReturnOldestUser () {

        LocalDate oldestUserBirthday = LocalDate.of(1999, 12, 1);


        userRepository.addUser("Jan,Mlodszy," + oldestUserBirthday.plusDays(2).toString() + ",1111111");
        userRepository.addUser("Jan,Sredni," + oldestUserBirthday.plusDays(1).toString() + ",1111111");
        userRepository.addUser("Jan,Starszy," + oldestUserBirthday.toString() + ",1111111");

        assertEquals(oldestUserBirthday, userRepository.getOldestUserWithPhoneNumber().getBirthDate());
    }
}
