package controller.command;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import constants.DefaultValues;
import constants.ExceptionMessages;
import constants.Pages;
import controller.PageDrawer;
import exceptioin.ContactReadFailedException;
import model.ContactTransferObject;
import model.PageConfig;
import model.SearchRequestParser;
import model.SearchRequest;
import model.ValidationObject;
import model.Validator;
import model.service.ContactService;

public class SearchCommand implements Command{

	private String resultPage;
	private HttpServletRequest request;
	private ContactService contactService;
	private Logger LOGGER = Logger.getLogger(SearchCommand.class);
	
	public SearchCommand(HttpServletRequest request, ContactService searchService) {
		super();
		this.request = request;
		this.contactService = searchService;
	}

	@Override
	public void execute() {
		LOGGER.info("Requested command 'Search contacts'");
		SearchRequest searchRequest = getSearchRequest(request);
		Validator validator = new Validator();
		ValidationObject validationResult = validator.validateSearchRequest(searchRequest);
		if(!validationResult.isEmpty()){
			processValidationFailure(validationResult);
		}
		else{
			processSearchRequest(searchRequest);
		}
	}

	@Override
	public String getResultPage() {
		return resultPage;
	}
	
	private void processValidationFailure(ValidationObject result){
		LOGGER.error("Validation failed: wrong fields:"  + result.toString());
		proccessVaidationError(result);
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
			resultPage = Pages.SEARCH_RESULT;
		} catch (ContactReadFailedException e) {
			proccessError(e.getMessage());
		}
	}
	
	private void proccessError(String message){
		resultPage = Pages.ERROR_PAGE;
		request.setAttribute("errorMessage", ExceptionMessages.CONTACT_READ_FAILED);
		LOGGER.error(message);
	}
	
	private void proccessVaidationError(ValidationObject result){
		LOGGER.error("Field validation failed");
		Command command = new ShowSearchPageCommand();
		command.execute();
		request.setAttribute("result", "Validation failed, wrong fields:" + result.toString());
		resultPage = command.getResultPage();
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
			searchRequest = (SearchRequest) request.getSession().getAttribute(searchId);
			return searchRequest;
		} else {
			return SearchRequestParser.INSTANCE.parseSearchRequest(request);
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
