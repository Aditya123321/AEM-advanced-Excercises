package com.tadigital.advanceassessment.core.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

 

import javax.annotation.PostConstruct;

 

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.osgi.service.component.annotations.Reference;

 


/**
 * @author nidhi.chaubey
 *
 */
@Model(adaptables= {Resource.class},
defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class TagListModel {
    
    
    /**
     * Injecting path where tags are present
     */
    @Optional
    @ValueMapValue
    private String tagurl;
    

 

    /**
     * Inject ResourceResolver Interface Object
     */
    @SlingObject
    private ResourceResolver resourceResolver;
    
    /**
     * Creating List of TagNames class 
     */
    private List<TagNames> tagnameList = new ArrayList<TagNames>();
    
    @Reference 
    Resource resource;
    
    /**
     * Executed Once all Injection is done
     */
    @PostConstruct
    public void init() {
        
        try {
            
            /**
             *     Getting Resource Object for for data located at this path 
             */
            Resource resource = resourceResolver.getResource(tagurl);
        
                if(resource != null) {
                    Iterator<Resource> childresources = resource.listChildren();
                    
                    if(resource!=null) {
                        while(childresources.hasNext()) {
                            
                            TagNames listname = new TagNames();
                            Resource childres = childresources.next(); 
                        
                            /**
                             *     Adapting to TagNames class type
                             */
                            listname = childres.adaptTo(TagNames.class);
                                    
                            tagnameList.add(listname);
                         }
                     }
                  }
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

 

    
    public List<TagNames> getTagnameList() {
        return tagnameList;
    }

 

}