package MethodTests;

import Enums.Statuses;
import Models.KeyName;
import Models.Pet;
import Steps.Steps;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class Post {

    @DataProvider(name = "possible-ids-to-create-pet")
    public Object[][] possibleIdsToCreatePet(){
        return new Object[][] {
                { new Pet().withSimpleFields(), " id is a positive integer"},
                { new Pet().withSimpleFields().withNegativeId(), "id is a negative integer"},
                { new Pet().withSimpleFields().withZeroId(), "id equals zero"},
                { new Pet().withSimpleFields().withId(null), "id equals null"},
                { new Pet().withSimpleFields().withLongId(), "id is long"},
                { new Pet().withSimpleFields().withMoreThanLongId(), "more than long id"},
        };
    }

    @Test (dataProvider = "possible-ids-to-create-pet")
    public void createPetWithDifferentIds(Pet pet, String scenarioDescription) throws IOException {
        var responsePet = Steps.CreatePet(pet);
        Steps.AssertThatThisIsExpectedPet(pet, responsePet, scenarioDescription);
    }

    @DataProvider(name = "possible-names-to-create-pet")
    public Object[][] possibleINamesToCreatePet(){
        return new Object[][] {
                { new Pet().withSimpleFields().withLongName(), " with long name"},
                { new Pet().withSimpleFields().withName(null), " with null name"},
                { new Pet().withSimpleFields().withName(""), " with empty name"},
                { new Pet().withSimpleFields().withNameWithSpecialCharacters(), " with name with special characters"},
        };
    }

    @Test (dataProvider = "possible-names-to-create-pet")
    public void createPetWithDifferentNames(Pet pet, String scenarioDescription) throws IOException {
        var responsePet = Steps.CreatePet(pet);
        Steps.AssertThatThisIsExpectedPet(pet, responsePet, scenarioDescription);
    }

    @DataProvider(name = "possible-statuses-to-create-pet")
    public Object[][] possibleStatusesPet(){
        return new Object[][] {
                { new Pet().withSimpleFields().withStatus(Statuses.Available), "with status Available"},
                { new Pet().withSimpleFields().withStatus(Statuses.Sold), "with status Sold"},
                { new Pet().withSimpleFields().withStatus(Statuses.Pending), "with status Pending"},
                { new Pet().withSimpleFields().withStatus(Statuses.AvailableLowerCase),
                        "with status Available written in lower case"},
                { new Pet().withSimpleFields().withStatus(Statuses.NotExisted), "with not existed status"},
                { new Pet().withSimpleFields().withStatus(Statuses.Null), "with null status"},
                { new Pet().withSimpleFields().withStatus(Statuses.Empty), "with empty status"},
                { new Pet().withSimpleFields().withStatus(Statuses.SpecialCharacters),
                        "with special characters in status"},
        };
    }

    @Test (dataProvider = "possible-statuses-to-create-pet")
    public void createPetWithDifferentStatuses(Pet pet, String scenarioDescription) throws IOException {
        var responsePet = Steps.CreatePet(pet);
        Steps.AssertThatThisIsExpectedPet(pet, responsePet, scenarioDescription);
    }

    @DataProvider(name = "possible-categories-to-create-pet")
    public Object[][] possibleCategoriesPet(){
        return new Object[][] {
                { new Pet().withSimpleFields().withCategory(new KeyName()), "with simple category"},
                // there are no validations on category
                { new Pet().withSimpleFields().withCategory(null), "with null category"},
                { new Pet().withSimpleFields().withCategory(new KeyName().withId(null)),
                        "with null id in category"},
                { new Pet().withSimpleFields().withCategory(new KeyName().withNegativeId()),
                        "with negative id in category"},
                { new Pet().withSimpleFields().withCategory(new KeyName().withLongId()),
                        "with long id in category"},
                { new Pet().withSimpleFields().withCategory(new KeyName().withMoreThanLongId()),
                        "with more than long id in category"},
                { new Pet().withSimpleFields().withCategory(new KeyName().withName("")), "with empty name in category"},
                { new Pet().withSimpleFields().withCategory(new KeyName().withNameWithSpecialCharacters()),
                        "with special characters in name in category"},
                { new Pet().withSimpleFields().withCategory(null), "with null id in category"},
        };
    }

    @Test (dataProvider = "possible-categories-to-create-pet")
    public void possibleCategoriesToCreatePet(Pet pet, String scenarioDescription) throws IOException {
        var responsePet = Steps.CreatePet(pet);
        Steps.AssertThatThisIsExpectedPet(pet, responsePet, scenarioDescription);
    }

    @DataProvider(name = "possible-tags-to-create-pet")
    public Object[][] possibleTagsPet(){
        var tag = new KeyName();
        return new Object[][] {
                { new Pet().withSimpleFields().withTags(new KeyName()), "with simple tag"},
                // there are no validations on tags(it can be tags with the same id by different names)
                { new Pet().withSimpleFields().withTags(tag, new KeyName(tag)), "with the same tags"},
                { new Pet().withSimpleFields().withTags(), "without tags"},
                { new Pet().withSimpleFields().withTags(new KeyName().withId(null)), "tag with null id"},
                { new Pet().withSimpleFields().withTags(new KeyName().withNegativeId()), "with negative id in tag"},
                { new Pet().withSimpleFields().withTags(new KeyName().withLongId()), "with long id in tag"},
                // { new Pet().withSimpleFields().withTags(), "with more than long id in tag"},
                { new Pet().withSimpleFields().withTags(new KeyName().withName(null)), "tag with null name"},
                { new Pet().withSimpleFields().withTags(new KeyName().withName("")), "tag with empty name"},
                { new Pet().withSimpleFields().withTags(new KeyName().withNameWithSpecialCharacters()),
                        "tag with special characters in name"},
                //{ new Pet().withSimpleFields().withTags(null), "null tag"},

        };
    }

    @Test (dataProvider = "possible-tags-to-create-pet")
    public void possibleTagsToCreatePet(Pet pet, String scenarioDescription) throws IOException {
        var responsePet = Steps.CreatePet(pet);
        Steps.AssertThatThisIsExpectedPet(pet, responsePet, scenarioDescription);
    }
}
