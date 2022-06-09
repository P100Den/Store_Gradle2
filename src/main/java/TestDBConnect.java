
import ru.product.Product;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.Properties;


public class TestDBConnect {

    public static final Connection CONNECTION = getConnection();

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, SQLException {


        Connection connection = null;
        try{
           connection = CONNECTION;

            //Product p = new Product("Cola","Drink","Green","Pepsi",10);
            //  deleteProduct(connection,125);
           // updateProduct(connection,"Fanta",120);
            //deleteClients(connection,63);

        }finally {
            if (connection!=null)
                connection.close();
        }
        }

    private static void deleteClients(Connection connection ,  int authenticationNumber ) throws SQLException {
        Statement st = connection.createStatement();
        String query = ("DELETE FROM clients WHERE authenticationNumber =" + authenticationNumber );
        System.out.println(query);
        int row = st.executeUpdate(query);
        System.out.println(row);
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
