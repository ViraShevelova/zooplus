package MethodTests;

import Models.Pet;
import Steps.Steps;
import org.testng.annotations.Test;

import java.io.IOException;

public class DeletePet {

    @Test
    public void deleteExistedPet() throws IOException {
        var pet = Steps.CreatePet(new Pet().withSimpleFields());
        Steps.DeletePet(pet);
        Steps.GetNotExistedPet(pet);
    }

    @Test
    public void deleteNotExistedPet() throws IOException {
        var pet = new Pet().withSimpleFields();
        Steps.DeleteNotExistedPet(pet);
    }
}
