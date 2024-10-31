public class CreditCard extends BankCard {

    private double creditLimit;
    private double creditBalance;

    public CreditCard(double balance, double creditLimit) {
        super(balance);
        this.creditLimit = creditLimit;
        creditBalance = creditLimit;
    }

    public double getCreditLimit() {
        return creditLimit;
    }

    @Override
    public boolean toPay(double receivedSum) {
        if (receivedSum > 0 && balance + creditBalance > receivedSum) {
            if (balance >= receivedSum) {
                balance -= receivedSum;
                System.out.println("Сумма списания: " + receivedSum);
                return true;
            } else {
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

    public void topUp(double depositSum) {
        if (depositSum > 0) {
            if (creditBalance < creditLimit && creditBalance >= 0) {
                double maximumDepositSum = creditLimit - creditBalance;
                if (depositSum <= maximumDepositSum) {
                    creditBalance += depositSum;
                    System.out.println("Сумма пополнения: " + depositSum);
                } else {
                    creditBalance += maximumDepositSum;
                    balance += depositSum - maximumDepositSum;
                    System.out.println("Сумма пополнения: " + depositSum);
                }
            } else {
                balance += depositSum;
                System.out.println("Сумма пополнения: " + depositSum);
            }

        } else {
            System.out.println("Введены некорректные данные!");
        }
    }

    @Override
    public String infoBalance() {
        return "Собственные средства: " + String.format("%.2f", balance) +
                "\nКредитные средства: " + String.format("%.2f", creditBalance) +
                "\nКредитный лимит: " + String.format("%.2f", creditLimit);
    }


}
