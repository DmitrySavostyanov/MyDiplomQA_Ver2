package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataGenerator {


    private static LocalDate today = LocalDate.now();
    private static DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern("MM");
    private static DateTimeFormatter yearFormatter = DateTimeFormatter.ofPattern("yy");


    protected static String getApprovedCardNumber() {
        return "4444 4444 4444 4441";
    }

    protected static String getDeclinedCardNumber() {
        return "4444 4444 4444 4442";
    }

    protected static String getInvalidCardNumber() {
        return "1111 1111 1111 1111";
    }

    protected static String getNotFullCardNumber() {
        return "1111 1111 1111 111";
    }

    protected static String getZeroCardNumber() {
        return "0000 0000 0000 0000";
    }

    protected static String getValidOwner() {
        Faker faker = new Faker(new Locale("en"));
        return faker.name().fullName();
    }

    protected static String getInvalidOwner() {
        Faker faker = new Faker(new Locale("ru"));
        return faker.name().fullName();
    }

    protected static String getValidCvc() {
        return "111";
    }

    protected static String getInvalidCvc() {
        return "69";
    }

    protected static String getZeroCvc() {
        return "000";
    }

    protected static String getCurrentYear() { // new pravka v string
        LocalDate currentYear = LocalDate.now();
        return yearFormatter.format(currentYear);
    }

    protected static String getValidExpirationDate() {// действительный срок действия карты
        LocalDate newYear = today.plusYears(1);
        return yearFormatter.format(newYear);
    }

    protected static String getExpiredYear() { //получить просроченный год
        LocalDate newYear = today.minusYears(1);
        return yearFormatter.format(newYear);
    }


    protected static String getZeroYear() {
        return "00";
    }

    protected static String  getValidMonth() {
        LocalDate newMonth = today.plusMonths(1);
        return monthFormatter.format(newMonth);
    }

    protected static String  getExpiredMonth() {
        LocalDate newMonth = today.minusMonths(1);
        return monthFormatter.format(newMonth);
    }

    protected static String  getInvalidMonth() {
        return "13";
    }

    protected static String  getZeroMonth() {
        return "00";
    }



}
