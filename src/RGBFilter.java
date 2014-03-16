import java.awt.Color;
import java.awt.image.BufferedImage;

public class RGBFilter {
	private static final float RED_MINIMUN = 0.20f;
	private static final float GREEN_MINIMUN = 0.20f;
	private static final float YELLOW_MINIMUN = 0.10f;

	private BufferedImage originalImage;
	private BufferedImage redImage;
	private BufferedImage greenImage;
	private BufferedImage yellowImage;
	private boolean hasBanana;
	private boolean hasKiwi;
	private boolean hasTomato;

	public RGBFilter(BufferedImage originalImage) {
		this.originalImage = originalImage;
		
	}

	public BufferedImage redRGBFilter() {
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
				if(actualRedValue < RED_MINIMUN || actualRedValue > 255 || actualGreenValue < 31 || actualGreenValue > 149 || actualBlueValue < 5 || actualBlueValue > 109){
					//System.out.println("entro");
					redImage.setRGB(i, j, 0);
					
				}else{
					numberOfRedPixels++;
				}
			}
		}
		float numberOfPixels = originalImage.getTileHeight() * originalImage.getTileWidth();
		if ((numberOfRedPixels / numberOfPixels)> RED_MINIMUN) {
			hasTomato = true;
		}else{
			hasTomato = false;
		}
		return redImage;

	}

	public BufferedImage greenRGBFilter() {
		this.greenImage = originalImage;
		Color actualPixel;
		int actualRedValue;
		int actualGreenValue;
		int actualBlueValue;
		float numberOfGreenPixels = 0f;
		for (int i = 0; i < originalImage.getWidth(); i++) {
			for (int j = 0; j < originalImage.getHeight(); j++) {
				actualPixel = new Color(originalImage.getRGB(i, j));
				actualRedValue = actualPixel.getRed();
				actualGreenValue = actualPixel.getGreen();
				actualBlueValue = actualPixel.getBlue();
			
				if(actualRedValue < 20 || actualRedValue > 185 || actualGreenValue < 80 || actualGreenValue > 225 || actualBlueValue < 0 || actualBlueValue > 95){
					//System.out.println("entro");
					greenImage.setRGB(i, j, 0);
				}else{
					numberOfGreenPixels++;
				}
			}
		}
		float numberOfPixels = originalImage.getTileHeight() * originalImage.getTileWidth();
		if ((numberOfGreenPixels / numberOfPixels) > GREEN_MINIMUN) {
			hasKiwi = true;
		}else{
			hasKiwi = false;
		}
		return greenImage;
	}
	
	public BufferedImage yellowRGBFilter(){
		this.yellowImage = originalImage;
		Color actualPixel;
		int actualRedValue;
		int actualGreenValue;
		int actualBlueValue;
		float numberOfYellowPixels = 0f;
		for (int i = 0; i < originalImage.getWidth(); i++) {
			for (int j = 0; j < originalImage.getHeight(); j++) {
				actualPixel = new Color(originalImage.getRGB(i, j));
				actualRedValue = actualPixel.getRed();
				actualGreenValue = actualPixel.getGreen();
				actualBlueValue = actualPixel.getBlue();
				
				if(actualRedValue < 210 || actualRedValue > 250 || actualGreenValue < 110 || actualGreenValue > 225 || actualBlueValue < 0 || actualBlueValue > 80){
					//System.out.println("entro");
					yellowImage.setRGB(i, j, 0);
				}else{
					numberOfYellowPixels++;
				}
			}
		}
		float numberOfPixels = originalImage.getTileHeight() * originalImage.getTileWidth();
		if ((numberOfYellowPixels / numberOfPixels) > YELLOW_MINIMUN) {
			hasBanana = true;
		}else{
			hasBanana = false;
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
