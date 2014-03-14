package app.edit;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Color;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.SwingConstants;

import app.db.DatabaseManager;
import app.db.TestConnection;
import app.model.Inventory;
import app.ui.SuperUserWindow;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;

import java.awt.SystemColor;

import javax.swing.JComboBox;

import javax.swing.DefaultComboBoxModel;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowFocusListener;

public class EditItem extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCode;
	private JTextField txtName;
	private JTextField txtQuantity;
	private JTextField txtPrice;
	private JComboBox<?> cmbBrand;
	private JComboBox<?> cmbSize;
	private JComboBox<?> cmbColor;


	public EditItem(final Inventory i) {
		
		setUndecorated(true);
		setBackground(new Color(51, 51, 255));
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				txtCode.setText(i.getItemCode());
				txtName.setText(i.getItemName());
				txtQuantity.setText(Integer.toString(i.getQuantityAvailable()));
				txtPrice.setText(Integer.toString(i.getPrice()));
				cmbBrand.setSelectedItem(i.getItemBrand());
				cmbColor.setSelectedItem(i.getItemColor());
				cmbSize.setSelectedItem(i.getSize());

			}
		});
		setBounds(900, 320, 327, 380);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 255, 255));
		contentPanel.setBorder(new LineBorder(new Color(51, 153, 255)));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel label_1 = new JLabel("Edit Shoe");
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("SansSerif", Font.PLAIN, 15));
		label_1.setBounds(10, 0, 162, 23);
		contentPanel.add(label_1);

		JLabel label = new JLabel("");

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
		button.setBounds(290, 0, 37, 22);
		contentPanel.add(button);
		label.setIcon(new ImageIcon(EditItem.class.getResource("/app/image/title.png")));
		label.setForeground(Color.WHITE);
		label.setFont(new Font("SansSerif", Font.PLAIN, 15));
		label.setBounds(0, 0, 327, 23);
		contentPanel.add(label);

		JLabel label_2 = new JLabel("Code");
		label_2.setForeground(SystemColor.textHighlight);
		label_2.setFont(new Font("SansSerif", Font.PLAIN, 20));
		label_2.setBounds(10, 34, 105, 29);
		contentPanel.add(label_2);

		txtCode = new JTextField();
		txtCode.setOpaque(false);
		txtCode.setHorizontalAlignment(SwingConstants.CENTER);
		txtCode.setForeground(Color.BLACK);
		txtCode.setFont(new Font("SansSerif", Font.PLAIN, 15));
		txtCode.setEditable(false);
		txtCode.setColumns(10);
		txtCode.setBorder(new LineBorder(new Color(51, 153, 255)));
		txtCode.setBounds(108, 34, 209, 29);
		contentPanel.add(txtCode);

		JLabel label_3 = new JLabel("Name");
		label_3.setForeground(SystemColor.textHighlight);
		label_3.setFont(new Font("SansSerif", Font.PLAIN, 20));
		label_3.setBounds(10, 74, 105, 29);
		contentPanel.add(label_3);

		txtName = new JTextField();
		txtName.setHorizontalAlignment(SwingConstants.CENTER);
		txtName.setForeground(Color.BLACK);
		txtName.setFont(new Font("SansSerif", Font.PLAIN, 15));
		txtName.setColumns(10);
		txtName.setBorder(new LineBorder(new Color(51, 153, 255)));
		txtName.setBounds(108, 74, 209, 29);
		contentPanel.add(txtName);

		JLabel label_4 = new JLabel("Brand");
		label_4.setForeground(SystemColor.textHighlight);
		label_4.setFont(new Font("SansSerif", Font.PLAIN, 20));
		label_4.setBounds(10, 114, 105, 29);
		contentPanel.add(label_4);

		cmbBrand = new JComboBox();
		cmbBrand.setModel(new DefaultComboBoxModel(new String[] {"Nike", "Adidas", "Jordan", "Under Armor"}));
		cmbBrand.setForeground(SystemColor.textHighlight);
		cmbBrand.setFont(new Font("SansSerif", Font.PLAIN, 15));
		cmbBrand.setBorder(new LineBorder(new Color(51, 153, 255)));
		cmbBrand.setBackground(Color.WHITE);
		cmbBrand.setBounds(109, 114, 208, 29);
		contentPanel.add(cmbBrand);

		JLabel label_5 = new JLabel("Color");
		label_5.setForeground(SystemColor.textHighlight);
		label_5.setFont(new Font("SansSerif", Font.PLAIN, 20));
		label_5.setBounds(10, 154, 105, 29);
		contentPanel.add(label_5);

		cmbColor = new JComboBox();
		cmbColor.setModel(new DefaultComboBoxModel(new String[] {"Red", "Blue", "Green", "White", "Black", "Gray", "Pink", "Yellow", "Orange"}));
		cmbColor.setForeground(SystemColor.textHighlight);
		cmbColor.setFont(new Font("SansSerif", Font.PLAIN, 15));
		cmbColor.setBorder(new LineBorder(new Color(51, 153, 255)));
		cmbColor.setBackground(Color.WHITE);
		cmbColor.setBounds(109, 154, 208, 29);
		contentPanel.add(cmbColor);

		JLabel label_6 = new JLabel("Size");
		label_6.setForeground(SystemColor.textHighlight);
		label_6.setFont(new Font("SansSerif", Font.PLAIN, 20));
		label_6.setBounds(10, 194, 105, 29);
		contentPanel.add(label_6);

		cmbSize = new JComboBox();
		cmbSize.setModel(new DefaultComboBoxModel(new String[] {"5", "6", "6 1/2", "7", "7 1/2", "8", "8 1/2", "9", "9 1/2", "10", "11", "12"}));
		cmbSize.setForeground(SystemColor.textHighlight);
		cmbSize.setFont(new Font("SansSerif", Font.PLAIN, 15));
		cmbSize.setBorder(new LineBorder(new Color(51, 153, 255)));
		cmbSize.setBackground(Color.WHITE);
		cmbSize.setBounds(109, 194, 208, 29);
		contentPanel.add(cmbSize);

		JLabel label_7 = new JLabel("Quantity");
		label_7.setForeground(SystemColor.textHighlight);
		label_7.setFont(new Font("SansSerif", Font.PLAIN, 20));
		label_7.setBounds(9, 234, 105, 29);
		contentPanel.add(label_7);

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
		txtQuantity.setBounds(108, 234, 209, 29);
		contentPanel.add(txtQuantity);

		JLabel label_8 = new JLabel("Price");
		label_8.setForeground(SystemColor.textHighlight);
		label_8.setFont(new Font("SansSerif", Font.PLAIN, 20));
		label_8.setBounds(9, 274, 105, 29);
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
		txtPrice.setBounds(108, 274, 209, 29);
		contentPanel.add(txtPrice);

		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String brandName = "";
				String size = "";
				String color = "";
				TestConnection tc = new TestConnection();
				DatabaseManager dm = new DatabaseManager();

				i.setItemCode(txtCode.getText());
				i.setItemName(txtName.getText());
				i.setQuantityAvailable(Integer.parseInt(txtQuantity.getText()));
				i.setPrice(Integer.parseInt(txtPrice.getText()));
				if(cmbBrand.getSelectedItem().equals("Nike")){
					brandName = "Nike";
				}else if(cmbBrand.getSelectedItem().equals("Adidas")){
					brandName = "Adidas";
				}else if(cmbBrand.getSelectedItem().equals("Jordan")){
					brandName = "Jordan";
				}else if(cmbBrand.getSelectedItem().equals("Under Armor")){
					brandName = "Under Armor";
				}
				i.setItemBrand(brandName);
				if(cmbSize.getSelectedItem().equals("5")){
					size="5";

				}else if(cmbSize.getSelectedItem().equals("6")){
					size="6";
				}else if(cmbSize.getSelectedItem().equals("7")){
					size="7";
				}else if(cmbSize.getSelectedItem().equals("8")){
					size="8";
				}else if(cmbSize.getSelectedItem().equals("9")){
					size="9";
				}else if(cmbSize.getSelectedItem().equals("10")){
					size="10";
				}else if(cmbSize.getSelectedItem().equals("11")){
					size="11";
				}else if(cmbSize.getSelectedItem().equals("12")){
					size="12";
				}else if(cmbSize.getSelectedItem().equals("5 1/2")){
					size="5 1/2";
				}else if(cmbSize.getSelectedItem().equals("6 1/2")){
					size="6-1/2";
				}else if(cmbSize.getSelectedItem().equals("7 1/2")){
					size="7 1/2";
				}else if(cmbSize.getSelectedItem().equals("8 1/2")){
					size="8 1/2";
				}else if(cmbSize.getSelectedItem().equals("9 1/2")){
					size="9 1/2";

				}
				i.setSize(size);

				if(cmbColor.getSelectedItem().equals("Red")){
					color = "Red";
				}else if(cmbColor.getSelectedItem().equals("Blue")){
					color = "Blue";
				}else if(cmbColor.getSelectedItem().equals("Green")){
					color = "Green";
				}else if(cmbColor.getSelectedItem().equals("White")){
					color = "White";
				}else if(cmbColor.getSelectedItem().equals("Black")){
					color = "Black";
				}else if(cmbColor.getSelectedItem().equals("Gray")){
					color = "Gray";
				}else if(cmbColor.getSelectedItem().equals("Pink")){
					color = "Pink";
				}else if(cmbColor.getSelectedItem().equals("Yellow")){
					color = "Yellow";
				}else if(cmbColor.getSelectedItem().equals("Orange")){
					color = "Orange";
				}
				i.setItemColor(color);

				try {
					boolean exists = alreadyExists(txtName.getText().toString(), cmbBrand.getSelectedItem().toString(), cmbColor.getSelectedItem().toString(),cmbSize.getSelectedItem().toString(),txtQuantity.getText(),txtPrice.getText());
					if(!exists){
						int rs = dm.updateItem(tc.getConnection(), i);
						if(rs==1){

							SuperUserWindow su = new SuperUserWindow();
							su.frmSuperUser.requestFocusInWindow();
							JOptionPane.showMessageDialog(contentPanel, "Successfully Edited !");
							dispose();

						}

					}else{
						JOptionPane.showMessageDialog(contentPanel, "Nothing Change ! or\nAlready Exists ! ");
					}
				} catch (ClassNotFoundException | SQLException e) {

					e.printStackTrace();
				}
			}
		});
		btnSave.setForeground(Color.WHITE);
		btnSave.setFont(new Font("SansSerif", Font.PLAIN, 15));
		btnSave.setBorder(null);
		btnSave.setBackground(new Color(51, 102, 255));
		btnSave.setBounds(10, 334, 89, 35);
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
		btnCancel.setBounds(109, 334, 89, 35);
		contentPanel.add(btnCancel);
	}
	
	public static boolean alreadyExists(String name,String brand, String color,String size, String quantity,String price){
		TestConnection tc = new TestConnection();


		boolean exists = false;

		try {
			String query = "SELECT count(*) AS itemCount FROM inventory_items WHERE ItemName = '"+name+"' AND ItemBrand = '"+brand+"' AND ItemColor = '"+color+"' AND ItemSize = '"+size+"' AND ItemQuantity = '"+quantity+"' AND Price = '"+price+"';";
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
