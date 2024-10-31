import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LightDebitCardTest {
    private LightDebitCard lightDebitCard;

    @BeforeEach
    public void setup() {
        lightDebitCard = new LightDebitCard(1000);
    }

    // Проверка успешной оплаты
    @Test
    public void ToPayTest() {
        Assertions.assertTrue(lightDebitCard.toPay(1000));
    }

    // Проверка неудачной попытки оплаты
    @Test
    public void negativeToPayTest() {
        Assertions.assertFalse(lightDebitCard.toPay(-1000));
    }

    // Проверка оставшегося баланса
    @Test
    public void sumReceiptToPayTest() {
        Assertions.assertTrue(lightDebitCard.toPay(100));
        Assertions.assertEquals(900, lightDebitCard.getBalance());
    }

    // Проверка оплаты с нулевым балансом
    @Test
    public void negativeSumReceiptToPayTest() {
        lightDebitCard.balance = 0;
        Assertions.assertFalse(lightDebitCard.toPay(500));
    }

    // Проверка оплаты на 0
    @Test
    public void paymentZeroToPayTest() {
        Assertions.assertFalse(lightDebitCard.toPay(0));
    }

    // Проверка успешного кэшбэка
    @Test
    public void checkingSuccessfulCashbackСalculatingCashback() {
        double receivedSum = 20_000;
        double cashback = 600;
        double actualCashback = lightDebitCard.calculatingCashback(receivedSum);
        Assertions.assertEquals(cashback, actualCashback);
    }

    // Проверка удачного пополнения
    @Test
    public void successfulReplenishmentTopUp() {
        lightDebitCard.balance = 0;
        lightDebitCard.topUp(100);
        Assertions.assertEquals(100, lightDebitCard.getBalance());
    }

    // Проверка успешных накоплений
    @Test
    public void successfulAccumulationTopUp() {
        double depositSum = 500;
        double accumulation = 0.025;
        lightDebitCard.topUp(depositSum);
        Assertions.assertEquals(accumulation, lightDebitCard.getAccumulation());
    }

    // Проверка неудачной попытки пополнения
    @Test
    public void negativeSumTopUp() {
        lightDebitCard.balance = 0;
        lightDebitCard.topUp(-100);
        Assertions.assertEquals(0.0, lightDebitCard.getBalance());
    }

}
