package org.millr.sling.servlets;

import java.io.IOException;

import javax.servlet.ServletException;

import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.millr.sling.models.SampleContent;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SlingServlet(
	    resourceTypes = "millr/sling/exporter/sample",
	    selectors = "servlet",
	    extensions = "json",
	    methods = "GET")
public class SampleContentServlet extends SlingSafeMethodsServlet {

	private static final long serialVersionUID = -1765279544984834180L;
	
	@Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        Resource sampleResource = request.getResource();
        SampleContent sampleContent = sampleResource.adaptTo(SampleContent.class);
        
        ObjectMapper objectMapper = new ObjectMapper();
        String json = "{}";
        try {
            json = objectMapper.writeValueAsString(sampleContent);
        } catch (JsonProcessingException ex) {
            // Silently die as this is a POC
        }
        // The magic to make localized content work.
        // BOTH ContentType and CharacterEncoding must be set.
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }
	
}