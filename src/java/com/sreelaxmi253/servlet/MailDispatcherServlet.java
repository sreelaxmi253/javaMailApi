package com.sreelaxmi253.servlet;

import com.sreelaxmi253.ejb.MailSenderBean;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author User1
 */
@WebServlet(name = "MailDispatcherServlet", urlPatterns = {"/MailDispatcherServlet"})
public class MailDispatcherServlet extends HttpServlet {
    
   
    
    @EJB 
    private MailSenderBean mailSender;

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, MessagingException {
        response.setContentType("text/html;charset=UTF-8");
        
        String toEmail = request.getParameter("toEmail");
        String subject = request.getParameter("subject");
        String message = request.getParameter("message");
        
        String fromEmail = "samplea384@gmail.com";
        String username = "sample account";
        String password = "sample1123";
       
        
        
        
        try (PrintWriter out = response.getWriter()) {
            
            
            mailSender.sendEmail(fromEmail, username, password, toEmail, subject, message);
            
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Mail Status</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Mail Status !!!</h1>");
            out.println("<b>Mail Sent Successfully !!!</b><br/>");
            out.println("Click <a href='emailClient.jsp'> here</a> to go back !!!");
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
        try {
            processRequest(request, response);
        } catch (MessagingException ex) {
            Logger.getLogger(MailDispatcherServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (MessagingException ex) {
            Logger.getLogger(MailDispatcherServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
