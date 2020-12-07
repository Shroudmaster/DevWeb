package Controllers;

import Aplicacao.Artigo;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Model.DAO.ArtigoDAO;
import Model.Linkers.ArtigoLinker;
import Model.Linkers.Linker;
import java.util.List;
import java.util.Objects;

@WebServlet(name = "PostDetail", urlPatterns = {"/post-detail"})
public class PostDetail extends BaseController {

    private static final String ID_NAO_IDENTIFICADO = "Não foi possível identificar o artigo desejado";
    

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        super.doGet(request, response);

        
        if(!Objects.isNull(request.getParameter("id"))) {
            request.setAttribute("error", ID_NAO_IDENTIFICADO);
        } else {
            ArtigoDAO dao = new ArtigoDAO();
            Linker<Artigo> linker = new ArtigoLinker();
            int id = Integer.parseInt(request.getParameter("id"));
            Artigo a = dao.getArtigoPorID(id);
            if(Objects.isNull(a)) {
                request.setAttribute("error", ID_NAO_IDENTIFICADO);
            }
            linker.Link(a);
            request.setAttribute("artigo", a);            
        }
        
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/post-detail/index.jsp");
        rd.forward(request, response);
    }

}
