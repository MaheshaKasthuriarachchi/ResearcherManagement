package com;

import model.Researcher;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Researchers")
public class ResearcherService {

	Researcher researcher = new Researcher();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readresearchers() {
		return researcher.readReseachers();
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertResearcher(@FormParam("researcherID") String researcherID,
			@FormParam("researcherFname") String researcherFname, @FormParam("researcherLName") String researcherLname,
			@FormParam("researcherEmail") String researcherEmail,
			@FormParam("researcherPhoneNo") String researcherPhoneNo,
			@FormParam("researcherSpecialization") String researcherSpecialization,
			@FormParam("researchTitle") String researchTitle) {
		String output = researcher.insertReseacher(researcherFname, researcherLname, researcherEmail, researcherPhoneNo,
				researcherSpecialization, researchTitle);
		return output;
	}

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateResearcher(String researcherData) {
		// Convert the input string to a JSON object
		JsonObject researcherObj = new JsonParser().parse(researcherData).getAsJsonObject();
		// Read the values from the JSON object
		String researcherID = researcherObj.get("researcherID").getAsString();
		String researcherFname = researcherObj.get("researcherFname").getAsString();
		String researcherLname = researcherObj.get("researcherLname").getAsString();
		String researcherEmail = researcherObj.get("researcherEmail").getAsString();
		String researcherPhoneNo = researcherObj.get("researcherPhoneNoe").getAsString();
		String researcherSpecialization = researcherObj.get("researcherSpecialization").getAsString();
		String researchTitle = researcherObj.get("researchTitle").getAsString();
		String output = researcher.updateReseacher(researcherID, researcherFname, researcherLname, researcherEmail,
				researcherPhoneNo, researcherSpecialization, researchTitle);

		return output;
	}

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteResearcher(String researcherData) {
		// Convert the input string to an XML document
		Document doc = Jsoup.parse(researcherData, "", Parser.xmlParser());

		String reseacherID = doc.select("researcherID").text();
		String output = researcher.deleteReseacher(reseacherID);
		return output;
	}

}
