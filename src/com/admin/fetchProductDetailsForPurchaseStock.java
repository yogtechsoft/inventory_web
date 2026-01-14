package com.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.connection.DatabaseConnection;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.model.PurchaseStockModel;

@WebServlet("/fetchProductDetailsForPurchaseStock")
public class fetchProductDetailsForPurchaseStock extends  HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			String productId = request.getParameter("productId");
			List<PurchaseStockModel> getDetails=new ArrayList<PurchaseStockModel>();
			ResultSet captchResultSet = DatabaseConnection.getResultFromSqlQuery("select * from tbl_product_inventory where id="+productId);
			while (captchResultSet.next()) {
				PurchaseStockModel st=new PurchaseStockModel();
				st.setProductName(captchResultSet.getString("product_name"));
				st.setPrice(captchResultSet.getString("price"));
				st.setDescription(captchResultSet.getString("description"));
				st.setMrpPrice(captchResultSet.getString("mrp_price"));
				st.setQuantity(captchResultSet.getString("stock_quantity"));
				st.setDate(captchResultSet.getString("create_date"));
				st.setCategory(captchResultSet.getString("product_category"));
				st.setVendorName(captchResultSet.getString("vendor_name"));
				getDetails.add(st);
				
			}
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String json = gson.toJson(getDetails);
			out.print(json);
			out.flush();
	        out.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
