package Steps;

import ApiRequests.ApiRequests;
import Enums.Statuses;
import Models.Pet;
import Utils.JSONHelper;
import Utils.ModelsConvertor;
import io.restassured.response.Response;
import org.testng.Assert;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Steps {

    public static Pet GetPet(Long id) throws IOException
    {
        var response = ApiRequests.GetPetById(id);
        Assert.assertEquals(response.statusCode(), 200, "Response status code");
        var petDTO = JSONHelper.ConvertToPetDto(response.body().asString());
        return ModelsConvertor.ConvertToPet(petDTO);
    }

    public static Response DeletePet(Pet pet) throws IOException
    {
        var response = ApiRequests.DeletePetId(pet.getId());
        Assert.assertEquals(response.statusCode(), 200, "Response status code");
        return response;
    }

    public static Response DeleteNotExistedPet(Pet pet) throws IOException
    {
        var response = ApiRequests.DeletePetId(pet.getId());
        Assert.assertEquals(response.statusCode(), 404, "Response status code");
        return response;
    }

    public static Response GetNotExistedPet(Pet pet) throws IOException
    {
        var response = ApiRequests.GetPetById(pet.getId());
        Assert.assertEquals(response.statusCode(), 404, "Response status code");
        return response;
    }

    public static Pet CreatePet(Pet pet) throws IOException
    {
        var response = ApiRequests.PostPet(JSONHelper.ConvertToJSON(ModelsConvertor.ConvertToDto(pet)));
        Assert.assertEquals(response.statusCode(), 200, "Response status code");
        var petDTO = JSONHelper.ConvertToPetDto(response.body().asString());
        return ModelsConvertor.ConvertToPet(petDTO);
    }

    public static void AssertThatThisIsExpectedPet(Pet expectedPet, Pet actualPet, String scenarioDescription) {
        Assert.assertEquals(actualPet, expectedPet, scenarioDescription + ". Pets are not equal");
    }

    public static List<Pet> GetPetsByStatuses(Statuses... statuses) throws IOException {
        var response = ApiRequests.GetPetsByStatuses(
                Arrays.stream(statuses).map(status -> status.getName()).collect(Collectors.toList()));
        Assert.assertEquals(response.statusCode(), 200, "Response status code");
        var petDTOs =  JSONHelper.ConvertToPetDtos(response.body().asString());
        return Arrays.stream(petDTOs).map(petDTO -> ModelsConvertor.ConvertToPet(petDTO)).collect(Collectors.toList());
    }
}
