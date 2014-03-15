package app.add;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

import java.awt.Font;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;

import java.awt.SystemColor;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import app.db.DatabaseManager;
import app.db.TestConnection;
import app.edit.EditColor;
import app.model.Colors;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddColor extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtColor;
	private JTable tblColor;
	private JTextField txtPrompt;

	
	public AddColor() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				updateColorTable();
			}
		});
		setUndecorated(true);
		setBounds(900, 320, 307, 363);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new LineBorder(new Color(51, 153, 255)));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		final JPanel pnlPrompt = new JPanel();
		pnlPrompt.setVisible(false);
		pnlPrompt.setBorder(new LineBorder(new Color(51, 153, 255), 3));
		pnlPrompt.setBackground(new Color(255, 255, 255));
		pnlPrompt.setBounds(10, 135, 286, 120);
		contentPanel.add(pnlPrompt);
		pnlPrompt.setLayout(null);

		JButton button_3 = new JButton("OK");
		button_3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				pnlPrompt.setVisible(false);
			}
		});
		button_3.setForeground(Color.WHITE);
		button_3.setFont(new Font("SansSerif", Font.PLAIN, 15));
		button_3.setBorder(null);
		button_3.setBackground(new Color(51, 102, 255));
		button_3.setBounds(98, 74, 89, 35);
		pnlPrompt.add(button_3);

		txtPrompt = new JTextField();
		txtPrompt.setEditable(false);
		txtPrompt.setBorder(null);
		txtPrompt.setOpaque(false);
		txtPrompt.setForeground(new Color(0, 255, 0));
		txtPrompt.setFont(new Font("SansSerif", Font.PLAIN, 18));
		txtPrompt.setHorizontalAlignment(SwingConstants.CENTER);
		txtPrompt.setText("<prompt>");
		txtPrompt.setBounds(10, 11, 266, 46);
		pnlPrompt.add(txtPrompt);
		txtPrompt.setColumns(10);

		final JPanel pnlDelete = new JPanel();
		pnlDelete.setVisible(false);
		pnlDelete.setBorder(new LineBorder(new Color(51, 153, 255), 3));
		pnlDelete.setBackground(Color.WHITE);
		pnlDelete.setBounds(10, 135, 286, 120);
		contentPanel.add(pnlDelete);
		pnlDelete.setLayout(null);

		JLabel label = new JLabel("Are you sure you want to Delete ?");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.RED);
		label.setFont(new Font("SansSerif", Font.PLAIN, 16));
		label.setBounds(0, 20, 287, 29);
		pnlDelete.add(label);

		JButton button_1 = new JButton("Yes");
		button_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				TestConnection tc = new TestConnection();
				DatabaseManager dm = new DatabaseManager();

				DefaultTableModel model = (DefaultTableModel)tblColor.getModel();
				try{
					String id = model.getValueAt(tblColor.getSelectedRow(), 0).toString();

					try {
						int rs = dm.deleteColor(tc.getConnection(), id);
						if(rs==1){
							updateColorTable();
							pnlPrompt.setVisible(true);
							txtPrompt.setText("Successfully Deleted !");
							pnlDelete.setVisible(false);

						}
					} catch (ClassNotFoundException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}catch(ArrayIndexOutOfBoundsException aio){

				}
			}
		});
		button_1.setForeground(Color.WHITE);
		button_1.setFont(new Font("SansSerif", Font.PLAIN, 15));
		button_1.setBorder(null);
		button_1.setBackground(new Color(51, 102, 255));
		button_1.setBounds(70, 74, 66, 35);
		pnlDelete.add(button_1);

		JButton button_2 = new JButton("No");
		button_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				pnlDelete.setVisible(false);
			}
		});
		button_2.setForeground(Color.WHITE);
		button_2.setFont(new Font("SansSerif", Font.PLAIN, 15));
		button_2.setBorder(null);
		button_2.setBackground(new Color(51, 102, 255));
		button_2.setBounds(146, 74, 66, 35);
		pnlDelete.add(button_2);

		JLabel lblAddColor = new JLabel("Add Color");
		lblAddColor.setForeground(Color.WHITE);
		lblAddColor.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblAddColor.setBounds(10, 0, 162, 23);
		contentPanel.add(lblAddColor);

		JButton button = new JButton("X");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				
			}
		});
		button.setForeground(Color.WHITE);
		button.setFont(new Font("SansSerif", Font.BOLD, 11));
		button.setBorder(null);
		button.setBackground(new Color(0, 51, 255));
		button.setBounds(270, 1, 37, 22);
		contentPanel.add(button);

		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(AddColor.class.getResource("/app/image/title.png")));
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("SansSerif", Font.PLAIN, 15));
		label_1.setBounds(0, 0, 450, 23);
		contentPanel.add(label_1);

		JLabel lblColor = new JLabel("Color");
		lblColor.setForeground(SystemColor.textHighlight);
		lblColor.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblColor.setBounds(123, 34, 105, 29);
		contentPanel.add(lblColor);

		txtColor = new JTextField();
		txtColor.setHorizontalAlignment(SwingConstants.CENTER);
		txtColor.setForeground(Color.BLACK);
		txtColor.setFont(new Font("SansSerif", Font.PLAIN, 15));
		txtColor.setColumns(10);
		txtColor.setBorder(new LineBorder(new Color(51, 153, 255)));
		txtColor.setBounds(44, 59, 209, 29);
		contentPanel.add(txtColor);

		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(txtColor.getText().equals("")){
					pnlPrompt.setVisible(true);
					txtPrompt.setText("Insert Color Name !");
					txtPrompt.setForeground(Color.RED);
				}else{
					TestConnection tc = new TestConnection();
					DatabaseManager dm = new DatabaseManager();
					Colors c = new Colors();

					c.setColor(txtColor.getText());

					try {
						boolean exists = alreadyExists(txtColor.getText());
						if(!exists){
						int rs = dm.insertColor(tc.getConnection(), c);
						if(rs==1){
							updateColorTable();
							pnlPrompt.setVisible(true);
							txtPrompt.setText("Successfully Added !");
							pnlDelete.setVisible(false);
						}
						}else{
							pnlPrompt.setVisible(true);
							txtPrompt.setText("Color Already Exist ! !");
							txtPrompt.setForeground(Color.RED);
						}
					} catch (ClassNotFoundException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
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

		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(tblColor.getSelectedRow()==-1){
					pnlPrompt.setVisible(true);
					txtPrompt.setText("Select Item To Edit !");
					txtPrompt.setForeground(Color.RED);
					
				}else{
					try{
						EditColor ec = new EditColor(getSelectedColor());
						ec.setVisible(true);
						ec.setLocationRelativeTo(null);
						ec.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
						ec.setAlwaysOnTop(true);
						contentPanel.setFocusable(false);
						}catch(ArrayIndexOutOfBoundsException aio){
							pnlPrompt.setVisible(true);
							txtPrompt.setText("Select Item To Edit !");
							txtPrompt.setForeground(Color.RED);
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
				if(tblColor.getSelectedRow()==-1){
					pnlPrompt.setVisible(true);
					txtPrompt.setText("Select Item to Delete !");
					txtPrompt.setForeground(Color.RED);
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

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 141, 287, 211);
		contentPanel.add(scrollPane);

		tblColor = new JTable();
		tblColor.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Color Id", "Color"
				}
				) {
			boolean[] columnEditables = new boolean[] {
					false, false
			};
			@Override
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(tblColor);
	}
	public void updateColorTable(){
		TestConnection tc = new TestConnection();
		DatabaseManager dm = new DatabaseManager();
		DefaultTableModel model = (DefaultTableModel)tblColor.getModel();
		model.getDataVector().removeAllElements();
		tblColor.updateUI();

		try {
			ResultSet rs = dm.color(tc.getConnection());
			while(rs.next()){
				String colorId = rs.getString("colorId");
				String color = rs.getString("color");
				model.addRow(new Object[]{colorId,color});
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Colors getSelectedColor(){
		Colors c = new Colors();
		DefaultTableModel modelBrand = (DefaultTableModel)tblColor.getModel();
		c.setColorId(Integer.parseInt(modelBrand.getValueAt(tblColor.getSelectedRow(), 0).toString()));
		c.setColor(modelBrand.getValueAt(tblColor.getSelectedRow(), 1).toString());
		return c;
	}
	public static boolean alreadyExists(String colorName){
		TestConnection tc = new TestConnection();
		boolean exists = false;
		
		try {
			String query = "SELECT count(*) AS colorCount FROM color WHERE color = '"+colorName+"'";
			Statement stmt = tc.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			if (rs.next()) {
				if (Integer.parseInt(rs.getString("colorCount")) > 0 ) {
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
