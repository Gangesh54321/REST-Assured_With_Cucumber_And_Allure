package resources;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.github.javafaker.Faker;

import PoJo.AddPlaceRequest;
import PoJo.DeletePlaceRequest;
import PoJo.Location;
import PoJo.UpdatePlaceRequest;

public class TestData {
	Faker faker = new Faker();
	
	public AddPlaceRequest AddPlacePayload() {
		AddPlaceRequest addPlace = new AddPlaceRequest();
		addPlace.setAccuracy(50);
		addPlace.setAddress(faker.address().fullAddress());
		addPlace.setLanguage("Spanish");
		addPlace.setName(faker.funnyName().name());
		addPlace.setPhone_number(faker.phoneNumber().cellPhone());
		addPlace.setWebsite(faker.internet().domainName());
		
		List<String> myTypesList = new ArrayList<String>();
		myTypesList.add(faker.book().title());
		myTypesList.add(faker.ancient().god());
		addPlace.setTypes(myTypesList);
		
		Location loc = new Location();
		Double lat = Double.parseDouble(faker.address().latitude());
		Double lng = Double.parseDouble(faker.address().longitude());
		loc.setLat(lat);
		loc.setLng(lng);
		addPlace.setLocation(loc);
		return addPlace;
	}
	
	public DeletePlaceRequest DeletePlacePayload(String placeId) {
		DeletePlaceRequest deletePlace = new DeletePlaceRequest();
		deletePlace.setPlace_id(placeId);
		return deletePlace;
	}
	
	public UpdatePlaceRequest UpdatePlacePayload(String placeId) throws IOException {
		UpdatePlaceRequest updatePlace = new UpdatePlaceRequest();
		String newAddress = faker.address().fullAddress();
	
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\java\\resources\\Global.properties");
		prop.load(fis);
		String keyValue= prop.getProperty("key");
		
		updatePlace.setPlace_id(placeId);
		updatePlace.setAddress(newAddress);
		updatePlace.setKey(keyValue);
		
		return updatePlace;
	}
}
