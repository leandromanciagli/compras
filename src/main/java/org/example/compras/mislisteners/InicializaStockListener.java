package org.example.compras.mislisteners;

import jakarta.servlet.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Hashtable;
import org.example.compras.model.Product;

public class InicializaStockListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        String stock = context.getInitParameter("stock");
        Hashtable<String, Product> products = new Hashtable<>();
        BufferedReader buffer = null;
        try {
            InputStreamReader inputStream = new InputStreamReader(context.getResourceAsStream(stock));
            buffer = new BufferedReader(inputStream);
            int posComa = 0;
            String line = buffer.readLine();
            while (line != null) {
                posComa = line.indexOf(",");
                String productName = line.substring(0, posComa);
                String productPrice = line.substring(posComa + 1);
                Product product = new Product(productName, Double.parseDouble(productPrice), 0, 0);
                products.put(productName, product);
                line = buffer.readLine();
            }
            context.setAttribute("products", products);
            context.log("Lista de Productos creada.");
        } catch(Exception e) {
            context.log("Ocurrió una excepción... ", e);
        } finally {
            if(buffer != null) {
                try {
                    buffer.close();
                } catch (Exception e) {}
            }
        }
    }
}