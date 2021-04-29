package MethodTests;

import Providers.StaticProvider;
import models.Pet;
import org.testng.annotations.Test;
import steps.Steps;

import java.io.IOException;

public class GetPetById {
    @Test (dataProvider = "possible-ids-to-create-pet",
            dataProviderClass = StaticProvider.class,
            description = "Find Pet By Existed Id")
    public void findPetByExistedId(Pet pet, String scenarioDescription) throws IOException {
        var createdPet = Steps.createPet(pet);
        var foundPet = Steps.getPet(createdPet.getId());
        Steps.assertThatThisIsExpectedPet(createdPet, foundPet,
                "Pet is found by existed id" + scenarioDescription);
    }

    @Test(description = "Find Pet By Not Existed Id")
    public void findPetByNotExistedId() {
        var pet = new Pet().withSimpleFields();
        Steps.getNotExistedPet(pet);
    }
}
