<jsp:useBean id="dayAndFilmBean" class="cinematicket.DayAndFilmBean" scope="request" />
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Cinema Ticket Application</title>
</head>
<body>
	<h1>Welcome to the Cinema Ticket Application!</h1>
	<h1>Here you can order a movie ticket.</h1>
	<table width=100% height=100% border="1" cellpadding="10">
		<tr>
			<td width=100% valign="top">
    <br />
	Select one or more options:
	<hr>
    <br />
	<form method="post" name="findSessionForm" action="findSessions">
		Select day: <select name="day" >
			<option value="">Select day</option>
			<option value="Monday">Monday</option>
			<option value="Tuesday">Tueday</option>
			<option value="Wednesday">Wednesday</option>
			<option value="Thursday">Thursday</option>
			<option value="Friday">Friday</option>
			<option value="Saturday">Saturday</option>
			<option value="Sunday">Sunday</option>
		</select>&nbsp;&nbsp;
		Select film: <select name="film" >
			<option value="">Select film</option>
			<option value="Pulp Fiction">Pulp Fiction</option>
			<option value="Forrest Gump">Forrest Gump</option>
			<option value="The Shawshank Redemption">The Shawshank Redemption</option>
			<option value="Fight Club">Fight Club</option>
		</select>&nbsp;&nbsp;
		Select time: <select name="time" >
			<option value="">Select time</option>
			<option value="9:00">9:00</option>
			<option value="10:00">10:00</option>
			<option value="11:00">11:00</option>
			<option value="12:00">12:00</option>
			<option value="13:00">13:00</option>
			<option value="14:00">14:00</option>
			<option value="15:00">15:00</option>
			<option value="16:00">16:00</option>
			<option value="17:00">17:00</option>
			<option value="18:00">18:00</option>
			<option value="19:00">19:00</option>
			<option value="20:00">20:00</option>
			<option value="21:00">21:00</option>
			<option value="22:00">22:00</option>
		</select>&nbsp;&nbsp;
		<input type="submit" name="findButton" value="GO" />
	</form>
		<br />
	<hr>
    <br>
<jsp:getProperty name="dayAndFilmBean" property="info" />
    <br>
	<form method="get" name="orderForm" action="makeOrder">
		<input type="submit" name="goOrderButton" value="Make Order" />
		 Proceed with new oder.	
	</form>
	&nbsp;&nbsp;
	<form method="post" name="orderForm" action="checkOrder">
		<input type="submit" name="checkOrderButton" value="Check Order" />
		Check or cancel oder. Enter your order ID: 
		<input type="text" size="5" name="orderId"/>
	</form>
			</td>
	</tr>
	</table>
</body>
</html>
