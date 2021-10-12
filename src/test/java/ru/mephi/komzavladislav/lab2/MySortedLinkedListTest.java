package ru.mephi.komzavladislav.lab2;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MySortedLinkedListTest {

    @Test
    void testSortedIntegers() {
        MySortedLinkedList<Integer> sortedList = new MySortedLinkedList<>();

        assertThrows(UnsupportedOperationException.class, ()->sortedList.add(1, 1));
        assertThrows(UnsupportedOperationException.class, ()->sortedList.set(1, 1));

        sortedList.add(85);
        sortedList.add(98);
        sortedList.add(36);
        sortedList.add(9);
        sortedList.add(24);
        sortedList.add(52);
        sortedList.add(40);
        sortedList.add(36);
        sortedList.add(95);

        assertIterableEquals(Arrays.asList(9, 24, 36, 36, 40, 52, 85, 95, 98), sortedList);

    }

    @Test
    void testSortedStrings(){
        MySortedLinkedList<String> sortedList = new MySortedLinkedList<>();

        sortedList.add("Павел");
        sortedList.add("Алена");
        sortedList.add("Иван");
        sortedList.add("Ольга");
        sortedList.add("Артем");
        sortedList.add("Алина");
        sortedList.add("Марк");
        sortedList.add("Владислав");
        sortedList.add("Антон");
        sortedList.add("Денис");
        sortedList.add("Иван");

        List<String> expected = Arrays.asList("Алена",
                "Алина",
                "Антон",
                "Артем",
                "Владислав",
                "Денис",
                "Иван",
                "Иван",
                "Марк",
                "Ольга",
                "Павел");


        assertIterableEquals(expected, sortedList);
    }

    @Test
    void testMergeSortedLists() {
        MySortedLinkedList<Integer> emptyLinkedList = new MySortedLinkedList<>();
        assertTrue(MySortedLinkedList.mergeSortedLists(emptyLinkedList, emptyLinkedList).isEmpty());

        MySortedLinkedList<Integer> tenItemsLinkedList = new MySortedLinkedList<>();
        for (int i = 0; i < 10; i++) {
            tenItemsLinkedList.add(i);
        }

        MySortedLinkedList<Integer> result = MySortedLinkedList.mergeSortedLists(tenItemsLinkedList, emptyLinkedList);

        assertIterableEquals(tenItemsLinkedList, result);

        MySortedLinkedList<Integer> squaresList = new MySortedLinkedList<>();
        for (int i = 0; i < 10; i++) {
            squaresList.add(i * i);
        }

        MySortedLinkedList<Integer> fibonacciList = new MySortedLinkedList<>();
        fibonacciList.add(0);
        fibonacciList.add(1);
        for (int i = 2; i < 10; i++) {
            int newNumber = (fibonacciList.get(i - 2)) + (fibonacciList.get(i - 1));
            fibonacciList.add(newNumber);
        }

        MySortedLinkedList<Integer> mergeResult = MySortedLinkedList.mergeSortedLists(squaresList, fibonacciList);
        assertIterableEquals(Arrays.asList(0, 0, 1, 1, 1, 2, 3, 4, 5, 8, 9, 13, 16, 21, 25, 34, 36, 49, 64, 81),
                mergeResult);
        assertEquals(mergeResult.size(), 20);

    }

    @Test
    void testMergeSortedStringLists() {
        MySortedLinkedList<String> namesB19514 = new MySortedLinkedList<>();
        {
            namesB19514.add("Акмурзин Павел Сергеевич");
            namesB19514.add("Анисимова Алёна Сергеевна");
            namesB19514.add("Баранов Иван Сергеевич");
            namesB19514.add("Бобровская Ольга Сергеевна");
            namesB19514.add("Васелюк Артём Сергеевич");
            namesB19514.add("Иванова Алина Вячеславовна");
            namesB19514.add("Каравашкин Марк Александрович");
            namesB19514.add("Комза Владислав Петрович");
            namesB19514.add("Кудряшов Антон Владимирович");
            namesB19514.add("Максимов Денис Романович");
            namesB19514.add("Миловидов Иван Сергеевич");
            namesB19514.add("Михеенко Кирилл Дмитриевич");
            namesB19514.add("Никишин Дмитрий Андреевич");
            namesB19514.add("Панин Илья Сергеевич");
            namesB19514.add("Пахомов Данила Александрович");
            namesB19514.add("Потемкина Анна Александровна");
            namesB19514.add("Сидоренко Олег Игоревич");
            namesB19514.add("Спиридонов Егор Сергеевич");
            namesB19514.add("Федоров Клим Алексеевич");
            namesB19514.add("Черваков Филипп Сергеевич");
        }

        MySortedLinkedList<String> namesB19511 = new MySortedLinkedList<>();
        {
            namesB19511.add("Ахмаев Динислам Ильфирович");
            namesB19511.add("Галимов Тимур Шафкатович");
            namesB19511.add("Дмитрук Анастасия Андреевна");
            namesB19511.add("Евдокимов Василий Максимович");
            namesB19511.add("Ивлев Андрей Евгеньевич");
            namesB19511.add("Кашкарова Арина Владимировна");
            namesB19511.add("Керомян Никита Андреевич");
            namesB19511.add("Кузнецова Ярослава Евгеньевна");
            namesB19511.add("Приваленко Антон Алексеевич");
            namesB19511.add("Сибгатулин Тимур Рустамович");
            namesB19511.add("Соболь Константин Андреевич");
            namesB19511.add("Фролов Иван Владимирович");
            namesB19511.add("Хилалов Муслим Зуфарович");
            namesB19511.add("Чернова Мария Павловна");
            namesB19511.add("Чистый Аркадий Сергеевич");
        }

        MySortedLinkedList<String> expected = new MySortedLinkedList<>();
        {
            expected.add("Акмурзин Павел Сергеевич");
            expected.add("Анисимова Алёна Сергеевна");
            expected.add("Ахмаев Динислам Ильфирович");
            expected.add("Баранов Иван Сергеевич");
            expected.add("Бобровская Ольга Сергеевна");
            expected.add("Васелюк Артём Сергеевич");
            expected.add("Галимов Тимур Шафкатович");
            expected.add("Дмитрук Анастасия Андреевна");
            expected.add("Евдокимов Василий Максимович");
            expected.add("Иванова Алина Вячеславовна");
            expected.add("Ивлев Андрей Евгеньевич");
            expected.add("Каравашкин Марк Александрович");
            expected.add("Кашкарова Арина Владимировна");
            expected.add("Керомян Никита Андреевич");
            expected.add("Комза Владислав Петрович");
            expected.add("Кудряшов Антон Владимирович");
            expected.add("Кузнецова Ярослава Евгеньевна");
            expected.add("Максимов Денис Романович");
            expected.add("Миловидов Иван Сергеевич");
            expected.add("Михеенко Кирилл Дмитриевич");
            expected.add("Никишин Дмитрий Андреевич");
            expected.add("Панин Илья Сергеевич");
            expected.add("Пахомов Данила Александрович");
            expected.add("Потемкина Анна Александровна");
            expected.add("Приваленко Антон Алексеевич");
            expected.add("Сибгатулин Тимур Рустамович");
            expected.add("Сидоренко Олег Игоревич");
            expected.add("Соболь Константин Андреевич");
            expected.add("Спиридонов Егор Сергеевич");
            expected.add("Федоров Клим Алексеевич");
            expected.add("Фролов Иван Владимирович");
            expected.add("Хилалов Муслим Зуфарович");
            expected.add("Черваков Филипп Сергеевич");
            expected.add("Чернова Мария Павловна");
            expected.add("Чистый Аркадий Сергеевич");
        }

        assertIterableEquals(expected, MySortedLinkedList.mergeSortedLists(namesB19511, namesB19514));

    }

}