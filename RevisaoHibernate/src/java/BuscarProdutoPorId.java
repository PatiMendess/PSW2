/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author RA21504781
 */
public class BuscarProdutoPorId extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet BuscarProdutoPorId</title>");            
            out.println("</head>");
            out.println("<body>");
            
            Session sessao = HibernateUtil.getSessionFactory().openSession();
            
            //pesquisar pelo id
            int idForm = 2;
            //varialvel que referenciara o objeto ja com o resultado final
            //Produto p = (Produto) sessao.get(Produto.class, idForm);
            
            
            //buscar varios produtos
            //exclui int idForm = 2;
            //Produto p = (Produto) sessao.get(Produto.class, idForm);
            
            
            Criteria c = sessao.createCriteria(Produto.class);
            c.add(Restrictions.ge("preco", 10));
            //c.add(Restrictions.eq("nome", "batata"));
            
            //alterar um valor no banco (atualizando/update)
            List<Produto> produtos = c.list();
            
            Transaction t = sessao.beginTransaction();
           
            for(Produto p : produtos){
                p.setPreco(10);
                sessao.save(p);
            }
            
            
            //p.setNome("Novo batata");
                      
            sessao.flush();
            t.commit();
            
            
                         
            
            out.println("<h1>Servlet BuscarProdutoPorId at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
