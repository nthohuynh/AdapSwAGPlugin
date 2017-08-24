package adapswagplugin.handlers;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import productgeneration.productArchitecture.ACMEGeneration;
import ACME.ACMEFactory;
import ACME.Attachment;
import ACME.Binding;
import ACME.ComponentInstance;
import ACME.Connector;
import java.awt.EventQueue;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;

public class Generation {

	public JFrame frame;
	private final JPanel pnControl = new JPanel();
	private JTextField txtVSpecTree;
	private JTextField txtDirectory;
	private JTextField txtResolutionModel;
	private JTextField txtBaseModel;

	
//	private ArrayList<VSpec> vSpecList = new ArrayList<VSpec>();
//	private ArrayList<VariationPoint> vpList = new ArrayList<VariationPoint>();
//	private ArrayList<VSpecResolution> vSpecResolutionList = new ArrayList<VSpecResolution>();

	ArrayList<ComponentInstance> componentList = new ArrayList<ComponentInstance>();
	ArrayList<Connector> connectorList = new ArrayList<Connector>();
	ArrayList<Attachment> attachementList = new ArrayList<Attachment>();
	ArrayList<Binding> bindingList = new ArrayList<Binding>();
	
	ACME.System destSystem;
	ACMEFactory acmeFactory;
	
	String baseModelFileName;
	
	ACME.System system;
	/**
	 * Create the application.
	 */
	public Generation() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 575, 278);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		
		
		JPanel pnStatus = new JPanel();
		frame.getContentPane().add(pnStatus, BorderLayout.NORTH);
		
		JLabel lblNewLabel_4 = new JLabel("Generate an adaptive software architecture");
		pnStatus.add(lblNewLabel_4);
		
		JPanel pnLoad = new JPanel();
		frame.getContentPane().add(pnLoad, BorderLayout.CENTER);
		GridBagLayout gbl_pnLoad = new GridBagLayout();
		gbl_pnLoad.columnWidths = new int[]{148, 148, 148, 0, 0};
		gbl_pnLoad.rowHeights = new int[] {30, 30, 30, 0, 0};
		gbl_pnLoad.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_pnLoad.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		pnLoad.setLayout(gbl_pnLoad);
		
		JLabel lblNewLabel = new JLabel("Variability model");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		pnLoad.add(lblNewLabel, gbc_lblNewLabel);
		
		txtVSpecTree = new JTextField();
		GridBagConstraints gbc_txtVSpecTree = new GridBagConstraints();
		gbc_txtVSpecTree.gridwidth = 2;
		gbc_txtVSpecTree.fill = GridBagConstraints.BOTH;
		gbc_txtVSpecTree.insets = new Insets(0, 0, 5, 5);
		gbc_txtVSpecTree.gridx = 1;
		gbc_txtVSpecTree.gridy = 0;
		pnLoad.add(txtVSpecTree, gbc_txtVSpecTree);
		txtVSpecTree.setColumns(30);
		
		
		
		JButton btnVSpecTree = new JButton("Load");
		btnVSpecTree.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fc = new JFileChooser();
	        	File fi = new File("/home/DiskD/implementation/process_workspace/"
	    				+ "TransactionManagement/modelinstances/statetransfersystem/");
	            fc.setCurrentDirectory(fi);
	            
	            int returnVal = fc.showOpenDialog(frame);
	        	if (returnVal == JFileChooser.APPROVE_OPTION) {
	        		File file = fc.getSelectedFile();
	        		String newFileName = file.getAbsolutePath();
	        		txtVSpecTree.setText(newFileName);
	        	}
			}	
		});
		GridBagConstraints gbc_btnVSpecTree = new GridBagConstraints();
		gbc_btnVSpecTree.fill = GridBagConstraints.BOTH;
		gbc_btnVSpecTree.insets = new Insets(0, 0, 5, 0);
		gbc_btnVSpecTree.gridx = 3;
		gbc_btnVSpecTree.gridy = 0;
		pnLoad.add(btnVSpecTree, gbc_btnVSpecTree);
		
		JLabel lblNewLabel_1 = new JLabel("Base model");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 1;
		pnLoad.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		txtBaseModel = new JTextField();
		GridBagConstraints gbc_txtBaseModel = new GridBagConstraints();
		gbc_txtBaseModel.gridwidth = 2;
		gbc_txtBaseModel.fill = GridBagConstraints.BOTH;
		gbc_txtBaseModel.insets = new Insets(0, 0, 5, 5);
		gbc_txtBaseModel.gridx = 1;
		gbc_txtBaseModel.gridy = 1;
		pnLoad.add(txtBaseModel, gbc_txtBaseModel);
		txtBaseModel.setColumns(30);
		
		JButton btnBaseModel = new JButton("Load");
		btnBaseModel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
	        	File fi = new File("/home/DiskD/implementation/process_workspace/"
				+ "TransactionManagement/modelinstances/statetransfersystem/");
	            fc.setCurrentDirectory(fi);
	            
	        	int returnVal = fc.showOpenDialog(frame);
	        	if (returnVal == JFileChooser.APPROVE_OPTION) {
	        		File file = fc.getSelectedFile();
	        		String newFileName = file.getAbsolutePath();
	        		txtBaseModel.setText(newFileName);
	             }
				
			}
		});
		GridBagConstraints gbc_btnBaseModel = new GridBagConstraints();
		gbc_btnBaseModel.fill = GridBagConstraints.BOTH;
		gbc_btnBaseModel.insets = new Insets(0, 0, 5, 0);
		gbc_btnBaseModel.gridx = 3;
		gbc_btnBaseModel.gridy = 1;
		pnLoad.add(btnBaseModel, gbc_btnBaseModel);
		
		JLabel lblNewLabel_2 = new JLabel("Resolution model");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 2;
		pnLoad.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		txtResolutionModel = new JTextField();
		GridBagConstraints gbc_txtResolutionModel = new GridBagConstraints();
		gbc_txtResolutionModel.gridwidth = 2;
		gbc_txtResolutionModel.fill = GridBagConstraints.BOTH;
		gbc_txtResolutionModel.insets = new Insets(0, 0, 5, 5);
		gbc_txtResolutionModel.gridx = 1;
		gbc_txtResolutionModel.gridy = 2;
		pnLoad.add(txtResolutionModel, gbc_txtResolutionModel);
		txtResolutionModel.setColumns(30);
		
		JButton btnResolutionModel = new JButton("Load");
		btnResolutionModel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
	        	JFileChooser fc = new JFileChooser();
	        	File fi = new File("/home/DiskD/implementation/process_workspace/"
	    				+ "TransactionManagement/modelinstances/statetransfersystem/");
	            fc.setCurrentDirectory(fi);
	            
	        	int returnVal = fc.showOpenDialog(frame);
	        	if (returnVal == JFileChooser.APPROVE_OPTION) {
	        		File file = fc.getSelectedFile();
	        		String newFileName = file.getAbsolutePath();
	        		txtResolutionModel.setText(newFileName);
	 	        }
				
			}
		});
		GridBagConstraints gbc_btnResolutionModel = new GridBagConstraints();
		gbc_btnResolutionModel.insets = new Insets(0, 0, 5, 0);
		gbc_btnResolutionModel.fill = GridBagConstraints.BOTH;
		gbc_btnResolutionModel.gridx = 3;
		gbc_btnResolutionModel.gridy = 2;
		pnLoad.add(btnResolutionModel, gbc_btnResolutionModel);
		
		JLabel lblNewLabel_3 = new JLabel("Directory");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_3.gridx = 0;
		gbc_lblNewLabel_3.gridy = 3;
		pnLoad.add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		txtDirectory = new JTextField();
		GridBagConstraints gbc_txtVariationPoints = new GridBagConstraints();
		gbc_txtVariationPoints.gridwidth = 2;
		gbc_txtVariationPoints.fill = GridBagConstraints.BOTH;
		gbc_txtVariationPoints.insets = new Insets(0, 0, 0, 5);
		gbc_txtVariationPoints.gridx = 1;
		gbc_txtVariationPoints.gridy = 3;
		pnLoad.add(txtDirectory, gbc_txtVariationPoints);
		txtDirectory.setColumns(30);
		
		JButton btnVariationPoint = new JButton("Load");
		btnVariationPoint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser f = new JFileChooser();
		        f.setFileSelectionMode(1);
		        f.showSaveDialog(null);
		        
		        Generation.this.txtDirectory.setText(f.getSelectedFile().getPath());
			}
		});
		GridBagConstraints gbc_btnVariationPoint = new GridBagConstraints();
		gbc_btnVariationPoint.fill = GridBagConstraints.BOTH;
		gbc_btnVariationPoint.gridx = 3;
		gbc_btnVariationPoint.gridy = 3;
		pnLoad.add(btnVariationPoint, gbc_btnVariationPoint);
		frame.getContentPane().add(pnControl, BorderLayout.SOUTH);
		GridBagLayout gbl_pnControl = new GridBagLayout();
		gbl_pnControl.columnWidths = new int[]{191, 101, 81, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_pnControl.rowHeights = new int[]{25, 0};
		gbl_pnControl.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_pnControl.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		pnControl.setLayout(gbl_pnControl);
		
		JButton btnNewButton_4 = new JButton("Generate");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				generator(); 
			}
		});
		GridBagConstraints gbc_btnNewButton_4 = new GridBagConstraints();
		gbc_btnNewButton_4.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnNewButton_4.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_4.gridx = 5;
		gbc_btnNewButton_4.gridy = 0;
		pnControl.add(btnNewButton_4, gbc_btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("Cancel");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);	
			}
		});
		GridBagConstraints gbc_btnNewButton_5 = new GridBagConstraints();
		gbc_btnNewButton_5.gridwidth = 3;
		gbc_btnNewButton_5.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_5.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnNewButton_5.gridx = 6;
		gbc_btnNewButton_5.gridy = 0;
		pnControl.add(btnNewButton_5, gbc_btnNewButton_5);
	}
	
	public void generator() {
		
		int mc = JOptionPane.WARNING_MESSAGE;
		JOptionPane.showMessageDialog (null, "Starting generating module: "+txtBaseModel.getText(),
				"Generation", mc);
		
		ACMEGeneration f = new ACMEGeneration(txtResolutionModel.getText(),
				txtVSpecTree.getText(),
				txtBaseModel.getText(),
				txtDirectory.getText());
		f.generate();
		
		JOptionPane.showMessageDialog (null, "Finishing generation",
				"Genereted", mc);
	}
	public static void main(String arg[]) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Generation window = new Generation();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
