package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.Addplace;
import pojo.Location;

public class TestDataBuild {

	
	public Addplace addPlacePayLoad(String name,String language,String address) {
		Addplace p = new Addplace();
		p.setAccuracy(50);
		p.setAddress(address);
		p.setLanguage(language);
		Location l=new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		p.setLocation(l);
		p.setName(name);
		p.setPhone_number("(+91) 983 893 3937");
		p.setWebsite("http://google.com");
		List<String> myList=new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");
		p.setTypes(myList);
		
		return p;
	}
	
	public String deletePlacePayload(String PlaceId) {
		return "{\r\n    \"place_id\":\""+PlaceId+"\"\r\n}";
	}
}
