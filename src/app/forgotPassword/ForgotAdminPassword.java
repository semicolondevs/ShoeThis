package app.forgotPassword;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Color;

import javax.swing.JLabel;

import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import app.db.DatabaseManager;
import app.db.TestConnection;
import app.ui.LoginWindow;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JPasswordField;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowEvent;

public class ForgotAdminPassword extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtUsername;
	private JPasswordField txtPincode;


	public ForgotAdminPassword() {
		
		setUndecorated(true);
		setTitle("Forgot Password ?");
		setBounds(100, 100, 345, 167);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 255, 255));
		contentPanel.setBorder(new LineBorder(new Color(51, 153, 255)));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JButton btnOk = new JButton("OK");
			btnOk.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					TestConnection tc = new TestConnection();
					DatabaseManager dm = new DatabaseManager();
					int wrong = 0;
					int blank = 0;
					try {
						ResultSet rs = dm.accountAdmin(tc.getConnection());

						while(rs.next()){
							String admPassword = rs.getString("password");

							if(txtPincode.getPassword().equals("") || txtUsername.getText().equals("")){
								blank=1;
							}else{

								if(txtUsername.getText().equals(rs.getString("username")) && new String(txtPincode.getPassword()).equals(rs.getString("PinCode"))){
									JOptionPane.showMessageDialog(contentPanel, "Your Password is "+admPassword);
									LoginWindow lw = new LoginWindow();
									lw.frmLogin.requestFocusInWindow();
									dispose();

								}else{
									wrong = 1;

								}
								
							}
						}

					} catch (ClassNotFoundException | SQLException e1) {
						e1.printStackTrace();
					}
					if(wrong==1){
						JOptionPane.showMessageDialog(contentPanel, "Either wrong username and pincode");
					}
					if(blank==1){
						JOptionPane.showMessageDialog(contentPanel, "Please specify fields !");
					}
					
				}
			});
			btnOk.setForeground(Color.WHITE);
			btnOk.setFont(new Font("SansSerif", Font.PLAIN, 15));
			btnOk.setBorder(null);
			btnOk.setBackground(new Color(51, 102, 255));
			btnOk.setBounds(283, 127, 52, 29);
			contentPanel.add(btnOk);
		}
		{
			JButton btnClose = new JButton("X");
			btnClose.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if(JOptionPane.showConfirmDialog(contentPanel, "Are you sure you want to close ?","PROMPT",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==0){
						dispose();
					}
				}
			});
			btnClose.setForeground(Color.WHITE);
			btnClose.setFont(new Font("SansSerif", Font.BOLD, 11));
			btnClose.setBorder(null);
			btnClose.setBackground(new Color(0, 153, 255));
			btnClose.setBounds(308, 0, 37, 22);
			contentPanel.add(btnClose);
		}
		{
			JLabel lblForgotPassword = new JLabel("Forgot Password");
			lblForgotPassword.setForeground(new Color(255, 255, 255));
			lblForgotPassword.setFont(new Font("SansSerif", Font.PLAIN, 15));
			lblForgotPassword.setBounds(10, 0, 147, 22);
			contentPanel.add(lblForgotPassword);
		}
		{
			JLabel label = new JLabel("");
			label.setIcon(new ImageIcon(ForgotAdminPassword.class.getResource("/app/image/title.png")));
			label.setBounds(0, 0, 314, 22);
			contentPanel.add(label);
		}

		txtUsername = new JTextField();
		txtUsername.setHorizontalAlignment(SwingConstants.CENTER);
		txtUsername.setForeground(Color.BLACK);
		txtUsername.setFont(new Font("SansSerif", Font.BOLD, 15));
		txtUsername.setColumns(10);
		txtUsername.setBorder(new LineBorder(new Color(51, 153, 255)));
		txtUsername.setBounds(131, 53, 183, 29);
		contentPanel.add(txtUsername);

		JLabel lblUsername = new JLabel("Username");
		lblUsername.setForeground(SystemColor.textHighlight);
		lblUsername.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblUsername.setBounds(20, 51, 105, 29);
		contentPanel.add(lblUsername);

		JLabel lblPincode = new JLabel("Pincode");
		lblPincode.setForeground(SystemColor.textHighlight);
		lblPincode.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblPincode.setBounds(30, 85, 125, 29);
		contentPanel.add(lblPincode);

		txtPincode = new JPasswordField();
		txtPincode.setHorizontalAlignment(SwingConstants.CENTER);
		txtPincode.setBorder(new LineBorder(new Color(51, 153, 255)));
		txtPincode.setBounds(131, 85, 183, 29);
		contentPanel.add(txtPincode);
	}
}
