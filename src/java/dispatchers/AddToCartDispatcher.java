/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dispatchers;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
//import model.Book;
import model.Tbooks;
import model.CartItem;

/**
 *
 * @author Cool_
 */
public class AddToCartDispatcher implements Dispatcherss {

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String nextPage = "/jsp/titles.jsp";

        Map<String, CartItem> cart = (Map<String, CartItem>) session.getAttribute("cart");
        String[] selectedBooks = request.getParameterValues("add");

        if (selectedBooks == null || selectedBooks.length == 0) {
            dispatch(request, response, nextPage);
            return null;
        }

        if (cart == null) {
            cart = new HashMap();
            for (String isbn : selectedBooks) {
                int quantity = Integer.parseInt(request.getParameter(isbn));
                Tbooks book = getBookFromList(isbn, session);
                CartItem item = new CartItem(book);
                item.setQuantity(quantity);
                cart.put(isbn, item);
            }
            session.setAttribute("cart", cart);
        } else {
            for (String isbn : selectedBooks) {
                int quantity = Integer.parseInt(request.getParameter(isbn));
                if (cart.containsKey(isbn)) {
                    cart.get(isbn).setQuantity(quantity);
                } else {
                    Tbooks book = getBookFromList(isbn, session);
                    CartItem item = new CartItem(book);
                    item.setQuantity(quantity);
                    cart.put(isbn, item);
                }
            }
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


    private Tbooks getBookFromList(String isbn, HttpSession session) {
        List<Tbooks> books = (List<Tbooks>) session.getAttribute("Books");
        if (books != null) {
            for (Tbooks book : books) {
                if (isbn.equals(book.getIsbn())) {
                    return book;
                }
            }
        }
        return null;
    }
}