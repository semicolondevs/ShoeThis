package app.add;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import app.db.DatabaseManager;
import app.db.TestConnection;
import app.model.Inventory;
import app.ui.SuperUserWindow;

import java.awt.event.WindowFocusListener;
import java.awt.event.WindowEvent;

public class AddItem extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtShoeName;
	private JTextField txtQuantity;
	private JTextField txtPrice;
	private DateFormat dateFormat = new SimpleDateFormat("SSSSSYYYY");
	private TestConnection tc = new TestConnection();
	private DatabaseManager dm = new DatabaseManager();

	
	public AddItem(final Inventory i) {
		
		setUndecorated(true);
		setBackground(new Color(51, 51, 255));
		setBounds(900, 280, 327, 460);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 255, 255));
		contentPanel.setBorder(new LineBorder(new Color(51, 153, 255)));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel label_1 = new JLabel("Add Shoe");
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("SansSerif", Font.PLAIN, 15));
		label_1.setBounds(10, 0, 162, 23);
		contentPanel.add(label_1);
		
		JLabel label = new JLabel("");
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		
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
		button.setBounds(290, 1, 37, 22);
		contentPanel.add(button);
		label.setIcon(new ImageIcon(AddItem.class.getResource("/app/image/title.png")));
		label.setForeground(Color.WHITE);
		label.setFont(new Font("SansSerif", Font.PLAIN, 15));
		label.setBounds(0, 0, 327, 23);
		contentPanel.add(label);
		
		JLabel label_3 = new JLabel("Name");
		label_3.setForeground(SystemColor.textHighlight);
		label_3.setFont(new Font("SansSerif", Font.PLAIN, 20));
		label_3.setBounds(11, 34, 105, 29);
		contentPanel.add(label_3);
		
		txtShoeName = new JTextField();
		txtShoeName.setHorizontalAlignment(SwingConstants.CENTER);
		txtShoeName.setForeground(Color.BLACK);
		txtShoeName.setFont(new Font("SansSerif", Font.PLAIN, 15));
		txtShoeName.setColumns(10);
		txtShoeName.setBorder(new LineBorder(new Color(51, 153, 255)));
		txtShoeName.setBounds(109, 34, 209, 29);
		contentPanel.add(txtShoeName);
		
		JLabel label_4 = new JLabel("Brand");
		label_4.setForeground(SystemColor.textHighlight);
		label_4.setFont(new Font("SansSerif", Font.PLAIN, 20));
		label_4.setBounds(11, 74, 105, 29);
		contentPanel.add(label_4);
		
		final JComboBox<String> cmbBrand = new JComboBox();

		try {
			ResultSet rsBrand = dm.brands(tc.getConnection());
			while(rsBrand.next()){
				String getBrand = rsBrand.getString("brandName");
				cmbBrand.addItem(getBrand);
			}
		} catch (ClassNotFoundException | SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		cmbBrand.setForeground(SystemColor.textHighlight);
		cmbBrand.setFont(new Font("SansSerif", Font.PLAIN, 15));
		cmbBrand.setBorder(new LineBorder(new Color(51, 153, 255)));
		cmbBrand.setBackground(Color.WHITE);
		cmbBrand.setBounds(110, 74, 208, 29);
		contentPanel.add(cmbBrand);
		
		JLabel label_5 = new JLabel("Color");
		label_5.setForeground(SystemColor.textHighlight);
		label_5.setFont(new Font("SansSerif", Font.PLAIN, 20));
		label_5.setBounds(11, 114, 105, 29);
		contentPanel.add(label_5);
		
		final JComboBox<String> cmbColor = new JComboBox();
		try {
			ResultSet rsColor = dm.color(tc.getConnection());
			while(rsColor.next()){
				String getColor = rsColor.getString("color");
				cmbColor.addItem(getColor);
			}
		} catch (ClassNotFoundException | SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		cmbColor.setForeground(SystemColor.textHighlight);
		cmbColor.setFont(new Font("SansSerif", Font.PLAIN, 15));
		cmbColor.setBorder(new LineBorder(new Color(51, 153, 255)));
		cmbColor.setBackground(Color.WHITE);
		cmbColor.setBounds(110, 114, 208, 29);
		contentPanel.add(cmbColor);
		
		JLabel label_6 = new JLabel("Size");
		label_6.setForeground(SystemColor.textHighlight);
		label_6.setFont(new Font("SansSerif", Font.PLAIN, 20));
		label_6.setBounds(10, 234, 105, 29);
		contentPanel.add(label_6);
		
		final JComboBox<String> cmbSize = new JComboBox();
		cmbSize.setModel(new DefaultComboBoxModel(new String[] {"5", "6", "6 1/2", "7", "7 1/2", "8", "8 1/2", "9", "9 1/2", "10", "11", "12"}));
		cmbSize.setForeground(SystemColor.textHighlight);
		cmbSize.setFont(new Font("SansSerif", Font.PLAIN, 15));
		cmbSize.setBorder(new LineBorder(new Color(51, 153, 255)));
		cmbSize.setBackground(Color.WHITE);
		cmbSize.setBounds(109, 234, 208, 29);
		contentPanel.add(cmbSize);
		
		JLabel label_7 = new JLabel("Quantity");
		label_7.setForeground(SystemColor.textHighlight);
		label_7.setFont(new Font("SansSerif", Font.PLAIN, 20));
		label_7.setBounds(10, 274, 105, 29);
		contentPanel.add(label_7);
		
		JLabel lblStyle = new JLabel("Style");
		lblStyle.setForeground(SystemColor.textHighlight);
		lblStyle.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblStyle.setBounds(11, 154, 105, 29);
		contentPanel.add(lblStyle);
		
		final JComboBox<String> cmbStyle = new JComboBox();
		cmbStyle.setModel(new DefaultComboBoxModel(new String[] {"Customize", "Elite", "N/A"}));
		cmbStyle.setForeground(SystemColor.textHighlight);
		cmbStyle.setFont(new Font("SansSerif", Font.PLAIN, 15));
		cmbStyle.setBorder(new LineBorder(new Color(51, 153, 255)));
		cmbStyle.setBackground(Color.WHITE);
		cmbStyle.setBounds(109, 154, 208, 29);
		contentPanel.add(cmbStyle);
		
		JLabel lblCategory = new JLabel("Category");
		lblCategory.setForeground(SystemColor.textHighlight);
		lblCategory.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblCategory.setBounds(11, 194, 105, 29);
		contentPanel.add(lblCategory);
		
		final JComboBox<String> cmbCategory = new JComboBox();
		cmbCategory.setModel(new DefaultComboBoxModel(new String[] {"Shoes", "Cap", "Socks"}));
		cmbCategory.setForeground(SystemColor.textHighlight);
		cmbCategory.setFont(new Font("SansSerif", Font.PLAIN, 15));
		cmbCategory.setBorder(new LineBorder(new Color(51, 153, 255)));
		cmbCategory.setBackground(Color.WHITE);
		cmbCategory.setBounds(109, 194, 208, 29);
		contentPanel.add(cmbCategory);
		
		txtQuantity = new JTextField();
		txtQuantity.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent getCharQuantity) {
				char input = getCharQuantity.getKeyChar();
				if(!(Character.isDigit(input)) || (input==KeyEvent.VK_BACK_SPACE) || (input==KeyEvent.VK_DELETE)){
					getCharQuantity.consume();
				}
			}
		});
		txtQuantity.setHorizontalAlignment(SwingConstants.CENTER);
		txtQuantity.setForeground(Color.BLACK);
		txtQuantity.setFont(new Font("SansSerif", Font.PLAIN, 15));
		txtQuantity.setColumns(10);
		txtQuantity.setBorder(new LineBorder(new Color(51, 153, 255)));
		txtQuantity.setBounds(109, 274, 209, 29);
		contentPanel.add(txtQuantity);
		
		JLabel label_8 = new JLabel("Price");
		label_8.setForeground(SystemColor.textHighlight);
		label_8.setFont(new Font("SansSerif", Font.PLAIN, 20));
		label_8.setBounds(10, 314, 105, 29);
		contentPanel.add(label_8);
		
		txtPrice = new JTextField();
		txtPrice.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent getCharPrice) {
				char input = getCharPrice.getKeyChar();
				if(!(Character.isDigit(input)) || (input==KeyEvent.VK_BACK_SPACE) || (input==KeyEvent.VK_DELETE)){
					getCharPrice.consume();
				}
			}
		});
		txtPrice.setHorizontalAlignment(SwingConstants.CENTER);
		txtPrice.setForeground(Color.BLACK);
		txtPrice.setFont(new Font("SansSerif", Font.PLAIN, 15));
		txtPrice.setColumns(10);
		txtPrice.setBorder(new LineBorder(new Color(51, 153, 255)));
		txtPrice.setBounds(109, 314, 209, 29);
		contentPanel.add(txtPrice);
		
		JButton btnSave = new JButton("Save");
		final JPanel Specify = new JPanel();
		btnSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(txtShoeName.getText().equals("")||txtQuantity.getText().equals("")||txtPrice.getText().equals("")){
					Specify.setVisible(true);
					
				}else{
					
					
					Specify.setVisible(false);
					String sizeCode = "";
					tc = new TestConnection();
					dm = new DatabaseManager();
					Inventory i = new Inventory();
					String categoryCode = "";
					String styleCode = "";
					
					i.setItemName(txtShoeName.getText().toString());
					i.setItemBrand(cmbBrand.getSelectedItem());
					i.setSize(cmbSize.getSelectedItem().toString());
					i.setItemColor(cmbColor.getSelectedItem().toString());
					i.setItemStyle(cmbStyle.getSelectedItem().toString());
					
					i.setItemCategory(cmbCategory.getSelectedItem().toString());
					Date date = new Date();
					if(cmbCategory.getSelectedItem().equals("Shoes")){
						categoryCode = "SH";
					}else if(cmbCategory.getSelectedItem().equals("Socks")){
						categoryCode = "SC";
					}else{
						categoryCode = "CP";
					}
					if(cmbStyle.getSelectedItem().equals("Customize")){
						styleCode = "CM";
					}else if(cmbStyle.getSelectedItem().equals("Elite")){
						styleCode="ELT";
					}else{
						styleCode = "NA";
					}
					i.setItemCode(categoryCode+dateFormat.format(date)+"S"+styleCode);
					i.setQuantityAvailable(Integer.parseInt(txtQuantity.getText().toString()));
					i.setPrice(Integer.parseInt(txtPrice.getText().toString()));
					
					
					try {
						boolean exists = alreadyExists(txtShoeName.getText().toString(), cmbBrand.getSelectedItem().toString(), cmbColor.getSelectedItem().toString());
						
						if (!exists) {
							
							int rs = dm.insertInventory(tc.getConnection(), i);
							if(rs==1){
								clearText();
								SuperUserWindow su = new SuperUserWindow();
								su.updateItemTable();
								su.frmSuperUser.requestFocusInWindow();
								JOptionPane.showMessageDialog(contentPanel, "Successfully Added !");
								dispose();
							}
						} else {
							JOptionPane.showMessageDialog(contentPanel, "Already Exist ! ");
							clearText();
							
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
		btnSave.setBounds(10, 416, 89, 35);
		contentPanel.add(btnSave);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancel.setForeground(Color.WHITE);
		btnCancel.setFont(new Font("SansSerif", Font.PLAIN, 15));
		btnCancel.setBorder(null);
		btnCancel.setBackground(new Color(51, 102, 255));
		btnCancel.setBounds(109, 416, 89, 35);
		contentPanel.add(btnCancel);
		
		Specify.setVisible(false);
		Specify.setOpaque(false);
		Specify.setBounds(10, 354, 308, 51);
		contentPanel.add(Specify);
		Specify.setLayout(null);
		
		JLabel label_9 = new JLabel("Please specify the fields !");
		label_9.setBounds(39, 0, 204, 51);
		label_9.setForeground(Color.RED);
		label_9.setFont(new Font("SansSerif", Font.PLAIN, 18));
		Specify.add(label_9);
		
		JButton button_1 = new JButton("OK");
		button_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Specify.setVisible(false);
			}
		});
		button_1.setForeground(Color.WHITE);
		button_1.setFont(new Font("SansSerif", Font.PLAIN, 15));
		button_1.setBorder(new LineBorder(new Color(51, 153, 255), 2));
		button_1.setBackground(new Color(51, 102, 255));
		button_1.setBounds(253, 9, 46, 35);
		Specify.add(button_1);
		
		
	}
	public void clearText(){
		txtPrice.setText("");
		txtQuantity.setText("");
		txtShoeName.setText("");
	}
	
	// Check whether a product already exists
	public static boolean alreadyExists(String name,String brand, String color){
		TestConnection tc = new TestConnection();
		
		boolean exists = false;
		
		try {
			String query = "SELECT count(*) AS itemCount FROM inventory_items WHERE ItemName = '"+name+"' AND ItemBrand = '"+brand+"' AND ItemColor = '"+color+"'";
			Statement stmt = tc.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			if (rs.next()) {
				if (Integer.parseInt(rs.getString("itemCount")) > 0 ) {
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
