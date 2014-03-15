package app.edit;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import java.awt.Color;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.ImageIcon;

import app.add.AddColor;
import app.db.DatabaseManager;
import app.db.TestConnection;
import app.model.Colors;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.SystemColor;
import java.sql.SQLException;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class EditColor extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtColorId;
	private JTextField txtColorName;

	
	public EditColor(final Colors c) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				txtColorId.setText(Integer.toString(c.getColorId()));
				txtColorName.setText(c.getColor());
			}
		});
		setUndecorated(true);
		setBounds(100, 100, 229, 202);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 255, 255));
		contentPanel.setBorder(new LineBorder(new Color(51, 153, 255)));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		final JPanel pnlSuccessEdit = new JPanel();
		pnlSuccessEdit.setVisible(false);
		pnlSuccessEdit.setBorder(new LineBorder(new Color(51, 153, 255), 3));
		pnlSuccessEdit.setBounds(10, 29, 209, 123);
		contentPanel.add(pnlSuccessEdit);
		pnlSuccessEdit.setLayout(null);
		
		final JLabel label_3 = new JLabel("Succesfully Edited !");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setForeground(Color.GREEN);
		label_3.setFont(new Font("SansSerif", Font.PLAIN, 20));
		label_3.setBounds(-39, 28, 287, 29);
		pnlSuccessEdit.add(label_3);
		
		JButton button_2 = new JButton("OK");
		button_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button_2.setForeground(Color.WHITE);
		button_2.setFont(new Font("SansSerif", Font.PLAIN, 15));
		button_2.setBorder(null);
		button_2.setBackground(new Color(51, 102, 255));
		button_2.setBounds(63, 77, 89, 35);
		pnlSuccessEdit.add(button_2);
		
		JLabel lblEditColor = new JLabel("Edit Color");
		lblEditColor.setForeground(Color.WHITE);
		lblEditColor.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblEditColor.setBounds(10, 0, 162, 23);
		contentPanel.add(lblEditColor);
		
		JButton button = new JButton("X");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button.setForeground(Color.WHITE);
		button.setFont(new Font("SansSerif", Font.BOLD, 11));
		button.setBorder(null);
		button.setBackground(new Color(0, 51, 255));
		button.setBounds(192, 1, 37, 22);
		contentPanel.add(button);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(EditColor.class.getResource("/app/image/title.png")));
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("SansSerif", Font.PLAIN, 15));
		label_1.setBounds(0, 0, 450, 23);
		contentPanel.add(label_1);
		
		txtColorId = new JTextField();
		txtColorId.setOpaque(false);
		txtColorId.setHorizontalAlignment(SwingConstants.CENTER);
		txtColorId.setForeground(Color.BLACK);
		txtColorId.setFont(new Font("SansSerif", Font.PLAIN, 15));
		txtColorId.setEditable(false);
		txtColorId.setColumns(10);
		txtColorId.setBorder(new LineBorder(new Color(51, 153, 255)));
		txtColorId.setBounds(10, 61, 209, 29);
		contentPanel.add(txtColorId);
		
		JLabel label = new JLabel("Brand Id");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(SystemColor.textHighlight);
		label.setFont(new Font("SansSerif", Font.PLAIN, 20));
		label.setBounds(35, 34, 145, 29);
		contentPanel.add(label);
		
		txtColorName = new JTextField();
		txtColorName.setHorizontalAlignment(SwingConstants.CENTER);
		txtColorName.setForeground(Color.BLACK);
		txtColorName.setFont(new Font("SansSerif", Font.PLAIN, 15));
		txtColorName.setColumns(10);
		txtColorName.setBorder(new LineBorder(new Color(51, 153, 255)));
		txtColorName.setBounds(10, 123, 209, 29);
		contentPanel.add(txtColorName);
		
		JLabel label_2 = new JLabel("Brand Name");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setForeground(SystemColor.textHighlight);
		label_2.setFont(new Font("SansSerif", Font.PLAIN, 20));
		label_2.setBounds(36, 98, 158, 29);
		contentPanel.add(label_2);
		
		JButton button_1 = new JButton("Save");
		button_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TestConnection tc = new TestConnection();
				DatabaseManager dm = new DatabaseManager();
				Colors c = new Colors();

				c.setColorId(Integer.parseInt(txtColorId.getText()));
				c.setColor(txtColorName.getText());

				try {
					int rs = dm.updateColor(tc.getConnection(), c);
					if(rs==1){
							pnlSuccessEdit.setVisible(true);
							AddColor ac = new AddColor();
							ac.setFocusable(true);
								
							
						}
					
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		button_1.setForeground(Color.WHITE);
		button_1.setFont(new Font("SansSerif", Font.PLAIN, 15));
		button_1.setBorder(null);
		button_1.setBackground(new Color(51, 102, 255));
		button_1.setBounds(72, 163, 89, 35);
		contentPanel.add(button_1);
	}
}
