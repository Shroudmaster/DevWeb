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

@WebServlet(name = "NovoArtigo", urlPatterns = {"/admin/artigos/novo"})
public class NovoArtigo extends AdminBase {
    
    private static final String ERRO_USUARIO_SEM_PERMISSAO = "O usuário logado não possui permissão para editar este conteúdo";
    private static final String ERRO_INESPERADO = "Ocorreu um erro inesperado e o artigo não pode ser salvo corretamente";

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
            Artigo artigo = new Artigo();
            
            String titulo = request.getParameter("titulo");
            String conteudo = request.getParameter("text");
            Integer idCategoria = Integer.parseInt(request.getParameter("categoria"));
            
            artigo.setTitulo(titulo);
            artigo.setConteudo(conteudo);
            artigo.setIdCategoria(idCategoria);
            artigo.setIdUsuario(usuarioLogado.getId());
            artigo.setAprovado("N");
            artigo.setLiberar("N");
            
            dao.gravar(artigo);
            
            response.sendRedirect("/admin/meus-posts");
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
