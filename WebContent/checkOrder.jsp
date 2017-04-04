<jsp:useBean id="orderManagerBean" class="cinematicket.OrderManagerBean" scope="session" />
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Check Order Manager</title>
	</head>
<body>
	<h1>Check or cancel your order.</h1>
	<form action="index.jsp" method="get">
   		<input type="submit" name="goHome"value="HOME" />
	</form>
    <br/>
	<hr>
	<table width=100% height=100% border="1" cellpadding="10">
		<tr>
			<td width=50% valign="top">
				<h1>Order Info:</h1>
				<jsp:getProperty name="orderManagerBean" property="orderInfo" />
    			<br/>
				<hr>
    			<br/>
				<form action="checkOrder" method="post">
   					<input type="submit" name="cancelOrder"value="Cancel Order" />
				</form>
			</td>
			<td width=50% valign="top">
				<h1>Tickets:</h1>
				<jsp:getProperty name="orderManagerBean" property="printTicketList" />
	 			<br/>
				<hr>
    			<br/>

			</td>
			</td>
		</tr>
	</table>
</body>
</html>
