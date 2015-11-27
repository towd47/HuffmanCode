import java.util.Iterator;

public class HuffmanCode {

	public HuffmanList huffList;
	private boolean[] path;

	public HuffmanCode(byte[] byteArray) {
		huffList = new HuffmanList(byteArray);
		buildTree();
	}

	public HuffmanCode(String filePath) {
		huffList = new HuffmanList(filePath);
		buildTree();
	}

	public HuffmanCode(byte[] byteArray, int[] counts) {
		huffList = new HuffmanList(byteArray, counts);
		buildTree();
	}

	public void buildTree() {
		while (huffList.list.size() > 2) {
			Iterator<HuffmanNode> iter = huffList.list.iterator();
			iter.next();
			HuffmanNode currentLow = iter.next();
			HuffmanNode nextLowest = iter.next();
			HuffmanNode temp;
			if (currentLow.count > nextLowest.count || (currentLow.count == nextLowest.count && currentLow.b > nextLowest.b)) {
				HuffmanNode temp2 = currentLow;
				currentLow = nextLowest;
				nextLowest = temp2;
			}
			while (iter.hasNext()) {
				temp = iter.next();
				if (temp.count <= currentLow.count) {
					if (temp.count < currentLow.count || temp.b < currentLow.b) {
						nextLowest = currentLow;
						currentLow = temp;
					}
				}
				else if (temp.count <= nextLowest.count) {
					if (temp.count < nextLowest.count || temp.b < nextLowest.b) {
						nextLowest = temp;
					}
				}
			}
			HuffmanNode newNode = new HuffmanNode(currentLow.count + nextLowest.count);
			newNode.left = currentLow;
			newNode.right = nextLowest;
			huffList.list.remove(currentLow);
			huffList.list.remove(nextLowest);
			huffList.list.getLast().next = newNode;
			huffList.list.add(newNode);

			temp = huffList.list.getFirst();
			while (temp.next != null) {
				temp = temp.next;
				if (temp.next == currentLow || temp.next == nextLowest) {
					temp.next = temp.next.next;
				}
			}
		}
	}

	public boolean[] code(byte b) {
		path = null;
		HuffmanNode current = huffList.list.getFirst().next;
		boolean isInTree = search(b, current, 0);
		if (isInTree) {
			return path;
		}
		throw new IllegalArgumentException("b not in tree");
	}

	public boolean[][] code(byte[] b) {
		boolean[][] codes = new boolean[b.length][1];
		for (int i = 0; i < b.length; i++) {
			codes[i] = code(b[i]);
		}
		return codes;
	}

	public boolean search(byte b, HuffmanNode root, int rootHeight) {
		if (root.left != null) {
			if (root.left.b == b) {
				path = new boolean[rootHeight+1];
				path[rootHeight] = false;
				return true;
			}
			if (search(b, root.left, rootHeight +1)) {
				path[rootHeight] = false;
				return true;
			}
		}
		if (root.right != null) {
			if (root.right.b == b) {
				path = new boolean[rootHeight+1];
				path[rootHeight] = true;
				return true;
			}
			if (search(b, root.right, rootHeight +1)) {
				path[rootHeight] = true;
				return true;
			}
		}
		return false;
	}

	public String codeString(byte b) {
		boolean[] bPath = code(b);
		String sPath = "";
		for (int i = 0; i < bPath.length; i++) {
			if (bPath[i]) {
				sPath = sPath + 1;
			}
			else {
				sPath = sPath + 0;
			}
		}
		return sPath;
	}

	public String toString() {
		huffList.byteCount.setOrder("countDec");
		byte[] bytes = huffList.byteCount.getElements();
		String encodings = "";
		for (int i = 0; i < bytes.length; i++) {
			encodings = encodings + bytes[i] + ": " + codeString(bytes[i]);
			if (i != bytes.length - 1) {
				encodings = encodings + "\n";
			}
		}

		return encodings;
	}
}
