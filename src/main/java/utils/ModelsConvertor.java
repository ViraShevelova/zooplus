package utils;

import dtos.KeyNameDto;
import dtos.PetDto;
import enums.Statuses;
import models.KeyName;
import models.Pet;

public class ModelsConvertor {

    private ModelsConvertor() { }
    public static PetDto ConvertToDto(final Pet pet) {
        var petDto = new PetDto();
        petDto.setId(pet.getId());
        petDto.setName(pet.getName());
        var categoryDto = new KeyNameDto();
        categoryDto.setId(pet.getCategory() != null ? pet.getCategory().getId() : null);
        categoryDto.setName(pet.getCategory() != null ? pet.getCategory().getName() : null);
        petDto.setCategory(categoryDto);
        var tagDtos = pet
                .getTags()
                .stream()
                .map(tag -> {
                        var tagDto = new KeyNameDto();
                        tagDto.setId(tag.getId());
                        tagDto.setName(tag.getName());
                        return tagDto;
                    })
                .toList();
        petDto.getTags().addAll(tagDtos);
        petDto.setPhotoUrls(pet.getPhotoUrls());
        petDto.setStatus(pet.getStatus() != null ? pet.getStatus().getName() : null);
        return petDto;
    }

    public static Pet convertToPet(final PetDto petDto) {
        var pet = new Pet();
        pet.setId(petDto.getId());
        pet.setName(petDto.getName());
        var category = petDto.getCategory() != null
                ? new KeyName().withId(petDto.getCategory().getId()).withName(petDto.getCategory().getName())
                : null;
        pet.setCategory(category);
        pet.setStatus(petDto.getStatus() != null ? Statuses.getStatusByName(petDto.getStatus()) : null);
        var tags =
                petDto.getTags()
                        .stream()
                        .map(tagDto ->
                                new KeyName().withId(tagDto.getId()).withName(tagDto.getName()))
                        .toList();
        pet.setTags(tags);
        pet.setPhotoUrls(petDto.getPhotoUrls());
        return pet;
    }
}
