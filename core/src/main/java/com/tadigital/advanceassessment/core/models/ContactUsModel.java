package com.tadigital.advanceassessment.core.models;

import java.util.List;
import javax.inject.Inject;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

import com.tadigital.advanceassessment.core.models.LinkModel.Links; 

@Model(
        adaptables = {Resource.class},
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)

public class ContactUsModel {
	
	 @Inject
     private List<Emails> recepient;

	public List<Emails> getRecepient() {
		return recepient;
	}

	/*
	 * public void setRecepient(List<Emails> recepient) { this.recepient =
	 * recepient; }
	 */
	 
	 

}
