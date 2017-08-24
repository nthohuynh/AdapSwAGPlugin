package adapswagplugin.handlers;


import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import codegeneration.JavaGeneration;

public class CodeGeneration {
	public JFrame frame;
	private final JPanel pnControl = new JPanel();
	private JTextField txtProductModel;
	private JTextField txtDirectory;

	public CodeGeneration() {
		initialize();
	}

	private void initialize() {
		this.frame = new JFrame();
		this.frame.setBounds(100, 100, 574, 173);

		this.frame.setLocationRelativeTo(null);

		JPanel pnStatus = new JPanel();
		this.frame.getContentPane().add(pnStatus, "North");

		JLabel lblNewLabel_4 = new JLabel("Generate executable code");
		pnStatus.add(lblNewLabel_4);

		JPanel pnLoad = new JPanel();
		this.frame.getContentPane().add(pnLoad, "Center");
		GridBagLayout gbl_pnLoad = new GridBagLayout();
		gbl_pnLoad.columnWidths = new int[] { 148, 148, 148 };
		gbl_pnLoad.rowHeights = new int[] { 30, 0, 30 };
		gbl_pnLoad.columnWeights = new double[] { 1.0D, 0.0D, 0.0D, 0.0D,
				Double.MIN_VALUE };
		gbl_pnLoad.rowWeights = new double[] { 0.0D, 1.0D, 0.0D,
				Double.MIN_VALUE };
		pnLoad.setLayout(gbl_pnLoad);

		JLabel lblNewLabel = new JLabel("Product model");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		pnLoad.add(lblNewLabel, gbc_lblNewLabel);

		this.txtProductModel = new JTextField();
		GridBagConstraints gbc_txtVSpecTree = new GridBagConstraints();
		gbc_txtVSpecTree.gridwidth = 2;
		gbc_txtVSpecTree.fill = 1;
		gbc_txtVSpecTree.insets = new Insets(0, 0, 5, 5);
		gbc_txtVSpecTree.gridx = 1;
		gbc_txtVSpecTree.gridy = 0;
		pnLoad.add(this.txtProductModel, gbc_txtVSpecTree);
		this.txtProductModel.setColumns(30);

		this.txtProductModel.setText("/home/DiskD/implementation/process_workspace/TransactionManagement/"
				+ "modelinstances/statetransfersystem/TMSBaseModel.xmi");

		JButton btnVSpecTree = new JButton("Load");
		btnVSpecTree.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				File fi = new File(
						"/home/DiskD/implementation/process_workspace/");
				fc.setCurrentDirectory(fi);

				int returnVal = fc.showOpenDialog(CodeGeneration.this.frame);
				if (returnVal == 0) {
					File file = fc.getSelectedFile();
					String newFileName = file.getAbsolutePath();
					txtProductModel.setText(newFileName);
				}
			}
		});
		GridBagConstraints gbc_btnVSpecTree = new GridBagConstraints();
		gbc_btnVSpecTree.fill = 1;
		gbc_btnVSpecTree.insets = new Insets(0, 0, 5, 0);
		gbc_btnVSpecTree.gridx = 3;
		gbc_btnVSpecTree.gridy = 0;
		pnLoad.add(btnVSpecTree, gbc_btnVSpecTree);
		this.frame.getContentPane().add(this.pnControl, "South");
		GridBagLayout gbl_pnControl = new GridBagLayout();
		gbl_pnControl.columnWidths = new int[] { 191, 101, 81 };
		gbl_pnControl.rowHeights = new int[] { 25 };
		gbl_pnControl.columnWeights = new double[] { 0.0D, 0.0D, 0.0D, 0.0D,
				0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D,
				Double.MIN_VALUE };
		gbl_pnControl.rowWeights = new double[] { 0.0D, Double.MIN_VALUE };
		this.pnControl.setLayout(gbl_pnControl);

		JLabel lblNewLabel_1 = new JLabel(" Directory");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.fill = 1;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 1;
		pnLoad.add(lblNewLabel_1, gbc_lblNewLabel_1);

		this.txtDirectory = new JTextField();
		GridBagConstraints gbc_txtBaseModel = new GridBagConstraints();
		gbc_txtBaseModel.gridwidth = 2;
		gbc_txtBaseModel.fill = 1;
		gbc_txtBaseModel.insets = new Insets(0, 0, 5, 5);
		gbc_txtBaseModel.gridx = 1;
		gbc_txtBaseModel.gridy = 1;
		pnLoad.add(this.txtDirectory, gbc_txtBaseModel);
		this.txtDirectory.setColumns(30);
		this.txtDirectory
				.setText("/home/hnt/Desktop/output/");
		JButton btnBaseModel = new JButton("Load");
		btnBaseModel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser f = new JFileChooser();
				f.setFileSelectionMode(1);
				f.showSaveDialog(null);

				txtDirectory.setText(f.getSelectedFile().getPath());
			}
		});
		GridBagConstraints gbc_btnBaseModel = new GridBagConstraints();
		gbc_btnBaseModel.fill = 1;
		gbc_btnBaseModel.insets = new Insets(0, 0, 5, 0);
		gbc_btnBaseModel.gridx = 3;
		gbc_btnBaseModel.gridy = 1;
		pnLoad.add(btnBaseModel, gbc_btnBaseModel);

		JButton btnNewButton_4 = new JButton("Generate");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String file = txtProductModel.getText();
				new JavaGeneration(txtDirectory.getText(), file);
			}
		});
		GridBagConstraints gbc_btnNewButton_4 = new GridBagConstraints();
		gbc_btnNewButton_4.anchor = 18;
		gbc_btnNewButton_4.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_4.gridx = 5;
		gbc_btnNewButton_4.gridy = 0;
		this.pnControl.add(btnNewButton_4, gbc_btnNewButton_4);

		JButton btnNewButton_5 = new JButton("Cancel");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
			}
		});
		GridBagConstraints gbc_btnNewButton_5 = new GridBagConstraints();
		gbc_btnNewButton_5.gridwidth = 3;
		gbc_btnNewButton_5.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_5.anchor = 18;
		gbc_btnNewButton_5.gridx = 6;
		gbc_btnNewButton_5.gridy = 0;
		this.pnControl.add(btnNewButton_5, gbc_btnNewButton_5);
	}
	public static void main(String[] arg)
	  {
	    EventQueue.invokeLater(new Runnable()
	    {
	      public void run()
	      {
	        try
	        {
	          CodeGeneration window = new CodeGeneration();
	          window.frame.setVisible(true);
	        }
	        catch (Exception e)
	        {
	          e.printStackTrace();
	        }
	      }
	    });
	  }
}
