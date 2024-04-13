package org.example.compras.misservlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Hashtable;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.compras.model.Product;


public class ProductServlet extends HttpServlet {

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Hashtable<String, Product> products = (Hashtable<String, Product>)getServletContext().getAttribute("products");
        HttpSession session = request.getSession(false);
        if (session == null) {
            response.sendRedirect("index.jsp");
        } else {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<html>");
            out.println("<style>table, th, td { border:1px solid black; min-width:200px; }</style>");
            out.println("<head><title>Comprar golosinas</title></head>");
            out.println("<body>");
            out.println("<h1>Seleccione los productos</h1>");
            out.println("<form action='facturar' method='GET'>");
            out.println("<table style='width:30%'>");
            out.println("<tr><th>Nombre</th><th>Precio unitario</th><th>Cantidad a comprar</th></tr>");
            session = request.getSession();
            Hashtable<String, Product> userProducts = (Hashtable<String, Product>) session.getAttribute("userProducts");
            for (String i : products.keySet()) {
                if (userProducts.containsKey(i)) {
                    products.get(i).setAmount(userProducts.get(i).getAmount());
                }
                out.println("<tr>" +
                        "<td>" + products.get(i).getName() + "</td>" +
                        "<td><center>$ " + products.get(i).getPrice() + "</center></td>" +
                        "<td><center><input type='number' name='" + i + "' value='" + products.get(i).getAmount() + "' /></center></td>" +
                        "</tr>");
            }
            out.println("</table>");
            out.println("<input type='submit' value='Facturar' />");
            out.println("</form>");
            out.println("<a href='terminarSesion'>Salir</a>");
            out.println("</body>");
            out.println("</html>");
            out.close();
        }
    }
}
