package org.example.compras;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.compras.model.Product;

import java.util.Enumeration;
import java.util.Hashtable;


public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private Hashtable<String, String> usersAndPasswords;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        usersAndPasswords = new Hashtable<>();
        Enumeration<String> initParameterNames = config.getInitParameterNames();
        while (initParameterNames.hasMoreElements()) {
            String paramName = initParameterNames.nextElement();
            String paramValue = config.getInitParameter(paramName);
            usersAndPasswords.put(paramName, paramValue);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name").trim();
        String postalCode = request.getParameter("postalCode").trim();
        String user = request.getParameter("user").trim();
        String password = request.getParameter("password").trim();
        String storedPassword = usersAndPasswords.get(user);
        if (storedPassword != null && storedPassword.equals(password)) {
            HttpSession session = request.getSession();
            session.setAttribute("name", name);
            session.setAttribute("postalCode", postalCode);
            session.setAttribute("user", user);
            session.setAttribute("password", password);
            session.setAttribute("products", new Hashtable<String, Product>());
            // Se redirecciona al servlet de productos
            response.sendRedirect("/productos");
        } else {
            String responseTitle = "¡Upss!";
            String responseMessage = "El usuario o contraseña ingesados son inválidos. Por favor, intente nuevamente <a href='/'>aquí</a>";
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<HTML>");
            out.println("<HEAD>");
            out.println("<TITLE>" + responseTitle + "</TITLE>");
            out.println("</HEAD>");
            out.println("<BODY>");
            out.println("<H1>" + responseTitle + "</H1>");
            out.println("<P>" + responseMessage + "</P>");
            out.println("</BODY>");
            out.println("</HTML>");
            out.close();
        }
    }
}
