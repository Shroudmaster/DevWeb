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
import Model.Linkers.ArtigoLinker;
import Model.Linkers.Linker;
import java.util.List;

@WebServlet(name = "MeusPosts", urlPatterns = {"/admin/artigos/aprovacoes"})
public class AprovacoesPosts extends AdminBase {
    
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
        CategoriaDAO categoriadao = new CategoriaDAO();
        ArtigoDAO artigodao = new ArtigoDAO();
        List<Artigo> artigos = artigodao.getListaArtigosParaAprovacao();
        Linker<Artigo> linker = new ArtigoLinker();
        linker.Link(artigos);
        
        request.setAttribute("listaArtigos", artigos);
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/admin/aprovacoes-post/index.jsp");
        rd.forward(request, response);
    }
    
}
