package ru.product;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.Properties;

@WebServlet(urlPatterns = "/productSearch")
public class ProductSearch extends HttpServlet {
    public static final Connection CONNECTION = getConnection();


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        Connection connection = null;

        try {
            connection = CONNECTION;

            String pName = request.getParameter("name");
            String pCategory = request.getParameter("category");
            String pColour = request.getParameter("colour");
            String pBrand = request.getParameter("brand");
            String pPrice = request.getParameter("price");
            String pBarcode = request.getParameter("barcode");

            Statement st = connection.createStatement();

            Product p = new Product(pName, pCategory, pColour, pBrand, Double.valueOf(pPrice),Double.valueOf(pBarcode));

            st.executeUpdate("INSERT INTO products (Name, Category, Colour, Brand, Price, Barcode) VALUES ((\'" + p.getName() + "\'),(\'"+ p.getCategory() + "\'),(\'"+ p.getColour() +"\'),(\'"+ p.getBrand() + "\'),(\'"+ p.getPrice()+"\'),(\'"+ p.getBarcode() + "\'))");

            if (p != null) {
                response.getWriter().append("<html><head>\n" +
                        "</head><body><p> Name: " + p.getName() + " Category: " +
                        p.getCategory() + " Colour: " +
                        p.getColour() + " Brand: " +
                        p.getBrand() + " Price: " + p.getPrice() + " Barcode: " + p.getBarcode() +"</p><p><a href=\"./\">Return back</a></p></body></html>");
                response.getWriter().append("<p><a href=\"./addToBasket?name=" + p.getName() + "\">Add to basket</a></p>");
            } else {
                response.getWriter().append("<html><head>\n" +
                        "</head><body>Unknown product<p><a href=\"./\">Return back</a></p></body></html>");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }





    private static Connection getConnection() {

        Properties props = getProperties();

        String url = props.getProperty("url");

        String username = props.getProperty("username");

        String password = props.getProperty("password");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();

            Connection connection =

                    DriverManager.getConnection(url, username, password);

            System.out.println("Connection successful!");

            return connection;

        } catch (Exception ex) {
            System.out.println("Connection failed...");
            System.out.println(ex);
        }
        return null;
    }

    private static Properties getProperties() {
        Properties props = new Properties();

        try (InputStream in = Files.newInputStream(Paths.get("src/main/resources/database.properties"))) {
            props.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return props;
    }
}


