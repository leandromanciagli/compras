package org.example.compras;

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


public class Facturar extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        out.println("<html>");
        out.println("<style>table, th, td { border:1px solid black; }</style>");
        out.println("<head><title>Carrito de compras</title></head>");
        out.println("<body>");
        out.println("<h1>Carrito de compras</h1>");
        out.println("<table style='width:30%'>");
        out.println("<tr><th>Nombre</th><th>Cantidad a comprar</th><th>Precio total</th></tr>");
        HttpSession session = request.getSession();
        Hashtable<String, Product> userProducts = (Hashtable<String, Product>)session.getAttribute("products");
        double totalGeneral = 0.0;
        Enumeration<String> parameters = request.getParameterNames();
        while (parameters.hasMoreElements()) {
            String productName = parameters.nextElement();
            int productAmount = Integer.parseInt(request.getParameter(productName));
            if (productAmount > 0) {
                if (userProducts.containsKey(productName)) {
                    userProducts.get(productName).setAmount(Integer.parseInt(request.getParameter(productName)));
                } else {
                    Product product = new Product(productName, )
                }
            }
            // aca hay que ver como contabilizar el total por producto
            out.println("<tr>" +
                    "<td>" + userProducts.get(i).getName() + "</td>" +
                    "<td><center>" + userProducts.get(i).getAmount() + "</center></td>" +
                    "<td><center><input type='text' name='"+i+"Amount' value='"+userProducts.get(i).getAmount()+"' /></center></td>" +
                    "</tr>");
        }

        out.println("<tr><td>Total general</td><td>"+ totalGeneral +"</td></tr>");
        out.println("</table>");
        out.println("<a href='http://localhost:8080/compras_war_exploded/productos'>Seguir comprando</a>");
        out.println("<a href='http://localhost:8080/compras_war_exploded/terminarSesion'>Salir</a>");
        out.println("</body>");
        out.println("</html>");
        out.close();
    }
}
