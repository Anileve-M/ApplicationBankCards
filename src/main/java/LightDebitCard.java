public class LightDebitCard extends DebitCard {

    private double cashback;
    private double accumulation;

    private final double MIN_CASHBACK_RATE = 5000;
    private final int CASHBACK_PERCENTAGE = 3;
    private final double ACCUMULATION_PERCENTAGE = 0.005;

    public LightDebitCard(double balance) {
        super(balance);
    }

    public double calculatingCashback(double receivedSum) {
        if (receivedSum >= MIN_CASHBACK_RATE) {
            cashback += receivedSum * CASHBACK_PERCENTAGE / 100;
        }
        return cashback;
    }

    @Override
    public boolean toPay(double receivedSum) {
        if (balance > 0 && receivedSum > 0) {
            if (balance >= receivedSum) {
                balance -= receivedSum;
                calculatingCashback(receivedSum);
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
    public void topUp(double depositSum) {
        if (depositSum > 0) {
            balance += depositSum;
            accumulation += depositSum * ACCUMULATION_PERCENTAGE / 100;
            System.out.println("Сумма пополнения: " + depositSum);
        } else {
            System.out.println("Введены некорректные данные!");
        }
    }

    public void setAccumulation(double accumulation) {
        this.accumulation = accumulation;
    }

    public double getAccumulation() {
        return accumulation;
    }

    @Override
    public String infoBalance() {
        return "Собственные средства: " + String.format("%.2f", balance) +
                "\nКэшбэк: " + String.format("%.2f", cashback) +
                "\nНакопления: " + String.format("%.2f", accumulation);
    }
}
