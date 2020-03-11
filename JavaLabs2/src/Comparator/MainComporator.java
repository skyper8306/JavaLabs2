package Comparator;

import java.util.ArrayList;
import java.util.Comparator;

//"Создать класс Person с полями: name, surname, age.
//Имплементировать интерфейс Comparable, используя поля surname, name
//Добавить несколько объектов Person в List
//Вывести 1)неотсортированный список, отсортированный по 2)name, 3)surname, 4)age"

public class MainComporator {

    public static void main(String[] args) {
        ArrayList<Person> arrayPerson = new ArrayList<>();
        Person p1 = new Person("Zhunusov", "Mihail", 22);
        Person p2 = new Person("Zhunusov", "Misha", 22);
        Person p3 = new Person("Smirnov", "Alexander", 23);
        Person p4 = new Person("a", "a", 1);
        Person p5 = new Person("a", "b", 2);
        arrayPerson.add(p1);
        arrayPerson.add(p2);
        arrayPerson.add(p3);
        arrayPerson.add(p4);
        arrayPerson.add(p5);

        System.out.println("Not sorted:");
        for (Person p : arrayPerson) {
            System.out.println(p);
        }
        System.out.println();

        arrayPerson.sort(Person::compareTo);
        System.out.println("Sorted by default:");
        for (Person p : arrayPerson) {
            System.out.println(p);
        }
        System.out.println();

        arrayPerson.sort(Comparator.comparing(person -> person.name.toLowerCase()));
        System.out.println("Sorted by name:");
        for (Person p : arrayPerson) {
            System.out.println(p);
        }
        System.out.println();

        arrayPerson.sort(Comparator.comparing(person -> person.surname.toLowerCase()));
        System.out.println("Sorted by surname:");
        for (Person p : arrayPerson) {
            System.out.println(p);
        }
        System.out.println();

        arrayPerson.sort(Comparator.comparingInt(person -> person.age));
        System.out.println("Sorted by age:");
        for (Person p : arrayPerson) {
            System.out.println(p);
        }
        System.out.println();
    }
}
