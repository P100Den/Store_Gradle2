package catalog;

import ru.product.Product;
import service.ProductService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet(urlPatterns = "/catalog1")
public class Catalog extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private Map<String, Product> products = null;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        products = ProductService.getAll();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        request.setAttribute("catalog",products.get(products));
     request.getRequestDispatcher("/catalog.jsp").forward(request,response);
    }
}
