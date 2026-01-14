package com.admin;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.connection.DatabaseConnection;

@WebServlet("/SalesProductDetails")
public class SalesProductDetails extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String customerName=request.getParameter("customerName");
		String customerAddress=request.getParameter("customerAddress");
		String emailId=request.getParameter("emailId");
		String mobileNo=request.getParameter("mobileNo");
		String productName=request.getParameter("productName");
		String stockQuantity=request.getParameter("stockQuantity");
		String proudctCategory=request.getParameter("proudctCategory");
		String vendorName=request.getParameter("vendorName");
		String salesQuantity=request.getParameter("salesQuantity");
		String salesPrice=request.getParameter("salesPrice");

		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm a");

		String currentDateTime = now.format(formatter);
		String prodName="";
		Double stockQuantityCalculate=Double.parseDouble(stockQuantity);
		Double salesQuantityCalcualte=Double.parseDouble(salesQuantity);
		Double totalStock=stockQuantityCalculate - salesQuantityCalcualte;
		String totalStockStr = String.valueOf(totalStock);
		int a=DatabaseConnection.insertUpdateFromSqlQuery("UPDATE tbl_product_inventory SET stock_quantity='"+totalStockStr+"' WHERE id="+productName);

		
		ResultSet captchResultSet = DatabaseConnection.getResultFromSqlQuery("select * from tbl_product_inventory where id="+productName);
		try {
			while (captchResultSet.next()) {
				prodName=captchResultSet.getString("product_name");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		int i = DatabaseConnection.insertUpdateFromSqlQuery("insert into tbl_sale_product_details(customer_name,customer_address,email_id,mobile_no,product_name,total_quantity,product_category,vendor_name,sale_quantity,sale_price,created_date) values('" + customerName+ "','" + customerAddress + "','"+emailId+"','" + mobileNo + "','" + prodName + "','"+stockQuantity+"','"+proudctCategory+"','"+vendorName+"','"+salesQuantity+"','"+salesPrice+"','"+currentDateTime+"')");
		if (i > 0) {
			String success = "Product added successfully.";
			session.setAttribute("message", success);
			response.sendRedirect("admin-view-customers.jsp");
		}


	}
}
