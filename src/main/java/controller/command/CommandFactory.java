package controller.command;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.service.ContactService;
import constants.CommandTypes;
import constants.StringConstants;

public class CommandFactory {

	private ContactService contactService;
	
	public CommandFactory() {
		contactService = new ContactService();
		
	}

	public Command getCommand(HttpServletRequest request, HttpServletResponse response){
		String command = request.getParameter(StringConstants.command);
		CommandTypes type = CommandTypes.getType(command);
		switch(type){
			case GET_ALL_CONTACTS: 	return new GetAllContactsCommand(contactService, request);
			case DELETE_CONTACTS: 	return new DeleteContactsCommand(contactService, request);
			case EDIT_CONTACT: 		return new EditContactCommand(contactService, request);
			case CREATE_CONTACT:	return new EditContactCommand(contactService, request);
			case SEARCH_CONTACTS: 	return new SearchCommand(request, contactService);
			case SHOW_SEARCH_PAGE : return new ShowSearchPageCommand();
			default: 				return new ErrorCommand();
		}
	}
}
