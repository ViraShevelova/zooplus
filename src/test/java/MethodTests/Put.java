package MethodTests;

import enums.Statuses;
import models.KeyName;
import models.Pet;
import steps.Steps;
import utils.RandomGenerator;
import org.testng.annotations.Test;

import java.io.IOException;

public class Put {

    @Test(description = "Update pet name")
    public void updateName() throws IOException {
        var createdPet = Steps.createPet(new Pet().withSimpleFields());
        createdPet.withName(RandomGenerator.getRandomString(25));
        var updatedPet = Steps.updatePet(createdPet);
        Steps.assertThatThisIsExpectedPet(createdPet, updatedPet, "Update first name");
    }

    @Test(description = "Update pet status")
    public void updateStatus() throws IOException {
        var createdPet = Steps.createPet(new Pet().withSimpleFields());
        createdPet.withStatus(Statuses.NotExisted);
        var updatedPet = Steps.updatePet(createdPet);
        Steps.assertThatThisIsExpectedPet(createdPet, updatedPet, "Update status");
    }

    @Test(description = "Update pet tags")
    public void updateTags() throws IOException {
        var createdPet = Steps.createPet(new Pet().withSimpleFields());
        createdPet.withTags(new KeyName(), new KeyName(), new KeyName(), new KeyName());
        var updatedPet = Steps.updatePet(createdPet);
        Steps.assertThatThisIsExpectedPet(createdPet, updatedPet, "Update tags");
    }

    @Test(description = "Update pet category")
    public void updateCategory() throws IOException {
        var createdPet = Steps.createPet(new Pet().withSimpleFields());
        createdPet.withCategory(new KeyName());
        var updatedPet = Steps.updatePet(createdPet);
        Steps.assertThatThisIsExpectedPet(createdPet, updatedPet, "Update category");
    }

    @Test(description = "Update pet urls")
    public void updatePhotoUrls() throws IOException {
        var createdPet = Steps.createPet(new Pet().withSimpleFields());
        createdPet.withPhotoUrls(
                RandomGenerator.getRandomString(25),
                RandomGenerator.getRandomString(25));
        var updatedPet = Steps.updatePet(createdPet);
        Steps.assertThatThisIsExpectedPet(createdPet, updatedPet, "Update photo urls");
    }
}
