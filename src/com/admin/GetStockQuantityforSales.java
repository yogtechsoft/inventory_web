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
import com.model.StockQuantityModel;

@WebServlet("/GetStockQuantityforSales")
public class GetStockQuantityforSales extends  HttpServlet {

protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	Double quantity = 0d;
	String productId=request.getParameter("productId");
	List<StockQuantityModel> getDetails=new ArrayList<StockQuantityModel>();

	try {
		ResultSet captchResultSet = DatabaseConnection.getResultFromSqlQuery("select * from tbl_product_inventory where id="+productId);
		while (captchResultSet.next()) {
			StockQuantityModel st=new StockQuantityModel();
			st.setStockQuantity(Double.valueOf(captchResultSet.getString("stock_quantity")));
			st.setProudctCategory(captchResultSet.getString("product_category"));
			st.setVdendorName(captchResultSet.getString("vendor_name"));
			getDetails.add(st);

		}
		PrintWriter out = response.getWriter();
		response.setContentType("text/plain");
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(getDetails);
		out.print(json);
		out.flush();
        out.close();
		

	} catch (Exception e) {
	    e.printStackTrace();
	}

	}
}
