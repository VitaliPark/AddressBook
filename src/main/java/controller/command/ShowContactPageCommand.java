package controller.command;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import model.entity.Contact;
import model.service.ContactService;
import constants.Pages;
import constants.database.PersonColumnNames;
import exceptioin.ContactReadFailedException;

public class ShowContactPageCommand implements Command{

	private String resultString;
	private ContactService contactService;
	private HttpServletRequest request;
	private Logger LOGGER = Logger.getLogger(ShowContactPageCommand.class);
	
	public ShowContactPageCommand(ContactService contactService,
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
				LOGGER.info("Contact info request with id: " + personId);
				setContactData(personId);
				resultString = Pages.EDIT_PAGE;
			} catch (NumberFormatException e) {
				proccessError("Wrong contact id");
			} catch (ContactReadFailedException e) {
				proccessError(e.getMessage());
			}
		}
		else {
			resultString = Pages.EDIT_PAGE;
		}
	}
	
	private void proccessError(String message){
		LOGGER.error(message);
		request.setAttribute("errorMessage", message);
		resultString = Pages.ERROR_PAGE;
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
