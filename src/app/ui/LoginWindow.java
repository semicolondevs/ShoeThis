package app.ui;

import java.awt.EventQueue;
import java.awt.Frame;

import javax.swing.JFrame;

import java.awt.Color;

import javax.swing.JButton;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;
import javax.swing.SwingConstants;

import java.awt.SystemColor;

import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import app.db.DatabaseManager;
import app.db.TestConnection;
import app.forgotPassword.ForgotAdminPassword;
import app.forgotPassword.ForgotSuperuserPassword;
import app.util.DigitalClock;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

public class LoginWindow {

	public JFrame frmLogin;
	private JTextField txtUsername;
	private JPasswordField txtPassword;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					LoginWindow window = new LoginWindow();
					window.frmLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLogin = new JFrame();
		frmLogin.addWindowFocusListener(new WindowFocusListener() {
			@Override
			public void windowGainedFocus(WindowEvent arg0) {
			}
			@Override
			public void windowLostFocus(WindowEvent arg0) {
			}
		});
		frmLogin.getContentPane().setBackground(Color.WHITE);
		frmLogin.getContentPane().setLayout(null);

		JButton button = new JButton("X");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(frmLogin, "Are you sure you want to exit ?","PROMPT",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==0){
					System.exit(0);
				}
			}
		});
		button.setForeground(Color.WHITE);
		button.setFont(new Font("SansSerif", Font.BOLD, 11));
		button.setBorder(null);
		button.setBackground(new Color(0, 153, 255));
		button.setBounds(1329, 0, 37, 22);
		frmLogin.getContentPane().add(button);

		JButton button_1 = new JButton("_");
		button_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				frmLogin.setState(Frame.ICONIFIED);
			}
		});
		button_1.setForeground(Color.WHITE);
		button_1.setFont(new Font("SansSerif", Font.BOLD, 11));
		button_1.setBorder(null);
		button_1.setBackground(new Color(0, 51, 255));
		button_1.setBounds(1291, 0, 37, 22);
		frmLogin.getContentPane().add(button_1);

		JLabel label = new JLabel("Shoe This Inventory Management System");
		label.setForeground(Color.WHITE);
		label.setFont(new Font("SansSerif", Font.PLAIN, 15));
		label.setBounds(10, 0, 463, 23);
		frmLogin.getContentPane().add(label);

		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(LoginWindow.class.getResource("/app/image/title.png")));
		label_1.setBounds(0, 0, 1366, 22);
		frmLogin.getContentPane().add(label_1);

		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ForgotSuperuserPassword fsu = new ForgotSuperuserPassword();
				fsu.setVisible(true);
				fsu.setVisible(true);
				fsu.setLocationRelativeTo(null);
				fsu.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
				frmLogin.setFocusable(false);
			}
		});
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setBorder(null);
		btnNewButton.setBounds(1015, 150, 26, 23);
		frmLogin.getContentPane().add(btnNewButton);

		JLabel label_2 = new JLabel("");
		label_2.setIcon(new ImageIcon(LoginWindow.class.getResource("/app/image/ShoeThis.png")));
		label_2.setBounds(280, 34, 800, 300);
		frmLogin.getContentPane().add(label_2);

		txtUsername = new JTextField();
		txtUsername.setHorizontalAlignment(SwingConstants.CENTER);
		txtUsername.setForeground(Color.BLACK);
		txtUsername.setFont(new Font("SansSerif", Font.BOLD, 15));
		txtUsername.setColumns(10);
		txtUsername.setBorder(new LineBorder(new Color(51, 153, 255)));
		txtUsername.setBounds(574, 400, 209, 29);
		frmLogin.getContentPane().add(txtUsername);

		JLabel lblUsername = new JLabel("Username");
		lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsername.setForeground(SystemColor.textHighlight);
		lblUsername.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblUsername.setBounds(623, 360, 105, 29);
		frmLogin.getContentPane().add(lblUsername);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setForeground(SystemColor.textHighlight);
		lblPassword.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblPassword.setBounds(623, 440, 105, 29);
		frmLogin.getContentPane().add(lblPassword);

		txtPassword = new JPasswordField();
		txtPassword.setHorizontalAlignment(SwingConstants.CENTER);
		txtPassword.setBorder(new LineBorder(new Color(51, 153, 255)));
		txtPassword.setOpaque(false);
		txtPassword.setFont(new Font("SansSerif", Font.BOLD, 15));
		txtPassword.setBounds(574, 480, 209, 29);
		frmLogin.getContentPane().add(txtPassword);

		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int put = 0;
				
				TestConnection t = new TestConnection();
				DatabaseManager dm = new DatabaseManager();

				try {
					ResultSet rsAdmin = dm.accountAdmin(t.getConnection());
					ResultSet rsSuper = dm.accountSuper(t.getConnection());

					while(rsAdmin.next() && rsSuper.next()){
						String adminUsername = rsAdmin.getString("username");
						String adminPassword = rsAdmin.getString("password");
						if(txtUsername.getText().equals(rsSuper.getString("username")) && new String(txtPassword.getPassword()).equals(rsSuper.getString("password"))){
							SuperUserWindow su = new SuperUserWindow();
							su.frmSuperUser.setVisible(true);
							JOptionPane.showMessageDialog(frmLogin, "Successfully Logged in !");
							frmLogin.dispose();
							su.frmSuperUser.setFocusable(false);	
							

						}else if(txtUsername.getText().equals(adminUsername) && new String(txtPassword.getPassword()).equals(adminPassword)){
							AdminWindow am = new AdminWindow();
							am.frmAdmin.setVisible(true);
							JOptionPane.showMessageDialog(frmLogin, "Successfully Logged in !");
							frmLogin.dispose();
							am.frmAdmin.setFocusable(false);
						}else{
							put=1;

						}
					}
					rsAdmin.close();
					rsSuper.close();
					t.getConnection().close();

				} catch (ClassNotFoundException | SQLException e2) {
					
					
				}

				if(put==1){
					JOptionPane.showMessageDialog(frmLogin, "Either Wrong Username and Password, Or Insert Username and Password !");

				}

			}
		});
		btnLogin.setForeground(Color.WHITE);
		btnLogin.setFont(new Font("SansSerif", Font.PLAIN, 15));
		btnLogin.setBorder(null);
		btnLogin.setBackground(new Color(51, 102, 255));
		btnLogin.setBounds(574, 543, 89, 35);
		frmLogin.getContentPane().add(btnLogin);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(frmLogin, "Are you sure you want to exit ?","PROMPT",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==0){
					System.exit(0);
				}
			}
		});
		btnCancel.setForeground(Color.WHITE);
		btnCancel.setFont(new Font("SansSerif", Font.PLAIN, 15));
		btnCancel.setBorder(null);
		btnCancel.setBackground(new Color(51, 102, 255));
		btnCancel.setBounds(694, 543, 89, 35);
		frmLogin.getContentPane().add(btnCancel);

		JLabel lblClock = new JLabel("");
		new DigitalClock(lblClock);
		lblClock.setHorizontalAlignment(SwingConstants.CENTER);
		lblClock.setForeground(new Color(51, 51, 255));
		lblClock.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblClock.setBounds(574, 268, 209, 35);
		frmLogin.getContentPane().add(lblClock);

		JButton btnForgotPassword = new JButton("*Forgot Password ?");
		btnForgotPassword.setFont(new Font("Tahoma", Font.ITALIC, 13));
		btnForgotPassword.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ForgotAdminPassword fp = new ForgotAdminPassword();
				fp.setVisible(true);
				fp.setLocationRelativeTo(null);
				fp.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
				frmLogin.setFocusable(false);
			}
		});
		btnForgotPassword.setBackground(new Color(255, 255, 255));
		btnForgotPassword.setBorder(null);
		btnForgotPassword.setBounds(616, 589, 123, 23);
		frmLogin.getContentPane().add(btnForgotPassword);
		frmLogin.setBackground(Color.WHITE);
		frmLogin.setUndecorated(true);
		frmLogin.setBounds(0, 0, 1366, 768);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
