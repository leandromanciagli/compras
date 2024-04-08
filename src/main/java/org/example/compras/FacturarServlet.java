package org.example.compras;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Hashtable;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.compras.model.Product;


public class FacturarServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null) {
            response.sendRedirect("index.jsp");
        } else {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<html>");
            out.println("<style>table, th, td { border:1px solid black; min-width:200px; }</style>");
            out.println("<head><title>Carrito de compras</title></head>");
            out.println("<body>");
            out.println("<h1>Carrito de compras</h1>");
            out.println("<table style='width:30%'>");
            out.println("<tr><th>Nombre</th><th>Precio unitario</th><th>Cantidad a comprar</th><th>Precio total</th></tr>");
            Hashtable<String, Product> products = (Hashtable<String, Product>) getServletContext().getAttribute("products");
            double totalGeneral = 0.0;
            session = request.getSession();
            Hashtable<String, Product> userProducts = (Hashtable<String, Product>) session.getAttribute("userProducts");
            Enumeration<String> parameters = request.getParameterNames();
            while (parameters.hasMoreElements()) {
                String productName = parameters.nextElement();
                double productPrice = products.get(productName).getPrice();
                int productAmount = Integer.parseInt(request.getParameter(productName));
                if (productAmount > 0) {
                    double productTotalPrice = productPrice * productAmount;
                    totalGeneral = totalGeneral + productTotalPrice;
                    if (userProducts.containsKey(productName)) {
                        userProducts.get(productName).setAmount(productAmount);
                        userProducts.get(productName).setTotalPrice(productTotalPrice);
                    } else {
                        Product product = new Product(productName, productPrice, productAmount, productTotalPrice);
                        userProducts.put(productName, product);
                    }
                    out.println("<tr>" +
                            "<td>" + userProducts.get(productName).getName() + "</td>" +
                            "<td><center>$ " + userProducts.get(productName).getPrice() + "</center></td>" +
                            "<td><center>" + userProducts.get(productName).getAmount() + " U.</center></td>" +
                            "<td><center>$ " + userProducts.get(productName).getTotalPrice() + "</center></td>" +
                            "</tr>");
                } else if (userProducts.containsKey(productName)) {
                    userProducts.remove(productName);
                    products.get(productName).setAmount(0);
                }

            }
            out.println("<tr><td>Total general</td><td></td><td></td><td><center>$ " + totalGeneral + "</center></td></tr>");
            out.println("</table>");
            out.println("<a href='productos'>Seguir comprando</a>");
            out.println("<a href='terminarSesion'>Salir</a>");
            out.println("</body>");
            out.println("</html>");
            out.close();
        }
    }
}
