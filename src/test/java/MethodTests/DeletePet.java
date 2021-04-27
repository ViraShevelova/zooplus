package MethodTests;

import models.Pet;
import steps.Steps;
import org.testng.annotations.Test;

import java.io.IOException;

public class DeletePet {

    @Test
    public void deleteExistedPet() throws IOException {
        var pet = Steps.createPet(new Pet().withSimpleFields());
        Steps.deletePet(pet);
        Steps.getNotExistedPet(pet);
    }

    @Test
    public void deleteNotExistedPet() {
        var pet = new Pet().withSimpleFields();
        Steps.deleteNotExistedPet(pet);
    }
}
