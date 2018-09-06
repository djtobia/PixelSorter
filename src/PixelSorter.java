
/*
 * 	File: PixelSorter.java
 * 	Author: Dylan Tobia
 * 	Purpose: Sort a an images pixels in each row, and return this as a new file.
 * 
 */

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class PixelSorter {
	// variable for storing the original image to mess with
	private BufferedImage image;

	// constructor
	public PixelSorter(BufferedImage imageToSort) {
		image = imageToSort;
	}

	/*
	 * pixelSortWrite(): Using its already stored image, grab all of its pixels
	 * and store them into a 1D array. Call a helper method to sort, and then
	 * write them into a new image. Return this image.
	 * 
	 */
	public BufferedImage pixelSortWrite() {
		// variables needed for for loops
		int height = image.getHeight();
		int width = image.getWidth();
		int count = 0;
		// get all pixels into a 1D array
		int[] all1DPixels = get1DPixels();
		// sort them
		all1DPixels = sort1D(all1DPixels);
		// create new image using same variables as the original image
		BufferedImage imageToWrite = new BufferedImage(width, height, image.getType());
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				// write pixels to new image
				imageToWrite.setRGB(j, i, all1DPixels[count]);
				count++;
			}

		}
		return imageToWrite;
	}

	/*
	 * rowSortWrite(): Using its already stored image, grab all of its pixels
	 * and store them into a 2D array. Call a helper method to sort them by row,
	 * and then write them into a new image. Return this image.
	 * 
	 */
	public BufferedImage rowSortWrite() {
		// variables needed for for loops
		int height = image.getHeight();
		int width = image.getWidth();
		// use old sort method to both grab, and sort all pixels into a 2d array
		long[][] all2DPixels = sort();
		// new image to write to
		BufferedImage imageToWrite = new BufferedImage(width, height, image.getType());
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				// write to new image
				imageToWrite.setRGB(j, i, (int)all2DPixels[i][j]);
			}
		}
		return imageToWrite;
	}

	/*
	 * sort1D(all1DPixels): Copys the original array of pixels into a new array,
	 * sort it, and return it.
	 */
	private int[] sort1D(int[] all1DPixels) {
		int[] sortedPixels = new int[all1DPixels.length];
		System.arraycopy(all1DPixels, 0, sortedPixels, 0, sortedPixels.length);
		Arrays.sort(sortedPixels);
		return sortedPixels;
	}

	/*
	 * sort(): Get all of the pixels from the classes image variable, store them
	 * into a 2d array by row, call a helper method to sort them, and return
	 * this now sorted 2D array of pixels.
	 * 
	 */
	private long[][] sort() {
		int height = image.getHeight();
		int width = image.getWidth();
		long[][] allPixels = get2DPixels();

		long[][] sorted = new long[height][width];
		for (int i = 0; i < height; i++) {
			sorted[i] = sortIntoRow(allPixels[i]);
		}

		return sorted;
	}

    public BufferedImage flattenImage() {

		int height = image.getHeight();
		int width = image.getWidth();
		long[][]pixels = get2DPixels();
		BufferedImage imageToWrite = new BufferedImage(width, height, image.getType());
	     for(int i = 0; i < height; i++){


	        for(int j = 0; j < width; j++){
	            long test = findClosest(pixels[i][j]);
				imageToWrite.setRGB(j,i, (int) test);
            }
        }
        return imageToWrite;
    }

    private long findClosest(long l) {
	    final int distanceToBlack = (int)Math.abs(-l-16777216);
	    final int distanceToWhite = (int)Math.abs(-l-1);
	    final int distanceToRed = (int)Math.abs(-l-2088896);
	    final int distanceToBlue = (int)Math.abs(-l-12566336);
	    final int distanceToGreen = (int)Math.abs(-l-14630848);
	    final int distanceToYellow = (int)Math.abs(-l-255);
	    final int distanceToOrange = (int)Math.abs(-l-2048000);
        ArrayList<Integer> list = new ArrayList<>();
        list.add(distanceToBlack);
        list.add(distanceToBlue);
        list.add(distanceToGreen);
        list.add(distanceToOrange);
        list.add(distanceToRed);
        list.add(distanceToWhite);
        list.add(distanceToYellow);
        Collections.sort(list);
        long val = list.get(0);
        if(val == distanceToBlack)
                return -16777216;
        else if(val == distanceToWhite)
            return -1;
        else if(val == distanceToRed)
            return -2088896;
        else if(val == distanceToBlue)
            return -12566336;
        else if(val == distanceToGreen)
            return -14630848;
        else if(val == distanceToYellow)
            return -255;
        else
            return -2048000;


    }

    /*
	 * sortIntoRow(row): Copy the original array of pixels into a new array, and
	 * sort it. Then return this array.
	 * 
	 */
	private static long[] sortIntoRow(long[] row) {
		long[] sortedRow = new long[row.length];
		System.arraycopy(row, 0, sortedRow, 0, sortedRow.length);
		Arrays.sort(sortedRow);
		return sortedRow;
	}

	/*
	 * get2DPixels(): grab all of the pixels from the classes image variable,
	 * store them by row into a 2D array, and return this array.
	 * 
	 */
	private long[][] get2DPixels() {
		// array to return
		long[][] pixels = new long[image.getHeight()][image.getWidth()];
		// variables for for loop
		int height = image.getHeight();
		int width = image.getWidth();
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				// store pixel into array
				pixels[i][j] = image.getRGB(j, i);

			}

		}

		return pixels;
	}


    /*
	 * get1DPixels(): Grab all pixels from classes image variable, store them
	 * into a 1D array, and return this array.
	 * 
	 */
	private int[] get1DPixels() {
		// creat new array
		int[] pixels = new int[image.getHeight() * image.getWidth()];
		// variables for for loops
		int height = image.getHeight();
		int width = image.getWidth();
		int count = 0;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				// store pixel into array at count, and increment count
				pixels[count] = image.getRGB(j, i);
				count++;
			}

		}
		return pixels;
	}

}
