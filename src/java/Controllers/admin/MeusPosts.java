package Controllers.admin;

import Aplicacao.Artigo;
import Aplicacao.Categoria;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Model.ArtigoDAO;
import Model.CategoriaDao;
import java.util.List;

@WebServlet(name = "MeusPosts", urlPatterns = {"/admin/meus-posts/"})
public class MeusPosts extends AdminBase {
    
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
        
        String[] ids = request.getParameterValues("id[]");

        ArtigoDAO dao = new ArtigoDAO();            
        try {
            for(int i =0; i<ids.length; i++){
                String liberado = request.getParameter("liberado"+i) == null? "N": "S";
                
                Artigo artigo = dao.getArtigoPorID(Integer.parseInt(ids[i]));                    
                artigo.setLiberar(liberado);
                dao.gravar(artigo);
            }    
        } catch(Exception e) {
            e.printStackTrace();
            request.setAttribute("erro", ERRO_INESPERADO);
        }
        
        forward(request, response);
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
        CategoriaDao categoriadao = new CategoriaDao();
        ArtigoDAO artigodao = new ArtigoDAO();
        List<Categoria> categorias = categoriadao.getListaCategorias();
        List<Artigo> artigos = artigodao.getListaArtigosDoUsuario(usuarioLogado.getId());
        
        request.setAttribute("listaCategorias", categorias);
        request.setAttribute("listaArtigos", artigos);
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/admin/meus-posts/index.jsp");
        rd.forward(request, response);       
    }
    
}
