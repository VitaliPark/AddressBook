package exceptioin;

@SuppressWarnings("serial")
public class ContactReadFailedException extends Exception{

	public ContactReadFailedException() {
		super();
	}

	public ContactReadFailedException(String message) {
		super(message);
	}

}
