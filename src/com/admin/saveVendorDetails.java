package com.admin;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.connection.DatabaseConnection;

@WebServlet("/saveVendorDetails")
public class saveVendorDetails extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String vendorName=request.getParameter("vendorName");
		String emailAddress=request.getParameter("emailAddress");
		String contactNo=request.getParameter("contactNo");
		String address=request.getParameter("address");
		String gstNo=request.getParameter("gstNo");
		String vendorType=request.getParameter("vendorType");
		String city=request.getParameter("city");
		String state=request.getParameter("state");
		String zipCode=request.getParameter("zipCode");
		String remark=request.getParameter("remark");
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm a");
		String currentDateTime = now.format(formatter);
		
		int i = DatabaseConnection.insertUpdateFromSqlQuery("insert into tbl_vendor_details(vendor_name,vendor_email_id,vendor_mobile_no,vendor_address,vendor_gst_no,vendor_type,city,state,zipcode,remark,created_date) values('" + vendorName+ "','" + emailAddress + "','"+contactNo+"','" + address + "','" + gstNo + "','"+vendorType+"','"+city+"','"+state+"','"+zipCode+"','"+remark+"','"+currentDateTime+"')");
		if (i > 0) {
			String success = "Vendor added successfully.";
			session.setAttribute("message", success);
			response.sendRedirect("admin-add-product.jsp");
		}


	}
}
