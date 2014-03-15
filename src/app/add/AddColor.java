package app.add;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;

import java.awt.SystemColor;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import app.db.DatabaseManager;
import app.db.TestConnection;

public class AddColor extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtColor;
	private JTable tblColor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AddColor dialog = new AddColor();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AddColor() {
		setUndecorated(true);
		setBounds(900, 320, 307, 363);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new LineBorder(new Color(51, 153, 255)));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblAddColor = new JLabel("Add Color");
		lblAddColor.setForeground(Color.WHITE);
		lblAddColor.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblAddColor.setBounds(10, 0, 162, 23);
		contentPanel.add(lblAddColor);
		
		JButton button = new JButton("X");
		button.setForeground(Color.WHITE);
		button.setFont(new Font("SansSerif", Font.BOLD, 11));
		button.setBorder(null);
		button.setBackground(new Color(0, 51, 255));
		button.setBounds(270, 1, 37, 22);
		contentPanel.add(button);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(AddColor.class.getResource("/app/image/title.png")));
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("SansSerif", Font.PLAIN, 15));
		label_1.setBounds(0, 0, 450, 23);
		contentPanel.add(label_1);
		
		JLabel lblColor = new JLabel("Color");
		lblColor.setForeground(SystemColor.textHighlight);
		lblColor.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblColor.setBounds(123, 34, 105, 29);
		contentPanel.add(lblColor);
		
		txtColor = new JTextField();
		txtColor.setHorizontalAlignment(SwingConstants.CENTER);
		txtColor.setForeground(Color.BLACK);
		txtColor.setFont(new Font("SansSerif", Font.PLAIN, 15));
		txtColor.setColumns(10);
		txtColor.setBorder(new LineBorder(new Color(51, 153, 255)));
		txtColor.setBounds(44, 59, 209, 29);
		contentPanel.add(txtColor);
		
		JButton button_1 = new JButton("Save");
		button_1.setForeground(Color.WHITE);
		button_1.setFont(new Font("SansSerif", Font.PLAIN, 15));
		button_1.setBorder(null);
		button_1.setBackground(new Color(51, 102, 255));
		button_1.setBounds(10, 99, 89, 35);
		contentPanel.add(button_1);
		
		JButton button_2 = new JButton("Edit");
		button_2.setForeground(Color.WHITE);
		button_2.setFont(new Font("SansSerif", Font.PLAIN, 15));
		button_2.setBorder(null);
		button_2.setBackground(new Color(51, 102, 255));
		button_2.setBounds(109, 99, 89, 35);
		contentPanel.add(button_2);
		
		JButton button_3 = new JButton("Delete");
		button_3.setForeground(Color.WHITE);
		button_3.setFont(new Font("SansSerif", Font.PLAIN, 15));
		button_3.setBorder(null);
		button_3.setBackground(new Color(51, 102, 255));
		button_3.setBounds(207, 99, 89, 35);
		contentPanel.add(button_3);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 141, 287, 211);
		contentPanel.add(scrollPane);
		
		tblColor = new JTable();
		tblColor.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Color Id", "Color"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(tblColor);
	}
	public void updateColorTable(){
		TestConnection tc = new TestConnection();
		DatabaseManager dm = new DatabaseManager();
		DefaultTableModel model = (DefaultTableModel)tblColor.getModel();
		model.getDataVector().removeAllElements();
		tblColor.updateUI();
		
		try {
			ResultSet rs = dm.color(tc.getConnection());
			while(rs.next()){
				String colorId = rs.getString("colorId");
				String color = rs.getString("color");
				model.addRow(new Object[]{colorId,color});
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
