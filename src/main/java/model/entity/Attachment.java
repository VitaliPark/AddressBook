package model.entity;

import java.util.Date;

public class Attachment {
	
	private int idAttachment;
	private String fileName;
	private Date uploadDate;
	private String comment;
	
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
	
}
