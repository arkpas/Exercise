package main.java;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class User {

    private String name;
    private String surname;
    private LocalDate birthDate;
    private String phoneNumber;

    public User (String data) throws Exception {
        List<String> dataList = this.splitData(data);
        this.convertToFields(dataList);
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public List<String> splitData (String data) {
        if (data == null)
            return new ArrayList<>();

        data = data.replaceAll(" ", "");
        String[] splittedData = data.split(",");

        return Arrays.stream(splittedData).filter(s -> s.length() > 0).collect(Collectors.toList());
    }

    private void convertToFields (List<String> dataList) throws Exception {

        //this method will intentionally ignore any excessive data, it only checks if minimum data is provided

        if (dataList.size() >= 3) {
            this.name = dataList.get(0);
            this.surname = dataList.get(1);
            this.birthDate = LocalDate.parse(dataList.get(2));
            if (dataList.size() == 4) {
                this.phoneNumber = dataList.get(3);
            }
        }
        else {
            throw new Exception();
        }
    }

    public static int calculateAge (LocalDate birthDate, LocalDate currentDate) {

        int age = currentDate.getYear() - birthDate.getYear();

        //check if user had birthday this year, if not we have to substract one year from his age, because his birthday is after present day
        LocalDate helperDate = birthDate.plusYears(age);
        if (helperDate.isAfter(currentDate))
            return age - 1;
        else
            return age;
    }

    public String toString() {
        int age = calculateAge(this.birthDate, LocalDate.now());
        return String.format("%s,%s,%d,%s", this.name, this.surname, age, this.phoneNumber);
    }

}
