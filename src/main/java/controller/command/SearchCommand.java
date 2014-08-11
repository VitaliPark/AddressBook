package controller.command;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import constants.AddressColumnNames;
import constants.PersonColumnNames;
import model.entity.Contact;
import model.service.SearchService;

public class SearchCommand implements Command{

	private String resultString;
	private HttpServletRequest request;
	private SearchService searchService;
	
	public SearchCommand(HttpServletRequest request, SearchService searchService) {
		super();
		this.request = request;
		this.searchService = searchService;
	}

	@Override
	public void execute() {
		
	}

	@Override
	public String getResultPage() {
		return resultString;
	}
	
//	private Contact parseRequest(){
//		String firstName = request.getParameter(PersonColumnNames.firstName);
//		String secondName = request.getParameter(PersonColumnNames.secondName);
//		String patronnymicName = request.getParameter(PersonColumnNames.patronymicName);
//		String dateString = request.getParameter(PersonColumnNames.dateOfBirth);
//		String sex = request.getParameter(PersonColumnNames.sex);
//		String citizenship = request.getParameter(PersonColumnNames.citizenship);
//		String maritalStatus = request.getParameter(PersonColumnNames.maritalStatus);
//		String country = request.getParameter(AddressColumnNames.country);
//		String city = request.getParameter(AddressColumnNames.city);
//		String houseNumber = request.getParameter(AddressColumnNames.houseNumber);
//		String street = request.getParameter(AddressColumnNames.street);
//		String apartment = request.getParameter(AddressColumnNames.apartment);
//		String postIndex = request.getParameter(AddressColumnNames.postIndex);
//	}

}
