package MethodTests;

import Models.Pet;
import Steps.Steps;
import org.testng.annotations.Test;

import java.io.IOException;

public class UploadImage {

    private final static String uploadAnImageUrl = ".\\src\\test\\java\\Data\\FileToUpload.jpg";

    @Test
    public void uploadImageForExistingPet() throws IOException {
        var pet = Steps.CreatePet((Pet)new Pet().withSimpleFields().withLongId());
        Steps.UploadImageForPet(pet, uploadAnImageUrl);
    }

    @Test
    public void uploadImageForNotExistedPet() throws IOException {
        var pet = (Pet)new Pet().withSimpleFields();
        Steps.UploadImageForPet(pet, uploadAnImageUrl);
    }
}
