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

public class ForgotPassword extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JPasswordField txtPincode;
	private JTextField txtYourUsername;
	private JTextField txtYourPasswordIs;
	private JTextField txtPrompt;
	private JButton button;

	public ForgotPassword() {
		addWindowFocusListener(new WindowFocusListener() {
			public void windowGainedFocus(WindowEvent e) {
			}
			public void windowLostFocus(WindowEvent e) {
				dispose();
			}
		});
		
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
				dispose();
			}
		});
		
		final JPanel pnlShowPassword = new JPanel();
		pnlShowPassword.setVisible(false);
		
		final JPanel pnlPromt = new JPanel();
		pnlPromt.setVisible(false);
		pnlPromt.setBorder(new LineBorder(new Color(51, 153, 255), 3));
		pnlPromt.setBackground(new Color(255, 255, 255));
		pnlPromt.setBounds(10, 33, 325, 123);
		contentPanel.add(pnlPromt);
		pnlPromt.setLayout(null);
		
		JButton button_2 = new JButton("OK");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlPromt.setVisible(false);
				button.setVisible(true);
				txtPincode.setText("");
			}
		});
		button_2.setForeground(Color.WHITE);
		button_2.setFont(new Font("SansSerif", Font.PLAIN, 15));
		button_2.setBorder(null);
		button_2.setBackground(new Color(51, 102, 255));
		button_2.setBounds(119, 77, 89, 35);
		pnlPromt.add(button_2);
		
		txtPrompt = new JTextField();
		txtPrompt.setText("<prompt>");
		txtPrompt.setOpaque(false);
		txtPrompt.setHorizontalAlignment(SwingConstants.CENTER);
		txtPrompt.setForeground(new Color(255, 0, 0));
		txtPrompt.setFont(new Font("SansSerif", Font.PLAIN, 15));
		txtPrompt.setEditable(false);
		txtPrompt.setColumns(10);
		txtPrompt.setBorder(null);
		txtPrompt.setBounds(10, 18, 305, 35);
		pnlPromt.add(txtPrompt);
		
		pnlShowPassword.setBorder(new LineBorder(new Color(51, 153, 255), 3));
		pnlShowPassword.setBackground(Color.WHITE);
		pnlShowPassword.setBounds(10, 33, 325, 123);
		contentPanel.add(pnlShowPassword);
		pnlShowPassword.setLayout(null);
		
		JButton button_1 = new JButton("OK");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button_1.setForeground(Color.WHITE);
		button_1.setFont(new Font("SansSerif", Font.PLAIN, 15));
		button_1.setBorder(null);
		button_1.setBackground(new Color(51, 102, 255));
		button_1.setBounds(121, 77, 89, 35);
		pnlShowPassword.add(button_1);
		
		txtYourUsername = new JTextField();
		txtYourUsername.setHorizontalAlignment(SwingConstants.CENTER);
		txtYourUsername.setText("Your username is");
		txtYourUsername.setForeground(Color.BLACK);
		txtYourUsername.setFont(new Font("SansSerif", Font.PLAIN, 15));
		txtYourUsername.setBorder(null);
		txtYourUsername.setOpaque(false);
		txtYourUsername.setEditable(false);
		txtYourUsername.setBounds(10, 11, 305, 35);
		pnlShowPassword.add(txtYourUsername);
		txtYourUsername.setColumns(10);
		
		txtYourPasswordIs = new JTextField();
		txtYourPasswordIs.setText("Your password is");
		txtYourPasswordIs.setOpaque(false);
		txtYourPasswordIs.setHorizontalAlignment(SwingConstants.CENTER);
		txtYourPasswordIs.setForeground(Color.BLACK);
		txtYourPasswordIs.setFont(new Font("SansSerif", Font.PLAIN, 15));
		txtYourPasswordIs.setEditable(false);
		txtYourPasswordIs.setColumns(10);
		txtYourPasswordIs.setBorder(null);
		txtYourPasswordIs.setBounds(10, 39, 305, 35);
		pnlShowPassword.add(txtYourPasswordIs);
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
		label_2.setIcon(new ImageIcon(ForgotPassword.class.getResource("/app/image/title.png")));
		label_2.setBounds(0, 0, 345, 22);
		contentPanel.add(label_2);
		
		JLabel label_3 = new JLabel("Pincode");
		label_3.setForeground(SystemColor.textHighlight);
		label_3.setFont(new Font("SansSerif", Font.PLAIN, 20));
		label_3.setBounds(25, 62, 95, 29);
		contentPanel.add(label_3);
		
		button = new JButton("OK");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				button.setVisible(false);
				TestConnection tc = new TestConnection();
				DatabaseManager dm = new DatabaseManager();
				int wrong = 0;
				int blank = 0;
				try {
					ResultSet rs = dm.accountSuper(tc.getConnection());
					
					while(rs.next()){
						String username = rs.getString("username");
						String password = rs.getString("password");
						
						if(new String(txtPincode.getPassword()).equals("")){
							blank=1;
						}else{
							if(new String(txtPincode.getPassword()).equals(rs.getString("pincode"))){
								pnlShowPassword.setVisible(true);
								txtYourUsername.setText("Your Username is "+username+" !");
								txtYourPasswordIs.setText("Your Password is "+password+" !");
								LoginWindow lw = new LoginWindow();
								lw.frmLogin.requestFocusInWindow();
								
								
								
							}else{
								wrong = 1;
							}
						
						}
						
					}
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}
				if(wrong==1){
					pnlPromt.setVisible(true);
					txtPrompt.setText("Invalid Pincode !");
				}
				if(blank==1){
					pnlPromt.setVisible(true);
					txtPrompt.setText("Insert Pincode !");
				}
			}
		});
		button.setForeground(Color.WHITE);
		button.setFont(new Font("SansSerif", Font.PLAIN, 15));
		button.setBorder(null);
		button.setBackground(new Color(51, 102, 255));
		button.setBounds(261, 104, 52, 29);
		contentPanel.add(button);
		
		txtPincode = new JPasswordField();
		txtPincode.setBorder(new LineBorder(new Color(51, 153, 255)));
		txtPincode.setHorizontalAlignment(SwingConstants.CENTER);
		txtPincode.setFont(new Font("SansSerif", Font.PLAIN, 15));
		txtPincode.setBounds(130, 64, 183, 29);
		contentPanel.add(txtPincode);
	}
}
