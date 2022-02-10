package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	public static RequestSpecification reqspec;
	
	public RequestSpecification requestSpecification() throws IOException {
		if(reqspec==null) {
		PrintStream log =new PrintStream(new FileOutputStream("logging.txt"));
		
		RestAssured.useRelaxedHTTPSValidation();
		
		//Create API
		
		reqspec = new RequestSpecBuilder().setBaseUri(getGlobalProperties("baseUrl")).setContentType(ContentType.JSON)
				.addQueryParam("key", "qaclick123").addFilter(RequestLoggingFilter.logRequestTo(log))
				.addFilter(ResponseLoggingFilter.logResponseTo(log)).build();
				
		return reqspec;
		}
		return reqspec;
	}
	
	public String getGlobalProperties(String Key) throws IOException {
		FileInputStream fis= new FileInputStream("..\\APIFramework\\src\\test\\java\\resources\\global.properties");
		
		Properties prop=new Properties();
		prop.load(fis);
		
		return prop.getProperty(Key);
	}
	
	public String getJsonPathValue(Response response, String Key) {
		String resp=response.asString();
		JsonPath js=new JsonPath(resp);
		return js.get(Key).toString();
	}
	
	
}
