package Controllers;

import Aplicacao.Artigo;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Model.DAO.UsuarioDAO;
import Aplicacao.Usuario;
import Model.DAO.ArtigoDAO;
import Model.Linkers.ArtigoLinker;
import Model.Linkers.Linker;
import java.util.List;

@WebServlet(name = "Error", urlPatterns = {"/error"})
public class Error extends BaseController {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        super.doGet(request, response);
        request.setAttribute("error", session.getAttribute("error"));
        session.setAttribute("error", null);
        
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/error/index.jsp");
        rd.forward(request, response);
    }
}
