# Bank Simulator
**Project stack:** Vanilla Java

## Инструкция по запуску
Из склонированного репозитория запустить метод `main`.

## Описание проекта
Приложение для практики многопоточности на Java в котом имитируется работа банка с аккаунтами и транзакциями между ними. Основной задачей является обеспечение корректности перевода средств в условиях многопоточности и контроль над итоговым состоянием кошелька (отсутсвие отрицательного баланса). 

## Функционал приложения
**Работа со счетами**
- Создание счетов с уникальными ID и начальным балансом
- Пополнение и списание средств
- Переводы между счетами
- Проверка на отрицательный баланс
- Обработка ситуаций, когда на счете недостаточно средств

**Параллельные транзакции**
- Многопоточное выполнение операций
- Гарантия атомарности операций (например, перевод должен быть либо выполнен полностью, либо отменен)

**Статистика и логгирование**
- Подсчет общего количества транзакций, количество успешных и неудачных операций, среднее время выполнения транзакции через отдельный сервис
- Логгирование через отдельный сервис

## Уточнения по технической части
Работа с syncronized, Atomic variables, Adder variables, Thread Pool, решение проблемы deadlock'a через указание последовательности блокировки.
