package ru.product;


import service.ProductService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

    @WebServlet(urlPatterns = "/product")
    public class ProductServlet extends HttpServlet {

        private static final long serialVersionUID = 1L;
        public static final String NAME = "name";
        public static final String CONTENT_TYPE = "text/html;charset=UTF-8";
        public static final String UTF_8 = "UTF-8";


        private Map<String, Product> products = null;

        @Override
        public void init(ServletConfig config) throws ServletException {
            super.init(config);

            products = ProductService.getAll();

        }

        protected void doGet(HttpServletRequest request, HttpServletResponse response)
                throws IOException {

            request.setCharacterEncoding(UTF_8);
            response.setContentType(CONTENT_TYPE);
            String productName = request.getParameter(NAME);
            Product foundProduct = products.get(productName);
            response.setHeader("Page-Name", "product info page");

            if (foundProduct != null) {
                response.getWriter().append("<html><head>\n" +
                        "</head><body><p> Name: " + foundProduct.getName() + " Category: " +
                        foundProduct.getCategory() + " Price: " + foundProduct.getPrice() + "</p><p><a href=\"./\">Return back</a></p></body></html>");
                response.getWriter().append("<p><a href=\"./addToBasket?name="  + foundProduct.getName() + "\">Add to basket</a></p>");
            } else {
                response.getWriter().append("<html><head>\n" +
                        "</head><body>Unknown product<p><a href=\"./\">Return back</a></p></body></html>");
            }

        }

        protected void doPost(HttpServletRequest request, HttpServletResponse response)
                throws IOException {

            String pName = request.getParameter("name");
            String pCategory = request.getParameter("category");
            String pPrice = request.getParameter("price");
            String pbrand = request.getParameter("brand");

            Product product = new Product(pName, Double.valueOf(pPrice), pCategory,pbrand);
            ProductService.add(product);

            response.getWriter().append("<html><head>\n" +
                    "</head><body>New product added!<p><a href=\"./\">Return back</a></p></body></html>");
        }

}
