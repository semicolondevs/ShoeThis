package app.ui;

import java.awt.Frame;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.WindowConstants;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

import javax.swing.border.LineBorder;
import javax.swing.JTable;
import javax.swing.JScrollPane;

import java.awt.Font;

import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.SystemColor;

import javax.swing.JComboBox;
import app.add.AddBrand;
import app.add.AddItem;
import app.changePassword.AdminChangePassword;
import app.db.DatabaseManager;
import app.db.TestConnection;
import app.edit.EditItem;
import app.model.DeleteLogs;
import app.model.Delivery;
import app.model.Inventory;
import app.util.DigitalClock;

import java.awt.event.MouseAdapter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import javax.swing.ListSelectionModel;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class AdminWindow {

	public JFrame frmAdmin;
	public JPanel SuccessEdit;
	private JTable tblItems;
	private JTextField txtSearchItem;
	private JComboBox cmbBrand;
	private JComboBox cmbColor;
	private JComboBox cmbSize;
	private String search;
	private String code;
	private DateFormat currentDate = new SimpleDateFormat("MMMM dd,yyyy");
	private DateFormat deliveryDate = new SimpleDateFormat("MMMM dd,yyyy");
	private Inventory get = new Inventory();
	private JTextField txtSearchData;
	private JTable tblItemData;
	private JTable tblDeleted;
	private JTextField txtDeliverTo;
	private JTextField txtAddress;
	private JTextField txtQuantityDeliver;
	private JTextField txtDate;
	private JTable tblDeliveryItems;
	private JTextField txtSearchDeliver;
	private JTable tblDeliveryReports;

	public AdminWindow() {
		initialize();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAdmin = new JFrame();
		frmAdmin.addWindowFocusListener(new WindowFocusListener() {
			@Override
			public void windowGainedFocus(WindowEvent arg0) {
				updateItemTable();
				updateDeleteLogsTable();
				updateDeliverTable();
			}
			@Override
			public void windowLostFocus(WindowEvent arg0) {
			}
		});
		frmAdmin.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				updateItemTable();
				updateDeleteLogsTable();
				updateDeliverTable();

			}
		});
		frmAdmin.setUndecorated(true);
		frmAdmin.setBounds(0, 0, 1366, 768);
		frmAdmin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAdmin.getContentPane().setLayout(null);

		JButton btnExitSystem = new JButton("X");
		btnExitSystem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(frmAdmin, "Are you sure you want to exit ?","PROMPT",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==0){
					System.exit(0);
				}
			}
		});
		btnExitSystem.setBounds(1329, 0, 37, 22);
		frmAdmin.getContentPane().add(btnExitSystem);
		btnExitSystem.setForeground(Color.WHITE);
		btnExitSystem.setFont(new Font("SansSerif", Font.BOLD, 11));
		btnExitSystem.setBorder(null);
		btnExitSystem.setBackground(new Color(0, 153, 255));

		JButton btnMinimizeSystem = new JButton("_");
		btnMinimizeSystem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frmAdmin.setState(Frame.ICONIFIED);
			}
		});
		btnMinimizeSystem.setForeground(Color.WHITE);
		btnMinimizeSystem.setFont(new Font("SansSerif", Font.BOLD, 11));
		btnMinimizeSystem.setBorder(null);
		btnMinimizeSystem.setBackground(new Color(0, 51, 255));
		btnMinimizeSystem.setBounds(1291, 0, 37, 22);
		frmAdmin.getContentPane().add(btnMinimizeSystem);

		JLabel lblShoeThisInventory = new JLabel("Shoe This Inventory Management System");
		lblShoeThisInventory.setBounds(10, 0, 463, 23);
		frmAdmin.getContentPane().add(lblShoeThisInventory);
		lblShoeThisInventory.setForeground(Color.WHITE);
		lblShoeThisInventory.setFont(new Font("SansSerif", Font.PLAIN, 15));

		JLabel tittlebar = new JLabel("");
		tittlebar.setIcon(new ImageIcon(AdminWindow.class.getResource("/app/image/title.png")));
		tittlebar.setBounds(0, 0, 1366, 22);
		frmAdmin.getContentPane().add(tittlebar);

		JPanel sideBar = new JPanel();
		sideBar.setBackground(Color.WHITE);
		sideBar.setBounds(0, 0, 300, 768);
		frmAdmin.getContentPane().add(sideBar);
		sideBar.setLayout(null);

		final JButton btnInventoryManagement = new JButton("");
		final JButton btnShoesMasterData = new JButton("");
		final JButton btnDelivery = new JButton("");
		final JPanel InventoryManagement = new JPanel();
		final JPanel ItemMasterData = new JPanel();
		final JPanel Delivery = new JPanel();
		ItemMasterData.setVisible(false);
		btnInventoryManagement.setBackground(Color.WHITE);
		btnInventoryManagement.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				InventoryManagement.setVisible(true);
				InventoryManagement.setBounds(310, 33, 1046, 724);
				ItemMasterData.setVisible(false);
				Delivery.setVisible(false);
				btnInventoryManagement.setIcon(new ImageIcon(AdminWindow.class.getResource("/app/image/IMActive.png")));
				btnShoesMasterData.setIcon(new ImageIcon(AdminWindow.class.getResource("/app/image/IMD.png")));
				btnDelivery.setIcon(new ImageIcon(AdminWindow.class.getResource("/app/image/delivery.png")));
			}
		});
		btnInventoryManagement.setIcon(new ImageIcon(AdminWindow.class.getResource("/app/image/IM.png")));
		btnInventoryManagement.setBorder(null);
		btnInventoryManagement.setBounds(0, 157, 300, 100);
		sideBar.add(btnInventoryManagement);

		btnShoesMasterData.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0){
				InventoryManagement.setVisible(false);
				ItemMasterData.setVisible(true);
				Delivery.setVisible(false);
				ItemMasterData.setBounds(310, 33, 1046, 724);
				btnShoesMasterData.setIcon(new ImageIcon(AdminWindow.class.getResource("/app/image/IMDActive.png")));
				btnInventoryManagement.setIcon(new ImageIcon(AdminWindow.class.getResource("/app/image/IM.png")));
				btnDelivery.setIcon(new ImageIcon(AdminWindow.class.getResource("/app/image/delivery.png")));
			}
		});
		btnShoesMasterData.setIcon(new ImageIcon(AdminWindow.class.getResource("/app/image/IMD.png")));
		btnShoesMasterData.setBorder(null);
		btnShoesMasterData.setBounds(0, 280, 300, 100);
		sideBar.add(btnShoesMasterData);

		btnDelivery.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				InventoryManagement.setVisible(false);
				ItemMasterData.setVisible(false);
				Delivery.setVisible(true);
				Delivery.setBounds(310, 33, 1046, 724);
				btnDelivery.setIcon(new ImageIcon(AdminWindow.class.getResource("/app/image/deliveryActive.png")));
				btnInventoryManagement.setIcon(new ImageIcon(AdminWindow.class.getResource("/app/image/IM.png")));
				btnShoesMasterData.setIcon(new ImageIcon(AdminWindow.class.getResource("/app/image/IMD.png")));
			}
		});
		btnDelivery.setIcon(new ImageIcon(AdminWindow.class.getResource("/app/image/delivery.png")));
		btnDelivery.setBorder(null);
		btnDelivery.setBounds(0, 401, 300, 100);
		sideBar.add(btnDelivery);


		JLabel lblClock = new JLabel("");
		new DigitalClock(lblClock);
		lblClock.setForeground(Color.WHITE);
		lblClock.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblClock.setBounds(147, 29, 143, 23);
		sideBar.add(lblClock);

		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(frmAdmin, "Are you sure you want to Logout ?","PROMPT",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==0){
					LoginWindow l = new LoginWindow();
					l.frmLogin.setVisible(true);
					frmAdmin.dispose();
				}
			}
		});
		btnLogout.setBorder(null);
		btnLogout.setBackground(new Color(51, 102, 255));
		btnLogout.setForeground(Color.WHITE);
		btnLogout.setFont(new Font("SansSerif", Font.PLAIN, 12));
		btnLogout.setBounds(10, 29, 89, 23);
		sideBar.add(btnLogout);

		JLabel lblModule = new JLabel("Modules");
		lblModule.setHorizontalAlignment(SwingConstants.CENTER);
		lblModule.setForeground(new Color(255, 255, 255));
		lblModule.setFont(new Font("SansSerif", Font.PLAIN, 22));
		lblModule.setBounds(0, 91, 300, 55);
		sideBar.add(lblModule);

		JButton btnchangePassword = new JButton("*Change Password");
		btnchangePassword.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				AdminChangePassword acp = new AdminChangePassword();
				acp.setVisible(true);
				acp.setLocationRelativeTo(null);
				acp.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
				acp.setAlwaysOnTop(true);
				frmAdmin.setFocusable(false);

			}
		});
		btnchangePassword.setOpaque(false);
		btnchangePassword.setHorizontalAlignment(SwingConstants.LEADING);
		btnchangePassword.setForeground(Color.WHITE);
		btnchangePassword.setFont(new Font("SansSerif", Font.ITALIC, 12));
		btnchangePassword.setBorder(null);
		btnchangePassword.setBackground(new Color(51, 102, 255));
		btnchangePassword.setBounds(0, 745, 120, 23);
		sideBar.add(btnchangePassword);

		JLabel sideBarBG = new JLabel("");
		sideBarBG.setIcon(new ImageIcon(AdminWindow.class.getResource("/app/image/sidebar.png")));
		sideBarBG.setBounds(0, 0, 300, 768);
		sideBar.add(sideBarBG);

		InventoryManagement.setVisible(false);
		InventoryManagement.setBackground(new Color(255, 255, 255));
		InventoryManagement.setBorder(new LineBorder(new Color(51, 153, 255)));
		InventoryManagement.setBounds(310, 33, 0, 0);
		frmAdmin.getContentPane().add(InventoryManagement);
		InventoryManagement.setLayout(null);

		JButton btnCloseIM = new JButton("X");
		btnCloseIM.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(frmAdmin, "Are you sure you want to close ?","PROMPT",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==0){
					InventoryManagement.setVisible(false);
					btnInventoryManagement.setIcon(new ImageIcon(AdminWindow.class.getResource("/app/image/IM.png")));
				}

			}
		});
		btnCloseIM.setForeground(Color.WHITE);
		btnCloseIM.setFont(new Font("SansSerif", Font.BOLD, 11));
		btnCloseIM.setBorder(null);
		btnCloseIM.setBackground(new Color(0, 51, 255));
		btnCloseIM.setBounds(1009, 0, 37, 23);
		InventoryManagement.add(btnCloseIM);

		JLabel lblInventoryManagement = new JLabel("Inventory Management");
		lblInventoryManagement.setForeground(Color.WHITE);
		lblInventoryManagement.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblInventoryManagement.setBounds(10, 0, 162, 23);
		InventoryManagement.add(lblInventoryManagement);

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(AdminWindow.class.getResource("/app/image/title.png")));
		label.setBounds(0, 0, 1046, 23);
		InventoryManagement.add(label);

		final JPanel SuccessDelete = new JPanel();
		SuccessDelete.setVisible(false);
		SuccessDelete.setOpaque(false);
		SuccessDelete.setBounds(370, 22, 320, 48);
		InventoryManagement.add(SuccessDelete);
		SuccessDelete.setLayout(null);

		JLabel lblSuccessfullyDeleted = new JLabel("Successfully Deleted !");
		lblSuccessfullyDeleted.setForeground(new Color(51, 255, 0));
		lblSuccessfullyDeleted.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblSuccessfullyDeleted.setBounds(0, 0, 211, 56);
		SuccessDelete.add(lblSuccessfullyDeleted);

		JButton button_3 = new JButton("OK");
		button_3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				SuccessDelete.setVisible(false);
			}
		});
		button_3.setForeground(Color.WHITE);
		button_3.setFont(new Font("SansSerif", Font.PLAIN, 15));
		button_3.setBorder(null);
		button_3.setBackground(new Color(51, 102, 255));
		button_3.setBounds(179, 12, 46, 35);
		SuccessDelete.add(button_3);

		JScrollPane scrollPaneItems = new JScrollPane();
		scrollPaneItems.setEnabled(false);
		scrollPaneItems.setOpaque(false);
		scrollPaneItems.setBackground(new Color(255, 255, 255));
		scrollPaneItems.setBorder(new LineBorder(new Color(51, 153, 255), 2));
		scrollPaneItems.setBounds(10, 72, 1026, 597);
		InventoryManagement.add(scrollPaneItems);

		tblItems = new JTable();
		tblItems.setRowHeight(35);
		tblItems.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblItems.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				get = getSelectedItem();
			}
		});
		tblItems.setBackground(new Color(255, 255, 255));
		tblItems.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Code", "Name", "Brand", "Color", "Size", "Quantity", "Price"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPaneItems.setViewportView(tblItems);

		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				AddItem ai = new AddItem(get);
				ai.setVisible(true);
				ai.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
				ai.setAlwaysOnTop(true);
				frmAdmin.setFocusable(false);


			}
		});
		btnAdd.setForeground(new Color(255, 255, 255));
		btnAdd.setFont(new Font("SansSerif", Font.PLAIN, 15));
		btnAdd.setBackground(new Color(51, 102, 255));
		btnAdd.setBorder(null);
		btnAdd.setBounds(10, 678, 89, 35);
		InventoryManagement.add(btnAdd);

		final JPanel Warning = new JPanel();
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(tblItems.getSelectedRow()==-1){
					Warning.setVisible(true);
				}else{
					try{
						Warning.setVisible(false);
						tblItems.setEnabled(true);
						EditItem ei = new EditItem(getSelectedItem());
						ei.setVisible(true);
						ei.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
						ei.setAlwaysOnTop(true);
						frmAdmin.setFocusable(false);
					}catch(ArrayIndexOutOfBoundsException aio){
						Warning.setVisible(true);
					}

				}

			}

		});
		btnEdit.setForeground(Color.WHITE);
		btnEdit.setFont(new Font("SansSerif", Font.PLAIN, 15));
		btnEdit.setBorder(null);
		btnEdit.setBackground(new Color(51, 102, 255));
		btnEdit.setBounds(109, 678, 89, 35);
		InventoryManagement.add(btnEdit);

		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(tblItems.getSelectedRow()==-1){
					Warning.setVisible(true);
				}else{
					try{
						if(JOptionPane.showConfirmDialog(frmAdmin, "Are you sure you want to delete?\n\n*This item will go to Delete Logs\n\n(Item Master Data/Delete Logs tab)","PROMPT",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==0){
							Warning.setVisible(false);
							TestConnection tc = new TestConnection();
							DatabaseManager dm = new DatabaseManager();
							String getCode = get.getItemCode();
							String getName = get.getItemName();
							String getBrand = get.getItemBrand();
							String getColor = get.getItemColor();
							String getSize = get.getSize();
							int getQuantity = get.getQuantityAvailable();
							int getPrice = get.getPrice();

							DeleteLogs dl = new DeleteLogs();
							dl.setItemCode(getCode);
							dl.setItemName(getName);
							dl.setItemBrand(getBrand);
							dl.setItemColor(getColor);
							dl.setSize(getSize);
							dl.setQuantityAvailable(getQuantity);
							dl.setPrice(getPrice);
							Date date = new Date();
							dl.setDateDeleted(currentDate.format(date));


							DefaultTableModel model = (DefaultTableModel) tblItems.getModel();
							
							code = (model.getValueAt(tblItems.getSelectedRow(), 0).toString());
							
							
							try {
								int rs = dm.insertDeleteLogs(tc.getConnection(), dl);
								int rs2 = dm.deleteItem(tc.getConnection(), code);
								if(rs==1){
									updateDeleteLogsTable();
								}
								if(rs2==1){
									SuccessDelete.setVisible(true);
									updateItemTable();
								}
							} catch (ClassNotFoundException | SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							updateItemTable();
						}
					}catch(ArrayIndexOutOfBoundsException aoi){
						Warning.setVisible(true);
					}
				}
			}

		});
		btnDelete.setForeground(Color.WHITE);
		btnDelete.setFont(new Font("SansSerif", Font.PLAIN, 15));
		btnDelete.setBorder(null);
		btnDelete.setBackground(new Color(51, 102, 255));
		btnDelete.setBounds(208, 678, 89, 35);
		InventoryManagement.add(btnDelete);

		JLabel lblSearch = new JLabel("Search");
		lblSearch.setForeground(new Color(51, 153, 255));
		lblSearch.setFont(new Font("SansSerif", Font.PLAIN, 24));
		lblSearch.setBounds(10, 34, 79, 29);
		InventoryManagement.add(lblSearch);

		txtSearchItem = new JTextField();
		txtSearchItem.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				TestConnection tc = new TestConnection();
				DatabaseManager dm = new DatabaseManager();
				DefaultTableModel model = (DefaultTableModel)tblItems.getModel();
				model.getDataVector().removeAllElements();
				tblItems.updateUI();

				search = txtSearchItem.getText().toString();

				try {
					ResultSet rs = dm.searchItem(tc.getConnection(), search);
					while(rs.next()){
						String itemCode = rs.getString("ItemCode");
						String itemName = rs.getString("ItemName");
						String itemBrand = rs.getString("ItemBrand");
						String itemColor = rs.getString("ItemColor");
						String itemSize = rs.getString("ItemSize");
						String itemQuantity = rs.getString("ItemQuantity");
						String price = rs.getString("Price");
						model.addRow(new Object[]{itemCode,itemName,itemBrand,itemColor,itemSize,itemQuantity,price});
					}
					tblItems.updateUI();
					rs.close();
					tc.getConnection().close();
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}



			}
		});
		txtSearchItem.setBorder(new LineBorder(new Color(51, 153, 255)));
		txtSearchItem.setForeground(new Color(51, 153, 255));
		txtSearchItem.setHorizontalAlignment(SwingConstants.CENTER);
		txtSearchItem.setFont(new Font("SansSerif", Font.PLAIN, 15));
		txtSearchItem.setBounds(88, 34, 272, 29);
		InventoryManagement.add(txtSearchItem);
		txtSearchItem.setColumns(10);

		Warning.setVisible(false);
		Warning.setOpaque(false);
		Warning.setAutoscrolls(true);
		Warning.setBounds(307, 674, 213, 41);
		InventoryManagement.add(Warning);
		Warning.setLayout(null);

		JLabel lblWarning = new JLabel("Select Item to Proceed !");
		lblWarning.setForeground(Color.RED);
		lblWarning.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblWarning.setBounds(0, 0, 177, 41);
		Warning.add(lblWarning);

		JButton btnOK = new JButton("OK");
		btnOK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Warning.setVisible(false);
			}
		});
		btnOK.setForeground(Color.WHITE);
		btnOK.setFont(new Font("SansSerif", Font.PLAIN, 15));
		btnOK.setBorder(new LineBorder(new Color(51, 153, 255), 2));
		btnOK.setBackground(new Color(51, 102, 255));
		btnOK.setBounds(167, 3, 46, 35);
		Warning.add(btnOK);

		JButton btnAddBrand = new JButton("Add Brand");
		btnAddBrand.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AddBrand ab = new AddBrand(null);
				ab.setVisible(true);
				ab.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
				ab.setAlwaysOnTop(true);
				frmAdmin.setFocusable(false);
			}
		});
		btnAddBrand.setForeground(Color.WHITE);
		btnAddBrand.setFont(new Font("SansSerif", Font.PLAIN, 15));
		btnAddBrand.setBorder(null);
		btnAddBrand.setBackground(new Color(51, 102, 255));
		btnAddBrand.setBounds(947, 680, 89, 35);
		InventoryManagement.add(btnAddBrand);

		ItemMasterData.setBorder(new LineBorder(new Color(51, 153, 255)));
		ItemMasterData.setBackground(new Color(255, 255, 255));
		ItemMasterData.setBounds(310, 33, 0, 0);
		frmAdmin.getContentPane().add(ItemMasterData);
		ItemMasterData.setLayout(null);

		JButton btmCloseIMD = new JButton("X");
		btmCloseIMD.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(frmAdmin, "Are you sure you want to close ?","PROMPT",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==0){
					ItemMasterData.setVisible(false);
					btnShoesMasterData.setIcon(new ImageIcon(AdminWindow.class.getResource("/app/image/IMD.png")));
				}


			}
		});
		btmCloseIMD.setForeground(Color.WHITE);
		btmCloseIMD.setFont(new Font("SansSerif", Font.BOLD, 11));
		btmCloseIMD.setBorder(null);
		btmCloseIMD.setBackground(new Color(0, 51, 255));
		btmCloseIMD.setBounds(1009, 0, 37, 23);
		ItemMasterData.add(btmCloseIMD);

		JLabel lblItemMasterData = new JLabel("Item Master Data");
		lblItemMasterData.setForeground(Color.WHITE);
		lblItemMasterData.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblItemMasterData.setBounds(10, 0, 162, 23);
		ItemMasterData.add(lblItemMasterData);

		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(AdminWindow.class.getResource("/app/image/title.png")));
		label_1.setBounds(0, 0, 1046, 23);
		ItemMasterData.add(label_1);

		JLabel label_2 = new JLabel("Search");
		label_2.setForeground(SystemColor.textHighlight);
		label_2.setFont(new Font("SansSerif", Font.PLAIN, 24));
		label_2.setBounds(10, 34, 79, 29);
		ItemMasterData.add(label_2);

		txtSearchData = new JTextField();
		txtSearchData.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				TestConnection tc = new TestConnection();
				DatabaseManager dm = new DatabaseManager();
				DefaultTableModel model = (DefaultTableModel)tblItemData.getModel();
				model.getDataVector().removeAllElements();
				tblItemData.updateUI();

				search = txtSearchData.getText().toString();

				try {
					ResultSet rs = dm.searchItem(tc.getConnection(), search);
					while(rs.next()){
						String itemCode = rs.getString("ItemCode");
						String itemName = rs.getString("ItemName");
						String itemBrand = rs.getString("ItemBrand");
						String itemColor = rs.getString("ItemColor");
						String itemSize = rs.getString("ItemSize");
						String itemQuantity = rs.getString("ItemQuantity");
						String price = rs.getString("Price");
						model.addRow(new Object[]{itemCode,itemName,itemBrand,itemColor,itemSize,itemQuantity,price});
					}
					tblItemData.updateUI();
					rs.close();
					tc.getConnection().close();
				} catch (ClassNotFoundException | SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}
		});
		txtSearchData.setHorizontalAlignment(SwingConstants.CENTER);
		txtSearchData.setForeground(SystemColor.textHighlight);
		txtSearchData.setFont(new Font("SansSerif", Font.PLAIN, 15));
		txtSearchData.setColumns(10);
		txtSearchData.setBorder(new LineBorder(new Color(51, 153, 255)));
		txtSearchData.setBounds(88, 34, 272, 29);
		ItemMasterData.add(txtSearchData);

		final JScrollPane scrollPaneItemList = new JScrollPane();
		scrollPaneItemList.setBorder(new LineBorder(new Color(51, 153, 255), 2));

		final JScrollPane scrollPaneDeleteItems = new JScrollPane();
		scrollPaneDeleteItems.setBorder(new LineBorder(new Color(51, 153, 255), 2));
		scrollPaneDeleteItems.setBounds(10, 112, 0, 0);
		ItemMasterData.add(scrollPaneDeleteItems);

		tblDeleted = new JTable();
		tblDeleted.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblDeleted.setRowHeight(35);
		tblDeleted.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				get = retrieveItem();
			}
		});
		tblDeleted.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Code", "Name", "Brand", "Color", "Size", "Quantity", "Price", "Date Deleted"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPaneDeleteItems.setViewportView(tblDeleted);
		scrollPaneItemList.setBounds(10, 112, 1026, 566);
		ItemMasterData.add(scrollPaneItemList);

		tblItemData = new JTable();
		tblItemData.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblItemData.setRowHeight(35);
		tblItemData.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Code", "Name", "Brand", "Color", "Size", "Quantity", "Price"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPaneItemList.setViewportView(tblItemData);

		final JButton btnItemList = new JButton("Item List");
		final JButton btnDeletedItems = new JButton("Deleted Items");
		final JButton btnRetreiveItem = new JButton("Retreive Item");
		btnRetreiveItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(tblDeleted.getSelectedRow()==-1){
					JOptionPane.showMessageDialog(frmAdmin, "Select item to retreive !");
				}else{
					TestConnection tc = new TestConnection();
					DatabaseManager dm = new DatabaseManager();
					Inventory i = new Inventory();
					String code = get.getItemCode();
					String name = get.getItemName();
					String brand = get.getItemBrand();
					String color = get.getItemColor();
					String size = get.getSize();
					int quantity = get.getQuantityAvailable();
					int price = get.getPrice();

					i.setItemCode(code);
					i.setItemName(name);
					i.setItemBrand(brand);
					i.setItemColor(color);
					i.setSize(size);
					i.setQuantityAvailable(quantity);
					i.setPrice(price);

					DefaultTableModel model = (DefaultTableModel)tblDeleted.getModel();
					String ItemCode = (model.getValueAt(tblDeleted.getSelectedRow(), 0).toString());

					try {
						int rs = dm.insertInventory(tc.getConnection(), i);
						int rs2 = dm.retrieve(tc.getConnection(), ItemCode);
						if(rs==1){
							updateItemTable();
							updateDeleteLogsTable();
							JOptionPane.showMessageDialog(frmAdmin, "Successfully Retreive !");
						}
						if(rs2==1){
							updateDeleteLogsTable();
							updateItemTable();
						}
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
			}
		});
		btnItemList.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				scrollPaneDeleteItems.setVisible(false);
				scrollPaneItemList.setVisible(true);
				scrollPaneItemList.setBounds(10, 112, 1026, 566);
				btnRetreiveItem.setVisible(false);
			}
		});
		btnItemList.setForeground(Color.WHITE);
		btnItemList.setFont(new Font("SansSerif", Font.PLAIN, 15));
		btnItemList.setBorder(null);
		btnItemList.setBackground(new Color(51, 102, 255));
		btnItemList.setBounds(20, 76, 89, 35);
		ItemMasterData.add(btnItemList);

		btnDeletedItems.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				scrollPaneDeleteItems.setVisible(true);
				scrollPaneDeleteItems.setBounds(10, 112, 1026, 566);
				scrollPaneItemList.setVisible(false);
				btnRetreiveItem.setVisible(true);
			}
		});
		btnDeletedItems.setForeground(Color.WHITE);
		btnDeletedItems.setFont(new Font("SansSerif", Font.PLAIN, 15));
		btnDeletedItems.setBorder(null);
		btnDeletedItems.setBackground(new Color(51, 102, 255));
		btnDeletedItems.setBounds(108, 76, 111, 35);
		ItemMasterData.add(btnDeletedItems);

		btnRetreiveItem.setVisible(false);
		btnRetreiveItem.setForeground(Color.WHITE);
		btnRetreiveItem.setFont(new Font("SansSerif", Font.PLAIN, 15));
		btnRetreiveItem.setBorder(null);
		btnRetreiveItem.setBackground(new Color(51, 102, 255));
		btnRetreiveItem.setBounds(925, 684, 111, 35);
		ItemMasterData.add(btnRetreiveItem);

		Delivery.setVisible(false);
		Delivery.setBackground(new Color(255, 255, 255));
		Delivery.setBorder(new LineBorder(new Color(51, 153, 255)));
		Delivery.setBounds(310, 33, 0, 0);
		frmAdmin.getContentPane().add(Delivery);
		Delivery.setLayout(null);

		JButton btnCloseDelivery = new JButton("X");
		btnCloseDelivery.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(frmAdmin, "Are you sure you want to close ?","PROMPT",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==0){
					Delivery.setVisible(false);
					btnDelivery.setIcon(new ImageIcon(AdminWindow.class.getResource("/app/image/delivery.png")));
				}
			}
		});
		btnCloseDelivery.setForeground(Color.WHITE);
		btnCloseDelivery.setFont(new Font("SansSerif", Font.BOLD, 11));
		btnCloseDelivery.setBorder(null);
		btnCloseDelivery.setBackground(new Color(0, 51, 255));
		btnCloseDelivery.setBounds(1009, 0, 37, 23);
		Delivery.add(btnCloseDelivery);

		JLabel lblDelivery = new JLabel("Delivery");
		lblDelivery.setForeground(Color.WHITE);
		lblDelivery.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblDelivery.setBounds(10, 0, 162, 23);
		Delivery.add(lblDelivery);

		JLabel label_5 = new JLabel("");
		label_5.setIcon(new ImageIcon(AdminWindow.class.getResource("/app/image/title.png")));
		label_5.setBounds(0, 0, 1046, 23);
		Delivery.add(label_5);

		JPanel tabDelivery = new JPanel();
		tabDelivery.setBackground(new Color(255, 255, 255));
		tabDelivery.setBounds(10, 33, 1026, 680);
		Delivery.add(tabDelivery);
		tabDelivery.setLayout(null);

		final JPanel DeliveryTab = new JPanel();
		DeliveryTab.setBackground(new Color(255, 255, 255));
		DeliveryTab.setBounds(0, 35, 1016, 645);
		tabDelivery.add(DeliveryTab);
		DeliveryTab.setLayout(null);

		final JButton btnPrintDeliveryReport = new JButton("Print Delivery Report");
		btnPrintDeliveryReport.setForeground(Color.WHITE);
		btnPrintDeliveryReport.setFont(new Font("SansSerif", Font.PLAIN, 15));
		btnPrintDeliveryReport.setBorder(null);
		btnPrintDeliveryReport.setVisible(false);
		btnPrintDeliveryReport.setBackground(new Color(51, 102, 255));
		btnPrintDeliveryReport.setBounds(10, 634, 172, 35);
		tabDelivery.add(btnPrintDeliveryReport);

		final JScrollPane scrollPaneDeliveryReports = new JScrollPane();
		scrollPaneDeliveryReports.setVisible(false);
		scrollPaneDeliveryReports.setBorder(new LineBorder(new Color(51, 153, 255), 2));
		scrollPaneDeliveryReports.setBounds(0, 35, 0, 0);
		tabDelivery.add(scrollPaneDeliveryReports);

		JButton btnDeliver_1 = new JButton("Deliver");
		btnDeliver_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DeliveryTab.setVisible(true);
				DeliveryTab.setBounds(0, 35, 1016, 645);
				btnPrintDeliveryReport.setVisible(false);
				scrollPaneDeliveryReports.setVisible(false);

			}
		});
		btnDeliver_1.setForeground(Color.WHITE);
		btnDeliver_1.setFont(new Font("SansSerif", Font.PLAIN, 15));
		btnDeliver_1.setBorder(null);
		btnDeliver_1.setBackground(new Color(51, 102, 255));
		btnDeliver_1.setBounds(0, 0, 89, 35);
		tabDelivery.add(btnDeliver_1);

		JButton btnDeliveryReports = new JButton("Delivery Reports");
		btnDeliveryReports.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DeliveryTab.setVisible(false);
				scrollPaneDeliveryReports.setVisible(true);
				scrollPaneDeliveryReports.setBounds(0, 35, 1026, 591);
				btnPrintDeliveryReport.setVisible(true);
			}
		});
		btnDeliveryReports.setForeground(Color.WHITE);
		btnDeliveryReports.setFont(new Font("SansSerif", Font.PLAIN, 15));
		btnDeliveryReports.setBorder(null);
		btnDeliveryReports.setBackground(new Color(51, 102, 255));
		btnDeliveryReports.setBounds(90, 0, 123, 35);
		tabDelivery.add(btnDeliveryReports);

		JPanel DeliveryInfo = new JPanel();
		DeliveryInfo.setBackground(Color.WHITE);
		DeliveryInfo.setBounds(10, 11, 996, 152);
		DeliveryTab.add(DeliveryInfo);
		DeliveryInfo.setLayout(null);

		JLabel lblDeliveryInformation = new JLabel("Delivery Information");
		lblDeliveryInformation.setForeground(SystemColor.textHighlight);
		lblDeliveryInformation.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblDeliveryInformation.setBounds(10, 0, 226, 29);
		DeliveryInfo.add(lblDeliveryInformation);

		JLabel lblReceipient = new JLabel("Deliver to");
		lblReceipient.setForeground(SystemColor.textHighlight);
		lblReceipient.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblReceipient.setBounds(10, 40, 105, 29);
		DeliveryInfo.add(lblReceipient);

		txtDeliverTo = new JTextField();
		txtDeliverTo.setHorizontalAlignment(SwingConstants.CENTER);
		txtDeliverTo.setForeground(Color.BLACK);
		txtDeliverTo.setFont(new Font("SansSerif", Font.PLAIN, 15));
		txtDeliverTo.setColumns(10);
		txtDeliverTo.setBorder(new LineBorder(new Color(51, 153, 255)));
		txtDeliverTo.setBounds(10, 80, 209, 29);
		DeliveryInfo.add(txtDeliverTo);

		JLabel lblAddress = new JLabel("Address");
		lblAddress.setForeground(SystemColor.textHighlight);
		lblAddress.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblAddress.setBounds(243, 40, 105, 29);
		DeliveryInfo.add(lblAddress);

		txtAddress = new JTextField();
		txtAddress.setHorizontalAlignment(SwingConstants.CENTER);
		txtAddress.setForeground(Color.BLACK);
		txtAddress.setFont(new Font("SansSerif", Font.PLAIN, 15));
		txtAddress.setColumns(10);
		txtAddress.setBorder(new LineBorder(new Color(51, 153, 255)));
		txtAddress.setBounds(243, 80, 276, 29);
		DeliveryInfo.add(txtAddress);

		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setForeground(SystemColor.textHighlight);
		lblQuantity.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblQuantity.setBounds(529, 40, 105, 29);
		DeliveryInfo.add(lblQuantity);

		txtQuantityDeliver = new JTextField();
		txtQuantityDeliver.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent getChar) {
				char input = getChar.getKeyChar();
				if(!(Character.isDigit(input)) || (input==KeyEvent.VK_BACK_SPACE) || (input==KeyEvent.VK_DELETE)){
					getChar.consume();
				}
			}
		});
		txtQuantityDeliver.setHorizontalAlignment(SwingConstants.CENTER);
		txtQuantityDeliver.setForeground(Color.BLACK);
		txtQuantityDeliver.setFont(new Font("SansSerif", Font.PLAIN, 15));
		txtQuantityDeliver.setColumns(10);
		txtQuantityDeliver.setBorder(new LineBorder(new Color(51, 153, 255)));
		txtQuantityDeliver.setBounds(529, 80, 105, 29);
		DeliveryInfo.add(txtQuantityDeliver);

		JLabel lblDate = new JLabel("Date");
		lblDate.setForeground(SystemColor.textHighlight);
		lblDate.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblDate.setBounds(644, 40, 105, 29);
		DeliveryInfo.add(lblDate);

		Date date = new Date();
		txtDate = new JTextField(deliveryDate.format(date));
		txtDate.setOpaque(false);
		txtDate.setEditable(false);
		txtDate.setHorizontalAlignment(SwingConstants.CENTER);
		txtDate.setForeground(Color.BLACK);
		txtDate.setFont(new Font("SansSerif", Font.PLAIN, 15));
		txtDate.setColumns(10);
		txtDate.setBorder(new LineBorder(new Color(51, 153, 255)));
		txtDate.setBounds(644, 80, 209, 29);
		DeliveryInfo.add(txtDate);

		final JPanel SelectItemtoDeliver = new JPanel();
		SelectItemtoDeliver.setOpaque(false);
		SelectItemtoDeliver.setVisible(false);
		SelectItemtoDeliver.setBounds(729, 0, 257, 43);
		DeliveryInfo.add(SelectItemtoDeliver);
		SelectItemtoDeliver.setLayout(null);

		JScrollPane scrollPaneDeliveryItems = new JScrollPane();
		scrollPaneDeliveryItems.setBorder(new LineBorder(new Color(51, 153, 255), 2));
		scrollPaneDeliveryItems.setBounds(10, 164, 996, 470);
		DeliveryTab.add(scrollPaneDeliveryItems);

		tblDeliveryItems = new JTable();
		tblDeliveryItems.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblDeliveryItems.setRowHeight(35);
		tblDeliveryItems.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				get = getSelectedDeliverItem();
			}
		});
		tblDeliveryItems.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Code", "Name", "Brand", "Color", "Size", "Quantity", "Price"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPaneDeliveryItems.setViewportView(tblDeliveryItems);

		JButton btnDeliver = new JButton("Deliver");
		btnDeliver.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(tblDeliveryItems.getSelectedRow()==-1){
					SelectItemtoDeliver.setVisible(true);
				}else{
					if(txtDeliverTo.getText().equals("") || txtQuantityDeliver.getText().equals("") || txtAddress.getText().equals("")){
						JOptionPane.showMessageDialog(frmAdmin, "Specify the Fields !");
					}else{
						SelectItemtoDeliver.setVisible(false);
						scrollPaneDeliveryReports.setVisible(false);

						TestConnection tc = new TestConnection();
						DatabaseManager dm = new DatabaseManager();
						Inventory i = new Inventory();
						Delivery d = new Delivery();

						String code = get.getItemCode();
						String name = get.getItemName();
						int quantity = get.getQuantityAvailable();
						int price = get.getPrice();
						int deliveredQuantity = Integer.parseInt(txtQuantityDeliver.getText());
						int totalPrice = price * deliveredQuantity;
						int itemsLeft = quantity - deliveredQuantity;
						if(deliveredQuantity>quantity){
							JOptionPane.showMessageDialog(frmAdmin, "Unsufficient Quantity ! !");
						}else{
							d.setItemCode(code);
							d.setItemName(name);
							d.setReceipient(txtDeliverTo.getText());
							d.setQuantity(deliveredQuantity);
							d.setTotalPrice(totalPrice);
							d.setAddress(txtAddress.getText());
							d.setDateDelivered(txtDate.getText());

							i.setItemCode(code);
							i.setQuantityAvailable(itemsLeft);

							try {
								int rs = dm.insertDelivery(tc.getConnection(), d);
								int rs2 = dm.updateQuantity(tc.getConnection(), i);
								if(rs==1){

									clearText();
									JOptionPane.showMessageDialog(frmAdmin, "Delivery is on Process !");

									updateDeliverTable();
									updateItemTable();
								}

								if(rs2==1){
									updateDeliverTable();
									updateItemTable();
								}

							} catch (ClassNotFoundException | SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				}
			}
		});
		btnDeliver.setForeground(Color.WHITE);
		btnDeliver.setFont(new Font("SansSerif", Font.PLAIN, 15));
		btnDeliver.setBorder(null);
		btnDeliver.setBackground(new Color(51, 102, 255));
		btnDeliver.setBounds(897, 80, 89, 35);
		DeliveryInfo.add(btnDeliver);



		JLabel lblSelectItemTo = new JLabel("Select Item to Deliver !");
		lblSelectItemTo.setForeground(Color.RED);
		lblSelectItemTo.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblSelectItemTo.setBounds(0, 0, 247, 43);
		SelectItemtoDeliver.add(lblSelectItemTo);

		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				SelectItemtoDeliver.setVisible(false);
			}
		});
		btnOk.setForeground(Color.WHITE);
		btnOk.setFont(new Font("SansSerif", Font.PLAIN, 15));
		btnOk.setBorder(null);
		btnOk.setBackground(new Color(51, 102, 255));
		btnOk.setBounds(212, 5, 45, 35);
		SelectItemtoDeliver.add(btnOk);

		JLabel label_7 = new JLabel("Search");
		label_7.setForeground(SystemColor.textHighlight);
		label_7.setFont(new Font("SansSerif", Font.PLAIN, 24));
		label_7.setBounds(10, 120, 79, 29);
		DeliveryInfo.add(label_7);

		txtSearchDeliver = new JTextField();
		txtSearchDeliver.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				TestConnection tc = new TestConnection();
				DatabaseManager dm = new DatabaseManager();
				DefaultTableModel model = (DefaultTableModel)tblDeliveryItems.getModel();
				model.getDataVector().removeAllElements();
				tblDeliveryItems.updateUI();

				search = txtSearchDeliver.getText().toString();

				try {
					ResultSet rs = dm.searchItem(tc.getConnection(), search);
					while(rs.next()){
						String itemCode = rs.getString("ItemCode");
						String itemName = rs.getString("ItemName");
						String itemBrand = rs.getString("ItemBrand");
						String itemColor = rs.getString("ItemColor");
						String itemSize = rs.getString("ItemSize");
						String itemQuantity = rs.getString("ItemQuantity");
						String price = rs.getString("Price");
						model.addRow(new Object[]{itemCode,itemName,itemBrand,itemColor,itemSize,itemQuantity,price});
					}
					tblDeliveryItems.updateUI();
					rs.close();
					tc.getConnection().close();
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}


			}
		});
		txtSearchDeliver.setHorizontalAlignment(SwingConstants.CENTER);
		txtSearchDeliver.setForeground(SystemColor.textHighlight);
		txtSearchDeliver.setFont(new Font("SansSerif", Font.PLAIN, 15));
		txtSearchDeliver.setColumns(10);
		txtSearchDeliver.setBorder(new LineBorder(new Color(51, 153, 255)));
		txtSearchDeliver.setBounds(88, 120, 272, 29);
		DeliveryInfo.add(txtSearchDeliver);

		tblDeliveryReports = new JTable();
		tblDeliveryReports.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblDeliveryReports.setRowHeight(35);
		tblDeliveryReports.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Delivery No", "Item Code", "Item Name", "Receipient", "Quantity", "TotalPrice", "Address", "Date Delivered"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPaneDeliveryReports.setViewportView(tblDeliveryReports);

		JLabel shoeThis = new JLabel("");
		shoeThis.setIcon(new ImageIcon(AdminWindow.class.getResource("/app/image/ShoeThis.png")));
		shoeThis.setBounds(445, 237, 800, 300);
		frmAdmin.getContentPane().add(shoeThis);

		JLabel mainBg = new JLabel("");
		mainBg.setIcon(new ImageIcon(AdminWindow.class.getResource("/app/image/bgwhite.png")));
		mainBg.setBounds(0, 0, 1366, 768);
		frmAdmin.getContentPane().add(mainBg);
	}
	public void clearText(){
		txtDeliverTo.setText("");;
		txtAddress.setText("");
		txtQuantityDeliver.setText("");;

	}
	public void updateItemTable(){
		TestConnection tc = new TestConnection();
		DatabaseManager dm = new DatabaseManager();
		DefaultTableModel modelInventory = (DefaultTableModel)tblItems.getModel();
		DefaultTableModel modelItemData = (DefaultTableModel)tblItemData.getModel();
		DefaultTableModel modelDeliver = (DefaultTableModel)tblDeliveryItems.getModel();
		modelDeliver.getDataVector().removeAllElements();
		modelInventory.getDataVector().removeAllElements();
		modelItemData.getDataVector().removeAllElements();

		tblItems.updateUI();
		tblItemData.updateUI();
		tblDeliveryItems.updateUI();
		try {
			ResultSet rs = dm.inventory(tc.getConnection());
			while (rs.next()) {

				String itemCode = rs.getString("ItemCode");
				String itemName = rs.getString("ItemName");
				String itemBrand = rs.getString("ItemBrand");
				String itemColor = rs.getString("ItemColor");
				String itemSize = rs.getString("ItemSize");
				String itemQuantity = rs.getString("ItemQuantity");
				String price = rs.getString("Price");
				modelInventory.addRow(new Object[]{itemCode,itemName,itemBrand,itemColor,itemSize,itemQuantity,price});
				modelItemData.addRow(new Object[]{itemCode,itemName,itemBrand,itemColor,itemSize,itemQuantity,price});
				modelDeliver.addRow(new Object[]{itemCode,itemName,itemBrand,itemColor,itemSize,itemQuantity,price});

			}
			tblItems.updateUI();
			tblItemData.updateUI();
			tblDeliveryItems.updateUI();
			rs.close();
			tc.getConnection().close();
		} catch (ClassNotFoundException | SQLException e) {

		}	
	}
	public void updateDeleteLogsTable(){
		TestConnection t = new TestConnection();
		DatabaseManager dm = new DatabaseManager();
		DefaultTableModel modelDelete = (DefaultTableModel)tblDeleted.getModel();
		modelDelete.getDataVector().removeAllElements();

		tblDeleted.updateUI();
		try {
			ResultSet rs = dm.deleted(t.getConnection());
			while (rs.next()) {

				String itemCode = rs.getString("ItemCode");
				String itemName = rs.getString("ItemName");
				String itemBrand = rs.getString("ItemBrand");
				String itemColor = rs.getString("ItemColor");
				String itemSize = rs.getString("ItemSize");
				String itemQuantity = rs.getString("ItemQuantity");
				String price = rs.getString("ItemPrice");
				String dateDeleted = rs.getString("DateDeleted");
				modelDelete.addRow(new Object[]{itemCode,itemName,itemBrand,itemColor,itemSize,itemQuantity,price,dateDeleted});

			}
			tblDeleted.updateUI();
			rs.close();
			t.getConnection().close();
		} catch (ClassNotFoundException | SQLException e) {

		}	
	}

	public void updateDeliverTable(){
		TestConnection t = new TestConnection();
		DatabaseManager dm = new DatabaseManager();
		DefaultTableModel modelDeliver = (DefaultTableModel)tblDeliveryReports.getModel();
		modelDeliver.getDataVector().removeAllElements();
		tblDeliveryReports.updateUI();

		try{
			ResultSet rs = dm.deliver(t.getConnection());
			while(rs.next()){
				String deliveryNo = rs.getString("DeliveryNo");
				String itemCode = rs.getString("ItemCode");
				String itemName = rs.getString("ItemName");
				String receipient = rs.getString("Receipient");
				String quantity = rs.getString("Quantity");
				String totalPrice = rs.getString("TotalPrice");
				String address = rs.getString("Address");
				String dateDeliver = rs.getString("DateDelivered");
				modelDeliver.addRow(new Object[]{deliveryNo,itemCode,itemName,receipient,quantity,totalPrice,address,dateDeliver});



			}

			tblDeliveryReports.updateUI();
			rs.close();
			t.getConnection().close();

		}catch(SQLException | ClassNotFoundException e){

		}
	}
	
	public Inventory retrieveItem(){
		Inventory i = new Inventory();
		DefaultTableModel modelItems = (DefaultTableModel)tblDeleted.getModel();


		i.setItemCode(modelItems.getValueAt(tblDeleted.getSelectedRow(), 0).toString());
		i.setItemName(modelItems.getValueAt(tblDeleted.getSelectedRow(), 1).toString());
		i.setItemBrand(modelItems.getValueAt(tblDeleted.getSelectedRow(), 2).toString());
		i.setItemColor(modelItems.getValueAt(tblDeleted.getSelectedRow(), 3).toString());
		i.setSize(modelItems.getValueAt(tblDeleted.getSelectedRow(), 4).toString());
		i.setQuantityAvailable(Integer.parseInt(modelItems.getValueAt(tblDeleted.getSelectedRow(), 5).toString()));
		i.setPrice(Integer.parseInt(modelItems.getValueAt(tblDeleted.getSelectedRow(), 6).toString()));



		return i;
	}

	public Inventory getSelectedItem(){
		Inventory i = new Inventory();
		DefaultTableModel modelItems = (DefaultTableModel)tblItems.getModel();

		i.setItemCode(modelItems.getValueAt(tblItems.getSelectedRow(), 0).toString());
		i.setItemName(modelItems.getValueAt(tblItems.getSelectedRow(), 1).toString());
		i.setItemBrand(modelItems.getValueAt(tblItems.getSelectedRow(), 2).toString());
		i.setItemColor(modelItems.getValueAt(tblItems.getSelectedRow(), 3).toString());
		i.setSize(modelItems.getValueAt(tblItems.getSelectedRow(), 4).toString());
		i.setQuantityAvailable(Integer.parseInt(modelItems.getValueAt(tblItems.getSelectedRow(), 5).toString()));
		i.setPrice(Integer.parseInt(modelItems.getValueAt(tblItems.getSelectedRow(), 6).toString()));

		return i;
	}
	public Inventory getSelectedDeliverItem(){
		Inventory i = new Inventory();
		DefaultTableModel modelItems = (DefaultTableModel)tblDeliveryItems.getModel();

		i.setItemCode(modelItems.getValueAt(tblDeliveryItems.getSelectedRow(), 0).toString());
		i.setItemName(modelItems.getValueAt(tblDeliveryItems.getSelectedRow(), 1).toString());
		i.setItemBrand(modelItems.getValueAt(tblDeliveryItems.getSelectedRow(), 2).toString());
		i.setItemColor(modelItems.getValueAt(tblDeliveryItems.getSelectedRow(), 3).toString());
		i.setSize(modelItems.getValueAt(tblDeliveryItems.getSelectedRow(), 4).toString());
		i.setQuantityAvailable(Integer.parseInt(modelItems.getValueAt(tblDeliveryItems.getSelectedRow(), 5).toString()));
		i.setPrice(Integer.parseInt(modelItems.getValueAt(tblDeliveryItems.getSelectedRow(), 6).toString()));

		return i;
	}
	
}
