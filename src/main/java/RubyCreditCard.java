public class RubyCreditCard extends CreditCard {

    private double creditBalance;
    private int giftCertificateCount;
    private double fundsSpent = 0;
    private double cashback;
    private double yearOfUseSet;

    private final double MIN_CASHBACK_RATE = 5000;
    private final int CASHBACK_PERCENTAGE = 5;
    private final int DOUBLING_CASHBACK = 2;
    private final double MIN_CERTIFICATE_RATE = 50_000;
    private final int PRICE_CERTIFICATE = 1000;
    private final int YEAR_OF_USE = 3;


    public RubyCreditCard(double balance, double creditLimit) {
        super(balance, creditLimit);
        creditBalance = creditLimit;
    }

    public double loyaltyProgramForRegularCustomers(double receivedSum) {
        if (yearOfUseSet >= YEAR_OF_USE) {
            cashback += receivedSum * (CASHBACK_PERCENTAGE * DOUBLING_CASHBACK) / 100;
        } else if (receivedSum >= MIN_CASHBACK_RATE) {
            cashback += receivedSum * CASHBACK_PERCENTAGE / 100;
        }
        return cashback;

    }

    public void setYearOfUseSet(double yearOfUseSet) {
        this.yearOfUseSet = yearOfUseSet;
    }

    public void gettingCertificate(double receivedSum) {
        fundsSpent += receivedSum;
        giftCertificateCount += fundsSpent / MIN_CERTIFICATE_RATE;
    }

    public double getFundsSpent() {
        return fundsSpent;
    }

    public int getGiftCertificateCount() {
        return giftCertificateCount;
    }

    @Override
    public boolean toPay(double receivedSum) {
        if (receivedSum > 0 && balance + creditBalance > receivedSum) { // Проверка на наличие средств
            if (balance >= receivedSum) {
                balance -= receivedSum;
                loyaltyProgramForRegularCustomers(receivedSum);
                gettingCertificate(receivedSum);
                System.out.println("Сумма списания: " + receivedSum);
                return true;
            } else {
                loyaltyProgramForRegularCustomers(receivedSum);
                gettingCertificate(receivedSum);
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
                "\nКоличество сертификатов: " + giftCertificateCount + " на сумму " + PRICE_CERTIFICATE;
    }
}
