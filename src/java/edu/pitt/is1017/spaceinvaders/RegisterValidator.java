
package edu.pitt.is1017.spaceinvaders;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "RegisterValidator", urlPatterns = {"/RegisterValidator"})
public class RegisterValidator extends HttpServlet {

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
            String firstName = "";
            String lastName = "";
            String email = "";
            String password = "";
            String password2 = "";
            User user;
            if (request.getParameter("btnRegister") != null) {
                if (request.getParameter("txtFirstName") != null) {
                    if (request.getParameter("txtFirstName") != "") {
                        firstName = request.getParameter("txtFirstName");
                    }

                }
                if (request.getParameter("txtLastName") != null) {
                    if (request.getParameter("txtLasttName") != "") {
                        lastName = request.getParameter("txtLastName");
                    }

                }
                if (request.getParameter("txtEmail") != null) {
                    if (request.getParameter("txtEmail") != "") {
                        email = request.getParameter("txtEmail");
                    }

                }
                if (request.getParameter("txtPassword") != null) {
                    if (request.getParameter("txtPassword") != "") {
                        password = request.getParameter("txtPassword");
                    }

                }
                if (request.getParameter("txtConfirmPassword") != null) {
                    if (request.getParameter("txtConfirmPassword") != "") {
                        password2 = request.getParameter("txtConfirmPassword");
                    }

                }
                if (!firstName.equals("") && !lastName.equals("") && !email.equals("") && !password.equals("") && !password2.equals("")) {
                    if (password.equals(password2)) {
                        user = new User(lastName, firstName, email, password);
                        if(user.loggedIn){
                            response.sendRedirect("index.jsp");
                        } else {
                            out.println("<script>alert('REGISTER FAILED');</script>");
                        }   
                            
                            
                    } else {
                        out.println("<script>alert('Passwords Did Not Match!');</script>");
                    }
                   // out.println("<script>alert('SUCCESSFUL LOGIN');</script>");

                } else {
                    out.println("<script>alert('REGISTER FAILED');</script>");
                }
            }

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
