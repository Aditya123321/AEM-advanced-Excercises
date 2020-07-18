package com.tadigital.advanceassessment.core.models;

import java.util.List;
import javax.inject.Inject;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model; 

//Model which is adapting Resource Interface Properties and all injection fields are optional ie it will not throw exception
@Model(adaptables = {Resource.class},
	    defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public interface LinkModel {
	
	//Injecting linklabel as a list in this Model
    //If defined then Resource must have this property and it will inject else it will return null
	 @Inject
     List<Links> getLinklabel(); 
     
	 
     @Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
     interface Links {
         
       //Injecting linktexts in this Model
       //If defined then Resource must have this property and it will inject else it will return null	 
       @Inject
       String getLinktexts();
       
       //Injecting linkurl in this Model
       //If defined then Resource must have this property and it will inject else it will return null	 
       @Inject
       String getLinkurl();
       
     }
}
