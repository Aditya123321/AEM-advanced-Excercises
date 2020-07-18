package com.tadigital.advanceassessment.core.models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.wcm.api.Page;

//Model which is adapting Resource Interface Properties and all injection fields are optional ie it will not throw exception
@Model(adaptables = {SlingHttpServletRequest.class , Resource.class})
public class PRContactModel {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PRContactModel.class);
	
	//mapping the contactfilepath
	@Optional
	@ValueMapValue
	private String contactfilePath;

	//Injecting Resource Interface Method object
	@SlingObject
	private ResourceResolver resourceResolver; 
	private List<PRContactList> datafromContactModelList = new ArrayList<PRContactList>();
	
	//Method will be Excecuted Once all Injection done
	@PostConstruct
	public void init() {
	  try {
		Resource resource = resourceResolver.getResource(contactfilePath);
		
		if(resource!=null) {

			Page parentpage = resource.adaptTo(Page.class);
			if(parentpage!=null) {
				Iterator<Page> listChildPage = parentpage.listChildren();
				if(listChildPage == null) {
					PRContactList contatclist = new PRContactList();
					Resource childresource = parentpage.getContentResource();
					contatclist = childresource.adaptTo(PRContactList.class);
					datafromContactModelList.add(contatclist);
				}else {
					while(listChildPage.hasNext()) {
						PRContactList contatclist = new PRContactList();
						Page childpage = listChildPage.next();
						Resource childresource = childpage.getContentResource();
						contatclist = childresource.adaptTo(PRContactList.class);
						datafromContactModelList.add(contatclist);
					}
				}
			}
		}
		
	}catch(Exception e) { e.printStackTrace();}
 }

	public List<PRContactList> getDatafromContactModelList() {
		return datafromContactModelList;
	}
}
	