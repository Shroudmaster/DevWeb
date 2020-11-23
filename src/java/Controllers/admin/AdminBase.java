package Controllers.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Aplicacao.Usuario;

public abstract class AdminBase extends HttpServlet {
    
    protected HttpSession session;
    protected Usuario usuarioLogado;
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        session = request.getSession();
        usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        session = request.getSession();
        usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
    }
    
    
    protected boolean redirectSignIn(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if(usuarioLogado == null) {
            response.sendRedirect("/sign-in/index.jsp");
            return true;
        }
        return false;
    }
    
}
