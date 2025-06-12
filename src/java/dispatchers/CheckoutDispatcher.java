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
import javax.servlet.http.HttpSession;

/**
 *
 * @author Cool_
 */
public class CheckoutDispatcher implements Dispatcherss {

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String nextPage = "/jsp/checkout.jsp";
      
        return nextPage;
    }
}