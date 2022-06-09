package clients;

import help.ServletHelper;
import ru.product.Product;

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

@WebServlet(urlPatterns = "/registration")
public class UserRegistration extends HttpServlet {

    public static final Connection CONNECTION = getConnection();
    public static final String TABLE_BEGIN = "<table style=\" border: 1px solid white;   border-collapse: collapse;\">\n" +
            "  <tr>\n" +
            "    <th>Name</th>\n" +
            "    <th>Email</th> \n" +
            "    <th>Password</th> \n" +
            "    <th>AuthenticationNumber</th>\n" +
            "  </tr>";
    public static final String TABLE_END = "</table>";

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        Connection connection = null;

        try {
            connection = CONNECTION;

            String cName = request.getParameter("name");
            String cEmail = request.getParameter("email");
            String cPassword = request.getParameter("password");
            String cAuthenticationNumber = request.getParameter("authenticationNumber");

            Statement st = connection.createStatement();

            Clients c = new Clients(cName, cEmail, cPassword, Double.valueOf(cAuthenticationNumber));

            st.executeUpdate("INSERT INTO clients (Name, Email, Password, AuthenticationNumber) VALUES ((\'" + c.getName() + "\'),(\'"+ c.getEmail() + "\'),(\'"+ c.getPassword() +"\'),(\'"+c.getAuthenticationNumber() + "\'))");

            if (c!=null) {
                response.getWriter().append(TABLE_BEGIN);
                response.getWriter().append("<tr>\n" +
                        "    <td>" + cName + "</td>\n" +
                        "    <td>" + cEmail + "</td> \n" +
                        "    <td>" + cPassword + "</td> \n" +
                        "    <td>" + cAuthenticationNumber + "</td>\n" +
                        "  </tr>");
                response.getWriter().append(TABLE_END);

            }else {
            response.getWriter().append("<p>A mistake was made!</p>");
        }
            ServletHelper.populateHtmlBegin(response);
            ServletHelper.populateHtmlEnd(response);

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
