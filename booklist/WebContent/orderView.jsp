<%@ page import="java.sql.*" contentType="text/html; charset=EUC_KR" %>
<%
	Class.forName("oracle.jdbc.driver.OracleDriver");
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	Connection dbConn = DriverManager.getConnection(url, "c##madang", "c##madang");
	Statement stmt = dbConn.createStatement();
	String custid = request.getParameter("custid");
	String SQL = "SELECT DISTINCT c.name AS name1"+
				" FROM customer C, ORDERS O, BOOK B"+
				" WHERE c.custid = o.custid AND b.bookid = o.bookid"+
				" and c.custid != "+custid+
				" AND b.publisher in (SELECT DISTINCT b.publisher"+
									" FROM customer C, ORDERS O, BOOK B"+
									" WHERE c.custid = o.custid AND b.bookid = o.bookid AND C.custid = "+custid+")";
	System.out.println(SQL);
				
	ResultSet myResultSet = stmt.executeQuery(SQL);
%>
<html>

<head>
<meta http-equiv="content-type" content="text/html; charset=euc-kr">
<title> ** SAME PUBLISHER CUSTOMER VIEW **</title>
</head>


<body bgcolor = "white" text = "black" link = "blue" vlink = "purple" alink = "red">
<table border = "1" cellspacing = "0" width = "400" bordercolor = "#9AD2F7"
			bordercolordark = "white" bordercolorlight = "#B9E0FA">
<%
if(myResultSet != null) {
	while(myResultSet.next()){
		String W_NAME = myResultSet.getString("name1");
%>
			<tr>
				<td width="50" height = "20">
					<p><span style="font-size: 9pt;">
					<%=W_NAME%></span></p>
				</td>
			</tr>
<% 
	}
	} else {
		
	}
	stmt.close();
	dbConn.close();
%>
</table>

<table cellpadding="0" cellspacing="0" width = "400" height="23">
	<tr>
		<td width="150">
			<p align="right"><span style="font-size: 9pt">
			<a href="customerList.jsp?">
			<font color="black">목록</font></a></span></p>
		</td>
	</tr>
</table>

</body>

</html>







