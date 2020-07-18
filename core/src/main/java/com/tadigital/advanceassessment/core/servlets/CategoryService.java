package com.tadigital.advanceassessment.core.servlets;

/*
 *  Copyright 2015 Adobe Systems Incorporated
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

 

import javax.servlet.Servlet;
import javax.servlet.ServletException;

 

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

 

import com.day.cq.wcm.api.Page;
 
/**
 * @author aditya.sharma
 *
 */
@Component(service=Servlet.class,
           property={
                   Constants.SERVICE_DESCRIPTION + "=FAQ Servlet",
                   "sling.servlet.methods=" + HttpConstants.METHOD_GET,
                   "sling.servlet.resourceTypes="+ "advanceassessment/components/content/faq",
                   "sling.servlet.extensions=" + "txt",
                   "sling.servlet.paths=/bin/servicecategory/faq"
           })
public class CategoryService extends SlingSafeMethodsServlet {

 

    private static final long serialVersionUID = 1L;
  
    /**
     * Method for reading Category selected and returning list of pages with that category
     */
    @Override
    protected void doGet(final SlingHttpServletRequest req,
            final SlingHttpServletResponse resp) throws ServletException, IOException {
        
        PrintWriter out = resp.getWriter();
        
        /**
         * Getting Parameter through ajax call
         */
        String selectedcategory = req.getParameter("selected");
        String path = req.getParameter("path");
        System.out.println("path"+path);
        System.out.println("selected"+selectedcategory);
        
        Resource res = null;
        
        ResourceResolver resourceResolver = req.getResourceResolver();
        resp.setContentType("text/plain");
       
        /**
         *     Getting Resource Object for for data located at this path 
         */
        res = resourceResolver.getResource(path);
        if(res!=null) {
        /**
         *     Adapting to Page class type
         */
        	System.out.println("resource"+res);
        	Page parentPage = res.adaptTo(Page.class);
        	System.out.println("parent page"+parentPage);
           
            if(parentPage != null) {
    
                /**
                 *     Getting List of Child Pages
                 */
                Iterator<Page> listPages = parentPage.listChildren();
                System.out.println("list pages"+listPages);
                
                while(listPages.hasNext()) {
                    Page childPage = listPages.next();
                    System.out.println("child page"+childPage);
                   
                    /**
                     *     Getting Resource Object for for data located at jcr:content of this path 
                     */
                    res = childPage.getContentResource();
                    System.out.println("child resource"+res);
                   
                    /**
                     *     Getting Resource Object for for data located at component inside of this path 
                     */
                    res = res.getChild(res.getPath()+"/root/responsivegrid/faq");
                    System.out.println("get child resource faq"+res);
                    
                    ValueMap mp = res.getValueMap();                    
                    
                    if(mp != null) {
                        System.out.println((String)mp.get("cat") +"  "+ selectedcategory);
                        
                        /**
                         *     Comparing Category Value through ajax call and Page Category Value
                         */
                        if(((String)mp.get("cat")).equals(selectedcategory)) {
                            
                            System.out.println("true");
                            out.print("<p>" + (String)mp.get("ques") + "</p><br>");
                            out.print("<p>" + (String)mp.get("ans") + "</p><br><br>");
                        } 
                    }
                   
                }
                
            }
        }
    }
}