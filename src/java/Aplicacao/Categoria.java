/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aplicacao;

import org.apache.tomcat.util.buf.StringUtils;

/**
 *
 * @author arthu
 */
public class Categoria {
    
    private int id;
    private String descricao;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }
    
    public String getDescricaoFormat() {
        return descricao.substring(0,1).toUpperCase() + descricao.substring(1).toLowerCase();
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    
    
}
