
import ru.product.Product;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


public class TestDBConnect {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, SQLException {
        Connection connection = null;
        try{
           connection = getConnection();

            Product p = new Product("Fanta",100,"Drinks","Cola");
            createNewProduct(connection, p);

        }finally {
            if (connection!=null)
                connection.close();
        }
    }

    private static void createNewProduct(Connection connection , Product p) throws SQLException {
        Statement st = connection.createStatement();
        st.executeUpdate("INSERT INTO products (name ,price) VALUES (\""+ p.getName() + "\"," + (int) p.getPrice() +");");
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
