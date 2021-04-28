package MethodTests;

import io.qameta.allure.Feature;
import models.Pet;
import org.testng.annotations.Test;
import steps.Steps;

import java.io.IOException;

@Feature("Delete Test")
public class DeletePet {

    @Test(description = "Delete existed pet")
    public void deleteExistedPet() throws IOException {
        var pet = Steps.createPet(new Pet().withSimpleFields());
        Steps.deletePet(pet);
        Steps.getNotExistedPet(pet);
    }

    @Test(description = "Delete not existed pet")
    public void deleteNotExistedPet() {
        var pet = new Pet().withSimpleFields();
        Steps.deleteNotExistedPet(pet);
    }
}
