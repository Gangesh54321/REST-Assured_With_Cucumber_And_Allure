package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SetupSpecs {
	
	public static RequestSpecification baseReqSpec;
	
	public RequestSpecification BaseRequestSpec() throws IOException {
		if(baseReqSpec == null) {
			String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
			PrintStream requestLog = new PrintStream(new FileOutputStream(System.getProperty("user.dir")+"\\src\\test\\java\\output\\logfiles\\"+timeStamp+"_Request_Log.txt"));
			PrintStream responseLog = new PrintStream(new FileOutputStream(System.getProperty("user.dir")+"\\src\\test\\java\\output\\logfiles\\"+timeStamp+"_Response_Log.txt"));
			
			String baseUrl = this.getGlobalValues("baseUrl");
			
			baseReqSpec = new RequestSpecBuilder()
					.setBaseUri(baseUrl)
					.addQueryParam("key", "qaclick123")
					.setContentType(ContentType.JSON)
					.addFilter(RequestLoggingFilter.logRequestTo(requestLog))
					.addFilter(ResponseLoggingFilter.logResponseTo(responseLog))
					.build();
			
			return baseReqSpec;
		}
		return baseReqSpec;
	}
	
	public ResponseSpecification BaseResponseSpec() {
		ResponseSpecification baseResSpec = new ResponseSpecBuilder()
				.expectStatusCode(200)
				.expectContentType(ContentType.JSON)
				.build();
		return baseResSpec;
	}
	
	public String getGlobalValues(String propertyName) throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\java\\resources\\Global.properties");
		prop.load(fis);
		String propertyValue= prop.getProperty(propertyName);
		return propertyValue;
	}
}
