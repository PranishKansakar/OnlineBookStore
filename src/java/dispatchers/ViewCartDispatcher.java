/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dispatchers;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.CartItem;

/**
 *
 * @author Cool_
 */
public class ViewCartDispatcher implements Dispatcherss {

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String nextPage = "/jsp/cart.jsp";

        Map<String, CartItem> cart = (Map<String, CartItem>) session.getAttribute("cart");
        if (cart == null) {
            // If no cart in session, redirect to titles page
            nextPage = "/jsp/titles.jsp";
        }

           dispatch(request, response, nextPage);
        return null;
    }

    private void dispatch(HttpServletRequest request, HttpServletResponse response, String nextPage) {
        try {
            RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
            dispatcher.forward(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(AddToCartDispatcher.class.getName()).log(Level.SEVERE, "ServletException during dispatch", ex);
        } catch (IOException ex) {
            Logger.getLogger(AddToCartDispatcher.class.getName()).log(Level.SEVERE, "IOException during dispatch", ex);
        }
    }
}
