package exceptioin;

@SuppressWarnings("serial")
public class EmailReadFailedException extends Exception{

	public EmailReadFailedException() {
		super();
	}

	public EmailReadFailedException(String message) {
		super(message);
	}
	

}
