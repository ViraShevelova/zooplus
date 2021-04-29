package steps;

import api.ApiRequests;
import enums.Statuses;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import models.Pet;
import org.testng.Assert;
import utils.JSONHelper;
import utils.ModelsConvertor;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class Steps {

    private static final int SUCCESS_CODE = 200;
    private static final int NOT_FOUND_CODE = 404;
    private Steps() { }

    @Step("Get pet by id = {id}")
    public static Pet getPet(final Long id) throws IOException {
        var response = ApiRequests.getPetById(id);
        Assert.assertEquals(response.statusCode(), SUCCESS_CODE, "Response status code");
        var petDTO = JSONHelper.convertToPetDto(response.body().asString());
        return ModelsConvertor.convertToPet(petDTO);
    }

    @Step("Delete pet by id = {pet.id}")
    public static Response deletePet(final Pet pet) {
        var response = ApiRequests.deletePetId(pet.getId());
        Assert.assertEquals(response.statusCode(), SUCCESS_CODE, "Response status code");
        return response;
    }

    @Step("Delete not existed pet by id = {pet.id}")
    public static Response deleteNotExistedPet(final Pet pet) {
        var response = ApiRequests.deletePetId(pet.getId());
        Assert.assertEquals(response.statusCode(), NOT_FOUND_CODE, "Response status code");
        return response;
    }

    @Step("Get not existed pet by id = {pet.id}")
    public static Response getNotExistedPet(final Pet pet) {
        var response = ApiRequests.getPetById(pet.getId());
        Assert.assertEquals(response.statusCode(), NOT_FOUND_CODE, "Response status code");
        return response;
    }

    @Step("Create a pet with id {pet.id}")
    public static Pet createPet(final Pet pet) throws IOException {
        var response = ApiRequests.postPet(JSONHelper.convertToJSON(ModelsConvertor.ConvertToDto(pet)));
        Assert.assertEquals(response.statusCode(), SUCCESS_CODE, "Response status code");
        var petDTO = JSONHelper.convertToPetDto(response.body().asString());
        return ModelsConvertor.convertToPet(petDTO);
    }

    @Step("Update pet by id = {pet.id}")
    public static Pet updatePet(final Pet pet) throws IOException {
        var response = ApiRequests.putPet(JSONHelper.convertToJSON(ModelsConvertor.ConvertToDto(pet)));
        Assert.assertEquals(response.statusCode(), SUCCESS_CODE, "Response status code");
        var petDTO = JSONHelper.convertToPetDto(response.body().asString());
        return ModelsConvertor.convertToPet(petDTO);
    }

    @Step("Upload image pet by id = {pet.id}")
    public static Response uploadImageForPet(final Pet pet, final String imageUrl) {
        var response =
                ApiRequests.uploadImageForPetPetId(pet.getId(), imageUrl);
        Assert.assertEquals(response.statusCode(), SUCCESS_CODE, "Response status code");
        return response;
    }

    @Step("Upload image pet by id = {pet.id}")
    public static Response uploadImageForNotExistedPet(final Pet pet, final String imageUrl) {
        var response =
                ApiRequests.uploadImageForPetPetId(pet.getId(), imageUrl);
        Assert.assertEquals(response.statusCode(), NOT_FOUND_CODE, "Response status code");
        return response;
    }

    @Step("Update pet by id = {pet.id}. Set name = {pet.name} and status = {pet.status}")
    public static Response updatePetById(final Pet pet) {
        var response =
                ApiRequests.updatePetPetId(pet.getId(), pet.getName(), pet.getStatus().getName());
        Assert.assertEquals(response.statusCode(), SUCCESS_CODE, "Response status code");
        return response;
    }

    @Step("Update not existed pet by id = {pet.id}. Set name = {pet.name} and status = {pet.status}")
    public static Response updateNotExistedPetById(final Pet pet) {
        var response =
                ApiRequests.updatePetPetId(pet.getId(), pet.getName(), pet.getStatus().getName());
        Assert.assertEquals(response.statusCode(), NOT_FOUND_CODE, "Response status code");
        return response;
    }

    @Step("Find pets by statuses {statuses}")
    public static List<Pet> getPetsByStatuses(final Statuses... statuses) throws IOException {
        var response = ApiRequests.getPetsByStatuses(
                Arrays.stream(statuses).map(Statuses::getName).collect(Collectors.toList()));
        Assert.assertEquals(response.statusCode(), SUCCESS_CODE, "Response status code");
        var petDTOs =  JSONHelper.convertToPetDtos(response.body().asString());
        return Arrays.stream(petDTOs).map(ModelsConvertor::convertToPet).collect(Collectors.toList());
    }

    @Step("Check that pets with ids {expectedPet.id} and {actualPet.id} are equal")
    public static void assertThatThisIsExpectedPet(
            final Pet expectedPet,
            final Pet actualPet,
            final String scenarioDescription) {
        Allure.addAttachment("Actual pet", actualPet.toFullString());
        Allure.addAttachment("Expected pet", expectedPet.toFullString());
        Assert.assertEquals(actualPet, expectedPet, scenarioDescription + ". Pets are not equal");
    }
}
