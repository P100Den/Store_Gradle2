package catalog;

import ru.product.Product;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.util.ArrayList;
import java.util.List;
@WebServlet(urlPatterns = "/catalog")
public class ProductController extends HttpServlet {

    private final Product Kefir = new Product("Kefir", 100, "DiaryProduct", "11");
    private final Product Milk = new Product("Milk", 200, "DiaryProduct", "12");
    private final Product Yougrt = new Product("Yougrt", 30, "DiaryProduct", "14");
    private final Product Banan = new Product("Banan", 90, "Fruits", "15");
    private final Product Apple = new Product("Apple", 100, "Fruits", "20");

    private List<Product> product = List.of(Kefir, Milk, Yougrt, Banan, Apple);
    public List<Product> getProduct() {
        return product;
    }
    private List<Integer>code = new ArrayList<>();
    private List<Product> codeProduct = new ArrayList<Product>();


    public int giveNumberId(String openTheCatalog, String idnumber) {
        int newApassNumber = code.size();
        code.add(newApassNumber);
        codeProduct.add(newApassNumber, getProductId(idnumber));

        return newApassNumber;
    }

    private Product getProductId(String idnumber) {
        for (Product currentListOfProducts:product) {
            if (currentListOfProducts!= null && currentListOfProducts.getId().equals(idnumber))
                return currentListOfProducts;

        }
        return null;
    }
}