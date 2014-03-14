package app.edit;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Color;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;

import java.awt.SystemColor;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;

import app.db.DatabaseManager;
import app.db.TestConnection;
import app.model.User;
import app.ui.SuperUserWindow;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.WindowFocusListener;

public class EditUser extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtAdminName;
	private JTextField txtAdminUsername;
	private JPasswordField txtAdminPassword;
	private JTextField txtContact;
	private JTextField txtPin;
	private JTextField txtId;


	public EditUser(final User u) {
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				txtAdminName.setText(u.getName());
				txtAdminUsername.setText(u.getUserName());
				txtAdminPassword.setText(u.getPassword());
				txtContact.setText(u.getContactNo());
				txtPin.setText(u.getPinCode());
				txtId.setText(u.getID());

			}
		});
		setUndecorated(true);
		setBounds(900, 320, 327, 320);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new LineBorder(new Color(51, 153, 255)));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblAddUser = new JLabel("Edit User");
		lblAddUser.setForeground(Color.WHITE);
		lblAddUser.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblAddUser.setBounds(10, 0, 162, 23);
		contentPanel.add(lblAddUser);

		JButton button = new JButton("X");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
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

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(EditUser.class.getResource("/app/image/title.png")));
		label.setForeground(Color.WHITE);
		label.setFont(new Font("SansSerif", Font.PLAIN, 15));
		label.setBounds(0, 0, 327, 23);
		contentPanel.add(label);

		JLabel label_1 = new JLabel("Name");
		label_1.setForeground(SystemColor.textHighlight);
		label_1.setFont(new Font("SansSerif", Font.PLAIN, 20));
		label_1.setBounds(10, 71, 105, 29);
		contentPanel.add(label_1);

		txtAdminName = new JTextField();
		txtAdminName.setHorizontalAlignment(SwingConstants.CENTER);
		txtAdminName.setForeground(Color.BLACK);
		txtAdminName.setFont(new Font("SansSerif", Font.PLAIN, 15));
		txtAdminName.setColumns(10);
		txtAdminName.setBorder(new LineBorder(new Color(51, 153, 255)));
		txtAdminName.setBounds(108, 71, 209, 29);
		contentPanel.add(txtAdminName);

		JLabel label_2 = new JLabel("Username");
		label_2.setForeground(SystemColor.textHighlight);
		label_2.setFont(new Font("SansSerif", Font.PLAIN, 20));
		label_2.setBounds(10, 111, 105, 29);
		contentPanel.add(label_2);

		txtAdminUsername = new JTextField();
		txtAdminUsername.setHorizontalAlignment(SwingConstants.CENTER);
		txtAdminUsername.setForeground(Color.BLACK);
		txtAdminUsername.setFont(new Font("SansSerif", Font.PLAIN, 15));
		txtAdminUsername.setColumns(10);
		txtAdminUsername.setBorder(new LineBorder(new Color(51, 153, 255)));
		txtAdminUsername.setBounds(108, 111, 209, 29);
		contentPanel.add(txtAdminUsername);

		JLabel label_3 = new JLabel("Password");
		label_3.setForeground(SystemColor.textHighlight);
		label_3.setFont(new Font("SansSerif", Font.PLAIN, 20));
		label_3.setBounds(10, 151, 105, 29);
		contentPanel.add(label_3);

		txtAdminPassword = new JPasswordField();
		txtAdminPassword.setHorizontalAlignment(SwingConstants.CENTER);
		txtAdminPassword.setOpaque(false);
		txtAdminPassword.setFont(new Font("SansSerif", Font.BOLD, 15));
		txtAdminPassword.setBorder(new LineBorder(new Color(51, 153, 255)));
		txtAdminPassword.setBounds(108, 151, 209, 29);
		contentPanel.add(txtAdminPassword);

		JLabel label_4 = new JLabel("Contact #");
		label_4.setForeground(SystemColor.textHighlight);
		label_4.setFont(new Font("SansSerif", Font.PLAIN, 20));
		label_4.setBounds(10, 191, 105, 29);
		contentPanel.add(label_4);

		txtContact = new JTextField();
		txtContact.setHorizontalAlignment(SwingConstants.CENTER);
		txtContact.setForeground(Color.BLACK);
		txtContact.setFont(new Font("SansSerif", Font.PLAIN, 15));
		txtContact.setColumns(10);
		txtContact.setBorder(new LineBorder(new Color(51, 153, 255)));
		txtContact.setBounds(108, 191, 209, 29);
		contentPanel.add(txtContact);

		JLabel label_5 = new JLabel("Pin");
		label_5.setForeground(SystemColor.textHighlight);
		label_5.setFont(new Font("SansSerif", Font.PLAIN, 20));
		label_5.setBounds(10, 231, 105, 29);
		contentPanel.add(label_5);

		txtPin = new JTextField();
		txtPin.setHorizontalAlignment(SwingConstants.CENTER);
		txtPin.setForeground(Color.BLACK);
		txtPin.setFont(new Font("SansSerif", Font.PLAIN, 15));
		txtPin.setColumns(10);
		txtPin.setBorder(new LineBorder(new Color(51, 153, 255)));
		txtPin.setBounds(108, 231, 209, 29);
		contentPanel.add(txtPin);

		JLabel lblId = new JLabel("ID");
		lblId.setForeground(SystemColor.textHighlight);
		lblId.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblId.setBounds(10, 31, 105, 29);
		contentPanel.add(lblId);

		txtId = new JTextField();
		txtId.setOpaque(false);
		txtId.setEditable(false);
		txtId.setHorizontalAlignment(SwingConstants.CENTER);
		txtId.setForeground(Color.BLACK);
		txtId.setFont(new Font("SansSerif", Font.PLAIN, 15));
		txtId.setColumns(10);
		txtId.setBorder(new LineBorder(new Color(51, 153, 255)));
		txtId.setBounds(108, 31, 209, 29);
		contentPanel.add(txtId);

		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				TestConnection tc = new TestConnection();
				DatabaseManager dm = new DatabaseManager();
				User u = new User();
				u.setID(txtId.getText());
				u.setName(txtAdminName.getText());
				u.setUserName(txtAdminUsername.getText());
				u.setPassword(new String(txtAdminPassword.getPassword()));
				u.setContactNo(txtContact.getText());
				u.setPinCode(txtPin.getText());

				try {
					boolean exists = alreadyExists(txtAdminUsername.getText());
					if(!exists){

						int rs = dm.updateUser(tc.getConnection(), u);
						if(rs==1){
							SuperUserWindow su = new SuperUserWindow();
							su.frmSuperUser.requestFocusInWindow();
							JOptionPane.showMessageDialog(contentPanel, "Successfully Edited !");
							dispose();
						}
					}else{
						JOptionPane.showMessageDialog(contentPanel, "Nothing Change ! "); 
					}
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}


			}
		});
		btnSave.setForeground(Color.WHITE);
		btnSave.setFont(new Font("SansSerif", Font.PLAIN, 15));
		btnSave.setBorder(null);
		btnSave.setBackground(new Color(51, 102, 255));
		btnSave.setBounds(10, 274, 89, 35);
		contentPanel.add(btnSave);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.setForeground(Color.WHITE);
		btnCancel.setFont(new Font("SansSerif", Font.PLAIN, 15));
		btnCancel.setBorder(null);
		btnCancel.setBackground(new Color(51, 102, 255));
		btnCancel.setBounds(108, 274, 89, 35);
		contentPanel.add(btnCancel);
	}
	public static boolean alreadyExists(String userName){
		TestConnection tc = new TestConnection();


		boolean exists = false;

		try {
			String query = "SELECT count(*) AS userCount FROM users WHERE username = '"+userName+"';";
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
