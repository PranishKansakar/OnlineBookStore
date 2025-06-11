/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dispatchers;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Cool_
 */
public class CheckoutDispatcher implements Dispatcherss {

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String nextPage = "/jsp/checkout.jsp";
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
