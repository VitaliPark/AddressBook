package controller.command;

import constants.Pages;

public class ShowSearchPageCommand implements Command{

	private String resultString;
	
	
	
	public ShowSearchPageCommand() {
		resultString = Pages.SEARCH_PAGE;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getResultPage() {
		return resultString;
	}

}
