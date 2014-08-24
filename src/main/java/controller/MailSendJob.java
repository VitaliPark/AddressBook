package controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;

import model.entity.Person;
import model.service.ContactService;
import model.service.MailService;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import constants.StringConstants;
import exceptioin.ContactReadFailedException;

public class MailSendJob implements Job{
	
	private ContactService contactService;
	private MailService mailService;
	Logger LOGGER = Logger.getLogger(MailSendJob.class);

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		setContactService(context);
		setMailService(context);
		try {
			List<Person> contacts = contactService.getContactsByDate(getCurrentDate());
			if(!contacts.isEmpty()){
				String message = parseContacts(contacts);
				String[] to = {"mailprojecttest@gmail.com"};
				String subject = "День рождения";
				mailService.sendMail(to, subject, message);
			}
		} catch (ContactReadFailedException | MessagingException e) {
			LOGGER.error(e.getMessage());
			throw new JobExecutionException(e.getMessage());
		}
	}
	
	private String parseContacts(List<Person> list){
		StringBuilder builder = new StringBuilder();
		builder.append("Не забудь поздравить с днём рождения:"); builder.append("\n");
		for(Person person : list){
			SimpleDateFormat dateFormat = new SimpleDateFormat(StringConstants.DEFAULT_DATE_FORMAT);
			String date = dateFormat.format(person.getDateOfBirth());
			builder.append(person.getSecondName()); builder.append(" ");
			builder.append(person.getFirstName()); builder.append(" ");
			builder.append(date); builder.append(" ");
			builder.append(person.getEmail());
			builder.append("\n");
		}
		return builder.toString();
	}

	private void setContactService(JobExecutionContext context) {
		JobDataMap dataMap = context.getJobDetail().getJobDataMap();
		contactService = (ContactService) dataMap.get("contactService");
	}
	
	private void setMailService(JobExecutionContext context){
		JobDataMap dataMap = context.getJobDetail().getJobDataMap();
		mailService = (MailService) dataMap.get("mailService");
	}
	
	private Date getCurrentDate(){
		Date date = new Date();
		return date;
	}
	
}
