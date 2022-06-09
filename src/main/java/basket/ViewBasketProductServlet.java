package basket;

import help.ServletHelper;
import ru.product.Product;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/viewBasket")
public class ViewBasketProductServlet extends HttpServlet {

    public static final String TABLE_BEGIN = "<table style=\" border: 1px solid white;   border-collapse: collapse;\">\n" +
            "  <tr>\n" +
            "    <th>Name</th>\n" +
            "    <th>Category</th> \n" +
            "    <th>Colour</th> \n" +
            "    <th>Brand</th>\n" +
            "    <th>Price</th>\n" +
            "    <th>Remove</th>\n" +
            "  </tr>";

    public static final String TABLE_END = "</table>";

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        ServletHelper.populateHtmlBegin(response);

        HttpSession httpSession = request.getSession();

        List<Product> myBasket = (List<Product>)httpSession.getAttribute("myBasket");

        if (myBasket!=null && myBasket.size() != 0) {
            response.getWriter().append("<p>View basket:</p>");

            response.getWriter().append(TABLE_BEGIN);

            for (int i=0; i < myBasket.size(); i++) {
                Product p = myBasket.get(i);
                response.getWriter().append("<tr>\n" +
                        "    <td>" + p.getName() + "</td>\n" +
                        "    <td>" + p.getCategory() + "</td> \n" +
                        "    <td>" + p.getColour() + "</td> \n" +
                        "    <td>" + p.getBrand() + "</td>\n" +
                        "    <td>" + p.getPrice() + "</td>\n" +
                        "    <td><a href= \"./remove?id=" + i + "\">Remove</a></td>\n" +
                        "  </tr>");
            }

            response.getWriter().append(TABLE_END);

        } else {
            response.getWriter().append("<p>Our basket is Empty!</p>");
        }

        ServletHelper.populateHtmlEnd(response);
    }
}
