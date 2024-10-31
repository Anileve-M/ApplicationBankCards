public class GoldenCreditCard extends CreditCard {

    private double creditBalance;
    private double cashback;
    private double bonusMiles;

    private final double MIN_CASHBACK_RATE = 3000;
    private final int CASHBACK_PERCENTAGE = 1;
    private final double MIN_BONUS_MILES_RATE = 50;

    public GoldenCreditCard(double balance, double creditLimit) {
        super(balance, creditLimit);
        creditBalance = creditLimit;
    }

    public double calculatingCashback(double receivedSum) {
        if (receivedSum >= MIN_CASHBACK_RATE) {
            cashback += receivedSum * CASHBACK_PERCENTAGE / 100;
        }
        return cashback;
    }

    public double calculatingBonusMiles(double receivedSum) {
        bonusMiles += receivedSum / MIN_BONUS_MILES_RATE;
        return bonusMiles;
    }

    @Override
    public boolean toPay(double receivedSum) {
        if (receivedSum > 0 && balance + creditBalance > receivedSum) {
            if (balance >= receivedSum) {
                balance -= receivedSum;
                calculatingCashback(receivedSum);
                calculatingBonusMiles(receivedSum);
                System.out.println("Сумма списания: " + receivedSum);
                return true;
            } else {
                calculatingCashback(receivedSum);
                calculatingBonusMiles(receivedSum);
                System.out.println("Сумма списания: " + receivedSum);
                receivedSum -= balance;
                balance = 0;
                creditBalance -= receivedSum;
                return false;
            }
        } else {
            System.out.println("На вашем счете недостаточно средств!\n");
            return false;
        }
    }

    @Override
    public String infoBalance() {
        return "Собственные средства: " + String.format("%.2f", balance) +
                "\nКредитные средства: " + String.format("%.2f", creditBalance) +
                "\nКредитный лимит: " + String.format("%.2f", getCreditLimit()) +
                "\nКэшбек: " + String.format("%.2f", cashback) +
                "\nБонусне мили: " + String.format("%.2f", bonusMiles);

    }
}
