import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GoldenCreditCardTest {

    private GoldenCreditCard goldenCreditCard;

    @BeforeEach
    public void setup() {
        goldenCreditCard = new GoldenCreditCard(1000, 10_000);
    }

    // Проверка успешной оплаты
    @Test
    public void ToPayTest() {
        Assertions.assertTrue(goldenCreditCard.toPay(1000));
    }

    // Проверка неудачной попытки оплаты
    @Test
    public void negativeToPayTest() {
        Assertions.assertFalse(goldenCreditCard.toPay(-1000));
    }

    // Проверка оставшегося баланса
    @Test
    public void sumReceiptToPayTest() {
        Assertions.assertTrue(goldenCreditCard.toPay(100));
        Assertions.assertEquals(900, goldenCreditCard.getBalance());
    }

    // Проверка оплаты с нулевым балансом
    @Test
    public void negativeSumReceiptToPayTest() {
        goldenCreditCard.balance = 0;
        Assertions.assertFalse(goldenCreditCard.toPay(500));
    }

    // Проверка оплаты на 0
    @Test
    public void paymentZeroToPayTest() {
        Assertions.assertFalse(goldenCreditCard.toPay(0));
    }

    // Проверка успешного кэшбэка
    @Test
    public void checkingSuccessfulCashbackСalculatingCashback() {
        double receivedSum = 1000;
        double cashback = 0;
        double actualCashback = goldenCreditCard.calculatingCashback(receivedSum);
        Assertions.assertEquals(cashback, actualCashback);
    }

    // Проверка успешного выполнения бонусов мили
    @Test
    public void calculatingBonusMilesTest() {
        double receivedSum = 5000;
        double bonusMiles = 100;
        double actualBonusMiles = goldenCreditCard.calculatingBonusMiles(receivedSum);
        Assertions.assertEquals(bonusMiles, actualBonusMiles);
    }
}
