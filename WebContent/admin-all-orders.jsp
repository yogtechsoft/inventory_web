<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.connection.*"%>
<%@ page import="java.sql.*"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">

<!-- Mirrored from www.binarytheme.com/BTlivedemos/2014/08/29/horizontal-admin/ by HTTrack Website Copier/3.x [XR&CO'2014], Sun, 14 Jul 2019 14:46:41 GMT -->
<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1" />
<meta name="description" content="" />
<meta name="author" content="" />
<!--[if IE]>
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <![endif]-->
<title>Inventory Management System</title>
<!-- BOOTSTRAP CORE STYLE  -->
<link href="assets/css/bootstrap.css" rel="stylesheet" />
<!-- FONT AWESOME STYLE  -->
<link href="assets/css/font-awesome.css" rel="stylesheet" />
<!-- CUSTOM STYLE  -->
<link href="assets/css/style.css" rel="stylesheet" />
<!-- GOOGLE FONT -->
<link href='http://fonts.googleapis.com/css?family=Open+Sans'
	rel='stylesheet' type='text/css' />

</head>
<body>
	<%
		if (session.getAttribute("uname") != null && session.getAttribute("uname") != "") {
	%>
	<div class="navbar navbar-inverse set-radius-zero">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="index-2.html"> <img
					src="assets/img/logo.png" />
				</a>

			</div>

			<div class="right-div">
				<a href="admin-logout.jsp" class="btn btn-danger pull-right">LOG
					ME OUT</a>
			</div>
		</div>
	</div>
	<jsp:include page="adminHeader.jsp"></jsp:include>
	<!-- MENU SECTION END-->
	<form role="form" action="PurchaseStockDetails" method="post">
	<div class="content-wrapper">
		<div class="container-fluid">
			<div class="row pad-botm">
				<div class="col-md-12">
					<h4 class="header-line">Purchase Stock Details</h4>
				</div>
				<div class="row">
				  <div class="col-md-3">
					<div class="form-group">
						<label>Select product Name</label> 
							<select class="form-control" id="productId" name="productId">
								<option>Select</option>
									<%
										int ip = 0;
									ResultSet contractorName = DatabaseConnection.getResultFromSqlQuery("select * from tbl_product_inventory");
										while (contractorName.next()) {
										ip++;
									%>
										<option value="<%=contractorName.getString(1)%>"><%=contractorName.getString(2)%></option> 
										<%
											}
										%>
							</select>
				  		</div>
					</div>
				</div>
				<div class="row" id="indentName">
				<div class="col-md-3">
					<div class="form-group">
						<label>Product Name</label> 
						 <input class="form-control" type="text" name="productName" id="productName"  required="required" />
					</div>
				</div>
				<div class="col-md-3">
					<div class="form-group">
						<label>Price</label> 
						 <input class="form-control" type="text" name="price" id="price"  required="required" />
					</div>
				</div>
				<div class="col-md-3">
					<div class="form-group">
						<label>Description</label> 
						 <input class="form-control" type="text" name="description" id="description"  required="required" />
					</div>
				</div>
				<div class="col-md-3">
					<div class="form-group">
						<label>MRP Price</label>
						<input class="form-control" type="text" name="mrpPrice" id="mrpPrice" required="required" />
					</div>
				</div>
			</div>
			<div class="row" id="quantity">
				<div class="col-md-3">
					<div class="form-group">
						<label>Stock Quantity</label> 
						 <input class="form-control" type="text" name="stockQuantity" id="stockQuantity"  required="required" />
					</div>
				</div>
				<div class="col-md-3">
					<div class="form-group">
						<label>Date</label> 
						 <input class="form-control" type="text" name="date" id="date" required="required" />
					</div>
				</div>
				<div class="col-md-3">
					<div class="form-group">
						<label>Category</label> 
						 <input class="form-control" type="text" name="category" id="category"   />
					</div>
				</div>
				<div class="col-md-3">
					<div class="form-group">
						<label>Vendor Name</label> 
						 <input class="form-control" type="text" name="vendorName" id="vendorName"   />
					</div>
				</div>
				<div class="col-md-3">
					<div class="form-group">
						<label>Enter Stock</label>
						<input class="form-control" type="text" name="updateStock" id="updateStock" required="required" placeholder="Enter the New Stock" />
					</div>
				</div>
			</div>
			<div class="row" id="button">
				<div class="col-md-3">
					<div class="form-group">
						 <button class="btn btn-primary" " name="stockQuantity" id="stockQuantity">Update</</button>
					</div>
				</div>
				</div>
			</div>
		</div>
	</div>
	</form>
	<!-- CONTENT-WRAPPER SECTION END-->
	<jsp:include page="admin-footer.jsp"></jsp:include>
	<!-- FOOTER SECTION END-->
	<!-- JAVASCRIPT FILES PLACED AT THE BOTTOM TO REDUCE THE LOADING TIME  -->
	<!-- CORE JQUERY  -->
	<script src="assets/js/jquery-1.10.2.js"></script>
	<!-- BOOTSTRAP SCRIPTS  -->
	<script src="assets/js/bootstrap.js"></script>
	<!-- CUSTOM SCRIPTS  -->
	<script src="assets/js/custom.js"></script>
	<script>
	 window.onload = function() {
         document.getElementById("indentName").style.display = "none"; 
         document.getElementById("quantity").style.display = "none"; 
         document.getElementById("button").style.display = "none"; 
     };
     $("#productId").change(function(){
    	 var product = $("#productId").val();
    	 $.ajax({
  			url : 'fetchProductDetailsForPurchaseStock',
  			data : {
  				productId : product
  			},
  			success : function(responseText) {
  				var dataTablesObj = $.parseJSON(responseText);
  				
  				 for(var i=0;i<=dataTablesObj.length;i++){
  					$("#productName").val(dataTablesObj[i].productName);
  					$("#price").val(dataTablesObj[i].price);
  					$("#description").val(dataTablesObj[i].description);
  					$("#mrpPrice").val(dataTablesObj[i].mrpPrice);
  					$("#stockQuantity").val(dataTablesObj[i].quantity);
  					$("#date").val(dataTablesObj[i].date);
  					$("#category").val(dataTablesObj[i].category);
  					$("#vendorName").val(dataTablesObj[i].vendorName);
  				} 
  				
  				
  			}
  		}); 
    	 
    	 $("#indentName").show(); 
    	 $("#quantity").show(); 
    	 $("#button").show(); 
     });
	
	</script>
	<%
		} else {
	response.sendRedirect("admin-login.jsp");
	}
	%>
</body>
</html>
