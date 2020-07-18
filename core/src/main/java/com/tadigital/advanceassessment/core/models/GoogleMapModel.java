package com.tadigital.advanceassessment.core.models;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

//model for googlemaps component
@Model(adaptables = { Resource.class }, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class GoogleMapModel {

	//Injecting address in this Model
    //If defined then Resource must have this property and it will inject else it will return null
	@Inject
	String address;

	//Injecting latitude in this Model
    //If defined then Resource must have this property and it will inject else it will return null
	@Inject
	String latitude;

	//Injecting longitude in this Model
    //If defined then Resource must have this property and it will inject else it will return null
	@Inject
	String longitude;

	//String function which will hold the appeneded latitute and longitude.
	String latlon;

	//Method will be Excecuted Once all Injection done
	@PostConstruct
	public void check() {
		latlon = latitude + ',' + longitude;
	}

	//getter method for address field
	public String getAddress() {
		return address;
	}
	
	//getter method for latitude and longitude appended string
	public String getLatlon() {
		return latlon;
	}

}