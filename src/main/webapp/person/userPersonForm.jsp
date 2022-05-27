<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>User Info</title>
</head>
<body>
<p>Name: <%= request.getParameter("name") %></p>
<p>Age: <%= request.getParameter("age") %></p>

<p><a href=".\userPersonSendForm.jsp">Register!</a></p>
<p><a href="./">Home page</a>  </p>
</body>
</html>
