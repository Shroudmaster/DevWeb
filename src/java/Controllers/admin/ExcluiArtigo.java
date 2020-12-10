package Controllers.admin;

import Aplicacao.Artigo;
import Aplicacao.Categoria;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Model.DAO.ArtigoDAO;
import Model.DAO.CategoriaDAO;
import java.util.List;
import java.util.Objects;

@WebServlet(name = "ExcluiArtigo", urlPatterns = {"/admin/artigos/delete"})
public class ExcluiArtigo extends AdminBase {
    
    private static final String ERRO_USUARIO_SEM_PERMISSAO = "O usuário logado não possui permissão para editar este conteúdo";
    private static final String ERRO_ID = "Não foi possível identificar um artigo para este id";
    private static final String ERRO_INESPERADO = "Ocorreu um erro inesperado e o artigo não pode ser salvo corretamente";
    private static final String ARTIGO_APROVADO = "Não é possível editar um artigo aprovado pelo administrador";
    
    public static final String sessionErrorKey = "erroExclui";
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        super.doGet(request, response);
        if(redirectSignIn(request, response))
            return;
        
        if(usuarioLogado.getPapel() != 0 && usuarioLogado.getPapel() != 1) {
            request.setAttribute("erro", ERRO_USUARIO_SEM_PERMISSAO);
        }        
        
        delete(request, response);
        
        response.sendRedirect("/admin/artigos/meus");
    }
    
    private void delete(HttpServletRequest request, HttpServletResponse response) {
        if(Objects.isNull(request.getParameter("id"))) {
            session.setAttribute(sessionErrorKey, ERRO_ID);
            return;
        }
        ArtigoDAO dao = new ArtigoDAO();
        int id = Integer.parseInt(request.getParameter("id"));
        Artigo artigo = dao.getArtigoPorID(id);
        if(Objects.isNull(artigo)) {
            session.setAttribute(sessionErrorKey, ERRO_ID);
            return;
        }
        if(artigo.getAprovado().equals("S")){
            session.setAttribute(sessionErrorKey, ARTIGO_APROVADO);
            return;
        }
        if(usuarioLogado.getPapel() != 0 && usuarioLogado.getId() != artigo.getIdUsuario()) {
            session.setAttribute(sessionErrorKey, ERRO_USUARIO_SEM_PERMISSAO);
            return;
        }
        dao.excluir(id);
    }
    
}
