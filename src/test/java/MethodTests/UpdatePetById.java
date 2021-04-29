package MethodTests;

import Providers.StaticProvider;
import models.Pet;
import org.testng.annotations.Test;
import steps.Steps;

import java.io.IOException;

public class UpdatePetById {

    @Test(dataProvider = "possible-ids-to-create-pet",
            dataProviderClass = StaticProvider.class,
            description = "Update existed pet by id")
    public void updateExistedPetId(Pet pet, String scenarioDescription ) throws IOException {
        var createdPet = Steps.createPet(pet);
        createdPet.withUpdatedNameAndStatus();
        Steps.updatePetById(createdPet);
        var updatedPet = Steps.getPet(createdPet.getId());
        Steps.assertThatThisIsExpectedPet(createdPet, updatedPet, "Pet is updated" + scenarioDescription);
    }

    @Test(dataProvider = "possible-names-to-create-pet",
            dataProviderClass = StaticProvider.class,
            description = "Update existed pet with name")
    public void updateExistedPetWithDifferentNames(Pet pet, String scenarioDescription ) throws IOException {
        var createdPet = Steps.createPet(new Pet().withSimpleFields());
        createdPet.withName(pet.getName());
        Steps.updatePetById(createdPet);
        var updatedPet = Steps.getPet(createdPet.getId());
        Steps.assertThatThisIsExpectedPet(createdPet, updatedPet, "Pet is updated" + scenarioDescription);
    }

    @Test(dataProvider = "possible-statuses-to-create-pet",
            dataProviderClass = StaticProvider.class,
            description = "Update existed pet with status")
    public void updateExistedPetWithDifferentStatuses(Pet pet, String scenarioDescription ) throws IOException {
        var createdPet = Steps.createPet(new Pet().withSimpleFields());
        createdPet.withStatus(pet.getStatus());
        Steps.updatePetById(createdPet);
        var updatedPet = Steps.getPet(createdPet.getId());
        Steps.assertThatThisIsExpectedPet(createdPet, updatedPet, "Pet is updated" + scenarioDescription);
    }

    @Test(description = "Update not existed pet by id")
    public void updateNotExistedPetId() {
        var createdPet = new Pet().withSimpleFields();
        Steps.updateNotExistedPetById(createdPet);
    }
}
