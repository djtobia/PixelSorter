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

	private BufferedImage image;
	public PixelSorter(BufferedImage imageToSort)
	{
		image = imageToSort;
	}


	public BufferedImage pixelSortWrite() {
		int height = image.getHeight();
		int width = image.getWidth();
		int count = 0;
		int[] all1DPixels = get1DPixels();
		all1DPixels = sort1D(all1DPixels);
		BufferedImage imageToWrite = new BufferedImage(width, height, image.getType());
		for(int i = 0; i < height; i++)
		{
			for(int j = 0; j < width; j++)
			{
				imageToWrite.setRGB(j, i, all1DPixels[count]);
				count++;
			}
			
		}
		return imageToWrite;
	}

	public BufferedImage rowSortWrite() {
		int height = image.getHeight();
		int width = image.getWidth();
		int[][] all2DPixels = sort();
		BufferedImage imageToWrite = new BufferedImage(width, height, image.getType());
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				imageToWrite.setRGB(j, i, all2DPixels[i][j]);
			}
		}
		return imageToWrite;
	}

	private int[] sort1D(int[] all1DPixels) {
		int[] sortedPixels = new int[all1DPixels.length];
		System.arraycopy(all1DPixels, 0, sortedPixels, 0, sortedPixels.length);
		Arrays.sort(sortedPixels);
		return sortedPixels;
	}


	// gets each individual row from the array of pixels, and calls another
	// function to sort them, then returns sorted 2d array
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

	// copy original values into new array, and sort that array using
	// Arrays.sort
	private static int[] sortIntoRow(int[] row) {
		int[] sortedRow = new int[row.length];
		System.arraycopy(row, 0, sortedRow, 0, sortedRow.length);
		Arrays.sort(sortedRow);
		return sortedRow;
	}


	// step through the file and grab each pixel's rgb single int value, and
	// store it in a 2d array, return this array.
	private int[][] get2DPixels() {
		int[][] pixels = new int[image.getHeight()][image.getWidth()];
		int height = image.getHeight();
		int width = image.getWidth();
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				pixels[i][j] = image.getRGB(j, i);
			}

		}

		return pixels;
	}

	// get all pixels and store them as a 1D array
	private int[] get1DPixels() {
		int[] pixels = new int[image.getHeight()*image.getWidth()];
		int height = image.getHeight();
		int width = image.getWidth();
		int count = 0;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				pixels[count]= image.getRGB(j, i);
				count++;
			}

		}
		return pixels;
	}
	
}
