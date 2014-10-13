//UIUC CS125 FALL 2014 MP. File: PixelEffects.java, CS125 Project: Challenge4-Photoscoop, Version: 2014-10-05T15:45:48-0500.087714731

/** A class to implement the various pixel effects.
 *
 * Todo: Put your netid (i.e. username) in the line below * 
 * @author sabowma2
 */
public class PixelEffects {

	/** Copies the source image to a new 2D integer image */
	public static int[][] copy(int[][] source) { //working
		// Create a NEW 2D integer array and copy the colors across
		// See redeye code below
		
		int width = source.length, height = source[0].length;
		int[][] result = new int[width][height];
		
		for(int i=0; i<width; i++) // row stays constant
		{
			  for(int j=0; j<height; j++)// column changes
			  {
				  int rgb = source[i][j];
				  int red = RGBUtilities.toRed(rgb);
				  int green = RGBUtilities.toGreen(rgb);
				  int blue = RGBUtilities.toBlue(rgb);
				  result[i][j] = RGBUtilities.toRGB(red, green, blue); 
			  }
		}	    
		return result; 				
	}
	/**
	 * Resize the array image to the new width and height
	 * You are going to need to figure out how to map between a pixel
	 * in the destination image to a pixel in the source image
	 * @param source
	 * @param newWidth
	 * @param newHeight
	 * @return
	 */
	public static int[][] resize(int[][] source, int newWidth, int newHeight) { //WORKING
		
		// Hints: Use two nested for loops between 0... newWidth-1 and 0.. newHeight-1 inclusive.
		// Hint: You can just use relative proportion to calculate the x (or y coordinate)  in the original image.
		// For example if you're setting a pixel halfway across the image, you should be reading half way across the original image too.
		
		int[][] result = new int[newWidth][newHeight];
		double prop = ((double) newWidth) / source.length;
		double prop2 = ((double) newHeight) / source[0].length;
		    for(int i=0; i<newWidth; i++){
		    	for(int j=0; j<newHeight; j++)
		    	{
		    		result[i][j]=source[(int)(i/prop)][(int)(j/prop2)];
		        }
		    }		    
		return result;		        
	}

	/**
	 * Half the size of the image. This method should be just one line! Just
	 * delegate the work to resize()!
	 */
	public static int[][] half(int[][] source) { //WORKING
		return resize(source, source.length/2, (source[0].length)/2);
	}
	
	/**
	 * Create a new image array that is the same dimesions of the reference
	 * array. The array may be larger or smaller than the source. Hint -
	 * this methods should be just one line - delegate the work to resize()!
	 * 
	 * @param source
	 *            the source image
	 * @param reference
	 * @return the resized image
	 */
	public static int[][] resize(int[][] source, int[][] reference) { //WORKING //A --> B
		return resize(source, reference.length, reference[0].length); 
	}

	/** Flip the image vertically */
	public static int[][] flip(int[][] source) //WORKING
	{
		int srcW = source.length;
		int srcH = source[0].length;
		int tgtW= srcW;
		int tgtH = srcH;
		int [][]result = new int[tgtW][tgtH];
		for(int tgtX=0; tgtX<tgtW; tgtX++){
			for(int tgtY=0; tgtY<tgtH; tgtY++)
			{
				int srcY = tgtH-tgtY-1;
				int srcX = tgtX;
				result[tgtX][tgtY] = source[srcX][srcY];				
			}			
		}		
		return result;
	}

	/** Reverse the image horizontally */
	public static int[][] mirror(int[][] source) { //WORKING!
		
		int srcW = source.length;
		int srcH = source[0].length;
		int tgtW= srcW;
		int tgtH = srcH;
		int [][]result = new int[tgtW][tgtH];
		for(int tgtX=0; tgtX<tgtW; tgtX++){
			for(int tgtY=0; tgtY<tgtH; tgtY++)
			{
				int srcY = tgtY;
				int srcX = tgtW-tgtX-1;
				result[tgtX][tgtY] = source[srcX][srcY];				
			}			
		}		
		return result;
	}

	/** Rotate the image */
	public static int[][] rotateLeft(int[][] source) {
		int srcW = source.length;
		int srcH = source[0].length;
		int tgtW= srcH;
		int tgtH = srcW;
		int [][]result = new int[tgtW][tgtH];
		for(int tgtX=0; tgtX<tgtW; tgtX++){
			for(int tgtY=0; tgtY<tgtH; tgtY++)
			{				
				int srcX = tgtH-tgtY-1;
				int srcY = tgtX; // 2 - 2
				result[tgtX][tgtY] = source[srcX][srcY];				
			}			
		}		
		return result;
	}

	/** Merge the red,blue,green components from two images */
	public static int[][] merge(int[][] sourceA, int[][] sourceB) {
		// The output should be the same size as the input. Scale (x,y) values
		// when reading the background
		// (e.g. so the far right pixel of the source is merged with the
		// far-right pixel of the background).
		int width = sourceA.length, height = sourceA[0].length;
		int[][] result = new int[width][height];
		for (int i = 0; i < width; i++)
			for (int j = 0; j < height; j++) {
				int rgb = sourceA[i][j];
				int rgb2 = sourceB[i][j];
				int red = RGBUtilities.toRed(rgb);
				int red2 = RGBUtilities.toRed(rgb2);
				int green = RGBUtilities.toGreen(rgb);
				int green2 = RGBUtilities.toGreen(rgb2);
				int blue = RGBUtilities.toBlue(rgb);
				int blue2 = RGBUtilities.toBlue(rgb2);
				int combRed = (red+red2)/2;
				int combGreen = (green+green2)/2;
				int combBlue = (blue+blue2)/2;
				result[i][j] = RGBUtilities.toRGB(combRed, combGreen, combBlue);
			}

		return result;
	}

	/**
	 * Replace the green areas of the foreground image with parts of the back
	 * image
	 */
	public static int[][] chromaKey(int[][] foreImage, int[][] backImage) { 
		// If the image has a different size than the background use the
		// resize() method
		// create an image the same as the background size.
		int rgb2, green2, blue2, red2;
		resize(backImage, foreImage);
		//resize(foreImage, backImage);
		int width = foreImage.length, height = foreImage[0].length;
		int[][] result = new int[width][height];
		for (int i = 0; i < width; i++){
			for (int j = 0; j < height; j++) {
				int rgb = foreImage[i][j];
				int red = RGBUtilities.toRed(rgb);
				int green = RGBUtilities.toGreen(rgb);
				int blue = RGBUtilities.toBlue(rgb);
				if (green > 4 * Math.max(red, blue) && green > 64)
				{
					rgb2 = backImage[i][j];
					red2 = RGBUtilities.toRed(rgb2);
					green2 = RGBUtilities.toGreen(rgb2);
					blue2 = RGBUtilities.toBlue(rgb2);
					
					result[i][j] = RGBUtilities.toRGB(red2, green2, blue2);
				}		
				else
				{
					result[i][j]=foreImage[i][j];
				}
			}
		}
		return result;
	}

	/** Removes "redeye" caused by a camera flash. sourceB is not used */
	public static int[][] redeye(int[][] source, int[][] sourceB) { //WORKING

		int width = source.length, height = source[0].length;
		int[][] result = new int[width][height];
		for (int i = 0; i < width; i++)
			for (int j = 0; j < height; j++) {
				int rgb = source[i][j];
				int red = RGBUtilities.toRed(rgb);
				int green = RGBUtilities.toGreen(rgb);
				int blue = RGBUtilities.toBlue(rgb);
				if (red > 4 * Math.max(green, blue) && red > 64)
					red = green = blue = 0;
				result[i][j] = RGBUtilities.toRGB(red, green, blue);
			}

		return result;
	}

	/* Upto you! do something fun to the image */
	public static int[][] funky(int[][] source, int[][] sourceB) { //WORKING kind of hehe
		// You need to invent your own image effect
		// Minimum boring requirements to pass autograder:
		
		// Does not ask for any user input and returns a new 2D array
		// Todo: remove this return null
		
		int srcW = source.length;
		int srcH = source[0].length;
		int tgtW= srcH;
		int tgtH = srcW;
		int [][]result = new int[tgtW][tgtH];
		for(int tgtX=0; tgtX<tgtW; tgtX++){
			for(int tgtY=0; tgtY<tgtH; tgtY++)
			{				
				int srcX = tgtH-tgtY-1;
				int srcY = tgtX; // 2 - 2
				result[tgtX][tgtY] = source[srcX][srcY];				
			}			
		}		
		return result;
	}
}
