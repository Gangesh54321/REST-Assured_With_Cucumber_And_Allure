package Utils;

import io.restassured.path.json.JsonPath;

public class ReusableMethods {
	
	public static JsonPath stringToJson(String response) {
		JsonPath js = new JsonPath(response);
		return js;
	}

}
