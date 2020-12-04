package Model.DAO;

import Aplicacao.Artigo;
import Aplicacao.Comentario;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ComentarioDAO {

    private Connection conexao;
    private String fields = "id, comentario, id_usuario, id_artigo";

    public ComentarioDAO() {
        try {
            // Executa a conexão com o banco de dados
            conexao = Conexao.criaConexao();
        } catch (Exception e) {
            System.out.println("Erro criação de conexao DAO");
            System.out.println(e);
        }
    }

    public ArrayList<Comentario> getListaComentarios() {
        //Cria o objeto resultado que irá armazenar os registros retornados do BD
        ArrayList<Comentario> resultado = new ArrayList<>();
        try {
            // Cria o objeto para quer será utilizado para enviar comandos SQL
            // para o BD
            Statement stmt = conexao.createStatement();
            // Armazena o resultado do comando enviado para o banco de dados
            ResultSet rs = stmt.executeQuery("select " + this.fields + " from comentario");
            // rs.next() Aponta para o próximo registro do BD, se houver um 
            while (rs.next()) {
                //Cria o objeto da classe Contato para armazenar os dados
                //que vieram do BD
                Comentario comentario = new Comentario();
                this.populateComentarioObject(comentario, rs);
                resultado.add(comentario);
            }
        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
        }

        // Retorna a lista de Contatos encontrados no banco de dados.
        return resultado;
    }

    public ArrayList<Comentario> getListaComentariosDoUsuario(int id) {
        ArrayList<Comentario> resultado = new ArrayList<>();
        try {
            String sql = "SELECT " + this.fields + " FROM comentario WHERE id_usuario = ?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Comentario comentario = new Comentario();
                this.populateComentarioObject(comentario, rs);
                resultado.add(comentario);
            }
        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
        }

        return resultado;
    }

    public Comentario getComentarioPorID(int id) {
        Comentario comentario = new Comentario();
        try {
            String sql = "SELECT " + this.fields + " FROM comentario WHERE id = ?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                populateComentarioObject(comentario, rs);
            }

        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
        }
        return comentario;
    }

    public boolean gravar(Comentario comentario) {
        try {
            String sql;
            if (comentario.getId() == 0) {
                // Realizar uma inclusão
                sql = "INSERT INTO comentario (comentario, id_usuario, id_artigo) VALUES (?,?,?)";
            } else {
                // Realizar uma alteração
                sql = "UPDATE comentario SET comentario=?, id_usuario=?, id_artigo=? WHERE id=?";
            }

            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, comentario.getComentario());
            ps.setInt(2, comentario.getIdUsuario());
            ps.setInt(3, comentario.getIdArtigo());            

            if (comentario.getId() > 0) {
                ps.setInt(4, comentario.getId());
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
            String sql = "DELETE FROM comentario WHERE id = ?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
            return false;
        }
    }

    private void populateComentarioObject(Comentario a, ResultSet rs) throws SQLException {
        a.setId(rs.getInt("id"));
        a.setComentario(rs.getString("titulo"));
        a.setIdUsuario(rs.getInt("id_usuario"));
        a.setIdArtigo(rs.getInt("id_artigo"));
    }

}
