package ApiRequests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.List;

public class ApiRequests {
    private final static String baseUrl = "https://petstore.swagger.io/v2";
    private final static String uploadAnImageUrl = "/pet/{petId}/uploadImage";
    private final static String addUpdatePetToTheStore = "/pet";
    private final static String findPetsByStatus = "/pet/findByStatus";
    private final static String findUpdateDeletePetById = "/pet/{petId}";

    public static Response PostPet(String json)
    {
        return Post(addUpdatePetToTheStore, json);
    }

    public static Response GetPetById(Long id)
    {
        return Get(findUpdateDeletePetById.replace("{petId}", String.valueOf(id)));
    }

    public static Response DeletePetId(Long id)
    {
        return Delete(findUpdateDeletePetById.replace("{petId}", String.valueOf(id)));
    }

    public static Response GetPetsByStatuses(List<String> statuses)
    {
        var urlString = new StringBuilder();
        urlString.append(findPetsByStatus);
        if(statuses.stream().count() > 0)
            urlString.append("?");
        statuses.stream().forEach(status -> urlString.append(String.format("status=%s&", status)));
        if(statuses.stream().count() > 0)
            urlString.deleteCharAt(urlString.length() - 1);
        return Get(urlString.toString());
    }

    private static Response Get(String url) {
        RestAssured.baseURI = baseUrl;
        RequestSpecification request = RestAssured.given();
        return request.get(url);
    }

    public static Response Delete(String url)
    {
        RestAssured.baseURI = baseUrl;
        RequestSpecification request = RestAssured.given();
        return request.delete(url);
    }

    private static Response Post(String url, String body)
    {
        RestAssured.baseURI = baseUrl;
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.body(body);
        return request.post(url);
    }
}
