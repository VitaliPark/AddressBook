package controller.command;

import javax.servlet.http.HttpServletRequest;

import exceptioin.ContactDelitionFailedException;
import model.service.ContactService;

public class DeleteContactsCommand implements Command{

	private HttpServletRequest request;
	private ContactService service;
	private String resultPage;
	
	public DeleteContactsCommand(ContactService service, HttpServletRequest request) {
		this.request = request;
		this.service = service;
	}

	@Override
	public void execute() {
		String [] contactId = request.getParameterValues("contactId");
		
		try {
			for(int i = 0; i < contactId.length; ++i){
				int id = Integer.parseInt(contactId[i]);
				service.deleteContact(id);
			}
			Command updateCommand = new GetAllContactsCommand(service, request);
			updateCommand.execute();
			resultPage = updateCommand.getResultPage();
			
		} catch (ContactDelitionFailedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public String getResultPage() {
		return resultPage;
	}

}
