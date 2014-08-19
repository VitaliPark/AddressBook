package controller.command;

import java.net.HttpRetryException;

import javax.servlet.http.HttpServletRequest;

import constants.DefaultValues;
import constants.Pages;
import controller.PageDrawer;
import exceptioin.ContactReadFailedException;
import model.ContactTransferObject;
import model.PageConfig;
import model.RequestParser;
import model.SearchRequest;
import model.ValidationObject;
import model.Validator;
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
		SearchRequest searchRequest = getSearchRequest(request);
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
		try {
			int currentPage = getCurrentPage();
			int first = getFirstRecordNumber(currentPage);
			ContactTransferObject searchResult = contactService.performSearch(searchRequest, first, DefaultValues.contactsOnPage);
			int count = searchResult.getNumberOfRecords();
			request.setAttribute("result", searchResult.getContacts());
			request.setAttribute("currentPage", currentPage);
			setSearchRequestInfo(request, searchRequest);
			setPagerProperties(request, currentPage, count);
			resultString = Pages.SEARCH_RESULT;
		} catch (ContactReadFailedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void setSearchRequestInfo(HttpServletRequest request, SearchRequest searchRequest){
		String searchRequestId = Integer.toString(searchRequest.hashCode());
		request.setAttribute("requestId", searchRequestId);
		request.getSession().setAttribute(searchRequestId, searchRequest);
	}
	
	private SearchRequest getSearchRequest(HttpServletRequest request){
		SearchRequest searchRequest = null;
		String searchId = request.getParameter("requestId");
		if(searchId != null && !searchId.isEmpty()){
			System.out.println("we got it");
			searchRequest = (SearchRequest) request.getSession().getAttribute(searchId);
			return searchRequest;
		} else {
			System.out.println("we dont have it");
			return RequestParser.INSTANCE.parseSearchRequest(request);
		}
	}
	
	private int getFirstRecordNumber(int currentPage){
		return (currentPage-1) * DefaultValues.contactsOnPage;
	}
	
	private int getCurrentPage() {
		String currentPageString = request.getParameter("currentPage");
		int currentPage;
		if(currentPageString != null && !currentPageString.isEmpty()){
			currentPage = Integer.parseInt(currentPageString);
		}
		else {
			currentPage = 1;
		}
		return currentPage;
	}
	
	private void setPagerProperties(HttpServletRequest request, int newPage, int count){
		PageConfig pageConfig = PageDrawer.INSTANCE.getPageConfig(count, newPage);
		request.setAttribute("pageConfig", pageConfig);
	}
}
