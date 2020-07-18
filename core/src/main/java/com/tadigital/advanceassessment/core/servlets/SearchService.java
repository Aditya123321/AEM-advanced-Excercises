package com.tadigital.advanceassessment.core.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

 

import javax.jcr.Session;

 

import javax.servlet.Servlet;
import javax.servlet.ServletException;

 

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
//import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

 

import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;
import com.google.gson.Gson;
import com.tadigital.advanceassessment.core.models.SearchModel;


 

@Component(service=Servlet.class,
          property={
                  Constants.SERVICE_DESCRIPTION + "=Simple Demo Servlet",
                  "sling.servlet.methods=" + HttpConstants.METHOD_GET,
                  "sling.servlet.resourceTypes="+ "advanceassessment/components/structure/page",
                  "sling.servlet.extensions=" + "txt",
                  "sling.servlet.paths=/bin/searchservice"
          })
public class SearchService extends SlingSafeMethodsServlet{

 

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    @Reference
    private transient QueryBuilder queryBuilder;
    
    @Override
    protected void doGet(final SlingHttpServletRequest req, final SlingHttpServletResponse res) throws ServletException, IOException{
        
        final Resource resource = req.getResource();
        Map<String,String> map = new HashMap<>();
        PrintWriter out = res.getWriter();
        List<SearchModel> searchdetails = new ArrayList<SearchModel>();
        
        res.setContentType("text/html");
        String searchKey = req.getParameter("searchKey");
        String path = req.getParameter("path");
        
        System.out.println("::::::::::::Page Path:::::::::::" + path);
        System.out.println("::::::::::::Dearch key:::::::::::" + searchKey);
        
        //map for query
        map.put("path",path);
        map.put("type","cq:page");
        map.put("fulltext",searchKey);
        
        ResourceResolver resourceResolver = req.getResourceResolver();
        
        Session session = resourceResolver.adaptTo(Session.class);
        
        Query query = queryBuilder.createQuery(PredicateGroup.create(map), session);
        SearchResult searchResult = query.getResult();
        
        
        
        for(Hit hit:searchResult.getHits()) {
            
            try {
            	System.out.println("Hit " + hit);
                String hitPath = hit.getPath();
                if((hitPath != null) && (hitPath.contains("jcr:content") == true)) {
                    String array[];
                    System.out.println("Hit Path " + hitPath);
                    array = hitPath.split("/");
                    int index = array.length-1;
                    for(index = array.length-1; index >= 0; index--) {
                        if(array[index].equals("jcr:content")) 
                            break;
                    }
                    
                    String resourcePath = "";
                    for(int i = 0; i <= index; i++) {
                        resourcePath += "/" + array[i];
                    }
                    
                    Resource pathResource = resourceResolver.getResource(resourcePath);
                    ValueMap valueMap = pathResource.getValueMap();
                    if(pathResource != null) {
                        System.out.println("::::::::::::Search Result Page title:::::::::::" + valueMap.get("jcr:title"));
                        
                        
                        System.out.println(" ");
                        String resultPath = pathResource.getParent().getPath()+".html";
                        System.out.println("::::::::::::Search Result Page Path:::::::::::" + resultPath);
                        
                        out.println("<p>"+(String)valueMap.get("jcr:title") + "</p>");
                        out.println("<p>"+resultPath +"</p>");
                        out.println("<p>"+(String)valueMap.get("text") + "</p><br>");
                        
                        String title = (String)valueMap.get("jcr:title");
                        String pagePath = resultPath;
                        String content = (String)valueMap.get("text");
                        
                        SearchModel searchmodel = new SearchModel(title,pagePath);
                        searchdetails.add(searchmodel);
                    }
                }else {
                    out.print("No result found");
                }
                
            }catch(Exception e) {
                
            }
        }
        
        if(searchResult.getHits().isEmpty()) {
            out.print("No result found");
        }else {
            Gson gson = new Gson();
            res.getWriter().write(gson.toJson(searchdetails));
        }
        out.flush();
        
        
    }
    
}