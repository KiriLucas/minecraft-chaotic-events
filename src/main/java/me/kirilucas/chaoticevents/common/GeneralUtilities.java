package me.kirilucas.chaoticevents.common;

public class GeneralUtilities {

    public static int getRandomNumber(int maximum) {
        return (int) ((Math.random() * (maximum)));
    }

    public static int getRandomNumber(int maximum, int minimum) {
        return (int) ((Math.random() * (maximum - minimum)) + minimum);
    }
}
