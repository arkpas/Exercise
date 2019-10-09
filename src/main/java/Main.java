package main.java;

import java.io.BufferedReader;

public class Main {
    public static void main (String[] args) {

        String fileName = "users.txt";
        String filePath = "/main/resources/";
        BufferedReader reader = FileUtils.getReaderForFile(filePath + fileName);

        UserRepository userRepository = new UserRepository();
        userRepository.addAllUsers(reader);
        System.out.println("Number of users: " + userRepository.getUsers().size());
        System.out.println("Oldest user with phone number: " + userRepository.getOldestUserWithPhoneNumber());


    }


}
