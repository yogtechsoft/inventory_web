package com.admin;

import java.io.IOException;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.connection.DatabaseConnection;

@WebServlet("/PurchaseStockDetails")
public class PurchaseStockDetails extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String productId=request.getParameter("productId");
		String productName=request.getParameter("productName");
		String price=request.getParameter("price");
		String description=request.getParameter("description");
		String mprice=request.getParameter("mrpPrice");
		String quantity=request.getParameter("stockQuantity");
		String status="Active";
		String category=request.getParameter("category");
		String newStockDetails=request.getParameter("updateStock");
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm a");
		String currentDateTime = now.format(formatter);
		String pending_stock_quantity="";
		//fetch stock details
		try {
			
			ResultSet captchResultSet = DatabaseConnection.getResultFromSqlQuery("SELECT stock_quantity FROM tbl_product_inventory where id="+productId+"");
			while (captchResultSet.next()) {
				pending_stock_quantity=	captchResultSet.getString(1);
			}
			Double updateValue=Double.parseDouble(pending_stock_quantity)+Double.parseDouble(newStockDetails);
			int a=DatabaseConnection.insertUpdateFromSqlQuery("UPDATE tbl_product_inventory SET stock_quantity='"+updateValue+"',price='"+price+"',mrp_price='"+mprice+"',description='"+description+"',product_category='"+category+"' WHERE id="+productId);
			
			
			int i = DatabaseConnection.insertUpdateFromSqlQuery("insert into tbl_puchase_stock_details(product_name,price,product_description,mrp_price,stock_quantity,active_status,create_date,product_category,new_stock_details) values('" + productName+ "','" + price + "','"+description+"','" + mprice + "','" + quantity + "','"+status+"','"+currentDateTime+"','"+category+"','"+newStockDetails+"')");
			if (i > 0) {
				String success = "Stock Updated successfully.";
				session.setAttribute("message", success);
				response.sendRedirect("admin-all-orders.jsp");
			}

		}catch(Exception e) {
			e.printStackTrace();
		}
		
		//update details
		

	}
}
