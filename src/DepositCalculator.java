import java.util.Scanner;

public class DepositCalculator {
    static final int DIGITS = 2;

    public static void main(String[] args) {
        new DepositCalculator().calculateDeposit();
    }

    double calculateComplexPercent(double amount, double yearRate, int depositPeriod) {
        double payment = amount * Math.pow((1 + yearRate / 12), 12 * depositPeriod);
        return round(payment, DIGITS);
    }

    double calculateSimplePercent(double amount, double yearRate, int depositPeriod) {
        double payment = amount + amount * yearRate * depositPeriod;
        return round(payment, DIGITS);
    }

    double round(double value, int digits) {
        double scale = Math.pow(10, digits);
        return Math.round(value * scale) / scale;
    }

    void calculateDeposit() {
        int period;
        double amount;
        double payment;
        double yearRate = 0.06;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите сумму вклада в рублях:");
        amount = scanner.nextDouble();
        System.out.println("Введите срок вклада в годах:");
        period = scanner.nextInt();
        System.out.println("Выберите тип вклада, 1 - вклад с обычным процентом, 2 - вклад с капитализацией:");
        switch (scanner.nextInt()) {
            case 1 -> payment = calculateSimplePercent(amount, yearRate, period);
            case 2 -> payment = calculateComplexPercent(amount, yearRate, period);
            default -> {
                System.out.println("Выбран несуществующий тип вклада");
                return;
            }
        }
        System.out.println("Результат вклада cо ставкой " + yearRate * 100 + " % годовых :" + amount
                + " рублей за " + period + " лет превратятся в " + payment + " рублей");
    }
}
