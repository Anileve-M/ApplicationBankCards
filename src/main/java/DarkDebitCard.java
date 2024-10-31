public class DarkDebitCard extends DebitCard {

    private double bonusPoints;
    private double cashback;

    private final double MIN_CASHBACK_RATE = 7000;
    private final int CASHBACK_PERCENTAGE = 4;
    private final double BONUS_PERCENTAGE = 0.5;

    public DarkDebitCard(double balance) {
        super(balance);
    }

    public double calculatingCashback(double receivedSum) {
        if (receivedSum >= MIN_CASHBACK_RATE) {
            cashback += receivedSum * CASHBACK_PERCENTAGE / 100;
        }
        return cashback;
    }

    public double calculatingBonusPoints(double receivedSum) {
        bonusPoints += receivedSum * BONUS_PERCENTAGE / 100;
        return bonusPoints;
    }

    @Override
    public boolean toPay(double receivedSum) {
        if (balance > 0 && receivedSum > 0) {
            if (balance >= receivedSum) {
                balance -= receivedSum;
                calculatingCashback(receivedSum);
                calculatingBonusPoints(receivedSum);
                System.out.println("Сумма списания: " + receivedSum);
                return true;
            } else {
                System.out.println("На вашем счете недостаточно средств!\n");
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public String infoBalance() {
        return "Собственные средства: " + String.format("%.2f", balance) +
                "\nКэшбэк: " + String.format("%.2f", cashback) +
                "\nБонусные баллы: " + String.format("%.2f", bonusPoints);
    }
}
