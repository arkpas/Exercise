package main.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

public class UserRepository {

    private List<User> users = new ArrayList<>();

    public List<User> getUsers() {
        return Collections.unmodifiableList(users);
    }

    public void addAllUsers (BufferedReader reader) {
        if (reader != null) {
            String line = null;
            try {
                while ((line = reader.readLine()) != null) {
                    addUser(line);
                }
            }
            catch (IOException e) { System.out.println("Error reading line from file"); }
        }
    }

    public void addUser (String userData) {
        try {
            User user = new User(userData);
            users.add(user);
        }
        catch (Exception e) { System.out.println("Error adding user: " + userData);}
    }

    public User getOldestUserWithPhoneNumber () {

        Optional<User> optional = users.stream().filter(user -> user.getPhoneNumber() != null).min(Comparator.comparing(User::getBirthDate));
        return optional.orElse(null);
    }
}
