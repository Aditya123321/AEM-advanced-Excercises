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
package com.tadigital.advanceassessment.core.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

 

import javax.mail.internet.InternetAddress;
import javax.servlet.Servlet;
import javax.servlet.ServletException;

 

import org.apache.commons.mail.HtmlEmail;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

 

import com.day.cq.mailer.MessageGateway;
import com.day.cq.mailer.MessageGatewayService;
import com.tadigital.advanceassessment.core.models.LinkModel;

 


@Component(service = Servlet.class, property = { Constants.SERVICE_DESCRIPTION + "=Mail Servlet",
        "sling.servlet.methods=" + HttpConstants.METHOD_GET,
        "sling.servlet.paths=" + "/bin/servlet/advanceassessment" })
public class ContactUsServlet extends SlingSafeMethodsServlet {

 

    private static final long serialVersionUid = 1L;
    private Logger log = LoggerFactory.getLogger(ContactUsServlet.class);

 

    @Reference
    private MessageGatewayService messageGatewayService;

 

    @Reference
    private MessageGateway<HtmlEmail> messageGateway;

 

    @Override
    protected void doGet(SlingHttpServletRequest req, SlingHttpServletResponse resp)
            throws ServletException, IOException {
        final Resource resource = req.getResource();
     
        String firstName = req.getParameter("fname");
        String lastName = req.getParameter("lname");
        String emailAddress = req.getParameter("email");
        String phoneNumber = req.getParameter("phno");
        String textArea = req.getParameter("ques");
        String checkBox = req.getParameter("checkbox");
        boolean check = false;
        if (checkBox != null) {
            check = true;
        }

 

        try {

 

            ArrayList<InternetAddress> emailRecipients = new ArrayList<>();
            HtmlEmail email1 = new HtmlEmail();

 

              emailRecipients.add(new InternetAddress("adityasharma12399@gmail.com"));

 

            // sending mail
            email1.setTo(emailRecipients);

 

            // checking for cc value
            if (check) {
                email1.addCc(emailAddress); // setting true if cc is checked.
            }

 

            // setting subject of email
            email1.setSubject("AEM AdvancedTrainingproject check");

 

            // setting body of email
            email1.setContent("<pre>Hi " + firstName + " " + lastName + "" + "<br><b>" + textArea + "</b></pre>",
                    "text/html");

 

            // Inject a MessageGateway Service and send the message
            messageGateway = messageGatewayService.getGateway(HtmlEmail.class);
            // Check the logs to see that messageGateway is not null
            messageGateway.send(email1);
            System.out.println(email1);

 

        }
        catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            e.printStackTrace();
            resp.getWriter().write(sw.toString());
        }
        resp.setContentType("text/plain");
        resp.getWriter().write("Email sent from " + emailAddress + " checkbox value = " + checkBox);
    }
}