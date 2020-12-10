package Controllers;

import Aplicacao.Artigo;
import Aplicacao.Categoria;
import Aplicacao.Comentario;
import Aplicacao.Usuario;
import Controllers.admin.AdminBase;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Model.DAO.ArtigoDAO;
import Model.DAO.CategoriaDAO;
import Model.DAO.ComentarioDAO;
import java.util.List;
import java.util.Objects;

@WebServlet(name = "ExcluiComentario", urlPatterns = {"/comentario/delete"})
public class ExcluiComentario extends BaseController {
    
    private static final String ERRO_USUARIO_SEM_PERMISSAO = "O usuário logado não possui permissão para editar este conteúdo";
    private static final String ERRO_ID = "Não foi possível identificar um artigo para este id";
    private static final String ERRO_INESPERADO = "Ocorreu um erro inesperado e o artigo não pode ser salvo corretamente";
    private static final String ARTIGO_APROVADO = "Não é possível editar um artigo aprovado pelo administrador";
    
    public static final String sessionErrorKey = "error";
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        super.doGet(request, response);
        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
        
        if(Objects.isNull(request.getParameter("id"))) {
            session.setAttribute(sessionErrorKey, ERRO_ID);
            this.redirectError(response);
            return;
        }
        ComentarioDAO dao = new ComentarioDAO();
        int id = Integer.parseInt(request.getParameter("id"));
        Comentario com = dao.getComentarioPorID(id);
        if(Objects.isNull(com)) {
            session.setAttribute(sessionErrorKey, ERRO_ID);
            this.redirectError(response);
            return;
        }
        if(usuarioLogado.getPapel() != 0 && usuarioLogado.getId() != com.getIdUsuario()) {
            session.setAttribute(sessionErrorKey, ERRO_USUARIO_SEM_PERMISSAO);
            this.redirectError(response);
            return;
        }
        dao.excluir(id);
        
        response.sendRedirect("/post-detail?id=" + request.getParameter("idArtigo"));
    }
    
    protected void redirectError(HttpServletResponse response) throws IOException {
        response.sendRedirect("/error");
    }
}
