package edu.hw4;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Tasks {

    private Tasks() {
    }

    /*Task1
    Отсортировать животных по росту от самого маленького к самому большому*/
    public static List<Animal> sortByHeightFromLeast(List<Animal> animals) {
        return animals.stream()
            .sorted(Comparator.comparingInt(Animal::height))
            .toList();
    }

    /*Task2
    Отсортировать животных по весу от самого тяжелого к самому легкому,
    выбрать k первых*/
    public static List<Animal> sortByWeightFromMostChooseFirstK(
        List<Animal> animals, int k
    ) {
        return animals.stream()
            .sorted(Comparator.comparingInt(Animal::weight).reversed())
            .limit(k)
            .toList();
    }

    /*Task3
    Сколько животных каждого вида*/
    public static Map<Animal.Type, Integer> findCountByType(List<Animal> animals) {
        return animals.stream()
            .collect(Collectors.groupingBy(
                Animal::type,
                Collectors.reducing(0, e -> 1, Integer::sum)
            ));
    }

    /*Task4
    У какого животного самое длинное имя*/
    public static Animal findAnimalWithLongestName(List<Animal> animals) {
        return animals.stream()
            .max(Comparator.comparingInt(animal -> animal.name().length()))
            .orElse(null);
    }

    /*Task5
    Каких животных больше: самцов или самок*/
    public static Animal.Sex findMostCommonSex(List<Animal> animals) {
        int maleCount = Math.toIntExact(animals.stream()
            .filter(animal -> animal.sex().equals(Animal.Sex.M))
            .count());
        int femaleCount = animals.size() - maleCount;
        if (maleCount > femaleCount) {
            return Animal.Sex.M;
        }
        if (maleCount < femaleCount) {
            return Animal.Sex.F;
        }
        return null;
    }

    /*Task6
    Самое тяжелое животное каждого вида*/
    public static Map<Animal.Type, Animal> findHeaviestOfEachSpecies(List<Animal> animals) {
        return animals.stream()
            .collect(Collectors.toMap(
                Animal::type,
                animal -> animal,
                BinaryOperator.maxBy(Comparator.comparingInt(Animal::weight))
            ));
    }

    /*Task7
    K-е самое старое животное*/
    public static Animal findKthOldest(List<Animal> animals, int k) {
        return animals.stream()
            .sorted(Comparator.comparingInt(Animal::age).reversed())
            .skip(k - 1)
            .findFirst()
            .orElse(null);
    }

    /*Task8
    Самое тяжелое животное среди животных ниже k см*/
    public static Optional<Animal> findHeaviestFromBelowKcmHeight(
        List<Animal> animals, int k
    ) {
        return animals.stream()
            .filter(animal -> animal.height() < k)
            .max(Comparator.comparingInt(Animal::weight));
    }

    /*Task9
    Сколько в сумме лап у животных в списке*/
    public static Integer countPaws(List<Animal> animals) {
        return animals.stream()
            .mapToInt(Animal::paws)
            .sum();
    }

    /*Task10
    Список животных, возраст у которых не совпадает с количеством лап*/
    public static List<Animal> findWhoseAgeNotEqualsPaws(List<Animal> animals) {
        return animals.stream()
            .filter(animal -> animal.age() != animal.paws())
            .toList();
    }

    /*Task11
    Список животных, которые могут укусить и рост превышает 100см*/
    @SuppressWarnings("MagicNumber")
    public static List<Animal> collectWhoseCanBiteAndHigher100cm(List<Animal> animals) {
        return animals.stream()
            .filter(animal -> animal.bites() && animal.height() > 100)
            .toList();
    }

    /*Task12
    Сколько в списке животных, вес которых превышает рост*/
    public static Integer findHowManyAnimalsHaveWeightMoreThenHeight(List<Animal> animals) {
        return Math.toIntExact(animals.stream()
            .filter(animal -> animal.weight() > animal.height())
            .count());
    }

    /*Task13
    Список животных, имена которых состоят из более чем двух слов*/
    public static List<Animal> findWhoseNameContainsMoreThenTwoWords(List<Animal> animals) {
        return animals.stream()
            .filter(animal -> animal.name().split(" ").length > 1)
            .toList();
    }

    /*Task14
    Есть ли в списке собака ростом больше k см*/
    public static boolean isDogInListWithHeightMoreThenKcm(List<Animal> animals, int k) {
        return Math.toIntExact(animals.stream()
            .filter(animal -> animal.type().equals(Animal.Type.DOG))
            .filter(animal -> animal.height() > k).count()) > 0;
    }

    /*Task15
    Найти суммарный вес животных каждого вида, которым от k до l лет*/
    public static Map<Animal.Type, Integer> findTotalWeightBySpeciesFromKtoLAges(
        List<Animal> animals, Integer k, Integer l
    ) {
        return animals.stream()
            .filter(animal -> animal.age() >= k && animal.age() <= l)
            .collect(Collectors.groupingBy(
                Animal::type,
                Collectors.summingInt(Animal::weight)
            ));
    }

    /*Task16
    Список животных, отсортированных по виду, затем по полу, затем по имени*/
    public static List<Animal> sortBySpeciesThenBySexThenByName(List<Animal> animals) {
        return animals.stream()
            .sorted(Comparator
                .comparing(Animal::type)
                .thenComparing(Animal::sex)
                .thenComparing(Animal::name))
            .toList();
    }

    /*Task17
    Правда ли, что пауки кусаются чаще, чем собаки*/
    public static boolean isSpidersBitesMoreThenDogs(List<Animal> animals) {
        List<Animal> spiders = animals.stream()
            .filter(animal -> animal.type().equals(Animal.Type.SPIDER))
            .toList();
        List<Animal> dogs = animals.stream()
            .filter(animal -> animal.type().equals(Animal.Type.DOG))
            .toList();
        long bitingSpidersCount = spiders.stream()
            .filter(Animal::bites)
            .count();
        long bitingDogsCount = dogs.stream()
            .filter(Animal::bites)
            .count();
        double spidersBitingFreq = (double) bitingSpidersCount / spiders.size();
        double dogsBitingFreq = (double) bitingDogsCount / dogs.size();
        return spidersBitingFreq > dogsBitingFreq;
    }

    /*Task18
    Найти самую тяжелую рыбку в 2х или более списках*/
    @SafeVarargs public static Animal findHeaviestFish(List<Animal>... lists) {
        if (lists.length < 2) {
            return null;
        }
        return Stream.of(lists)
            .flatMap(List::stream)
            .filter(animal -> animal.type().equals(Animal.Type.FISH))
            .max(Comparator.comparingInt(Animal::weight))
            .orElse(null);
    }

    /*Task19
    Животные, в записях о которых есть ошибки: вернуть имя и список ошибок*/
    public static Map<String, Set<ValidationError>> findErrorsInAnimalsExemplars(
        List<Animal> animals
    ) {
        return animals.stream()
            .filter(animal -> !Validation.validateErrors(animal).isEmpty())
            .collect(Collectors.toMap(
                Animal::name,
                Validation::validateErrors
            ));
    }

    /*Task20
    Вернуть имя и названия полей с ошибками, объединенные в строку с прошлого задания*/
    public static Map<String, String> getErrorsInMoreReadableForm(
        Map<String, Set<ValidationError>> animalsWithErrors
    ) {
        Map<String, String> result = new HashMap<>();
        for (String key : animalsWithErrors.keySet()) {
            Set<ValidationError> errors = animalsWithErrors.get(key);
            StringBuilder exemplarErrors = new StringBuilder();
            exemplarErrors.append("Fields with errors: ");
            if (errors.contains(ValidationError.INCORRECT_NAME)) {
                exemplarErrors.append("Name, ");
            }
            if (errors.contains(ValidationError.INCORRECT_AGE)) {
                exemplarErrors.append("Age, ");
            }
            if (errors.contains(ValidationError.INCORRECT_HEIGHT)) {
                exemplarErrors.append("Height, ");
            }
            if (errors.contains(ValidationError.INCORRECT_WEIGHT)) {
                exemplarErrors.append("Weight, ");
            }
            exemplarErrors.delete(exemplarErrors.length() - 2, exemplarErrors.length());
            result.put(key, exemplarErrors.toString());
        }
        return result;
    }
}
