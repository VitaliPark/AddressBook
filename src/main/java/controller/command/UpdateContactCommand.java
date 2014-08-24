package controller.command;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import model.entity.Contact;
import model.service.ContactService;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import validation.ValidationObject;
import constants.ExceptionMessages;
import constants.Pages;
import constants.StringConstants;
import constants.database.PersonColumnNames;
import controller.ContactRequestParser;
import controller.DefaultParameterContainer;
import controller.ParameterContainer;
import exceptioin.ContactCreationFailedException;
import exceptioin.ContactUpdateFailed;
import exceptioin.ContactValidationException;

public class UpdateContactCommand implements Command{

	private ContactService contactService;
	private HttpServletRequest request;
	private ServletFileUpload servletFileUpload;
	private String uploadPath;
	private String resultPage;
	
	private Logger LOGGER = Logger.getLogger(UpdateContactCommand.class);
	
	public UpdateContactCommand(HttpServletRequest request, ContactService service) {
		super();
		this.request = request;
		this.contactService = service;
	}

	@Override
	public void execute() {
		if (ServletFileUpload.isMultipartContent(request)) {
			processMultipartRequest(request);
		}
	}
	
	private void processMultipartRequest(HttpServletRequest request) {
		initFileUpload();
		String contactId = request.getParameter(PersonColumnNames.idPerson);
		if (contactId == null || contactId.isEmpty()) {
			processCreateContact(request);
		} else {
			processUpdateContact(request);
		}
	}
	
	private void initFileUpload() {
		ServletContext context = request.getSession().getServletContext();
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
		servletFileUpload = new ServletFileUpload(factory);
		uploadPath = context.getInitParameter("uploadPath");
		File file = new File(uploadPath);
		if(!file.exists()){
			file.mkdir();
		}
	}
	
	@SuppressWarnings("unchecked")
	private void processCreateContact(HttpServletRequest request){
		ContactRequestParser parser = new ContactRequestParser();
		try {
			List<FileItem> items = servletFileUpload.parseRequest(request);
			List<FileItem> files = getFiles(items);
			ParameterContainer parameterContainer = new DefaultParameterContainer(items);
			Contact contact = parser.parseContactRequest(parameterContainer);
			storeFiles(files);
			contactService.createContact(contact);
			proccesSuccessfullCreation("Контакт успешно создан");
		}  catch (ContactValidationException e) {
			proccessVaidationError(e.getValidationObject());
		} catch (ContactCreationFailedException e) {
			proccessError(ExceptionMessages.CONTACT_CREATION_FAILED);
		} catch (Exception e) {
			proccessFileError("Unable to store files");
		}
	}
	
	@SuppressWarnings("unchecked")
	private void processUpdateContact(HttpServletRequest request){
		ContactRequestParser parser = new ContactRequestParser();
		int contactId = Integer.parseInt(request.getParameter(PersonColumnNames.idPerson));
		try {
			List<FileItem> items = servletFileUpload.parseRequest(request);
			List<FileItem> files = getFiles(items);
			ParameterContainer parameterContainer = new DefaultParameterContainer(items);
			Contact contact = parser.parseContactRequest(parameterContainer);
			contact.getPerson().setIdPerson(contactId);
			storeFiles(files);
			contactService.updateContact(contact);
			proccesSuccessfullUpdate("Контакт успешно обновлён");
		} catch (ContactValidationException e) {
			proccessVaidationError(e.getValidationObject());
		} catch (ContactUpdateFailed e) {
			proccessError(ExceptionMessages.CONTACT_UPDATE_FAILED);
		} catch (Exception e) {
			proccessFileError("Unable to store files");
		}
	}
	
	private void proccesSuccessfullUpdate(String message){
		Command updateCommand = new ShowContactPageCommand(contactService, request);
		updateCommand.execute();
		request.setAttribute("result", message);
		resultPage = updateCommand.getResultPage();
	}
	
	private void proccesSuccessfullCreation(String message){
		Command updateCommand = new GetAllContactsCommand(contactService, request);
		updateCommand.execute();
		request.setAttribute("result", message);
		resultPage = updateCommand.getResultPage();
	}
	
	private void proccessError(String message){
		resultPage = Pages.ERROR_PAGE;
		request.setAttribute(StringConstants.ERROR_MESSAGE, message);
		LOGGER.error(message);
	}
	
	private void proccessVaidationError(ValidationObject result){
		LOGGER.error("Field validation failed");
		Command command = new ShowContactPageCommand(contactService, request);
		command.execute();
		request.setAttribute("result", "Validation failed, wrong fields: " + result.toString());
		resultPage = command.getResultPage();
	}
	
	private void proccessFileError(String message){
		LOGGER.error(message);
		Command command = new ShowContactPageCommand(contactService, request);
		command.execute();
		request.setAttribute("result", message);
		resultPage = command.getResultPage();
	}
	
	private List<FileItem> getFiles(List<FileItem> items){
		List<FileItem> files = new ArrayList<FileItem>();
		for (FileItem item : items) {
			if (!item.isFormField()) {
				files.add(item);
			}
		}
		return files;
	}
	
	private void storeFiles(List<FileItem> files) throws Exception{
		
		for(FileItem file : files){
			if(file.getFieldName().contains(StringConstants.IMAGE_FIELD)){
				storeContactImage(file);
			} else {
				storeAttachment(file);
			}
		}
	}
	
	private void storeAttachment(FileItem attachment) throws Exception{
		String filePath = generateAttahmentPath(attachment);
		if(filePath != null){
			File destination = new File(filePath);
			attachment.write(destination);
		}
	}
	
	private void storeContactImage(FileItem imageFile) throws Exception{
		
		String imageFilePath = generateImageFilePath(imageFile);
		if(imageFilePath != null){
			File destination = new File(imageFilePath);
			imageFile.write(destination);
		}
	}
	
	private String generateAttahmentPath(FileItem attachment){
		if(!attachment.getName().isEmpty()){
			StringBuilder builder = new StringBuilder();
			builder.append(uploadPath).append("/").append(StringConstants.ATTACHMENT_FOLDER);
			checkIfExists(builder.toString());
			builder.append("/").append(attachment.getFieldName()).append(attachment.getName());
			return builder.toString();
		}
		return null;
	}
	private void checkIfExists(String filePath){
		File file = new File(filePath);
		if(!file.exists()){
			file.mkdir();
		}
	}
	
	private String generateImageFilePath(FileItem imageFile){
		if(!imageFile.getName().isEmpty()){
			StringBuilder builder = new StringBuilder();
			builder.append(uploadPath).append("/").append(StringConstants.IMAGE_FOLDER);
			checkIfExists(builder.toString());
			builder.append("/").append(imageFile.getFieldName()).append(imageFile.getName());
			return builder.toString();
		}
		return null;
	}

	@Override
	public String getResultPage() {
		return resultPage;
	}

}
