package com.tadigital.advanceassessment.core.models;

//model to implement taglist component in advance exercises.
public class SelectedTagList {

	private String title;
	private String pagePath;
	
	//constructor for selectedtaglist class
	public SelectedTagList(String title, String pagePath) {
		super();
		this.title = title;
		this.pagePath = pagePath;
	}

	//getter method for title
	public String getTitle() {
		return title;
	}

	//getter method for title
	public String getPagePath() {
		return pagePath;
	}
	
}
