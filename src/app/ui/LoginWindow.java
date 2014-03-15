package app.ui;

import java.awt.EventQueue;
import java.awt.Frame;

import javax.swing.JFrame;

import java.awt.Color;

import javax.swing.JButton;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;
import javax.swing.SwingConstants;

import java.awt.SystemColor;

import javax.swing.JPasswordField;

import app.db.DatabaseManager;
import app.db.TestConnection;
import app.forgotPassword.ForgotPassword;
import app.util.DigitalClock;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import javax.swing.JPanel;

public class LoginWindow {

	public JFrame frmLogin;
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	private DateFormat dateFormat = new SimpleDateFormat("MMMM dd, YYYY");
	private Date date = new Date();

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

		final JPanel pnlExit = new JPanel();
		pnlExit.setVisible(false);

		final JPanel pnlSpecify = new JPanel();
		pnlSpecify.setVisible(false);

		final JPanel pnlSuccessLogin = new JPanel();
		pnlSuccessLogin.setBorder(new LineBorder(new Color(51, 153, 255), 3));
		pnlSuccessLogin.setVisible(false);

		final JPanel pnlInvalidAccount = new JPanel();
		pnlInvalidAccount.setVisible(false);
		pnlInvalidAccount.setBackground(new Color(255, 255, 255));
		pnlInvalidAccount.setBorder(new LineBorder(new Color(51, 153, 255), 3));
		pnlInvalidAccount.setBounds(525, 291, 322, 133);
		frmLogin.getContentPane().add(pnlInvalidAccount);
		pnlInvalidAccount.setLayout(null);

		JLabel lblInvalidAccount = new JLabel("Invalid Account !");
		lblInvalidAccount.setHorizontalAlignment(SwingConstants.CENTER);
		lblInvalidAccount.setForeground(Color.RED);
		lblInvalidAccount.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblInvalidAccount.setBounds(10, 28, 302, 29);
		pnlInvalidAccount.add(lblInvalidAccount);

		JButton button_1 = new JButton("Ok");
		button_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pnlInvalidAccount.setVisible(false);
				txtPassword.setEnabled(true);
				txtUsername.setEnabled(true);
				txtUsername.setText("");
				txtPassword.setText("");

			}
		});
		button_1.setForeground(Color.WHITE);
		button_1.setFont(new Font("SansSerif", Font.PLAIN, 15));
		button_1.setBorder(null);
		button_1.setBackground(new Color(51, 102, 255));
		button_1.setBounds(127, 87, 66, 35);
		pnlInvalidAccount.add(button_1);
		pnlSuccessLogin.setBackground(new Color(255, 255, 255));
		pnlSuccessLogin.setBounds(525, 291, 322, 133);
		frmLogin.getContentPane().add(pnlSuccessLogin);
		pnlSuccessLogin.setLayout(null);

		JButton button = new JButton("Ok");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				UserWindow su = new UserWindow();
				su.frmUserWindow.setVisible(true);
				frmLogin.dispose();
				su.frmUserWindow.setFocusable(false);	

			}
		});
		button.setForeground(Color.WHITE);
		button.setFont(new Font("SansSerif", Font.PLAIN, 15));
		button.setBorder(null);
		button.setBackground(new Color(51, 102, 255));
		button.setBounds(127, 87, 66, 35);
		pnlSuccessLogin.add(button);

		JLabel lblSuccessfullyLogin = new JLabel("Successfully Login !");
		lblSuccessfullyLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblSuccessfullyLogin.setForeground(Color.GREEN);
		lblSuccessfullyLogin.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblSuccessfullyLogin.setBounds(10, 31, 287, 29);
		pnlSuccessLogin.add(lblSuccessfullyLogin);
		pnlSpecify.setBorder(new LineBorder(new Color(51, 153, 255), 3));
		pnlSpecify.setBackground(new Color(255, 255, 255));
		pnlSpecify.setBounds(525, 291, 322, 133);
		frmLogin.getContentPane().add(pnlSpecify);
		pnlSpecify.setLayout(null);

		JLabel lblSpecifyFields = new JLabel("Specify Field(s) !");
		lblSpecifyFields.setHorizontalAlignment(SwingConstants.CENTER);
		lblSpecifyFields.setForeground(Color.RED);
		lblSpecifyFields.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblSpecifyFields.setBounds(25, 25, 287, 29);
		pnlSpecify.add(lblSpecifyFields);

		JButton btnOk = new JButton("Ok");
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pnlSpecify.setVisible(false);
				txtPassword.setEnabled(true);
				txtUsername.setEnabled(true);
			}
		});
		btnOk.setForeground(Color.WHITE);
		btnOk.setFont(new Font("SansSerif", Font.PLAIN, 15));
		btnOk.setBorder(null);
		btnOk.setBackground(new Color(51, 102, 255));
		btnOk.setBounds(125, 87, 66, 35);
		pnlSpecify.add(btnOk);
		pnlExit.setBorder(new LineBorder(new Color(51, 153, 255), 4));
		pnlExit.setBackground(Color.WHITE);
		pnlExit.setBounds(525, 291, 322, 133);
		frmLogin.getContentPane().add(pnlExit);
		pnlExit.setLayout(null);

		final JButton btnForgotPassword = new JButton("Forgot Password ?");
		btnForgotPassword.setFont(new Font("Tahoma", Font.ITALIC, 14));
		btnForgotPassword.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ForgotPassword fsu = new ForgotPassword();
				fsu.setVisible(true);
				fsu.setLocationRelativeTo(null);
				fsu.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
				frmLogin.setFocusable(false);
			}
		});
		btnForgotPassword.setBackground(Color.WHITE);
		btnForgotPassword.setBorder(null);
		btnForgotPassword.setBounds(610, 591, 138, 23);
		frmLogin.getContentPane().add(btnForgotPassword);

		final JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int put = 0;

				TestConnection t = new TestConnection();
				DatabaseManager dm = new DatabaseManager();
				if(txtUsername.getText().equals("")||txtPassword.getPassword().equals("")){
					pnlSpecify.setVisible(true);
					txtPassword.setEnabled(false);
					txtUsername.setEnabled(false);
				}else{
					try {
						ResultSet rsSuper = dm.accountSuper(t.getConnection());

						while(rsSuper.next()){

							if(txtUsername.getText().equals(rsSuper.getString("username")) && new String(txtPassword.getPassword()).equals(rsSuper.getString("password"))){			
								pnlSuccessLogin.setVisible(true);
							}else{
								put=1;

							}
						}
						rsSuper.close();
						t.getConnection().close();

					} catch (ClassNotFoundException | SQLException e2) {
						e2.printStackTrace();

					}

					if(put==1){
						pnlInvalidAccount.setVisible(true);
						txtUsername.setEnabled(false);
						txtPassword.setEnabled(false);
					}

				}
			}
		});
		btnLogin.setForeground(Color.WHITE);
		btnLogin.setFont(new Font("SansSerif", Font.PLAIN, 15));
		btnLogin.setBorder(null);
		btnLogin.setBackground(new Color(51, 102, 255));
		btnLogin.setBounds(574, 543, 89, 35);
		frmLogin.getContentPane().add(btnLogin);

		final JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pnlExit.setVisible(true);
			}
		});
		btnCancel.setForeground(Color.WHITE);
		btnCancel.setFont(new Font("SansSerif", Font.PLAIN, 15));
		btnCancel.setBorder(null);
		btnCancel.setBackground(new Color(51, 102, 255));
		btnCancel.setBounds(694, 543, 89, 35);
		frmLogin.getContentPane().add(btnCancel);

		JButton btnClose = new JButton("X");
		btnClose.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pnlExit.setVisible(true);
				txtUsername.setEnabled(false);
				txtPassword.setEnabled(false);
				btnLogin.setEnabled(false);
				btnCancel.setEnabled(false);
				btnForgotPassword.setEnabled(false);


			}
		});
		btnClose.setForeground(Color.WHITE);
		btnClose.setFont(new Font("SansSerif", Font.BOLD, 11));
		btnClose.setBorder(null);
		btnClose.setBackground(new Color(0, 153, 255));
		btnClose.setBounds(1329, 0, 37, 22);
		frmLogin.getContentPane().add(btnClose);



		JLabel lblAreYouSure = new JLabel("Are you sure you want to Exit ?");
		lblAreYouSure.setHorizontalAlignment(SwingConstants.CENTER);
		lblAreYouSure.setForeground(Color.RED);
		lblAreYouSure.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblAreYouSure.setBounds(10, 11, 287, 29);
		pnlExit.add(lblAreYouSure);

		JButton btnYes = new JButton("Yes");
		btnYes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnYes.setForeground(Color.WHITE);
		btnYes.setFont(new Font("SansSerif", Font.PLAIN, 15));
		btnYes.setBorder(null);
		btnYes.setBackground(new Color(51, 102, 255));
		btnYes.setBounds(60, 85, 66, 35);
		pnlExit.add(btnYes);

		JButton btnNo = new JButton("No");
		btnNo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pnlExit.setVisible(false);
				txtUsername.setEnabled(true);
				txtPassword.setEnabled(true);
				btnLogin.setEnabled(true);
				btnCancel.setEnabled(true);
				btnForgotPassword.setEnabled(true);
			}
		});
		btnNo.setForeground(Color.WHITE);
		btnNo.setFont(new Font("SansSerif", Font.PLAIN, 15));
		btnNo.setBorder(null);
		btnNo.setBackground(new Color(51, 102, 255));
		btnNo.setBounds(197, 85, 66, 35);
		pnlExit.add(btnNo);

		JButton btnMinimize = new JButton("_");
		btnMinimize.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				frmLogin.setState(Frame.ICONIFIED);
			}
		});
		btnMinimize.setForeground(Color.WHITE);
		btnMinimize.setFont(new Font("SansSerif", Font.BOLD, 11));
		btnMinimize.setBorder(null);
		btnMinimize.setBackground(new Color(0, 51, 255));
		btnMinimize.setBounds(1291, 0, 37, 22);
		frmLogin.getContentPane().add(btnMinimize);

		JLabel lblClock = new JLabel("");
		new DigitalClock(lblClock);
		lblClock.setHorizontalAlignment(SwingConstants.CENTER);
		lblClock.setForeground(new Color(51, 51, 255));
		lblClock.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblClock.setBounds(718, 256, 209, 35);
		frmLogin.getContentPane().add(lblClock);

		JLabel label = new JLabel("Shoe This Inventory Management System");
		label.setForeground(Color.WHITE);
		label.setFont(new Font("SansSerif", Font.PLAIN, 15));
		label.setBounds(10, 0, 463, 23);
		frmLogin.getContentPane().add(label);
		JLabel lblDate = new JLabel(dateFormat.format(date));
		lblDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblDate.setForeground(new Color(51, 51, 255));
		lblDate.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblDate.setBounds(470, 256, 209, 35);
		frmLogin.getContentPane().add(lblDate);

		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(LoginWindow.class.getResource("/app/image/title.png")));
		label_1.setBounds(0, 0, 1366, 22);
		frmLogin.getContentPane().add(label_1);

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

		frmLogin.setBackground(Color.WHITE);
		frmLogin.setUndecorated(true);
		frmLogin.setBounds(0, 0, 1366, 768);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
