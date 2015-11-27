import java.io.IOException;

public class HuffmanCoder {
	private String input;
	private String output;
	
	public HuffmanCoder(String inputFile, String outputFile) {
		input = inputFile;
		output = outputFile;
	}
	
	public void compress() throws IOException {
		HuffmanCode byteCodes = new HuffmanCode(input);
		BinaryWriter writer = new BinaryWriter(output);
		
		byte[] bytes = byteCodes.huffList.byteCount.fileBytes;
		for (int i = 0; i < bytes.length; i++) {
			writer.writeBinaryArray(byteCodes.code(bytes[i]));
		}
		writer.finalize();
	}
}