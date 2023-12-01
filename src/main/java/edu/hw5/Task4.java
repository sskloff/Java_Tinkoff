package edu.hw5;

public class Task4 {

    private Task4() {
    }

    public static boolean isPasswordValid(String password) {
        return password.matches(".*[~!@#$%^&*|].*");
    }
}
