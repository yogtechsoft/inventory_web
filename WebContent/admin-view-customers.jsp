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
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

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
	<div class="content-wrapper">
		<div class="container">
			<div class="row pad-botm">
				<div class="col-md-12">
					<h4 class="header-line">Sold Product Item</h4>

				</div>
			</div>
			<%
				String message = (String) session.getAttribute("message");
				if (message != null) {
				session.removeAttribute("message");
			%>
			<div class="alert alert-danger" id="success">Sold Item successfully.</div>
			<%
				}
			%>
			<div class="row">
				<div class="col-md-12">
					<div class="panel panel-info">
						<div class="panel-heading">Sold Product Item</div>
						<div class="panel-body">
							<form role="form" action="SalesProductDetails" method="post"
								>
								<div class="form-group">
									<label>Customer Name</label> <input class="form-control"
										type="text" name="customerName" id="customerName" />
								</div>
								<div class="form-group">
									<label>customer Address</label> <input class="form-control" type="text"
										name="customerAddress" id="customerAddress" />
								</div>
								<div class="form-group">
									<label>Email Id</label> <input class="form-control"
										type="text" style="min-height: 100px;" name="emailId" id="emailId"/>
								</div>
								<div class="form-group">
									<label>Mobile No</label> <input class="form-control"
										type="text" name="mobileNo" id="mobileNo" />
								</div>
								
								<div class="form-group">
									<label>Select Products</label> 								
								<select class="form-control" id="productName" name="productName">
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
								
								<div class="form-group">
									<label>Quantity</label> <input class="form-control"
										type="text" name="stockQuantity" id="stockQuantity" />
								</div>
								<div class="form-group">
									<label>Product Category</label>
									 <input class="form-control"
										type="text" name="proudctCategory" id="proudctCategory" />
								</div>
								
								<div class="form-group">
									<label>Vendor Name</label>
									 <input class="form-control"
										type="text" name="vendorName" id="vendorName" />
								</div>
								
								<div class="form-group">
									<label>Enter Quantity Fir Sales</label> <input class="form-control"
										type="text" name="salesQuantity" id="salesQuantity" />
								</div>
								
								
								
								<div class="form-group">
									<label>Sales Price</label> <input class="form-control"
										type="text" name="salesPrice" id="salesPrice"/>
								</div>
								
								
								<button type="submit" class="btn btn-success" onclick="return confirm('Are you sure Do you want to add this product?');">Add
									Product</button>
								<button type="reset" class="btn btn-danger">Reset</button>
							</form>
						</div>
					</div>
				</div>

			</div>

		</div>
	</div>
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
		(function(i, s, o, g, r, a, m) {
			i['GoogleAnalyticsObject'] = r;
			i[r] = i[r] || function() {
				(i[r].q = i[r].q || []).push(arguments)
			}, i[r].l = 1 * new Date();
			a = s.createElement(o), m = s.getElementsByTagName(o)[0];
			a.async = 1;
			a.src = g;
			m.parentNode.insertBefore(a, m)
		})
				(
						window,
						document,
						'script',
						'../../../../../../www.google-analytics.com/analytics.js',
						'ga');

		ga('create', 'UA-58127580-1', 'auto');
		ga('send', 'pageview');
	</script>
	<script>
	 $("#productName").change(function(){
    	 var product = $("#productName").val();
    	 $.ajax({
  			url : 'GetStockQuantityforSales',
  			data : {
  				productId : product
  			},
  			success : function(responseText) {
  				var dataTablesObj = $.parseJSON(responseText);
  				
  				 for(var i=0;i<=dataTablesObj.length;i++){
   					$("#stockQuantity").val(dataTablesObj[i].stockQuantity);
   					$("#proudctCategory").val(dataTablesObj[i].proudctCategory);
   					$("#vendorName").val(dataTablesObj[i].vdendorName);
   					
   					$("#stockQuantity").prop("readonly",true);
   					$("#proudctCategory").prop("readonly",true);
   					$("#vendorName").prop("readonly",true);
   				} 
  				
  			}
  		}); 
 
     });
	 
	 $("#salesQuantity").change(function(){
		 var totalQuantity = parseInt($("#stockQuantity").val(), 10);
		 var saleQuantity = parseInt($("#salesQuantity").val(), 10);

		    if (saleQuantity > totalQuantity) {
		        $("#salesQuantity").val(""); // clear input
		        $("#salesQuantity").focus();
		    }
	 });
	</script>
	<%
		} else {
	response.sendRedirect("admin-login.jsp");
	}
	%>
</body>
<script type="text/javascript">
	$(function() {
		$('#success').delay(3000).show().fadeOut('slow');
	});

	$(function() {
		$('#danger').delay(3000).show().fadeOut('slow');
	});

	$(function() {
		$('#warning').delay(3000).show().fadeOut('slow');
	});

	$(function() {
		$('#info').delay(3000).show().fadeOut('slow');
	});
</script>
</html>
