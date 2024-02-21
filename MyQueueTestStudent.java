//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertTrue;
//import static org.junit.Assert.fail;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class MyQueueTestStudent {
	public MyQueue<String> stringQ;
	public String a="a", b="b", c="c", d="d", e="e", f="f";
	public ArrayList<String> fill = new ArrayList<String>();
	
	// STUDENT: student tests will use the doubleQ
	public MyQueue<Double> doubleQ;
	// STUDENT: add variables as needed for your student tests

	@BeforeEach
	public void setUp() throws Exception {
		stringQ = new MyQueue<String>(5);
		stringQ.enqueue(a);
		stringQ.enqueue(b);
		stringQ.enqueue(c);
		
		//STUDENT: add setup for doubleQ for student tests
		doubleQ = new MyQueue<Double>(5); // Initialize doubleQ with a capacity of 5
        doubleQ.enqueue(2.5);
        doubleQ.enqueue(2.5);
        doubleQ.enqueue(1.5);
	}

	@AfterEach
	public void tearDown() throws Exception {
		stringQ = null;
		doubleQ = null;
	}

	@Test
	public void testIsEmpty() throws QueueUnderflowException {
		assertEquals(false,stringQ.isEmpty());
		stringQ.dequeue();
		stringQ.dequeue();
		stringQ.dequeue();
		assertEquals(true, stringQ.isEmpty());
	}

	@Test
	public void testDequeue() {
		try {
			assertEquals(a, stringQ.dequeue());
			assertEquals(b, stringQ.dequeue());
			assertEquals(c, stringQ.dequeue());
			//Queue is empty, next statement should cause QueueUnderFlowException
			stringQ.dequeue();
			assertTrue(false, "This should have caused an QueueUnderflowException");
		}
		catch (QueueUnderflowException e){
			assertTrue(true, "This should have caused an QueueUnderflowException");
		}
		catch (Exception e){
			assertTrue(false, "This should have caused an QueueUnderflowException");
		}
	}
	
	@Test
	public void testDequeueStudent() {
		 try {
	            assertEquals(2.5, doubleQ.dequeue(), 0.001);
	            assertEquals(2.5, doubleQ.dequeue(), 0.001);
	            assertEquals(1.5, doubleQ.dequeue(), 0.001);
	            
	            doubleQ.dequeue();
	            fail("Should have thrown QueueUnderflowException");
	        } catch (QueueUnderflowException e) {
	            assertTrue(true, "QueueUnderflowException succeeded");
	        } catch (Exception e) {
	            fail("Expected QueueUnderflowException");
	        }		
	}

	@Test
	public void testSize() throws QueueOverflowException, QueueUnderflowException {
		assertEquals(3, stringQ.size());
		stringQ.enqueue(d);
		assertEquals(4, stringQ.size());
		stringQ.dequeue();
		stringQ.dequeue();
		assertEquals(2, stringQ.size());
	}

	@Test
	public void testEnqueue() {
		try {
			assertEquals(3, stringQ.size());
			assertEquals(true, stringQ.enqueue(d));
			assertEquals(4, stringQ.size());
			assertEquals(true, stringQ.enqueue(e));
			assertEquals(5, stringQ.size());
			//Queue is full, next statement should cause QueueOverFlowException
			stringQ.enqueue(f);
			assertTrue(false, "This should have caused an QueueOverflowException");
		}
		catch (QueueOverflowException e){
			assertTrue(true, "This should have caused an QueueOverflowException");
		}
		catch (Exception e){
			assertTrue(false, "This should have caused an QueueOverflowException");
		}
	}

	@Test
	public void testEnqueueStudent() {
		  try {
	            assertTrue(doubleQ.enqueue(3.9)); 
	            assertTrue(doubleQ.enqueue(4.0));
	            doubleQ.enqueue(5.4);
	            fail("Should have thrown a QueueOverflowException");
	        } catch (QueueOverflowException e) {
	            assertTrue(true, "QueueOverflowException thrown");
	        } catch (Exception e) {
	            fail("Expected QueueOverflowException");
	        }
	}

	@Test
	public void testIsFull() throws QueueOverflowException {
		assertEquals(false, stringQ.isFull());
		stringQ.enqueue(d);
		stringQ.enqueue(e);
		assertEquals(true, stringQ.isFull());
	}

	@Test
	public void testToString() throws QueueOverflowException {
		assertEquals("abc", stringQ.toString());
		stringQ.enqueue(d);
		assertEquals("abcd", stringQ.toString());
		stringQ.enqueue(e);
		assertEquals("abcde", stringQ.toString());
	}
	
	@Test
	public void testToStringStudent() {
		try {
			doubleQ.enqueue(3.5);
		} catch (QueueOverflowException e) {
			e.printStackTrace();
		} 
        assertEquals("2.52.51.53.5", doubleQ.toString(),"");
        assertEquals("2.5,2.5,1.5,3.5", doubleQ.toString(","), "");
    }
	

	@Test
	public void testToStringDelimiter() throws QueueOverflowException {
		assertEquals("a%b%c", stringQ.toString("%"));
		stringQ.enqueue(d);
		assertEquals("a&b&c&d", stringQ.toString("&"));
		stringQ.enqueue(e);
		assertEquals("a/b/c/d/e", stringQ.toString("/"));
	}

	@Test
	public void testFill() throws QueueOverflowException, QueueUnderflowException {
		fill.add("apple");
		fill.add("banana");
		fill.add("carrot");
		//start with an empty queue
		stringQ = new MyQueue<String>(5);
		//fill with an ArrayList
		stringQ.fill(fill);
		assertEquals(3,stringQ.size());
		assertEquals("apple", stringQ.dequeue());
		assertEquals("banana", stringQ.dequeue());
		assertEquals("carrot", stringQ.dequeue());		
	}

}
