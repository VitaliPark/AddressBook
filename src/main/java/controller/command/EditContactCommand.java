package controller.command;

import javax.servlet.http.HttpServletRequest;

import model.entity.Contact;
import model.service.ContactService;
import constants.Pages;
import constants.PersonColumnNames;
import exceptioin.ContactReadFailedException;

public class EditContactCommand implements Command{

	private String resultString;
	private ContactService contactService;
	private HttpServletRequest request;
	
	public EditContactCommand(ContactService contactService,
			HttpServletRequest request) {
		super();
		this.contactService = contactService;
		this.request = request;
	}

	@Override
	public void execute() {
		String personId = request.getParameter(PersonColumnNames.idPerson);		
		if(personId != null && !personId.isEmpty()){
			System.out.println("hellllooooo");
			setContactData(personId);
		}	
		resultString = Pages.EDIT_PAGE;
	}

	private void setContactData(String personId) {
		try {
			Contact contact = contactService.getContact(Integer.parseInt(personId));
			System.out.println(contact.getFullName());
			request.setAttribute("contact", contact);
		} catch (NumberFormatException | ContactReadFailedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public String getResultPage() {
		return resultString;
	}

}
