package enums;

import utils.RandomGenerator;

import java.util.Arrays;
import java.util.NoSuchElementException;

public enum Statuses {
    Available("Available"),
    AvailableLowerCase("available"),
    Pending("Pending"),
    Sold("Sold"),
    NotExisted("NotExisted"),
    Null(null),
    Empty(""),
    SpecialCharacters(RandomGenerator.SPECIAL_CHARACTERS_STRING);

    public String getName() {
        return name;
    }

    private final String name;

    Statuses(String name) {
        this.name = name;
    }

    public static Statuses getStatusByName(final String name) {
        return Arrays.stream(Statuses.values())
                .filter(status -> status.name.equals(name))
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
    }
}
