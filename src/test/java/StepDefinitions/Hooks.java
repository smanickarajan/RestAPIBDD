package StepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@Deleteplace")
public void beforeScenario() throws IOException {
		StepDefinition m=new StepDefinition();
		if (StepDefinition.place_id==null) {
		m.add_place_payload_with("manick", "french", "Nagercoil");
		m.user_calls_with_http_request("AddplaceAPI", "POST");
		m.verify_place_id_created_maps_to_using("manick", "GetplaceAPI");
		
		}
	}
}
