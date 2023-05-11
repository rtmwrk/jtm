package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SavingAccountTest {

//    Тест исключений

    @Test
    public void shouldNotAcceptNegativeMinBalance() { /* Нижний лимит не может быть отрицательным */
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            SavingAccount account = new SavingAccount(
                    2_000,
                    -1_000,
                    10_000,
                    5);
        });
    }

    @Test
    public void shouldNotAcceptMinBalanceBiggerThanMax() { /* Верхний лимит должен быть больше нижнего */
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            SavingAccount account = new SavingAccount(
                    2_000,
                    11_000,
                    10_000,
                    5);
        });
    }

    @Test
    public void shouldNotAcceptMinBalanceBiggerThanInitial() { /* Баланс не может уходить за нижний лимит */
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            SavingAccount account = new SavingAccount(
                    2_000,
                    3_000,
                    10_000,
                    5);
        });
    }

    @Test
    public void shouldNotAcceptInitialBalanceBiggerThanMax() { /* Баланс не может превышать верхний лимит */
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            SavingAccount account = new SavingAccount(
                    11_000,
                    3_000,
                    10_000,
                    5);
        });
    }

    @Test
    public void shouldNotAcceptNegativeRate() { /* Ставка не может быть отрицательной */
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            SavingAccount account = new SavingAccount(
                    2_000,
                    1_000,
                    10_000,
                    -1);
        });
    }

//    Тесты для оплаты

    @Test
    public void shouldPayUpAverage() { /* Среднее */
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        int oldBalance = account.getBalance(); /* Запоминает значение баланса счета */
        Assertions.assertTrue(account.pay(500)); /* Пытаемся провести покупку */

        Assertions.assertEquals(oldBalance - 500, account.getBalance());
    }

    @Test
    public void shouldPayUpToMinBalance() { /* Оплата до мин лимита */
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        int oldBalance = account.getBalance(); /* Запоминает значение баланса счета */
        Assertions.assertTrue(account.pay(1_000)); /* Пытаемся провести покупку */

        Assertions.assertEquals(oldBalance - 1_000, account.getBalance());
    }

    @Test
    public void shouldNotPayBellowMinBalance() { /* Оплата ниже мин лимита не должна проходить */
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        int oldBalance = account.getBalance(); /* Запоминает значение баланса счета */
        Assertions.assertFalse(account.pay(1_500)); /* Пытаемся провести покупку */

        Assertions.assertEquals(oldBalance, account.getBalance());
    }

    @Test
    public void shouldPayToZero() { /* Оплата до нуля с мин лимитом нуль */
        SavingAccount account = new SavingAccount(
                2_000,
                0,
                10_000,
                5
        );

        int oldBalance = account.getBalance(); /* Запоминает значение баланса счета */
        Assertions.assertTrue(account.pay(2_000)); /* Пытаемся провести покупку */

        Assertions.assertEquals(oldBalance - 2000, account.getBalance());
    }

    @Test
    public void shouldNotPayBellowZero() { /* Счет не должен уходить в минус */
        SavingAccount account = new SavingAccount(
                2_000,
                0,
                10_000,
                5
        );

        int oldBalance = account.getBalance(); /* Запоминает значение баланса счета */
        Assertions.assertFalse(account.pay(3_000)); /* Пытаемся провести покупку */

        Assertions.assertEquals(oldBalance, account.getBalance());
    }

    @Test
    public void shouldNotAcceptNullForPayment() { /* Сумма оплаты не может быть равна нулю */
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        int oldBalance = account.getBalance(); /* Запоминает значение баланса счета */
        Assertions.assertFalse(account.pay(0)); /* Пытаемся провести покупку */

        Assertions.assertEquals(oldBalance, account.getBalance());
    }

    @Test
    public void shouldNotAcceptNegativeNumbersForPayment() { /* Сумма оплаты не может быть отрицательной */
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        int oldBalance = account.getBalance(); /* Запоминает значение баланса счета */
        Assertions.assertFalse(account.pay(-1)); /* Пытаемся провести покупку */

        Assertions.assertEquals(oldBalance, account.getBalance());
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

        int oldBalance = account.getBalance(); /* Запоминает значение баланса счета */
        Assertions.assertTrue(account.add(3_000)); /* Пытаемся пополнить счет */

        Assertions.assertEquals(oldBalance + 3_000, account.getBalance());
    }

    @Test
    public void shouldAddToMaxBalance() { /* Пополнение счета до верхнего лимита */
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        int oldBalance = account.getBalance(); /* Запоминает значение баланса счета */
        Assertions.assertTrue(account.add(8_000)); /* Пытаемся пополнить счет */

        Assertions.assertEquals(oldBalance + 8_000, account.getBalance());
    }

    @Test
    public void shouldNotAddAboveMaxBalance() { /* Пополнение счета не должно превышать верхний лимит */
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        int oldBalance = account.getBalance(); /* Запоминает значение баланса счета */
        Assertions.assertFalse(account.add(9_000)); /* Пытаемся пополнить счет */

        Assertions.assertEquals(oldBalance, account.getBalance());
    }

    @Test
    public void shouldNotAcceptNullForDeposit() { /* Пополнение счета не должно принимать ноль */
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        int oldBalance = account.getBalance(); /* Запоминает значение баланса счета */
        Assertions.assertFalse(account.add(0)); /* Пытаемся пополнить счет */

        Assertions.assertEquals(oldBalance, account.getBalance());
    }

    @Test
    public void shouldNotAcceptNegativeNumbersForDeposit() { /* Пополнение счета не должно принимать отрицательные */
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        int oldBalance = account.getBalance(); /* Запоминает значение баланса счета */
        Assertions.assertFalse(account.add(-1)); /* Пытаемся пополнить счет */

        Assertions.assertEquals(oldBalance, account.getBalance());
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

//    Тесты для геттеров getMinBalance, getMaxBalance

    @Test
    public void shouldGetMinBalance() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        Assertions.assertEquals(account.minBalance, account.getMinBalance());
    }

    @Test
    public void shouldGetMaxBalance() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        Assertions.assertEquals(account.maxBalance, account.getMaxBalance());
    }
}