package TwoIterators;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;

//Написать метод, на вход - 2 итератора по числам. известно, что коллекции под итераторами упорядочены и бесконечны.
// Необходимо вывести все элементы первой коллекции, которых нет во второй. Напишите junit-тесты.

public class TwoIterators {
    public static <T> ArrayList<T> abc(Iterator<T> it1, Iterator<T> it2) {
        ArrayList<T> arr = new ArrayList<>();
        ArrayList<T> cache = new ArrayList<>();
        while (it2.hasNext()) {
            cache.add(it2.next());
        }
        AtomicBoolean bool = new AtomicBoolean(false); //если есть совпадения, то станет true (нам нужен false)

        it1.forEachRemaining(obj1 -> {
            bool.set(false);
            cache.iterator().forEachRemaining(obj2 -> {
                if (obj1.equals(obj2)) {
                    bool.set(true);
                }
            });
            if (!bool.get()) {
                arr.add(obj1);
            }
        });

        return arr;
    }
}
