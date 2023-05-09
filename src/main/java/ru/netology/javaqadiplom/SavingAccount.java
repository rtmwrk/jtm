package ru.netology.javaqadiplom;

/**
 * Сберегательный счёт
 * Может иметь баланс только в пределах от указанного минимального до указанного максимального включительно.
 * Не может уходить в минус (минимальный баланс не может быть отрицательным).
 * Имеет ставку - количество процентов годовых на остаток.
 */
public class SavingAccount extends Account {
    protected int minBalance;
    protected int maxBalance;

    /**
     * Создаёт новый объект сберегательного счёта с заданными параметрами.
     * Если параметры некорректны (мин. баланс больше максимального и так далее), то
     * должно выкидываться исключения вида IllegalArgumentException.
     * @param initialBalance - начальный баланс
     * @param minBalance - минимальный баланс
     * @param maxBalance - максимальный баланс
     * @param rate - неотрицательное число, ставка в процентах годовых на остаток
     */
    public SavingAccount(int initialBalance, int minBalance, int maxBalance, int rate) {
        // minBalance должен быть неотрицательным, что следует из следующих условий задания:
        // - баланс должен быть неотрицательным;
        // - баланс находится в пределах от минимального до максимального.
        if (minBalance < 0) {
            throw new IllegalArgumentException(
                    "Минимальный баланс не может быть отрицательным, а у вас: " + minBalance
            );
        }
        // maxBalance должен быть > minBalance, считаем, что они не могут быть равными (это логично),
        // хотя прямого такого требования нет
        if (maxBalance <= minBalance) {
            throw new IllegalArgumentException(
                    "Максимальный баланс должен быть больше минимального, а у вас: \n" +
                    "- минимальный баланс: " + minBalance + "\n;" +
                    "- максимальный баланс: " + maxBalance
            );
        }
        // initialBalance должен быть >= minBalance и <= maxBalance
        if ((initialBalance < minBalance) || (initialBalance > maxBalance)) {
            throw new IllegalArgumentException(
                    "Начальный баланс должен быть в пределах от минимального до максимального, а у вас: \n" +
                            "- начальный баланс: " + initialBalance + "\n;" +
                            "- минимальный баланс: " + minBalance + "\n;" +
                            "- максимальный баланс: " + maxBalance
            );
        }
        // 3-ри предыдущих проверки обеспечат в том числи и проверку на неотрицательный баланс
        // rate должен быть > 0
        if (rate < 0) {
            throw new IllegalArgumentException(
              "Накопительная ставка не может быть отрицательной, а у вас: " + rate
            );
        }
        // Запоминаем переданные через параметры данные в поля объекта
        this.balance = initialBalance;
        this.minBalance = minBalance;
        this.maxBalance = maxBalance;
        this.rate = rate;
    }

    /**
     * Операция оплаты с карты на указанную сумму.
     * В результате успешного вызова этого метода, баланс должен уменьшиться
     * на сумму покупки. Если же операция может привести к некорректному
     * состоянию счёта (например, баланс может уйти в минус), то операция должна
     * завершиться вернув false и ничего не поменяв на счёте.
     * @param amount - сумма покупки
     * @return true если операция прошла успешно, false иначе.
     */
    @Override
    public boolean pay(int amount) {
        if (amount <= 0) {
            return false;
        }
        if ((balance - amount) >= minBalance) {                 // добавляем требование "=>" balance может = minBalance
            balance = balance - amount;                         // добавляем операцию "уменьшение счета"
            return true;
        } else {
            return false;
        }
    }

    /**
     * Операция пополнения карты на указанную сумму.
     * В результате успешного вызова этого метода, баланс должен увеличиться
     * на сумму покупки. Если же операция может привести к некорректному
     * состоянию счёта, то операция должна
     * завершиться вернув false и ничего не поменяв на счёте.
     * @param amount - сумма пополнения
     * @return true если операция прошла успешно, false иначе.
     * @param amount
     * @return
     */
    @Override
    public boolean add(int amount) {
        if (amount <= 0) {
            return false;
        }
        if ((balance + amount) <= maxBalance) {                 // добавляем требование "<=" balance может = maxBalance
            balance = balance + amount;                         // добавляем операцию "увеличение счета"
            return true;
        } else {
            return false;
        }
    }

    /**
     * Операция расчёта процентов на остаток счёта при условии, что
     * счёт не будет меняться год. Сумма процентов приводится к целому
     * числу через отбрасывание дробной части (так и работает целочисленное деление).
     * Пример: если на счёте 200 рублей, то при ставке 15% ответ должен быть 30.
     * @return
     */
    @Override
    public int yearChange() {
        return balance / 100 * rate;
    }

    public int getMinBalance() {
        return minBalance;
    }

    public int getMaxBalance() {
        return maxBalance;
    }
}
