package com.admin;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.connection.DatabaseConnection;
import com.model.PurchaseStockModel;

/**
 * Servlet implementation class AddProducts
 */
@WebServlet("/AddProducts")
public class AddProducts extends HttpServlet {
	//private final String UPLOAD_DIRECTORY = "D://bkp/APIDevelopment/OnlineShoppingSystemCode+Database/OnlineShoppingSystem/WebContent/uploads/";
	private final String UPLOAD_DIRECTORY = "/home/jarandes/image/";
	//private final String UPLOAD_DIRECTORY = "D://image/";


	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String productName=request.getParameter("pname");
		String price=request.getParameter("price");
		String description=request.getParameter("description");
		String mprice=request.getParameter("mprice");
		String quantity=request.getParameter("quantity");
		String status=request.getParameter("status");
		String category=request.getParameter("category");
		String vendorId=request.getParameter("vendorName");

		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm a");

		String currentDateTime = now.format(formatter);
		String vendorName="";
		ResultSet captchResultSet = DatabaseConnection.getResultFromSqlQuery("select * from tbl_vendor_details where id="+vendorId);
		try {
			while (captchResultSet.next()) {
				vendorName=captchResultSet.getString("vendor_name");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		int i = DatabaseConnection.insertUpdateFromSqlQuery("insert into tbl_product_inventory(product_name,price,description,mrp_price,stock_quantity,active,create_date,product_category,vendor_name) values('" + productName+ "','" + price + "','"+description+"','" + mprice + "','" + quantity + "','"+status+"','"+currentDateTime+"','"+category+"','"+vendorName+"')");
		if (i > 0) {
			String success = "Product added successfully.";
			session.setAttribute("message", success);
			response.sendRedirect("admin-add-product.jsp");
		}


	}
}
