package Aplicacao;

import java.util.List;

public class Artigo {

    private int id;
    private int id_usuario;
    private int id_categoria;
    private String titulo;
    private String conteudo;
    private String liberar;
    private String aprovado;
    
    // Transient
    private Usuario usuario;
    private Categoria categoria;
    private List<Comentario> comentarios;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUsuario() {
        return id_usuario;
    }

    public void setIdUsuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getIdCategoria() {
        return id_categoria;
    }

    public void setIdCategoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public String getLiberar() {
        return liberar;
    }

    public void setLiberar(String liberar) {
        this.liberar = liberar;
    }

    public String getAprovado() {
        return aprovado;
    }

    public void setAprovado(String aprovado) {
        this.aprovado = aprovado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }
    
    

    public String getLink() {
        return "/post-detail?id=" + this.id;
    }

}
