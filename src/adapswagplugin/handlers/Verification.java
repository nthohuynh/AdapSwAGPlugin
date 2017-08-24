package adapswagplugin.handlers;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;








import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;

import resolutionmodel.api.*;
import resolutionmodel.implement.ResolutionModel;
import vspectree.api.*; 
import vspectree.implement.VSpecTreeImpl;
import cvl.*;
public class Verification {

	public JFrame frame;
	private final JPanel pnControl = new JPanel();
	private JTextField txtVSpecTree;
	private JTextField txtResolutionModel;

	private ResolutionModelService rmService;
	private VSpecTreeService vspService;
	ArrayList<VSpecResolution> resolutionList;
	/**
	 * Create the application.
	 */
	public Verification() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.rmService = new ResolutionModel();
	    this.vspService = new VSpecTreeImpl();
	    
	    
		frame = new JFrame();
		frame.setBounds(100, 100, 575, 165);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		
		JPanel pnStatus = new JPanel();
		frame.getContentPane().add(pnStatus, BorderLayout.NORTH);
		
		JLabel lblNewLabel_4 = new JLabel("Verify the resolution model and the VSpec tree");
		pnStatus.add(lblNewLabel_4);
		
		JPanel pnLoad = new JPanel();
		frame.getContentPane().add(pnLoad, BorderLayout.CENTER);
		GridBagLayout gbl_pnLoad = new GridBagLayout();
		gbl_pnLoad.columnWidths = new int[]{148, 148, 148, 0, 0};
		gbl_pnLoad.rowHeights = new int[] {30, 30, 0};
		gbl_pnLoad.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_pnLoad.rowWeights = new double[]{0.0, 0.0};
		pnLoad.setLayout(gbl_pnLoad);
		
		JLabel lblNewLabel = new JLabel("VSpec tree");
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
		txtVSpecTree.setText("/home/DiskD/implementation/process_workspace/"
				+ "TransactionManagement/modelinstances/statetransfersystem/TMSVariabilityModel.xmi");
		
		JButton btnVSpecTree = new JButton("Load");
		btnVSpecTree.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fc = new JFileChooser();
	        	File fi = new File("/home/DiskD/implementation/process_workspace/Process/model/");
	            fc.setCurrentDirectory(fi);
	            
	            int returnVal = fc.showOpenDialog((new Verification()).frame);
	        	if (returnVal == JFileChooser.APPROVE_OPTION) {
	        		File file = fc.getSelectedFile();
	        		String newFileName = file.getAbsolutePath();
	        		txtVSpecTree.setText(newFileName);
	        	} else {
	        		//log.append("Open command cancelled by user." + newline);
	        	}
			}
		});
		GridBagConstraints gbc_btnVSpecTree = new GridBagConstraints();
		gbc_btnVSpecTree.fill = GridBagConstraints.BOTH;
		gbc_btnVSpecTree.insets = new Insets(0, 0, 5, 0);
		gbc_btnVSpecTree.gridx = 3;
		gbc_btnVSpecTree.gridy = 0;
		pnLoad.add(btnVSpecTree, gbc_btnVSpecTree);
		
		JLabel lblNewLabel_2 = new JLabel("Resolution model");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 1;
		pnLoad.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		txtResolutionModel = new JTextField();
		GridBagConstraints gbc_txtResolutionModel = new GridBagConstraints();
		gbc_txtResolutionModel.gridwidth = 2;
		gbc_txtResolutionModel.fill = GridBagConstraints.BOTH;
		gbc_txtResolutionModel.insets = new Insets(0, 0, 5, 5);
		gbc_txtResolutionModel.gridx = 1;
		gbc_txtResolutionModel.gridy = 1;
		pnLoad.add(txtResolutionModel, gbc_txtResolutionModel);
		txtResolutionModel.setColumns(30);
		txtResolutionModel.setText("/home/DiskD/implementation/process_workspace/"
				+ "TransactionManagement/modelinstances/statetransfersystem/TMSResolutionModelV1.xmi");
		
		JButton btnResolutionModel = new JButton("Load");
		btnResolutionModel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
	        	File fi = new File("/home/DiskD/implementation/process_workspace/Process/model/");
	            fc.setCurrentDirectory(fi);
	            
	            int returnVal = fc.showOpenDialog(null);
	        	if (returnVal == JFileChooser.APPROVE_OPTION) {
	        		File file = fc.getSelectedFile();
	        		String newFileName = file.getAbsolutePath();
	        		txtResolutionModel.setText(newFileName);
	        	} else {
	        		//log.append("Open command cancelled by user." + newline);
	        	}
				
			}
		});
		GridBagConstraints gbc_btnResolutionModel = new GridBagConstraints();
		gbc_btnResolutionModel.insets = new Insets(0, 0, 5, 0);
		gbc_btnResolutionModel.fill = GridBagConstraints.BOTH;
		gbc_btnResolutionModel.gridx = 3;
		gbc_btnResolutionModel.gridy = 1;
		pnLoad.add(btnResolutionModel, gbc_btnResolutionModel);
		frame.getContentPane().add(pnControl, BorderLayout.SOUTH);
		GridBagLayout gbl_pnControl = new GridBagLayout();
		gbl_pnControl.columnWidths = new int[]{191, 101, 81, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_pnControl.rowHeights = new int[]{25, 0};
		gbl_pnControl.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_pnControl.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		pnControl.setLayout(gbl_pnControl);
		
		JButton btnNewButton_4 = new JButton("Verify");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				verify(txtVSpecTree.getText(), txtResolutionModel.getText());
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
				Verification.this.frame.dispatchEvent(new WindowEvent(Verification.this.frame, 201));
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
	boolean verify(String vmFile, String rmFile) {
		System.out.println("Start the verification process");
		resolutionList = new ArrayList<VSpecResolution>();
		
		VPackage vpackage = this.vspService.getVPackage(vmFile);
		ArrayList<BCLConstraint> bclConstraints = new ArrayList<BCLConstraint>();
		bclConstraints = this.vspService.getBCLConstraint(vpackage);
		
		VSpecResolution vspecResolutionRoot = rmService.getVSpecResolutionRoot(rmFile); 
		if (!((ChoiceResolution)vspecResolutionRoot).isDecision()) {
			int mc = JOptionPane.WARNING_MESSAGE;
			JOptionPane.showMessageDialog (null, "The VSpecResolution root is not selected ",
					"Resolution model error", mc);
			return false;
		}
		resolutionList = rmService.getVSPecResolutionList(vspecResolutionRoot);
		String result = null;
		for (VSpecResolution vspecResolution : resolutionList) {
			result = verifyNode(vspecResolution, bclConstraints);
			if (!result.equals("valid") ) {
				int mc = JOptionPane.WARNING_MESSAGE;
				JOptionPane.showMessageDialog (null, result, "Error Message", mc);
				return false;
			}
		}
	
	    int mc = JOptionPane.WARNING_MESSAGE;;
	    JOptionPane.showMessageDialog(null, "The resolution model is : " + result, 
	    		"Generation", mc);
	
		
		return true;
		
	}
	
	String verifyNode(VSpecResolution vspecResolution,
			ArrayList<BCLConstraint> bclConstraints) {
		if (vspecResolution instanceof ChoiceResolution) {
			ChoiceResolution choiceResolution = (ChoiceResolution) vspecResolution;
			Choice resolvedChoice = choiceResolution.getResolvedChoice();

			if (!choiceResolution.isDecision()) { // check impliedByParent
				if (resolvedChoice.isIsImpliedByParent()) {
					EObject parent = choiceResolution.eContainer();
					if (((ChoiceResolution) parent).isDecision()) {
						return "Error: Conflict with impliedByParent constraint in "
								+ choiceResolution.getName();
					}
				}
			} else {
				if (resolvedChoice.getGroupMultiplicity() != null) { // check
																		// multiplicity
					int min = resolvedChoice.getGroupMultiplicity().getLower();
					int max = resolvedChoice.getGroupMultiplicity().getUpper();
					int count = 0;
					for (VSpecResolution subVSpecResolution : choiceResolution
							.getChild()) {
						if (subVSpecResolution instanceof ChoiceResolution) {
							ChoiceResolution subChoiceResolution = (ChoiceResolution) subVSpecResolution;
							if (subChoiceResolution.isDecision()) {
								count++;
							}
						}
					}
					if ((count < min) || (count > max)) {
						return "Error: Conflict with multiplicity constraint in "
								+ resolvedChoice.getName();
					}
				}

				// check implies/excludes

				for (BCLConstraint bclConstraint : bclConstraints) {
					EList<BCLExpression> expressions = bclConstraint
							.getExpression();

					for (BCLExpression expression : expressions) {
						if (expression instanceof OperationCallExp) {
							OperationCallExp operationExpression = (OperationCallExp) expression;
							EList<BCLExpression> arguments = operationExpression
									.getArgument();
							VSpec vspec1 = ((VSpecRef) arguments.get(0))
									.getVSpec();
							VSpec vspec2 = ((VSpecRef) arguments.get(1))
									.getVSpec();

							if ((operationExpression.getOperation().getName()
									.equals("logImplies"))
									&& (vspec1.getName().equals(resolvedChoice
											.getName()))) {
								for (VSpecResolution vspresolution : resolutionList) {
									if ((vspresolution.getResolvedVSpec()
											.getName().equals(vspec2.getName()))
											&& (!((ChoiceResolution) vspresolution)
													.isDecision())) {
										return "Error: Conflict with implies constraint at  "
												+ resolvedChoice.getName();
									}
								}
							}
							if ((operationExpression.getOperation().getName()
									.equals("logExcludes"))
									&& (vspec1.getName().equals(resolvedChoice
											.getName()))) {
								for (VSpecResolution vspresolution : resolutionList) {
									if ((vspresolution.getResolvedVSpec()
											.getName().equals(vspec2.getName()))
											&& (((ChoiceResolution) vspresolution)
													.isDecision())) {
										return "Error: Conflict with excludes constraint at "
												+ resolvedChoice.getName();
									}
								}
							}

						}
					}
				}

				if (choiceResolution.getChild() != null) {
					for (VSpecResolution subVSpecResolution : choiceResolution
							.getChild()) {
						return verifyNode(subVSpecResolution, bclConstraints);
					}
				}

			}

		}

		return "valid";
	}
	
	public static void main(String arg[]) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Verification window = new Verification();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
