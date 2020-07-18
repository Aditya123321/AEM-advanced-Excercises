package com.tadigital.advanceassessment.core.models;

import java.util.List;
import javax.inject.Inject;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

//Model which is adapting Resource Interface Properties and all injection fields are optional ie it will not throw exception
@Model(
	    adaptables = {Resource.class},
	    defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public interface CarouselSlideModel {
	
	//Injecting slides as a list in this Model
    //If defined then Resource must have this property and it will inject else it will return null
	@Inject
	List<slides> getSlides(); 
	
	//Model which is adapting Resource Interface Properties and all injection fields are optional ie it will not throw exception
	@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
	  interface slides {
		  
		//Injecting imagepath in this Model
	    @Inject
	    String getImagepath();
	    
	    //Injecting menulink in this Model
	    @Inject
	    String getMenulink();
	    
	  }

}
