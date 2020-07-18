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

 

@Model(adaptables = SlingHttpServletRequest.class)
public class MyModel {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(MyModel.class);
    
    @Optional
    @ValueMapValue
    private String filePath;
    
    
    @SlingObject
    private ResourceResolver resourceResolver;
    private List<ListPageDetail> datafromModelList = new ArrayList<ListPageDetail>();
    //private String currentpagepath;
    
    
    @PostConstruct
    public void init() {
        try {
            if(!filePath.isEmpty()) {
                Resource resource = resourceResolver.getResource(filePath);
                //LOGGER.info("::::: RESOURCE PATH ::::: " + resource.getPath());
                if (resource!=null) {
                    Page parentPage = resource.adaptTo(Page.class);
                    if(parentPage!=null)
                    {
                        Iterator<Page> listChildPages = parentPage.listChildren();
                        while (listChildPages.hasNext()) {
                            
                            ListPageDetail detail = new ListPageDetail();
                            Page childPage = listChildPages.next();
                            
                            //LOGGER.info(":::::::child resource path :::::::"+childPage.get);
                            //getting jcr content path of child pages
                            Resource res = childPage.getContentResource();     
                            LOGGER.info(":::::::child resource path :::::::"+res.getPath());
                            
                            detail = res.adaptTo(ListPageDetail.class);
                            
                            detail.setPageurl(res.getParent().getPath());
                            LOGGER.info(":::::::child page path :::::::"+res.getParent().getPath());
                            datafromModelList.add(detail);
                        }
                    }
                }
            }
        }
        catch(Exception e) { e.printStackTrace();}
    }
//    public String getCurrentpagepath() {
//        return currentpagepath;
//    }
    public List<ListPageDetail> getDatafromModelList() {
        return datafromModelList;
    }
}