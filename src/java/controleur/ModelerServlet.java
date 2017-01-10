/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modeler.Adresse;
import modeler.Personnel;

/**
 *
 * @author lapin
 */
public class ModelerServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    EntityManager em = Persistence.createEntityManagerFactory("DEFAULT_PU").createEntityManager();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ModelerServlet</title>");
            out.println("</head>");
            out.println("<body>");

            em.getTransaction().begin();

            Personnel pers = new Personnel();
            Personnel pers1 = new Personnel();
            Personnel pers2 = new Personnel();
            Personnel pers3 = new Personnel();

            Adresse adr = new Adresse();
            Adresse adr1 = new Adresse();
            Adresse adr2 = new Adresse();
            Adresse adr3 = new Adresse();

            adr.setAdresse("42 Rue de la Place Verte");
            adr1.setAdresse("57 Rue de la Place Bleue");
            adr2.setAdresse("69 Rue de la Place Orange");
            adr3.setAdresse("87 Rue de la Place Jaune");

            pers.setNom("Louise");
            pers.setPrenom("solange");
            pers.setAdresse(adr3);

            pers1.setNom("Pierre");
            pers1.setPrenom("alain");
            pers1.setAdresse(adr);

            pers2.setNom("Solange");
            pers2.setPrenom("bilé");
            pers2.setAdresse(adr2);

            pers3.setNom("Etienne");
            pers3.setPrenom("ciryll");
            pers3.setAdresse(adr1);

//            em.persist(pers);
//            em.persist(pers1);
//            em.persist(pers2);
//            em.persist(pers3);
//
//            em.persist(adr);
//            em.persist(adr1);
//            em.persist(adr2);
//            em.persist(adr3);

            em.getTransaction().commit();
            List<Personnel> personnels = lister();
            for (Personnel personnel : personnels) {
                out.println(personnel.toString()+ "<br/>");
            }
            em.close();

            //TypedQuery<>
            out.println("<h1>Servlet ModelerServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    private List<Personnel> lister() {
       TypedQuery<Personnel> query = em.createQuery("select p from Personnel p",Personnel.class);
        return query.getResultList();
    }
    
    
   

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
