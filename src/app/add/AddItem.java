package app.add;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
import app.ui.UserWindow;

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
	private JButton btnSave;
	private JButton btnCancel;
	private final JComboBox<String> cmbSize;
	private final JComboBox<String> cmbBrand;
	private final JComboBox<String> cmbStyle;
	private final JComboBox<String> cmbColor;
	private final JComboBox<String>	cmbCategory;



	public AddItem(final Inventory i) {
		addWindowFocusListener(new WindowFocusListener() {
			@Override
			public void windowGainedFocus(WindowEvent arg0) {
			}
			@Override
			public void windowLostFocus(WindowEvent arg0) {
				dispose();
			}
		});
		setUndecorated(true);
		setBackground(new Color(255, 255, 255));
		setBounds(900, 280, 327, 399);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 255, 255));
		contentPanel.setBorder(new LineBorder(new Color(51, 153, 255)));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		final JPanel Unable = new JPanel();
		Unable.setVisible(false);
		final JPanel Specify = new JPanel();
		Specify.setBorder(new LineBorder(new Color(51, 153, 255), 3));
		Specify.setBackground(new Color(255, 255, 255));
		Specify.setBounds(10, 90, 308, 134);
		contentPanel.add(Specify);

		Specify.setVisible(false);
		Specify.setLayout(null);

		JLabel lblSpecifyFields = new JLabel("Specify Field(s) !");
		lblSpecifyFields.setHorizontalAlignment(SwingConstants.CENTER);
		lblSpecifyFields.setBounds(10, 26, 288, 51);
		lblSpecifyFields.setForeground(Color.RED);
		lblSpecifyFields.setFont(new Font("SansSerif", Font.PLAIN, 18));
		Specify.add(lblSpecifyFields);

		JButton button_1 = new JButton("Ok");
		button_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Specify.setVisible(false);
				txtPrice.setEnabled(true);
				txtQuantity.setEnabled(true);
				txtShoeName.setEnabled(true);
				cmbCategory.setEnabled(true);
				cmbSize.setEnabled(true);
				cmbStyle.setEnabled(true);
				cmbBrand.setEnabled(true);
				cmbColor.setEnabled(true);
				btnSave.setEnabled(true);
				btnCancel.setEnabled(true);
			}
		});
		button_1.setForeground(Color.WHITE);
		button_1.setFont(new Font("SansSerif", Font.PLAIN, 15));
		button_1.setBorder(null);
		button_1.setBackground(new Color(51, 102, 255));
		button_1.setBounds(120, 88, 66, 35);
		Specify.add(button_1);
		Unable.setBorder(new LineBorder(new Color(51, 153, 255), 3));
		Unable.setBackground(Color.WHITE);
		Unable.setBounds(10, 90, 308, 134);
		contentPanel.add(Unable);
		Unable.setLayout(null);

		JLabel lblThe = new JLabel("The Price should greated than\r\n");
		lblThe.setHorizontalAlignment(SwingConstants.CENTER);
		lblThe.setForeground(Color.RED);
		lblThe.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblThe.setBounds(10, 11, 287, 32);
		Unable.add(lblThe);

		JLabel lblSuggestedRetailPrice = new JLabel("Suggested Retail Price !");
		lblSuggestedRetailPrice.setHorizontalAlignment(SwingConstants.CENTER);
		lblSuggestedRetailPrice.setForeground(Color.RED);
		lblSuggestedRetailPrice.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblSuggestedRetailPrice.setBounds(10, 36, 287, 32);
		Unable.add(lblSuggestedRetailPrice);

		final JPanel ExitAddItem = new JPanel();
		contentPanel.add(ExitAddItem);
		ExitAddItem.setBounds(10, 90, 308, 134);
		ExitAddItem.setVisible(false);
		ExitAddItem.setBorder(new LineBorder(new Color(51, 153, 255), 3));
		ExitAddItem.setBackground(new Color(255, 255, 255));
		ExitAddItem.setLayout(null);

		JLabel lblAreYouSure = new JLabel("Are you sure you want to close ?");
		lblAreYouSure.setHorizontalAlignment(SwingConstants.CENTER);
		lblAreYouSure.setForeground(new Color(255, 0, 0));
		lblAreYouSure.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblAreYouSure.setBounds(10, 29, 287, 29);
		ExitAddItem.add(lblAreYouSure);

		JButton btnOkExit = new JButton("Yes");
		btnOkExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnOkExit.setForeground(Color.WHITE);
		btnOkExit.setFont(new Font("SansSerif", Font.PLAIN, 15));
		btnOkExit.setBorder(null);
		btnOkExit.setBackground(new Color(51, 102, 255));
		btnOkExit.setBounds(82, 88, 66, 35);
		ExitAddItem.add(btnOkExit);

		JButton btnNo = new JButton("No");
		btnNo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ExitAddItem.setVisible(false);
				txtPrice.setEnabled(true);
				txtQuantity.setEnabled(true);
				txtShoeName.setEnabled(true);
				cmbCategory.setEnabled(true);
				cmbSize.setEnabled(true);
				cmbStyle.setEnabled(true);
				cmbBrand.setEnabled(true);
				cmbColor.setEnabled(true);
				btnSave.setEnabled(true);
				btnCancel.setEnabled(true);
			}
		});
		btnNo.setForeground(Color.WHITE);
		btnNo.setFont(new Font("SansSerif", Font.PLAIN, 15));
		btnNo.setBorder(null);
		btnNo.setBackground(new Color(51, 102, 255));
		btnNo.setBounds(158, 88, 66, 35);
		ExitAddItem.add(btnNo);

		final JPanel SuccessAdd = new JPanel();
		contentPanel.add(SuccessAdd);
		SuccessAdd.setBounds(0, 0, 327, 1);
		SuccessAdd.setVisible(false);
		SuccessAdd.setBackground(new Color(255, 255, 255));
		SuccessAdd.setBorder(new LineBorder(new Color(51, 153, 255), 3));
		SuccessAdd.setLayout(null);

		JLabel lblSuccesfullyAdded = new JLabel("Succesfully Added !");
		SuccessAdd.setBounds(10, 90, 308, 134);
		lblSuccesfullyAdded.setHorizontalAlignment(SwingConstants.CENTER);
		lblSuccesfullyAdded.setForeground(Color.GREEN);
		lblSuccesfullyAdded.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblSuccesfullyAdded.setBounds(10, 29, 287, 29);
		SuccessAdd.add(lblSuccesfullyAdded);

		JButton btnOKSuccess = new JButton("OK");
		btnOKSuccess.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SuccessAdd.setVisible(false);
				btnSave.setEnabled(true);
				btnCancel.setEnabled(true);
				txtPrice.setEnabled(true);
				txtQuantity.setEnabled(true);
				txtShoeName.setEnabled(true);
				cmbCategory.setEnabled(true);
				cmbSize.setEnabled(true);
				cmbStyle.setEnabled(true);
				cmbBrand.setEnabled(true);
				cmbColor.setEnabled(true);
				dispose();

			}
		});
		btnOKSuccess.setForeground(Color.WHITE);
		btnOKSuccess.setFont(new Font("SansSerif", Font.PLAIN, 15));
		btnOKSuccess.setBorder(null);
		btnOKSuccess.setBackground(new Color(51, 102, 255));
		btnOKSuccess.setBounds(108, 88, 89, 35);
		SuccessAdd.add(btnOKSuccess);

		cmbSize = new JComboBox();
		cmbSize.setOpaque(false);
		cmbSize.setModel(new DefaultComboBoxModel(new String[] {"7", "7 1/2", "8", "8 1/2", "9", "9 1/2", "10", "11", "12", "Small", "Medium", "Large"}));
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

		cmbStyle = new JComboBox();
		cmbStyle.setOpaque(false);
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

		cmbCategory = new JComboBox();
		cmbCategory.setOpaque(false);
		cmbCategory.setModel(new DefaultComboBoxModel(new String[] {"Shoes", "Cap", "Socks"}));
		cmbCategory.setForeground(SystemColor.textHighlight);
		cmbCategory.setFont(new Font("SansSerif", Font.PLAIN, 15));
		cmbCategory.setBorder(new LineBorder(new Color(51, 153, 255)));
		cmbCategory.setBackground(Color.WHITE);
		cmbCategory.setBounds(109, 194, 208, 29);
		contentPanel.add(cmbCategory);

		cmbBrand = new JComboBox();
		cmbBrand.setOpaque(false);

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

		cmbColor = new JComboBox();
		cmbColor.setOpaque(false);
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

		JButton btnOk = new JButton("Ok");
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Unable.setVisible(false);
				btnSave.setEnabled(true);
				btnCancel.setEnabled(true);
				txtPrice.setEnabled(true);
				txtQuantity.setEnabled(true);
				txtShoeName.setEnabled(true);
				cmbCategory.setEnabled(true);
				cmbSize.setEnabled(true);
				cmbStyle.setEnabled(true);
				cmbBrand.setEnabled(true);
				cmbColor.setEnabled(true);
			}
		});
		btnOk.setForeground(Color.WHITE);
		btnOk.setFont(new Font("SansSerif", Font.PLAIN, 15));
		btnOk.setBorder(null);
		btnOk.setBackground(new Color(51, 102, 255));
		btnOk.setBounds(116, 88, 66, 35);
		Unable.add(btnOk);

		JLabel label_6 = new JLabel("Size");
		label_6.setForeground(SystemColor.textHighlight);
		label_6.setFont(new Font("SansSerif", Font.PLAIN, 20));
		label_6.setBounds(10, 234, 105, 29);
		contentPanel.add(label_6);

		JLabel label_1 = new JLabel("Add Shoe");
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("SansSerif", Font.PLAIN, 15));
		label_1.setBounds(10, 0, 162, 23);
		contentPanel.add(label_1);

		JLabel label = new JLabel("");

		JButton button = new JButton("X");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ExitAddItem.setVisible(true);
				txtPrice.setEnabled(false);
				txtQuantity.setEnabled(false);
				txtShoeName.setEnabled(false);
				cmbCategory.setEnabled(false);
				cmbSize.setEnabled(false);
				cmbStyle.setEnabled(false);
				cmbBrand.setEnabled(false);
				cmbColor.setEnabled(false);
				btnSave.setEnabled(false);
				btnCancel.setEnabled(false);
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
		txtShoeName.setOpaque(false);
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

		txtQuantity = new JTextField();
		txtQuantity.setOpaque(false);
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
		txtPrice.setOpaque(false);
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

		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(txtShoeName.getText().equals("")||txtQuantity.getText().equals("")||txtPrice.getText().equals("")){
					Specify.setVisible(true);
					txtPrice.setEnabled(false);
					txtQuantity.setEnabled(false);
					txtShoeName.setEnabled(false);
					cmbCategory.setEnabled(false);
					cmbSize.setEnabled(false);
					cmbStyle.setEnabled(false);
					cmbBrand.setEnabled(false);
					cmbColor.setEnabled(false);
					btnSave.setEnabled(false);
					btnCancel.setEnabled(false);
					ExitAddItem.setVisible(false);
				}else{
					ExitAddItem.setVisible(true);
					txtPrice.setEnabled(false);
					txtQuantity.setEnabled(false);
					txtShoeName.setEnabled(false);
					cmbCategory.setEnabled(false);
					cmbSize.setEnabled(false);
					cmbStyle.setEnabled(false);
					cmbBrand.setEnabled(false);
					cmbColor.setEnabled(false);
					ExitAddItem.setVisible(false);
					Specify.setVisible(false);
					btnSave.setEnabled(false);
					btnCancel.setEnabled(false);

					tc = new TestConnection();
					dm = new DatabaseManager();
					Inventory i = new Inventory();
					String categoryCode = "";
					String styleCode = "";
					int price = Integer.parseInt(txtPrice.getText());


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
					i.setQuantityAvailable(Integer.parseInt(txtQuantity.getText()));
					i.setPrice(Integer.parseInt(txtPrice.getText().toString()));

				

					try {
						boolean exists = alreadyExists(txtShoeName.getText().toString(), cmbBrand.getSelectedItem().toString(), cmbColor.getSelectedItem().toString(),cmbStyle.getSelectedItem().toString(),cmbCategory.getSelectedItem().toString(),cmbSize.getSelectedItem().toString());

						if (!exists) {
							
							int rs = dm.insertInventory(tc.getConnection(), i);
							if(rs==1){
								clearText();
								UserWindow su = new UserWindow();
								su.updateItemTable();
								su.frmUserWindow.requestFocusInWindow();
								SuccessAdd.setVisible(true);

							}
							
						} else {
							JOptionPane.showMessageDialog(contentPanel, "This item Already Exist ! ");
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
		btnSave.setBounds(70, 353, 89, 35);
		contentPanel.add(btnSave);

		btnCancel = new JButton("Cancel");
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
		btnCancel.setBounds(169, 353, 89, 35);
		contentPanel.add(btnCancel);


	}
	public void clearText(){
		txtPrice.setText("");
		txtQuantity.setText("");
		txtShoeName.setText("");
	}

	// Check whether a product already exists
	public static boolean alreadyExists(String name,String brand, String color,String style,String category,String size){
		TestConnection tc = new TestConnection();

		boolean exists = false;

		try {
			String query = "SELECT count(*) AS itemCount FROM inventory_items WHERE ItemName = '"+name+"' AND ItemBrand = '"+brand+"' AND ItemColor = '"+color+"' AND"
					+ " ItemStyle = '"+style+"' AND ItemCategory = '"+category+"' AND ItemSize = '"+size+"'";
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
