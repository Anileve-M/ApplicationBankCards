import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        DebitCard debitCard = new DebitCard(1000);
        //debitCardMet(debitCard);

        CreditCard creditCard = new CreditCard(1030, 10_000);
        //creditCardMet(creditCard, scanner);

        LightDebitCard lightDebitCard = new LightDebitCard(4500);
        //lightDebitCardMet(lightDebitCard);

        DarkDebitCard darkDebitCard = new DarkDebitCard(1200);
        //darkDebitCardMet(darkDebitCard);

        GoldenCreditCard goldenCreditCard = new GoldenCreditCard(2452, 15000);
        //goldenCreditCardMet(goldenCreditCard);

        RubyCreditCard rubyCreditCard = new RubyCreditCard(3525, 5000);
        //rubyCreditCardMet(rubyCreditCard, scanner);
    }

    public static void rubyCreditCardMet(RubyCreditCard rubyCreditCard, Scanner scanner) {
        System.out.println(rubyCreditCard.infoBalance());
        System.out.println("Сколько лет вы пользуетесь картой 'RubyCreditCard'?");
        double yearOfUse = scanner.nextDouble();
        rubyCreditCard.setYearOfUseSet(yearOfUse);
        rubyCreditCard.toPay(3500);
        rubyCreditCard.topUp(150_500);
        rubyCreditCard.toPay(145_000);
        System.out.println(rubyCreditCard.infoBalance());
    }

    public static void goldenCreditCardMet(GoldenCreditCard goldenCreditCard) {
        System.out.println(goldenCreditCard.infoBalance());
        goldenCreditCard.topUp(251);
        goldenCreditCard.toPay(12000);
        System.out.println(goldenCreditCard.infoBalance());
    }

    public static void darkDebitCardMet(DarkDebitCard darkDebitCard) {
        System.out.println(darkDebitCard.infoBalance());
        darkDebitCard.topUp(1500);
        darkDebitCard.toPay(125);
        System.out.println(darkDebitCard.infoBalance());
    }

    public static void lightDebitCardMet(LightDebitCard lightDebitCard) {
        System.out.println(lightDebitCard.infoBalance());
        lightDebitCard.topUp(2000);
        lightDebitCard.toPay(5000);
        System.out.println(lightDebitCard.infoBalance());
    }

    public static void creditCardMet(CreditCard creditCard, Scanner scanner) {
        boolean isIni = true;

        while (isIni) {
            System.out.println("1. Получить информацию о балансе\n" +
                    "2. Посмотреть лимит кредитной карты\n" +
                    "3. Пополнить баланс\n" +
                    "4. Оплатить\n" +
                    "0. Выход\n");

            int answer = scanner.nextInt();

            switch (answer) {
                case 1:
                    System.out.println(creditCard.infoBalance() + "\n");
                    break;
                case 2:
                    System.out.println("Лимит кредитной карты: " + creditCard.getCreditLimit() + "\n");
                    break;
                case 3:
                    System.out.print("Укажите сумму пополнения: ");
                    double sumTopUp = scanner.nextDouble();
                    creditCard.topUp(sumTopUp);
                    System.out.println(creditCard.infoBalance() + "\n");
                    break;
                case 4:
                    System.out.print("Укажите сумму оплаты: ");
                    double sumTopPay = scanner.nextDouble();
                    creditCard.toPay(sumTopPay);
                    System.out.println(creditCard.infoBalance() + "\n");
                    break;
                case 0:
                    isIni = false;
                    System.out.println("End...");
                    break;
                default:
                    System.out.println("Введены некорректные данные!");
                    break;
            }
        }
    }

    public static void debitCardMet(DebitCard debitCard) {
        System.out.println(debitCard.infoBalance());
        debitCard.topUp(1500);
        debitCard.toPay(125);
        System.out.println(debitCard.infoBalance());
    }
}
