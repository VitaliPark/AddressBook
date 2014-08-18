package controller.command;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.http.HttpServletRequest;

import model.service.MailService;


public class SendMailCommand implements Command{

	private String resultPage;
	private HttpServletRequest request;
	private MailService mailService;
	
	public SendMailCommand(HttpServletRequest request,
			MailService mailService) {
		this.request = request;
		this.mailService = mailService;
	}

	@Override
	public void execute() {
		String[] recipents = parserToInput();
		String subject = request.getParameter("mailSubject");
		String messageText = request.getParameter("mailMessage");
		System.out.println(subject);
		System.out.println(messageText);
		try {
			mailService.sendMail(recipents, subject, messageText);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getResultPage() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private String[] parserToInput(){
		String emailsString = request.getParameter("mailTo");
		String [] recipients = emailsString.split(";");
		return recipients;
	}
	
	

}
