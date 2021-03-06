import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 * 
 * @author Pablo Diez Perez
 * @author Ivan Montes Vicente
 * @author Alberto Manzano Reguero
 * @version 1.0
 */
public class Window extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JMenuBar menu;
	private JMenu file;
	private JMenuItem restoreImage;
	private JPanel panelFrame, panelOptions;
	private JLabel filterRGB, filterHSV, filterYUV, imageJPG, labelTime;
	private JTextField time;
	private BufferedImage originalImage;
	/** Buttons to apply the filters with RGB */
	private JRadioButton buttonRGBRed, buttonRGBYellow, buttonRGBGreen;
	/** Buttons to apply the filters with HSV */
	private JRadioButton buttonHSVRed, buttonHSVYellow, buttonHSVGreen;
	/** Buttons to apply the filters with YUV */
	private JRadioButton buttonYUVRed, buttonYUVYellow, buttonYUVGreen;
	private JButton buttonNextImage;
	private int count;
	private int heightImage = 450;
	private int widthImage = 800;
	private Chronometer chronometer;
	private ButtonGroup group;
	private File[] myImages;
	RGBFilter rgbFilter;
	HSVFilter hsvFilter;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window frame = new Window();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws IOException
	 */
	public Window() throws IOException {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 200, 1000, 500);
		setMinimumSize(new Dimension(1000, 500));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		this.setTitle("Banco de pruebas");
		// Create a chronometer
		chronometer = new Chronometer();
		// Initialize count (to open the first image)
		count = 0;
		loadImages();
		// Add menu to the panel
		setMenu();
		// JPanel where image will be placed
		panelFrame = new JPanel(new GridBagLayout());
		// Place the JLabel into JPanel
		setLabelImage();
		// Add this panel to the center
		this.getContentPane().add(panelFrame, BorderLayout.CENTER);
		// Panel where buttons will be placed
		panelOptions = new JPanel(new GridBagLayout());
		// Add all filter buttons
		setRGBFiltersButtons();
		setHSVFiltersButtons();
		setYUVFiltersButtons();
		// Add buttons to a GroupButtons
		groupButtons();
		// Add panelOptions in SOUTH position
		this.getContentPane().add(panelOptions, BorderLayout.SOUTH);
		// Add chronometer
		setChorometer();
		// Add others buttons
		setMoreOptions();
	}

	/**
	 * Method that sets a menu
	 */
	private void setMenu() {

		menu = new JMenuBar();
		file = new JMenu("File");
		restoreImage = new JMenuItem("Restore Image");
		restoreImage.addActionListener(new ActionRestoreImage());
		file.add(restoreImage);
		menu.add(file);
		// Place menu in NORTH position
		this.getContentPane().add(menu, BorderLayout.NORTH);
	}

	/**
	 * Method to select an image
	 * 
	 * @param archive
	 * @throws IOException
	 */
	public void setImage() throws IOException {
		if(count >= myImages.length){
			count = 0;
		}
		// Read image
		BufferedImage image = ImageIO.read(myImages[count]);
		// Save de img in a BufferedImage
		originalImage = image;
		// Resize the image
		adjustImage(originalImage);
		// Refresh the label with the new image
		imageJPG.setIcon(new ImageIcon(originalImage.getScaledInstance(
				widthImage, heightImage, Image.SCALE_DEFAULT)));
		// Refresh the count
		count++;
		

	}

	/**
	 * Method that scale the image
	 * 
	 * @param image
	 */
	private void adjustImage(BufferedImage image) {

		widthImage = 800;
		heightImage = 450;
		float coef;
		if (image.getWidth() >= image.getHeight()) {
			// Its horizontal
			coef = (float) widthImage / image.getWidth();
			heightImage = (int) (image.getHeight() * coef);
		} else {
			// Its vertical
			coef = (float) heightImage / image.getHeight();
			widthImage = (int) (image.getWidth() * coef);
		}

	}

	/**
	 * Method that place the label where the image will be placed
	 * 
	 * @throws IOException
	 */
	private void setLabelImage() throws IOException {

		// Next line taken out of practice last year
		// panelFrame.setBorder(BorderFactory.createLineBorder(Color.black));
		GridBagConstraints constr = new GridBagConstraints();
		constr.gridx = 0;
		constr.gridy = 0;
		imageJPG = new JLabel();
		// Add the first image
		setImage();
		panelFrame.add(imageJPG, constr);
	}

	/**
	 * Method that places the RGB filters buttons
	 */
	private void setRGBFiltersButtons() {
		// RGB
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		filterRGB = new JLabel("RGB: ");
		panelOptions.add(filterRGB, c);

		c.gridx = 1;
		c.gridy = 0;
		buttonRGBRed = new JRadioButton("Red");
		buttonRGBRed.addActionListener(new ActionApplyFilter());
		panelOptions.add(buttonRGBRed, c);

		c.gridx = 2;
		c.gridy = 0;
		buttonRGBYellow = new JRadioButton("Yellow");
		buttonRGBYellow.addActionListener(new ActionApplyFilter());
		panelOptions.add(buttonRGBYellow, c);

		c.gridx = 3;
		c.gridy = 0;
		buttonRGBGreen = new JRadioButton("Green");
		buttonRGBGreen.addActionListener(new ActionApplyFilter());
		panelOptions.add(buttonRGBGreen, c);

	}

	/**
	 * Method that places the HSV filters buttons
	 */
	private void setHSVFiltersButtons() {
		GridBagConstraints c = new GridBagConstraints();
		// HSV
		c.gridx = 0;
		c.gridy = 1;
		filterHSV = new JLabel("HSV: ");
		panelOptions.add(filterHSV, c);

		c.gridx = 1;
		c.gridy = 1;
		buttonHSVRed = new JRadioButton("Red");
		buttonHSVRed.addActionListener(new ActionApplyFilter());
		panelOptions.add(buttonHSVRed, c);

		c.gridx = 2;
		c.gridy = 1;
		buttonHSVYellow = new JRadioButton("Yellow");
		buttonHSVYellow.addActionListener(new ActionApplyFilter());
		panelOptions.add(buttonHSVYellow, c);

		c.gridx = 3;
		c.gridy = 1;
		buttonHSVGreen = new JRadioButton("Green");
		buttonHSVGreen.addActionListener(new ActionApplyFilter());
		panelOptions.add(buttonHSVGreen, c);

	}

	/**
	 * Method that places the YUV filters buttons
	 */
	private void setYUVFiltersButtons() {

		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 2;
		filterYUV = new JLabel("YUV: ");
		panelOptions.add(filterYUV, c);

		c.gridx = 1;
		c.gridy = 2;
		buttonYUVRed = new JRadioButton("Red");
		buttonYUVRed.addActionListener(new ActionApplyFilter());
		panelOptions.add(buttonYUVRed, c);

		c.gridx = 2;
		c.gridy = 2;
		buttonYUVYellow = new JRadioButton("Yellow");
		buttonYUVYellow.addActionListener(new ActionApplyFilter());
		panelOptions.add(buttonYUVYellow, c);

		c.gridx = 3;
		c.gridy = 2;
		buttonYUVGreen = new JRadioButton("Green");
		buttonYUVGreen.addActionListener(new ActionApplyFilter());
		panelOptions.add(buttonYUVGreen, c);

	}

	/**
	 * Method that group the buttons
	 */
	private void groupButtons() {
		// Code of ButtonGroup take out of practice last year
		// This group avoids that two or more buttons can be selected at the
		// same time
		group = new ButtonGroup();
		group.add(buttonRGBRed);
		group.add(buttonRGBYellow);
		group.add(buttonRGBGreen);
		group.add(buttonHSVRed);
		group.add(buttonHSVYellow);
		group.add(buttonHSVGreen);
		group.add(buttonYUVRed);
		group.add(buttonYUVYellow);
		group.add(buttonYUVGreen);

	}

	/**
	 * Method to the chorometer
	 */
	private void setChorometer() {

		GridBagConstraints cons = new GridBagConstraints();

		cons.gridx = 5;
		cons.gridy = 1;
		cons.gridheight = GridBagConstraints.RELATIVE;
		cons.gridwidth = GridBagConstraints.RELATIVE;
		cons.anchor = GridBagConstraints.NORTH;
		cons.fill = GridBagConstraints.NONE;
		labelTime = new JLabel("Time: ");
		panelOptions.add(labelTime, cons);

		cons.gridx = 6;
		time = new JTextField();
		time.setEditable(false);
		time.setText("000000s");
		panelOptions.add(time, cons);

	}

	/**
	 * Button next image
	 */
	private void setMoreOptions() {

		GridBagConstraints cons = new GridBagConstraints();

		cons.gridx = 6;
		cons.gridy = 2;
		buttonNextImage = new JButton("Next Image");
		buttonNextImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					setImage();
					resetButtons();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		});

		panelOptions.add(buttonNextImage, cons);
	}

	/**
	 * Method that show a message dialog if kiwis, tomatoes or lemons are been
	 * detected in the image
	 */
	public void fruitsDetected(int fruit) {
		switch (fruit) {
		case 0:
			JOptionPane.showMessageDialog(this, "There is not fruits","Not fruit", JOptionPane.INFORMATION_MESSAGE);
			break;
		case 1:
			JOptionPane.showMessageDialog(this, "Kiwis are been detected",
					"Kiwis - Green", JOptionPane.INFORMATION_MESSAGE);
			break;
		case 2:
			JOptionPane.showMessageDialog(this, "Tomatoes are been detected",
					"Tomato - Red", JOptionPane.INFORMATION_MESSAGE);
			break;
		case 3:
			JOptionPane.showMessageDialog(this, "Lemons are been detected",
					"Lemnos - Yellow", JOptionPane.INFORMATION_MESSAGE);
			break;
		}

	}

	/**
	 * Filters to apply
	 */
	class ActionApplyFilter implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {

			if (buttonRGBRed.isSelected()) {
				// call to the class RGBFilter (lo podemos poner en la funcion
				// principal para no tener que llamarlo todas las veces)
				rgbFilter = new RGBFilter(originalImage);
				// chronometer star to run
				chronometer.activate();
				// Apply the filter to the image
				originalImage = rgbFilter.redRGBFilter();
				// once the filter is finished, the chronometer stops
				chronometer.stop();
				// read the time
				chronometer.read();
				// show the result
				time.setText(chronometer.toString());
				// reset the chronometer
				chronometer.reset();
				// resize the filter image and put it
				adjustImage(originalImage);
				imageJPG.setIcon(new ImageIcon(originalImage.getScaledInstance(
						widthImage, heightImage, Image.SCALE_DEFAULT)));
				System.out.println("RGB red filter applied.");
				if(rgbFilter.hasTomato()){
					fruitsDetected(2);
				}else{
					fruitsDetected(0);
				}
			}
			if (buttonRGBGreen.isSelected()) {
				rgbFilter = new RGBFilter(originalImage);
				chronometer.activate();
				originalImage = rgbFilter.greenRGBFilter();
				chronometer.stop();
				chronometer.read();
				time.setText(chronometer.toString());
				chronometer.reset();
				adjustImage(originalImage);
				imageJPG.setIcon(new ImageIcon(originalImage.getScaledInstance(
						widthImage, heightImage, Image.SCALE_DEFAULT)));
				System.out.println("RGB green filter applied");
				if(rgbFilter.hasKiwi()){
					fruitsDetected(1);
				}else{
					fruitsDetected(0);
				}
			}
			if (buttonRGBYellow.isSelected()) {
				rgbFilter = new RGBFilter(originalImage);
				chronometer.activate();
				originalImage = rgbFilter.yellowRGBFilter();
				chronometer.stop();
				chronometer.read();
				time.setText(chronometer.toString());
				chronometer.reset();
				adjustImage(originalImage);
				imageJPG.setIcon(new ImageIcon(originalImage.getScaledInstance(
						widthImage, heightImage, Image.SCALE_DEFAULT)));
				System.out.println("RGB Yellow filter applied");
				if(rgbFilter.hasBanana()){
					fruitsDetected(3);
				}else{
					fruitsDetected(0);
				}
			}
			if (buttonHSVRed.isSelected()) {
				hsvFilter = new HSVFilter(originalImage);
				chronometer.activate();
				originalImage = hsvFilter.redHSVFilter();
				chronometer.stop();
				chronometer.read();
				time.setText(chronometer.toString());
				chronometer.reset();
				adjustImage(originalImage);
				imageJPG.setIcon(new ImageIcon(originalImage.getScaledInstance(
						widthImage, heightImage, Image.SCALE_DEFAULT)));
				System.out.println("HSV Red filter applied");
			}
			if (buttonHSVGreen.isSelected()) {
				hsvFilter = new HSVFilter(originalImage);
				chronometer.activate();
				originalImage = hsvFilter.greenHSVFilter();
				chronometer.stop();
				chronometer.read();
				time.setText(chronometer.toString());
				chronometer.reset();
				adjustImage(originalImage);
				imageJPG.setIcon(new ImageIcon(originalImage.getScaledInstance(
						widthImage, heightImage, Image.SCALE_DEFAULT)));
				System.out.println("HSV Green filter applied");
			}
			if (buttonHSVYellow.isSelected()) {
				hsvFilter = new HSVFilter(originalImage);
				chronometer.activate();
				originalImage = hsvFilter.yellowHSVFilter();
				chronometer.stop();
				chronometer.read();
				time.setText(chronometer.toString());
				chronometer.reset();
				adjustImage(originalImage);
				imageJPG.setIcon(new ImageIcon(originalImage.getScaledInstance(
						widthImage, heightImage, Image.SCALE_DEFAULT)));
				System.out.println("HSV Yellow filter applied");
			}
		}
	}

	/**
	 * Class that implements ActionListener to restore the image
	 */
	class ActionRestoreImage implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {

			if (count == 1) {
				count = 3;
			} else
				count--;
			try {
				setImage();
				resetButtons();
				
			} catch (IOException e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(getContentPane(),
						"The image was not able to restore", "Error",
						JOptionPane.ERROR_MESSAGE);
			}

			JOptionPane.showMessageDialog(getContentPane(),
					"The image has been restored", "Restored",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	/**
	 * Method that reset the JRadioButtons to their default value (false)
	 */
	public void resetButtons() {
		time.setText("000000 s");
		group.clearSelection();
		
	}
	
	/**
	 * Method that load the images
	 */
	public void loadImages(){
		File folder = new File(System.getProperty("user.dir") + "/Images");
		myImages = folder.listFiles();
	}
}