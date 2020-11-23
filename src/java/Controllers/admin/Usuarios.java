package Controllers.admin;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Model.UsuarioDAO;
import Aplicacao.Usuario;
import java.util.List;

@WebServlet(name = "usuarios", urlPatterns = {"/admin/usuarios"})
public class Usuarios extends AdminBase {
    
    private static final String USUARIO_NAO_ENCONTRADO = "O usuário informado não foi identificado, por favor verifique as credenciais";
    private static final String ERRO_USUARIO_NAO_ADM = "O usuário logado não possui permissão para editar este conteúdo";
    private static final String ERRO_PROCESSO = "Houve um erro ao atualizar os cadastros";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        super.doPost(request, response);
        if(redirectSignIn(request, response))
            return;
        
        String[] ids = request.getParameterValues("id[]");

        UsuarioDAO usuariodao = new UsuarioDAO();
        if(usuarioLogado.getPapel() != 0){
            request.setAttribute("erroAdminUsuarios", ERRO_USUARIO_NAO_ADM);
            forward(request,response);
            return;
        }
            
        try {
            for(int i =0; i<ids.length; i++){
                String aprovado = request.getParameter("aprovado"+i) == null? "N": "S";
                String papel = request.getParameter("papelradio"+i);
                
                Usuario usuario = usuariodao.getUsuarioPorID(Integer.parseInt(ids[i]));
                usuario.setAprovado(aprovado);
                usuario.setPapel(Integer.parseInt(papel));
                usuariodao.gravar(usuario);
            }    
        } catch(Exception e) {
            e.printStackTrace();
            request.setAttribute("erroAdminUsuarios", ERRO_PROCESSO);
        }
        
        forward(request, response);
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        super.doGet(request, response);
        if(!redirectSignIn(request, response))
            forward(request, response);        
    }

    private void forward(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        UsuarioDAO usuariodao = new UsuarioDAO();
        List<Usuario> usuarios = usuariodao.getListaUsuarios();
        
        request.setAttribute("listaUsuarios", usuarios);
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/admin/usuarios/index.jsp");
        rd.forward(request, response);        
    }
    
}
