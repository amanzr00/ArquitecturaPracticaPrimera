import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;


public class Window extends JFrame {
	
	/** Evitar Warning serial, generado por el IDE  */
	private static final long serialVersionUID = 1L;
	/** Boton para elegir la imagen */
	private JButton btnChooseImg;
	/** Boton para pasar a la siguiente imagen */
	private JButton btnNextImg;
	/** Boton para aplicar el filtro de rojos */
	private JButton btnRedFilter;
	/** Boton para aplicar el filtro de verdes */
	private JButton btnGreenFilter;
	/** Boton para aplicar el filtro de azules */
	private JButton btnBlueFilter;
	/** Boton para aplicar el filtro HSV */
	private JButton btnHSV;
	/** Boton para salir */
	private JButton btnExit;
	/** Imagen a la que se aplicaran los filtros */
	private BufferedImage originalImage;
	/** Panel para la imagen */
	private JPanel imagePanel;
	/** Label donde estara la imaen */
	private JLabel imageView;
	/** Llamada a la clase Processor donde estaran diversas funciones */
	Processor proces = new Processor();
	
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
	 */
	public Window() {
		setTitle("Filtro de imagenes");
		// Cierra por defecto la ventana
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(700, 500);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panelButtons = new JPanel();
		getContentPane().add(panelButtons, BorderLayout.SOUTH);
		
		btnRedFilter = new JButton("Filto Rojo");
		panelButtons.add(btnRedFilter);
		
		btnGreenFilter = new JButton("Filtro verde");
		panelButtons.add(btnGreenFilter);
		
		btnBlueFilter = new JButton("Filtro azul");
		panelButtons.add(btnBlueFilter);
		
		btnHSV = new JButton("HSV");
		panelButtons.add(btnHSV);
		
		btnNextImg = new JButton("Sig. Imagen");
		panelButtons.add(btnNextImg);
		
		JPanel panelPrincipal = new JPanel();
		getContentPane().add(panelPrincipal, BorderLayout.NORTH);
		
		btnChooseImg = new JButton("Cargar Imagen");
		panelPrincipal.add(btnChooseImg);
		
		btnExit = new JButton("Salir");
		panelPrincipal.add(btnExit);
		
		imageView = new JLabel("");
		
		// Panel para la imagen que halla en cada momento
		imagePanel = new JPanel(new FlowLayout());
		imagePanel.add(imageView);
		getContentPane().add(imagePanel);
		
		createActions();
	}

	private void createActions() {
		
		/**
		 * Action para salir de la aplicacion
		 */
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		
		/**
		 * Action para elegir la imagen
		 */
		btnChooseImg.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				originalImage = proces.chooseImg();
				imageView.setIcon(new ImageIcon(originalImage.getScaledInstance(400, 300, Image.SCALE_DEFAULT)));
			}
		});
		
		/**
		 * Action para aplicar el filtro rojo
		 */
		btnRedFilter.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				originalImage = proces.redFilter();
				imageView.setIcon(new ImageIcon(originalImage.getScaledInstance(400, 300, Image.SCALE_DEFAULT)));
			}
		});
		
		/**
		 * Action para aplicar el filtro verde
		 */
		btnGreenFilter.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				
			}
		});
		
		/**
		 * Action para aplicar el filtro azul
		 */
		btnBlueFilter.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				
			}
		});
		
		/**
		 * Action para aplicar el filtro HSV
		 */
		btnHSV.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				
			}
		});
		
		/**
		 * Action para pasar a la siguiente imagen
		 */
		btnHSV.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				
			}
		});
		
	}
}
