package api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.io.File;
import java.util.List;

/**
 * Sends requests to the API
 *
 */
public final class ApiRequests {
  private static final String BASE_URL = "https://petstore.swagger.io/v2";
  private static final String UPLOAD_IMAGE_URL = "/pet/{petId}/uploadImage";
  private static final String ADD_UPDATE_PET_URL = "/pet";
  private static final String FIND_PETS_BY_STATUS_URL = "/pet/findByStatus";
  private static final String FIND_UPDATE_DELETE_PET_BY_ID_URL = "/pet/{petId}";

  private ApiRequests() { }

  public static Response postPet(final String json) {
    return post(ADD_UPDATE_PET_URL, json);
  }

  public static Response putPet(final String json) {
    return put(ADD_UPDATE_PET_URL, json);
  }

  public static Response getPetById(final Long id) {
    return get(FIND_UPDATE_DELETE_PET_BY_ID_URL.replace("{petId}", String.valueOf(id)));
  }

  public static Response deletePetId(final Long id) {
    return delete(FIND_UPDATE_DELETE_PET_BY_ID_URL.replace("{petId}", String.valueOf(id)));
  }

  public static Response uploadImageForPetPetId(final Long id, final String imageUrl) {
    return postWithFileUpload(UPLOAD_IMAGE_URL.replace("{petId}", String.valueOf(id)), imageUrl);
  }

  public static Response updatePetPetId(final Long id, final String firstName, final String status) {
    return postUpdateById(FIND_UPDATE_DELETE_PET_BY_ID_URL.replace("{petId}", String.valueOf(id)), firstName, status);
  }

  public static Response getPetsByStatuses(final List<String> statuses) {
    var urlString = new StringBuilder();
    urlString.append(FIND_PETS_BY_STATUS_URL);
    if (statuses.size() > 0) {
      urlString.append("?");
    }
    statuses.forEach(status -> urlString.append(String.format("status=%s&", status)));
    if (statuses.size() > 0) {
      urlString.deleteCharAt(urlString.length() - 1);
    }
    return get(urlString.toString());
  }

  private static Response get(final String url) {
    RestAssured.baseURI = BASE_URL;
    RequestSpecification request = RestAssured.given();
    return request.get(url);
  }

  public static Response delete(final String url) {
    RestAssured.baseURI = BASE_URL;
    RequestSpecification request = RestAssured.given();
    return request.delete(url);
  }

  private static Response post(final String url, final String body) {
    RestAssured.baseURI = BASE_URL;
    RequestSpecification request = RestAssured.given();
    request.header("Content-Type", "application/json");
    request.body(body);
    return request.post(url);
  }

  private static Response put(final String url, final String body) {
    RestAssured.baseURI = BASE_URL;
    RequestSpecification request = RestAssured.given();
    request.header("Content-Type", "application/json");
    request.body(body);
    return request.put(url);
  }

  private static Response postWithFileUpload(final String url, final String fileToUpload) {
    RestAssured.baseURI = BASE_URL;
    RequestSpecification request = RestAssured.given()
            .multiPart("file", new File(fileToUpload), "image/jpeg");
    request.header("Content-Type", "multipart/form-data");
    request.header("accept", "application/json");
    request.formParam("additionalMetadata", "additional metadataT&*^*&$%^#%^#$%#@#@#$#%^&$&^$%&^");
    return request.post(url);
  }

  private static Response postUpdateById(final String url, final String name, final String status) {
    RestAssured.baseURI = BASE_URL;
    RequestSpecification request = RestAssured.given();
    request.header("Content-Type", "application/x-www-form-urlencoded");
    request.header("accept", "application/json");
    request.formParams("name", name, "status", status);
    return request.post(url);
  }
}
