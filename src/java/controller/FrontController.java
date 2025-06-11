package controller;

import dispatchers.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.Tbooks;

/**
 * FrontController class to handle HTTP requests and responses.
 */
public class FrontController extends HttpServlet {

    private String defaultPage;
    private String errorPage;

    @PersistenceContext(unitName = "BookShopPU") // Make sure this matches your persistence.xml unit
    private EntityManager em;

    @Resource
    private javax.transaction.UserTransaction utx;

    private final HashMap<String, Dispatcherss> actions = new HashMap();

    /**
     * Initialize global variables.
     */
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        defaultPage = config.getInitParameter("defaultPage");
        errorPage = config.getInitParameter("errorPage");

        // Dispatcher mappings
        actions.put("add_to_cart", new AddToCartDispatcher());
        actions.put("view_cart", new ViewCartDispatcher());
        actions.put("checkout", new CheckoutDispatcher());
        actions.put("update_cart", new UpdateCartDispatcher());
        actions.put("continue", new ContinueDispatcher());
    }

    /**
     * Handle GET requests.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    /**
     * Handle POST requests.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        String requestedAction = request.getParameter("action");
        HttpSession session = request.getSession();
        String nextPage = errorPage;

        if (requestedAction == null) {
            try {
                List<Tbooks> books = em.createQuery("SELECT b FROM Tbooks b", Tbooks.class).getResultList();
                session.setAttribute("Books", books);
                nextPage = defaultPage;
            } catch (Exception ex) {
                request.setAttribute("result", ex.getMessage());
            } finally {
                dispatch(request, response, nextPage);
                return;
            }
        }

        Dispatcherss dispatcher = actions.get(requestedAction);
        if (dispatcher != null) {
            dispatcher.execute(request, response);
        } else {
            request.setAttribute("result", "Unknown action: " + requestedAction);
            dispatch(request, response, errorPage);
        }
    }

    /**
     * Forward request to a page.
     */
    private void dispatch(HttpServletRequest request, HttpServletResponse response, String page) throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }

    public String getServletInfo() {
        return "controller.FrontController Information";
    }

    public void persist(Object object) {
        try {
            utx.begin();
            em.persist(object);
            utx.commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            throw new RuntimeException(e);
        }
    }
}
