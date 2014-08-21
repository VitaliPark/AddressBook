package controller;
import org.apache.log4j.Logger;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import constants.CommandTypes;
import controller.command.Command;
import controller.command.CommandFactory;



@SuppressWarnings("serial")
public class FrontController extends HttpServlet {

	public static final Logger log = Logger.getLogger(FrontController.class);	
	private CommandFactory factory;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        factory = new CommandFactory();
        //scheduleTask();
    }
    
    private void scheduleTask(){
    	Command scheduleTaskCommand = factory.getCommand(CommandTypes.SCHEDULE_MAIL_SEND);
    	scheduleTaskCommand.execute();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		proccessRequest(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		proccessRequest(request, response);
	}
	
	private void proccessRequest(HttpServletRequest request, HttpServletResponse response){		
		Command command = factory.getCommand(request, response);
		command.execute();
		String page = command.getResultPage();
		dispatch(request, response, page);
	}

	private void dispatch(HttpServletRequest request,
			HttpServletResponse response, String page) {
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		try {
			dispatcher.forward(request, response);			
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
