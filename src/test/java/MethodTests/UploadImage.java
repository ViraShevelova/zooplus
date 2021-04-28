package MethodTests;

import models.Pet;
import steps.Steps;
import org.testng.annotations.Test;

import java.io.IOException;

public class UploadImage {

    private final static String FILE_TO_UPLOAD_JPG = ".\\src\\test\\java\\Data\\FileToUpload.jpg";

    @Test(description = "Upload image for existed pet")
    public void uploadImageForExistingPet() throws IOException {
        var pet = Steps.createPet((Pet)new Pet().withSimpleFields().withLongId());
        Steps.uploadImageForPet(pet, FILE_TO_UPLOAD_JPG);
    }

    @Test(description = "Upload image for not existed pet")
    public void uploadImageForNotExistedPet() throws IOException {
        var pet = (Pet)new Pet().withSimpleFields();
        Steps.uploadImageForPet(pet, FILE_TO_UPLOAD_JPG);
    }
}
