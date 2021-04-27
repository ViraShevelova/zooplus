package ApiRequests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.File;
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

    public static Response PutPet(String json)
    {
        return Put(addUpdatePetToTheStore, json);
    }

    public static Response GetPetById(Long id)
    {
        return Get(findUpdateDeletePetById.replace("{petId}", String.valueOf(id)));
    }

    public static Response DeletePetId(Long id)
    {
        return Delete(findUpdateDeletePetById.replace("{petId}", String.valueOf(id)));
    }

    public static Response UploadImageForPetPetId(Long id, String imageUrl)
    {
        return PostWithFileUpload(uploadAnImageUrl.replace("{petId}", String.valueOf(id)), imageUrl);
    }

    public static Response updatePetPetId(Long id, String firstName, String status)
    {
        return PostUpdateById(findUpdateDeletePetById.replace("{petId}", String.valueOf(id)), firstName, status);
    }

    public static Response GetPetsByStatuses(List<String> statuses)
    {
        var urlString = new StringBuilder();
        urlString.append(findPetsByStatus);
        if(statuses.size() > 0)
            urlString.append("?");
        statuses.forEach(status -> urlString.append(String.format("status=%s&", status)));
        if(statuses.size() > 0)
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

    private static Response Put(String url, String body)
    {
        RestAssured.baseURI = baseUrl;
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.body(body);
        return request.put(url);
    }

    private static Response PostWithFileUpload(String url, String fileToUpload) {
        RestAssured.baseURI = baseUrl;
        RequestSpecification request = RestAssured.given()
                .multiPart("file", new File(fileToUpload), "image/jpeg");
        request.header("Content-Type", "multipart/form-data");
        request.header("accept", "application/json");
        request.formParam("additionalMetadata", "additional metadataT&*^*&$%^#%^#$%#@#@#$#%^&$&^$%&^");
        return request.post(url);
    }

    private static Response PostUpdateById(String url, String name, String status) {
        RestAssured.baseURI = baseUrl;
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/x-www-form-urlencoded");
        request.header("accept", "application/json");
        request.formParams("name", name, "status", status);
        return request.post(url);
    }
}
