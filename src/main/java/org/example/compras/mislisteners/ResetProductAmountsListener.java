package org.example.compras.mislisteners;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.example.compras.model.Product;

import java.util.Hashtable;

public class ResetProductAmountsListener implements HttpSessionListener {

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        ServletContext context = se.getSession().getServletContext();
        Hashtable<String, Product> products = (Hashtable<String, Product>)context.getAttribute("products");
        for (String i : products.keySet()) {
            products.get(i).setAmount(0);
        }
    }

}