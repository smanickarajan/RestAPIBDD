package StepDefinitions;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import pojo.Addplace;
import pojo.Location;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class StepDefinition extends Utils {
	
	
	ResponseSpecification resspec;
	RequestSpecification request;
	Response response;
	static String place_id;
	
	
	TestDataBuild data= new TestDataBuild();
	
	@Given("Add Place Payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address) throws IOException {
		
		resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

		request = given().log().all().spec(requestSpecification())
				.body(data.addPlacePayLoad(name,language,address));
	}
	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String method) {

		APIResources resourceAPI = APIResources.valueOf(resource);
		
		//System.out.println(resourceAPI.getResource().toString());
		if (method.equalsIgnoreCase("POST"))
			response = request.when().post(resourceAPI.getResource());
		else if (method.equalsIgnoreCase("GET"))
			response = request.when().get(resourceAPI.getResource());

	}
	@Then("API call got success with status code {int}")
	public void api_call_got_suuccess_with_status_code(Integer int1) {
	   assertEquals(response.getStatusCode(),200);
	}
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyvalue, String expectedvalue) {
	    
	    assertEquals(getJsonPathValue(response, keyvalue),expectedvalue);
	   
	}
	
	@Then("verify place_id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {
		
		 place_id=getJsonPathValue(response, "place_id");
		request = given().log().all().spec(requestSpecification()).queryParam("place_id", place_id);
		user_calls_with_http_request(resource, "GET");
		String actualName=getJsonPathValue(response, "name");
		assertEquals("Name Not matched", expectedName, actualName);
		
	}
	
	@Given("DeletePlace Payload")
	public void delete_place_payload() throws IOException {
		request =  given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));
		
		
	   
	}

}
