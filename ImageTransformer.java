import java.awt.image.BufferedImage;
import java.awt.Color;
import java.io.File;
import javax.imageio.ImageIO;

public class ImageTransformer {
	public static void main(String args[]) {
		try {
			// Instantiate ImageUtils
			ImageUtils iu = new ImageUtils();
			
			// Load orignial image
			Color[][] image = iu.loadImage("LennaCV.png");
			
			// Add a tab to display the original image
			iu.addImage(image, "Original Color Image");
			
			// Display the image
			iu.display();
			
			// Now start transforming the image to grayscale
			BufferedImage img = null;
			File f = null;

			// Read source image file to convert to grayScale
			f = new File("LennaCV.png");
			img = ImageIO.read(f);
			
			// Get image width and height
			int width = img.getWidth();
			int height = img.getHeight();

			// Convert to grayScale
			for(int y = 0; y < height; y++) {
				for(int x = 0; x < width; x++) {
					int p = img.getRGB(x,y);

					int a = (p>>24)&0xff;
					int r = (p>>16)&0xff;
					int g = (p>>8)&0xff;
					int b = p&0xff;

					// Calculate average
					int avg = (r+g+b)/3;

					// Replace RGB value with avg
					p = (a<<24) | (avg<<16) | (avg<<8) | avg;

					img.setRGB(x, y, p);
				}
			}

			// Write transformed image to disc
			f = new File("grayScale.jpg");
			ImageIO.write(img, "jpg", f);
			
			// Load grayScale image
			Color[][] image2 = iu.loadImage("grayScale.jpg");
			
			// Add a tab to display the grayScale image
			iu.addImage(image2, "Transformed Gray Scale Image");
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
}
