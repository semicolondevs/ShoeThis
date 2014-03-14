package app.ui;

import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.Point;

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
import javax.swing.DefaultComboBoxModel;

import app.add.AddBrand;
import app.add.AddItem;
import app.add.AddUser;
import app.changePassword.AdminChangePassword;
import app.changePassword.SuperuserChangePassword;
import app.db.DatabaseManager;
import app.db.TestConnection;
import app.edit.EditItem;
import app.edit.EditUser;
import app.model.DeleteLogs;
import app.model.Delivery;
import app.model.Inventory;
import app.model.User;
import app.util.DigitalClock;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionAdapter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import javax.swing.ListSelectionModel;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTabbedPane;
import javax.swing.JSplitPane;
import javax.swing.JLayeredPane;
import javax.swing.JDesktopPane;
import javax.swing.JMenuBar;

public class SuperUserWindow {

	public JFrame frmSuperUser;
	public JPanel SuccessEdit;
	private JTable tblItems;
	private JTextField txtSearchItem;
	private JComboBox cmbBrand;
	private JComboBox cmbColor;
	private JComboBox cmbSize;
	private String search;
	private String code;
	private String id;
	private DateFormat dateFormat = new SimpleDateFormat("SSSS");
	private DateFormat currentDate = new SimpleDateFormat("MMMM dd,yyyy");
	private DateFormat deliveryDate = new SimpleDateFormat("MMMM dd,yyyy");
	private Inventory get = new Inventory();
	private JTextField txtSearchData;
	private JTable tblItemData;
	private JTable tblDeleted;
	private JTextField txtSearchUser;
	private JTable tblUser;
	private JTextField txtDeliverTo;
	private JTextField txtAddress;
	private JTextField txtQuantityDeliver;
	private JTextField txtDate;
	private JTable tblDeliveryItems;
	private JTextField txtSearchDeliver;
	private JTable tblDeliveryReports;
	private JTable table;
	private JTextField txtCustomerName;

	public SuperUserWindow() {
		initialize();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSuperUser = new JFrame();
		frmSuperUser.addWindowFocusListener(new WindowFocusListener() {
			public void windowGainedFocus(WindowEvent arg0) {
				updateItemTable();
				updateDeleteLogsTable();
				updateUserTable();
				updateDeliverTable();
			}
			public void windowLostFocus(WindowEvent arg0) {
			}
		});
		frmSuperUser.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				updateItemTable();
				updateDeleteLogsTable();
				updateUserTable();
				updateDeliverTable();

			}
		});
		frmSuperUser.setUndecorated(true);
		frmSuperUser.setBounds(0, 0, 1366, 768);
		frmSuperUser.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSuperUser.getContentPane().setLayout(null);

		JButton btnExitSystem = new JButton("X");
		btnExitSystem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(frmSuperUser, "Are you sure you want to exit ?","PROMPT",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==0){
					System.exit(0);
				}
			}
		});
		btnExitSystem.setBounds(1329, 0, 37, 22);
		frmSuperUser.getContentPane().add(btnExitSystem);
		btnExitSystem.setForeground(Color.WHITE);
		btnExitSystem.setFont(new Font("SansSerif", Font.BOLD, 11));
		btnExitSystem.setBorder(null);
		btnExitSystem.setBackground(new Color(0, 153, 255));

		JButton btnMinimizeSystem = new JButton("_");
		btnMinimizeSystem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmSuperUser.setState(Frame.ICONIFIED);
			}
		});
		btnMinimizeSystem.setForeground(Color.WHITE);
		btnMinimizeSystem.setFont(new Font("SansSerif", Font.BOLD, 11));
		btnMinimizeSystem.setBorder(null);
		btnMinimizeSystem.setBackground(new Color(0, 51, 255));
		btnMinimizeSystem.setBounds(1291, 0, 37, 22);
		frmSuperUser.getContentPane().add(btnMinimizeSystem);

		JLabel lblShoeThisInventory = new JLabel("Shoe This Inventory Management System");
		lblShoeThisInventory.setBounds(10, 0, 463, 23);
		frmSuperUser.getContentPane().add(lblShoeThisInventory);
		lblShoeThisInventory.setForeground(Color.WHITE);
		lblShoeThisInventory.setFont(new Font("SansSerif", Font.PLAIN, 15));

		JLabel tittlebar = new JLabel("");
		tittlebar.setIcon(new ImageIcon(SuperUserWindow.class.getResource("/app/image/title.png")));
		tittlebar.setBounds(0, 0, 1366, 22);
		frmSuperUser.getContentPane().add(tittlebar);

		JPanel sideBar = new JPanel();
		sideBar.setBackground(Color.WHITE);
		sideBar.setBounds(0, 0, 300, 768);
		frmSuperUser.getContentPane().add(sideBar);
		sideBar.setLayout(null);
		
		final JPanel Sales = new JPanel();
		Sales.setVisible(false);
		Sales.setBorder(new LineBorder(new Color(51, 153, 255)));
		Sales.setBounds(310, 33, 1046, 724);
		frmSuperUser.getContentPane().add(Sales);
		Sales.setLayout(null);
		
		JButton btnCloseSales = new JButton("X");
		btnCloseSales.setBounds(1009, 0, 37, 23);
		btnCloseSales.setForeground(Color.WHITE);
		btnCloseSales.setFont(new Font("SansSerif", Font.BOLD, 11));
		btnCloseSales.setBorder(null);
		btnCloseSales.setBackground(new Color(0, 51, 255));
		Sales.add(btnCloseSales);
		
		JLabel lblSales = new JLabel("Sales");
		lblSales.setBounds(10, 0, 162, 23);
		lblSales.setForeground(Color.WHITE);
		lblSales.setFont(new Font("SansSerif", Font.PLAIN, 15));
		Sales.add(lblSales);
		
		JLabel label_9 = new JLabel("");
		label_9.setBounds(0, 0, 1046, 23);
		label_9.setIcon(new ImageIcon(SuperUserWindow.class.getResource("/app/image/title.png")));
		Sales.add(label_9);
		
		JButton btnSaleItem = new JButton("Sale Item");
		btnSaleItem.setBounds(10, 34, 89, 35);
		btnSaleItem.setForeground(Color.WHITE);
		btnSaleItem.setFont(new Font("SansSerif", Font.PLAIN, 15));
		btnSaleItem.setBorder(null);
		btnSaleItem.setBackground(new Color(51, 102, 255));
		Sales.add(btnSaleItem);
		
		JButton btnSalesReport = new JButton("Sales Report");
		btnSalesReport.setBounds(100, 34, 123, 35);
		btnSalesReport.setForeground(Color.WHITE);
		btnSalesReport.setFont(new Font("SansSerif", Font.PLAIN, 15));
		btnSalesReport.setBorder(null);
		btnSalesReport.setBackground(new Color(51, 102, 255));
		Sales.add(btnSalesReport);
		
		JPanel CustomerInfo = new JPanel();
		CustomerInfo.setBounds(10, 70, 1026, 156);
		Sales.add(CustomerInfo);
		CustomerInfo.setLayout(null);
		
		JPanel SelectItemToSell = new JPanel();
		SelectItemToSell.setVisible(false);
		SelectItemToSell.setBounds(737, 11, 279, 46);
		CustomerInfo.add(SelectItemToSell);
		SelectItemToSell.setLayout(null);
		
		JLabel label_8 = new JLabel("Select Item to Deliver !");
		label_8.setForeground(Color.RED);
		label_8.setFont(new Font("SansSerif", Font.PLAIN, 18));
		label_8.setBounds(10, 0, 247, 43);
		SelectItemToSell.add(label_8);
		
		JButton button_2 = new JButton("OK");
		button_2.setForeground(Color.WHITE);
		button_2.setFont(new Font("SansSerif", Font.PLAIN, 15));
		button_2.setBorder(null);
		button_2.setBackground(new Color(51, 102, 255));
		button_2.setBounds(222, 5, 45, 35);
		SelectItemToSell.add(button_2);
		
		JLabel lblCustomerInformation = new JLabel("Customer Information");
		lblCustomerInformation.setForeground(SystemColor.textHighlight);
		lblCustomerInformation.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblCustomerInformation.setBounds(20, 11, 226, 29);
		CustomerInfo.add(lblCustomerInformation);
		
		JLabel lblCustomerName = new JLabel("Customer Name");
		lblCustomerName.setForeground(SystemColor.textHighlight);
		lblCustomerName.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblCustomerName.setBounds(20, 51, 193, 29);
		CustomerInfo.add(lblCustomerName);
		
		txtCustomerName = new JTextField();
		txtCustomerName.setHorizontalAlignment(SwingConstants.CENTER);
		txtCustomerName.setForeground(Color.BLACK);
		txtCustomerName.setFont(new Font("SansSerif", Font.PLAIN, 15));
		txtCustomerName.setColumns(10);
		txtCustomerName.setBorder(new LineBorder(new Color(51, 153, 255)));
		txtCustomerName.setBounds(20, 80, 209, 29);
		CustomerInfo.add(txtCustomerName);
		
		JPanel SaleTab = new JPanel();
		SaleTab.setBounds(10, 70, 1026, 643);
		Sales.add(SaleTab);
		SaleTab.setLayout(null);
		
		table = new JTable();
		table.setBounds(0, 157, 1026, 486);
		SaleTab.add(table);

		final JButton btnInventoryManagement = new JButton("");
		btnInventoryManagement.setRolloverIcon(new ImageIcon(SuperUserWindow.class.getResource("/app/image/IMActive.png")));
		final JButton btnShoesMasterData = new JButton("");
		btnShoesMasterData.setRolloverIcon(new ImageIcon(SuperUserWindow.class.getResource("/app/image/IMDActive.png")));
		final JButton btnUserManagement = new JButton("");
		btnUserManagement.setRolloverIcon(new ImageIcon(SuperUserWindow.class.getResource("/app/image/UMActive.png")));
		final JButton btnDelivery = new JButton("");
		btnDelivery.setRolloverIcon(new ImageIcon(SuperUserWindow.class.getResource("/app/image/deliveryActive.png")));
		final JButton btnSales = new JButton("");
		btnSales.setRolloverIcon(new ImageIcon(SuperUserWindow.class.getResource("/app/image/SalesActive.png")));
		final JPanel InventoryManagement = new JPanel();
		final JPanel ItemMasterData = new JPanel();
		final JPanel UserManagement = new JPanel();
		final JPanel Delivery = new JPanel();
		UserManagement.setVisible(false);
		ItemMasterData.setVisible(false);
		btnInventoryManagement.setBackground(Color.WHITE);
		btnInventoryManagement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				InventoryManagement.setVisible(true);
				InventoryManagement.setBounds(310, 33, 1046, 724);
				ItemMasterData.setVisible(false);
				UserManagement.setVisible(false);
				Delivery.setVisible(false);
				Sales.setVisible(false);
				btnInventoryManagement.setIcon(new ImageIcon(SuperUserWindow.class.getResource("/app/image/IMActive.png")));
				btnShoesMasterData.setIcon(new ImageIcon(SuperUserWindow.class.getResource("/app/image/IMD.png")));
				btnUserManagement.setIcon(new ImageIcon(SuperUserWindow.class.getResource("/app/image/UM.png")));
				btnDelivery.setIcon(new ImageIcon(SuperUserWindow.class.getResource("/app/image/delivery.png")));
				btnSales.setIcon(new ImageIcon(SuperUserWindow.class.getResource("/app/image/Sales.png")));
			}
		});
		btnInventoryManagement.setIcon(new ImageIcon(SuperUserWindow.class.getResource("/app/image/IM.png")));
		btnInventoryManagement.setBorder(null);
		btnInventoryManagement.setBounds(0, 129, 300, 100);
		sideBar.add(btnInventoryManagement);

		btnShoesMasterData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){
				InventoryManagement.setVisible(false);
				ItemMasterData.setVisible(true);
				UserManagement.setVisible(false);
				Delivery.setVisible(false);
				Sales.setVisible(false);
				ItemMasterData.setBounds(310, 33, 1046, 724);
				btnShoesMasterData.setIcon(new ImageIcon(SuperUserWindow.class.getResource("/app/image/IMDActive.png")));
				btnInventoryManagement.setIcon(new ImageIcon(SuperUserWindow.class.getResource("/app/image/IM.png")));
				btnUserManagement.setIcon(new ImageIcon(SuperUserWindow.class.getResource("/app/image/UM.png")));
				btnDelivery.setIcon(new ImageIcon(SuperUserWindow.class.getResource("/app/image/delivery.png")));
				btnSales.setIcon(new ImageIcon(SuperUserWindow.class.getResource("/app/image/Sales.png")));
			}
		});
		btnShoesMasterData.setIcon(new ImageIcon(SuperUserWindow.class.getResource("/app/image/IMD.png")));
		btnShoesMasterData.setBorder(null);
		btnShoesMasterData.setBounds(0, 252, 300, 100);
		sideBar.add(btnShoesMasterData);

		btnUserManagement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InventoryManagement.setVisible(false);
				ItemMasterData.setVisible(false);
				UserManagement.setVisible(true);
				Delivery.setVisible(false);
				Sales.setVisible(false);
				UserManagement.setBounds(310, 33, 1046, 724);
				btnUserManagement.setIcon(new ImageIcon(SuperUserWindow.class.getResource("/app/image/UMActive.png")));
				btnDelivery.setIcon(new ImageIcon(SuperUserWindow.class.getResource("/app/image/delivery.png")));
				btnInventoryManagement.setIcon(new ImageIcon(SuperUserWindow.class.getResource("/app/image/IM.png")));
				btnShoesMasterData.setIcon(new ImageIcon(SuperUserWindow.class.getResource("/app/image/IMD.png")));
				btnSales.setIcon(new ImageIcon(SuperUserWindow.class.getResource("/app/image/Sales.png")));

			}
		});
		btnUserManagement.setIcon(new ImageIcon(SuperUserWindow.class.getResource("/app/image/UM.png")));
		btnUserManagement.setBorder(null);
		btnUserManagement.setBounds(0, 374, 300, 100);
		sideBar.add(btnUserManagement);

		btnDelivery.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InventoryManagement.setVisible(false);
				ItemMasterData.setVisible(false);
				UserManagement.setVisible(false);
				Delivery.setVisible(true);
				Sales.setVisible(false);
				Delivery.setBounds(310, 33, 1046, 724);
				btnDelivery.setIcon(new ImageIcon(SuperUserWindow.class.getResource("/app/image/deliveryActive.png")));
				btnInventoryManagement.setIcon(new ImageIcon(SuperUserWindow.class.getResource("/app/image/IM.png")));
				btnShoesMasterData.setIcon(new ImageIcon(SuperUserWindow.class.getResource("/app/image/IMD.png")));
				btnUserManagement.setIcon(new ImageIcon(SuperUserWindow.class.getResource("/app/image/UM.png")));
				btnSales.setIcon(new ImageIcon(SuperUserWindow.class.getResource("/app/image/Sales.png")));
			}
		});
		btnDelivery.setIcon(new ImageIcon(SuperUserWindow.class.getResource("/app/image/delivery.png")));
		btnDelivery.setBorder(null);
		btnDelivery.setBounds(0, 495, 300, 100);
		sideBar.add(btnDelivery);
		
		btnSales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Sales.setVisible(true);
				InventoryManagement.setVisible(false);
				ItemMasterData.setVisible(false);
				UserManagement.setVisible(false);
				Delivery.setVisible(false);
				btnSales.setIcon(new ImageIcon(SuperUserWindow.class.getResource("/app/image/SalesActive.png")));
				btnInventoryManagement.setIcon(new ImageIcon(SuperUserWindow.class.getResource("/app/image/IM.png")));
				btnShoesMasterData.setIcon(new ImageIcon(SuperUserWindow.class.getResource("/app/image/IMD.png")));
				btnUserManagement.setIcon(new ImageIcon(SuperUserWindow.class.getResource("/app/image/UM.png")));
				btnDelivery.setIcon(new ImageIcon(SuperUserWindow.class.getResource("/app/image/delivery.png")));
			}
		});
		btnSales.setIcon(new ImageIcon(SuperUserWindow.class.getResource("/app/image/Sales.png")));
		btnSales.setBorder(null);
		btnSales.setBounds(0, 614, 300, 100);
		sideBar.add(btnSales);


		JLabel lblClock = new JLabel("");
		new DigitalClock(lblClock);
		lblClock.setForeground(Color.WHITE);
		lblClock.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblClock.setBounds(147, 29, 143, 23);
		sideBar.add(lblClock);

		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(frmSuperUser, "Are you sure you want to Logout ?","PROMPT",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==0){
					LoginWindow l = new LoginWindow();
					l.frmLogin.setVisible(true);
					frmSuperUser.dispose();
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
		lblModule.setBounds(0, 63, 300, 55);
		sideBar.add(lblModule);

		JButton btnchangePassword = new JButton("*Change Password");
		btnchangePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SuperuserChangePassword scp = new SuperuserChangePassword();
				scp.setVisible(true);
				scp.setLocationRelativeTo(null);
				scp.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
				scp.setAlwaysOnTop(true);
				frmSuperUser.setFocusable(false);

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
		sideBarBG.setIcon(new ImageIcon(SuperUserWindow.class.getResource("/app/image/sidebar.png")));
		sideBarBG.setBounds(0, 0, 300, 768);
		sideBar.add(sideBarBG);

		InventoryManagement.setVisible(false);
		InventoryManagement.setBackground(new Color(255, 255, 255));
		InventoryManagement.setBorder(new LineBorder(new Color(51, 153, 255)));
		InventoryManagement.setBounds(310, 33, 0, 0);
		frmSuperUser.getContentPane().add(InventoryManagement);
		InventoryManagement.setLayout(null);

		JButton btnCloseIM = new JButton("X");
		btnCloseIM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(frmSuperUser, "Are you sure you want to close ?","PROMPT",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==0){
					InventoryManagement.setVisible(false);
					btnInventoryManagement.setIcon(new ImageIcon(SuperUserWindow.class.getResource("/app/image/IM.png")));
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
		label.setIcon(new ImageIcon(SuperUserWindow.class.getResource("/app/image/title.png")));
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
				"Code", "Name", "Brand", "Color", "Style", "Category", "Size", "Quantity", "Price"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPaneItems.setViewportView(tblItems);

		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddItem ai = new AddItem(get);
				ai.setVisible(true);
				ai.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
				ai.setAlwaysOnTop(true);
				frmSuperUser.setFocusable(false);


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
						frmSuperUser.setFocusable(false);
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
			public void actionPerformed(ActionEvent arg0) {
				if(tblItems.getSelectedRow()==-1){
					Warning.setVisible(true);
				}else{
					try{
						if(JOptionPane.showConfirmDialog(frmSuperUser, "Are you sure you want to delete?\n\n*This item will go to Delete Logs\n\n(Item Master Data/Delete Logs tab)","PROMPT",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==0){
							Warning.setVisible(false);
							TestConnection tc = new TestConnection();
							DatabaseManager dm = new DatabaseManager();
							String getCode = get.getItemCode();
							String getName = get.getItemName();
							String getBrand = get.getItemBrand();
							String getColor = get.getItemColor();
							String getStyle = get.getItemStyle();
							String getCategory = get.getItemCategory();
							String getSize = get.getSize();
							int getQuantity = get.getQuantityAvailable();
							int getPrice = get.getPrice();

							DeleteLogs dl = new DeleteLogs();
							dl.setItemCode(getCode);
							dl.setItemName(getName);
							dl.setItemBrand(getBrand);
							dl.setItemColor(getColor);
							dl.setItemStyle(getStyle);
							dl.setItemCategory(getCategory);
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
			public void actionPerformed(ActionEvent e) {
				AddBrand ab = new AddBrand(null);
				ab.setVisible(true);
				ab.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
				ab.setAlwaysOnTop(true);
				frmSuperUser.setFocusable(false);
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
		frmSuperUser.getContentPane().add(ItemMasterData);
		ItemMasterData.setLayout(null);

		JButton btmCloseIMD = new JButton("X");
		btmCloseIMD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(frmSuperUser, "Are you sure you want to close ?","PROMPT",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==0){
					ItemMasterData.setVisible(false);
					btnShoesMasterData.setIcon(new ImageIcon(SuperUserWindow.class.getResource("/app/image/IMD.png")));
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
		label_1.setIcon(new ImageIcon(SuperUserWindow.class.getResource("/app/image/title.png")));
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
				"Code", "Name", "Brand", "Color", "Style", "Category", "Size", "Quantity", "Price", "Date Deleted"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false, false, false, false
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
				"Code", "Name", "Brand", "Color", "Style", "Category", "Size", "Quantity", "Price"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false, false, false
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
			public void actionPerformed(ActionEvent e) {
				if(tblDeleted.getSelectedRow()==-1){
					JOptionPane.showMessageDialog(frmSuperUser, "Select item to retreive !");
				}else{
					TestConnection tc = new TestConnection();
					DatabaseManager dm = new DatabaseManager();
					Inventory i = new Inventory();
					String code = get.getItemCode();
					String name = get.getItemName();
					String brand = get.getItemBrand();
					String color = get.getItemColor();
					String style = get.getItemStyle();
					String category = get.getItemCategory();
					String size = get.getSize();
					int quantity = get.getQuantityAvailable();
					int price = get.getPrice();

					i.setItemCode(code);
					i.setItemName(name);
					i.setItemBrand(brand);
					i.setItemColor(color);
					i.setItemStyle(style);
					i.setItemCategory(category);
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
							JOptionPane.showMessageDialog(frmSuperUser, "Successfully Retreive !");
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
		btnDeletedItems.setBounds(110, 76, 111, 35);
		ItemMasterData.add(btnDeletedItems);

		btnRetreiveItem.setVisible(false);
		btnRetreiveItem.setForeground(Color.WHITE);
		btnRetreiveItem.setFont(new Font("SansSerif", Font.PLAIN, 15));
		btnRetreiveItem.setBorder(null);
		btnRetreiveItem.setBackground(new Color(51, 102, 255));
		btnRetreiveItem.setBounds(925, 684, 111, 35);
		ItemMasterData.add(btnRetreiveItem);

		UserManagement.setBorder(new LineBorder(new Color(51, 153, 255)));
		UserManagement.setBackground(Color.WHITE);
		UserManagement.setBounds(310, 33, 0, 0);
		frmSuperUser.getContentPane().add(UserManagement);
		UserManagement.setLayout(null);

		JButton btnCloseUM = new JButton("X");
		btnCloseUM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(frmSuperUser, "Are you sure you want to close ?","PROMPT",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==0){
					UserManagement.setVisible(false);
					btnUserManagement.setIcon(new ImageIcon(SuperUserWindow.class.getResource("/app/image/UM.png")));
				}
			}
		});
		btnCloseUM.setForeground(Color.WHITE);
		btnCloseUM.setFont(new Font("SansSerif", Font.BOLD, 11));
		btnCloseUM.setBorder(null);
		btnCloseUM.setBackground(new Color(0, 51, 255));
		btnCloseUM.setBounds(1009, 0, 37, 23);
		UserManagement.add(btnCloseUM);

		JLabel lblUserManagement = new JLabel("User Management");
		lblUserManagement.setForeground(Color.WHITE);
		lblUserManagement.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblUserManagement.setBounds(10, 0, 162, 23);
		UserManagement.add(lblUserManagement);

		JLabel label_3 = new JLabel("");
		label_3.setIcon(new ImageIcon(SuperUserWindow.class.getResource("/app/image/title.png")));
		label_3.setBounds(0, 0, 1046, 23);
		UserManagement.add(label_3);

		JLabel label_4 = new JLabel("Search");
		label_4.setForeground(SystemColor.textHighlight);
		label_4.setFont(new Font("SansSerif", Font.PLAIN, 24));
		label_4.setBounds(10, 34, 79, 29);
		UserManagement.add(label_4);

		txtSearchUser = new JTextField();
		txtSearchUser.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				TestConnection tc = new TestConnection();
				DatabaseManager dm = new DatabaseManager();
				DefaultTableModel modelUser = (DefaultTableModel)tblUser.getModel();
				modelUser.getDataVector().removeAllElements();
				tblUser.updateUI();

				id = txtSearchUser.getText().toString();
				try {
					ResultSet rs = dm.searchUser(tc.getConnection(), id);
					while(rs.next()){
						String id = rs.getString("adminID");
						String username = rs.getString("username");
						String password = rs.getString("password");
						String name = rs.getString("Name");
						String contact = rs.getString("ContactNumber");
						String pin = rs.getString("PinCode");
						modelUser.addRow(new Object[]{id,username,password,name,contact,pin});
					}
					rs.close();
					tc.getConnection().close();
					tblUser.updateUI();
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}


			}
		});
		txtSearchUser.setHorizontalAlignment(SwingConstants.CENTER);
		txtSearchUser.setForeground(SystemColor.textHighlight);
		txtSearchUser.setFont(new Font("SansSerif", Font.PLAIN, 15));
		txtSearchUser.setColumns(10);
		txtSearchUser.setBorder(new LineBorder(new Color(51, 153, 255)));
		txtSearchUser.setBounds(88, 34, 272, 29);
		UserManagement.add(txtSearchUser);

		JScrollPane scrollPaneUser = new JScrollPane();
		scrollPaneUser.setBorder(new LineBorder(new Color(51, 153, 255), 2));
		scrollPaneUser.setBounds(10, 72, 1026, 587);
		UserManagement.add(scrollPaneUser);

		tblUser = new JTable();
		tblUser.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblUser.setRowHeight(35);
		tblUser.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"User ID", "Username", "Password", "Name", "Contact Number", "Pin Code"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPaneUser.setViewportView(tblUser);

		JButton btnAddUser = new JButton("Add");
		btnAddUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddUser au = new AddUser(null);
				au.setVisible(true);
				au.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
				au.setAlwaysOnTop(true);
				frmSuperUser.setFocusable(false);
			}
		});
		btnAddUser.setForeground(Color.WHITE);
		btnAddUser.setFont(new Font("SansSerif", Font.PLAIN, 15));
		btnAddUser.setBorder(null);
		btnAddUser.setBackground(new Color(51, 102, 255));
		btnAddUser.setBounds(10, 678, 89, 35);
		UserManagement.add(btnAddUser);

		JButton btnEditUser = new JButton("Edit");
		final JPanel WarningUser = new JPanel();
		btnEditUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(tblUser.getSelectedRow()==-1){
					WarningUser.setVisible(true);
				}else{
					try{
						EditUser eu = new EditUser(getSelectedUser());
						eu.setVisible(true);
						eu.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
						eu.setAlwaysOnTop(true);
						frmSuperUser.setFocusable(false);
					}catch(ArrayIndexOutOfBoundsException e){
						WarningUser.setVisible(true);
					}		
				}
			}
		});
		btnEditUser.setForeground(Color.WHITE);
		btnEditUser.setFont(new Font("SansSerif", Font.PLAIN, 15));
		btnEditUser.setBorder(null);
		btnEditUser.setBackground(new Color(51, 102, 255));
		btnEditUser.setBounds(109, 678, 89, 35);
		UserManagement.add(btnEditUser);

		JButton btnDeleteUser = new JButton("Delete");
		final JPanel SuccessDeleteUser = new JPanel();
		btnDeleteUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(tblUser.getSelectedRow()==-1){
					SuccessDeleteUser.setVisible(false);
					WarningUser.setVisible(true);
				}else{
					try{
						if(JOptionPane.showConfirmDialog(frmSuperUser, "Are you sure you want to delete?","PROMPT",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==0){
							TestConnection tc = new TestConnection();
							DatabaseManager dm = new DatabaseManager();


							DefaultTableModel modelUser = (DefaultTableModel)tblUser.getModel();
							id = (modelUser.getValueAt(tblUser.getSelectedRow(), 0).toString());

							try {
								int rs = dm.deleteUser(tc.getConnection(), id);
								if(rs==1){
									SuccessDeleteUser.setVisible(true);
									updateUserTable();

								}
							} catch (ClassNotFoundException | SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							updateUserTable();
						}
					}catch(ArrayIndexOutOfBoundsException aio){
						SuccessDeleteUser.setVisible(false);
						WarningUser.setVisible(true);	
					}
				}
			}
		});

		WarningUser.setVisible(false);
		WarningUser.setOpaque(false);
		WarningUser.setBounds(301, 660, 239, 64);
		UserManagement.add(WarningUser);
		WarningUser.setLayout(null);

		JLabel lblSelectUserTo = new JLabel("Select User to Proceed !");
		lblSelectUserTo.setForeground(Color.RED);
		lblSelectUserTo.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblSelectUserTo.setBounds(10, 11, 177, 41);
		WarningUser.add(lblSelectUserTo);

		JButton button = new JButton("OK");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				WarningUser.setVisible(false);
			}
		});
		button.setForeground(Color.WHITE);
		button.setFont(new Font("SansSerif", Font.PLAIN, 15));
		button.setBorder(new LineBorder(new Color(51, 153, 255), 2));
		button.setBackground(new Color(51, 102, 255));
		button.setBounds(177, 14, 46, 35);
		WarningUser.add(button);
		btnDeleteUser.setForeground(Color.WHITE);
		btnDeleteUser.setFont(new Font("SansSerif", Font.PLAIN, 15));
		btnDeleteUser.setBorder(null);
		btnDeleteUser.setBackground(new Color(51, 102, 255));
		btnDeleteUser.setBounds(208, 678, 89, 35);
		UserManagement.add(btnDeleteUser);

		SuccessDeleteUser.setVisible(false);
		SuccessDeleteUser.setOpaque(false);
		SuccessDeleteUser.setBounds(370, 22, 239, 48);
		UserManagement.add(SuccessDeleteUser);
		SuccessDeleteUser.setLayout(null);

		JLabel label_6 = new JLabel("Successfully Deleted !");
		label_6.setForeground(new Color(51, 255, 0));
		label_6.setFont(new Font("SansSerif", Font.PLAIN, 18));
		label_6.setBounds(0, 0, 211, 48);
		SuccessDeleteUser.add(label_6);

		JButton button_1 = new JButton("OK");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SuccessDeleteUser.setVisible(false);
			}
		});
		button_1.setForeground(Color.WHITE);
		button_1.setFont(new Font("SansSerif", Font.PLAIN, 15));
		button_1.setBorder(null);
		button_1.setBackground(new Color(51, 102, 255));
		button_1.setBounds(183, 8, 46, 35);
		SuccessDeleteUser.add(button_1);

		Delivery.setVisible(false);
		Delivery.setBackground(new Color(255, 255, 255));
		Delivery.setBorder(new LineBorder(new Color(51, 153, 255)));
		Delivery.setBounds(310, 33, 0, 0);
		frmSuperUser.getContentPane().add(Delivery);
		Delivery.setLayout(null);

		JButton btnCloseDelivery = new JButton("X");
		btnCloseDelivery.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(frmSuperUser, "Are you sure you want to close ?","PROMPT",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==0){
					Delivery.setVisible(false);
					btnDelivery.setIcon(new ImageIcon(SuperUserWindow.class.getResource("/app/image/delivery.png")));
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
		label_5.setIcon(new ImageIcon(SuperUserWindow.class.getResource("/app/image/title.png")));
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

		JLabel lblDeliverTo = new JLabel("Deliver to");
		lblDeliverTo.setForeground(SystemColor.textHighlight);
		lblDeliverTo.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblDeliverTo.setBounds(10, 40, 105, 29);
		DeliveryInfo.add(lblDeliverTo);

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
				try{
				get = getSelectedDeliverItem();
				}catch(NullPointerException npe){
					SelectItemtoDeliver.setVisible(true);
				}
			}
		});
		tblDeliveryItems.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Code", "Name", "Brand", "Color", "Style", "Category", "Size", "Quantity", "Price"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPaneDeliveryItems.setViewportView(tblDeliveryItems);

		JButton btnDeliver = new JButton("Deliver");
		btnDeliver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(tblDeliveryItems.getSelectedRow()==-1){
					SelectItemtoDeliver.setVisible(true);
				}else{
					if(txtDeliverTo.getText().equals("") || txtQuantityDeliver.getText().equals("") || txtAddress.getText().equals("")){
						JOptionPane.showMessageDialog(frmSuperUser, "Specify the Fields !");
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
							JOptionPane.showMessageDialog(frmSuperUser, "Unsufficient Quantity ! !");
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
									JOptionPane.showMessageDialog(frmSuperUser, "Delivery is on Process !");

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
				"Item Code", "Item Name", "Receipient", "Quantity", "TotalPrice", "Address", "Date Delivered"
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
		shoeThis.setIcon(new ImageIcon(SuperUserWindow.class.getResource("/app/image/ShoeThis.png")));
		shoeThis.setBounds(445, 237, 800, 300);
		frmSuperUser.getContentPane().add(shoeThis);

		JLabel mainBg = new JLabel("");
		mainBg.setIcon(new ImageIcon(SuperUserWindow.class.getResource("/app/image/bgwhite.png")));
		mainBg.setBounds(0, 0, 1366, 768);
		frmSuperUser.getContentPane().add(mainBg);
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
				String itemStyle = rs.getString("ItemStyle");
				String itemCategory = rs.getString("ItemCategory");
				String itemSize = rs.getString("ItemSize");
				String itemQuantity = rs.getString("ItemQuantity");
				String price = rs.getString("Price");
				modelInventory.addRow(new Object[]{itemCode,itemName,itemBrand,itemColor,itemStyle,itemCategory,itemSize,itemQuantity,price});
				modelItemData.addRow(new Object[]{itemCode,itemName,itemBrand,itemColor,itemStyle,itemCategory,itemSize,itemQuantity,price});
				modelDeliver.addRow(new Object[]{itemCode,itemName,itemBrand,itemColor,itemStyle,itemCategory,itemSize,itemQuantity,price});

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
				String itemStyle = rs.getString("ItemStyle");
				String itemCategory = rs.getString("ItemCategory");
				String itemSize = rs.getString("ItemSize");
				String itemQuantity = rs.getString("ItemQuantity");
				String price = rs.getString("ItemPrice");
				String dateDeleted = rs.getString("DateDeleted");
				modelDelete.addRow(new Object[]{itemCode,itemName,itemBrand,itemColor,itemStyle,itemCategory,itemSize,itemQuantity,price,dateDeleted});

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

				String itemCode = rs.getString("ItemCode");
				String itemName = rs.getString("ItemName");
				String receipient = rs.getString("Receipient");
				String quantity = rs.getString("Quantity");
				String totalPrice = rs.getString("TotalPrice");
				String address = rs.getString("Address");
				String dateDeliver = rs.getString("DateDelivered");
				modelDeliver.addRow(new Object[]{itemCode,itemName,receipient,quantity,totalPrice,address,dateDeliver});



			}

			tblDeliveryReports.updateUI();
			rs.close();
			t.getConnection().close();

		}catch(SQLException | ClassNotFoundException e){

		}
	}
	public void updateUserTable(){
		TestConnection t = new TestConnection();
		DatabaseManager dm = new DatabaseManager();
		DefaultTableModel modelUser = (DefaultTableModel)tblUser.getModel();
		modelUser.getDataVector().removeAllElements();
		tblUser.updateUI();

		try{
			ResultSet rs = dm.accountAdmin(t.getConnection());
			while(rs.next()){
				String id = rs.getString("adminID");
				String userName = rs.getString("userName");
				String password = rs.getString("password");
				String name = rs.getString("Name");
				String contact = rs.getString("ContactNumber");
				String pin = rs.getString("PinCode");


				modelUser.addRow(new Object[]{id,userName,password,name,contact,pin});

			}

			tblUser.updateUI();
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
		i.setItemStyle(modelItems.getValueAt(tblDeleted.getSelectedRow(), 4).toString());
		i.setItemCategory(modelItems.getValueAt(tblDeleted.getSelectedRow(), 5).toString());
		i.setSize(modelItems.getValueAt(tblDeleted.getSelectedRow(), 6).toString());
		i.setQuantityAvailable(Integer.parseInt(modelItems.getValueAt(tblDeleted.getSelectedRow(), 7).toString()));
		i.setPrice(Integer.parseInt(modelItems.getValueAt(tblDeleted.getSelectedRow(), 8).toString()));



		return i;
	}

	public Inventory getSelectedItem(){
		Inventory i = new Inventory();
		DefaultTableModel modelItems = (DefaultTableModel)tblItems.getModel();

		i.setItemCode(modelItems.getValueAt(tblItems.getSelectedRow(), 0).toString());
		i.setItemName(modelItems.getValueAt(tblItems.getSelectedRow(), 1).toString());
		i.setItemBrand(modelItems.getValueAt(tblItems.getSelectedRow(), 2).toString());
		i.setItemColor(modelItems.getValueAt(tblItems.getSelectedRow(), 3).toString());
		i.setItemStyle(modelItems.getValueAt(tblItems.getSelectedRow(), 4).toString());
		i.setItemCategory(modelItems.getValueAt(tblItems.getSelectedRow(), 5).toString());
		i.setSize(modelItems.getValueAt(tblItems.getSelectedRow(), 6).toString());
		i.setQuantityAvailable(Integer.parseInt(modelItems.getValueAt(tblItems.getSelectedRow(), 7).toString()));
		i.setPrice(Integer.parseInt(modelItems.getValueAt(tblItems.getSelectedRow(), 8).toString()));

		return i;
	}
	public Inventory getSelectedDeliverItem(){
		Inventory i = new Inventory();
		DefaultTableModel modelItems = (DefaultTableModel)tblDeliveryItems.getModel();

		i.setItemCode(modelItems.getValueAt(tblDeliveryItems.getSelectedRow(), 0).toString());
		i.setItemName(modelItems.getValueAt(tblDeliveryItems.getSelectedRow(), 1).toString());
		i.setItemBrand(modelItems.getValueAt(tblDeliveryItems.getSelectedRow(), 2).toString());
		i.setItemColor(modelItems.getValueAt(tblDeliveryItems.getSelectedRow(), 3).toString());
		i.setItemStyle(modelItems.getValueAt(tblDeliveryItems.getSelectedRow(), 4).toString());
		i.setItemCategory(modelItems.getValueAt(tblDeliveryItems.getSelectedRow(), 5).toString());
		i.setSize(modelItems.getValueAt(tblDeliveryItems.getSelectedRow(), 6).toString());
		i.setQuantityAvailable(Integer.parseInt(modelItems.getValueAt(tblDeliveryItems.getSelectedRow(), 7).toString()));
		i.setPrice(Integer.parseInt(modelItems.getValueAt(tblDeliveryItems.getSelectedRow(), 8).toString()));

		return i;
	}
	public User getSelectedUser(){

		User u = new User();
		DefaultTableModel modelUser = (DefaultTableModel)tblUser.getModel();

		u.setID(modelUser.getValueAt(tblUser.getSelectedRow(),0).toString());
		u.setUserName(modelUser.getValueAt(tblUser.getSelectedRow(),1).toString());
		u.setPassword(modelUser.getValueAt(tblUser.getSelectedRow(),2).toString());
		u.setName(modelUser.getValueAt(tblUser.getSelectedRow(),3).toString());
		u.setContactNo(modelUser.getValueAt(tblUser.getSelectedRow(),4).toString());
		u.setPinCode(modelUser.getValueAt(tblUser.getSelectedRow(), 5).toString());


		return u;
	}
}
