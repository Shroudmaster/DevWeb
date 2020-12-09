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
import Model.DAO.CategoriaDAO;
import Model.DAO.ComentarioDAO;
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
    ComentarioDAO comdao;
    
    public ArtigoLinker() {
        udao = new UsuarioDAO();
        cdao = new CategoriaDAO();
        comdao = new ComentarioDAO();
    }
    

    @Override
    public void Link(Artigo obj) {
        this._Link(
                obj, 
                udao.getUsuarioPorID(obj.getIdUsuario()), 
                cdao.getCategoriaPorID(obj.getIdCategoria()),
                comdao.getListaComentariosDoArtigo(obj.getId())
        );
    }

    @Override
    public void Link(List<Artigo> obj) {
        obj.forEach(this::Link);
    }
    
    private void _Link(Artigo artigo, Usuario u, Categoria c, List<Comentario> lcom) {
        if(Objects.isNull(artigo)) return;
        artigo.setUsuario(u);
        artigo.setCategoria(c);
        artigo.setComentarios(lcom);
    }

}
