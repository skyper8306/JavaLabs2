package Comparator;

public class Person implements Comparable<Person> {
    String name;
    String surname;
    int age;

    public Person(String surname, String name, int age) {
        this.surname = surname;
        this.name = name;
        this.age = age;
    }

    @Override
    public int compareTo(Person anotherPerson) {
        int surComp = String.CASE_INSENSITIVE_ORDER.compare(surname, anotherPerson.surname);
        if (surComp == 0) {
            return String.CASE_INSENSITIVE_ORDER.compare(name, anotherPerson.name);
        } else {
            return surComp;
        }
    }

    @Override
    public String toString() {
        return "Person surname="+surname+", name="+name+", age="+age;
    }
}
