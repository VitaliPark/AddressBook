package controller.command;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.service.ContactService;
import constants.CommandTypes;
import constants.StringConstants;

public class CommandFactory {

	private ContactService service;
	
	public CommandFactory() {
		service = new ContactService();
	}

	public Command getCommand(HttpServletRequest request, HttpServletResponse response){
		String command = request.getParameter(StringConstants.command);
//		if(command == null){
//			return new ErrorCommand();
//		} else if(command.equals("getAllContacts")) {
//			return new GetAllContactsCommand(service, request, response);
//		} else return new ErrorCommand();
		CommandTypes type = CommandTypes.getType(command);
		switch(type){
			case GET_ALL_CONTACTS: return new GetAllContactsCommand(service, request, response);
			default: return new ErrorCommand();
		}
	}
}
