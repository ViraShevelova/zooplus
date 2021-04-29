package MethodTests;

import Providers.StaticProvider;
import models.Pet;
import org.testng.annotations.Test;
import steps.Steps;

import java.io.IOException;

public class DeletePet {

    @Test (dataProvider = "possible-ids-to-create-pet",
            dataProviderClass = StaticProvider.class, description = "Delete existed pet")
    public void deleteExistedPet(Pet pet, String scenarioDescription) throws IOException {
        var createdPet = Steps.createPet(pet);
        Steps.deletePet(createdPet);
        Steps.getNotExistedPet(createdPet);
    }

    @Test(description = "Delete not existed pet")
    public void deleteNotExistedPet() {
        var pet = new Pet().withSimpleFields();
        Steps.deleteNotExistedPet(pet);
    }
}
