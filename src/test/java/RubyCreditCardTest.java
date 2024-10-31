import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RubyCreditCardTest {

    private RubyCreditCard rubyCreditCard;

    @BeforeEach
    public void setup() {
        rubyCreditCard = new RubyCreditCard(1000, 10_000);
    }

    // Проверка успешной оплаты
    @Test
    public void ToPayTest() {
        Assertions.assertTrue(rubyCreditCard.toPay(1000));
    }

    // Проверка неудачной попытки оплаты
    @Test
    public void negativeToPayTest() {
        Assertions.assertFalse(rubyCreditCard.toPay(-1000));
    }

    // Проверка оставшегося баланса
    @Test
    public void sumReceiptToPayTest() {
        Assertions.assertTrue(rubyCreditCard.toPay(100));
        Assertions.assertEquals(900, rubyCreditCard.getBalance());
    }

    // Проверка оплаты с нулевым балансом
    @Test
    public void negativeSumReceiptToPayTest() {
        rubyCreditCard.balance = 0;
        Assertions.assertFalse(rubyCreditCard.toPay(500));
    }

    // Проверка оплаты на 0
    @Test
    public void paymentZeroToPayTest() {
        Assertions.assertFalse(rubyCreditCard.toPay(0));
    }

    // Проверка успешного удвоения кэшбэка
    @Test
    public void loyaltyProgramForRegularCustomersTest() {
        rubyCreditCard.setYearOfUseSet(5);
        Assertions.assertEquals(100, rubyCreditCard.loyaltyProgramForRegularCustomers(1000));
    }

    // Проверка неудачного удвоения кэшбэка
    @Test
    public void negativeLoyaltyProgramForRegularCustomersTest() {
        rubyCreditCard.setYearOfUseSet(2);
        Assertions.assertEquals(0, rubyCreditCard.loyaltyProgramForRegularCustomers(1000));
    }

    // Проверка успешного получения сертификата
    @Test
    public void gettingCertificateTest() {
        rubyCreditCard.gettingCertificate(1000);
        Assertions.assertEquals(1000, rubyCreditCard.getFundsSpent());
        Assertions.assertEquals(0, rubyCreditCard.getGiftCertificateCount());
    }
}
