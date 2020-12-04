package Controllers;

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

@WebServlet(name = "Cadastro", urlPatterns = {"/Cadastro"})
public class Cadastro extends BaseController {
    
    private static String ERRO_CADASTRO = "Não foi possível realizar o cadastro, por favor tente novamente mais tarde";
    private static String CPF_DUPLICADO = "Esse CPF já existe";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String cpf = request.getParameter("cpf");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String nome = request.getParameter("nome");
        String papel = request.getParameter("papel");

        Usuario usuario = new Usuario();
        usuario.setAprovado("N");
        usuario.setCpf(cpf);
        usuario.setNome(nome);
        usuario.setPapel(Integer.parseInt(papel));
        usuario.setSenha(senha);
        usuario.setEmail(email);
        
        UsuarioDAO usuariodao = new UsuarioDAO();
        Usuario u = usuariodao.getUsuarioPorLogin(cpf);
        if(u.getId() != 0) {
            HttpSession session = request.getSession();
            session.setAttribute("erroCadastro", CPF_DUPLICADO);
            response.sendRedirect("/cadastro/index.jsp");
            return;
        }        
            
        boolean saved = usuariodao.gravar(usuario);        
        
        if (saved){
            response.sendRedirect("/sign-in/index.jsp");
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("erroCadastro", ERRO_CADASTRO);
            response.sendRedirect("/cadastro/index.jsp");
        }
    }
    
}
