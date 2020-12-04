/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Aplicacao.Usuario;
import Model.DAO.CategoriaDAO;
import java.io.IOException;
import java.util.Objects;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author arthu
 */
public class BaseController extends HttpServlet{
    
    protected HttpSession session;
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException { }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        session = request.getSession();
        if(Objects.isNull(session.getAttribute("categoriasMenu"))) {
            setCategoriasMenuSession();
        }
    }

    private void setCategoriasMenuSession() {
        CategoriaDAO dao = new CategoriaDAO();        
        session.setAttribute("categoriasMenu", dao.getListaCategorias());
    }
    
}
