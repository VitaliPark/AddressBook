package model.service;

import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

public class MailService {
	
	private final String username = "mailprojecttest@gmail.com";
	private final String password = "google12345678";
	
	public void sendMail(String[] recipients, String subject, String stringMessage) throws AddressException, MessagingException{
		System.out.println(stringMessage);
		Properties props = initProperties();
		Session session = initSession(props);
		MimeMessage message = prepareMessage(recipients, subject,
				stringMessage, session);
        Transport.send(message);
	}

	private MimeMessage prepareMessage(String[] recipients, String subject,
			String stringMessage, Session session) throws MessagingException,
			AddressException {
		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress(username));
		InternetAddress[] addressTo = new InternetAddress[recipients.length];
        for (int i = 0; i < recipients.length; i++){
            addressTo[i] = new InternetAddress(recipients[i]);
            System.out.println(recipients[i]);
        }
        message.setRecipients(RecipientType.TO, addressTo); 
        message.setSubject(subject);
        message.setText(stringMessage);
		return message;
	}

	private Session initSession(Properties props) {
		Session session = Session.getInstance(props,
				  new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				  });
		return session;
	}

	private Properties initProperties() {
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		return props;
	}
}
