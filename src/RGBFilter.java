import java.awt.Color;
import java.awt.image.BufferedImage;

public class RGBFilter {
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

	public RGBFilter(BufferedImage originalImage) {
		this.originalImage = originalImage;
		
	}

	public BufferedImage redRGBFilter() {
		this.redImage = originalImage;
		Color firstPixel = new Color(originalImage.getRGB(0, 0));
		float redAverage = (float) firstPixel.getRed();
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
				if(actualRedValue < RED_MINIMUN || actualRedValue > 255 || actualGreenValue < 31 || actualGreenValue > 149 || actualBlueValue < 5 || actualBlueValue > 109){
					//System.out.println("entro");
				redImage.setRGB(i, j, 0);
				}else{
					redAverage = (redAverage + actualRedValue) / 2;	
				}
			}
			
		}
		if (redAverage > RED_MINIMUN) {
			hasTomato = true;
		}
		return redImage;

	}

	public BufferedImage greenRGBFilter() {
		this.greenImage = originalImage;
		Color firstPixel = new Color(originalImage.getRGB(0, 0));
		float greenAverage = (float) firstPixel.getGreen();
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
			
				if(actualRedValue < 20 || actualRedValue > 185 || actualGreenValue < 80 || actualGreenValue > 225 || actualBlueValue < 0 || actualBlueValue > 95){
					//System.out.println("entro");
					greenImage.setRGB(i, j, 0);
				}else{
					greenAverage = (greenAverage + actualGreenValue) / 2;
				}
			}
			
		}
		if (greenAverage > GREEN_MINIMUN) {
			hasKiwi = true;
		}
		return greenImage;
	}
	
	public BufferedImage yellowRGBFilter(){
		this.yellowImage = originalImage;
		Color firstPixel = new Color(originalImage.getRGB(0, 0));
		float redAverage = (float) firstPixel.getRed();
		float greenAverage = (float) firstPixel.getGreen();
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
				
				if(actualRedValue < 210 || actualRedValue > 250 || actualGreenValue < 110 || actualGreenValue > 225 || actualBlueValue < 0 || actualBlueValue > 80){
					//System.out.println("entro");
					yellowImage.setRGB(i, j, 0);
				}else{
					greenAverage = (greenAverage + actualGreenValue) / 2;
					redAverage = (redAverage + actualRedValue) / 2;	
				}
			}
		}
		
			hasBanana = true;
		
		return yellowImage;
	}
}
