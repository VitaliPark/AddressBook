package model.entity;

import constants.Status;

public class Image {

	private String fileName;
	private String localFileName;
	private int imageId;
	
	private Status status;

	public int getImageId() {
		return imageId;
	}

	public void setImageId(int imageId) {
		this.imageId = imageId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getLocalFileName() {
		return localFileName;
	}

	public void setLocalFileName(String localFileName) {
		this.localFileName = localFileName;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
}
