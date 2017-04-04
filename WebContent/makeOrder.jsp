<jsp:useBean id="orderManagerBean" class="cinematicket.OrderManagerBean" scope="session" />
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Application Order Manager</title>
</head>
<body>
	<h1>You are in the Order Manager.</h1>
	<form action="index.jsp" method="get">
   		<input type="submit" name="goHome"value="HOME" />
	</form>
    <br/>
	<hr>
	<table width=100% height=100% border="1" cellpadding="10">
		<tr>
			<td width=50% valign="top">
				Current Session:
    			<br/>
				<jsp:getProperty name="orderManagerBean" property="sessionInfo" />
			    <br/>
				<hr>
			    <br/>
				<jsp:getProperty name="orderManagerBean" property="sessionSeats" />
			    <br/>
				<hr>
				<h1>New Order</h1>
   			 <br/>
				Choose ticket seat:
			    <br/>
   			 <br/>
				<form action="makeOrder" method="post">
   			     Row: <input type="text" size="2" name="row"/>
			        &nbsp;&nbsp;
   			     Seat: <input type="text" size="2" name="number"/>
					&nbsp;&nbsp;
   			     <input type="submit" name="addTicket" value="Add Ticket" />
					&nbsp;&nbsp;
   			     <input type="submit" name="removeTicket"value="Remove Ticket" />
				</form>
				<form action="makeOrder" method="post">
   			   		<input type="submit" name="createOrder"value="Create Order" />
				</form>
				<br/>
			</td>
			<td width=50% valign="top">
				<h1>Your Tickets</h1>
				Total price: <jsp:getProperty name="orderManagerBean" property="totalPrice" />
			    <br/><br/>
				<jsp:getProperty name="orderManagerBean" property="printTicketList" />
			</td>
		</tr>
	</table>
</body>
</html>
