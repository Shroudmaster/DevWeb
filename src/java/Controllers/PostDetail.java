package Controllers;

import Aplicacao.Artigo;
import Aplicacao.Comentario;
import Aplicacao.Usuario;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Model.DAO.ArtigoDAO;
import Model.DAO.ComentarioDAO;
import Model.Linkers.ArtigoLinker;
import Model.Linkers.ComentarioLinker;
import Model.Linkers.Linker;
import java.util.Objects;

@WebServlet(name = "PostDetail", urlPatterns = {"/post-detail"})
public class PostDetail extends BaseController {

    private static final String ID_NAO_IDENTIFICADO = "Não foi possível identificar o artigo desejado";
    private static final String USUARIO_NAO_IDENTIFICADO = "Não foi possível identificar o usuário logado, clique em entrar e tente novamente";
    

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        super.doPost(request, response);
        session = request.getSession();
        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
        if(Objects.isNull(usuarioLogado)) {
            request.setAttribute("error", USUARIO_NAO_IDENTIFICADO);
            this.redirectError(response);
            return;
        }
        
        String conteudo = request.getParameter("text");
        String artigoId = request.getParameter("id");
        if(!Objects.isNull(conteudo) && !Objects.isNull(artigoId) && !conteudo.equals("<p><br></p>")) {
            int aid = Integer.parseInt(artigoId);
            
            ArtigoDAO adao = new ArtigoDAO();
            ComentarioDAO cdao = new ComentarioDAO();
            
            if(Objects.isNull(adao.getArtigoPorID(aid))) {
                this.redirectError(response);
                return;
            }
            
            Comentario c = new Comentario();
            c.setIdArtigo(aid);
            c.setIdUsuario(usuarioLogado.getId());
            c.setComentario(conteudo);
            cdao.gravar(c);
        }
            
        response.sendRedirect("/post-detail?id=" + artigoId);
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        super.doGet(request, response);
        
        if(Objects.isNull(request.getParameter("id"))) {
            request.setAttribute("error", ID_NAO_IDENTIFICADO);
            this.redirectError(response);
            return;
        } else {
            ArtigoDAO dao = new ArtigoDAO();
            Linker<Artigo> linker = new ArtigoLinker();
            Linker<Comentario> linkerCom = new ComentarioLinker();
            int id = Integer.parseInt(request.getParameter("id"));
            Artigo a = dao.getArtigoPorID(id);
            if(Objects.isNull(a)) {
                request.setAttribute("error", ID_NAO_IDENTIFICADO);
                this.redirectError(response);
                return;
            } else {
                linker.Link(a);
                linkerCom.Link(a.getComentarios());
                request.setAttribute("artigo", a);
            }
        }
        
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/post-detail/index.jsp");
        rd.forward(request, response);
    }
    
    protected void redirectError(HttpServletResponse response) throws IOException {
        response.sendRedirect("/error/index.jsp");
    }

}
