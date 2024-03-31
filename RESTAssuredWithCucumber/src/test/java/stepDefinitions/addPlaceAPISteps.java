package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.Endpoints;
import resources.SetupSpecs;
import resources.TestData;
import static io.restassured.RestAssured.*;

import java.io.IOException;

import org.testng.Assert;

import PoJo.AddPlaceResponse;
import Utils.Context;
import Utils.ReusableMethods;

public class addPlaceAPISteps extends SetupSpecs {
	RequestSpecification requestSpec;
	RequestSpecification baseReqSpec;
	ResponseSpecification baseResSpec;
	Response response;
	TestData testData = new TestData();
	AddPlaceResponse addPlaceResponse;
	
	@Given("I have payload for the {string} endpoint")
	public void i_have_payload_for_the_add_place_api(String endpointName) throws IOException {
		String placeId;
		switch(endpointName){
			case "AddPlaceAPI":
				requestSpec = given().spec(BaseRequestSpec()).body(testData.AddPlacePayload());	
				break;
			case "GetPlaceAPI":
				placeId = Context.getPlaceID();
				requestSpec = given().spec(BaseRequestSpec()).queryParam("place_id", placeId);
				break;
			case "DeletePlaceAPI":
				placeId = Context.getPlaceID();
				requestSpec = given().spec(BaseRequestSpec()).body(testData.DeletePlacePayload(placeId));
				break;
			case "UpdatePlaceAPI":
				placeId = Context.getPlaceID();
				requestSpec = given().spec(BaseRequestSpec()).body(testData.UpdatePlacePayload(placeId));
				break;
		}
		
	}
	
	@When("I hit the {string} request for the {string} endpoint")
	public void i_hit_the_request_for_the_endpoint(String httpMethod, String endpointName) {
		
		Endpoints resource = Endpoints.valueOf(endpointName);
		String endpoint = resource.getEndpoint();
		if(httpMethod.equalsIgnoreCase("POST")) {
			response =	requestSpec.when().post(endpoint).
					then()
					.spec(BaseResponseSpec())
					.extract().response();	
		}else if (httpMethod.equalsIgnoreCase("GET")) {
			response =	requestSpec.when().get(endpoint).
					then()
					.spec(BaseResponseSpec())
					.extract().response();	
		}else if (httpMethod.equalsIgnoreCase("DELETE")) {
			response =	requestSpec.when().delete(endpoint).
					then()
					.spec(BaseResponseSpec())
					.extract().response();	
		}else if (httpMethod.equalsIgnoreCase("PUT")) {
			response =	requestSpec.when().put(endpoint).
					then()
					.spec(BaseResponseSpec())
					.extract().response();	
		}
		
	}
	
	@Then("I verify status code is {int}")
	public void i_verify_status_code_is(Integer expectedStatusCode) {
		int actualStatusCode = response.getStatusCode();
	    Assert.assertEquals(actualStatusCode,expectedStatusCode);
	}
	
	@Then("I verify {string} field in response body is {string}")
	public void i_verify_field_in_body_is(String key, String expectedValue) {
	   String strResponse = response.asString();
	   JsonPath js = ReusableMethods.stringToJson(strResponse);
	   String actualValue =  js.getString(key);
	   Assert.assertEquals(actualValue,expectedValue);
	}
	
	@Then("I verify {string} header in response is {string}")
	public void i_verify_header_in_response_is(String headerName, String expectedHeaderValue) {
		String actualHeaderValue = response.getHeader(headerName);
		Assert.assertEquals(actualHeaderValue,expectedHeaderValue);
	}
	
	@Then("I store value of {string} from the response body")
	public void i_store_value_from_response_body(String field) {
	   String strResponse = response.asString();
	   JsonPath js = ReusableMethods.stringToJson(strResponse);
	   String place_id =  js.getString(field);
	   Context.setPlaceID(place_id);
	}

}
