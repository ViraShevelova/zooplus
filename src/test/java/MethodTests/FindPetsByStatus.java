package MethodTests;

import enums.Statuses;
import models.Pet;
import steps.Steps;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class FindPetsByStatus {

    @DataProvider(name = "statuses")
    public Object[][] Statuses(){
        return new Object[][] {
                { Statuses.Available, "Available"},
                { Statuses.Pending, "pending"},
                { Statuses.Sold, "sold"},
                { Statuses.NotExisted, "not existed status"},
        };
    }

    @Test(dataProvider = "statuses", description = "Find Pet By Status")
    public void findPetByStatus(Statuses status, String scenarioDescription) throws IOException {
        var pet = Steps.createPet(new Pet().withSimpleFields().withStatus(status));
        var pets = Steps.getPetsByStatuses(status);
        Assert.assertTrue(pets.contains(pet),
                "There is created pet in the list for status:" + scenarioDescription);
    }

    @DataProvider(name = "emptyStatuses")
    public Object[][] EmptyStatuses(){
        return new Object[][] {
                // { Statuses.Null, "null status"},
                // { Statuses.Empty, "emptyString status"}
        };
    }

    @Test(dataProvider = "emptyStatuses", description = "Find Pet By Empty Status")
    public void findPetByEmptyStatus(Statuses status, String scenarioDescription) throws IOException {
        var pet = Steps.createPet(new Pet().withSimpleFields().withStatus(status));
        var pets = Steps.getPetsByStatuses(status);
        Assert.assertTrue(pets.contains(pet),
                "There is created pet in the list for status:" + scenarioDescription);
    }

    @Test(description = "Find Pets By Multiple Statuses")
    public void findPetsByMultipleStatuses() throws IOException {
        var availablePet = Steps.createPet(new Pet().withSimpleFields().withStatus(Statuses.Available));
        var soldPet = Steps.createPet(new Pet().withSimpleFields().withStatus(Statuses.Sold));
        var pets = Steps.getPetsByStatuses(Statuses.Available, Statuses.Sold);
        Assert.assertTrue(pets.contains(availablePet) ,
                "There is created pet in the list for status:" + Statuses.Available);
        Assert.assertTrue(pets.contains(soldPet) ,
                "There is created pet in the list for status:" + Statuses.Sold);
    }

    @Test(description = "Find Pets without Statuses")
    public void findPetsWithoutStatus() throws IOException {
        var pets = Steps.getPetsByStatuses();
        Assert.assertTrue(pets.isEmpty(),"none of pets should be returned");
    }

    @Test(description = "Check that status is not case sensitive")
    public void checkThatStatusIsNotCaseSensitive() throws IOException {
        var pet = Steps.createPet(new Pet().withSimpleFields().withStatus(Statuses.Available));
        var pets = Steps.getPetsByStatuses(Statuses.AvailableLowerCase);
        Assert.assertTrue(pets.contains(pet),
                "There is created pet in the list for status:" + Statuses.Available);
    }
}
