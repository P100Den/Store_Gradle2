<%@ page import="service.ProductService, ru.product.Product, java.util.Map" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<body>
<%
  ProductService ps = (ProductService)application.getAttribute("productService");

   Map<String, Product> all = ps.getAll();

  Product foundP =  all.get(request.getParameter("name"));

  out.println(foundP);

<jsp:include page="returnBack.jsp" />



</body>
</html>