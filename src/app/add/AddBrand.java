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
	int trylang = 0;
	public AddBrand(final Brands b) {
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

		JLabel lblAddBrand = new JLabel("Add Brand");
		lblAddBrand.setForeground(Color.WHITE);
		lblAddBrand.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblAddBrand.setBounds(10, 0, 162, 23);
		contentPanel.add(lblAddBrand);

		JButton button = new JButton("X");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(JOptionPane.showConfirmDialog(contentPanel, "Are you sure you want to close ?","PROMPT",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==0){
					dispose();
				}
			}
		});
		button.setForeground(Color.WHITE);
		button.setFont(new Font("SansSerif", Font.BOLD, 11));
		button.setBorder(null);
		button.setBackground(new Color(0, 51, 255));
		button.setBounds(270, 1, 37, 22);
		contentPanel.add(button);

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

		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				trylang = 1;
				if(txtBrand.getText().equals("")){
					JOptionPane.showMessageDialog(contentPanel, "Please specify fields !");
				}else{
				TestConnection tc = new TestConnection();
				DatabaseManager dm = new DatabaseManager();
				Brands b = new Brands();
				
				b.setBrandName(txtBrand.getText());

				try {
					boolean exists = alreadyExists(txtBrand.getText());
					if(!exists){
						
					
					int rs = dm.insertBrand(tc.getConnection(), b);
					if(rs==1){
							
							txtBrand.setText("");
							updateBrandTable();
							JOptionPane.showMessageDialog(contentPanel, "Brand Added !");
					
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
				));
		scrollPane.setViewportView(tblBrand);

		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(tblBrand.getSelectedRow()==-1){
					JOptionPane.showMessageDialog(contentPanel, "Select brand to edit !");
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

		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(tblBrand.getSelectedRow()==-1){
					JOptionPane.showMessageDialog(contentPanel, "Select brand to delete !");
				}else{
					TestConnection tc = new TestConnection();
					DatabaseManager dm = new DatabaseManager();
					
					DefaultTableModel model = (DefaultTableModel)tblBrand.getModel();
					String id = model.getValueAt(tblBrand.getSelectedRow(), 0).toString();
					try {
						int rs = dm.deleteBrand(tc.getConnection(), id);
						if(rs==1){
							updateBrandTable();
							JOptionPane.showMessageDialog(contentPanel, "Successfully Deleted !");
						}
					} catch (ClassNotFoundException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
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
