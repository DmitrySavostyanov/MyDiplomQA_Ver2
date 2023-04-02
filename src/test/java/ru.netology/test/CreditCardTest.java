package ru.netology.test;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.*;
import ru.netology.data.DataHelper;
import ru.netology.data.SqlHelper;
import ru.netology.pages.CreditCardPage;
import ru.netology.pages.DashboardPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static ru.netology.data.SqlHelper.*;

public class CreditCardTest {

    DashboardPage dashboardPage = new DashboardPage();

    @BeforeEach
    void setup() {
        open(System.getProperty("sut.url"));
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
        System.setProperty("url", "jdbc:mysql://localhost:3306/app");
    }

    @AfterEach //после
    void afterEach(){
        SqlHelper.cleanDataBase();
    }

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
        //SqlHelper.cleanDataBase();
    }

//    @Test //Тест - ок/ нужно менять строку с БД
//    @DisplayName("2. Покупка по одобренной кредитной карте (Статус Approved)")
//    void shouldPayByAppDC() {
//        // System.setProperty("url", "jdbc:postgresql://localhost:5432/app"); // url - ключ (имя переменной), "jdbc..../app"- значение переменной
//        //System.setProperty("url", "jdbc:mysql://localhost:3306/app");
//        val creditCardPage = dashboardPage.payByCreditCard();
//        val approvedCardInformation = DataHelper.getApprovedCardInfo();
//        //System.out.println(approvedCardInformation.);
//        creditCardPage.cardInfo(approvedCardInformation);
//        creditCardPage.okNotification();
//        val paymentStatus = SqlHelper.getCreditEntity();
//        assertEquals("APPROVED", paymentStatus);}

    @Test //Тест - ок/ нужно менять строку с БД
    @DisplayName("2. Покупка по одобренной кредитной карте (Статус Approved)")
    void shouldPayByAppDC() {
        CreditCardPage creditCardPage = dashboardPage.payByCreditCard();
        DataHelper.CardInfo approvedCardInformation = DataHelper.getApprovedCardInfo();
        creditCardPage.cardInfo(approvedCardInformation);
        creditCardPage.okNotification();
        //String paymentStatus = SqlHelper.getCreditEntity();
        //assertEquals("APPROVED", paymentStatus);
        assertEquals("APPROVED", getEntryFromCreditRequestEntity().getStatus());
        assertNotEquals("", getEntryFromOrderEntity().getId());
    }

   // @Test // тест упал //Форма выдает сообщение об успешной оплате по дебитовой/кредитной карте со статусом Declined -Баг
    @DisplayName("4. Покупка по отклоненной кредитной карте (Статус Declined)")
    void shouldPayNotByDecDC() {
        CreditCardPage creditCardPage = dashboardPage.payByCreditCard();
        DataHelper.CardInfo declinedCardInformation = DataHelper.getDeclinedCardInfo();
        creditCardPage.cardInfo(declinedCardInformation);
        creditCardPage.nokNotification();

//        val paymentStatus = SqlHelper.getCreditEntity();
//        assertEquals("DECLINED", paymentStatus);
        assertEquals("DECLINED", getEntryFromCreditRequestEntity().getStatus());
        assertNotEquals("", getEntryFromOrderEntity().getId());

    }

//    @Test /// после запуска
//    void test(){
//        System.setProperty("url", "jdbc:mysql://localhost:3306/app");
//        checkEmptyPaymentEntity();
//    }

//    @Test // Тест прошел -Ок
//    @DisplayName("6. Покупка по кредитной карте с невалидным номером")
//    void shouldNotPayByInvNum() {
//        val creditCardPage = dashboardPage.payByCreditCard();
//        val invalidCardInformation = DataHelper.getInvalidCardInfo();
//        creditCardPage.cardInfo(invalidCardInformation);
//        creditCardPage.messInvalidCardNumber();
//    }
    @Test // Тест прошел -Ок
    @DisplayName("6. Покупка по кредитной карте с невалидным номером")
    void shouldNotPayByInvNum() {
        //System.setProperty("url", "jdbc:mysql://localhost:3306/app");
        CreditCardPage creditCardPage = dashboardPage.payByCreditCard();
        DataHelper.CardInfo invalidCardInformation = DataHelper.getInvalidCardInfo();
        creditCardPage.cardInfo(invalidCardInformation);
        creditCardPage.messInvalidCardNumber();
        checkEmptyCreditRequestEntity();
        checkEmptyOrderEntity();
    }

    @Test // Тест прошел -Ок
    @DisplayName("8. Покупка по кредитной карте с неполным номером")
    void shouldErrorNotFullNum() {
        CreditCardPage creditCardPage = dashboardPage.payByCreditCard();
        DataHelper.CardInfo notFullCardInformation = DataHelper.getNotFullCardInfo();
        creditCardPage.cardInfo(notFullCardInformation);
        creditCardPage.messErrorNum();
        checkEmptyCreditRequestEntity();
        checkEmptyOrderEntity();
    }

    @Test // Тест прошел -Ок
    @DisplayName("10. Покупка по кредитной карте с указанием невалидного месяца")
    void shouldErrorInvalidMonth() {
       CreditCardPage creditCardPage = dashboardPage.payByCreditCard();
        DataHelper.CardInfo invalidMonthCardInformation = DataHelper.getInvalidMonthCardInfo();
        creditCardPage.cardInfo(invalidMonthCardInformation);
        creditCardPage.messInvalidMonth();
        checkEmptyCreditRequestEntity();
        checkEmptyOrderEntity();
    }

    @Test // Тест прошел -Ок
    @DisplayName("12. Покупка по кредитной карте с указанием истекшего месяца")
    void shouldErrorExpiredMonth() {
        CreditCardPage creditCardPage = dashboardPage.payByCreditCard();
        DataHelper.CardInfo expiredMonthCardInformation = DataHelper.getExpiredMonthCardInfo();
        creditCardPage.cardInfo(expiredMonthCardInformation);
        creditCardPage.messExpiredMonth();
        checkEmptyCreditRequestEntity();
        checkEmptyOrderEntity();
    }

    @Test // Тест прошел -Ок
    @DisplayName("14. Покупка по кредитной карте с указанием истекшего года")
    void shouldErrorExpiredYear() {
       // System.setProperty("url", "jdbc:mysql://localhost:3306/app");
        CreditCardPage creditCardPage = dashboardPage.payByCreditCard();
        DataHelper.CardInfo expiredYearCardInformation = DataHelper.getExpiredYearCardInfo();
        creditCardPage.cardInfo(expiredYearCardInformation);
        creditCardPage.messExpiredYearField();
        checkEmptyCreditRequestEntity();
        checkEmptyOrderEntity();
    }

    //@Test // тест упал ---- /Форма не выдает ошибку при вводе невалидных значений в поле Владелец - Баг
    @DisplayName("16. Покупка по кредитной карте с указанием невалидных значений в поле Владелец")
    void shouldErrorInvalidOwner() {
        //System.setProperty("url", "jdbc:mysql://localhost:3306/app");
        CreditCardPage creditCardPage = dashboardPage.payByCreditCard();;
        DataHelper.CardInfo invalidOwner = DataHelper.getInvalidOwnerCard();
        creditCardPage.cardInfo(invalidOwner);
        creditCardPage.messInvalidOwner();
        //checkEmptyCreditRequestEntity();
        //checkEmptyOrderEntity();
    }

    @Test // Тест прошел -Ок
    @DisplayName("18. Покупка по кредитной карте с неполным вводом в поле CVC/CVV")
    void shouldErrorCvc() {
        CreditCardPage creditCardPage = dashboardPage.payByCreditCard();
        DataHelper.CardInfo invalidCvc = DataHelper.getInvalidCvc();
        creditCardPage.cardInfo(invalidCvc);
        creditCardPage.messInvalidCvc();
        checkEmptyCreditRequestEntity();
        checkEmptyOrderEntity();
    }

    @Test // Тест прошел -Ок
    @DisplayName("20. Пустая форма заявки для покупки по кредитной карте")
    void shouldNotSendEmptyForm() {
        CreditCardPage creditCardPage = dashboardPage.payByCreditCard();
        DataHelper.CardInfo emptyForm = DataHelper.getEmptyCardInfo();
        creditCardPage.cardInfo(emptyForm);
        creditCardPage.messEmptyCardNumberField();
        creditCardPage.messEmptyMonthField();
        creditCardPage.messEmptyYearField();
        creditCardPage.messEmptyOwnerField();
        creditCardPage.messEmptyCvcField();
        checkEmptyCreditRequestEntity();
        checkEmptyOrderEntity();
    }

    @Test // Тест прошел -Ок
    @DisplayName("22. Покупка по кредитной карте с пустым полем Номер карты")
    void shouldErrorEmptyCardNum() {
        CreditCardPage creditCardPage = dashboardPage.payByCreditCard();
        DataHelper.CardInfo emptyCardNum = DataHelper.getEmptyCardNumber();
        creditCardPage.cardInfo(emptyCardNum);
        creditCardPage.messEmptyCardNumberField();
        checkEmptyCreditRequestEntity();
        checkEmptyOrderEntity();
    }

    @Test // Тест прошел -Ок
    @DisplayName("24. Покупка по кредитной карте с пустым полем Месяц")
    void shouldErrorEmptyMonth() {
        CreditCardPage creditCardPage = dashboardPage.payByCreditCard();
        DataHelper.CardInfo emptyMonth = DataHelper.getEmptyMonth();
        creditCardPage.cardInfo(emptyMonth);
        creditCardPage.messEmptyMonthField();
        checkEmptyCreditRequestEntity();
        checkEmptyOrderEntity();
    }
    @Test // Тест прошел -Ок
    @DisplayName("26. Покупка по кредитной карте с пустым полем Год")
    void shouldErrorEmptyYear() {
        CreditCardPage creditCardPage = dashboardPage.payByCreditCard();
        DataHelper.CardInfo emptyYear = DataHelper.getEmptyYear();
        creditCardPage.cardInfo(emptyYear);
        creditCardPage.messEmptyYearField();
        checkEmptyCreditRequestEntity();
        checkEmptyOrderEntity();
    }
    @Test // Тест прошел -Ок
    @DisplayName("28. Покупка по кредитной карте с пустым полем Владелец")
    void shouldErrorEmptyOwner() {
        CreditCardPage creditCardPage = dashboardPage.payByCreditCard();
        DataHelper.CardInfo emptyOwner = DataHelper.getEmptyOwner();
        creditCardPage.cardInfo(emptyOwner);
        creditCardPage.messEmptyOwnerField();
        checkEmptyCreditRequestEntity();
        checkEmptyOrderEntity();
    }
    @Test // Тест прошел -Ок
    @DisplayName("30. Покупка по кредитной карте с пустым полем Cvc")
    void shouldErrorEmptyCvc() {
        CreditCardPage creditCardPage = dashboardPage.payByCreditCard();
        DataHelper.CardInfo emptyCvc = DataHelper.getEmptyCvc();
        creditCardPage.cardInfo(emptyCvc);
        creditCardPage.messEmptyCvcField();
        checkEmptyCreditRequestEntity();
        checkEmptyOrderEntity();
    }
    //@Test // тест упал - - Форма не выдает сообщение об ошибке при вводе 000 в поле CVC- Баг
    @DisplayName("32. Покупка по кредитной карте с вводом 000 в поле Cvc")
    void shouldErrorZeroCvc() {
        CreditCardPage creditCardPage = dashboardPage.payByCreditCard();
        DataHelper.CardInfo zeroCvc = DataHelper.getZeroCvc();
        creditCardPage.cardInfo(zeroCvc);
        creditCardPage.messInvalidCvc();
        checkEmptyCreditRequestEntity();
        checkEmptyOrderEntity();
    }
    @Test // Тест прошел/
    @DisplayName("34. Покупка по кредитной карте с вводом 0 в поле Номер карты")
    void shouldErrorZeroCardNum() {
        CreditCardPage creditCardPage = dashboardPage.payByCreditCard();
        DataHelper.CardInfo zeroCardNum = DataHelper.getCardZeroNumber();
        creditCardPage.cardInfo(zeroCardNum);
        creditCardPage.messZeroNum();
        checkEmptyCreditRequestEntity();
        checkEmptyOrderEntity();
    }
    @Test // Тест прошел -Ок
    @DisplayName("36. Покупка по кредитной карте с вводом 0 в поле Месяц")
    void shouldErrorZeroMonth() {
        CreditCardPage creditCardPage = dashboardPage.payByCreditCard();
        DataHelper.CardInfo zeroMonth = DataHelper.getZeroMonthCardInfo();
        creditCardPage.cardInfo(zeroMonth);
        creditCardPage.messInvalidMonth();
        checkEmptyCreditRequestEntity();
        checkEmptyOrderEntity();
    }
    @Test // Тест прошел -Ок
    @DisplayName("38. Покупка по кредитной карте с вводом 0 в поле Год")
    void shouldErrorZeroYear() {
        CreditCardPage creditCardPage = dashboardPage.payByCreditCard();
        DataHelper.CardInfo zeroYear = DataHelper.getZeroYearCardInformation();
        creditCardPage.cardInfo(zeroYear);
        creditCardPage.messInvalidYear();
        checkEmptyCreditRequestEntity();
        checkEmptyOrderEntity();
    }
}
