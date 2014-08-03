package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.command.Command;
import controller.command.CommandFactory;


@SuppressWarnings("serial")
public class FrontController extends HttpServlet {
	
	private CommandFactory factory;
	
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        factory = new CommandFactory();
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       proccessRequest(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		proccessRequest(request, response);
	}
	
	private void proccessRequest(HttpServletRequest request, HttpServletResponse response){
		Command command = factory.getCommand(request, response);
		System.out.println(command.getClass());
		command.execute();
		String page = command.getResultPage();
		dispatch(request, response, page);
	}

	private void dispatch(HttpServletRequest request,
			HttpServletResponse response, String page) {
		//RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
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
