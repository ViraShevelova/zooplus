package Models;

import Enums.Statuses;
import Utils.RandomGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Pet extends KeyName {

    public KeyName getCategory() {
        return Category;
    }

    public void setCategory(KeyName category) {
        Category = category;
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

    public void setTags(List<KeyName> tags) {
        this.tags.addAll(tags);
    }

    public Pet withTags(KeyName... tags) {
        this.tags.clear();
        this.tags.addAll(Arrays.stream(tags).toList());
        return this;
    }

    public Statuses getStatus() {
        return status;
    }

    public void setStatus(Statuses status) {
        this.status = status;
    }

    private KeyName Category;
    private ArrayList<String> photoUrls = new ArrayList<>();;
    private List<KeyName> tags = new ArrayList<>();
    private Statuses status;

    public Pet withSimpleFields() {
        setId(RandomGenerator.getRandomPositiveIntAsLong());
        setName(RandomGenerator.getRandomString(25));
        setStatus(Statuses.Available);
        withTags(new KeyName(), new KeyName(), new KeyName());
        setCategory(new KeyName());
        return this;
    }

    public Pet withStatus(Statuses status) {
        setStatus(status);
        return this;
    }

    public Pet withCategory(KeyName category)
    {
        setCategory(category);
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, Category, name, photoUrls, tags, status);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pet)) return false;
        Pet pet = (Pet) o;
        boolean result = false;
        try {
            result =  getId().equals(pet.getId())
                    && ((getCategory() == null && pet.getCategory() == null) || getCategory().equals(pet.getCategory()))
                    && ((getName() == null && pet.getName() == null) || getName().equals(pet.getName()))
                    && getPhotoUrls().equals(pet.getPhotoUrls())
                    && getTags().equals(pet.getTags())
                    && getStatus() == pet.getStatus();
        }
        catch(Exception e) {}
        return result;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "Id=" + id +
                ", Category=" + Category +
                ", Name='" + name + '\'' +
                ", photoUrls=" + photoUrls +
                ", tags=" + tags +
                ", status=" + status +
                '}';
    }
}
