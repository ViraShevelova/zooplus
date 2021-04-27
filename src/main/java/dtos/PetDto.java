package dtos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PetDto extends KeyNameDto {
    public KeyNameDto getCategory() {
        return category;
    }

    public void setCategory(final KeyNameDto category) {
        this.category = category;
    }

    public ArrayList<String> getPhotoUrls() {
        return photoUrls;
    }

    public void setPhotoUrls(final ArrayList<String> photoUrls) {
        this.photoUrls = photoUrls;
    }

    public List<KeyNameDto> getTags() {
        return tags;
    }

    public void setTags(final List<KeyNameDto> tags) {
        this.tags = tags;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

    private KeyNameDto category;
    private ArrayList<String> photoUrls;
    private List<KeyNameDto> tags = new ArrayList<>();
    private String status;
}
