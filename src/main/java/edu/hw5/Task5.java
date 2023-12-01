package edu.hw5;

public class Task5 {

    private Task5() {
    }

    public static boolean isLicensePlateValid(String plate) {
        return plate.matches("^[АВЕКМНОРСТУХ]\\d{3}[АВЕКМНОРСТУХ]{2}\\d{2,3}$");
    }
}
