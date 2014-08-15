package controller.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import constants.Pages;
import exceptioin.ContactReadFailedException;
import model.entity.Contact;
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
			List<Contact> result = contactService.getAllContacts(0, 5);
			request.setAttribute("allContacts", result);
			resultString = Pages.INDEX_PAGE;
		} catch (ContactReadFailedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public String getResultPage() {
		return resultString;
	}
	
}
