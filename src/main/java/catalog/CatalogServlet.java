package catalog;

import help.ServletHelper;
import ru.product.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@WebServlet(urlPatterns = "/catalogServlet")
public class CatalogServlet extends HttpServlet {

    public static final Connection CONNECTION = getConnection();
    public static final String TABLE_BEGIN = "<table style=\" border: 1px solid white;   border-collapse: collapse;\">\n" +
            "  <tr>\n" +
            "    <th>Name</th>\n" +
            "    <th>Category</th> \n" +
            "    <th>Colour</th> \n" +
            "    <th>Brand</th>\n" +
            "    <th>Price</th>\n" +
            "    <th>Barcode</th>\n" +
            "    <th>Remove</th>\n" +
            "  </tr>";
    public static final String TABLE_END = "</table>";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> selectProducts = new ArrayList<>();
        Connection connection = null;
        try {
            connection = CONNECTION;
            Statement st = connection.createStatement();
            ResultSet resultSet = st.executeQuery("SELECT NAME, Category,Colour,Brand,Price,Barcode FROM products");

            while (resultSet.next()){
                String productName = resultSet.getString(1);
                String productCategory = resultSet.getString(2);
                String productColour = resultSet.getString(3);
                String productBrand = resultSet.getString(4);
                int productPrice = resultSet.getInt(5);
                int productBarcode = resultSet.getInt(6);
                selectProducts.add(new Product(productName,productCategory,productColour,productBrand,productPrice,productBarcode));

               if (selectProducts!=null) {
                    response.getWriter().append(TABLE_BEGIN);
                    response.getWriter().append("<tr>\n" +
                                "    <td>" + productName + "</td>\n" +
                                "    <td>" + productCategory + "</td> \n" +
                                "    <td>" + productColour + "</td> \n" +
                                "    <td>" + productBrand + "</td>\n" +
                                "    <td>" + productPrice + "</td>\n" +
                                "    <td>" + productBarcode + "</td>\n" +
                                "<td><a href= \"./addToBasket?name="  + productName + "\">Add to basket</a></td>\n" +
                                "  </tr>");
                    response.getWriter().append(TABLE_END);
                }else {
                    response.getWriter().append("<p>Our catalog is Empty!</p>");
                }
                ServletHelper.populateHtmlEnd(response);
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

        try {Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();

            Connection connection =

                    DriverManager.getConnection(url, username, password);

            System.out.println("Connection successful!");

            return connection;

        } catch (Exception ex){
            System.out.println("Connection failed...");
            System.out.println(ex);
        }
        return null;
    }



    private static Properties getProperties() {
        Properties props = new Properties();

        try(InputStream in = Files.newInputStream(Paths.get("src/main/resources/database.properties"))){
            props.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return props;
    }
}




















































