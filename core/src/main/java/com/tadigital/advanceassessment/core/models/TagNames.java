package com.tadigital.advanceassessment.core.models;

import javax.inject.Inject;
import javax.inject.Named;

 

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

 

/**
 * @author nidhi.chaubey
 *
 */
@Model(adaptables= {Resource.class},
defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class TagNames {
    
    /**Injecting jcr:title Value
     * 
     */
    @Inject
    @Named("jcr:title")
    private String tagName;
    
    /**
     * @param tagName
     */
    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
    

 

    /**
     * @return tagNmae
     */
    public String getTagName() {
        return tagName;
    }

 

    

 

}