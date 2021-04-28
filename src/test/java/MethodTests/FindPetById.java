package MethodTests;

import models.Pet;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import steps.Steps;

import java.io.IOException;

public class FindPetById {

    @DataProvider(name = "possible-ids-to-create-pet")
    public Object[][] possibleIdsToCreatePet(){
        return new Object[][] {
                { new Pet().withSimpleFields(), " id is a positive integer"},
                { new Pet().withSimpleFields().withLongId(), "id is long"},
                { new Pet().withSimpleFields().withZeroId(), "with zero id"},
                { new Pet().withSimpleFields().withNegativeId(), "with negative id"},
        };
    }

    @Test(dataProvider = "possible-ids-to-create-pet", description = "Find Pet By Existed Id")
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
