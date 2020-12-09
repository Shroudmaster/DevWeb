/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Linkers;

import Aplicacao.Artigo;
import Aplicacao.Categoria;
import Aplicacao.Comentario;
import Aplicacao.Usuario;
import Model.DAO.ArtigoDAO;
import Model.DAO.CategoriaDAO;
import Model.DAO.UsuarioDAO;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author arthu
 */
public class ComentarioLinker implements Linker<Comentario> {
    
    UsuarioDAO udao;
    ArtigoDAO adao;
    
    public ComentarioLinker() {
        udao = new UsuarioDAO();
        adao = new ArtigoDAO();
    }
    

    @Override
    public void Link(Comentario obj) {
        this._Link(
                obj, 
                udao.getUsuarioPorID(obj.getIdUsuario()),
                adao.getArtigoPorID(obj.getIdArtigo())
        );
    }

    @Override
    public void Link(List<Comentario> obj) {
        obj.forEach(c -> this._Link(c, udao.getUsuarioPorID(c.getIdUsuario()), null));
    }
    
    private void _Link(Comentario c, Usuario u, Artigo a) {
        if(Objects.isNull(c)) return;
        c.setUsuario(u);
        c.setArtigo(a);
    }

}
