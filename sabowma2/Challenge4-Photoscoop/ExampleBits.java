//UIUC CS125 FALL 2014 MP. File: ExampleBits.java, CS125 Project: Challenge4-Photoscoop, Version: 2014-10-05T15:45:48-0500.087714731

public class ExampleBits {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		for(int i=0;i<16;i++) {
			TextIO.putln(i+ " "+Integer.toHexString(i)+ " : "+Integer.toBinaryString(i));
		}
	}
}
