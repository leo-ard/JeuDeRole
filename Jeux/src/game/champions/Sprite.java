package game.champions;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class Sprite {
	Image m;
	int frequance;
	int frames;
	Image images[];
	
	long lastTime;
	int currentImage;
	
	
	/**
	 * 
	 * 
	 * @param m Image file
	 * @param frequance La frequance de changement d'image
	 * @param frames The number of frames
	 */
	public Sprite(Image m, int frequance, int frames){
		this.m = m;
		this.frequance = frequance;
		this.frames = frames;
		this.lastTime = System.currentTimeMillis();
		BufferedImage bm = toBufferedImage(m);
		images = new Image[frames];
		int split = bm.getWidth()/frames;
		
		for(int i = 0; i < frames; i++){
			images[i] = bm.getSubimage(i*split, 0, split, bm.getHeight());
			//new FilteredImageSource(m.getSource(), new CropFilter(i*split, m.getHeight(null), split, m.getHeight(null)));
			
			
			
		}
		
	}
	
	
	public Image getImage(){
		if(System.currentTimeMillis() >  lastTime + frequance){
			currentImage += 1;
			lastTime = System.currentTimeMillis();
			if(currentImage >= 4){
				currentImage = 0;
			}
			System.out.println(lastTime);
		}
		
		return images[currentImage];
	}
	
	public static BufferedImage toBufferedImage(Image img)
	{
	    if (img instanceof BufferedImage)
	    {
	        return (BufferedImage) img;
	    }

	    // Create a buffered image with transparency
	    BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

	    // Draw the image on to the buffered image
	    Graphics2D bGr = bimage.createGraphics();
	    bGr.drawImage(img, 0, 0, null);
	    bGr.dispose();

	    // Return the buffered image
	    return bimage;
	}

}
