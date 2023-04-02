package ru.netology.data;

import lombok.Value;

public class DataHelper {

   // static DataGenerator DataGenerator = new DataGenerator();

    @Value
    public static class CardInfo {
        String cardNumber;
        String year;
        String month;
        String owner;
        String cvc;
    }

    public static CardInfo getApprovedCardInfo() {
        return new CardInfo(DataGenerator.getApprovedCardNumber(), DataGenerator.getValidExpirationDate(), DataGenerator.getValidMonth(), DataGenerator.getValidOwner(), DataGenerator.getValidCvc());
    }

    public static CardInfo getDeclinedCardInfo() {
        return new CardInfo(DataGenerator.getDeclinedCardNumber(), DataGenerator.getValidExpirationDate(), DataGenerator.getValidMonth(), DataGenerator.getValidOwner(), DataGenerator.getValidCvc());
    }

    public static CardInfo getInvalidCardInfo() {
        return new CardInfo(DataGenerator.getInvalidCardNumber(), DataGenerator.getValidExpirationDate(), DataGenerator.getValidMonth(), DataGenerator.getValidOwner(), DataGenerator.getValidCvc());
    }

    public static CardInfo getNotFullCardInfo() {
        return new CardInfo(DataGenerator.getNotFullCardNumber(), DataGenerator.getValidExpirationDate(), DataGenerator.getValidMonth(), DataGenerator.getValidOwner(), DataGenerator.getValidCvc());
    }

    public static CardInfo getCardZeroNumber() {
        return new CardInfo(DataGenerator.getZeroCardNumber(), DataGenerator.getValidExpirationDate(), DataGenerator.getValidMonth(), DataGenerator.getValidOwner(), DataGenerator.getValidCvc());
    }

    public static CardInfo getExpiredMonthCardInfo() {
        return new CardInfo(DataGenerator.getApprovedCardNumber(), DataGenerator.getCurrentYear(), DataGenerator.getExpiredMonth(), DataGenerator.getValidOwner(), DataGenerator.getValidCvc());
    }

    public static CardInfo getInvalidMonthCardInfo() {
        return new CardInfo(DataGenerator.getApprovedCardNumber(), DataGenerator.getCurrentYear(), DataGenerator.getInvalidMonth(), DataGenerator.getValidOwner(), DataGenerator.getValidCvc());
    }

    public static CardInfo getZeroMonthCardInfo() {
        return new CardInfo(DataGenerator.getApprovedCardNumber(), DataGenerator.getCurrentYear(), DataGenerator.getZeroMonth(), DataGenerator.getValidOwner(), DataGenerator.getValidCvc());
    }

    public static CardInfo getExpiredYearCardInfo() {
        return new CardInfo(DataGenerator.getApprovedCardNumber(), DataGenerator.getExpiredYear(), DataGenerator.getValidMonth(), DataGenerator.getValidOwner(), DataGenerator.getValidCvc());
    }

    public static CardInfo getZeroYearCardInformation() {
        return new CardInfo(DataGenerator.getApprovedCardNumber(), DataGenerator.getZeroYear(), DataGenerator.getValidMonth(), DataGenerator.getValidOwner(), DataGenerator.getValidCvc());
    }

    public static CardInfo getInvalidCvc() {
        return new CardInfo(DataGenerator.getApprovedCardNumber(), DataGenerator.getValidExpirationDate(), DataGenerator.getValidMonth(), DataGenerator.getValidOwner(), DataGenerator.getInvalidCvc());
    }

    public static CardInfo getZeroCvc() {
        return new CardInfo(DataGenerator.getApprovedCardNumber(), DataGenerator.getValidExpirationDate(), DataGenerator.getValidMonth(), DataGenerator.getValidOwner(), DataGenerator.getZeroCvc());
    }

    public static CardInfo getEmptyCardInfo() {
        return new CardInfo(" ", " ", " ", " ", " ");
    };

    public static CardInfo getEmptyCardNumber() {
        return new CardInfo(" ", DataGenerator.getCurrentYear(), DataGenerator.getValidMonth(), DataGenerator.getValidOwner(), DataGenerator.getValidCvc());
    }

    public static CardInfo getEmptyMonth() {
        return new CardInfo(DataGenerator.getApprovedCardNumber(), DataGenerator.getCurrentYear(), "", DataGenerator.getValidOwner(), DataGenerator.getValidCvc());
    }

    public static CardInfo getEmptyYear() {
        return new CardInfo(DataGenerator.getApprovedCardNumber(), "", DataGenerator.getValidMonth(), DataGenerator.getValidOwner(), DataGenerator.getValidCvc());
    }

    public static CardInfo getEmptyOwner() {
        return new CardInfo(DataGenerator.getApprovedCardNumber(), DataGenerator.getCurrentYear(), DataGenerator.getValidMonth(), "", DataGenerator.getValidCvc());
    }

    public static CardInfo getEmptyCvc() {
        return new CardInfo(DataGenerator.getApprovedCardNumber(), DataGenerator.getCurrentYear(), DataGenerator.getValidMonth(), DataGenerator.getValidOwner(), "");
    }

    public static CardInfo getInvalidOwnerCard() {
        return new CardInfo(DataGenerator.getApprovedCardNumber(), DataGenerator.getValidExpirationDate(), DataGenerator.getValidMonth(), DataGenerator.getInvalidOwner(), DataGenerator.getValidCvc());
    }

}
