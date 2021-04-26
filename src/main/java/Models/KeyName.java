package Models;

import Utils.RandomGenerator;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Objects;
import java.util.Random;

public class KeyName {
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long id;
    public String name;

    public KeyName() {
        setId(RandomGenerator.getRandomPositiveIntAsLong());
        setName(RandomGenerator.getRandomString(25));
    }

    public KeyName(KeyName keyName) {
        setId(keyName.getId());
        setName(keyName.getName());
    }

    public KeyName withNegativeId() {
        var random = new Random();
        setId(-Math.abs((long)random.nextInt()));
        return this;
    }

    public KeyName withLongId() {
        setId(RandomGenerator.getRandomPositiveLong());
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

    public KeyName withName(String name) {
        setName(name);
        return this;
    }

    public KeyName withId(Long id) {
        setId(id);
        return this;
    }

    public KeyName withNameWithSpecialCharacters() {
        setName(RandomGenerator.getRandomStringWithSpecialCharacters(25));
        return this;
    }

    public KeyName withLongName() {
        setName(RandomStringUtils.randomAlphabetic(250, 1000));
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof KeyName)) return false;
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
        return "KeyName{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
