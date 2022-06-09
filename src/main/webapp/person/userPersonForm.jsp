<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP Application</title>
</head>
<body>
 <h2>Your entered data:</h2>
    <p>Name: <%= request.getParameter("name") %></p>
    <p>Password: <%= request.getParameter("password") %></p>


<jsp:include page="returnBack.jsp" />

</body>
</html>
