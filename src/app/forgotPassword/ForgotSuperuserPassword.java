package app.forgotPassword;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Color;

import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.JLabel;

import app.db.DatabaseManager;
import app.db.TestConnection;
import app.ui.LoginWindow;

import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JPasswordField;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowEvent;

public class ForgotSuperuserPassword extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtUsername;
	private JPasswordField txtPincode;

	public ForgotSuperuserPassword() {
		
		setUndecorated(true);
		setBounds(100, 100, 345, 167);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 255, 255));
		contentPanel.setBorder(new LineBorder(new Color(51, 153, 255)));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
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
		
		JLabel label_1 = new JLabel("Forgot Password");
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("SansSerif", Font.PLAIN, 15));
		label_1.setBounds(10, 0, 147, 22);
		contentPanel.add(label_1);
		
		JLabel label_2 = new JLabel("");
		label_2.setIcon(new ImageIcon(ForgotSuperuserPassword.class.getResource("/app/image/title.png")));
		label_2.setBounds(0, 0, 345, 22);
		contentPanel.add(label_2);
		
		JLabel label = new JLabel("Username");
		label.setForeground(SystemColor.textHighlight);
		label.setFont(new Font("SansSerif", Font.PLAIN, 20));
		label.setBounds(10, 51, 105, 29);
		contentPanel.add(label);
		
		JLabel label_3 = new JLabel("Pincode");
		label_3.setForeground(SystemColor.textHighlight);
		label_3.setFont(new Font("SansSerif", Font.PLAIN, 20));
		label_3.setBounds(20, 85, 95, 29);
		contentPanel.add(label_3);
		
		txtUsername = new JTextField();
		txtUsername.setHorizontalAlignment(SwingConstants.CENTER);
		txtUsername.setForeground(Color.BLACK);
		txtUsername.setFont(new Font("SansSerif", Font.BOLD, 15));
		txtUsername.setColumns(10);
		txtUsername.setBorder(new LineBorder(new Color(51, 153, 255)));
		txtUsername.setBounds(121, 53, 183, 29);
		contentPanel.add(txtUsername);
		
		JButton button = new JButton("OK");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				TestConnection tc = new TestConnection();
				DatabaseManager dm = new DatabaseManager();
				int wrong = 0;
				int blank = 0;
				try {
					ResultSet rs = dm.accountSuper(tc.getConnection());
					
					while(rs.next()){
						String admPassword = rs.getString("password");
						
						if(new String(txtPincode.getPassword()).equals("") && txtUsername.getText().equals("")){
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
		button.setForeground(Color.WHITE);
		button.setFont(new Font("SansSerif", Font.PLAIN, 15));
		button.setBorder(null);
		button.setBackground(new Color(51, 102, 255));
		button.setBounds(273, 127, 52, 29);
		contentPanel.add(button);
		
		txtPincode = new JPasswordField();
		txtPincode.setBorder(new LineBorder(new Color(51, 153, 255)));
		txtPincode.setHorizontalAlignment(SwingConstants.CENTER);
		txtPincode.setFont(new Font("SansSerif", Font.PLAIN, 15));
		txtPincode.setBounds(121, 89, 183, 29);
		contentPanel.add(txtPincode);
	}
}
