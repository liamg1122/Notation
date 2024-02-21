import java.util.ArrayList;
 
/**
 * Class is a generic queue implementation using  circular array.
 *
 * @param <T> the type of elements in the queue
 */

public class MyQueue <T> implements QueueInterface <T> {

	T[] queue;
	int front, rear = 0;
	int count;
	ArrayList myArray = new ArrayList <>();
	int size;
	final int DEFAULT_SIZE = 40;
	
   /**
   * Initializes queue with specified capacity.
   *
   * @param size of capacity of the queue
   */
	
	@SuppressWarnings("unchecked")
	public MyQueue(int size) {
		
		this.size = size;
		queue = (T[])new Object[size];
		this.count = 0;
		
	}
	
    /**
     * Initializes queue with default capacity.
     */
	
	@SuppressWarnings("unchecked")
	public MyQueue() {
		
		this.size = DEFAULT_SIZE;
		queue = (T[])new Object[DEFAULT_SIZE];
		this.count = 0;
		
	}
	
    /**
     * Checks if the queue is empty.
     *
     * @return true if empty, false if not empty.
     */
	
	@Override
	public boolean isEmpty() {
		if(count ==0) {
			return true;
		}
		return false;
	}

    /**
     * Checks if queue is full.
     *
     * @return true if full, false if not full.
     */
	
	@Override
	public boolean isFull() {
		if(count >= queue.length) {
			return true;
		}
		return false;
	}

    /**
     * Deletes and returns the front element of the queue.
     *
     * @return dequeued element
     * @throws QueueUnderflowException if queue is empty
     */
	
	@Override
	public T dequeue() throws QueueUnderflowException {
		if(isEmpty()) {
			throw new QueueUnderflowException("Queue is empty.");
		}
		else {
		T element = queue[front];
		front = (front+1)% queue.length;
		count --;
		return element;
		}
		
	}

    /**
     * Returns the number of elements in the queue using count.
     *
     * @return queue size
     */
	
	@Override
	public int size() {
		return count;
	}


    /**
     * Adds an element to the rear of the queue.
     *
     * @param e -- element to add
     * @return true -- if successful
     * @throws QueueOverflowException -- if queue is full
     */
	
	@Override
	public boolean enqueue(T e) throws QueueOverflowException {
		if(isFull()) {
			throw new QueueOverflowException("Queue is full");
		}
		else {
			queue[rear]= e;
			rear = (rear+1)%queue.length;
			count++;
			return true;
		}
	}
	
    /**
     * String representation with delimiter separating each element.
     *
     * @param delimiter --  separates elements
     * @return string representation of queue w/ delimiter
     */

	@Override
	public String toString(String delimiter) {
	    int loopIndex = front;
	    StringBuilder sb = new StringBuilder();
	    for (int i = 0; i < count; i++) {
	        sb.append(queue[loopIndex]);
	        loopIndex = (loopIndex + 1) % queue.length;
	        if (i < count - 1 && delimiter != null && !delimiter.isEmpty()) {
	            sb.append(delimiter);
	        }
	    }
	    return sb.toString(); 
	}
	
	 /**
     * String representation of queue.
     *
     * @return string representation of queue
     */
	
	@Override
	public String toString() {
	    int loopIndex = front;
	    StringBuilder sb = new StringBuilder();
	    for (int i = 0; i < count; i++) {
	        sb.append(queue[loopIndex]);
	        loopIndex = (loopIndex + 1) % queue.length;
	       
	        }
	    return sb.toString(); 
	}

	 /**
     * Fills queue from an ArrayList.
     *
     * @param list -- elements to add
     * @throws QueueOverflowException -- if not enough space in queue.
     */
	
	@Override
	public void fill(ArrayList<T> list) throws QueueOverflowException {
		if(list.size()>(queue.length - count)) {
			throw new QueueOverflowException("List is too big for queue.");
		}
		for(T element: list) {
			enqueue(element);
		}
	}

}
