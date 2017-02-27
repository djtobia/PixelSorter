
/*
 * 	File: PixelSorter.java
 * 	Author: Dylan Tobia
 * 	Purpose: Sort a an images pixels in each row, and return this as a new file.
 * 
 */

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import javax.imageio.ImageIO;

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
		int[][] all2DPixels = sort();
		// new image to write to
		BufferedImage imageToWrite = new BufferedImage(width, height, image.getType());
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				// write to new image
				imageToWrite.setRGB(j, i, all2DPixels[i][j]);
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
	private int[][] sort() {
		int height = image.getHeight();
		int width = image.getWidth();
		int[][] allPixels = get2DPixels();
		int[][] sorted = new int[height][width];
		for (int i = 0; i < height; i++) {
			sorted[i] = sortIntoRow(allPixels[i]);

		}
		return sorted;
	}

	/*
	 * sortIntoRow(row): Copy the original array of pixels into a new array, and
	 * sort it. Then return this array.
	 * 
	 */
	private static int[] sortIntoRow(int[] row) {
		int[] sortedRow = new int[row.length];
		System.arraycopy(row, 0, sortedRow, 0, sortedRow.length);
		Arrays.sort(sortedRow);
		return sortedRow;
	}

	/*
	 * get2DPixels(): grab all of the pixels from the classes image variable,
	 * store them by row into a 2D array, and return this array.
	 * 
	 */
	private int[][] get2DPixels() {
		// array to return
		int[][] pixels = new int[image.getHeight()][image.getWidth()];
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
