package controller.command;

import javax.servlet.http.HttpServletRequest;

import constants.Pages;
import constants.StringConstants;

public class ErrorCommand implements Command{

	private String resultPage;
	private HttpServletRequest request;
	
	
	public ErrorCommand(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public void execute() {
		resultPage = Pages.ERROR_PAGE;
		request.setAttribute(StringConstants.ERROR_MESSAGE, "Page not found");
	}

	@Override
	public String getResultPage() {
		return resultPage;
	}

}
