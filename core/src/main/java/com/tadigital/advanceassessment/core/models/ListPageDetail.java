package com.tadigital.advanceassessment.core.models;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;

//Model which is adapting Resource Interface Properties and all injection fields are optional ie it will not throw exception
@Model(adaptables = Resource.class)
public class ListPageDetail {         // model for fetching the page details
	
	//Injecting title using named annotation in this Model
   	@Inject
	@Named("jcr:title")
	private String title;
	
    //Injecting pagedate using named annotation in this Model 
	@Inject
	@Named("date")
	private String pagedate;
	
	private String pageurl;
	
	//setter method for retrieving the pageurl
	public void setPageurl(String pageurl) {
		this.pageurl = pageurl;
	}
	
	public String getPageurl() {
		return pageurl;
	}
	
	public String getTitle() {
		return title;
	}
	
	//setter method for retrieving the title
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getPagedate() {
		return pagedate;
	}
	
	//setter methods for retrieving the pagedate
	public void setPagedate(String pagedate) {
		this.pagedate = pagedate;
	}
	
}