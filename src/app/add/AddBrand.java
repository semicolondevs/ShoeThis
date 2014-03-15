package app.add;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.Color;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;

import java.awt.SystemColor;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import app.db.DatabaseManager;
import app.db.TestConnection;
import app.edit.EditBrand;
import app.model.Brands;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

public class AddBrand extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtBrand;
	private JTable tblBrand;
	private JButton btnX;
	private JButton btnSave;
	private JButton btnEdit;
	private JButton btnDelete;
	
	public AddBrand(final Brands b) {
		addWindowFocusListener(new WindowFocusListener() {
			public void windowGainedFocus(WindowEvent arg0) {
				updateBrandTable();
			}
			public void windowLostFocus(WindowEvent arg0) {
				dispose();
			}
		});
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				updateBrandTable();
			}
		});
		setUndecorated(true);
		setBounds(900, 320, 307, 363);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new LineBorder(new Color(51, 153, 255)));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		final JPanel pnlSuccessAdd = new JPanel();
		pnlSuccessAdd.setBackground(new Color(255, 255, 255));
		pnlSuccessAdd.setVisible(false);
		
		final JPanel pnlExit = new JPanel();
		pnlExit.setVisible(false);
		
		final JPanel pnlSelect = new JPanel();
		pnlSelect.setVisible(false);
		
		final JPanel pnlDelete = new JPanel();
		pnlDelete.setVisible(false);
		
		final JPanel pnlSuccessDelete = new JPanel();
		pnlSuccessDelete.setVisible(false);
		pnlSuccessDelete.setBorder(new LineBorder(new Color(51, 153, 255), 3));
		pnlSuccessDelete.setBackground(new Color(255, 255, 255));
		pnlSuccessDelete.setBounds(10, 141, 286, 111);
		contentPanel.add(pnlSuccessDelete);
		pnlSuccessDelete.setLayout(null);
		
		JLabel lblSuccesfullyDeleted = new JLabel("Succesfully Deleted !");
		lblSuccesfullyDeleted.setHorizontalAlignment(SwingConstants.CENTER);
		lblSuccesfullyDeleted.setForeground(Color.GREEN);
		lblSuccesfullyDeleted.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblSuccesfullyDeleted.setBounds(0, 11, 287, 29);
		pnlSuccessDelete.add(lblSuccesfullyDeleted);
		
		JButton button = new JButton("OK");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlSuccessDelete.setVisible(false);
			}
		});
		button.setForeground(Color.WHITE);
		button.setFont(new Font("SansSerif", Font.PLAIN, 15));
		button.setBorder(null);
		button.setBackground(new Color(51, 102, 255));
		button.setBounds(98, 65, 89, 35);
		pnlSuccessDelete.add(button);
		pnlDelete.setBorder(new LineBorder(new Color(51, 153, 255), 3));
		pnlDelete.setBackground(new Color(255, 255, 255));
		pnlDelete.setBounds(10, 141, 286, 111);
		contentPanel.add(pnlDelete);
		pnlDelete.setLayout(null);
		
		JLabel lblAreYouSure = new JLabel("Are you sure you want to Delete ?");
		lblAreYouSure.setHorizontalAlignment(SwingConstants.CENTER);
		lblAreYouSure.setForeground(Color.RED);
		lblAreYouSure.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblAreYouSure.setBounds(0, 11, 287, 29);
		pnlDelete.add(lblAreYouSure);
		
		JButton btnYesDelete = new JButton("Yes");
		btnYesDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TestConnection tc = new TestConnection();
				DatabaseManager dm = new DatabaseManager();
				
				DefaultTableModel model = (DefaultTableModel)tblBrand.getModel();
				try{
				String id = model.getValueAt(tblBrand.getSelectedRow(), 0).toString();
				
				try {
					int rs = dm.deleteBrand(tc.getConnection(), id);
					if(rs==1){
						updateBrandTable();
						pnlSuccessDelete.setVisible(true);
						pnlDelete.setVisible(false);
					}
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}catch(ArrayIndexOutOfBoundsException aio){
					pnlSelect.setVisible(true);
					txtBrand.setEnabled(false);
					btnDelete.setEnabled(false);
					btnEdit.setEnabled(false);
					btnSave.setEnabled(false);
					tblBrand.setEnabled(false);
					pnlDelete.setVisible(false);
				}
			}
		});
		btnYesDelete.setForeground(Color.WHITE);
		btnYesDelete.setFont(new Font("SansSerif", Font.PLAIN, 15));
		btnYesDelete.setBorder(null);
		btnYesDelete.setBackground(new Color(51, 102, 255));
		btnYesDelete.setBounds(70, 65, 66, 35);
		pnlDelete.add(btnYesDelete);
		
		JButton btnNoDelete = new JButton("No");
		btnNoDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlDelete.setVisible(false);
			}
		});
		btnNoDelete.setForeground(Color.WHITE);
		btnNoDelete.setFont(new Font("SansSerif", Font.PLAIN, 15));
		btnNoDelete.setBorder(null);
		btnNoDelete.setBackground(new Color(51, 102, 255));
		btnNoDelete.setBounds(146, 65, 66, 35);
		pnlDelete.add(btnNoDelete);
		pnlSelect.setBorder(new LineBorder(new Color(51, 153, 255), 3));
		pnlSelect.setBackground(new Color(255, 255, 255));
		pnlSelect.setBounds(10, 141, 286, 111);
		contentPanel.add(pnlSelect);
		pnlSelect.setLayout(null);
		
		JLabel lblSelectItemTo = new JLabel("Please Select Item !");
		lblSelectItemTo.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelectItemTo.setForeground(Color.RED);
		lblSelectItemTo.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblSelectItemTo.setBounds(0, 11, 287, 29);
		pnlSelect.add(lblSelectItemTo);
		
		JButton btnOkEdit = new JButton("OK");
		btnOkEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlSelect.setVisible(false);
				txtBrand.setEnabled(true);
				btnDelete.setEnabled(true);
				btnEdit.setEnabled(true);
				btnSave.setEnabled(true);
				tblBrand.setEnabled(true);
			}
		});
		btnOkEdit.setForeground(Color.WHITE);
		btnOkEdit.setFont(new Font("SansSerif", Font.PLAIN, 15));
		btnOkEdit.setBorder(null);
		btnOkEdit.setBackground(new Color(51, 102, 255));
		btnOkEdit.setBounds(97, 65, 89, 35);
		pnlSelect.add(btnOkEdit);
		pnlExit.setBackground(new Color(255, 255, 255));
		pnlExit.setBorder(new LineBorder(new Color(51, 153, 255), 3));
		pnlExit.setBounds(10, 141, 286, 111);
		contentPanel.add(pnlExit);
		pnlExit.setLayout(null);
		
		JLabel label_3 = new JLabel("Are you sure you want to close ?");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setForeground(Color.RED);
		label_3.setFont(new Font("SansSerif", Font.PLAIN, 18));
		label_3.setBounds(0, 11, 287, 29);
		pnlExit.add(label_3);
		
		JButton btnYesExit = new JButton("Yes");
		btnYesExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnYesExit.setForeground(Color.WHITE);
		btnYesExit.setFont(new Font("SansSerif", Font.PLAIN, 15));
		btnYesExit.setBorder(null);
		btnYesExit.setBackground(new Color(51, 102, 255));
		btnYesExit.setBounds(72, 65, 66, 35);
		pnlExit.add(btnYesExit);
		
		JButton btnExitNo = new JButton("No");
		btnExitNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlExit.setVisible(false);
				txtBrand.setEnabled(true);
				btnDelete.setEnabled(true);
				btnEdit.setEnabled(true);
				btnSave.setEnabled(true);
				tblBrand.setEnabled(true);
			}
		});
		btnExitNo.setForeground(Color.WHITE);
		btnExitNo.setFont(new Font("SansSerif", Font.PLAIN, 15));
		btnExitNo.setBorder(null);
		btnExitNo.setBackground(new Color(51, 102, 255));
		btnExitNo.setBounds(148, 65, 66, 35);
		pnlExit.add(btnExitNo);
		pnlSuccessAdd.setBorder(new LineBorder(new Color(51, 153, 255), 3));
		pnlSuccessAdd.setBounds(10, 141, 286, 111);
		contentPanel.add(pnlSuccessAdd);
		pnlSuccessAdd.setLayout(null);
		
		JLabel label_2 = new JLabel("Succesfully Added !");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setForeground(Color.GREEN);
		label_2.setFont(new Font("SansSerif", Font.PLAIN, 20));
		label_2.setBounds(0, 11, 287, 29);
		pnlSuccessAdd.add(label_2);
		
		JButton btnSuccesOk = new JButton("OK");
		btnSuccesOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pnlSuccessAdd.setVisible(false);
				txtBrand.setEnabled(true);
				btnDelete.setEnabled(true);
				btnEdit.setEnabled(true);
				btnSave.setEnabled(true);
				tblBrand.setEnabled(true);
			}
		});
		btnSuccesOk.setForeground(Color.WHITE);
		btnSuccesOk.setFont(new Font("SansSerif", Font.PLAIN, 15));
		btnSuccesOk.setBorder(null);
		btnSuccesOk.setBackground(new Color(51, 102, 255));
		btnSuccesOk.setBounds(98, 65, 89, 35);
		pnlSuccessAdd.add(btnSuccesOk);

		JLabel lblAddBrand = new JLabel("Add Brand");
		lblAddBrand.setForeground(Color.WHITE);
		lblAddBrand.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblAddBrand.setBounds(10, 0, 162, 23);
		contentPanel.add(lblAddBrand);

		btnX = new JButton("X");
		btnX.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				pnlExit.setVisible(true);
				txtBrand.setEnabled(false);
				btnDelete.setEnabled(false);
				btnEdit.setEnabled(false);
				btnSave.setEnabled(false);
				tblBrand.setEnabled(false);
			}
		});
		btnX.setForeground(Color.WHITE);
		btnX.setFont(new Font("SansSerif", Font.BOLD, 11));
		btnX.setBorder(null);
		btnX.setBackground(new Color(0, 51, 255));
		btnX.setBounds(270, 1, 37, 22);
		contentPanel.add(btnX);

		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(AddBrand.class.getResource("/app/image/title.png")));
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("SansSerif", Font.PLAIN, 15));
		label_1.setBounds(0, 0, 450, 23);
		contentPanel.add(label_1);

		JLabel label = new JLabel("Brand");
		label.setForeground(SystemColor.textHighlight);
		label.setFont(new Font("SansSerif", Font.PLAIN, 20));
		label.setBounds(123, 34, 105, 29);
		contentPanel.add(label);

		txtBrand = new JTextField();
		txtBrand.setHorizontalAlignment(SwingConstants.CENTER);
		txtBrand.setForeground(Color.BLACK);
		txtBrand.setFont(new Font("SansSerif", Font.PLAIN, 15));
		txtBrand.setColumns(10);
		txtBrand.setBorder(new LineBorder(new Color(51, 153, 255)));
		txtBrand.setBounds(44, 59, 209, 29);
		contentPanel.add(txtBrand);

		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(txtBrand.getText().equals("")){
					JOptionPane.showMessageDialog(contentPanel, "Please specify fields !");
				}else{
				TestConnection tc = new TestConnection();
				DatabaseManager dm = new DatabaseManager();
				Brands b = new Brands();
				
				txtBrand.setEnabled(false);
				btnDelete.setEnabled(false);
				btnEdit.setEnabled(false);
				btnSave.setEnabled(false);
				
				b.setBrandName(txtBrand.getText());

				try {
					boolean exists = alreadyExists(txtBrand.getText());
					if(!exists){
					int rs = dm.insertBrand(tc.getConnection(), b);
					if(rs==1){
							
							txtBrand.setText("");
							updateBrandTable();
							pnlSuccessAdd.setVisible(true);
					
					}
					}else{
						JOptionPane.showMessageDialog(contentPanel, "Brand Already Exists !");
					}
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				}
			}
		});
		btnSave.setForeground(Color.WHITE);
		btnSave.setFont(new Font("SansSerif", Font.PLAIN, 15));
		btnSave.setBorder(null);
		btnSave.setBackground(new Color(51, 102, 255));
		btnSave.setBounds(10, 99, 89, 35);
		contentPanel.add(btnSave);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 151, 286, 201);
		contentPanel.add(scrollPane);

		tblBrand = new JTable();
		tblBrand.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Brand Id", "Brands"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(tblBrand);

		btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(tblBrand.getSelectedRow()==-1){
					pnlSelect.setVisible(true);
					txtBrand.setEnabled(false);
					btnDelete.setEnabled(false);
					btnEdit.setEnabled(false);
					btnSave.setEnabled(false);
					tblBrand.setEnabled(false);
				}else{
					try{
					EditBrand eb = new EditBrand(getSelectedBrand());
					eb.setVisible(true);
					eb.setLocationRelativeTo(null);
					eb.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
					eb.setAlwaysOnTop(true);
					contentPanel.setFocusable(false);
					}catch(ArrayIndexOutOfBoundsException aio){
						JOptionPane.showMessageDialog(contentPanel, "Select brand to edit !");	
					}
				}
			}
		});
		btnEdit.setForeground(Color.WHITE);
		btnEdit.setFont(new Font("SansSerif", Font.PLAIN, 15));
		btnEdit.setBorder(null);
		btnEdit.setBackground(new Color(51, 102, 255));
		btnEdit.setBounds(109, 99, 89, 35);
		contentPanel.add(btnEdit);

		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(tblBrand.getSelectedRow()==-1){
					pnlSelect.setVisible(true);
					txtBrand.setEnabled(false);
					btnDelete.setEnabled(false);
					btnEdit.setEnabled(false);
					btnSave.setEnabled(false);
					tblBrand.setEnabled(false);
				}else{
					pnlDelete.setVisible(true);
				}
			}
		});
		btnDelete.setForeground(Color.WHITE);
		btnDelete.setFont(new Font("SansSerif", Font.PLAIN, 15));
		btnDelete.setBorder(null);
		btnDelete.setBackground(new Color(51, 102, 255));
		btnDelete.setBounds(207, 99, 89, 35);
		contentPanel.add(btnDelete);
	}
	public void updateBrandTable(){
		TestConnection tc = new TestConnection();
		DatabaseManager dm = new DatabaseManager();
		DefaultTableModel modelBrand = (DefaultTableModel)tblBrand.getModel();
		modelBrand.getDataVector().removeAllElements();
		tblBrand.updateUI();
		try {
			ResultSet rs = dm.brands(tc.getConnection());

			while(rs.next()){
				String brandId = rs.getString("brandId");
				String brandName = rs.getString("brandName");
				modelBrand.addRow(new Object[]{brandId,brandName});
			}
			rs.close();
			tc.getConnection().close();
			tblBrand.updateUI();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
	public Brands getSelectedBrand(){
		Brands b = new Brands();
		DefaultTableModel modelBrand = (DefaultTableModel)tblBrand.getModel();
		b.setBrandId(Integer.parseInt(modelBrand.getValueAt(tblBrand.getSelectedRow(), 0).toString()));
		b.setBrandName(modelBrand.getValueAt(tblBrand.getSelectedRow(), 1).toString());
		return b;
	}
	public static boolean alreadyExists(String brandName){
		TestConnection tc = new TestConnection();
		boolean exists = false;
		
		try {
			String query = "SELECT count(*) AS brandCount FROM brands WHERE brandName = '"+brandName+"'";
			Statement stmt = tc.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			if (rs.next()) {
				if (Integer.parseInt(rs.getString("brandCount")) > 0 ) {
					exists = true;
				} else {
					exists = false;
				}
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return exists;
	}
}
