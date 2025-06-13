/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dispatchers;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Tbooks;

/**
 *
 * @author Cool_
 */
public class TitlesDispatcher implements Dispatcherss{

    //  @PersistenceContext(unitName = "BookShopPU") 
    private EntityManager em;

    private EntityManagerFactory emf;
    @Resource
    private javax.transaction.UserTransaction utx;

    public String execute(HttpServletRequest request, HttpServletResponse response) {
     
        HttpSession session = request.getSession();
        emf = Persistence.createEntityManagerFactory("BookShopPU");
        em =emf.createEntityManager();
        String nextPage = "/jsp/error.jsp";
        try {
                List<Tbooks> books = em.createQuery("SELECT b FROM Tbooks b", Tbooks.class).getResultList();
                System.out.print("In Try ");
                session.setAttribute("Books", books);
                nextPage = "/jsp/titles.jsp";
            } catch (Exception ex) {
                request.setAttribute("result", ex.getMessage());
                System.out.print("exception");
            } finally {
                return nextPage;
            } 

    }

    /**
     *
     * @param object
     */
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
