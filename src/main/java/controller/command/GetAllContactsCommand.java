package controller.command;

import javax.servlet.http.HttpServletRequest;

import constants.DefaultValues;
import constants.Pages;
import controller.PageDrawer;
import exceptioin.ContactReadFailedException;
import model.ContactTransferObject;
import model.PageConfig;
import model.service.ContactService;

public class GetAllContactsCommand implements Command{

	private ContactService contactService;
	private HttpServletRequest request;
	private String resultString;
	
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
			request.setAttribute("allContacts", result.getContacts());
			request.setAttribute("currentPage", currentPage);
			setPagerProperties(request, currentPage, count);
			resultString = Pages.INDEX_PAGE;
		} catch (ContactReadFailedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	
	@Override
	public String getResultPage() {
		return resultString;
	}
	
}
