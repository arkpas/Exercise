package main.java;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileUtils {

    public static BufferedReader getReaderForFile (String filePath) {
        if (filePath == null || filePath.length() == 0)
            return null;

        InputStream inputStream = FileUtils.class.getResourceAsStream(filePath);

        if (inputStream == null)
            return null;

        return new BufferedReader(new InputStreamReader(inputStream));
    }
}
