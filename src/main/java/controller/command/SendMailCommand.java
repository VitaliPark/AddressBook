package controller.command;

import java.util.Arrays;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import constants.Pages;
import model.service.ContactService;
import model.service.MailService;


public class SendMailCommand implements Command{

	private String resultPage;
	private HttpServletRequest request;
	private MailService mailService;
	private ContactService contactService;
	private Logger LOGGER = Logger.getLogger(SendMailCommand.class);
	
	public SendMailCommand(HttpServletRequest request,
			MailService mailService, ContactService contactService) {
		this.request = request;
		this.mailService = mailService;
		this.contactService = contactService;
	}

	@Override
	public void execute() {
		LOGGER.info("Requested command 'SendEmail'");
		String[] recipients = parserToInput();
		LOGGER.debug("Recipients: " + Arrays.toString(recipients));
		String subject = request.getParameter("mailSubject");
		String messageText = request.getParameter("mailMessage");
		try {
			mailService.sendMail(recipients, subject, messageText);
			proccesSuccess();
		} catch (MessagingException e) {
			proccesError(e.getMessage());
		}
	}
	private void proccesSuccess(){
		LOGGER.debug("Mail sent successfull");
		Command getContactsCommand = new GetAllContactsCommand(contactService, request);
		getContactsCommand.execute();
		request.setAttribute("result", "Сообщение отправено");
		resultPage = getContactsCommand.getResultPage();
	}
	
	private void proccesError(String message){
		LOGGER.error(message);
		resultPage = Pages.ERROR_PAGE;
		request.setAttribute("errorMessage", "Отправка сообщения не удалась: " + message);
	}

	@Override
	public String getResultPage() {
		return resultPage;
	}
	
	private String[] parserToInput(){
		String emailsString = request.getParameter("mailTo");
		String [] recipients = emailsString.split(";");
		return recipients;
	}
	
	

}
