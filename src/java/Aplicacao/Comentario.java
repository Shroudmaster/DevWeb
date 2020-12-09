/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aplicacao;

/**
 *
 * @author arthu
 */
public class Comentario {
    
    private int id;
    private String comentario;
    private int id_artigo;
    private int id_usuario;
    
    // Transient
    private Artigo artigo;
    private Usuario usuario;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public int getIdArtigo() {
        return id_artigo;
    }

    public void setIdArtigo(int id_artigo) {
        this.id_artigo = id_artigo;
    }

    public int getIdUsuario() {
        return id_usuario;
    }

    public void setIdUsuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public Artigo getArtigo() {
        return artigo;
    }

    public void setArtigo(Artigo artigo) {
        this.artigo = artigo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    
}
