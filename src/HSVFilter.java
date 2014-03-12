import java.awt.Color;
import java.awt.image.BufferedImage;


public class HSVFilter {

	private BufferedImage originalImage;
	private BufferedImage redImage;
	private BufferedImage greenImage;
	private BufferedImage yellowImage;
	
	public HSVFilter(BufferedImage originalImage) {
		this.originalImage = originalImage;
		
	}
	
	public BufferedImage redHSVFilter(){
		this.redImage = originalImage;
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
				
				if(!(((h> 0 && h < 0.027) ||  (h > 0.972 && h < 1.00)) && s > 0.6 && v > 0.7)){
					redImage.setRGB(i, j, 0);
				}
			}
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
	
}
