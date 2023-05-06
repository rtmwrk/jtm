package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreditAccountTest {

    /**
     * Тесты конструктора класса "CreditAccount".
     * Осуществим позитивное и негативное функциональное тестирование конструктора класса.
     * Конструктор имеет 3-ри параметра:
     * - initialBalance - неотрицательное число, начальный баланс для счёта,
     * - creditLimit - неотрицательное число, максимальная сумма которую можно задолжать банку,
     * - rate - неотрицательное число, ставка кредитования для расчёта долга за отрицательный баланс.
     * Тестирование проведем по каждому параметру, используя методики "эквивалентных" и "граничных значений",
     * плюс протестируем логические условия работы конструктора.            .
     */

    // Проведем функциональное позитивное тестирование конструктора методом "эквивалентных значений", при
    // передаче конструктору параметров, ПОДОБРАННЫХ специальным образом.
    // Тест должен завершаться без ошибки.
    @Test
    public void shouldCreditAccountPositiveTestInitialBalanceValueOne() {
        CreditAccount account = new CreditAccount(
                5_000,
                5_000,
                15
        );

        Assertions.assertEquals(5_000, account.balance);
        Assertions.assertEquals( 5_000, account.creditLimit);
        Assertions.assertEquals(15, account.rate);
    }

    // По результату изучения логических условий задания видим изначальную ошибку в коде конструктора,
    // а именно - наличие лишнего параметра "initialBalance".
    // Так, по условию задания "баланс кредитного счёта изначально выставляется в кредитный лимит",
    // т.е. "account.balance" = "creditLimit". Очевидно, что параметр "initialBalance" конструктора
    // лишний. В конструкторе просто должна быть строка кода "this.balance = creditLimit;" вместо
    // "this.balance = initialBalance;".
    // При этом, наличие у конструктора отдельного параметра "initialBalance" позволяет задавать любое, в
    // том числе и неверное, значение "account.balance", что нарушет требование условия задания.
    // Подтвердим наш вывод тестом и зададим "account.balance" значение, отличное от "creditLimit".
    @Test
    public void shouldInitialBalansNotEqualCreditLimit() {
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );

        Assertions.assertEquals(5_000, account.balance);
    }
    // !!! Тест предсказуемо дает ошибку, так как конструктор не устанавливает баланс счета в "creditLimit".
    // В дальнейшем, чтобы в тестах не "отлавливать" постоянно данную ошибку конструктора, а проверять интересующие
    // нас требования, будем выставлять initialBalance = creditLimit, если иное не будет требоваться.

    // В виду указанного выше тестирование параметра конструктора "initialBalance" не имеет смысла, данный
    // параметр необходимо просто убрать из кода конструктора.

    // А вот другие параметры конструктора необходимо тестировать.
    // Проведем функциональный, позитивный тест параметра "creditLimit" конструктора.
    // Тест реализует методику "эквивалентных значений" в целом по параметру и "граничных значений" параметра при
    // "creditLimit" = 1.
    // Тест должен завершаться без ошибки.
    @Test
    public void shouldCreditAccountPositiveTestCreditLimitValueOne() {
        CreditAccount account = new CreditAccount(
                1,
                1,
                15
        );

        Assertions.assertEquals( 1, account.creditLimit);
    }

    // Функциональный, позитивный тест параметра "creditLimit" конструктора.
    // Методика "граничных значений" по значению "CreditLimit" = 0.
    // Тест должен завершаться без ошибки.
    @Test
    public void shouldCreditAccountPositiveTestCreditLimitValueZero() {
        CreditAccount account = new CreditAccount(
                0,
                0,
                15
        );

        Assertions.assertEquals(0, account.creditLimit);
    }

    // Функциональный негативный тест конструктора.
    // Методика "граничных значений" по значению "creditLimit" = -1.
    // !!! Тест должен завершаться вызовом исключения, но тест проходит.
    // Вывод - еще одна ошибка в коде конструктора.
    @Test
    public void shouldCreditAccountNegativeTestCreditLimitValueMinusOne() {
        Assertions.assertThrows(IllegalArgumentException.class,() -> {
            CreditAccount account = new CreditAccount(-1,           // тест вызова исключения
                                                                 -1,
                                                             15);
        });
    }

    // Функциональный, позитивный тест параметра "rate" конструктора.
    // Тест реализует методику "эквивалентных значений" в целом по конструктору и граничных значений
    // параметра "rate" = 1.
    // Тест должен завершаться без ошибки.
    @Test
    public void shouldCreditAccountPositiveTestRateValueOne() {
        CreditAccount account = new CreditAccount(
                5_000,
                5_000,
                1
        );

        Assertions.assertEquals( 1, account.rate);
    }

    // Функциональный, позитивный тест параметра "rate" конструктора.
    // Методика "граничных значений" по параметру "rate" = 0. По условию данный параметр может принимать
    // неотрицательные значения. Значит при значении rate = 0 тест должен проходить, но по факту тест завершается
    // исключительной ситуацие, что является ошибкой.
    // !!! Ошибка в коде рализации конструктора.
    @Test
    public void shouldCreditAccountPositiveTestRateValueZero() {
        CreditAccount account = new CreditAccount(
                5_000,
                5_000,
                0
        );

        Assertions.assertEquals(0, account.rate);
    }

    // Функциональный негативный тест параметра "rate" конструктора.
    // Методика "граничных значений" по значению "rate" = -1.
    // Тест должен завершаться с ошибкой, должна быть исключительная ситуация, что по факту и происходит.
    // Конструктор верно отрабатывает данную ситуаци.
    @Test
    public void shouldCreditAccountNegativeTestRateValueMinusOne() {
        Assertions.assertThrows(IllegalArgumentException.class,() -> {     // Тест вызова исключения при
            CreditAccount account = new CreditAccount(5_000,    // отрицательном значении rate
                                                                 5_000,
                                                             -1);
        });
    }


    /**
     * Тесты метода "Add".
     * Осуществим позитивное и негативное функциональное тестирование.
     * Метод имеет 1-н параметр:
     * - amount - сумма пополнения.
     * Плюс метод возвращает @return = true, если операция завершилась успешно, и false, если
     * операция пополнения счета не прошла.
     * Тестирование проведем, используя методики "эквивалентных" и "граничных значений".
     */

    // Считаем, что класс "Account.java" уже проверен, все методы работают надлежащим образом.

    // Функциональный, позитивный тест метода.
    // Тест реализует методику "эквивалентных значений", проверим, что метод работает в целом.
    // Тест должен завершаться без ошибки, баланс - измениться, метод возвращает true.
    // !!! Однако, тест проходит с ошибкой, баланс не увеличивается на сумму пополнения, а
    // становится равным сумме пополнения, что неверно, хотя и возвращается true.
    @Test
    public void shouldAddToPositiveTest() {
        CreditAccount account = new CreditAccount(                              // помним, что в конструкторе ошибка
                5_000,                                                          // поэтому задаем такие значения
                5_000,
                15
        );
        int oldBalance = account.getBalance();                                  // запоминает значение баланса счета
                                                                                // пытаемся пополнить баланс
        Assertions.assertTrue(account.add(1000));                               // метод вернул true, как результат
        Assertions.assertEquals(oldBalance+1000, account.getBalance()); // баланс увеличился на 1
    }

    // Функциональный, позитивный тест параметра "amount" метода.
    // Тест реализует методику "граничных значений" по параметру "amount" = 1.
    // Тест должен завершаться без ошибок, метод должен вернуть true, операция
    // пополнения должна проходить.
    @Test
    public void shouldAddToPositiveTestBalanceOneValue() {
        CreditAccount account = new CreditAccount(
                5_000,
                5_000,
                15
        );

        int oldBalance = account.getBalance();                                  // запоминает значение баланса счета
                                                                                // пытаемся пополнить баланс на "1"
        Assertions.assertTrue(account.add(1));                                  // метод вернул false, как результат
        Assertions.assertEquals(oldBalance+1,account.getBalance());     // проверяем, что баланс не изменился
    }

    // Функциональный, негативный тест метода.
    // Тест реализует методику "граничных значений" по параметру "amount" = 0.
    // Тест должен завершаться без ошибок, но операция пополнения не должна проходить,
    // метод должен возвращать false
    @Test
    public void shouldAddToNegativeTestBalanceZeroValue() {
        CreditAccount account = new CreditAccount(
                5_000,
                5_000,
                15
        );

        int oldBalance = account.getBalance();                                  // запоминает значение баланса счета
                                                                                // пытаемся пополнить баланс на "0"
        Assertions.assertFalse(account.add(0));                                 // метод вернул false, как результат
        Assertions.assertEquals(oldBalance,account.getBalance());               // проверяем, что баланс не изменился
    }

    // Функциональный, негативный тест метода.
    // Тест реализует методику "граничных значений" по параметру "amount" = -1.
    // Тест должен завершаться без ошибок, но баланс не должен изменяться и метод должен
    // возвращать false
    @Test
    public void shouldAddToNegativeTestBalanceMinusOneValue() {
        CreditAccount account = new CreditAccount(
                5_000,
                5_000,
                15
        );

        int oldBalance = account.getBalance();                                  // запоминает значение баланса счета
                                                                                // пытаемся пополнить баланс на "0"
        Assertions.assertFalse(account.add(-1));                                // метод вернул false, как результат
        Assertions.assertEquals(oldBalance,account.getBalance());               // баланс не изменился
    }

    /**
     * Тесты метода "Pay".
     * Осуществим позитивное и негативное функциональное тестирование.
     * Метод имеет 1-н параметр:
     * - amount - сумма покупки.
     * Плюс метод возвращает @return = true, если операция завершилась успешно, и false, если
     * операция пополнения счета не прошла.
     * Тестирование проведем, используя методики "эквивалентных" и "граничных значений".
     */

    // Проверим, что метод "Pay" работает в целом, проведем функциональный, позитивный тест метода.
    // Тест реализует методику "эквивалентных значений".
    // Тест должен завершаться без ошибки, баланс - измениться, метод возвращает true.
    // !!! Однако, тест завершается ошибкой, в следствие ошибки в коде метода, т.к. вместо
    // "balance = balance - amount" метод устанавливает "balance = -amount" при заданных значениях,
    // что не верно.
    @Test
    public void shouldPayToPositiveTest() {
        CreditAccount account = new CreditAccount(
                5_000,
                5_000,
                15
        );
        int oldBalance = account.getBalance();                                  // запоминает значение баланса счета
                                                                                // пытаемся провести покупку
        Assertions.assertTrue(account.pay(1_000));                       // метод должен вернуть true,
        Assertions.assertEquals(oldBalance-1_000, account.getBalance()); // баланс уменьшиться на 1
    }

    // Проверим условие задания, что "balance" не может быть меньше, чем -creditLimit.
    // Функциональное, негативное тестирование. Используем методику "граничных значений".
    // Рассмотрим работу метода при "balance = -creditLimit-1".
    // Тест должен завершаться без ошибки, но баланс не меняться, метод возвращает false.
    // !!! По факту тест завершается с ошибкой - изменяется значение balance, в следствие ошибки кода метода.
    @Test
    public void shouldPayToNegativeTestBalanceNotLessMinusCreditLimitMinusOne() {
        CreditAccount account = new CreditAccount(
                5_000,
                5_000,
                15
        );
        int oldBalance = account.getBalance();                                  // запоминает значение баланса счета
                                                                                // пытаемся провести покупку
        Assertions.assertFalse(account.pay(account.balance+
                                            account.creditLimit+
                                            1));                                // метод должен вернуть false,
        Assertions.assertEquals(oldBalance, account.getBalance());              // баланс не должен меняться
    }

    // Проверим условие задания, что "balance" не может быть меньше, чем -creditLimit.
    // Функциональное, негативное тестирование. Используем методику "граничных значений".
    // Рассмотрим работу метода при "balance = -creditLimit".
    // Тест должен завершаться без ошибки, но баланс не меняться, метод возвращает false.
    // !!! По факту тест завершается с ошибкой - изменяется значение balance, в следствие ошибки кода метода.
    @Test
    public void shouldPayToNegativeTestBalanceNotLessMinusCreditLimit() {
        CreditAccount account = new CreditAccount(
                5_000,
                5_000,
                15
        );
        int oldBalance = account.getBalance();                                  // запоминает значение баланса счета

        Assertions.assertFalse(account.pay(account.balance+
                                            account.creditLimit));              // метод должен вернуть false,
        Assertions.assertEquals(oldBalance, account.getBalance());              // баланс не должен меняться
    }

    // Проверим условие задания, что "balance" не может быть меньше, чем -creditLimit.
    // Функциональное, негативное тестирование. Используем методику "граничных значений".
    // Рассмотрим работу метода при "balance = -creditLimit+1".
    // Тест должен завершаться без ошибки, баланс меняться, метод возвращает true.
    // !!! По факту тест завершается с ошибкой - изменяется значение balance, в следствие ошибки кода метода.
    @Test
    public void shouldPayToNegativeTestBalanceNotLessMinusCreditLimitPlusOne() {
        CreditAccount account = new CreditAccount(
                5_000,
                5_000,
                15
        );
        int oldBalance = account.getBalance();                                  // запоминает значение баланса счета

        Assertions.assertTrue(account.pay(account.balance+
                                            account.creditLimit-
                                            1));                                // метод должен вернуть true,
        Assertions.assertEquals(oldBalance, account.getBalance());              // баланс должен меняться
    }

    // Теперь протестируем работу метода при граничных значениях параметра "amount", по
    // отношению к требованию, что указанный параметр должен иметь положительным числом.
    // Проведем функциональное, позитивное и негативное тестирования метода с использованием
    // методик "эквивалентных" и "граничных значений".
    // Фактически эквивалентные значения мы уже проверили в тестах ранее. Поэтому проведем
    // тестирование при значениях параметра "amount" = 1, = 0 и = -1.

    // Тест при "amount" = 1. Тест должен завершиться без ошибки, баланс - измениться, метод возвращать true.
    // !!! Но в следствие ошибки в методе тест завершиться с ошибкой - баланс измениться не верно, вместо
    // "balance = balance - 1" будет "balance = -amount".
    @Test
    public void shouldPayToPositiveTestOneValue() {
        CreditAccount account = new CreditAccount(
                5_000,
                5_000,
                15
        );
        int oldBalance = account.getBalance();                                  // запоминает значение баланса счета
                                                                                // пытаемся провести покупку
        Assertions.assertTrue(account.pay(1));                           // метод должен вернуть true,
        Assertions.assertEquals(oldBalance-1, account.getBalance());    // баланс уменьшиться на 1
    }

    // Функциональный, негативный тест метода.
    // Тест реализует методику "граничных значений" по параметру "amount" = 0.
    // Тест должен завершаться без ошибок, но операция оплаты не должна проходить,
    // метод должен возвращать false
    @Test
    public void shouldPayToNegativeTestZeroValue() {
        CreditAccount account = new CreditAccount(
                5_000,
                5_000,
                15
        );

        int oldBalance = account.getBalance();                                  // запоминает значение баланса счета
                                                                                // пытаемся оплатить покупку на "0"
        Assertions.assertFalse(account.pay(0));                          // метод должен вернуть false,
        Assertions.assertEquals(oldBalance,account.getBalance());               // баланс не должен измениться
    }

    // Функциональный, негативный тест метода.
    // Тест реализует методику "граничных значений" по параметру "amount" = -1.
    // Тест должен завершаться без ошибок, но баланс не должен изменяться и метод должен
    // возвращать false
    @Test
    public void shouldPayToNegativeTestMinusOneValue() {
        CreditAccount account = new CreditAccount(
                5_000,
                5_000,
                15
        );

        int oldBalance = account.getBalance();                                  // запоминает значение баланса счета
                                                                                // пытаемся осуществить покупку на "-1"
        Assertions.assertFalse(account.pay(-1));                         // метод должен вернуть false
        Assertions.assertEquals(oldBalance,account.getBalance());               // баланс должен остаться прежним
    }

    /**
     * Тесты метода "yearChange".
     * Осуществим позитивное и негативное функциональное тестирование.
     * Метод не имеет параметров, но имеет условия:
     * - % начисляется только на отрицательный баланс счета;
     * - счет должен быть неизменным в течение года.
     * Тестирование проведем, используя методики "эквивалентных" и "граничных значений".
     */

    // Проведем общее тестирование метода, процерим его работу в целом.
    // Функциональное тестирование, методика "эквивалентных значений"
    // Тест должен проходить без ошибок
    @Test
    public void shouldChangeToPositiveTest() {
        CreditAccount account = new CreditAccount(                                      // Помним, что конструктор
                -200,                                                                   // работает с ошибками.
                5_000,                                                                  // Воспользуемся нашим анализом
                15                                                                      // этой ошибки и зададим значения
        );                                                                              // папаметрам так, чтобы было
                                                                                        // удобно проверять имеено метод
                                                                                        // "yearChange"
        Assertions.assertEquals(-30,account.yearChange());
    }

    // Далее, изучая указанные условия и код метода видим наличие следующей ошибки в его реализации:
    // 1. Код начисляет % при любом значении баланса счета.
    // Подтвердим наш анализ тестами.

    // Отработаем тестом вариант положительного баланса счета.
    // !!! Тест завершается с ошибкой, т.к. код метода не отрабатывает условие - расчета % только
    // при отрицательном балансе счета.
    @Test
    public void shouldChangeToNegativeTest() {
        CreditAccount account = new CreditAccount(                                      // Помним, что конструктор
                200,                                                                    // работает с ошибками.
                5_000,                                                                  // Воспользуемся нашим анализом
                15                                                                      // этой ошибки и зададим значения
        );                                                                              // параметрам так, чтобы было
                                                                                        // удобно проверять имеено метод
                                                                                        // "yearChange"
        Assertions.assertEquals(0,account.yearChange());
    }

    // Тест при balance = 0 проводить нет смысла по причине особенностей самого метода, который вернет
    // значение равное "0" не в следствие выполнения условия задания в коде, а в следствие расчетной формулы.

    /**
     * Тесты метода "getCreditLimit".
     * Простой метод, исключительно возвращающий значение поля "creditLimit" объекта "CreditAccount".
     * Метод не имеет параметров.
     * В данном случае для тестирования достаточно провести функциональное позитивное тестирование.
     */

    // Функциональный, позитивный тест.
    // Тест должен проходить без ошибок
    @Test
    public void shouldGetCreditLimitTest() {
        CreditAccount account = new CreditAccount(
                5_000,
                5_000,
                15
        );

        Assertions.assertEquals(account.creditLimit,account.getCreditLimit());
    }

}
