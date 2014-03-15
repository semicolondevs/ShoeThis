package app.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import app.add.AddBrand;
import app.add.AddColor;
import app.add.AddItem;
import app.changePassword.ChangePassword;
import app.db.DatabaseManager;
import app.db.TestConnection;
import app.edit.EditItem;
import app.model.DeleteLogs;
import app.model.Delivery;
import app.model.Inventory;
import app.model.Sales;
import app.util.DigitalClock;


public class UserWindow {

	public JFrame frmUserWindow;
	public JPanel SuccessEdit;
	private JTable tblItems;
	private JTextField txtSearchItem;
	private JComboBox cmbBrand;
	private JComboBox cmbColor;
	private JComboBox cmbSize;
	private String search;
	private String code;
	private String id;
	private DateFormat currentDate = new SimpleDateFormat("MMMM dd,yyyy");
	private DateFormat deliveryDate = new SimpleDateFormat("MMMM dd,yyyy");
	private Inventory get = new Inventory();
	private Sales getSales = new Sales();
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
	private JTable tblSalesItem;
	private JTextField txtCustomerName;
	private JTextField txtContactNo;
	private JTable tblSalesReport;
	private JTextField txtQuantitySale;
	private JTextField txtSearchSaleItem;
	private JTextField txtPrompt;

	public UserWindow() {
		initialize();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmUserWindow = new JFrame();
		frmUserWindow.addWindowFocusListener(new WindowFocusListener() {
			@Override
			public void windowGainedFocus(WindowEvent arg0) {
				updateItemTable();
				updateDeleteLogsTable();
				updateDeliverTable();
				updateSalesReportTable();
			}
			@Override
			public void windowLostFocus(WindowEvent arg0) {
			}
		});
		frmUserWindow.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				updateItemTable();
				updateDeleteLogsTable();
				updateDeliverTable();
				updateSalesReportTable();
			}
		});
		frmUserWindow.setUndecorated(true);
		frmUserWindow.setBounds(0, 0, 1366, 768);
		frmUserWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmUserWindow.getContentPane().setLayout(null);
		
		final JPanel pnlPrompt = new JPanel();
		pnlPrompt.setVisible(false);
		
		final JPanel pnlExit = new JPanel();
		pnlExit.setVisible(false);
		pnlExit.setBackground(new Color(255, 255, 255));
		pnlExit.setBorder(new LineBorder(new Color(51, 153, 255), 3));
		pnlExit.setBounds(1087, 22, 279, 40);
		frmUserWindow.getContentPane().add(pnlExit);
		pnlExit.setLayout(null);
		
		JLabel lblExit = new JLabel("Exit ?");
		lblExit.setForeground(SystemColor.textHighlight);
		lblExit.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblExit.setBounds(23, 10, 89, 23);
		pnlExit.add(lblExit);
		
		JButton button_4 = new JButton("Yes");
		button_4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		button_4.setForeground(Color.WHITE);
		button_4.setFont(new Font("SansSerif", Font.PLAIN, 12));
		button_4.setBorder(null);
		button_4.setBackground(new Color(51, 102, 255));
		button_4.setBounds(90, 11, 81, 23);
		pnlExit.add(button_4);
		
		JButton button_5 = new JButton("No");
		button_5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pnlExit.setVisible(false);
			}
		});
		button_5.setForeground(Color.WHITE);
		button_5.setFont(new Font("SansSerif", Font.PLAIN, 12));
		button_5.setBorder(null);
		button_5.setBackground(new Color(51, 102, 255));
		button_5.setBounds(181, 11, 81, 23);
		pnlExit.add(button_5);
		
				JButton btnExitSystem = new JButton("X");
				btnExitSystem.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						pnlExit.setVisible(true);
					}
				});
				btnExitSystem.setBounds(1329, 0, 37, 22);
				frmUserWindow.getContentPane().add(btnExitSystem);
				btnExitSystem.setForeground(Color.WHITE);
				btnExitSystem.setFont(new Font("SansSerif", Font.BOLD, 11));
				btnExitSystem.setBorder(null);
				btnExitSystem.setBackground(new Color(0, 153, 255));
		
				JButton btnMinimizeSystem = new JButton("_");
				btnMinimizeSystem.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						frmUserWindow.setState(Frame.ICONIFIED);
					}
				});
				btnMinimizeSystem.setForeground(Color.WHITE);
				btnMinimizeSystem.setFont(new Font("SansSerif", Font.BOLD, 11));
				btnMinimizeSystem.setBorder(null);
				btnMinimizeSystem.setBackground(new Color(0, 51, 255));
				btnMinimizeSystem.setBounds(1291, 0, 37, 22);
				frmUserWindow.getContentPane().add(btnMinimizeSystem);
		
				JLabel lblShoeThisInventory = new JLabel("Shoe This Inventory Management System");
				lblShoeThisInventory.setBounds(10, 0, 463, 23);
				frmUserWindow.getContentPane().add(lblShoeThisInventory);
				lblShoeThisInventory.setForeground(Color.WHITE);
				lblShoeThisInventory.setFont(new Font("SansSerif", Font.PLAIN, 15));
		
				JLabel tittlebar = new JLabel("");
				tittlebar.setIcon(new ImageIcon(UserWindow.class.getResource("/app/image/title.png")));
				tittlebar.setBounds(0, 0, 1366, 22);
				frmUserWindow.getContentPane().add(tittlebar);
		
		pnlPrompt.setBorder(new LineBorder(new Color(51, 153, 255), 3));
		pnlPrompt.setBackground(new Color(255, 255, 255));
		pnlPrompt.setBounds(679, 304, 300, 138);
		frmUserWindow.getContentPane().add(pnlPrompt);
		pnlPrompt.setLayout(null);
		
		final JButton btnInventoryManagement = new JButton("");
		btnInventoryManagement.setRolloverIcon(new ImageIcon(UserWindow.class.getResource("/app/image/IMActive.png")));
		final JButton btnShoesMasterData = new JButton("");
		btnShoesMasterData.setRolloverIcon(new ImageIcon(UserWindow.class.getResource("/app/image/IMDActive.png")));
		final JButton btnDelivery = new JButton("");
		btnDelivery.setRolloverIcon(new ImageIcon(UserWindow.class.getResource("/app/image/deliveryActive.png")));
		final JButton btnSales = new JButton("");
		final JPanel pnlSales = new JPanel();
		btnSales.setRolloverIcon(new ImageIcon(UserWindow.class.getResource("/app/image/SalesActive.png")));
		final JPanel pnlInventoryManagement = new JPanel();
		final JPanel pnlItemMasterData = new JPanel();
		final JPanel pnlDelivery = new JPanel();
		JButton button = new JButton("Yes");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pnlPrompt.setVisible(false);
				pnlItemMasterData.setVisible(false);
				pnlDelivery.setVisible(false);
				pnlSales.setVisible(false);
				pnlInventoryManagement.setVisible(false);
				btnShoesMasterData.setIcon(new ImageIcon(UserWindow.class.getResource("/app/image/IMD.png")));
				btnDelivery.setIcon(new ImageIcon(UserWindow.class.getResource("/app/image/delivery.png")));
				btnSales.setIcon(new ImageIcon(UserWindow.class.getResource("/app/image/Sales.png")));
				btnInventoryManagement.setIcon(new ImageIcon(UserWindow.class.getResource("/app/image/IM.png")));
				
			}
		});
		button.setForeground(Color.WHITE);
		button.setFont(new Font("SansSerif", Font.PLAIN, 15));
		button.setBorder(null);
		button.setBackground(new Color(51, 102, 255));
		button.setBounds(80, 92, 66, 35);
		pnlPrompt.add(button);
		
		JButton button_1 = new JButton("No");
		button_1.setForeground(Color.WHITE);
		button_1.setFont(new Font("SansSerif", Font.PLAIN, 15));
		button_1.setBorder(null);
		button_1.setBackground(new Color(51, 102, 255));
		button_1.setBounds(156, 92, 66, 35);
		pnlPrompt.add(button_1);
		
		txtPrompt = new JTextField();
		txtPrompt.setEditable(false);
		txtPrompt.setText("<prompt>");
		txtPrompt.setOpaque(false);
		txtPrompt.setHorizontalAlignment(SwingConstants.CENTER);
		txtPrompt.setForeground(new Color(255, 0, 0));
		txtPrompt.setFont(new Font("SansSerif", Font.PLAIN, 18));
		txtPrompt.setColumns(10);
		txtPrompt.setBorder(null);
		txtPrompt.setBounds(10, 27, 280, 46);
		pnlPrompt.add(txtPrompt);

		JPanel pnlSideBar = new JPanel();
		pnlSideBar.setBackground(Color.WHITE);
		pnlSideBar.setBounds(0, 0, 300, 768);
		frmUserWindow.getContentPane().add(pnlSideBar);
		pnlSideBar.setLayout(null);

		pnlSales.setBackground(Color.WHITE);
		pnlSales.setVisible(false);
		pnlSales.setBorder(new LineBorder(new Color(51, 153, 255)));
		pnlSales.setBounds(310, 33, 0, 0);
		frmUserWindow.getContentPane().add(pnlSales);
		pnlSales.setLayout(null);
		JButton btnCloseSales = new JButton("X");
		btnCloseSales.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pnlPrompt.setVisible(true);
				txtPrompt.setText("Are your sure you want to close ?");
			}
		});
		btnCloseSales.setBounds(1009, 0, 37, 23);
		btnCloseSales.setForeground(Color.WHITE);
		btnCloseSales.setFont(new Font("SansSerif", Font.BOLD, 11));
		btnCloseSales.setBorder(null);
		btnCloseSales.setBackground(new Color(0, 51, 255));
		pnlSales.add(btnCloseSales);

		JLabel lblSales = new JLabel("Sales");
		lblSales.setBounds(10, 0, 162, 23);
		lblSales.setForeground(Color.WHITE);
		lblSales.setFont(new Font("SansSerif", Font.PLAIN, 15));
		pnlSales.add(lblSales);

		JLabel label_9 = new JLabel("");
		label_9.setBounds(0, 0, 1046, 23);
		label_9.setIcon(new ImageIcon(UserWindow.class.getResource("/app/image/title.png")));
		pnlSales.add(label_9);
		final JPanel pnlSalesReportTab = new JPanel();
		pnlSalesReportTab.setVisible(false);

		final JPanel pnlSaleTab = new JPanel();

		pnlSaleTab.setBounds(10, 70, 1026, 643);
		pnlSales.add(pnlSaleTab);
		pnlSaleTab.setLayout(null);

		JPanel CustomerInfo = new JPanel();
		CustomerInfo.setBounds(0, 0, 1026, 156);
		pnlSaleTab.add(CustomerInfo);
		CustomerInfo.setBackground(Color.WHITE);
		CustomerInfo.setLayout(null);

		final JPanel SelectItem = new JPanel();
		SelectItem.setBackground(Color.WHITE);
		SelectItem.setVisible(false);
		SelectItem.setBounds(737, 11, 279, 46);
		CustomerInfo.add(SelectItem);
		SelectItem.setLayout(null);

		JLabel lblSelectItemTo_1 = new JLabel("Please Select Item");
		lblSelectItemTo_1.setForeground(Color.RED);
		lblSelectItemTo_1.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblSelectItemTo_1.setBounds(61, 0, 153, 43);
		SelectItem.add(lblSelectItemTo_1);

		JButton button_2 = new JButton("OK");
		button_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SelectItem.setVisible(false);
			}
		});
		button_2.setForeground(Color.WHITE);
		button_2.setFont(new Font("SansSerif", Font.PLAIN, 15));
		button_2.setBorder(null);
		button_2.setBackground(new Color(51, 102, 255));
		button_2.setBounds(222, 5, 45, 35);
		SelectItem.add(button_2);

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
		txtCustomerName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent getChar) {
				char input = getChar.getKeyChar();
				if(!(Character.isLetter(input)) || (input==KeyEvent.VK_BACK_SPACE) || (input==KeyEvent.VK_DELETE)){
					getChar.consume();
				}
			}
		});
		txtCustomerName.setHorizontalAlignment(SwingConstants.CENTER);
		txtCustomerName.setForeground(Color.BLACK);
		txtCustomerName.setFont(new Font("SansSerif", Font.PLAIN, 15));
		txtCustomerName.setColumns(10);
		txtCustomerName.setBorder(new LineBorder(new Color(51, 153, 255)));
		txtCustomerName.setBounds(20, 80, 209, 29);
		CustomerInfo.add(txtCustomerName);

		JLabel lblContactNo = new JLabel("Contact No.");
		lblContactNo.setForeground(SystemColor.textHighlight);
		lblContactNo.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblContactNo.setBounds(239, 51, 193, 29);
		CustomerInfo.add(lblContactNo);

		txtContactNo = new JTextField();
		txtContactNo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent getChar) {
				char input = getChar.getKeyChar();
				if(!(Character.isDigit(input)) || (input==KeyEvent.VK_BACK_SPACE) || (input==KeyEvent.VK_DELETE)){
					getChar.consume();
				}
			}
		});
		txtContactNo.setHorizontalAlignment(SwingConstants.CENTER);
		txtContactNo.setForeground(Color.BLACK);
		txtContactNo.setFont(new Font("SansSerif", Font.PLAIN, 15));
		txtContactNo.setColumns(10);
		txtContactNo.setBorder(new LineBorder(new Color(51, 153, 255)));
		txtContactNo.setBounds(241, 80, 141, 29);
		CustomerInfo.add(txtContactNo);

		JLabel lblMeetUpTime = new JLabel("Meet Up Time");
		lblMeetUpTime.setForeground(SystemColor.textHighlight);
		lblMeetUpTime.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblMeetUpTime.setBounds(544, 51, 193, 29);
		CustomerInfo.add(lblMeetUpTime);

		JLabel lblMeetUpDate = new JLabel("Meet Up Date");
		lblMeetUpDate.setForeground(SystemColor.textHighlight);
		lblMeetUpDate.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblMeetUpDate.setBounds(704, 51, 193, 29);
		CustomerInfo.add(lblMeetUpDate);

		final JComboBox<String> cmbMonth = new JComboBox();
		cmbMonth.setFont(new Font("SansSerif", Font.PLAIN, 14));
		cmbMonth.setBackground(new Color(255, 255, 255));
		cmbMonth.setBorder(new LineBorder(new Color(51, 153, 255)));
		cmbMonth.setModel(new DefaultComboBoxModel(new String[] {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"}));
		cmbMonth.setBounds(704, 80, 65, 29);
		CustomerInfo.add(cmbMonth);
		
		final JComboBox<String> cmbDay = new JComboBox();
		cmbDay.setFont(new Font("SansSerif", Font.PLAIN, 15));
		cmbDay.setBackground(new Color(255, 255, 255));
		cmbDay.setBorder(new LineBorder(new Color(51, 153, 255)));
		cmbDay.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		cmbDay.setBounds(779, 80, 44, 29);
		CustomerInfo.add(cmbDay);

		final JComboBox<String> cmbYear = new JComboBox();
		cmbYear.setFont(new Font("SansSerif", Font.PLAIN, 15));
		cmbYear.setBorder(new LineBorder(new Color(51, 153, 255)));
		cmbYear.setBackground(new Color(255, 255, 255));
		cmbYear.setModel(new DefaultComboBoxModel(new String[] {"2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033"}));
		cmbYear.setBounds(832, 80, 65, 29);
		CustomerInfo.add(cmbYear);

		final JComboBox cmbHour = new JComboBox();
		cmbHour.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}));
		cmbHour.setFont(new Font("SansSerif", Font.PLAIN, 15));
		cmbHour.setBorder(new LineBorder(new Color(51, 153, 255)));
		cmbHour.setBackground(Color.WHITE);
		cmbHour.setBounds(544, 80, 44, 29);
		CustomerInfo.add(cmbHour);

		final JComboBox cmbMinutes = new JComboBox();
		cmbMinutes.setModel(new DefaultComboBoxModel(new String[] {"00", "30", "59"}));
		cmbMinutes.setFont(new Font("SansSerif", Font.PLAIN, 15));
		cmbMinutes.setBorder(new LineBorder(new Color(51, 153, 255)));
		cmbMinutes.setBackground(Color.WHITE);
		cmbMinutes.setBounds(592, 80, 44, 29);
		CustomerInfo.add(cmbMinutes);

		final JComboBox cmbAMPM = new JComboBox();
		cmbAMPM.setModel(new DefaultComboBoxModel(new String[] {"AM", "PM"}));
		cmbAMPM.setFont(new Font("SansSerif", Font.PLAIN, 15));
		cmbAMPM.setBorder(new LineBorder(new Color(51, 153, 255)));
		cmbAMPM.setBackground(Color.WHITE);
		cmbAMPM.setBounds(644, 80, 50, 29);
		CustomerInfo.add(cmbAMPM);

		final JPanel SpecifyCustomerField = new JPanel();
		JButton btnSet = new JButton("Set");
		btnSet.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(tblSalesItem.getSelectedRow()==-1){
					SelectItem.setVisible(true);
				}else{
					if(txtCustomerName.getText().equals("") || txtContactNo.getText().equals("")){
						SpecifyCustomerField.setVisible(true);
					}else{
						TestConnection tc = new TestConnection();
						DatabaseManager dm = new DatabaseManager();
						Sales s = new Sales();
						Inventory i = new Inventory();

						String itemCode = get.getItemCode();
						String itemName = get.getItemName();
						int price = get.getPrice();
						int quantity = get.getQuantityAvailable();
						int saleQuantity = Integer.parseInt(txtQuantitySale.getText());
						int total = price * saleQuantity;
						
						if(saleQuantity>quantity){
							JOptionPane.showMessageDialog(frmUserWindow,"Unsufficient Quantity !");
						}else{
							String customerName = txtCustomerName.getText();
							String contactNumber = txtContactNo.getText();
							String time = cmbHour.getSelectedItem()+":"+cmbMinutes.getSelectedItem()+" "+cmbAMPM.getSelectedItem();
							String date = cmbMonth.getSelectedItem()+" "+cmbDay.getSelectedItem()+", "+cmbYear.getSelectedItem();
							String status = "";

							s.setCustomerName(customerName);
							s.setContactNumber(contactNumber);
							s.setItemCode(itemCode);
							s.setItemName(itemName);
							s.setPrice(price);
							s.setQuantity(saleQuantity);
							s.setTotal(total);
							s.setDateTime(date+" "+time);
							s.setStatus(status);
							

							try {
								int rs = dm.insertSalesReport(tc.getConnection(),s);
								
								
								if(rs==1){
									JOptionPane.showMessageDialog(frmUserWindow,"Your meet up with "+txtCustomerName.getText()+
											"\non "+time+","+date);
									clearText();
									updateSalesReportTable();
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
		btnSet.setForeground(Color.WHITE);
		btnSet.setFont(new Font("SansSerif", Font.PLAIN, 15));
		btnSet.setBorder(null);
		btnSet.setBackground(new Color(51, 102, 255));
		btnSet.setBounds(927, 110, 89, 35);
		CustomerInfo.add(btnSet);

		SpecifyCustomerField.setVisible(false);
		SpecifyCustomerField.setOpaque(false);
		SpecifyCustomerField.setBounds(261, 0, 321, 46);
		CustomerInfo.add(SpecifyCustomerField);
		SpecifyCustomerField.setLayout(null);

		JLabel lblSpecifyFields = new JLabel("Specify Field(s)");
		lblSpecifyFields.setForeground(Color.RED);
		lblSpecifyFields.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblSpecifyFields.setBounds(0, 0, 133, 43);
		SpecifyCustomerField.add(lblSpecifyFields);

		final JButton btnOkSpecifyCustomer = new JButton("OK");
		btnOkSpecifyCustomer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SpecifyCustomerField.setVisible(false);
			}
		});
		btnOkSpecifyCustomer.setForeground(Color.WHITE);
		btnOkSpecifyCustomer.setFont(new Font("SansSerif", Font.PLAIN, 15));
		btnOkSpecifyCustomer.setBorder(null);
		btnOkSpecifyCustomer.setBackground(new Color(51, 102, 255));
		btnOkSpecifyCustomer.setBounds(128, 5, 45, 35);
		SpecifyCustomerField.add(btnOkSpecifyCustomer);

		JLabel lblQuantity_1 = new JLabel("Quantity");
		lblQuantity_1.setForeground(SystemColor.textHighlight);
		lblQuantity_1.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblQuantity_1.setBounds(395, 51, 193, 29);
		CustomerInfo.add(lblQuantity_1);

		txtQuantitySale = new JTextField();
		txtQuantitySale.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent getChar) {
				char input = getChar.getKeyChar();
				if(!(Character.isDigit(input)) || (input==KeyEvent.VK_BACK_SPACE) || (input==KeyEvent.VK_DELETE)){
					getChar.consume();
				}
			}
		});
		txtQuantitySale.setHorizontalAlignment(SwingConstants.CENTER);
		txtQuantitySale.setForeground(Color.BLACK);
		txtQuantitySale.setFont(new Font("SansSerif", Font.PLAIN, 15));
		txtQuantitySale.setColumns(10);
		txtQuantitySale.setBorder(new LineBorder(new Color(51, 153, 255)));
		txtQuantitySale.setBounds(393, 80, 141, 29);
		CustomerInfo.add(txtQuantitySale);

		JLabel label_8 = new JLabel("Search");
		label_8.setForeground(SystemColor.textHighlight);
		label_8.setFont(new Font("SansSerif", Font.PLAIN, 24));
		label_8.setBounds(20, 116, 79, 29);
		CustomerInfo.add(label_8);

		txtSearchSaleItem = new JTextField();
		txtSearchSaleItem.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				TestConnection tc = new TestConnection();
				DatabaseManager dm = new DatabaseManager();
				DefaultTableModel model = (DefaultTableModel)tblSalesItem.getModel();
				model.getDataVector().removeAllElements();
				tblSalesItem.updateUI();

				search = txtSearchSaleItem.getText().toString();

				try {
					ResultSet rs = dm.searchItem(tc.getConnection(), search);
					while(rs.next()){
						String itemCode = rs.getString("ItemCode");
						String itemName = rs.getString("ItemName");
						String itemBrand = rs.getString("ItemBrand");
						String itemColor = rs.getString("ItemColor");
						String itemStyle = rs.getString("ItemStyle");
						String itemCategory = rs.getString("ItemCategory");
						String itemSize = rs.getString("ItemSize");
						String itemQuantity = rs.getString("ItemQuantity");
						String srp = rs.getString("SuggestedRetailPrice");
						String price = rs.getString("Price");
						model.addRow(new Object[]{itemCode,itemName,itemBrand,itemColor,itemStyle,itemCategory,itemSize,itemQuantity,srp,price});
					}
					tblSalesItem.updateUI();
					rs.close();
					tc.getConnection().close();
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		txtSearchSaleItem.setHorizontalAlignment(SwingConstants.CENTER);
		txtSearchSaleItem.setForeground(SystemColor.textHighlight);
		txtSearchSaleItem.setFont(new Font("SansSerif", Font.PLAIN, 15));
		txtSearchSaleItem.setColumns(10);
		txtSearchSaleItem.setBorder(new LineBorder(new Color(51, 153, 255)));
		txtSearchSaleItem.setBounds(98, 116, 272, 29);
		CustomerInfo.add(txtSearchSaleItem);

		JScrollPane scrollPaneSaleItem = new JScrollPane();
		scrollPaneSaleItem.setBounds(0, 157, 1026, 486);
		pnlSaleTab.add(scrollPaneSaleItem);

		tblSalesItem = new JTable();
		tblSalesItem.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblSalesItem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				get = getSelectedSalesItem();
			}
		});
		tblSalesItem.setRowHeight(35);
		tblSalesItem.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Code", "Name", "Brand", "Color", "Style", "Category", "Size", "Quantity", "Price"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false, true, false
			};
			@Override
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPaneSaleItem.setViewportView(tblSalesItem);
		pnlSalesReportTab.setBounds(10, 70, 0, 0);
		pnlSales.add(pnlSalesReportTab);
		pnlSalesReportTab.setLayout(null);

		JScrollPane scrollPaneSalesReport = new JScrollPane();
		scrollPaneSalesReport.setBounds(0, 0, 1026, 592);
		pnlSalesReportTab.add(scrollPaneSalesReport);

		final JButton btnOk_1 = new JButton("OK");
		btnOk_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				TestConnection tc = new TestConnection();
				DatabaseManager dm = new DatabaseManager();
				Sales s = new Sales();
				if(tblSalesReport.getSelectedRow()==-1){
					JOptionPane.showMessageDialog(frmUserWindow,"No Selected Row !");
				}else{
					int customerNo = getSales.getSalesNo();
					String status = "Ok";

					s.setSalesNo(customerNo);
					s.setStatus(status);

					try {
						int rs = dm.updateStatus(tc.getConnection(),s);
						if(rs==1){
							JOptionPane.showMessageDialog(frmUserWindow,"Meet up is Done !");
							updateSalesReportTable();
						}
					} catch (ClassNotFoundException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		btnOk_1.setVisible(false);
		JButton btnSaleItem = new JButton("Set Meet Up");
		btnSaleItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pnlSaleTab.setBounds(10, 70, 1026, 643);
				pnlSaleTab.setVisible(true);
				pnlSalesReportTab.setVisible(false);
				pnlSalesReportTab.setBounds(10, 70, 0, 0);
				btnOk_1.setVisible(false);
			}
		});
		btnSaleItem.setBounds(10, 34, 89, 35);
		btnSaleItem.setForeground(Color.WHITE);
		btnSaleItem.setFont(new Font("SansSerif", Font.PLAIN, 15));
		btnSaleItem.setBorder(null);
		btnSaleItem.setBackground(new Color(51, 102, 255));
		pnlSales.add(btnSaleItem);

		JButton btnSalesReport = new JButton("Sales Report");
		btnSalesReport.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pnlSaleTab.setVisible(false);
				pnlSaleTab.setBounds(10, 70, 0, 0);
				pnlSalesReportTab.setVisible(true);
				pnlSalesReportTab.setBounds(10, 70, 1026, 643);
				btnOk_1.setVisible(true);
			}
		});
		btnSalesReport.setBounds(100, 34, 123, 35);
		btnSalesReport.setForeground(Color.WHITE);
		btnSalesReport.setFont(new Font("SansSerif", Font.PLAIN, 15));
		btnSalesReport.setBorder(null);
		btnSalesReport.setBackground(new Color(51, 102, 255));
		pnlSales.add(btnSalesReport);

		tblSalesReport = new JTable();
		tblSalesReport.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblSalesReport.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				getSales = getSelectedSales();
			}
		});
		tblSalesReport.setRowHeight(35);
		tblSalesReport.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Customer No", "Customer Name", "Contact No", "Item Code", "Item Name", "Price", "Quantity", "Total", "Date/Time", "Status"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false, false, false, false
			};
			@Override
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPaneSalesReport.setViewportView(tblSalesReport);

		JButton btnPrintSalesReport = new JButton("Print Sales Report");
		btnPrintSalesReport.setVisible(false);
		btnPrintSalesReport.setBorder(null);
		btnPrintSalesReport.setBackground(new Color(0, 51, 255));
		btnPrintSalesReport.setForeground(Color.WHITE);
		btnPrintSalesReport.setFont(new Font("SansSerif", Font.PLAIN, 15));
		btnPrintSalesReport.setBounds(10, 597, 172, 35);
		pnlSalesReportTab.add(btnPrintSalesReport);

		btnOk_1.setForeground(Color.WHITE);
		btnOk_1.setFont(new Font("SansSerif", Font.PLAIN, 15));
		btnOk_1.setBorder(null);
		btnOk_1.setBackground(new Color(51, 102, 255));
		btnOk_1.setBounds(987, 34, 49, 35);
		pnlSales.add(btnOk_1);
		
		pnlItemMasterData.setVisible(false);
		btnInventoryManagement.setBackground(Color.WHITE);
		btnInventoryManagement.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				pnlInventoryManagement.setVisible(true);
				pnlInventoryManagement.setBounds(310, 33, 1046, 724);
				pnlItemMasterData.setVisible(false);
				pnlDelivery.setVisible(false);
				pnlSales.setVisible(false);
				btnInventoryManagement.setIcon(new ImageIcon(UserWindow.class.getResource("/app/image/IMActive.png")));
				btnShoesMasterData.setIcon(new ImageIcon(UserWindow.class.getResource("/app/image/IMD.png")));
				btnDelivery.setIcon(new ImageIcon(UserWindow.class.getResource("/app/image/delivery.png")));
				btnSales.setIcon(new ImageIcon(UserWindow.class.getResource("/app/image/Sales.png")));
			}
		});
		btnInventoryManagement.setIcon(new ImageIcon(UserWindow.class.getResource("/app/image/IM.png")));
		btnInventoryManagement.setBorder(null);
		btnInventoryManagement.setBounds(0, 129, 300, 100);
		pnlSideBar.add(btnInventoryManagement);

		btnShoesMasterData.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0){
				pnlInventoryManagement.setVisible(false);
				pnlItemMasterData.setVisible(true);
				pnlDelivery.setVisible(false);
				pnlSales.setVisible(false);
				pnlItemMasterData.setBounds(310, 33, 1046, 724);
				btnShoesMasterData.setIcon(new ImageIcon(UserWindow.class.getResource("/app/image/IMDActive.png")));
				btnInventoryManagement.setIcon(new ImageIcon(UserWindow.class.getResource("/app/image/IM.png")));
				btnDelivery.setIcon(new ImageIcon(UserWindow.class.getResource("/app/image/delivery.png")));
				btnSales.setIcon(new ImageIcon(UserWindow.class.getResource("/app/image/Sales.png")));
			}
		});
		btnShoesMasterData.setIcon(new ImageIcon(UserWindow.class.getResource("/app/image/IMD.png")));
		btnShoesMasterData.setBorder(null);
		btnShoesMasterData.setBounds(0, 252, 300, 100);
		pnlSideBar.add(btnShoesMasterData);

		btnDelivery.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pnlInventoryManagement.setVisible(false);
				pnlItemMasterData.setVisible(false);
				pnlDelivery.setVisible(true);
				pnlSales.setVisible(false);
				pnlDelivery.setBounds(310, 33, 1046, 724);
				btnDelivery.setIcon(new ImageIcon(UserWindow.class.getResource("/app/image/deliveryActive.png")));
				btnInventoryManagement.setIcon(new ImageIcon(UserWindow.class.getResource("/app/image/IM.png")));
				btnShoesMasterData.setIcon(new ImageIcon(UserWindow.class.getResource("/app/image/IMD.png")));
				btnSales.setIcon(new ImageIcon(UserWindow.class.getResource("/app/image/Sales.png")));
				
			}
		});
		btnDelivery.setIcon(new ImageIcon(UserWindow.class.getResource("/app/image/delivery.png")));
		btnDelivery.setBorder(null);
		btnDelivery.setBounds(0, 373, 300, 100);
		pnlSideBar.add(btnDelivery);

		btnSales.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				pnlSales.setVisible(true);
				pnlSaleTab.setVisible(true);
				pnlSales.setBounds(310,33,1046,724);
				pnlInventoryManagement.setVisible(false);
				pnlItemMasterData.setVisible(false);
				pnlDelivery.setVisible(false);
				btnSales.setIcon(new ImageIcon(UserWindow.class.getResource("/app/image/SalesActive.png")));
				btnInventoryManagement.setIcon(new ImageIcon(UserWindow.class.getResource("/app/image/IM.png")));
				btnShoesMasterData.setIcon(new ImageIcon(UserWindow.class.getResource("/app/image/IMD.png")));
				btnDelivery.setIcon(new ImageIcon(UserWindow.class.getResource("/app/image/delivery.png")));
			}
		});
		btnSales.setIcon(new ImageIcon(UserWindow.class.getResource("/app/image/Sales.png")));
		btnSales.setBorder(null);
		btnSales.setBounds(0, 492, 300, 100);
		pnlSideBar.add(btnSales);


		JLabel lblClock = new JLabel("");
		new DigitalClock(lblClock);
		lblClock.setForeground(Color.WHITE);
		lblClock.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblClock.setBounds(147, 29, 143, 23);
		pnlSideBar.add(lblClock);
		
		final JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(51, 153, 255), 2));
		panel.setBackground(new Color(255, 255, 255));
		panel.setVisible(false);
		panel.setBounds(10, 52, 280, 55);
		pnlSideBar.add(panel);
		panel.setLayout(null);

		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(true);
			}
		});
		JLabel lblConfirm = new JLabel("Confirm !");
		lblConfirm.setForeground(new Color(51, 153, 255));
		lblConfirm.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblConfirm.setBounds(10, 11, 89, 23);
		panel.add(lblConfirm);
		
		JButton btnYes = new JButton("Yes");
		btnYes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				LoginWindow l = new LoginWindow();
				l.frmLogin.setVisible(true);
				frmUserWindow.dispose();
			}
		});
		btnYes.setForeground(Color.WHITE);
		btnYes.setFont(new Font("SansSerif", Font.PLAIN, 12));
		btnYes.setBorder(null);
		btnYes.setBackground(new Color(51, 102, 255));
		btnYes.setBounds(90, 11, 81, 23);
		panel.add(btnYes);
		
		JButton btnNo = new JButton("No");
		btnNo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(false);
			}
		});
		btnNo.setForeground(Color.WHITE);
		btnNo.setFont(new Font("SansSerif", Font.PLAIN, 12));
		btnNo.setBorder(null);
		btnNo.setBackground(new Color(51, 102, 255));
		btnNo.setBounds(181, 11, 81, 23);
		panel.add(btnNo);
		btnLogout.setBorder(null);
		btnLogout.setBackground(new Color(51, 102, 255));
		btnLogout.setForeground(Color.WHITE);
		btnLogout.setFont(new Font("SansSerif", Font.PLAIN, 12));
		btnLogout.setBounds(10, 29, 89, 23);
		pnlSideBar.add(btnLogout);

		JLabel lblModule = new JLabel("Modules");
		lblModule.setHorizontalAlignment(SwingConstants.CENTER);
		lblModule.setForeground(new Color(255, 255, 255));
		lblModule.setFont(new Font("SansSerif", Font.PLAIN, 22));
		lblModule.setBounds(0, 63, 300, 55);
		pnlSideBar.add(lblModule);

		JButton btnchangePassword = new JButton("*Change Password");
		btnchangePassword.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ChangePassword scp = new ChangePassword();
				scp.setVisible(true);
				scp.setLocationRelativeTo(null);
				scp.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
				scp.setAlwaysOnTop(true);
				frmUserWindow.setFocusable(false);

			}
		});
		btnchangePassword.setOpaque(false);
		btnchangePassword.setHorizontalAlignment(SwingConstants.LEADING);
		btnchangePassword.setForeground(Color.WHITE);
		btnchangePassword.setFont(new Font("SansSerif", Font.ITALIC, 12));
		btnchangePassword.setBorder(null);
		btnchangePassword.setBackground(new Color(51, 102, 255));
		btnchangePassword.setBounds(0, 745, 120, 23);
		pnlSideBar.add(btnchangePassword);

		JLabel sideBarBG = new JLabel("");
		sideBarBG.setIcon(new ImageIcon(UserWindow.class.getResource("/app/image/sidebar.png")));
		sideBarBG.setBounds(0, 0, 300, 768);
		pnlSideBar.add(sideBarBG);

		pnlInventoryManagement.setVisible(false);
		pnlInventoryManagement.setBackground(new Color(255, 255, 255));
		pnlInventoryManagement.setBorder(new LineBorder(new Color(51, 153, 255)));
		pnlInventoryManagement.setBounds(310, 33, 0, 0);
		frmUserWindow.getContentPane().add(pnlInventoryManagement);
		pnlInventoryManagement.setLayout(null);

		JButton btnCloseIM = new JButton("X");
		btnCloseIM.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pnlPrompt.setVisible(true);
				txtPrompt.setText("Are your sure you want to close ?");

			}
		});
		btnCloseIM.setForeground(Color.WHITE);
		btnCloseIM.setFont(new Font("SansSerif", Font.BOLD, 11));
		btnCloseIM.setBorder(null);
		btnCloseIM.setBackground(new Color(0, 51, 255));
		btnCloseIM.setBounds(1009, 0, 37, 23);
		pnlInventoryManagement.add(btnCloseIM);

		JLabel lblInventoryManagement = new JLabel("Inventory Management");
		lblInventoryManagement.setForeground(Color.WHITE);
		lblInventoryManagement.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblInventoryManagement.setBounds(10, 0, 162, 23);
		pnlInventoryManagement.add(lblInventoryManagement);

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(UserWindow.class.getResource("/app/image/title.png")));
		label.setBounds(0, 0, 1046, 23);
		pnlInventoryManagement.add(label);

		final JPanel SuccessDelete = new JPanel();
		SuccessDelete.setVisible(false);
		SuccessDelete.setOpaque(false);
		SuccessDelete.setBounds(370, 22, 320, 48);
		pnlInventoryManagement.add(SuccessDelete);
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
		scrollPaneItems.setOpaque(false);
		scrollPaneItems.setBackground(new Color(255, 255, 255));
		scrollPaneItems.setBorder(new LineBorder(new Color(51, 153, 255), 2));
		scrollPaneItems.setBounds(10, 72, 1026, 597);
		pnlInventoryManagement.add(scrollPaneItems);

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
			@Override
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
				ai.setLocationRelativeTo(null);
				ai.setVisible(true);
				ai.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
				ai.setAlwaysOnTop(true);
				frmUserWindow.setFocusable(false);


			}
		});
		btnAdd.setForeground(new Color(255, 255, 255));
		btnAdd.setFont(new Font("SansSerif", Font.PLAIN, 15));
		btnAdd.setBackground(new Color(51, 102, 255));
		btnAdd.setBorder(null);
		btnAdd.setBounds(10, 678, 89, 35);
		pnlInventoryManagement.add(btnAdd);

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
						ei.setLocationRelativeTo(null);
						ei.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
						ei.setAlwaysOnTop(true);
						frmUserWindow.setFocusable(false);
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
		pnlInventoryManagement.add(btnEdit);

		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(tblItems.getSelectedRow()==-1){
					Warning.setVisible(true);
				}else{
					try{
						if(JOptionPane.showConfirmDialog(frmUserWindow, "Are you sure you want to delete?\n\n*This item will go to Delete Logs\n\n(Item Master Data/Delete Logs tab)","PROMPT",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==0){
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
		pnlInventoryManagement.add(btnDelete);

		JLabel lblSearch = new JLabel("Search");
		lblSearch.setForeground(new Color(51, 153, 255));
		lblSearch.setFont(new Font("SansSerif", Font.PLAIN, 24));
		lblSearch.setBounds(10, 34, 79, 29);
		pnlInventoryManagement.add(lblSearch);

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
						String itemStyle = rs.getString("ItemStyle");
						String itemCategory = rs.getString("ItemCategory");
						String itemSize = rs.getString("ItemSize");
						String itemQuantity = rs.getString("ItemQuantity");
						String srp = rs.getString("SuggestedRetailPrice");
						String price = rs.getString("Price");
						model.addRow(new Object[]{itemCode,itemName,itemBrand,itemColor,itemStyle,itemCategory,itemSize,itemQuantity,srp,price});
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
		pnlInventoryManagement.add(txtSearchItem);
		txtSearchItem.setColumns(10);

		Warning.setVisible(false);
		Warning.setOpaque(false);
		Warning.setAutoscrolls(true);
		Warning.setBounds(307, 674, 213, 41);
		pnlInventoryManagement.add(Warning);
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
				ab.setLocationRelativeTo(null);
				ab.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
				ab.setAlwaysOnTop(true);
				frmUserWindow.setFocusable(false);

			}
		});
		btnAddBrand.setForeground(Color.WHITE);
		btnAddBrand.setFont(new Font("SansSerif", Font.PLAIN, 15));
		btnAddBrand.setBorder(null);
		btnAddBrand.setBackground(new Color(51, 102, 255));
		btnAddBrand.setBounds(947, 680, 89, 35);
		pnlInventoryManagement.add(btnAddBrand);
		
		JButton btnAddColor = new JButton("Add Color");
		btnAddColor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AddColor ac = new AddColor();
				ac.setVisible(true);
				ac.setLocationRelativeTo(null);
				ac.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
				ac.setAlwaysOnTop(true);
				frmUserWindow.setFocusable(false);
			}
		});
		btnAddColor.setForeground(Color.WHITE);
		btnAddColor.setFont(new Font("SansSerif", Font.PLAIN, 15));
		btnAddColor.setBorder(null);
		btnAddColor.setBackground(new Color(51, 102, 255));
		btnAddColor.setBounds(848, 680, 89, 35);
		pnlInventoryManagement.add(btnAddColor);

		pnlItemMasterData.setBorder(new LineBorder(new Color(51, 153, 255)));
		pnlItemMasterData.setBackground(new Color(255, 255, 255));
		pnlItemMasterData.setBounds(310, 33, 0, 0);
		frmUserWindow.getContentPane().add(pnlItemMasterData);
		pnlItemMasterData.setLayout(null);

		JButton btmCloseIMD = new JButton("X");
		btmCloseIMD.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pnlPrompt.setVisible(true);
				txtPrompt.setText("Are your sure you want to close ?");

			}
		});
		btmCloseIMD.setForeground(Color.WHITE);
		btmCloseIMD.setFont(new Font("SansSerif", Font.BOLD, 11));
		btmCloseIMD.setBorder(null);
		btmCloseIMD.setBackground(new Color(0, 51, 255));
		btmCloseIMD.setBounds(1009, 0, 37, 23);
		pnlItemMasterData.add(btmCloseIMD);

		JLabel lblItemMasterData = new JLabel("Item Master Data");
		lblItemMasterData.setForeground(Color.WHITE);
		lblItemMasterData.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblItemMasterData.setBounds(10, 0, 162, 23);
		pnlItemMasterData.add(lblItemMasterData);

		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(UserWindow.class.getResource("/app/image/title.png")));
		label_1.setBounds(0, 0, 1046, 23);
		pnlItemMasterData.add(label_1);

		JLabel label_2 = new JLabel("Search");
		label_2.setForeground(SystemColor.textHighlight);
		label_2.setFont(new Font("SansSerif", Font.PLAIN, 24));
		label_2.setBounds(10, 34, 79, 29);
		pnlItemMasterData.add(label_2);

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
						String itemStyle = rs.getString("ItemStyle");
						String itemCategory = rs.getString("ItemCategory");
						String itemSize = rs.getString("ItemSize");
						String itemQuantity = rs.getString("ItemQuantity");
						String srp = rs.getString("SuggestedRetailPrice");
						String price = rs.getString("Price");
						model.addRow(new Object[]{itemCode,itemName,itemBrand,itemColor,itemStyle,itemCategory,itemSize,itemQuantity,srp,price});
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
		pnlItemMasterData.add(txtSearchData);

		final JScrollPane scrollPaneItemList = new JScrollPane();
		scrollPaneItemList.setBorder(new LineBorder(new Color(51, 153, 255), 2));

		final JScrollPane scrollPaneDeleteItems = new JScrollPane();
		scrollPaneDeleteItems.setBorder(new LineBorder(new Color(51, 153, 255), 2));
		scrollPaneDeleteItems.setBounds(10, 112, 0, 0);
		pnlItemMasterData.add(scrollPaneDeleteItems);

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
			@Override
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPaneDeleteItems.setViewportView(tblDeleted);
		scrollPaneItemList.setBounds(10, 112, 1026, 566);
		pnlItemMasterData.add(scrollPaneItemList);

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
			@Override
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
					JOptionPane.showMessageDialog(frmUserWindow, "Select item to retreive !");
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
							JOptionPane.showMessageDialog(frmUserWindow, "Successfully Retreive !");
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
		pnlItemMasterData.add(btnItemList);

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
		btnDeletedItems.setBounds(110, 76, 111, 35);
		pnlItemMasterData.add(btnDeletedItems);

		btnRetreiveItem.setVisible(false);
		btnRetreiveItem.setForeground(Color.WHITE);
		btnRetreiveItem.setFont(new Font("SansSerif", Font.PLAIN, 15));
		btnRetreiveItem.setBorder(null);
		btnRetreiveItem.setBackground(new Color(51, 102, 255));
		btnRetreiveItem.setBounds(925, 684, 111, 35);
		pnlItemMasterData.add(btnRetreiveItem);

		pnlDelivery.setVisible(false);
		pnlDelivery.setBackground(new Color(255, 255, 255));
		pnlDelivery.setBorder(new LineBorder(new Color(51, 153, 255)));
		pnlDelivery.setBounds(310, 33, 0, 0);
		frmUserWindow.getContentPane().add(pnlDelivery);
		pnlDelivery.setLayout(null);

		JButton btnCloseDelivery = new JButton("X");
		btnCloseDelivery.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pnlPrompt.setVisible(true);
				txtPrompt.setText("Are your sure you want to close ?");
			}
		});
		btnCloseDelivery.setForeground(Color.WHITE);
		btnCloseDelivery.setFont(new Font("SansSerif", Font.BOLD, 11));
		btnCloseDelivery.setBorder(null);
		btnCloseDelivery.setBackground(new Color(0, 51, 255));
		btnCloseDelivery.setBounds(1009, 0, 37, 23);
		pnlDelivery.add(btnCloseDelivery);

		JLabel lblDelivery = new JLabel("Delivery");
		lblDelivery.setForeground(Color.WHITE);
		lblDelivery.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblDelivery.setBounds(10, 0, 162, 23);
		pnlDelivery.add(lblDelivery);

		JLabel label_5 = new JLabel("");
		label_5.setIcon(new ImageIcon(UserWindow.class.getResource("/app/image/title.png")));
		label_5.setBounds(0, 0, 1046, 23);
		pnlDelivery.add(label_5);

		JPanel tabDelivery = new JPanel();
		tabDelivery.setBackground(new Color(255, 255, 255));
		tabDelivery.setBounds(10, 33, 1026, 680);
		pnlDelivery.add(tabDelivery);
		tabDelivery.setLayout(null);

		final JPanel DeliveryTab = new JPanel();
		DeliveryTab.setBackground(new Color(255, 255, 255));
		DeliveryTab.setBounds(0, 35, 1016, 645);
		tabDelivery.add(DeliveryTab);
		DeliveryTab.setLayout(null);

		final JButton btnPrintDeliveryReport = new JButton("Print Delivery Report");
		btnPrintDeliveryReport.setVisible(false);
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
			@Override
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
						JOptionPane.showMessageDialog(frmUserWindow, "Specify the Fields !");
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
							JOptionPane.showMessageDialog(frmUserWindow, "Unsufficient Quantity ! !");
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
									JOptionPane.showMessageDialog(frmUserWindow, "Delivery is on Process !");

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
						String itemStyle = rs.getString("ItemStyle");
						String itemCategory = rs.getString("ItemCategory");
						String itemSize = rs.getString("ItemSize");
						String itemQuantity = rs.getString("ItemQuantity");
						String srp = rs.getString("SuggestedRetailPrice");
						String price = rs.getString("Price");
						model.addRow(new Object[]{itemCode,itemName,itemBrand,itemColor,itemStyle,itemCategory,itemSize,itemQuantity,srp,price});
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
			@Override
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPaneDeliveryReports.setViewportView(tblDeliveryReports);


		JLabel shoeThis = new JLabel("");
		shoeThis.setIcon(new ImageIcon(UserWindow.class.getResource("/app/image/ShoeThis.png")));
		shoeThis.setBounds(445, 237, 800, 300);
		frmUserWindow.getContentPane().add(shoeThis);

		JLabel mainBg = new JLabel("");
		mainBg.setIcon(new ImageIcon(UserWindow.class.getResource("/app/image/bgwhite.png")));
		mainBg.setBounds(0, 0, 1366, 768);
		frmUserWindow.getContentPane().add(mainBg);
	}
	public void clearText(){
		txtDeliverTo.setText("");;
		txtAddress.setText("");
		txtQuantityDeliver.setText("");
		txtCustomerName.setText("");
		txtContactNo.setText("");
		txtQuantitySale.setText("");

	}
	public void updateItemTable(){
		TestConnection tc = new TestConnection();
		DatabaseManager dm = new DatabaseManager();
		DefaultTableModel modelInventory = (DefaultTableModel)tblItems.getModel();
		DefaultTableModel modelItemData = (DefaultTableModel)tblItemData.getModel();
		DefaultTableModel modelDeliver = (DefaultTableModel)tblDeliveryItems.getModel();
		DefaultTableModel modelSales = (DefaultTableModel)tblSalesItem.getModel();
		modelDeliver.getDataVector().removeAllElements();
		modelInventory.getDataVector().removeAllElements();
		modelItemData.getDataVector().removeAllElements();
		modelSales.getDataVector().removeAllElements();

		tblItems.updateUI();
		tblItemData.updateUI();
		tblDeliveryItems.updateUI();
		tblSalesItem.updateUI();
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
				modelSales.addRow(new Object[]{itemCode,itemName,itemBrand,itemColor,itemStyle,itemCategory,itemSize,itemQuantity,price});

			}
			tblItems.updateUI();
			tblItemData.updateUI();
			tblDeliveryItems.updateUI();
			tblSalesItem.updateUI();
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
	public void updateSalesReportTable(){
		TestConnection t = new TestConnection();
		DatabaseManager dm = new DatabaseManager();
		DefaultTableModel modelSales = (DefaultTableModel)tblSalesReport.getModel();
		modelSales.getDataVector().removeAllElements();
		tblSalesReport.updateUI();

		try{
			ResultSet rs = dm.sales(t.getConnection());
			while(rs.next()){

				String customerNo = rs.getString("salesNo");
				String customerName = rs.getString("customerName");
				String contactNo = rs.getString("contactNo");
				String itemCode = rs.getString("ItemCode");
				String itemName = rs.getString("ItemName");
				String price = rs.getString("Price");
				String quantity = rs.getString("Quantity");
				String total = rs.getString("Total");
				String dateTime = rs.getString("DateTime");
				String status = rs.getString("Status");
				modelSales.addRow(new Object[]{customerNo,customerName,contactNo,itemCode,itemName,price,quantity,total,dateTime,status});



			}

			tblSalesReport.updateUI();
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
	public Inventory getSelectedSalesItem(){
		Inventory i = new Inventory();
		DefaultTableModel modelItems = (DefaultTableModel)tblSalesItem.getModel();

		i.setItemCode(modelItems.getValueAt(tblSalesItem.getSelectedRow(), 0).toString());
		i.setItemName(modelItems.getValueAt(tblSalesItem.getSelectedRow(), 1).toString());
		i.setItemBrand(modelItems.getValueAt(tblSalesItem.getSelectedRow(), 2).toString());
		i.setItemColor(modelItems.getValueAt(tblSalesItem.getSelectedRow(), 3).toString());
		i.setItemStyle(modelItems.getValueAt(tblSalesItem.getSelectedRow(), 4).toString());
		i.setItemCategory(modelItems.getValueAt(tblSalesItem.getSelectedRow(), 5).toString());
		i.setSize(modelItems.getValueAt(tblSalesItem.getSelectedRow(), 6).toString());
		i.setQuantityAvailable(Integer.parseInt(modelItems.getValueAt(tblSalesItem.getSelectedRow(), 7).toString()));
		i.setPrice(Integer.parseInt(modelItems.getValueAt(tblSalesItem.getSelectedRow(), 8).toString()));

		return i;
	}

	public Sales getSelectedSales(){
		Sales s = new Sales();
		DefaultTableModel modelSales = (DefaultTableModel)tblSalesReport.getModel();

		s.setSalesNo(Integer.parseInt(modelSales.getValueAt(tblSalesReport.getSelectedRow(), 0).toString()));
		s.setCustomerName(modelSales.getValueAt(tblSalesReport.getSelectedRow(), 1).toString());
		s.setContactNumber(modelSales.getValueAt(tblSalesReport.getSelectedRow(), 2).toString());
		s.setItemCode(modelSales.getValueAt(tblSalesReport.getSelectedRow(), 3).toString());
		s.setItemName(modelSales.getValueAt(tblSalesReport.getSelectedRow(), 4).toString());
		s.setPrice(Integer.parseInt(modelSales.getValueAt(tblSalesReport.getSelectedRow(), 5).toString()));
		s.setQuantity(Integer.parseInt(modelSales.getValueAt(tblSalesReport.getSelectedRow(), 6).toString()));
		s.setTotal(Integer.parseInt(modelSales.getValueAt(tblSalesReport.getSelectedRow(), 7).toString()));
		s.setDateTime(modelSales.getValueAt(tblSalesReport.getSelectedRow(), 8).toString());
		s.setStatus(modelSales.getValueAt(tblSalesReport.getSelectedRow(), 9).toString());
		return s;
	}
}
