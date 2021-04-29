package MethodTests;

import Providers.StaticProvider;
import models.KeyName;
import models.Pet;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import steps.Steps;
import utils.RandomGenerator;

import java.io.IOException;

public class Post {
    @Test (dataProvider = "possible-ids-to-create-pet",
            dataProviderClass = StaticProvider.class,
            description = "Create Pet With Different Ids")
    public void createPetWithDifferentIds(Pet pet, String scenarioDescription) throws IOException {
        var responsePet = Steps.createPet(pet);
        Steps.assertThatThisIsExpectedPet(pet, responsePet, scenarioDescription);
    }

    @Test(dataProvider = "possible-names-to-create-pet",
            dataProviderClass = StaticProvider.class,
            description = "Create Pet With Different Names")
    public void createPetWithDifferentNames(Pet pet, String scenarioDescription) throws IOException {
        var responsePet = Steps.createPet(pet);
        Steps.assertThatThisIsExpectedPet(pet, responsePet, scenarioDescription);
    }

    @Test(dataProvider = "possible-statuses-to-create-pet",
            dataProviderClass = StaticProvider.class,
            description = "Create Pet With Different Statuses")
    public void createPetWithDifferentStatuses(Pet pet, String scenarioDescription) throws IOException {
        var responsePet = Steps.createPet(pet);
        Steps.assertThatThisIsExpectedPet(pet, responsePet, scenarioDescription);
    }

    @DataProvider(name = "possible-categories-to-create-pet")
    public Object[][] possibleCategoriesPet(){
        return new Object[][] {
                { new Pet().withSimpleFields().withCategory(new KeyName()), "with simple category"},
                { new Pet().withSimpleFields().withCategory(null), "with null category"},
                { new Pet().withSimpleFields().withCategory(new KeyName().withId(null)),
                        "with null id in category"},
                { new Pet().withSimpleFields().withCategory(new KeyName().withNegativeId()),
                        "with negative id in category"},
                { new Pet().withSimpleFields().withCategory(new KeyName().withLongId()),
                        "with long id in category"},
                { new Pet().withSimpleFields().withCategory(new KeyName().withName("")), "with empty name in category"},
                { new Pet().withSimpleFields().withCategory(new KeyName().withNameWithSpecialCharacters()),
                        "with special characters in name in category"},
                { new Pet().withSimpleFields().withCategory(null), "with null id in category"},
        };
    }

    @Test (dataProvider = "possible-categories-to-create-pet", description = "Create Pet With Different Categories")
    public void possibleCategoriesToCreatePet(Pet pet, String scenarioDescription) throws IOException {
        var responsePet = Steps.createPet(pet);
        Steps.assertThatThisIsExpectedPet(pet, responsePet, scenarioDescription);
    }

    @DataProvider(name = "possible-tags-to-create-pet")
    public Object[][] possibleTagsPet(){
        var tag = new KeyName();
        return new Object[][] {
                { new Pet().withSimpleFields().withTags(new KeyName()), "with simple tag"},
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
        };
    }

    @Test (dataProvider = "possible-tags-to-create-pet", description = "Create Pet With Different Tags")
    public void possibleTagsToCreatePet(Pet pet, String scenarioDescription) throws IOException {
        var responsePet = Steps.createPet(pet);
        Steps.assertThatThisIsExpectedPet(pet, responsePet, scenarioDescription);
    }

    @DataProvider(name = "possible-photo-urls-to-create-pet")
    public Object[][] possiblePhotoUrlsPet(){
        return new Object[][] {
                { new Pet().withSimpleFields().withPhotoUrls(
                        RandomGenerator.getRandomString(25, 100)), "with simple string"},
                { new Pet().withSimpleFields().withPhotoUrls(
                        RandomGenerator.getRandomString(25, 100),
                        RandomGenerator.getRandomString(25, 100)),
                        "with multiple photo urls"},
                { new Pet().withSimpleFields().withPhotoUrls(""), "with empty photo url"},
                { new Pet().withSimpleFields().withNullPhotoUrl(), "with null photo url"},
                { new Pet().withSimpleFields().withPhotoUrls(
                        RandomGenerator.getRandomStringWithSpecialCharacters(25)),
                        "with special characters photo url"},
        };
    }

    @Test (dataProvider = "possible-photo-urls-to-create-pet", description = "Create Pet With Different Photo Urls")
    public void possiblePhotoUrlsToCreatePet(Pet pet, String scenarioDescription) throws IOException {
        var responsePet = Steps.createPet(pet);
        Steps.assertThatThisIsExpectedPet(pet, responsePet, scenarioDescription);
    }
}
