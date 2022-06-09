package clients;

import ru.product.Product;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

@WebServlet(urlPatterns = "/clientsAdd")
public class ClientsAdd  extends HttpServlet {

    public static final Connection CONNECTION = getConnection();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Connection connection = null;
        try {
            connection = CONNECTION;
            String cName = request.getParameter("name");
            String cEmail = request.getParameter("email");
            String cPassword = request.getParameter("password");
            String cAuthenticationNumber = request.getParameter("authenticationNumber");

            Statement st = connection.createStatement();

            Clients c = new Clients(cName,cEmail,cPassword,Double.valueOf(cAuthenticationNumber));

            st.executeUpdate("INSERT INTO clients (name, email, password, authenticationNumber) VALUES ((\'" + c.getName() + "\'),(\'"+ c.getEmail() + "\'),(\'"+ c.getPassword() +"\'),(\'"+ c.getAuthenticationNumber() + "\'))");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        response.getWriter().append("<html><head>\n" +
                "</head><body>New clients added!<p><a href=\"./\">Return back</a></p></body></html>");
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
