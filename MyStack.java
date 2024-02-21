import java.util.ArrayList;

/**
 * A generic stack implementation.
 * The stack uses an array to hold elements.
 * 
 * @param <T> Generic data type for the stack.
 */


public class MyStack <T> implements StackInterface<T> {

	private T[] stack;
	private int top;
	private final static int DEFAULT_SIZE = 40;
	
	
	@SuppressWarnings("unchecked")
	public MyStack() {
		
	
        stack = (T[]) new Object[DEFAULT_SIZE];
        top =-1;
		
	}
	
    /**
     * Constructs a stack with a specified size for the array
     * 
     * @param size -- capacity of the stack
     */
	
	@SuppressWarnings("unchecked")
	public MyStack(int size) {
		
		top =-1;
        stack = (T[]) new Object[size];

		
	}
	
    /**
     * Checks if stack is empty.
     * @return true or false
     */
	
	@Override
	public boolean isEmpty() {
		if(top == -1) {
			return true;
		}
		return false;
	}

	/**
     * Checks if stack is full
     * @return true or false
     */
	
	@Override
	public boolean isFull() {
		return top == stack.length -1;
	}

	/**
     * Pops top element from stack and returns it.
     * @return element -- generic data element type.
     * @throws StackUnderflowException -- if the stack is already empty.
     */
	
	@Override
	public T pop() throws StackUnderflowException {
		if(isEmpty()) {
			throw new StackUnderflowException("Stack is already empty");
		}
		T element = stack[top];
		top --;
		return element;
	}

	/**
     * Returns top element from stack.
     * @return stack[top] -- returns top element from stack
     * @throws StackUnderflowException -- if the stack is already empty.
     */
	
	@Override
	public T top() throws StackUnderflowException {
		if(isEmpty()) {
			throw new StackUnderflowException("Stack is empty");
		}
		
		return stack[top];
	}

	/**
     * Returns size of stack.
     * @return size -- Integer as size of stack.
     */
	
	@Override
	public int size() {
		return top+1;
	}
	

    /**
     * Pushes element onto top of stack
     * 
     * @param e -- element to be pushed
     * @return true -- if successful pushing
     * @throws StackOverflowException  -- if stack is already full.
     */
	
	@Override
	public boolean push(T e) throws StackOverflowException {
		if(isFull()) {
			throw new StackOverflowException("Stack is full");
		}
		top =top +1;
		stack[top] = e;
		return true;
		
	}
	
	 /**
     * Returns a string of stack
     * 
     * @return string representation of the stack.
     */

	@Override
	public String toString() {
	    
	    StringBuilder sb = new StringBuilder();
	    for (int i = 0; i <= top; i++) {
	        if (stack[i] != null) { 
	            sb.append(stack[i]);
	            }
	        }
	    
	    return sb.toString();
	}
	

    /**
     * Returns a string of stack, elements separated by any input delimiter
     * 
     * @param delimiter -- the separator for elements on stack.
     * @return string representation of the stack.
     */

	@Override
	public String toString(String delimiter) {
	    if (top == -1) {
	        return ""; 
	    }
	    
	    StringBuilder sb = new StringBuilder();
	    for (int i = 0; i <= top; i++) {
	        if (stack[i] != null) { 
	            sb.append(stack[i]);
	            if (i < top) {
	                sb.append(delimiter);
	            }
	        }
	    }
	    
	    return sb.toString();
	}
	
	
	  /**
     * Fills stack with elements of an ArrayList.
     * 
     * @param ArrayList -- containing what will be added to the stack.
     * @throws StackOverflowException -- not enough space to add elements from ArrayList to stack
     */
	
	@Override
	public void fill(ArrayList<T> list) throws StackOverflowException {
		for (T element : list) {
			push(element);
		}
	}

}
