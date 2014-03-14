package app.add;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Color;

import javax.swing.border.LineBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.ImageIcon;

import java.awt.SystemColor;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;

import app.db.DatabaseManager;
import app.db.TestConnection;
import app.model.User;
import app.ui.SuperUserWindow;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowEvent;

public class AddUser extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtAdminName;
	private JTextField txtAdminUsername;
	private JTextField txtContact;
	private JTextField txtPin;
	private JPasswordField txtPassword;
	private DateFormat dateFormat = new SimpleDateFormat("YYYYHHMMSSSS");

	
	public AddUser(final User u) {
		
		setUndecorated(true);
		setBounds(900, 320, 327, 330);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new LineBorder(new Color(51, 153, 255)));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblEditUser = new JLabel("Add User");
		lblEditUser.setForeground(Color.WHITE);
		lblEditUser.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblEditUser.setBounds(10, 0, 162, 23);
		contentPanel.add(lblEditUser);
		
		JButton btnNewButton = new JButton("X");
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(contentPanel, "Are you sure you want to close ?","PROMPT",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==0){
					dispose();
				}
			}
		});
		btnNewButton.setBackground(new Color(0, 51, 255));
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setFont(new Font("SansSerif", Font.BOLD, 11));
		btnNewButton.setBorder(null);
		btnNewButton.setBounds(290, 0, 37, 23);
		contentPanel.add(btnNewButton);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(AddUser.class.getResource("/app/image/title.png")));
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("SansSerif", Font.PLAIN, 15));
		label_1.setBounds(0, 0, 327, 23);
		contentPanel.add(label_1);
		
		JLabel label = new JLabel("Name");
		label.setForeground(SystemColor.textHighlight);
		label.setFont(new Font("SansSerif", Font.PLAIN, 20));
		label.setBounds(10, 34, 105, 29);
		contentPanel.add(label);
		
		txtAdminName = new JTextField();
		txtAdminName.setHorizontalAlignment(SwingConstants.CENTER);
		txtAdminName.setForeground(Color.BLACK);
		txtAdminName.setFont(new Font("SansSerif", Font.PLAIN, 15));
		txtAdminName.setColumns(10);
		txtAdminName.setBorder(new LineBorder(new Color(51, 153, 255)));
		txtAdminName.setBounds(108, 34, 209, 29);
		contentPanel.add(txtAdminName);
		
		JLabel Username = new JLabel("Username");
		Username.setForeground(SystemColor.textHighlight);
		Username.setFont(new Font("SansSerif", Font.PLAIN, 20));
		Username.setBounds(10, 74, 105, 29);
		contentPanel.add(Username);
		
		txtAdminUsername = new JTextField();
		txtAdminUsername.setHorizontalAlignment(SwingConstants.CENTER);
		txtAdminUsername.setFont(new Font("SansSerif", Font.PLAIN, 15));
		txtAdminUsername.setColumns(10);
		txtAdminUsername.setBorder(new LineBorder(new Color(51, 153, 255)));
		txtAdminUsername.setBounds(108, 74, 209, 29);
		contentPanel.add(txtAdminUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(SystemColor.textHighlight);
		lblPassword.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblPassword.setBounds(10, 114, 105, 29);
		contentPanel.add(lblPassword);
		
		JLabel lblContact = new JLabel("Contact #");
		lblContact.setForeground(SystemColor.textHighlight);
		lblContact.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblContact.setBounds(10, 154, 105, 29);
		contentPanel.add(lblContact);
		
		txtContact = new JTextField();
		txtContact.setHorizontalAlignment(SwingConstants.CENTER);
		txtContact.setForeground(Color.BLACK);
		txtContact.setFont(new Font("SansSerif", Font.PLAIN, 15));
		txtContact.setColumns(10);
		txtContact.setBorder(new LineBorder(new Color(51, 153, 255)));
		txtContact.setBounds(108, 154, 209, 29);
		contentPanel.add(txtContact);
		
		JLabel lblPin = new JLabel("Pin");
		lblPin.setForeground(SystemColor.textHighlight);
		lblPin.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblPin.setBounds(10, 194, 105, 29);
		contentPanel.add(lblPin);
		
		txtPin = new JTextField();
		txtPin.setHorizontalAlignment(SwingConstants.CENTER);
		txtPin.setForeground(Color.BLACK);
		txtPin.setFont(new Font("SansSerif", Font.PLAIN, 15));
		txtPin.setColumns(10);
		txtPin.setBorder(new LineBorder(new Color(51, 153, 255)));
		txtPin.setBounds(108, 194, 209, 29);
		contentPanel.add(txtPin);
		
		txtPassword = new JPasswordField();
		txtPassword.setHorizontalAlignment(SwingConstants.CENTER);
		txtPassword.setFont(new Font("SansSerif", Font.BOLD, 15));
		txtPassword.setBorder(new LineBorder(new Color(51, 153, 255)));
		txtPassword.setOpaque(false);
		txtPassword.setBounds(108, 114, 209, 29);
		contentPanel.add(txtPassword);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(txtAdminName.getText().equals("") || txtAdminUsername.getText().equals("") || 
						txtContact.getText().equals("") || txtPassword.getPassword().equals("") || txtPin.getText().equals("")){
					JOptionPane.showMessageDialog(contentPanel, "Specify the fields !");
				}else{
				TestConnection tc = new TestConnection();
				DatabaseManager dm = new DatabaseManager();
				User u = new User();
				
				Date date = new Date();
				u.setID("ADM-"+dateFormat.format(date));
				u.setName(txtAdminName.getText());
				u.setUserName(txtAdminUsername.getText());
				u.setPassword(new String(txtPassword.getPassword()));
				u.setContactNo(txtContact.getText());
				u.setPinCode(txtPin.getText());
				
				try {
					boolean exists = alreadyExists(txtAdminUsername.getText());
					
					if(!exists){
						int rs = dm.insertAccount(tc.getConnection(), u);
					if(rs==1){
						
						
						SuperUserWindow su = new SuperUserWindow();
						su.updateItemTable();
						su.frmSuperUser.requestFocusInWindow();
						JOptionPane.showMessageDialog(contentPanel, "Successfully Added !");
						clearText();
					}
					
					}else{
						JOptionPane.showMessageDialog(contentPanel, "Username Already Exist !");
						txtAdminUsername.setText("");
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
		btnSave.setBounds(10, 284, 89, 35);
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
		btnCancel.setBounds(108, 284, 89, 35);
		contentPanel.add(btnCancel);
		
		JPanel Specify = new JPanel();
		Specify.setOpaque(false);
		Specify.setVisible(false);
		Specify.setBounds(10, 224, 307, 56);
		contentPanel.add(Specify);
		Specify.setLayout(null);
		
		JLabel label_2 = new JLabel("Please specify the fields !");
		label_2.setForeground(Color.RED);
		label_2.setFont(new Font("SansSerif", Font.PLAIN, 18));
		label_2.setBounds(23, 0, 204, 51);
		Specify.add(label_2);
		
		JButton button = new JButton("OK");
		button.setForeground(Color.WHITE);
		button.setFont(new Font("SansSerif", Font.PLAIN, 15));
		button.setBorder(null);
		button.setBackground(new Color(51, 102, 255));
		button.setBounds(237, 9, 46, 35);
		Specify.add(button);
	}
	public void clearText(){
		txtAdminName.setText("");
		txtAdminUsername.setText("");
		txtContact.setText("");
		txtPassword.setText("");
		txtPin.setText("");
	}
	public static boolean alreadyExists(String username){
		TestConnection tc = new TestConnection();
		
		
		boolean exists = false;
		
		try {
			String query = "SELECT count(*) AS userCount FROM users WHERE username = '"+username+"'";
			Statement stmt = tc.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			if (rs.next()) {
				if (Integer.parseInt(rs.getString("userCount")) > 0 ) {
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
