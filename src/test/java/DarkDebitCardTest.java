import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DarkDebitCardTest {
    private DarkDebitCard darkDebitCard;

    @BeforeEach
    public void setup() {
        darkDebitCard = new DarkDebitCard(1000);
    }

    // Проверка успешной оплаты
    @Test
    public void ToPayTest() {
        Assertions.assertTrue(darkDebitCard.toPay(1000));
    }

    // Проверка неудачной попытки оплаты
    @Test
    public void negativeToPayTest() {
        Assertions.assertFalse(darkDebitCard.toPay(-1000));
    }

    // Проверка оставшегося баланса
    @Test
    public void sumReceiptToPayTest() {
        Assertions.assertTrue(darkDebitCard.toPay(100));
        Assertions.assertEquals(900, darkDebitCard.getBalance());
    }

    // Проверка оплаты с нулевым балансом
    @Test
    public void negativeSumReceiptToPayTest() {
        darkDebitCard.balance = 0;
        Assertions.assertFalse(darkDebitCard.toPay(500));
    }

    // Проверка оплаты на 0
    @Test
    public void paymentZeroToPayTest() {
        Assertions.assertFalse(darkDebitCard.toPay(0));
    }

    // Проверка успешного кэшбэка
    @Test
    public void checkingSuccessfulCashbackСalculatingCashback() {
        double receivedSum = 20_000;
        double cashback = 800;
        double actualCashback = darkDebitCard.calculatingCashback(receivedSum);
        Assertions.assertEquals(cashback, actualCashback);
    }

    @Test
    public void calculatingBonusPointsTest() {
        double receivedSum = 10_000;
        double cashback = 50;
        double actualCashback = darkDebitCard.calculatingBonusPoints(receivedSum);
        Assertions.assertEquals(cashback, actualCashback);
    }

    // Проверка удачного пополнения
    @Test
    public void successfulReplenishmentTopUp() {
        darkDebitCard.balance = 0;
        darkDebitCard.topUp(100);
        Assertions.assertEquals(100, darkDebitCard.getBalance());
    }

    // Проверка неудачной попытки пополнения
    @Test
    public void negativeSumTopUp() {
        darkDebitCard.balance = 0;
        darkDebitCard.topUp(-100);
        Assertions.assertEquals(0.0, darkDebitCard.getBalance());
    }
}
