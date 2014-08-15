package controller.command;

import javax.servlet.http.HttpServletRequest;

import model.entity.Contact;
import model.service.ContactService;
import constants.Pages;
import constants.database.PersonColumnNames;
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
			try {
				setContactData(personId);
				resultString = Pages.EDIT_PAGE;
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (ContactReadFailedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			resultString = Pages.EDIT_PAGE;
		}
	}

	private void setContactData(String personId) throws NumberFormatException, ContactReadFailedException{
		Contact contact = contactService.getContact(Integer.parseInt(personId));
		request.setAttribute("contact", contact);
	}

	@Override
	public String getResultPage() {
		return resultString;
	}

}
