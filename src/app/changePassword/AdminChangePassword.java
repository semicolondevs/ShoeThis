package app.changePassword;

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
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import app.db.DatabaseManager;
import app.db.TestConnection;
import app.model.User;
import app.ui.AdminWindow;

import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowEvent;

public class AdminChangePassword extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtUsername;
	private JTextField txtPassword;
	private JTextField txtNewPassword;

	
	public AdminChangePassword() {
		
		setUndecorated(true);
		setBounds(100, 100, 400, 238);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 255, 255));
		contentPanel.setBorder(new LineBorder(new Color(51, 153, 255)));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblChangePassword = new JLabel("Change Password");
		lblChangePassword.setForeground(Color.WHITE);
		lblChangePassword.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblChangePassword.setBounds(10, 0, 162, 23);
		contentPanel.add(lblChangePassword);
		
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
		button.setBounds(363, 1, 37, 22);
		contentPanel.add(button);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(AdminChangePassword.class.getResource("/app/image/title.png")));
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("SansSerif", Font.PLAIN, 15));
		label_1.setBounds(0, 0, 450, 23);
		contentPanel.add(label_1);
		
		txtUsername = new JTextField();
		txtUsername.setHorizontalAlignment(SwingConstants.CENTER);
		txtUsername.setForeground(Color.BLACK);
		txtUsername.setFont(new Font("SansSerif", Font.PLAIN, 15));
		txtUsername.setColumns(10);
		txtUsername.setBorder(new LineBorder(new Color(51, 153, 255)));
		txtUsername.setBounds(165, 34, 209, 29);
		contentPanel.add(txtUsername);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setForeground(SystemColor.textHighlight);
		lblUsername.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblUsername.setBounds(40, 34, 105, 29);
		contentPanel.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(SystemColor.textHighlight);
		lblPassword.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblPassword.setBounds(40, 74, 105, 29);
		contentPanel.add(lblPassword);
		
		txtPassword = new JTextField();
		txtPassword.setHorizontalAlignment(SwingConstants.CENTER);
		txtPassword.setForeground(Color.BLACK);
		txtPassword.setFont(new Font("SansSerif", Font.PLAIN, 15));
		txtPassword.setColumns(10);
		txtPassword.setBorder(new LineBorder(new Color(51, 153, 255)));
		txtPassword.setBounds(165, 74, 209, 29);
		contentPanel.add(txtPassword);
		
		final JPanel pnlNewPassword = new JPanel();
		pnlNewPassword.setVisible(false);
		pnlNewPassword.setOpaque(false);
		pnlNewPassword.setBounds(10, 155, 380, 72);
		contentPanel.add(pnlNewPassword);
		pnlNewPassword.setLayout(null);
		
		JLabel lblNewPassword = new JLabel("New Password");
		lblNewPassword.setForeground(SystemColor.textHighlight);
		lblNewPassword.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblNewPassword.setBounds(22, 0, 169, 29);
		pnlNewPassword.add(lblNewPassword);
		
		txtNewPassword = new JTextField();
		txtNewPassword.setHorizontalAlignment(SwingConstants.CENTER);
		txtNewPassword.setForeground(Color.BLACK);
		txtNewPassword.setFont(new Font("SansSerif", Font.PLAIN, 15));
		txtNewPassword.setColumns(10);
		txtNewPassword.setBorder(new LineBorder(new Color(51, 153, 255)));
		txtNewPassword.setBounds(22, 40, 209, 29);
		pnlNewPassword.add(txtNewPassword);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(txtNewPassword.getText().equals("")){
					JOptionPane.showMessageDialog(contentPanel, "Specify Fields !");
				}else{
					TestConnection tc = new TestConnection();
					DatabaseManager dm = new DatabaseManager();
					User u = new User();
					u.setUserName(txtUsername.getText());
					u.setPassword(txtNewPassword.getText());
					try {
						int rs = dm.updateAdminPassword(tc.getConnection(), u);
						if(rs==1){
							JOptionPane.showMessageDialog(contentPanel, "Successfully Changed !");
							dispose();
							AdminWindow aw = new AdminWindow();
							aw.frmAdmin.setFocusable(true);
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
		btnSave.setBounds(291, 34, 89, 35);
		pnlNewPassword.add(btnSave);
		
		JButton btnOK = new JButton("OK");
		btnOK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
					TestConnection tc = new TestConnection();
					DatabaseManager dm = new DatabaseManager();
					int wrong = 0;
					int blank = 0;
					try {
						ResultSet rs = dm.accountAdmin(tc.getConnection());
						while(rs.next()){
							String username = rs.getString("username");
							if(txtUsername.getText().equals("") || txtPassword.getText().equals("")){
								blank=1;
							}else{
								if(txtUsername.getText().equals(username) && txtPassword.getText().equals(rs.getString("password"))){
									pnlNewPassword.setVisible(true);
								}else{
									wrong=1;
								}
								break;
							}
							
						}
					} catch (ClassNotFoundException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(blank==1){
						JOptionPane.showMessageDialog(contentPanel, "Specify Fields !");
					}
					if(wrong==1){
						JOptionPane.showMessageDialog(contentPanel, "Check Your Username & Password !");
					}
					
				
			}
		});
		btnOK.setForeground(Color.WHITE);
		btnOK.setFont(new Font("SansSerif", Font.PLAIN, 15));
		btnOK.setBorder(null);
		btnOK.setBackground(new Color(51, 102, 255));
		btnOK.setBounds(323, 109, 51, 35);
		contentPanel.add(btnOK);
	}
}
