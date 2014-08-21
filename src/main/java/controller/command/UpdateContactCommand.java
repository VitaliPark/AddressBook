package controller.command;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import model.ContactRequestParser;
import model.DefaultParameterContainer;
import model.ParameterContainer;
import model.entity.Contact;
import model.entity.Phone;
import model.service.ContactService;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import constants.StringConstants;
import constants.database.PersonColumnNames;
import exceptioin.ContactCreationFailedException;
import exceptioin.ContactValidationException;

public class UpdateContactCommand implements Command{

	private ContactService contactService;
	private HttpServletRequest request;
	private ServletFileUpload servletFileUpload;
	private String repositoryPath;
	private String uploadPath;
	
	public UpdateContactCommand(HttpServletRequest request, ContactService service) {
		super();
		this.request = request;
		this.contactService = service;
	}

	@Override
	public void execute() {
		if (ServletFileUpload.isMultipartContent(request)) {
			System.out.println("MultipartRequest");
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
		repositoryPath = context.getInitParameter("tempFolder");
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
		servletFileUpload = new ServletFileUpload(factory);
		uploadPath = context.getInitParameter("uploadPath");
		File file = new File(uploadPath);
		if(!file.exists()){
			file.mkdir();
		}
	}
	
	private void processCreateContact(HttpServletRequest request){
		ContactRequestParser parser = new ContactRequestParser();
		try {
			List<FileItem> items = servletFileUpload.parseRequest(request);
			List<FileItem> files = getFiles(items);
			ParameterContainer parameterContainer = new DefaultParameterContainer(items);
			Contact contact = parser.parseContactRequest(parameterContainer);
			storeFiles(files);
			contactService.createContact(contact);
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (ContactValidationException e) {
			e.printStackTrace();
		} catch (ContactCreationFailedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void processUpdateContact(HttpServletRequest request){
		ContactRequestParser parser = new ContactRequestParser();
		int contactId = Integer.parseInt(request.getParameter(PersonColumnNames.idPerson));
		try {
			List<FileItem> items = servletFileUpload.parseRequest(request);
			List<FileItem> files = getFiles(items);
			ParameterContainer parameterContainer = new DefaultParameterContainer(items);
			Contact contact = parser.parseContactRequest(parameterContainer);
			contact.getPerson().setIdPerson(contactId);
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (ContactValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
			if(StringConstants.IMAGE_FIELD.equals(file.getFieldName())){
				storeContactImage(file);
			} else {
				storeAttachment(file);
			}
		}
	}
	
	private void storeAttachment(FileItem attachment) throws Exception{
		String filePath = generateAttahmentPath(attachment);
		File destination = new File(filePath);
		attachment.write(destination);
	}
	
	private void storeContactImage(FileItem imageFile) throws Exception{
		
		String imageFilePath = generateImageFilePath(imageFile);
		File destination = new File(imageFilePath);
		imageFile.write(destination);
	}
	
	private String generateAttahmentPath(FileItem attachment){
		StringBuilder builder = new StringBuilder();
		builder.append(uploadPath).append("/").append(StringConstants.ATTACHMENT_FOLDER);
		checkIfExists(builder.toString());
		builder.append("/").append(attachment.getFieldName()).append(attachment.getName());
		return builder.toString();
	}
	private void checkIfExists(String filePath){
		File file = new File(filePath);
		if(!file.exists()){
			file.mkdir();
		}
	}
	
	private String generateImageFilePath(FileItem imageFile){
		StringBuilder builder = new StringBuilder();
		builder.append(uploadPath).append("/").append(StringConstants.IMAGE_FOLDER);
		checkIfExists(builder.toString());
		builder.append("/").append(imageFile.getFieldName()).append(imageFile.getName());
		return builder.toString();
	}

	@Override
	public String getResultPage() {
		// TODO Auto-generated method stub
		return null;
	}

}
