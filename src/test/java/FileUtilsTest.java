package test.java;

import main.java.FileUtils;
import org.junit.Test;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.File;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(JUnit4.class)
public class FileUtilsTest {

    @Test
    public void getReaderForFileShouldReturnNullWhenArgumentIsNull () {
        assertNull(FileUtils.getReaderForFile(null));
    }

    @Test
    public void getReaderForFileShouldReturnNullWhenArgumentIsEmpty () {
        assertNull(FileUtils.getReaderForFile(""));
    }

    @Test
    public void getReaderForFileShouldReturnNullWhenFileDoesntExist () {
        assertNull(FileUtils.getReaderForFile("123_i_do_not_exist"));
    }

    @Test
    public void getReaderForFileShouldReturnReader () {
        assertNotNull(FileUtils.getReaderForFile("/test/resources/test.txt"));
    }
}
