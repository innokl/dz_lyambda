package ru.mephi.vikingdemo.service;

import org.springframework.stereotype.Service;
import ru.mephi.vikingdemo.model.BeardStyle;
import ru.mephi.vikingdemo.model.HairColor;
import ru.mephi.vikingdemo.model.Viking;

import java.util.Comparator;
import java.util.List;
import java.util.Random;

@Service
public class VikingLambdaService {

    private final VikingService vikingService;
    private final Random random = new Random();

    public VikingLambdaService(VikingService vikingService) {
        this.vikingService = vikingService;
    }

    public long countOlderThan(int age) {
        return vikingService.findAll()
                .stream()
                .filter(v -> v.age() > age)
                .count();
    }

    public long countYoungerThan(int age) {
        return vikingService.findAll()
                .stream()
                .filter(v -> v.age() < age)
                .count();
    }

    public long countAgeBetween(int min, int max) {
        return vikingService.findAll()
                .stream()
                .filter(v -> v.age() >= min && v.age() <= max)
                .count();
    }

    public long countAgeOutside(int min, int max) {
        return vikingService.findAll()
                .stream()
                .filter(v -> v.age() < min || v.age() > max)
                .count();
    }

    public long countByBeardAndHair(BeardStyle beardStyle,
                                    HairColor hairColor) {

        return vikingService.findAll()
                .stream()
                .filter(v ->
                        v.beardStyle() == beardStyle
                                &&
                                v.hairColor() == hairColor
                )
                .count();
    }


    public long countOneAxe() {
        return vikingService.findAll()
                .stream()
                .filter(v ->
                        v.equipment()
                                .stream()
                                .filter(e -> e.name().contains("Axe"))
                                .count() == 1
                )
                .count();
    }

    public long countTwoAxes() {
        return vikingService.findAll()
                .stream()
                .filter(v ->
                        v.equipment()
                                .stream()
                                .filter(e -> e.name().contains("Axe"))
                                .count() == 2
                )
                .count();
    }
    public long countOneOrTwoAxes() {

        return vikingService.findAll()
                .stream()
                .filter(v -> {

                    long axeCount = v.equipment()
                            .stream()
                            .filter(e -> e.name().contains("Axe"))
                            .count();

                    return axeCount == 1 || axeCount == 2;
                })
                .count();
    }
    public Viking randomTallViking() {

        List<Viking> tall = vikingService.findAll()
                .stream()
                .filter(v -> v.heightCm() > 180)
                .toList();

        if (tall.isEmpty()) {
            return null;
        }

        return tall.get(random.nextInt(tall.size()));
    }

    public List<Viking> legendaryVikings() {

        return vikingService.findAll()
                .stream()
                .filter(v ->
                        v.equipment()
                                .stream()
                                .anyMatch(e ->
                                        e.quality().equals("Legendary")
                                )
                )
                .toList();
    }

    
    public List<Viking> redBeardSorted() {

        return vikingService.findAll()
                .stream()
                .filter(v ->
                        v.hairColor() == HairColor.Red
                                &&
                                v.beardStyle() != BeardStyle.CLEAN_SHAVEN
                )
                .sorted(Comparator.comparing(Viking::age))
                .toList();
    }

    public Integer[] getIdsArray() {

        return vikingService.findAll()
                .stream()
                .map(Viking::id)
                .toArray(Integer[]::new);
    }

    public Integer maxId() {

        return Arrays.stream(getIdsArray())
                .max(Integer::compareTo)
                .orElse(0);
    }

    public List<Integer> evenIds() {

        return Arrays.stream(getIdsArray())
                .filter(id -> id % 2 == 0)
                .toList();
    }
}
