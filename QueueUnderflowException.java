/**
 * Error for when queue is empty.
 * 
 */
public class QueueUnderflowException extends Exception {
	
	public QueueUnderflowException(String message) {
		super(message);
	}

}
