package basket;

import ru.product.Product;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/remove")
public class RemoveProductFromBasketServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Integer id =  Integer.valueOf(request.getParameter("id"));

        Object myBasket = request.getSession().getAttribute("myBasket");

        if(myBasket!=null) {
            List<Product> basket = (List<Product>) myBasket;
            basket.remove((int)id);
        }

        // TODO: Refactor!
        response.sendRedirect("http://localhost:8082/viewBasket");
    }
}

