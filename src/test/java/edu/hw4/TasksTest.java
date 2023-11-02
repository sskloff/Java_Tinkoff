package edu.hw4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TasksTest {

    public static List<Animal> createCollection() {
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal("FISH", Animal.Type.FISH, Animal.Sex.F,
            1, 2, 1, true));
        animals.add(new Animal("DOG", Animal.Type.DOG, Animal.Sex.M,
            5, 3, 5, true));
        animals.add(new Animal("SPIDER", Animal.Type.SPIDER, Animal.Sex.M,
            1, 1, 1, false));
        animals.add(new Animal("SNOOP DOGGY", Animal.Type.DOG, Animal.Sex.M,
            10, 10, 10, false));
        return animals;
    }

    public static List<Animal> createExtraCollectionForTask17() {
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal("FISH2", Animal.Type.FISH, Animal.Sex.F,
            1, 2, 3, true));
        return animals;
    }

    public static List<Animal> createCollectionForLastTasks() {
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal("", Animal.Type.FISH, Animal.Sex.F,
            1, 2, -4, true));
        return animals;
    }

    @Test
    @DisplayName("Сортировка по росту")
    void whenSortByHeightFromLeastThenSpiderFirstSnoopDoggyLast() {
        //given
        List<Animal> animals = createCollection();

        //when
        var result = Tasks.sortByHeightFromLeast(animals);

        //then
        assertEquals("SPIDER", result.get(0).name());
        assertEquals("SNOOP DOGGY", result.get(3).name());
    }

    @Test
    @DisplayName("Сортировка по весу от большего, выбрать первые 2")
    void whenSortByWeightFromMostChooseFirst2ThenSnoopdoggyFirstDogLast() {
        //given
        List<Animal> animals = createCollection();

        //when
        var result = Tasks.sortByWeightFromMostChooseFirstK(animals, 2);

        //then
        assertEquals("SNOOP DOGGY", result.get(0).name());
        assertEquals("DOG", result.get(1).name());
    }

    @Test
    @DisplayName("Сколько животных каждого вида")
    void whenFindCountByTypeThenDog2Spider1() {
        //given
        List<Animal> animals = createCollection();

        //when
        var result = Tasks.findCountByType(animals);

        //then
        assertEquals(2, result.get(Animal.Type.DOG));
        assertEquals(1, result.get(Animal.Type.SPIDER));
    }

    @Test
    @DisplayName("У какого животного самое длинное имя")
    void whenFindAnimalWithLongestNameThenSnoopDoggy() {
        //given
        List<Animal> animals = createCollection();

        //when
        var result = Tasks.findAnimalWithLongestName(animals);

        //then
        assertEquals("SNOOP DOGGY", result.name());
    }

    @Test
    @DisplayName("Каких животных больше, самцов или самок")
    void whenFindMostCommonSexThenM() {
        //given
        List<Animal> animals = createCollection();

        //when
        var result = Tasks.findMostCommonSex(animals);

        //then
        assertEquals(Animal.Sex.M, result);
    }

    @Test
    @DisplayName("Самое тяжелое животное каждого вида")
    void whenFindHeaviestOfEachSpeciesThenDogSnoopDoggy() {
        //given
        List<Animal> animals = createCollection();

        //when
        var result = Tasks.findHeaviestOfEachSpecies(animals);

        //then
        assertEquals("SNOOP DOGGY", result.get(Animal.Type.DOG).name());
    }

    @Test
    @DisplayName("K-e самое старое животное")
    void whenFind2dOldestThenDog() {
        //given
        List<Animal> animals = createCollection();

        //when
        var result = Tasks.findKthOldest(animals, 2);

        //then
        assertEquals("DOG", result.name());
    }

    @Test
    @DisplayName("Самое тяжелое животное среди животных ниже k см")
    void whenFindHeaviestFromBelow9cmHeightThenWeight5() {
        //given
        List<Animal> animals = createCollection();

        //when
        var result = Tasks.findHeaviestFromBelowKcmHeight(animals, 9);

        //then
        assertEquals(5, result.get().weight());
    }

    @Test
    @DisplayName("Сколько в сумме лап у животных в списке")
    void whenCountPawsThen16() {
        //given
        List<Animal> animals = createCollection();

        //when
        var result = Tasks.countPaws(animals);

        //then
        assertEquals(16, result);
    }

    @Test
    @DisplayName("Список животных, у которых возраст не совпадает с количеством лап")
    void whenFindWhoseAgeNotEqualsPawsThenSizeIs4() {
        //given
        List<Animal> animals = createCollection();

        //when
        var result = Tasks.findWhoseAgeNotEqualsPaws(animals);

        //then
        assertEquals(4, result.size());
    }

    @Test
    @DisplayName("Список животных, которые могут укусить")
    void whenCollectWhoseCanBiteAndHigher100cmThenSize0() {
        //given
        List<Animal> animals = createCollection();

        //when
        var result = Tasks.collectWhoseCanBiteAndHigher100cm(animals);

        //then
        assertEquals(0, result.size());
    }

    @Test
    @DisplayName("Сколько в списке животных, вес которых превышает рост")
    void whenFindHowManyAnimalsHaveWeightMoreThenHeightThen1() {
        //given
        List<Animal> animals = createCollection();

        //when
        var result = Tasks.findHowManyAnimalsHaveWeightMoreThenHeight(animals);

        //then
        assertEquals(1, result);
    }

    @Test
    @DisplayName("Список животных, имена которых состоят из более чем двух слов")
    void whenFindWhoseNameContainsMoreThenTwoWordsThenSnoopDoggy() {
        //given
        List<Animal> animals = createCollection();

        //when
        var result = Tasks.findWhoseNameContainsMoreThenTwoWords(animals);

        //then
        assertEquals("SNOOP DOGGY", result.get(0).name());
    }

    @Test
    @DisplayName("Есть ли в списке собака ростом более 7 см")
    void whenIsDogInListWithHeightMoreThe9cmThenTrue() {
        //given
        List<Animal> animals = createCollection();

        //when
        var result = Tasks.isDogInListWithHeightMoreThenKcm(animals,9);

        //then
        assertTrue(result);
    }

    @Test
    @DisplayName("Суммарный вес животных каждого вида от 1 до 11 лет")
    void whenFindTotalWeightBySpeciesFrom1to11AgesThenDogSumIs15() {
        //given
        List<Animal> animals = createCollection();

        //when
        var result = Tasks.findTotalWeightBySpeciesFromKtoLAges(animals, 1, 11);

        //then
        assertEquals(15, result.get(Animal.Type.DOG));
    }

    @Test
    @DisplayName("Список животных, отсортированный по виду, по полу, по имени")
    void whenSortBySpeciesThenBySexThenByNameThenFirstNameDogLastSpider() {
        //given
        List<Animal> animals = createCollection();

        //when
        var result = Tasks.sortBySpeciesThenBySexThenByName(animals);

        //then
        assertEquals("DOG", result.get(0).name());
        assertEquals("SPIDER", result.get(3).name());
    }

    @Test
    @DisplayName("Правда ли, что пауки кусаются чаще")
    void whenIsSpidersBitesMoreThenFalse() {
        //given
        List<Animal> animals = createCollection();

        //when
        var result = Tasks.isSpidersBitesMoreThenDogs(animals);

        //then
        assertFalse(result);
    }

    @Test
    @DisplayName("Самая тяжелая рыба")
    void whenFindHeaviestFishThenFish2() {
        //given
        List<Animal> animals = createCollection();
        List<Animal> animals2 = createExtraCollectionForTask17();

        //when
        var result = Tasks.findHeaviestFish(animals, animals2);

        //then
        assertEquals("FISH2", result.name());
    }

    @Test
    @DisplayName("Вернуть имя и множество ошибок некорректных записей")
    void whenFindErrorsInAnimalsExemplarsThenSizeOfErrorsSetIs2() {
        //given
        List<Animal> animals = createCollectionForLastTasks();

        //when
        var result = Tasks.findErrorsInAnimalsExemplars(animals);

        //then
        assertEquals(2, result.get("").size());
    }

    @Test
    @DisplayName("Преобразование множества ошибок из прошлого задания в строку")
    void getErrorsInMoreReadableForm() {
        //given
        List<Animal> animals = createCollectionForLastTasks();
        var map = Tasks.findErrorsInAnimalsExemplars(animals);

        //when
        var result = Tasks.getErrorsInMoreReadableForm(map);

        //then
        assertEquals("Fields with errors: Name, Weight", result.get(""));
    }
}
