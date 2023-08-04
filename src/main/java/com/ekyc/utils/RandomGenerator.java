package com.ekyc.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class RandomGenerator {
    private static String getString(int randomLength, String characters) {
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < randomLength; i++) {
            int index = random.nextInt(characters.length());
            char randomChar = characters.charAt(index);
            stringBuilder.append(randomChar);
        }
        return stringBuilder.toString();
    }

    public static String generateRandomAlphaNumericString(int randomLength) {
        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        return getString(randomLength, characters);
    }

    public static String generateRandomAlphaString(int randomLength) {
        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        return getString(randomLength, characters);
    }

    public static String generateRandomNumericString(int randomLength) {
        String characters = "0123456789";
        return getString(randomLength, characters);
    }


    public static String generateEpochSeconds(int randomLength) {
        int epochSeconds = (int) System.currentTimeMillis() / 1000;
        String epochSecondsDecimals = String.format("%04d", epochSeconds / 1000);
        return "M" + epochSeconds + epochSecondsDecimals + generateRandomNumericString(randomLength);
    }

    public static String getDateTime(){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        return now.format(formatter);
    }
}
