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
import app.model.Superuser;
import app.ui.UserWindow;

import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowEvent;

import javax.swing.JPasswordField;

public class ChangePassword extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtUsername;
	private JTextField txtNewPassword;
	private JPasswordField txtPassword;
	private JTextField txtPrompt;
	private JButton btnOK;

	
	public ChangePassword() {
		addWindowFocusListener(new WindowFocusListener() {
			public void windowGainedFocus(WindowEvent e) {
			}
			public void windowLostFocus(WindowEvent e) {
				dispose();
			}
		});
		
		setUndecorated(true);
		setBounds(100, 100, 400, 238);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 255, 255));
		contentPanel.setBorder(new LineBorder(new Color(51, 153, 255)));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		final JPanel pnlPrompt = new JPanel();
		pnlPrompt.setVisible(false);
		pnlPrompt.setBackground(new Color(255, 255, 255));
		pnlPrompt.setBorder(new LineBorder(new Color(51, 153, 255), 3));
		pnlPrompt.setBounds(40, 34, 321, 115);
		contentPanel.add(pnlPrompt);
		pnlPrompt.setLayout(null);
		
		JButton button_1 = new JButton("Ok");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pnlPrompt.setVisible(false);
				txtUsername.setEnabled(true);
				txtPassword.setEnabled(true);
				btnOK.setVisible(true);
			}
		});
		button_1.setForeground(Color.WHITE);
		button_1.setFont(new Font("SansSerif", Font.PLAIN, 15));
		button_1.setBorder(null);
		button_1.setBackground(new Color(51, 102, 255));
		button_1.setBounds(124, 69, 66, 35);
		pnlPrompt.add(button_1);
		
		txtPrompt = new JTextField();
		txtPrompt.setText("<prompt>");
		txtPrompt.setOpaque(false);
		txtPrompt.setHorizontalAlignment(SwingConstants.CENTER);
		txtPrompt.setForeground(Color.RED);
		txtPrompt.setFont(new Font("SansSerif", Font.PLAIN, 15));
		txtPrompt.setEditable(false);
		txtPrompt.setColumns(10);
		txtPrompt.setBorder(null);
		txtPrompt.setBounds(0, 11, 321, 35);
		pnlPrompt.add(txtPrompt);
		
		JLabel lblChangePassword = new JLabel("Change Password");
		lblChangePassword.setForeground(Color.WHITE);
		lblChangePassword.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblChangePassword.setBounds(10, 0, 162, 23);
		contentPanel.add(lblChangePassword);
		
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
		button.setBounds(363, 1, 37, 22);
		contentPanel.add(button);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(ChangePassword.class.getResource("/app/image/title.png")));
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
		
		final JPanel pnlNewPassword = new JPanel();
		pnlNewPassword.setVisible(false);
		
		txtPassword = new JPasswordField();
		txtPassword.setHorizontalAlignment(SwingConstants.CENTER);
		txtPassword.setBorder(new LineBorder(new Color(51, 153, 255)));
		txtPassword.setFont(new Font("SansSerif", Font.PLAIN, 15));
		txtPassword.setBounds(165, 68, 209, 30);
		contentPanel.add(txtPassword);
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
					Superuser su = new Superuser();
					su.setUsername(txtUsername.getText());
					su.setPassword(txtNewPassword.getText());
					try {
						int rs = dm.updateSuperuserPassword(tc.getConnection(), su);
						if(rs==1){
							JOptionPane.showMessageDialog(contentPanel, "Successfully Changed !");
							dispose();
							UserWindow sw = new UserWindow();
							sw.frmUserWindow.setFocusable(true);
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
		btnSave.setBounds(257, 37, 89, 35);
		pnlNewPassword.add(btnSave);
		
		btnOK = new JButton("OK");
		btnOK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
					btnOK.setVisible(false);
					TestConnection tc = new TestConnection();
					DatabaseManager dm = new DatabaseManager();
					int blank = 0;
					int wrong = 0;
					txtPassword.setEnabled(false);
					txtUsername.setEnabled(false);
					try {
						ResultSet rs = dm.accountSuper(tc.getConnection());
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
							}
						}
					} catch (ClassNotFoundException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(blank==1){
						pnlPrompt.setVisible(true);
						txtPrompt.setText("Specify Field(s) !");
					}
					if(wrong==1){
						pnlPrompt.setVisible(true);
						txtPrompt.setText("Check you Username and Password !");
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
