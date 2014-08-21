package model.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import constants.Status;
import constants.StringConstants;

public class Attachment {
	
	private int idAttachment;
	private String fileName;
	private String localFileName;
	private Date uploadDate;
	private String comment;
	
	private Status status;
	
	public int getIdAttachment() {
		return idAttachment;
	}
	public void setIdAttachment(int idAttachment) {
		this.idAttachment = idAttachment;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public Date getUploadDate() {
		return uploadDate;
	}
	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public String getUploadDateAsString(){
		SimpleDateFormat dateFormat = new SimpleDateFormat(StringConstants.DEFAULT_DATE_FORMAT);
		return (uploadDate != null) ? dateFormat.format(uploadDate) : "";
	}
	
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public String getLocalFileName() {
		return localFileName;
	}
	public void setLocalFileName(String localFileName) {
		this.localFileName = localFileName;
	}
	
}
