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
public class UpdateCartDispatcher implements Dispatcherss{

    public String execute(HttpServletRequest request, HttpServletResponse response) {
     HttpSession session = request.getSession();
        String nextPage = "/jsp/cart.jsp";

        Map<String, CartItem> cart = (Map<String, CartItem>) session.getAttribute("cart");

        if (cart != null) {
            // Remove selected books from cart
            String[] booksToRemove = request.getParameterValues("remove");
            if (booksToRemove != null) {
                for (String bookToRemove : booksToRemove) {
                    cart.remove(bookToRemove);
                }
            }

            // Update quantities for the remaining books
            for (Map.Entry<String, CartItem> entry : cart.entrySet()) {
                String isbn = entry.getKey();
                CartItem item = entry.getValue();
                String qtyParam = request.getParameter(isbn);
                if (qtyParam != null) {
                    try {
                        int quantity = Integer.parseInt(qtyParam);
                        item.updateQuantity(quantity);
                    } catch (NumberFormatException e) {
 
                        item.updateQuantity(1); 
                    }
                }
            }
        }

         
      
        return nextPage;
    }


}
