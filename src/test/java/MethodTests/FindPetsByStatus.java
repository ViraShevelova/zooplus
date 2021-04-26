package MethodTests;

import Enums.Statuses;
import Models.Pet;
import Steps.Steps;
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

    @Test(dataProvider = "statuses")
    public void findPetByStatus(Statuses status, String scenarioDescription) throws IOException {
        var pet = Steps.CreatePet(new Pet().withSimpleFields().withStatus(status));
        var pets = Steps.GetPetsByStatuses(status);
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

    @Test(dataProvider = "emptyStatuses")
    public void findPetByEmptyStatus(Statuses status, String scenarioDescription) throws IOException {
        var pet = Steps.CreatePet(new Pet().withSimpleFields().withStatus(status));
        var pets = Steps.GetPetsByStatuses(status);
        Assert.assertTrue(pets.contains(pet),
                "There is created pet in the list for status:" + scenarioDescription);
    }

    @Test
    public void findPetsByMultipleStatuses() throws IOException {
        var availablePet = Steps.CreatePet(new Pet().withSimpleFields().withStatus(Statuses.Available));
        var soldPet = Steps.CreatePet(new Pet().withSimpleFields().withStatus(Statuses.Sold));
        var pets = Steps.GetPetsByStatuses(Statuses.Available, Statuses.Sold);
        Assert.assertTrue(pets.contains(availablePet) ,
                "There is created pet in the list for status:" + Statuses.Available);
        Assert.assertTrue(pets.contains(soldPet) ,
                "There is created pet in the list for status:" + Statuses.Sold);
    }

    @Test
    public void findPetsWithoutStatus() throws IOException {
        var pets = Steps.GetPetsByStatuses();
        Assert.assertTrue(pets.isEmpty(),"none of pets should be returned");
    }

    @Test
    public void checkThatStatusIsNotCaseSensitive() throws IOException {
        var pet = Steps.CreatePet(new Pet().withSimpleFields().withStatus(Statuses.Available));
        var pets = Steps.GetPetsByStatuses(Statuses.AvailableLowerCase);
        Assert.assertTrue(pets.contains(pet),
                "There is created pet in the list for status:" + Statuses.Available);
    }
}
