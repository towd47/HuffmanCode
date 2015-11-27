import java.util.Iterator;
import java.util.LinkedList;

public class HuffmanList {
	public LinkedList<HuffmanNode> list = new LinkedList<HuffmanNode>();
	public ByteCounter byteCount;
	
	public HuffmanList(byte[] byteArray) {
		HuffmanNode head = new HuffmanNode(0);
		HuffmanNode temp = head;
		list.add(head);
		byteCount = new ByteCounter(byteArray);
		byteCount.setOrder("countInc");
		byte[] orderedBytes = byteCount.getElements();
		int[] counts = byteCount.getCount(orderedBytes);
		for (int i = 0; i < orderedBytes.length; i++) {
			HuffmanNode node = new HuffmanNode(orderedBytes[i], counts[i]);
			temp.next = node;
			list.add(node);
			temp = node;
		}
	}
	
	public HuffmanList(String filePath) {
		HuffmanNode head = new HuffmanNode(0);
		HuffmanNode temp = head;
		list.add(head);
		byteCount = new ByteCounter(filePath);
		byteCount.setOrder("countInc");
		byte[] orderedBytes = byteCount.getElements();
		int[] counts = byteCount.getCount(orderedBytes);
		for (int i = 0; i < orderedBytes.length; i++) {
			HuffmanNode node = new HuffmanNode(orderedBytes[i], counts[i]);
			temp.next = node;
			list.add(node);
			temp = node;
		}
	}
	
	public HuffmanList(byte[] byteArray, int[] counts) {
		if (byteArray.length != counts.length) {
			throw new IllegalArgumentException("arrays must be same length");
		}
		for (int i = 0; i < byteArray.length; i++) {
			if (counts[i] < 1) {
				throw new IllegalArgumentException("Counts cannot be negative");
			}
			for (int j = i + 1; j < byteArray.length; j++) {
				if (byteArray[i] == byteArray[j]) {
					throw new IllegalArgumentException("ByteArray contains duplicates");
				}
			}
		}
		byteCount = new ByteCounter(byteArray);
		byteCount.counts = counts;
		HuffmanNode head = new HuffmanNode(0);
		HuffmanNode temp = head;
		list.add(head);
		byteCount.setOrder("countInc");
		byte[] orderedBytes = byteCount.getElements();
		int[] count = byteCount.getCount(orderedBytes);
		for (int i = 0; i < orderedBytes.length; i++) {
			HuffmanNode node = new HuffmanNode(orderedBytes[i], count[i]);
			temp.next = node;
			list.add(node);
			temp = node;
		}
	}

	public Iterator<HuffmanNode> iterator() {
		HuffmanIterator iter = new HuffmanIterator(list.getFirst());
		return iter;
	}

	public class HuffmanIterator implements Iterator<HuffmanNode> {
		private HuffmanNode current;
		
		public HuffmanIterator(HuffmanNode current) {
			this.current = current;
		}
		
		public boolean hasNext() {
			boolean hasNext = (current.next != null);
			return hasNext;
		}
		
		public HuffmanNode next() {
			if (!hasNext()) {
				return null;
			}
			else {
				current = current.next;
				return current;
			}
			
		}
		
	}
}
