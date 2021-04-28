package MethodTests;

import models.Pet;
import steps.Steps;
import org.testng.annotations.Test;

import java.io.IOException;

public class UpdatePetById {
    @Test(description = "Update existed pet by id")
    public void updateExistedPetId() throws IOException {
        var createdPet = Steps.createPet(new Pet().withSimpleFields());
        createdPet.withUpdatedNameAndStatus();
        Steps.updatePetById(createdPet);
        var updatedPet = Steps.getPet(createdPet.getId());
        Steps.assertThatThisIsExpectedPet(createdPet, updatedPet, "Pet is updated");
    }

    @Test(description = "Update not existed pet by id")
    public void updateNotExistedPetId() {
        var createdPet = new Pet().withSimpleFields();
        Steps.updateNotExistedPetById(createdPet);
    }
}
