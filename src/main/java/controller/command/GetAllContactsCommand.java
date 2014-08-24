package controller.command;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import constants.DefaultValues;
import constants.ExceptionMessages;
import constants.Pages;
import controller.PageConfig;
import controller.PageDrawer;
import exceptioin.ContactReadFailedException;
import model.ContactTransferObject;
import model.service.ContactService;

public class GetAllContactsCommand implements Command{

	final static Logger LOGGER = Logger.getLogger(GetAllContactsCommand.class);
	private ContactService contactService;
	private HttpServletRequest request;
	private String resultPage;
	
	public GetAllContactsCommand(ContactService contactService, HttpServletRequest request) {
		this.contactService = contactService;
		this.request = request;
		
	}

	@Override
	public void execute() {
		try {
			int currentPage = getCurrentPage();
			int first = getFirstRecordNumber(currentPage);
			ContactTransferObject result = contactService.getContacts(first, DefaultValues.contactsOnPage);
			int count = result.getNumberOfRecords();
			LOGGER.info("Requested command 'Get Contacts', page: " + currentPage);
			request.setAttribute("allContacts", result.getContacts());
			request.setAttribute("currentPage", currentPage);
			setPagerProperties(request, currentPage, count);
			resultPage = Pages.INDEX_PAGE;
		} catch (ContactReadFailedException e) {
			proccessError(e.getMessage());
		}
	}
	
	private void proccessError(String message){
		LOGGER.error(message);
		request.setAttribute("errorMessage", ExceptionMessages.CONTACT_READ_FAILED);
		resultPage = Pages.ERROR_PAGE;
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
	
	@Override
	public String getResultPage() {
		return resultPage;
	}
	
}
