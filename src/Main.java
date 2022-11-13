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

        long minors = persons.stream()
                .filter(person -> person.getAge() < 18)
                .count();
        System.out.println(minors);

        List<String> recruit = persons.stream()
                .filter(person -> person.getAge() > 18)
                .filter(person -> person.getAge() < 27)
                .map(Person::getFamily)
                .limit(20)
                // поставил limit только для удобного просмотра,
                // иначе весь вывод в консоль захламляетcя
                .collect(Collectors.toList());
        System.out.println(recruit);

        List<Person> workable = persons.stream()
                .filter(person -> person.getEducation() == Education.HIGHER)
                .filter(person -> person.getAge() > 18)
                .filter(person -> (person.getSex() == Sex.MAN) ? person.getAge() < 65 : person.getAge() < 60)
                .sorted(Comparator.comparing(Person::getFamily))
                .limit(20)
                // поставил limit только для удобного просмотра,
                // иначе весь вывод в консоль захламляетcя
                .collect(Collectors.toList());
        System.out.println(workable);

    }
}
