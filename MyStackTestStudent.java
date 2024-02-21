import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class MyStackTestStudent {
	public MyStack<String> stringS;
	public String a="a", b="b", c="c", d="d", e="e", f="f";
	public ArrayList<String> fill = new ArrayList<String>();
	
	public MyStack<Double> doubleS;
	
	@BeforeEach
	public void setUp() throws Exception {
		stringS = new MyStack<String>(5);
		stringS.push(a);
		stringS.push(b);
		stringS.push(c);
		
        doubleS = new MyStack<Double>(5);
        doubleS.push(2.5);
        doubleS.push(2.3);
        doubleS.push(3.4);
        }

	@AfterEach
	public void tearDown() throws Exception {
		stringS = null;
		doubleS = null;
	}

	@Test
	public void testIsEmpty() throws StackUnderflowException {
		assertEquals(false,stringS.isEmpty());
		stringS.pop();
		stringS.pop();
		stringS.pop();
		assertEquals(true, stringS.isEmpty());
	}

	@Test
	public void testIsFull() throws StackOverflowException {
		assertEquals(false, stringS.isFull());
		stringS.push(d);
		stringS.push(e);
		assertEquals(true, stringS.isFull());
	}

	@Test
	public void testPop() {
		try {
			assertEquals(c, stringS.pop());
			assertEquals(b, stringS.pop());
			assertEquals(a, stringS.pop());
			//Queue is empty, next statement should cause QueueUnderFlowException
			stringS.pop();
			assertTrue(false, "This should have caused an StackUnderflowException");
		}
		catch (StackUnderflowException e){
			assertTrue(true, "This should have caused an StackUnderflowException");
		}
		catch (Exception e){
			assertTrue(false, "This should have caused an StackUnderflowException");
		}
	}

	@Test
	public void testPopStudent() {
		   try {
		        assertEquals(3.4, doubleS.pop());
		        assertEquals(2.3, doubleS.pop());
		        assertEquals(2.5, doubleS.pop());
		        doubleS.pop();
		        fail("Expected a StackUnderflowException to be thrown");
		    } catch (StackUnderflowException e) {
		        assertTrue(true, "Correctly caught StackUnderflowException");
		    } catch (Exception e) {
		        fail("Expected StackUnderflowException but caught a different exception");
		    }
	}

	@Test
	public void testTop() throws StackUnderflowException, StackOverflowException {
		assertEquals(c, stringS.top());
		stringS.push(d);
		assertEquals(d, stringS.top());
		stringS.pop();
		stringS.pop();
		assertEquals(b, stringS.top());		
	}

	@Test
	public void testSize() throws StackOverflowException, StackUnderflowException {
		assertEquals(3, stringS.size());
		stringS.push(d);
		assertEquals(4, stringS.size());
		stringS.pop();
		stringS.pop();
		assertEquals(2, stringS.size());
	}

	@Test
	public void testPush() {
		try {
			assertEquals(3, stringS.size());
			assertEquals(true, stringS.push(d));
			assertEquals(4, stringS.size());
			assertEquals(true, stringS.push(e));
			assertEquals(5, stringS.size());
			//Queue is full, next statement should cause QueueOverFlowException
			stringS.push(f);
			assertTrue(false, "This should have caused an StackOverflowException");
		}
		catch (StackOverflowException e){
			assertTrue(true, "This should have caused an StackOverflowException");
		}
		catch (Exception e){
			assertTrue(false, "This should have caused an StackOverflowException");
		}
	}

	@Test
	public void testPushStudent() {
	    try {
	        // Initially, doubleS already has 3 items from setUp.
	        assertEquals(3, doubleS.size(), "Initial size should be 3.");
	        
	        // Push two more items, expecting success.
	        assertTrue(doubleS.push(4.5), "Push should return true when successfully added.");
	        assertTrue(doubleS.push(5.6), "Push should return true when successfully added.");
	        
	        // At this point, the stack should be full.
	        assertEquals(5, doubleS.size(), "Stack should be full now.");
	        assertTrue(doubleS.isFull(), "Stack should be reported as full.");

	        // Attempting to push another item should result in a StackOverflowException.
	        doubleS.push(6.7);
	        fail("Expected a StackOverflowException to be thrown because the stack is full.");
	    } catch (StackOverflowException e) {
	        // This is expected.
	        assertTrue(true, "Correctly caught StackOverflowException when trying to push onto a full stack.");
	    } catch (Exception e) {
	        fail("Expected StackOverflowException but caught a different exception.");
	    }
	}
	
	@Test
	public void testToString() throws StackOverflowException {
		assertEquals("abc", stringS.toString());
		stringS.push(d);
		assertEquals("abcd", stringS.toString());
		stringS.push(e);
		assertEquals("abcde", stringS.toString());
	}

	@Test
	public void testToStringStudent() {
		
	    String expectedWODelimiter = "2.52.33.4";
	    assertEquals(expectedWODelimiter, doubleS.toString(), "");

	    String expectedWithDelimiter = "2.5,2.3,3.4";
	    assertEquals(expectedWithDelimiter, doubleS.toString(","), "");

	    String expectedWith_Delimiter = "2.5 2.3 3.4";
	    assertEquals(expectedWith_Delimiter, doubleS.toString(" "), "");
	}
	
	@Test
	public void testToStringDelimiter() throws StackOverflowException {
		assertEquals("a%b%c", stringS.toString("%"));
		stringS.push(d);
		assertEquals("a&b&c&d", stringS.toString("&"));
		stringS.push(e);
		assertEquals("a/b/c/d/e", stringS.toString("/"));
	}

	@Test
	public void testFill() throws StackOverflowException, StackUnderflowException {
		fill.add("apple");
		fill.add("banana");
		fill.add("carrot");
		//start with an empty queue
		stringS = new MyStack<String>(5);
		//fill with an ArrayList
		stringS.fill(fill);
		assertEquals(3,stringS.size());
		assertEquals("carrot", stringS.pop());
		assertEquals("banana", stringS.pop());
		assertEquals("apple", stringS.pop());		
	}

}
