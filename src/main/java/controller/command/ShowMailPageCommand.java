package controller.command;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import constants.Pages;
import exceptioin.EmailReadFailedException;
import model.service.ContactService;

public class ShowMailPageCommand implements Command{

	private String resultPage;
	private HttpServletRequest request;
	private ContactService contactService;
	
	public ShowMailPageCommand(HttpServletRequest request,
			ContactService contactService) {
		this.request = request;
		this.contactService = contactService;
	}

	@Override
	public void execute() {
		String [] contactId = request.getParameterValues("contactId");
		List<String> emails = new ArrayList<>();
		int id;
		String email;
		System.out.println("SHOW Mail");
		try {
			for(int i = 0; i < contactId.length; i++){
				id = Integer.parseInt(contactId[i]);
				email = contactService.getContactEmail(id);
				emails.add(email);
			}
			setRequestData(emails);
		} catch (EmailReadFailedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
