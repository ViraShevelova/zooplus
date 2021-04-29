package api;

import io.qameta.allure.Allure;
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
  private static final String UPLOAD_IMAGE_URL = "/pet/%s/uploadImage";
  private static final String ADD_UPDATE_PET_URL = "/pet";
  private static final String FIND_PETS_BY_STATUS_URL = "/pet/findByStatus";
  private static final String FIND_UPDATE_DELETE_PET_BY_ID_URL = "/pet/%s";
  private static final String REQUEST_BODY = "Request body";
  private static final String RESPONSE_BODY = "Response body";

  private ApiRequests() { }

  public static Response postPet(final String json) {
    return post(ADD_UPDATE_PET_URL, json);
  }

  public static Response putPet(final String json) {
    return put(ADD_UPDATE_PET_URL, json);
  }

  public static Response getPetById(final Long id) {
    return get(setPetIdForUrl(FIND_UPDATE_DELETE_PET_BY_ID_URL, id));
  }

  public static Response deletePetId(final Long id) {
    return delete(setPetIdForUrl(FIND_UPDATE_DELETE_PET_BY_ID_URL, id));
  }

  public static Response uploadImageForPetPetId(final Long id, final String imageUrl) {
    return postWithFileUpload(setPetIdForUrl(UPLOAD_IMAGE_URL,id), imageUrl);
  }

  public static Response updatePetPetId(final Long id, final String firstName, final String status) {
    return postUpdateById(setPetIdForUrl(FIND_UPDATE_DELETE_PET_BY_ID_URL,id), firstName, status);
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
    var response  = request.get(url);
    Allure.addAttachment(url, response.asPrettyString());
    return request.get(url);
  }

  public static Response delete(final String url) {
    RestAssured.baseURI = BASE_URL;
    RequestSpecification request = RestAssured.given();
    var response  = request.delete(url);
    Allure.addAttachment("Response body", response.asPrettyString());
    return response;
  }

  private static Response post(final String url, final String body) {
    RestAssured.baseURI = BASE_URL;
    RequestSpecification request = RestAssured.given();
    request.header("Content-Type", "application/json");
    request.body(body);
    Allure.addAttachment(REQUEST_BODY, body);
    var response  = request.post(url);
    Allure.addAttachment(RESPONSE_BODY, response.asPrettyString());
    return response;
  }

  private static Response put(final String url, final String body) {
    RestAssured.baseURI = BASE_URL;
    RequestSpecification request = RestAssured.given();
    request.header("Content-Type", "application/json");
    request.body(body);
    var response  = request.put(url);
    Allure.addAttachment(RESPONSE_BODY, response.asPrettyString());
    return response;
  }

  private static Response postWithFileUpload(final String url, final String fileToUpload) {
    RestAssured.baseURI = BASE_URL;
    RequestSpecification request = RestAssured.given()
            .multiPart("file", new File(fileToUpload), "image/jpeg");
    request.header("Content-Type", "multipart/form-data");
    request.header("accept", "application/json");
    request.formParam("additionalMetadata", "additional metadata");
    var response  = request.post(url);
    Allure.addAttachment(RESPONSE_BODY, response.asPrettyString());
    return response;
  }

  private static Response postUpdateById(final String url, final String name, final String status) {
    RestAssured.baseURI = BASE_URL;
    RequestSpecification request = RestAssured.given();
    request.header("Content-Type", "application/x-www-form-urlencoded");
    request.header("accept", "application/json");
    request.formParam("name", name);
    request.formParam("status", status);
    var response  = request.post(url);
    Allure.addAttachment(RESPONSE_BODY, response.asPrettyString());
    return response;
  }

  private static String setPetIdForUrl(String url, Long petId) {
      return String.format(url, petId);
  }
}
