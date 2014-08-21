package exceptioin;

import model.ValidationObject;

@SuppressWarnings("serial")
public class ContactValidationException extends Exception{

	private ValidationObject validationObject;
	
	public ContactValidationException(String message, ValidationObject validationObject) {
		super(message);
		this.validationObject = validationObject;
	}

	public ValidationObject getValidationObject() {
		return validationObject;
	}

	public void setValidationObject(ValidationObject validationObject) {
		this.validationObject = validationObject;
	}
	
}
