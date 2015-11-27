import static org.junit.Assert.*;
import java.io.IOException;
import org.junit.Test;

public class HuffmanCodeFundTest {

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
	public void testByteArrayArgumentConstructor() {
    	@SuppressWarnings("unused")
    	HuffmanCode hc = new HuffmanCode(new byte[] {(byte)'a', (byte)'b'});
    	assertTrue(
			"skeleton: The constructor should make a HuffmanCode using a byte array.",
			true);
	}

	@Test
	public void testStringArgumentConstructor() throws IOException {
    	@SuppressWarnings("unused")
		HuffmanCode hc = new HuffmanCode("file.txt");
    	assertTrue(
			"skeleton: The constructor should make a HuffmanCode from a file.",
			true);
	}

	@Test
	public void testByteAndCountArraysConstructor() {
    	@SuppressWarnings("unused")
    	HuffmanCode hc = new HuffmanCode(new byte[] {(byte)'a', (byte)'b'}, new int [] {2, 3});
    	assertTrue(
			"skeleton: The constructor should make a HuffmanCode using byte and count arrays.",
			true);
	}

	@Test
	public void testToStringMethod() {
    	HuffmanCode hc = new HuffmanCode("file.txt");
    	String s = hc.toString();
    	assertEquals(
			"This method returns a string containing the table of the binary encodings of each byte in the Huffman Code.",
			"97: 0\n98: 11\n99: 101\n10: 100", s);
	}
	
	@Test
	public void testCodeMethod() {
    	HuffmanCode hc = new HuffmanCode("file.txt");
    	String code = hc.codeString((byte)'a');
    	String compare = "0";
    	assertEquals("This method reurns the code of specific byte", compare, code);
	}
}