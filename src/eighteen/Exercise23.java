package eighteen;

import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.BitSet;

public class Exercise23 {
	static BitSet isPrintable = new BitSet(127);
	static String encoding = System.getProperty("file.encoding");
	static {
	    // Assume an encoding that obeys ASCII eg.ISO-8859-1.
	    // Characters 32 to 127 represent printable characters.
	    for(int i = 32; i <= 127; i++)
	    	isPrintable.set(i);
	}
	// Set the position to the last printable character
    public static void setPrintableLimit(CharBuffer cb) {
    	cb.rewind();
    	while(isPrintable.get(cb.get()));
    	cb.limit(cb.position() - 1);
    	cb.rewind();
    }
	public static void main(String[] args) throws Exception{
//		FileChannel fc = new FileInputStream("F:/PLF/英语单词本.txt").getChannel();
//		ByteBuffer buffer = ByteBuffer.allocate(1024);
//		while(fc.read(buffer) != -1){
//			buffer.flip();
//			System.out.println(Charset.forName(System.getProperty("file.encoding")).decode(buffer));
//			buffer.clear();
//		}
		System.out.println("Default Encoding is: " + encoding);
	    CharBuffer buffer =
	      ByteBuffer.allocate(16).asCharBuffer();
	    buffer.put("ABCDE" + (char) 0x01 + "FG");
	    buffer.rewind();
	    System.out.println(buffer); // Print everything
	    setPrintableLimit(buffer);
	    System.out.println(buffer); // Print printable
	    
	}
}
