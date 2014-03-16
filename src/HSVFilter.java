import java.awt.Color;
import java.awt.image.BufferedImage;


public class HSVFilter {
	private static final float RED_MINIMUN = 150;
	private static final float GREEN_MINIMUN = 200;
	private static final float YELLOW_MINIMUN = 200;
	
	private BufferedImage originalImage;
	private BufferedImage redImage;
	private BufferedImage greenImage;
	private BufferedImage yellowImage;
	private boolean hasBanana;
	private boolean hasKiwi;
	private boolean hasTomato;
	
	public HSVFilter(BufferedImage originalImage) {
		this.originalImage = originalImage;
		
	}
	
	public BufferedImage redHSVFilter(){
		this.redImage = originalImage;
		Color actualPixel;
		int actualRedValue;
		int actualGreenValue;
		int actualBlueValue;
		float numberOfRedPixels = 0f;
		for (int i = 0; i < originalImage.getWidth(); i++) {
			for (int j = 0; j < originalImage.getHeight(); j++) {
				actualPixel = new Color(originalImage.getRGB(i, j));
				actualRedValue = actualPixel.getRed();
				actualGreenValue = actualPixel.getGreen();
				actualBlueValue = actualPixel.getBlue();
				float[] hsv = Color.RGBtoHSB(actualRedValue, actualGreenValue, actualBlueValue, null);
				float h = hsv[0]; 
				float s = hsv[1];
				float v = hsv[2];
				
				if(!(((h> 0 && h < 0.027) ||  (h > 0.972 && h < 1.00)) && s > 0.6 && v > 0.7)){
					redImage.setRGB(i, j, 0);
				}else{
					numberOfRedPixels++;
				}
			}
		}
		float numberOfPixels = originalImage.getTileHeight() * originalImage.getTileWidth();
		if ((numberOfRedPixels / numberOfPixels)> RED_MINIMUN) {
			hasTomato = true;
		}
		return redImage;
	}
	
	public BufferedImage greenHSVFilter(){
		this.greenImage = originalImage;
		Color actualPixel;
		int actualRedValue;
		int actualGreenValue;
		int actualBlueValue;
		for (int i = 0; i < originalImage.getWidth(); i++) {
			for (int j = 0; j < originalImage.getHeight(); j++) {
				actualPixel = new Color(originalImage.getRGB(i, j));
				actualRedValue = actualPixel.getRed();
				actualGreenValue = actualPixel.getGreen();
				actualBlueValue = actualPixel.getBlue();
				float[] hsv = Color.RGBtoHSB(actualRedValue, actualGreenValue, actualBlueValue, null);
				float h = hsv[0]; 
				float s = hsv[1];
				float v = hsv[2];
				if(!((h > 0.111 && h < 0.153 && s > 0.6 && v > 0.20) || (h > 0.164 && h < 0.153 && s > 0.6 && v < 0.75 && v > 0.20))){
					greenImage.setRGB(i, j, 0);
				}
			}
		}
		return greenImage;	
	}
	
	public BufferedImage yellowHSVFilter(){
		this.yellowImage = originalImage;
		Color actualPixel;
		int actualRedValue;
		int actualGreenValue;
		int actualBlueValue;
		for (int i = 0; i < originalImage.getWidth(); i++) {
			for (int j = 0; j < originalImage.getHeight(); j++) {
				actualPixel = new Color(originalImage.getRGB(i, j));
				actualRedValue = actualPixel.getRed();
				actualGreenValue = actualPixel.getGreen();
				actualBlueValue = actualPixel.getBlue();
				float[] hsv = Color.RGBtoHSB(actualRedValue, actualGreenValue, actualBlueValue, null);
				float h = hsv[0]; 
				float s = hsv[1];
				float v = hsv[2];
				if(!(h> 0.111 && h < 0.153 && s > 0.6 && v > 0.7)){
					yellowImage.setRGB(i, j, 0);
				}
			}
		}
		return yellowImage;
	}
	
	public boolean hasTomato(){
		return hasTomato;
	}
	
	public boolean hasBanana(){
		return hasBanana;
	}
	
	public boolean hasKiwi(){
		return hasKiwi;
	}
}
