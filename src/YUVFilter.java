import java.awt.Color;
import java.awt.image.BufferedImage;


public class YUVFilter {
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
	
	public YUVFilter(BufferedImage originalImage) {
		this.originalImage = originalImage;
	}
	
	public BufferedImage redRGBFilter() {
		this.redImage = originalImage;
		Color actualPixel;
		float actualRedValue;
		float actualGreenValue;
		float actualBlueValue;
		float numberOfRedPixels = 0f;
		for (int i = 0; i < originalImage.getWidth(); i++) {
			for (int j = 0; j < originalImage.getHeight(); j++) {
				actualPixel = new Color(originalImage.getRGB(i, j));
				actualRedValue = actualPixel.getRed();
				actualGreenValue = actualPixel.getGreen();
				actualBlueValue = actualPixel.getBlue();
				float y = getY(actualRedValue, actualGreenValue, actualBlueValue);
				float u = getU(actualRedValue, actualGreenValue, actualBlueValue);
				float v = getV(actualRedValue, actualGreenValue, actualBlueValue);
			}
		}
		return redImage;
	}
	private float getY(float actualRedValue,float actualGreenValue,float actualBlueValue){
		float y = 0.299f * actualRedValue + 0.587f * actualGreenValue + 0.114f * actualBlueValue;
		return y;
	}
	private float getU(float actualRedValue,float actualGreenValue,float actualBlueValue){
		float u = -0.147f * actualRedValue - 0.289f * actualGreenValue + 0.436f * actualBlueValue;
		return u;
	}
	private float getV(float actualRedValue,float actualGreenValue,float actualBlueValue){
		float v = 0.615f * actualRedValue - 0.515f * actualGreenValue - 0.100f * actualBlueValue;
		return v;
	}
}
