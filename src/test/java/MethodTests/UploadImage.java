package MethodTests;

import models.Pet;
import steps.Steps;
import org.testng.annotations.Test;

import java.io.IOException;

public class UploadImage {

    private final static String uploadAnImageUrl = ".\\src\\test\\java\\Data\\FileToUpload.jpg";

    @Test
    public void uploadImageForExistingPet() throws IOException {
        var pet = Steps.createPet((Pet)new Pet().withSimpleFields().withLongId());
        Steps.uploadImageForPet(pet, uploadAnImageUrl);
    }

    @Test
    public void uploadImageForNotExistedPet() throws IOException {
        var pet = (Pet)new Pet().withSimpleFields();
        Steps.uploadImageForPet(pet, uploadAnImageUrl);
    }
}
