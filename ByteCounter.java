	import java.io.IOException;
	import java.nio.file.Files;
	import java.nio.file.Paths;
	
	public class ByteCounter {
		String order = "byte";
		byte[] byteArray;
		int[] counts = null;
		byte[] fileBytes;
		
		public ByteCounter(byte[] byteArray) {
			this.byteArray = byteArray;
			fileBytes = byteArray;
		}
		
		public ByteCounter(String fileName) {
			try {
				this.byteArray = Files.readAllBytes(Paths.get(fileName));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			fileBytes = byteArray;
		}
		
		public int getCount(byte b) {
			int byteCount = 0;
			for (int i = 0; i < byteArray.length; i++) {
				if (b == byteArray[i]) {
					byteCount++;
				}
			}
			return byteCount;
		}
		
		public int[] getCount(byte[] b) {
			if (counts == null) {
				int[] byteCounts = new int[b.length]; 
				for (int i = 0; i < b.length; i++) {
					byteCounts[i] = getCount(b[i]);
				}
				return byteCounts;
			}
			else {
				return counts;
			}
			
		}
		
		public void setOrder(String order) {
			if (order == "byte" || order == "countInc" || order == "countDec") {
				this.order = order;
			}
			else {
				throw new IllegalArgumentException(order);
			}
			
		}
		
		public byte[] getElements() {
			if (order == "byte") {
				quickSort(byteArray, 0, byteArray.length - 1);
			}
			else if (order == "countInc" || order == "countDec"){
				int[] count = getCount(byteArray);
				quickSort(count, 0, count.length - 1);
				organizeSameCounts(byteArray, count);
				if (order == "countDec") {
					for (int i = 0; i < byteArray.length/2; i++) {
						byte temp = byteArray[i];
						int temp2 = count[i];
						byteArray[i] = byteArray[byteArray.length-i-1];
						byteArray[byteArray.length-i-1] = temp;
						count[i] = count[count.length-i-1];
						count[count.length-i-1] = temp2;
					}
				}
			}
			return removeDuplicates(byteArray);
		}
		
		public String toString() {
			byte[] bytesInOrder = getElements();
			int[] byteCounts = getCount(bytesInOrder);
			String bytesAndCounts = "";
			for (int i = 0; i < bytesInOrder.length; i++) {
				bytesAndCounts = bytesAndCounts + (int)bytesInOrder[i] + ":" + byteCounts[i];
				if (i < bytesInOrder.length - 1) {
					bytesAndCounts = bytesAndCounts + " ";
				}
			}
			return bytesAndCounts;
		}
		
		public String toString(String format) {
			byte[] bytesInOrder = getElements();
			int[] byteCounts = getCount(bytesInOrder);
			String bytesAndCounts = "";
			for (int i = 0; i < bytesInOrder.length; i++) {
				if (format == "char"){ 
					bytesAndCounts = bytesAndCounts + (char)bytesInOrder[i] + ":" + byteCounts[i];
				}
				else {
					bytesAndCounts = bytesAndCounts + (int)bytesInOrder[i] + ":" + byteCounts[i];
				}
				if (i < bytesInOrder.length - 1) {
				bytesAndCounts = bytesAndCounts + " ";
				}
			}
			return bytesAndCounts;
		}
		
		public void quickSort(byte[] byteArray, int low, int high) {
			if (byteArray == null || byteArray.length == 0) {
				return;
			}
			if (low >= high) {
				return;
			}
			int middle = low + (high - low) / 2;
			int pivot = byteArray[middle]; 
			int i = low, j = high;
			while (i <= j) {
				while (byteArray[i] < pivot) {
					i++;
				}
				while (byteArray[j] > pivot) {
					j--;
				}
				if (i <= j) {
					byte temp = byteArray[i];
					byteArray[i] = byteArray[j];
					byteArray[j] = temp;
					i++;
					j--;
				}
			}
			if (low < j){
				quickSort(byteArray, low, j);
			}
			if (high > i) {
				quickSort(byteArray, i, high);
			}
		}
		
		public void quickSort(int[] arr, int low, int high) {
			if (arr == null || arr.length == 0) {
				return;
			}
			if (low >= high) {
				return;
			}
			int middle = low + (high - low) / 2;
			int pivot = arr[middle];
			int i = low, j = high;
			while (i <= j) {
				while (arr[i] < pivot) {
					i++;
				}
				while (arr[j] > pivot) {
					j--;
				}
				if (i <= j) {
					int temp = arr[i];
					byte temp2 = byteArray[i];
					byteArray[i] = byteArray[j];
					byteArray[j] = temp2;
					arr[i] = arr[j];
					arr[j] = temp;
					i++;
					j--;
				}
			}
	 		if (low < j) {
				quickSort(arr, low, j);
	 		}
			if (high > i) {
				quickSort(arr, i, high);
			}
		}
		
		public byte[] removeDuplicates(byte[] byteArray) {
			byte[] newByte = new byte[byteArray.length];
			int removeLength = 0;
			for (int i = 0; i < byteArray.length; i++) {
				boolean isRepeat = false;
				for (int j = 0; j < i; j++) {
					if (byteArray[i] == newByte[j]) {
						isRepeat = true;
						removeLength++;
						break;
					}
				}
				if (!isRepeat) {
					newByte[i-removeLength] = byteArray[i];
				}
			}
			byte[] newNewByte = new byte[newByte.length - removeLength];
			for (int i = 0; i < newNewByte.length; i++) {
				newNewByte[i] = newByte[i];
			}
			return newNewByte;
		}
		
		public byte[] organizeSameCounts(byte[] byteArray, int[] counts) {
			int i = 0;
			while (i < byteArray.length) {
				int j = i+1;
				while (j < byteArray.length && counts[j] == counts[i]) {
					j++;
				}
				quickSort(byteArray, i, j-1);
				i = j;
			}
			return byteArray;
		}
		
	}
