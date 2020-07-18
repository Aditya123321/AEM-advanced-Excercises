package com.tadigital.advanceassessment.core.models;

//model to implement search component in advance exercises.
public class SearchModel {
	 
	private String title;
	private String pagePath;
	
	//constructor for searchmodel class
	public SearchModel(String title, String pagePath) {
		super();
		this.title = title;
		this.pagePath = pagePath;
	}
	
	//getter method for title
	public String getTitle() {
		return title;
	}
 
	//getter method for pagepath
	public String getPagePath() {
		return pagePath;
	}
	
	
}
