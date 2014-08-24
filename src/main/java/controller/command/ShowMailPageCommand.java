package controller.command;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import constants.ExceptionMessages;
import constants.Pages;
import constants.StringConstants;
import exceptioin.EmailReadFailedException;
import model.service.ContactService;

public class ShowMailPageCommand implements Command{

	private String resultPage;
	private HttpServletRequest request;
	private ContactService contactService;
	private Logger LOGGER = Logger.getLogger(ShowMailPageCommand.class);
	
	public ShowMailPageCommand(HttpServletRequest request, HttpServletResponse response,
			ContactService contactService) {
		this.request = request;
		this.contactService = contactService;
	}

	@Override
	public void execute(){
		showMailPage();
	}

	private void showMailPage() {
		LOGGER.info("Command requested 'ShowMailCommand'");
		String [] contactId = request.getParameterValues("contactId");
		List<String> emails = new ArrayList<>();
		int id;
		String email;
		try {
			for(int i = 0; i < contactId.length; i++){
				id = Integer.parseInt(contactId[i]);
				email = contactService.getContactEmail(id);
				emails.add(email);
			}
			setRequestData(emails);
		} catch (EmailReadFailedException e) {
			proccessError(e.getMessage());
		}
	}
	
	private void proccessError(String message){
		resultPage = Pages.ERROR_PAGE;
		request.setAttribute(StringConstants.ERROR_MESSAGE, ExceptionMessages.EMAIL_READ_FAILED);
		LOGGER.error(message);
	}
	
	private String listToString(List<String> emails){
		StringBuilder builder = new StringBuilder();
		for(String str : emails){
			if(str != null && !str.isEmpty()){
				builder.append(str); builder.append(";");
			}
		}
		return builder.toString();
	}
	
	private void setRequestData(List<String> emails){
		String emailsString = listToString(emails);
		request.setAttribute("emails", emailsString);
		resultPage = Pages.EMAIL_PAGE;
	}

	@Override
	public String getResultPage() {
		return resultPage;
	}

}
