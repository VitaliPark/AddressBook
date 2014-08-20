package controller.command;

import javax.servlet.http.HttpServletRequest;

public class UpdateContactCommand implements Command{

	private HttpServletRequest request;
	
	public UpdateContactCommand(HttpServletRequest request) {
		super();
		this.request = request;
	}

	@Override
	public void execute() {
		System.out.println(request.getParameter("idPerson"));
	}

	@Override
	public String getResultPage() {
		// TODO Auto-generated method stub
		return null;
	}

}
