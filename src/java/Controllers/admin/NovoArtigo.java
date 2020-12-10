package Controllers.admin;

import Aplicacao.Artigo;
import Aplicacao.Categoria;
import Aplicacao.Comentario;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Model.DAO.ArtigoDAO;
import Model.DAO.CategoriaDAO;
import Model.Linkers.ArtigoLinker;
import Model.Linkers.ComentarioLinker;
import Model.Linkers.Linker;
import java.util.List;
import java.util.Objects;

@WebServlet(name = "NovoArtigo", urlPatterns = {"/admin/artigos/novo"})
public class NovoArtigo extends AdminBase {
    
    private static final String ERRO_USUARIO_SEM_PERMISSAO = "O usuário logado não possui permissão para editar este conteúdo";
    private static final String ERRO_INESPERADO = "Ocorreu um erro inesperado e o artigo não pode ser salvo corretamente";
    private static final String ID_NAO_IDENTIFICADO = "Não foi possível identificar o artigo a ser editado";
    private static final String ARTIGO_APROVADO = "Não é possível editar um artigo aprovado pelo administrador";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        super.doPost(request, response);
        if(redirectSignIn(request, response))
            return;
        
        if(usuarioLogado.getPapel() != 0 && usuarioLogado.getPapel() != 1) {
            request.setAttribute("erro", ERRO_USUARIO_SEM_PERMISSAO);
            forward(request, response);
            return;
        }
        
        try {
            ArtigoDAO dao = new ArtigoDAO();
            Artigo artigo = null;
            
            String titulo = request.getParameter("titulo");
            String conteudo = request.getParameter("text");
            Integer idCategoria = Integer.parseInt(request.getParameter("categoria"));
            
            
            
            if(Objects.isNull(request.getParameter("id"))) {
                artigo = new Artigo();
                artigo.setIdUsuario(usuarioLogado.getId());
                artigo.setAprovado("N");
                artigo.setLiberar("N");
            } else {
                int id = Integer.parseInt(request.getParameter("id"));
                artigo = dao.getArtigoPorID(id);
                if(artigo.getAprovado().equals("S")){
                    request.setAttribute("error", ARTIGO_APROVADO);
                    response.sendRedirect("/admin/artigos/novo?id=" + id);
                    return;
                }
            }
            
            artigo.setTitulo(titulo);
            artigo.setConteudo(conteudo);
            artigo.setIdCategoria(idCategoria);
            
            dao.gravar(artigo);
            
            response.sendRedirect("/admin/artigos/meus");
        } catch(Exception e) {
            e.printStackTrace();
            request.setAttribute("erro", ERRO_INESPERADO);
            forward(request, response);
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        super.doGet(request, response);
        if(redirectSignIn(request, response))
            return;
        if(Objects.nonNull(request.getParameter("id"))) {
            ArtigoDAO dao = new ArtigoDAO();
            Linker<Artigo> linker = new ArtigoLinker();
            int id = Integer.parseInt(request.getParameter("id"));
            Artigo a = dao.getArtigoPorID(id);
            if(Objects.isNull(a)) {
                request.setAttribute("error", ID_NAO_IDENTIFICADO);
            } else {
                if(a.getAprovado().equals("S"))
                    request.setAttribute("error", ARTIGO_APROVADO);
                linker.Link(a);
                request.setAttribute("artigoEdit", a);
            }
        }
        
        forward(request, response);
    }
    
    
    private void forward(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        CategoriaDAO categoriadao = new CategoriaDAO();
        List<Categoria> categorias = categoriadao.getListaCategorias();
        
        request.setAttribute("listaCategorias", categorias);
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/admin/novo-post/index.jsp");
        rd.forward(request, response);       
    }
    
}
