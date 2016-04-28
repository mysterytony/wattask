<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Hello Form</title>
</head>
<body>
	<h1>Enter the following</h1>

	<form action="/wattask/hello/helloformsubmit.html" method="post">
		<p>Enter Name: <input type="text" name="name" /></p>
		<p>Enter Message: <input type="text" name="message" /></p>
		<input type="submit" value="Confirm & Submit"/>
	</form>
	
	<form>
	<input type="file" />
	</form>
</body>
</html>