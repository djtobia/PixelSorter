import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Driver {

	public static void main(String[] args) {
		// read in image
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File(args[0]));
		} catch (IOException e) {
			System.out.println("Couldn't open original image file.");
			System.exit(-1);
		}
		PixelSorter sorter = new PixelSorter(image);
		// get all pixels in file as a 2d array
		
		
		// create a new BufferedImage for creating the sorted image, using the
		// same width, height, and type of original image
		BufferedImage imageToWrite = null;
		File outputFile = null; 
		// create new file and write it.
		if(args[1].compareTo("regular") == 0){
			imageToWrite = sorter.rowSortWrite();
			outputFile = new File(getNewFileNameForRowSort(args[0]));
		try {

			ImageIO.write(imageToWrite, "jpg", outputFile);

		} catch (IOException e) {
			System.out.println("Problem writing to new sorted file");
			System.exit(-1);

		}
		}
		else{
		//create a gradient image and write it to file 
		imageToWrite = sorter.pixelSortWrite();
		outputFile = new File(getNewFileNameForGradientSort(args[0]));
		try {
			ImageIO.write(imageToWrite, "jpg", outputFile);
		} catch (IOException e) {
			System.out.println("Problem writing to new gradient sorted file");
			System.exit(-1);
		}
		}
		//imageToWrite = sorter.flattenImage();
		//outputFile = new File(getNewFileNameForFlattenedImage(args[0]));
		//try {
	//		ImageIO.write(imageToWrite, "jpg", outputFile);
	//	} catch (IOException e) {
	//		System.out.println("Problem writing to new gradient sorted file");
	//		System.exit(-1);
	//	}
	}

    private static String getNewFileNameForFlattenedImage(String originalFile) {
        return originalFile.replace(originalFile.substring(originalFile.length() - 4, originalFile.length()),
                "Flattened.jpg");

    }


    // takes original file name from original image, removes its old type, adds
		// "Sorted.jpg" to the end of it
		private static String getNewFileNameForRowSort(String originalFile) {
			return originalFile.replace(originalFile.substring(originalFile.length() - 4, originalFile.length()),
					"Sorted.jpg");
		}
		// takes original file name from original image, removes its old type, adds
				// "GradientSorted.jpg" to the end of it
		private static String getNewFileNameForGradientSort(String originalFile)
		{
			return originalFile.replace(originalFile.substring(originalFile.length() - 4, originalFile.length()),
					"GradientSorted.jpg");
		
		}
}
