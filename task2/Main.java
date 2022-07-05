package homeworkStreams.task2;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        long maloLet = persons.stream().filter(person -> person.getAge() < 18).count();

        List<String> prizivniki = persons.stream()
                .filter(person -> person.getAge() > 18 && person.getAge() < 27)
                .filter(person -> person.getSex().equals(Sex.MAN))
                .map(el -> el.getFamily())
                .collect(Collectors.toList());

        List<String> rabotosposobnie = persons.stream()
                .filter(person -> person.getEducation().equals(Education.HIGHER))
                .filter(person -> person.getSex().equals(Sex.WOMAN) && person.getAge() > 18 && person.getAge() < 60)
                .filter(person -> person.getSex().equals(Sex.MAN) && person.getAge() > 18 && person.getAge() < 65)
//                .sorted((x, y) -> x.getFamily().compareTo(y.getFamily()))
                .sorted(Comparator.comparing(Person::getFamily))
                .map(person -> person.getFamily())
                .collect(Collectors.toList());

        System.out.println("Меньше 18 лет: " + maloLet);
        System.out.println("Призывники: " + prizivniki.stream().count());
        System.out.println("Работоспособные люди, с высшим образованием: " + rabotosposobnie.stream().count());

    }
}