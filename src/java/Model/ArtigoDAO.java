package Model;

import Aplicacao.Artigo;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ArtigoDAO {

    private Connection conexao;
    private String fields = "id, id_usuario, id_categoria, titulo, conteudo, liberar, aprovado";

    public ArtigoDAO() {
        try {
            // Executa a conexão com o banco de dados
            conexao = Conexao.criaConexao();
        } catch (Exception e) {
            System.out.println("Erro criação de conexao DAO");
            System.out.println(e);
        }
    }

    public ArrayList<Artigo> getListaArtigos() {
        //Cria o objeto resultado que irá armazenar os registros retornados do BD
        ArrayList<Artigo> resultado = new ArrayList<>();
        try {
            // Cria o objeto para quer será utilizado para enviar comandos SQL
            // para o BD
            Statement stmt = conexao.createStatement();
            // Armazena o resultado do comando enviado para o banco de dados
            ResultSet rs = stmt.executeQuery("select "+this.fields+" from artigo");
            // rs.next() Aponta para o próximo registro do BD, se houver um 
            while (rs.next()) {
                //Cria o objeto da classe Contato para armazenar os dados
                //que vieram do BD
                Artigo artigo = new Artigo();
                this.populateArtigoObject(artigo, rs);
                resultado.add(artigo);
            }
        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
        }

        // Retorna a lista de Contatos encontrados no banco de dados.
        return resultado;
    }
    
    public ArrayList<Artigo> getListaArtigosDoUsuario(int id) {
        ArrayList<Artigo> resultado = new ArrayList<>();
        try {
            String sql = "SELECT "+this.fields+" FROM artigo WHERE id_usuario = ?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Artigo artigo = new Artigo();
                this.populateArtigoObject(artigo, rs);
                resultado.add(artigo);
            }
        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
        }

        return resultado;
    }

    public Artigo getArtigoPorID(int id) {
        Artigo artigo = new Artigo();
        try {
            String sql = "SELECT "+this.fields+" FROM artigo WHERE id = ?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                populateArtigoObject(artigo, rs);
            }

        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
        }
        return artigo;
    }

    public boolean gravar(Artigo artigo) {
        try {
            String sql;
            if (artigo.getId() == 0) {
                // Realizar uma inclusão
                sql = "INSERT INTO artigo (id_usuario, id_categoria, titulo, conteudo, liberar, aprovado) VALUES (?,?,?,?,?,?)";
            } else {
                // Realizar uma alteração
                sql = "UPDATE artigo SET id_usuario=?, id_categoria=?, titulo=?, conteudo=?, liberar=?, aprovado=? WHERE id=?";
            }

            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, artigo.getIdUsuario());
            ps.setInt(2, artigo.getIdCategoria());
            ps.setString(3, artigo.getTitulo());
            ps.setString(4, artigo.getConteudo());
            ps.setString(5, artigo.getLiberar());
            ps.setString(6, artigo.getAprovado());

            if (artigo.getId() > 0) {
                ps.setInt(7, artigo.getId());
            }

            ps.execute();

            return true;
        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
            return false;
        }
    }

    public boolean excluir(int id) {
        try {
            String sql = "DELETE FROM artigo WHERE id = ?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
            return false;
        }
    }

    
    private void populateArtigoObject(Artigo a, ResultSet rs) throws SQLException {        
        a.setId(rs.getInt("id"));
        a.setIdUsuario(rs.getInt("id_usuario"));
        a.setIdCategoria(rs.getInt("id_categoria"));
        a.setTitulo(rs.getString("titulo"));
        a.setConteudo(rs.getString("conteudo"));
        a.setLiberar(rs.getString("liberar"));
        a.setAprovado(rs.getString("aprovado"));
    }

}
