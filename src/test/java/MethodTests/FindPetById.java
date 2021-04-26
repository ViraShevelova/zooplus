package MethodTests;

import Models.Pet;
import Steps.Steps;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

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

    @Test(dataProvider = "possible-ids-to-create-pet")
    public void findPetByExistedId(Pet pet, String scenarioDescription) throws IOException {
        var createdPet = Steps.CreatePet(pet);
        var foundPet = Steps.GetPet(createdPet.getId());
        Steps.AssertThatThisIsExpectedPet(createdPet, foundPet,
                "Pet is found by existed id" + scenarioDescription);
    }

    @Test
    public void findPetByNotExistedId() throws IOException {
        var pet = new Pet().withSimpleFields();
        Steps.GetNotExistedPet(pet);
    }
}
