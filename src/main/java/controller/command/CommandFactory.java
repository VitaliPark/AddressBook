package controller.command;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.service.ContactService;
import model.service.MailService;
import constants.CommandTypes;
import constants.StringConstants;

public class CommandFactory {

	private ContactService contactService;
	private MailService mailService;
	
	public CommandFactory() {
		contactService = new ContactService();
		mailService = new MailService();
	}

	public Command getCommand(HttpServletRequest request, HttpServletResponse response){		
		String command = request.getParameter(StringConstants.COMMAND);
		CommandTypes type = CommandTypes.getType(command);
		switch(type){
			case GET_ALL_CONTACTS: 	return new GetAllContactsCommand(contactService, request);
			case DELETE_CONTACTS: 	return new DeleteContactsCommand(contactService, request);
			case UPDATE_CONTACT:    return new UpdateContactCommand(request, contactService);
			case EDIT_CONTACT: 		return new ShowContactPageCommand(contactService, request);
			case CREATE_CONTACT:	return new ShowContactPageCommand(contactService, request);
			case SEARCH_CONTACTS: 	return new SearchCommand(request, contactService);
			case SHOW_SEARCH_PAGE:  return new ShowSearchPageCommand();
			case SHOW_MAIL_PAGE: 	return new ShowMailPageCommand(request, contactService);	
			case SEND_MAIL: 		return new SendMailCommand(request, mailService, contactService);
			case SCHEDULE_MAIL_SEND:return new SheduleMailCommand(mailService, contactService);
			default: 				return new ErrorCommand();
		}
	}
	
	public Command getCommand(CommandTypes type){
		switch(type){
			case SCHEDULE_MAIL_SEND:return new SheduleMailCommand(mailService, contactService);
			default: 				return new ErrorCommand();
		}
	}
	
	
	

}
