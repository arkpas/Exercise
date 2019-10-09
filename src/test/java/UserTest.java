package test.java;

import main.java.User;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.time.LocalDate;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class UserTest {


    @Test
    public void constructorShouldThrowExceptionWhenInputIsNull ()  {
        boolean isExceptionThrown = false;
        try {
            User user = new User(null);
        }
        catch (Exception e) { isExceptionThrown = true; }
        assertTrue(isExceptionThrown);
    }

    @Test
    public void constructorShouldThrowExceptionWhenInputIsIncomplete ()  {
        boolean isExceptionThrown = false;
        try {
            User user = new User("Jan,Kowalski,");
        }
        catch (Exception e) { isExceptionThrown = true; }
        assertTrue(isExceptionThrown);
    }

    @Test
    public void constructorShouldThrowExceptionWhenDateFormatIsWrong ()  {
        boolean isExceptionThrown = false;
        try {
            User user = new User("Jan,Kowalski,2019/11/1");
        }
        catch (Exception e) { isExceptionThrown = true; }
        assertTrue(isExceptionThrown);
    }

    @Test
    public void splitDataShouldReturnEmptyListWhenInputIsNull ()  {
        try {
            User user = new User("Jan,Kowalski,1111-11-11");
            List<String> splitData = user.splitData(null);
            assertTrue(splitData.isEmpty());
        }
        catch (Exception e) {fail();}
    }

    @Test
    public void splitDataShouldReturnEmptyListWhenInputContainsOnlyCommas ()  {
        try {
            User user = new User("Jan,Kowalski,1111-11-11");
            List<String> splitData = user.splitData(",,,,");
            assertTrue(splitData.isEmpty());
        }
        catch (Exception e) {fail();}
    }

    @Test
    public void splitDataShouldReturnEmptyListWhenInputContainsOnlyWhitespaceCharacters ()  {
        try {
            User user = new User("Jan,Kowalski,1111-11-11");
            List<String> splitData = user.splitData("  ,  ,  ,  ,");
            assertTrue(splitData.isEmpty());
        }
        catch (Exception e) {fail();}
    }

    @Test
    public void calculateAgeShouldReturnOneWhenUsersBirthdayIsToday () {
            LocalDate birthday = LocalDate.now().minusYears(1); //user is one year old
            assertEquals(1, User.calculateAge(birthday, LocalDate.now()));
    }

    @Test
    public void calculateAgeShouldReturnOneWhenUserHadBirthdayAlreadyThisYear () {
        LocalDate birthday = LocalDate.now().minusYears(1).minusDays(1); //user had birthday yesterday
        assertEquals(1, User.calculateAge(birthday, LocalDate.now()));
    }

    @Test
    public void calculateAgeShouldReturnZeroWhenUsersBirthdayIsAfterToday () {
        LocalDate birthday = LocalDate.now().minusYears(1).plusDays(1); //user have birthday tommorow
        assertEquals(0, User.calculateAge(birthday, LocalDate.now()));
    }
}
