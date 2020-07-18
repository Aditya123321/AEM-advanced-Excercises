package com.tadigital.advanceassessment.core.models;

import javax.inject.Inject;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

@Model(adaptables=Resource.class)
public class Emails {

	@Inject
	String mailids;

	public String getMailids() {
		return mailids;
	}

}
	