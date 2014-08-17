package controller.command;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import constants.Pages;
import exceptioin.ContactReadFailedException;
import model.RequestParser;
import model.SearchRequest;
import model.ValidationObject;
import model.Validator;
import model.entity.Contact;
import model.service.ContactService;

public class SearchCommand implements Command{

	private String resultString;
	private HttpServletRequest request;
	private ContactService contactService;
	
	public SearchCommand(HttpServletRequest request, ContactService searchService) {
		super();
		this.request = request;
		this.contactService = searchService;
	}

	@Override
	public void execute() {
		SearchRequest searchRequest = RequestParser.INSTANCE.parseSearchRequest(request);
		ValidationObject validationResult = Validator.INSTANCE.validateSearchRequest(searchRequest);
		System.out.println(validationResult.toString());
		if(!validationResult.isEmpty()){
			processValidationFailure(validationResult);
		}
		else{
			processSearchRequest(searchRequest);
		}
	}

	@Override
	public String getResultPage() {
		return resultString;
	}
	
	private void processValidationFailure(ValidationObject result){
		System.out.println("ValidationFailed");
	}

	private void processSearchRequest(SearchRequest searchRequest){
		List<Contact> result;
		try {
			result = contactService.perfromSearch(searchRequest, 0, 10);
			request.setAttribute("result", result);
			resultString = Pages.SEARCH_RESULT;
		} catch (ContactReadFailedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
