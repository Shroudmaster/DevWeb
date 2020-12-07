/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Linkers;

import Aplicacao.Artigo;
import Aplicacao.Categoria;
import Aplicacao.Usuario;
import Model.DAO.CategoriaDAO;
import Model.DAO.UsuarioDAO;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author arthu
 */
public class ArtigoLinker implements Linker<Artigo> {
    
    UsuarioDAO udao;
    CategoriaDAO cdao;
    
    public ArtigoLinker() {
        udao = new UsuarioDAO();
        cdao = new CategoriaDAO();
    }
    

    @Override
    public void Link(Artigo obj) {
        this._Link(
                obj, 
                udao.getUsuarioPorID(obj.getIdUsuario()), 
                cdao.getCategoriaPorID(obj.getIdCategoria())
        );
    }

    @Override
    public void Link(List<Artigo> obj) {
        obj.forEach(this::Link);
    }
    
    private void _Link(Artigo artigo, Usuario u, Categoria c) {
        if(Objects.isNull(artigo)) return;
        artigo.setUsuario(u);
        artigo.setCategoria(c);
    }

}
