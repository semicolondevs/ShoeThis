package app.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import app.model.Brands;
import app.model.Color;
import app.model.DeleteLogs;
import app.model.Inventory;
import app.model.Delivery;
import app.model.Superuser;
import app.model.User;

public class DatabaseManager {


	public ResultSet accountAdmin(Connection conn) throws SQLException, ClassNotFoundException  {  
		String query = "SELECT * FROM users";  
		Statement st = conn.createStatement();  
		ResultSet rs = st.executeQuery(query);  
		return rs;  
	}
	
	public ResultSet brands(Connection conn) throws SQLException, ClassNotFoundException  {  
		String query = "SELECT * FROM brands";  
		Statement st = conn.createStatement();  
		ResultSet rs = st.executeQuery(query);  
		return rs;  
	}
	
	public ResultSet accountSuper(Connection conn) throws SQLException, ClassNotFoundException  {  
		String query = "SELECT * FROM superuser";  
		Statement st = conn.createStatement();  
		ResultSet rs = st.executeQuery(query);  
		return rs;  
	}

	public ResultSet inventory(Connection conn) throws SQLException, ClassNotFoundException{
		String query = "SELECT * FROM inventory_items";  
		Statement st = conn.createStatement();  
		ResultSet rs = st.executeQuery(query);  
		return rs; 
	}
	public ResultSet color(Connection conn) throws SQLException, ClassNotFoundException{
		String query = "SELECT * FROM color";  
		Statement st = conn.createStatement();  
		ResultSet rs = st.executeQuery(query);  
		return rs; 
	}

	public ResultSet deliver(Connection conn) throws SQLException, ClassNotFoundException{
		String query = "SELECT * FROM delivery ORDER BY DeliveryNo DESC";  
		Statement st = conn.createStatement();  
		ResultSet rs = st.executeQuery(query);  
		return rs; 
	}
	public ResultSet deleted(Connection conn) throws SQLException, ClassNotFoundException{
		String query = "SELECT * FROM delete_logs";  
		Statement st = conn.createStatement();  
		ResultSet rs = st.executeQuery(query);  
		return rs; 
	}
	public int insertBrand(Connection conn,Brands b) throws SQLException, ClassNotFoundException {
		String query ="INSERT INTO brands (brandId,brandName) VALUES('"+b.getBrandId()+"','"+b.getBrandName()+"')";
		Statement st = conn.createStatement();
		int rs = st.executeUpdate(query);		
		return rs;
	}
	public int insertColor(Connection conn,Color c) throws SQLException, ClassNotFoundException {
		String query ="INSERT INTO color (colorId,color) VALUES('"+c.getColorId()+"','"+c.getColor()+"')";
		Statement st = conn.createStatement();
		int rs = st.executeUpdate(query);		
		return rs;
	}
	public int insertDelivery(Connection conn,Delivery d) throws SQLException, ClassNotFoundException {
		String query ="INSERT INTO delivery (DeliveryNo,ItemCode,ItemName,Receipient,Quantity,TotalPrice,Address,DateDelivered) VALUES('"+d.getDeliveryNo()
				+"','"+d.getItemCode()+"','"+d.getItemName()+"','"+d.getReceipient()+"','"+d.getQuantity()+"','"+d.getTotalPrice()+"','"+d.getAddress()+"','"+d.getDateDelivered()+"')";
		Statement st = conn.createStatement();
		int rs = st.executeUpdate(query);		
		return rs;
	}

	public int insertAccount(Connection conn, User u) throws SQLException, ClassNotFoundException {
		String query ="INSERT INTO users (adminID,username,password,Name,ContactNumber,PinCode) VALUES('"+u.getID()
				+"','"+u.getUserName()+"','"+u.getPassword()+"','"+u.getName()+"','"+u.getContactNo()+"','"+u.getPinCode()+"')";
		Statement st = conn.createStatement();
		int rs = st.executeUpdate(query);		
		return rs;
	}

	public int insertInventory(Connection conn, Inventory i) throws SQLException, ClassNotFoundException {
		String query ="INSERT INTO inventory_items (ItemCode,ItemName,ItemBrand,ItemColor,ItemStyle,ItemCategory,ItemSize,ItemQuantity,Price) VALUES('"+i.getItemCode()
				+"','"+i.getItemName()+"','"+i.getItemBrand()+"','"+i.getItemColor()+"','"+i.getItemStyle()+"','"+i.getItemCategory()+"','"+i.getSize()+"','"+i.getQuantityAvailable()+"','"+i.getPrice()+"')";
		Statement st = conn.createStatement();
		int rs = st.executeUpdate(query);		
		return rs;
	}
	public int insertDeleteLogs(Connection conn, DeleteLogs d) throws SQLException, ClassNotFoundException {
		String query ="INSERT INTO delete_logs (ItemCode,ItemName,ItemBrand,ItemColor,ItemStyle,ItemCategory,ItemSize,ItemQuantity,ItemPrice,DateDeleted) VALUES('"+d.getItemCode()
				+"','"+d.getItemName()+"','"+d.getItemBrand()+"','"+d.getItemColor()+"','"+d.getItemStyle()+"','"+d.getItemCategory()+"','"+d.getSize()+"','"+d.getQuantityAvailable()+"','"+d.getPrice()+"','"+d.getDateDeleted()+"')";
		Statement st = conn.createStatement();
		int rs = st.executeUpdate(query);		
		return rs;
	}


	public int deleteUser(Connection conn,String id) throws SQLException, ClassNotFoundException{
		String query ="DELETE FROM users WHERE adminID ='"+id+"'";
		Statement st = conn.createStatement();
		int rs = st.executeUpdate(query);		
		return rs;
	}

	public int deleteItem(Connection conn,String code) throws SQLException, ClassNotFoundException{
		String query ="DELETE FROM inventory_items WHERE ItemCode ='"+code+"'";
		Statement st = conn.createStatement();
		int rs = st.executeUpdate(query);		
		return rs;
	}
	public int deleteBrand(Connection conn,String id) throws SQLException, ClassNotFoundException{
		String query ="DELETE FROM brands WHERE brandId ='"+id+"'";
		Statement st = conn.createStatement();
		int rs = st.executeUpdate(query);		
		return rs;
	}
	
	public int retrieve(Connection conn,String code) throws SQLException, ClassNotFoundException{
		String query ="DELETE FROM delete_logs WHERE ItemCode ='"+code+"'";
		Statement st = conn.createStatement();
		int rs = st.executeUpdate(query);		
		return rs;
	}

	public int updateItem(Connection conn, Inventory i) throws SQLException, ClassNotFoundException {
		String query = "UPDATE inventory_items SET ItemName ='"+i.getItemName()+"', ItemBrand = '"+i.getItemBrand()+"',ItemColor = '"+i.getItemColor()+"', ItemSize='"+i.getSize()+"', ItemQuantity ='"+i.getQuantityAvailable()
				+"', Price ='"+i.getPrice()+"' WHERE ItemCode ='"+i.getItemCode()+"'";    
		Statement st = conn.createStatement();  
		int rs = st.executeUpdate(query);  
		return rs; 
	}
	public int updateQuantity(Connection conn, Inventory i)throws SQLException, ClassNotFoundException{
		String query ="UPDATE inventory_items SET ItemQuantity='"+i.getQuantityAvailable()+"' WHERE ItemCode='"+i.getItemCode()+"'";
		Statement st = conn.createStatement();
		int rs= st.executeUpdate(query);
		return rs;
	}

	public int updateUser(Connection conn, User u) throws SQLException, ClassNotFoundException {
		String query = "UPDATE users SET username ='"+u.getUserName()+"', password ='"+u.getPassword()
				+"',Name='"+u.getName()+"', ContactNumber ='"+u.getContactNo()+"', PinCode='"+u.getPinCode()+"'  WHERE adminID ='"+u.getID()+"'";    
		Statement st = conn.createStatement();  
		int rs = st.executeUpdate(query);  
		return rs; 
	}
	public int updateBrand(Connection conn, Brands b) throws SQLException, ClassNotFoundException {
		String query = "UPDATE brands SET brandName ='"+b.getBrandName()+"'  WHERE brandId ='"+b.getBrandId()+"'";    
		Statement st = conn.createStatement();  
		int rs = st.executeUpdate(query);  
		return rs; 
	}
	public int updateSuperuserPassword(Connection conn, Superuser su) throws SQLException, ClassNotFoundException {
		String query = "UPDATE superuser SET password ='"+su.getPassword()+"'  WHERE username ='"+su.getUsername()+"'";    
		Statement st = conn.createStatement();  
		int rs = st.executeUpdate(query);  
		return rs; 
	}
	public int updateAdminPassword(Connection conn, User u) throws SQLException, ClassNotFoundException {
		String query = "UPDATE users SET password ='"+u.getPassword()+"'  WHERE username ='"+u.getUserName()+"'";    
		Statement st = conn.createStatement();  
		int rs = st.executeUpdate(query);  
		return rs; 
	}
	public ResultSet searchItem(Connection conn,String name)throws ClassNotFoundException, SQLException {
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM inventory_items WHERE ItemName LIKE '%"+name+"%' OR ItemColor LIKE '%"+name+"%' OR ItemBrand LIKE '%"+name+"%'");
		return rs;
	}
	
	public ResultSet searchUser(Connection conn,String name)throws ClassNotFoundException, SQLException {
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM users WHERE username LIKE '%"+name+"%' OR Name LIKE '%"+name+"%'");
		return rs;
	}
	

}
