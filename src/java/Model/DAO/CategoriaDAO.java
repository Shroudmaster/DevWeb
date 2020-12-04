package Model.DAO;

import Aplicacao.Categoria;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CategoriaDAO {

    private Connection conexao;
    private String fields = "id, descricao";

    public CategoriaDAO() {
        try {
            // Executa a conexão com o banco de dados
            conexao = Conexao.criaConexao();
        } catch (Exception e) {
            System.out.println("Erro criação de conexao DAO");
            System.out.println(e);
        }
    }

    public ArrayList<Categoria> getListaCategorias() {
        //Cria o objeto resultado que irá armazenar os registros retornados do BD
        ArrayList<Categoria> resultado = new ArrayList<>();
        try {
            // Cria o objeto para quer será utilizado para enviar comandos SQL
            // para o BD
            Statement stmt = conexao.createStatement();
            // Armazena o resultado do comando enviado para o banco de dados
            ResultSet rs = stmt.executeQuery("select "+this.fields+" from categoria");
            // rs.next() Aponta para o próximo registro do BD, se houver um 
            while (rs.next()) {
                //Cria o objeto da classe Contato para armazenar os dados
                //que vieram do BD
                Categoria c = new Categoria();
                this.populateCategoriaObject(c, rs);
                resultado.add(c);
            }
        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
        }

        // Retorna a lista de Contatos encontrados no banco de dados.
        return resultado;
    }

    public Categoria getCategoriaPorID(int id) {
        Categoria c = new Categoria();
        try {
            String sql = "SELECT "+this.fields+" FROM categoria WHERE id = ?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                populateCategoriaObject(c, rs);
            }

        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
        }
        return c;
    }
    
    private void populateCategoriaObject(Categoria c, ResultSet rs) throws SQLException {
        c.setId(rs.getInt("id"));
        c.setDescricao(rs.getString("descricao"));        
    }

}
