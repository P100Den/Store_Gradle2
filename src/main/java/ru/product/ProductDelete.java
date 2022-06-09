package ru.product;

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

@WebServlet(urlPatterns = "/productDelete")
public class ProductDelete extends HttpServlet {
    public static final Connection CONNECTION = getConnection();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        Connection connection = null;
        try {
            connection = CONNECTION;
            String pName = request.getParameter("name");
            String pCategory = request.getParameter("category");
            String pColour = request.getParameter("colour");
            String pBrand = request.getParameter("brand");
            String pPrice = request.getParameter("price");
            String pBarcode= request.getParameter("barcode");

            Statement st = connection.createStatement();

            st.executeUpdate ("DELETE FROM products WHERE Barcode =" + pBarcode );

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        response.getWriter().append("<html><head>\n" +
                "</head><body> Product delete!<p><a href=\"./\">Return back</a></p></body></html>");
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
