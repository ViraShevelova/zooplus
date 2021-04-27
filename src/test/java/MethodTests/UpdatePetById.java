package MethodTests;

import Models.Pet;
import Steps.Steps;
import org.testng.annotations.Test;

import java.io.IOException;

public class UpdatePetById {
    @Test
    public void updateExistedPetId() throws IOException {
        var createdPet = Steps.CreatePet(new Pet().withSimpleFields());
        createdPet.withUpdatedNameAndStatus();
        Steps.updatePetById(createdPet);
        var updatedPet = Steps.GetPet(createdPet.getId());
        Steps.AssertThatThisIsExpectedPet(createdPet, updatedPet, "Pet is updated");
    }

    @Test
    public void updateNotExistedPetId() throws IOException {
        var createdPet = new Pet().withSimpleFields();
        Steps.updateNotExistedPetById(createdPet);
    }
}
