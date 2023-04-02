package ru.netology.test;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.*;
import ru.netology.data.DataHelper;
import ru.netology.data.SqlHelper;
import ru.netology.pages.DashboardPage;
import ru.netology.pages.DebitCardPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static ru.netology.data.SqlHelper.*;

public class DebitCardTest {
    DashboardPage dashboardPage = new DashboardPage();

    @BeforeEach//перед
    void setup() {
        open(System.getProperty("sut.url"));
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
        //System.setProperty("url", "jdbc:mysql://localhost:3306/app");

    }
    @AfterEach
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

    }

    @Test // Тест - ок/
    @DisplayName("1. Покупка по одобренной дебетовой карте (Статус Approved)")
    void shouldPayByAppDC() {
        DebitCardPage debitCardPage = dashboardPage.payByDebitCard();
        DataHelper.CardInfo approvedCardInformation = DataHelper.getApprovedCardInfo();
        debitCardPage.cardInfo(approvedCardInformation);
        debitCardPage.okNotification();
        assertEquals("APPROVED", getEntryFromPaymentEntity().getStatus());
        assertNotEquals("", getEntryFromOrderEntity().getId());

    }

   @Test //Форма выдает сообщение об успешной оплате по дебитовой/кредитной карте со статусом Declined -Баг
    @DisplayName("3. Покупка по отклоненной дебетовой карте (Статус Declined)")
    void shouldPayNotByDecDC() {
        DebitCardPage debitCardPage = dashboardPage.payByDebitCard();
        DataHelper.CardInfo declinedCardInformation = DataHelper.getDeclinedCardInfo();
        debitCardPage.cardInfo(declinedCardInformation);
        debitCardPage.nokNotification();
        assertEquals("DECLINED", getEntryFromPaymentEntity().getStatus());
        assertNotEquals("", getEntryFromOrderEntity().getId());

    }

    @Test // тест проходит - Ок
    @DisplayName("5. Покупка по дебетовой карте с невалидным номером")
    void shouldNotPayByInvNum() {
        DebitCardPage debitCardPage = dashboardPage.payByDebitCard();
        DataHelper.CardInfo invalidCardInformation = DataHelper.getInvalidCardInfo();
        debitCardPage.cardInfo(invalidCardInformation);
        debitCardPage.messInvalidCardNumber();
        checkEmptyPaymentEntity();
        checkEmptyOrderEntity();

    }

    @Test // тест прошел - Ок
    @DisplayName("7. Покупка по дебетовой карте с неполным номером")
    void shouldErrorNotFullNum() {
        DebitCardPage debitCardPage = dashboardPage.payByDebitCard();
        DataHelper.CardInfo notFullCardInformation = DataHelper.getNotFullCardInfo();
        debitCardPage.cardInfo(notFullCardInformation);
        debitCardPage.messErrorNum();
        checkEmptyPaymentEntity();
        checkEmptyOrderEntity();
    }

    @Test // тест прошел - Ок
    @DisplayName("9. Покупка по дебетовой карте с указанием невалидного месяца")
    void shouldErrorInvalidMonth() {
        DebitCardPage debitCardPage = dashboardPage.payByDebitCard();
        DataHelper.CardInfo invalidMonthCardInformation = DataHelper.getInvalidMonthCardInfo();
        debitCardPage.cardInfo(invalidMonthCardInformation);
        debitCardPage.messInvalidMonth();
        checkEmptyPaymentEntity();
        checkEmptyOrderEntity();
    }

    @Test // тест прошел - Ок
    @DisplayName("11. Покупка по дебетовой карте с указанием истекшего месяца")
    void shouldErrorExpiredMonth() {
        DebitCardPage debitCardPage = dashboardPage.payByDebitCard();
        DataHelper.CardInfo expiredMonthCardInformation = DataHelper.getExpiredMonthCardInfo();
        debitCardPage.cardInfo(expiredMonthCardInformation);
        debitCardPage.messExpiredMonth();
        checkEmptyPaymentEntity();
        checkEmptyOrderEntity();
    }

    @Test // тест прошел - Ок
    @DisplayName("13. Покупка по дебетовой карте с указанием истекшего года")
    void shouldErrorExpiredYear() {
        DebitCardPage debitCardPage = dashboardPage.payByDebitCard();
        DataHelper.CardInfo expiredYearCardInformation = DataHelper.getExpiredYearCardInfo();
        debitCardPage.cardInfo(expiredYearCardInformation);
        debitCardPage.messExpiredYearField();
        checkEmptyPaymentEntity();
        checkEmptyOrderEntity();
    }

    @Test // тест упал!!! //Форма не выдает ошибку при вводе невалидных значений в поле Владелец - Баг
    @DisplayName("15. Покупка по дебетовой карте с указанием невалидных значений в поле Владелец")
    void shouldErrorInvalidOwner() {
        DebitCardPage debitCardPage = dashboardPage.payByDebitCard();
        DataHelper.CardInfo invalidOwner = DataHelper.getInvalidOwnerCard();
        debitCardPage.cardInfo(invalidOwner);
        debitCardPage.messInvalidOwner();
        checkEmptyPaymentEntity();
        checkEmptyOrderEntity();
    }

    @Test // тест прошел - Ок
    @DisplayName("17. Покупка по дебитовой карте с неполным вводом в поле CVC/CVV")
    void shouldErrorCvc() {
        DebitCardPage debitCardPage = dashboardPage.payByDebitCard();
        DataHelper.CardInfo invalidCvc = DataHelper.getInvalidCvc();
        debitCardPage.cardInfo(invalidCvc);
        debitCardPage.messInvalidCvc();
        checkEmptyPaymentEntity();
        checkEmptyOrderEntity();
    }

    @Test // тест прошел - Ок
    @DisplayName("19. Пустая форма заявки для покупки по дебетовой карте")
    void shouldNotSendEmptyForm() {
        DebitCardPage debitCardPage = dashboardPage.payByDebitCard();
        DataHelper.CardInfo emptyForm = DataHelper.getEmptyCardInfo();
        debitCardPage.cardInfo(emptyForm);
        debitCardPage.messEmptyCardNumberField();
        debitCardPage.messEmptyMonthField();
        debitCardPage.messEmptyYearField();
        debitCardPage.messEmptyOwnerField();
        debitCardPage.messEmptyCvcField();
    }

    @Test // тест прошел - Ок
    @DisplayName("21. Покупка по дебетовой карте с пустым полем Номер карты")
    void shouldErrorEmptyCardNum() {
        DebitCardPage debitCardPage = dashboardPage.payByDebitCard();
        DataHelper.CardInfo emptyCardNum = DataHelper.getEmptyCardNumber();
        debitCardPage.cardInfo(emptyCardNum);
        debitCardPage.messEmptyCardNumberField();
        checkEmptyPaymentEntity();
        checkEmptyOrderEntity();
    }

    @Test // тест прошел - Ок
    @DisplayName("23. Покупка по дебетовой карте с пустым полем Месяц")
    void shouldErrorEmptyMonth() {
        DebitCardPage debitCardPage = dashboardPage.payByDebitCard();
        DataHelper.CardInfo emptyMonth = DataHelper.getEmptyMonth();
        debitCardPage.cardInfo(emptyMonth);
        debitCardPage.messEmptyMonthField();
        checkEmptyPaymentEntity();
        checkEmptyOrderEntity();
    }
    @Test // тест прошел - Ок
    @DisplayName("25. Покупка по дебетовой карте с пустым полем Год")
    void shouldErrorEmptyYear() {
        DebitCardPage debitCardPage = dashboardPage.payByDebitCard();
        DataHelper.CardInfo emptyYear = DataHelper.getEmptyYear();
        debitCardPage.cardInfo(emptyYear);
        debitCardPage.messEmptyYearField();
        checkEmptyPaymentEntity();
        checkEmptyOrderEntity();
    }
    @Test// тест прошел - Ок
    @DisplayName("27. Покупка по дебетовой карте с пустым полем Владелец")
    void shouldErrorEmptyOwner() {
        DebitCardPage debitCardPage = dashboardPage.payByDebitCard();
        DataHelper.CardInfo emptyOwner = DataHelper.getEmptyOwner();
        debitCardPage.cardInfo(emptyOwner);
        debitCardPage.messEmptyOwnerField();
        checkEmptyPaymentEntity();
        checkEmptyOrderEntity();
    }
    @Test // тест прошел - Ок
    @DisplayName("29. Покупка по дебетовой карте с пустым полем Cvc")
    void shouldErrorEmptyCvc() {
        DebitCardPage debitCardPage = dashboardPage.payByDebitCard();
        DataHelper.CardInfo emptyCvc = DataHelper.getEmptyCvc();
        debitCardPage.cardInfo(emptyCvc);
        debitCardPage.messEmptyCvcField();
    }
   // @Test // тест упал - Форма не выдает сообщение об ошибке при вводе 000 в поле CVC- Баг
    @DisplayName("31. Покупка по дебетовой карте с вводом 000 в поле Cvc")
    void shouldErrorZeroCvc() {
        DebitCardPage debitCardPage = dashboardPage.payByDebitCard();
        DataHelper.CardInfo zeroCvc = DataHelper.getZeroCvc();
        debitCardPage.cardInfo(zeroCvc);
        debitCardPage.messInvalidCvc();
        checkEmptyPaymentEntity();
        checkEmptyOrderEntity();
    }
    @Test //тест прошел -Ок -- нотификацию поправил//
    @DisplayName("33. Покупка по дебетовой карте с вводом 0 в поле Номер карты")
    void shouldErrorZeroCardNum() {
        DebitCardPage debitCardPage = dashboardPage.payByDebitCard();
        DataHelper.CardInfo zeroCardNum = DataHelper.getCardZeroNumber();
        debitCardPage.cardInfo(zeroCardNum);
        debitCardPage.messZeroNum();
        checkEmptyPaymentEntity();
        checkEmptyOrderEntity();
    }
    @Test // тест прошел
    @DisplayName("35. Покупка по дебетовой карте с вводом 0 в поле Месяц")
    void shouldErrorZeroMonth() {
        DebitCardPage debitCardPage = dashboardPage.payByDebitCard();
        DataHelper.CardInfo zeroMonth = DataHelper.getZeroMonthCardInfo();
        debitCardPage.cardInfo(zeroMonth);
        debitCardPage.messInvalidMonth();
        checkEmptyPaymentEntity();
        checkEmptyOrderEntity();
    }
    @Test // тест прошел
    @DisplayName("37. Покупка по дебетовой карте с вводом 0 в поле Год")
    void shouldErrorZeroYear() {
        DebitCardPage debitCardPage = dashboardPage.payByDebitCard();
        DataHelper.CardInfo zeroYear = DataHelper.getZeroYearCardInformation();
        debitCardPage.cardInfo(zeroYear);
        debitCardPage.messInvalidYear();
        checkEmptyPaymentEntity();
        checkEmptyOrderEntity();
    }

}
