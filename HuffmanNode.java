
public class HuffmanNode {
	
	public byte b;
	public int count;
	public boolean[] code;
	public HuffmanNode next;
	public HuffmanNode left;
	public HuffmanNode right;
	
	public HuffmanNode(byte b, int c) {
		this.b = b;
		count = c;
		right = null;
		next = null;
		left = null;
	}
	public HuffmanNode(int c) {
		count = c;
		right = null;
		next = null;
		left = null;
	}
}
