package MethodTests;

import Providers.StaticProvider;
import models.Pet;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import steps.Steps;

import java.io.IOException;

public class UploadImage {

    private final static String FILE_TO_UPLOAD_JPG = ".\\src\\test\\java\\Data\\FileToUpload.jpg";
    private final static String FILE_WITH_SPACE_JPG = ".\\src\\test\\java\\Data\\File File.jpg";
    private final static String FILE_TXT = ".\\src\\test\\java\\Data\\TestZooplusTest.txt";

    @Test (dataProvider = "possible-ids-to-create-pet",
            dataProviderClass = StaticProvider.class,
            description = "Delete existed pet")
    public void uploadImageForExistingPetWithDifferentIds(Pet pet, String scenarioDescription) throws IOException {
        var createdPet = Steps.createPet(pet);
        Steps.uploadImageForPet(pet, FILE_WITH_SPACE_JPG);
    }

    @DataProvider(name = "possible-files-to-upload")
    public Object[][] possiblFilesToUpload(){
        return new Object[][] {
                { ".\\src\\test\\java\\Data\\FileToUpload.jpg", " ipg file"},
                { ".\\src\\test\\java\\Data\\File File.jpg", "file with spaces"},
                { ".\\src\\test\\java\\Data\\TestZooplusTest.txt", "txt file"},
                { ".\\src\\test\\java\\Data\\0BitesFile.txt", "0 bytes file"}
                // haven't tested a large file
        };
    }

    @Test (dataProvider = "possible-files-to-upload",
            description = "Upload different files")
    public void uploadDifferentFiles(String pathToFile, String scenarioDescription) throws IOException {
        var createdPet = Steps.createPet(new Pet().withSimpleFields());
        Steps.uploadImageForPet(createdPet, pathToFile);
    }

    @Test(description = "Upload image for not existed pet")
    public void uploadImageForNotExistedPet() throws IOException {
        var pet = (Pet)new Pet().withSimpleFields();
        Steps.uploadImageForNotExistedPet(pet, FILE_TO_UPLOAD_JPG);
    }
}
