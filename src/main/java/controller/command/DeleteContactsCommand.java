package controller.command;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import constants.ExceptionMessages;
import constants.Pages;
import exceptioin.ContactDelitionFailedException;
import model.service.ContactService;

public class DeleteContactsCommand implements Command{

	private HttpServletRequest request;
	private ContactService service;
	private String resultPage;
	private Logger LOGGER = Logger.getLogger(DeleteContactsCommand.class);
	
	public DeleteContactsCommand(ContactService service, HttpServletRequest request) {
		this.request = request;
		this.service = service;
	}

	@Override
	public void execute() {
		String [] contactId = request.getParameterValues("contactId");
		LOGGER.error("Requested command 'Delete contacts'");
		try {
			for(int i = 0; i < contactId.length; ++i){
				int id = Integer.parseInt(contactId[i]);
				service.deleteContact(id);
			}
			Command updateCommand = new GetAllContactsCommand(service, request);
			updateCommand.execute();
			resultPage = updateCommand.getResultPage();
			
		} catch (ContactDelitionFailedException e) {
			proccessError(e.getMessage());
		}
	}

	private void proccessError(String message) {
		LOGGER.error(message);
		request.setAttribute("errorMessage", ExceptionMessages.CONTACT_DELITION_FAILED);
		resultPage = Pages.ERROR_PAGE;
	}

	@Override
	public String getResultPage() {
		return resultPage;
	}

}
