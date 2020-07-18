package com.tadigital.advanceassessment.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

 

@Model(adaptables = Resource.class)
public class PRContactList {

 

    
    public String getCompany() {
        return company;
    }

 

    public void setCompany(String company) {
        this.company = company;
    }

 

    public String getTitle() {
        return title;
    }

 

    public void setTitle(String title) {
        this.title = title;
    }

 

    public String getName() {
        return name;
    }

 

    public void setName(String name) {
        this.name = name;
    }

 

    public String getPhone() {
        return phone;
    }

 

    public void setPhone(String phone) {
        this.phone = phone;
    }

 

    public String getFax() {
        return fax;
    }

 

    public void setFax(String fax) {
        this.fax = fax;
    }

 

    public String getEmail() {
        return email;
    }

 

    public void setEmail(String email) {
        this.email = email;
    }

 

    public String getAltemail() {
        return altemail;
    }

 

    public void setAltemail(String altemail) {
        this.altemail = altemail;
    }

 

    @Optional
    @ValueMapValue
    private String company;
    
    @Optional
    @ValueMapValue
    private String title;
    
    @Optional
    @ValueMapValue
    private String name;
    
    @Optional
    @ValueMapValue
    private String phone;
    
    @Optional
    @ValueMapValue
    private String fax;
    
    @Optional
    @ValueMapValue
    private String email;
    
    @Optional
    @ValueMapValue
    private String altemail;
    
}