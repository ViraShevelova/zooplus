package MethodTests;

import Enums.Statuses;
import Models.KeyName;
import Models.Pet;
import Steps.Steps;
import Utils.RandomGenerator;
import org.testng.annotations.Test;

import java.io.IOException;

public class Put {

    @Test
    public void updateName() throws IOException {
        var createdPet = Steps.CreatePet(new Pet().withSimpleFields());
        createdPet.withName(RandomGenerator.getRandomString(25));
        var updatedPet = Steps.UpdatePet(createdPet);
        Steps.AssertThatThisIsExpectedPet(createdPet, updatedPet, "Update first name");
    }

    @Test
    public void updateStatus() throws IOException {
        var createdPet = Steps.CreatePet(new Pet().withSimpleFields());
        createdPet.withStatus(Statuses.NotExisted);
        var updatedPet = Steps.UpdatePet(createdPet);
        Steps.AssertThatThisIsExpectedPet(createdPet, updatedPet, "Update status");
    }

    @Test
    public void updateTags() throws IOException {
        var createdPet = Steps.CreatePet(new Pet().withSimpleFields());
        createdPet.withTags(new KeyName(), new KeyName(), new KeyName(), new KeyName());
        var updatedPet = Steps.UpdatePet(createdPet);
        Steps.AssertThatThisIsExpectedPet(createdPet, updatedPet, "Update tags");
    }

    @Test
    public void updateCategory() throws IOException {
        var createdPet = Steps.CreatePet(new Pet().withSimpleFields());
        createdPet.withCategory(new KeyName());
        var updatedPet = Steps.UpdatePet(createdPet);
        Steps.AssertThatThisIsExpectedPet(createdPet, updatedPet, "Update category");
    }

    @Test
    public void updatePhotoUrls() throws IOException {
        var createdPet = Steps.CreatePet(new Pet().withSimpleFields());
        createdPet.withPhotoUrls(
                RandomGenerator.getRandomString(25),
                RandomGenerator.getRandomString(25));
        var updatedPet = Steps.UpdatePet(createdPet);
        Steps.AssertThatThisIsExpectedPet(createdPet, updatedPet, "Update photo urls");
    }
}
