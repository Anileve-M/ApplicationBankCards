public class DebitCard extends BankCard {

    public DebitCard(double balance) {
        super(balance);
    }

    public void topUp(double depositSum) {
        if (depositSum > 0) {
            balance += depositSum;
            System.out.println("Сумма пополнения: " + depositSum);
        } else {
            System.out.println("Введены некорректные данные!");
        }
    }

    @Override
    public String infoBalance() {
        return "Собственные средства: " + String.format("%.2f", balance);
    }
}
