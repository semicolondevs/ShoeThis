package app.edit;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import app.add.AddBrand;
import app.db.DatabaseManager;
import app.db.TestConnection;
import app.model.Brands;

import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.JLabel;

import java.awt.SystemColor;

import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.awt.event.WindowFocusListener;

public class EditBrand extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtBrandId;
	private JTextField txtBrandName;


	public EditBrand(final Brands b) {
		addWindowFocusListener(new WindowFocusListener() {
			@Override
			public void windowGainedFocus(WindowEvent arg0) {
			}
			@Override
			public void windowLostFocus(WindowEvent arg0) {
				dispose();
			}
		});
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				txtBrandId.setText(Integer.toString(b.getBrandId()));
				txtBrandName.setText(b.getBrandName());
			}
		});
		setUndecorated(true);
		setBounds(100, 100, 229, 202);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new LineBorder(new Color(51, 153, 255)));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		final JPanel pnlSuccessEdit = new JPanel();
		pnlSuccessEdit.setVisible(false);
		pnlSuccessEdit.setBorder(new LineBorder(new Color(51, 153, 255), 3));
		pnlSuccessEdit.setBackground(new Color(255, 255, 255));
		pnlSuccessEdit.setBounds(10, 33, 209, 123);
		contentPanel.add(pnlSuccessEdit);
		pnlSuccessEdit.setLayout(null);
		
		JLabel lblSuccesfullyEdited = new JLabel("Succesfully Edited !");
		lblSuccesfullyEdited.setHorizontalAlignment(SwingConstants.CENTER);
		lblSuccesfullyEdited.setForeground(Color.GREEN);
		lblSuccesfullyEdited.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblSuccesfullyEdited.setBounds(-41, 28, 287, 29);
		pnlSuccessEdit.add(lblSuccesfullyEdited);
		
		JButton button = new JButton("OK");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button.setForeground(Color.WHITE);
		button.setFont(new Font("SansSerif", Font.PLAIN, 15));
		button.setBorder(null);
		button.setBackground(new Color(51, 102, 255));
		button.setBounds(61, 77, 89, 35);
		pnlSuccessEdit.add(button);
		{
			txtBrandId = new JTextField();
			txtBrandId.setOpaque(false);
			txtBrandId.setEditable(false);
			txtBrandId.setHorizontalAlignment(SwingConstants.CENTER);
			txtBrandId.setForeground(Color.BLACK);
			txtBrandId.setFont(new Font("SansSerif", Font.PLAIN, 15));
			txtBrandId.setColumns(10);
			txtBrandId.setBorder(new LineBorder(new Color(51, 153, 255)));
			txtBrandId.setBounds(10, 60, 209, 29);
			contentPanel.add(txtBrandId);
		}
		{
			JLabel lblBrandId = new JLabel("Brand Id");
			lblBrandId.setHorizontalAlignment(SwingConstants.CENTER);
			lblBrandId.setForeground(SystemColor.textHighlight);
			lblBrandId.setFont(new Font("SansSerif", Font.PLAIN, 20));
			lblBrandId.setBounds(35, 33, 145, 29);
			contentPanel.add(lblBrandId);
		}
		{
			txtBrandName = new JTextField();
			txtBrandName.setHorizontalAlignment(SwingConstants.CENTER);
			txtBrandName.setForeground(Color.BLACK);
			txtBrandName.setFont(new Font("SansSerif", Font.PLAIN, 15));
			txtBrandName.setColumns(10);
			txtBrandName.setBorder(new LineBorder(new Color(51, 153, 255)));
			txtBrandName.setBounds(10, 122, 209, 29);
			contentPanel.add(txtBrandName);
		}
		{
			JLabel lblBrandName = new JLabel("Brand Name");
			lblBrandName.setHorizontalAlignment(SwingConstants.CENTER);
			lblBrandName.setForeground(SystemColor.textHighlight);
			lblBrandName.setFont(new Font("SansSerif", Font.PLAIN, 20));
			lblBrandName.setBounds(36, 97, 158, 29);
			contentPanel.add(lblBrandName);
		}

		JLabel lblEditBrand = new JLabel("Edit Brand");
		lblEditBrand.setForeground(Color.WHITE);
		lblEditBrand.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblEditBrand.setBounds(10, -1, 162, 23);
		contentPanel.add(lblEditBrand);

		JButton btnCloseEditBrand = new JButton("X");
		btnCloseEditBrand.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCloseEditBrand.setForeground(Color.WHITE);
		btnCloseEditBrand.setFont(new Font("SansSerif", Font.BOLD, 11));
		btnCloseEditBrand.setBorder(null);
		btnCloseEditBrand.setBackground(new Color(0, 51, 255));
		btnCloseEditBrand.setBounds(192, 0, 37, 22);
		contentPanel.add(btnCloseEditBrand);

		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(EditBrand.class.getResource("/app/image/title.png")));
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("SansSerif", Font.PLAIN, 15));
		label_1.setBounds(0, -1, 450, 23);
		contentPanel.add(label_1);

		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				TestConnection tc = new TestConnection();
				DatabaseManager dm = new DatabaseManager();
				Brands b = new Brands();

				b.setBrandId(Integer.parseInt(txtBrandId.getText()));
				b.setBrandName(txtBrandName.getText());

				try {
					int rs = dm.updateBrand(tc.getConnection(), b);
					if(rs==1){
						
								pnlSuccessEdit.setVisible(true);
								AddBrand ab = new AddBrand(null);
								ab.setFocusable(true);
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
		btnSave.setBounds(72, 162, 89, 35);
		contentPanel.add(btnSave);
	}
	
}
