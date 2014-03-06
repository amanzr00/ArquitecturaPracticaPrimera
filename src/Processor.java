import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;


public class Processor {

	public BufferedImage currentImg;
	
	public BufferedImage chooseImg() {
		
		BufferedImage bmp = null;
		JFileChooser selector = new JFileChooser();
		selector.setDialogTitle("Seleccione una imagen");
		// Filtramos los tipos de archivos
		FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & GIF & BMP", "jpg", "gif", "bmp");
		selector.setFileFilter(filter);
		
		int flag = selector.showOpenDialog(null);
		
		// Comprobamos que se pulsa aceptar
		if(flag==JFileChooser.APPROVE_OPTION){
			try{
				// Devuelve la img seleccionada
				File selectedImg = selector.getSelectedFile();
				
				bmp = ImageIO.read(selectedImg);
				
			}catch(Exception e){
				
			}
		}
		this.currentImg = bmp;
		
		
		return bmp;
	}
	
	public BufferedImage redFilter(){
		
		Color colorAux;
		int pixel, colorSRGB;
		int i = 0;
		int j = 0;
		// Recorremos los pixeles de la imagen uno a uno
		for(i = 0; i < currentImg.getWidth(); i++){
			for(j = 0; j < currentImg.getHeight(); j++){
			
				colorAux = new Color(currentImg.getRGB(i, j));
				
				pixel = (int) colorAux.getRed();
				
				colorSRGB = pixel << 16 ;
				
				currentImg.setRGB(i, j, colorSRGB);
			}
		}
		
		
		return currentImg;
	}

}
