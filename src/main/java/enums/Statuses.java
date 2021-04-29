package enums;

import utils.RandomGenerator;

import java.util.Arrays;
import java.util.NoSuchElementException;

public enum Statuses {
    Available("Available"),
    PartOfAvailable("Avai"),
    AvailableLowerCase("available"),
    Pending("Pending"),
    Sold("Sold"),
    NotExisted("NotExisted"),
    SpecialCharacters(RandomGenerator.SPECIAL_CHARACTERS_STRING),
    Empty(""),
    Null(null);

    public String getName() {
        return name;
    }

    private final String name;

    Statuses(String name) {
        this.name = name;
    }

    public static Statuses getStatusByName(final String name) {
        if (name == null)
            return Null;
        return Arrays.stream(Statuses.values())
                .filter(status -> status.getName().equals(name))
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
    }
}
