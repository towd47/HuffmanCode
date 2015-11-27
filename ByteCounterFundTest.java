import static org.junit.Assert.*;
import java.io.IOException;
import org.junit.Test;

public class  ByteCounterFundTest {

    /* JUnit tests of fundamental functionality
     *
     * Use these JUnit tests to ensure that your code correctly
     * implements the fundamental functionality.
     */

    @Test
    public void testArrayArgumentConstructorAndToString() {
    	byte[] test = {(byte)'a', (byte)'a', (byte)'a', (byte)'b', (byte)'b', (byte)'c'};
    	ByteCounter byteCount = new ByteCounter(test);
        assertEquals(
            "A constructor with an byte array argument should store the unique bytes with their occurrences values. (This could also indicate a problem the the to String method.)",
            (byte)'a'+":3 "+ (byte)'b'+":2 "+(byte)'c'+":1", byteCount.toString());
    }

    @Test
    public void testStringArgumentConstructorAndGetCount()throws IOException {
		ByteCounter byteCount = new ByteCounter("file.txt");
    	byte b = (byte)'a';
        assertEquals(
            "A constructor with a String arguemnt should compute the byte counts from the specified file. (This could also indicate a problem the the to String method.)",
            4, byteCount.getCount(b));
    }

    @Test
    public void testByteArgumentGetCount() {
    	byte[] test = {(byte)'y', (byte)'y', (byte)'z'};
    	byte b = (byte)'y';
    	ByteCounter byteCount = new ByteCounter(test);
        assertEquals(
            "The getCount method should take a byte value and return the number of occurrences.",
            2, byteCount.getCount(b));
    }

    @Test
    public void testArrayArgumentGetCount() {
    	byte[] test = {(byte)'a', (byte)'a', (byte)'a', (byte)'b', (byte)'b', (byte)'c'};
    	byte[] b = {(byte)'c', (byte)'b', (byte)'a'};
    	ByteCounter byteCount = new ByteCounter(test);
    	int[] count = byteCount.getCount(b);
        assertArrayEquals(
            "The getCount method should take a byte array and return an array of the number of occurrences of each byte.",
            new int[] {1, 2, 3}, count);
    }

    @Test
    public void testGetElements() {
        byte[] test = {(byte)'a', (byte)'b', (byte)'c', (byte)'a', (byte)'b', (byte)'a'};
       	/*ByteCounter byteCount = new ByteCounter(test);
    	byte[] elements = byteCount.getElements();
        assertArrayEquals(
            "The getElements method should return an array of bytes that have a non-zero count.", new byte[] {(byte)'a', (byte)'b',
            (byte)'c'}, elements);
        assertEquals(
            "The getElements method should return an array of bytes that have a non-zero count", 3, elements.length);*/
        ByteCounter byteCount2 = new ByteCounter(test);
        byteCount2.setOrder("countDec");
        String byteString = byteCount2.toString("char");
        assertEquals("should return bytes in order of decreasing count",
        		byteString, "a:3 b:2 c:1");
    }

    /* Other JUnit tests.
     *
     * Write your own JUnit tests below to check the correctness of your implementation.
     * Leave the above methods intact and add your own tests below this comment so it's
     * easier for the graders to see what you've added.
     *
     * When you turn in your drafts we will run our own test suite on your code and provide
     * you with the feedback to help you debug an issues.
     *
     * Your draft code needs to contain a complete set of methods as specified in the assignment.
     * Otherwise, it won't compile with our test suite.  To avoid this, you should first write
     * a complete set of "skeleton" methods, i.e. methods with the correct names and
     * the correct return and argument types.
     *
     * To help ensure your code will compile when you submit it for testing, below we have
	 * included a set of skeleton tests. You should replace these with more meaningful tests
	 * as you write your methods.
     */

    @Test
	public void testSetOrder() {
    	byte[] test = {(byte)'c', (byte)'c', (byte)'b', (byte)'a', (byte)'a', (byte)'a'};
    	ByteCounter byteCount = new ByteCounter(test);
    	byteCount.setOrder("byte");
    	String byteString = byteCount.toString("char");
    	assertEquals(
            "setOrder byte should return bytes in order of byte value",
            byteString, "a:3 b:1 c:2");
    	
    	byteCount.setOrder("countInc");
    	byteString = byteCount.toString("char");
    	assertEquals(
            "setOrder countInc should return bytes in order of counts from low to high",
            byteString, "b:1 c:2 a:3");
    	
    	byteCount.setOrder("countDec");
    	byteString = byteCount.toString("char");
    	assertEquals(
            "setOrder countDec should return bytes in order of counts from high to low",
            "a:3 c:2 b:1", byteString);
	}

    @Test
	public void testFormatToString() {
    	byte[] test = {(byte)'a', (byte)'b'};
    	ByteCounter byteCount = new ByteCounter(test);
    	byteCount.toString("byte");
    	assertTrue(
            "skeleton: The toString method should return the bytes and their counts based on the format provided.",
			true);
	}
}