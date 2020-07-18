package com.tadigital.advanceassessment.core.models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.adobe.acs.commons.email.EmailService;
import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.WorkflowProcess;
import com.adobe.granite.workflow.metadata.MetaDataMap;

@Component(immediate = true, property = { "process.label=Custom Email Workflow", })
public class EmailWorkFlowModel implements WorkflowProcess {

	// refers to the emailservice which is being imported.
	@Reference
	EmailService emailService;

	// Overrides the execute function
	@Override
	public void execute(WorkItem arg0, WorkflowSession arg1, MetaDataMap arg2) throws WorkflowException {

		// setting up the template path which contains the mail format
		String templatePath = "/etc/notification/email/html/en.html";

		Map<String, String> emailParams = new HashMap<String, String>();

		// setting up the parameters for sending a mail.
		emailParams.put("firstName", "aditya");
		emailParams.put("lastName", "sharma");
		emailParams.put("questions", "WorkFlow email after creating a page");
		emailParams.put("senderEmailAddress", "adityasharma12399@gmail.com");

		// adding the recipients
		String[] recipients = { "adityasharma12399@gmail.com" };

		// sending the email using email service.
		List<String> failureList = emailService.sendEmail(templatePath, emailParams, recipients);

		// checks for the failure list for email sending.
		if (failureList.isEmpty()) {
			System.out.println("Email sent successfully to the recipients");
		} else {
			System.out.println("Email sent failed");
		}
	}
}
