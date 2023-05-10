package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SavingAccountTest {

//    Тесты для оплаты

    @Test
    public void shouldPayUpAverage() { /* Среднее */
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.pay(500);

        Assertions.assertEquals(2_000 - 500, account.getBalance());
    }

    @Test
    public void shouldPayUpToMinBalance() { /* Оплата до мин лимита */
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.pay(1_000);

        Assertions.assertEquals(2_000 - 1_000, account.getBalance());
    }

    @Test
    public void shouldNotPayBellowMinBalance() { /* Оплата ниже мин лимита не должна проходить */
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.pay(1_500);


        Assertions.assertEquals(2_000, account.getBalance());
    }

    @Test
    public void shouldPayToZero() { /* Оплата до нуля с мин лимитом нуль */
        SavingAccount account = new SavingAccount(
                2_000,
                0,
                10_000,
                5
        );

        account.pay(2_000);

        Assertions.assertEquals(0, account.getBalance());
    }

    @Test
    public void shouldNotPayBellowZero() { /* Счет не должен уходить в минус */
        SavingAccount account = new SavingAccount(
                2_000,
                0,
                10_000,
                5
        );

        account.pay(3_000);

        Assertions.assertEquals(2_000, account.getBalance());
    }

    @Test
    public void shouldNotAcceptNullForPayment() { /* Сумма оплаты не может быть равна нулю */
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.pay(0);

        Assertions.assertEquals(2_000, account.getBalance());
    }

    @Test
    public void shouldNotAcceptNegativeNumbersForPayment() { /* Сумма оплаты не может быть отрицательной */
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.pay(-1);

        Assertions.assertEquals(2_000, account.getBalance());
    }

//    Тесты для пополнения

    @Test
    public void shouldAddAverage() { /* Среднее */
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.add(3_000);

        Assertions.assertEquals(2_000 + 3_000, account.getBalance());
    }

    @Test
    public void shouldAddToMaxBalance() { /* Пополнение счета до верхнего лимита */
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.add(8_000);

        Assertions.assertEquals(2_000 + 8_000, account.getBalance());
    }

    @Test
    public void shouldNotAddAboveMaxBalance() { /* Пополнение счета не должно превышать верхний лимит */
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.add(9_000);

        Assertions.assertEquals(2_000, account.getBalance());
    }

    @Test
    public void shouldNotAcceptNullForDeposit() { /* Пополнение счета не должно принимать ноль */
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.add(0);

        Assertions.assertEquals(2_000, account.getBalance());
    }

    @Test
    public void shouldNotAcceptNegativeNumbersForDeposit() { /* Пополнение счета не должно принимать отрицательные */
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.add(-1);

        Assertions.assertEquals(2_000, account.getBalance());
    }

//    Тест процентной ставки

    @Test
    public void shouldCalcRateAverage() { /* Среднее */
        SavingAccount account = new SavingAccount(
                200,
                100,
                10_000,
                15
        );

        Assertions.assertEquals(30, account.yearChange());
    }

//    Тест ниже должен работать, не смог придумать решение
//    однако "бросает" ошибку как и задумано

    @Test
    public void shouldNotAcceptNegativeRate() {
        Assertions.assertThrows(IllegalArgumentException.class,() -> {
           SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                -1);
        });
    }

//    Нужны правила для лимитов баланса(min, max) чтобы можно было проверить
//
//    @Test
//    public void shouldNotAcceptBalanceBellowMin() {
//        SavingAccount account = new SavingAccount(
//                1_000,
//                2_000,
//                10_000,
//                5
//        );
//
//        Assertions.assertThrows(IllegalArgumentException.class, () -> {
//            account.();
//        });
//    }
//
//    @Test
//    public void shouldNotAcceptBalanceAboveMax() {
//        SavingAccount account = new SavingAccount(
//                11_000,
//                2_000,
//                10_000,
//                5
//        );
//
//        Assertions.assertThrows(IllegalArgumentException.class, () -> {
//            account.();
//        });
//    }
}
