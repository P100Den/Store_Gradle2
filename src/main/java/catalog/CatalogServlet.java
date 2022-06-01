package catalog;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(urlPatterns = "/catalog")
public class CatalogServlet extends HttpServlet {

    public static final String URL = "url";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String COM_MYSQL_CJ_JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    Connection connection = null;
    @Override
    public void init() throws ServletException {
        super.init();
            try {
                Class.forName(COM_MYSQL_CJ_JDBC_DRIVER);
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }

        }
        public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            try {
                response.setContentType("text/html;charset=UTF-8");
                PrintWriter out = response.getWriter();

                out.println("<html><body>");
                out.println("<h3>Catalog</h3>");
                out.println("<table border=1><tr>" + "<td><b>S.No</b></td>" + "<td><b>Name</b></td>"
                        + "<td><b>Category</b></td>" + "<td><b>Brand</b></td>" + "<td><b>Price</b></td></tr>");

                Statement stmt = connection.createStatement();
                ResultSet resultSet = stmt.executeQuery("SELECT NAME, Category,Brand,Price FROM products");

                while (resultSet.next()) {
                    String name = resultSet.getString("Name");
                    String category = resultSet.getString("Category");
                    String brand = resultSet.getString("Brand");
                    int price = resultSet.getInt("Price");

                    out.println("<tr>" + "<td>" + name + "</td>" + "<td>" + category + "</td>" + "<td>" + brand + "</td>"
                            + "<td>" + price
                            + "</td></tr>");

                }
                out.println("</table></body></html>");
                resultSet.close();
                stmt.close();
                out.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        public void destroy() {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }



















