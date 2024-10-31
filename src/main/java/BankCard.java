public abstract class BankCard {

    protected double balance;

    public BankCard(double balance) {
        this.balance = balance;
    }

    public boolean toPay(double receivedSum) {
        if (balance > 0 && receivedSum > 0) {
            if (balance >= receivedSum) {
                balance -= receivedSum;
                System.out.println("Сумма списания: " + receivedSum);
                return true;
            } else {
                System.out.println("На вашем счете недостаточно средств!");
                return false;
            }
        } else {
            System.out.println("Error...");
            return false;
        }
    }

    public abstract String infoBalance();

    public double getBalance() {
        return balance;
    }
}
