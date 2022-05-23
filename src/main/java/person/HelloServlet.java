package person;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/hello")
public class HelloServlet extends HttpServlet {

    private Map<String, String> client = new HashMap<>();

    @Override
    public void init() throws ServletException {
        client.put("145","Bob Walker");
        client.put("452", "Jon Smith");
        client.put("478","Sam Johansson");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if (id != null){
            req.setAttribute("FIO",client.get(id));
            req.setAttribute("FIO",client.get(id));

        }
       req.setAttribute("age",35);

        req.getRequestDispatcher("/basic.jsp").forward(req, resp);
    }
}
