package models;

import enums.Statuses;
import utils.RandomGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class Pet extends KeyName {

    public KeyName getCategory() {
        return category;
    }

    public void setCategory(final KeyName category) {
        this.category = category;
    }

    public ArrayList<String> getPhotoUrls() {
        return photoUrls;
    }

    public void setPhotoUrls(ArrayList<String> photoUrls) {
        this.photoUrls = photoUrls;
    }

    public List<KeyName> getTags() {
        return tags;
    }

    public void setTags(final List<KeyName> tags) {
        this.tags.addAll(tags);
    }

    public Pet withTags(final KeyName... tags) {
        this.tags.clear();
        this.tags.addAll(Arrays.stream(tags).collect(Collectors.toList()));
        return this;
    }

    public Pet withPhotoUrls(final String... urls) {
        this.photoUrls.clear();
        this.photoUrls.addAll(Arrays.asList(urls));
        return this;
    }

    public Pet withNullPhotoUrl() {
        this.photoUrls.clear();
        this.photoUrls.add(null);
        return this;
    }

    public Statuses getStatus() {
        return status;
    }

    public void setStatus(final Statuses status) {
        this.status = status;
    }

    private KeyName category;
    private ArrayList<String> photoUrls = new ArrayList<>();
    private List<KeyName> tags = new ArrayList<>();
    private Statuses status;

    public Pet withSimpleFields() {
        setId(RandomGenerator.getRandomPositiveIntAsLong());
        setName(RandomGenerator.getRandomString(NAME_LENGTH));
        setStatus(Statuses.Available);
        withTags(new KeyName(), new KeyName(), new KeyName());
        setCategory(new KeyName());
        return this;
    }

    public Pet withStatus(final Statuses status) {
        setStatus(status);
        return this;
    }

    public Pet withCategory(final KeyName category) {
        setCategory(category);
        return this;
    }

    public KeyName withUpdatedNameAndStatus() {
        setName(RandomGenerator.getRandomString(NAME_LENGTH));
        setStatus(Statuses.NotExisted);
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), category, getName(), photoUrls, tags, status);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Pet)) {
            return false;
        }
        Pet pet = (Pet) o;
        var result = false;
        try {
            result = getId().equals(pet.getId())
                    && ((getCategory() == null && pet.getCategory() == null)
                    || getCategory().equals(pet.getCategory()))
                    && ((getName() == null && pet.getName() == null) || getName().equals(pet.getName()))
                    && getPhotoUrls().equals(pet.getPhotoUrls())
                    && getTags().equals(pet.getTags())
                    && getStatus().equals(pet.getStatus());
        }
        catch (Exception ex) { }
        return   result;
    }

    @Override
    public String toString() {
        return toFullString();
    }

    public String toFullString() {
        return "Pet{"
                + "Id=" + getId()
                + ", Category=" + category
                + ", Name='" + getName() + '\''
                + ", photoUrls=" + photoUrls
                + ", tags=" + tags
                + ", status=" + status
                + '}';
    }
}
