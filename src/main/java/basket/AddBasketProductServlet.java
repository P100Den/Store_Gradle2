package basket;

import help.ServletHelper;
import ru.product.Product;
import service.ProductService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@WebServlet(urlPatterns = "/addToBasket")
public class AddBasketProductServlet extends HttpServlet {


    private static final long serialVersionUID = 1L;
    public static final String NAME = "name";

    private Map<String, Product> products = null;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        products = ProductService.getAll();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String pName = request.getParameter(NAME);

        Product selectProduct = products.get(pName);

        Object myBasket = request.getSession().getAttribute("myBasket");
        if (myBasket !=null) {
            List<Product> list = (List<Product>)myBasket;
            list.add(selectProduct);
        } else {
            List<Product> list = new ArrayList();
            list.add(selectProduct);
            request.getSession().setAttribute("myBasket", list);
        }

        ServletHelper.populateHtmlBegin(response);
        response.getWriter().append("<p>Product Added to basket!<p>");
        ServletHelper.populateHtmlEnd(response);
    }

}
