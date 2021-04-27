package models;

import utils.RandomGenerator;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Objects;
import java.util.Random;

public class KeyName {
    protected static final int NAME_LENGTH = 25;
    private static final int LONG_NAME_LENGTH = 250;
    private static final int SUPER_LONG_NAME_LENGTH = 1000;
    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    private Long id;
    private String name;

    public KeyName() {
        setId(RandomGenerator.getRandomPositiveIntAsLong());
        setName(RandomGenerator.getRandomString(NAME_LENGTH));
    }

    public KeyName(final KeyName keyName) {
        setId(keyName.getId());
        setName(keyName.getName());
    }

    public KeyName withNegativeId() {
        var random = new Random();
        setId(-Math.abs((long) random.nextInt()));
        return this;
    }

    public KeyName withLongId() {
        setId(RandomGenerator.getRandomPositiveLong());
        return this;
    }

    public KeyName withNegativeLongId() {
        setId(-RandomGenerator.getRandomPositiveLong());
        return this;
    }

    public KeyName withZeroId() {
        setId(0L);
        return this;
    }

    public KeyName withMoreThanLongId() {
        setId(Long.MAX_VALUE + 1);
        return this;
    }

    public KeyName withName(final String name) {
        setName(name);
        return this;
    }

    public KeyName withId(final Long id) {
        setId(id);
        return this;
    }

    public KeyName withNameWithSpecialCharacters() {
        setName(RandomGenerator.getRandomStringWithSpecialCharacters(NAME_LENGTH));
        return this;
    }

    public KeyName withLongName() {
        setName(RandomStringUtils.randomAlphabetic(LONG_NAME_LENGTH, SUPER_LONG_NAME_LENGTH));
        return this;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof KeyName)) {
            return false;
        }
        KeyName keyName = (KeyName) o;
        return getId().equals(keyName.getId())
                && Objects.equals(getName(), keyName.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }

    @Override
    public String toString() {
        return "KeyName{"
                + "id=" + id
                + ", name='" + name + '\''
                + '}';
    }
}
