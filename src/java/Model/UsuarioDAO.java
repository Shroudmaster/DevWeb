package Model;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import Aplicacao.Usuario; //Modelo que representa um registro do BD

public class UsuarioDAO {

    private Connection conexao;
    private String fields = "id, nome, cpf, senha, email, papel, cadastro_aprovado";

    public UsuarioDAO() {
        try {
            // Executa a conexão com o banco de dados
            conexao = Conexao.criaConexao();
        } catch (Exception e) {
            System.out.println("Erro criação de conexao DAO");
            System.out.println(e);
        }
    }

    public ArrayList<Usuario> getListaUsuarios() {
        //Cria o objeto resultado que irá armazenar os registros retornados do BD
        ArrayList<Usuario> resultado = new ArrayList<>();
        try {
            // Cria o objeto para quer será utilizado para enviar comandos SQL
            // para o BD
            Statement stmt = conexao.createStatement();
            // Armazena o resultado do comando enviado para o banco de dados
            ResultSet rs = stmt.executeQuery("select "+this.fields+" from usuario");
            // rs.next() Aponta para o próximo registro do BD, se houver um 
            while (rs.next()) {
                //Cria o objeto da classe Contato para armazenar os dados
                //que vieram do BD
                Usuario usuario = new Usuario();
                this.populateUsuarioObject(usuario, rs);
                resultado.add(usuario);
            }
        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
        }

        // Retorna a lista de Contatos encontrados no banco de dados.
        return resultado;
    }

    public Usuario getUsuarioPorID(int id) {
        Usuario usuario = new Usuario();
        try {
            String sql = "SELECT "+this.fields+" FROM usuario WHERE id = ?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                populateUsuarioObject(usuario, rs);
            }

        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
        }
        return usuario;
    }

    public boolean gravar(Usuario usuario) {
        try {
            String sql;
            if (usuario.getId() == 0) {
                // Realizar uma inclusão
                sql = "INSERT INTO usuario (nome, cpf, senha, email, papel, cadastro_aprovado) VALUES (?,?,?,?,?,?)";
            } else {
                // Realizar uma alteração
                sql = "UPDATE usuario SET nome=?, cpf=?, senha=?, email=?, papel=?, cadastro_aprovado=? WHERE id=?";
            }

            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getCpf());
            ps.setString(3, usuario.getSenha());
            ps.setString(4, usuario.getEmail());
            ps.setInt(5, usuario.getPapel());
            ps.setString(6, usuario.getAprovado());            

            if (usuario.getId() > 0) {
                ps.setInt(7, usuario.getId());
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
            String sql = "DELETE FROM usuario WHERE id = ?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
            return false;
        }
    }

    public Usuario getUsuarioPorLogin(String cpf) {
        Usuario usuario = new Usuario();
        try {
            usuario.setId(0);
            
            String sql = "SELECT "+this.fields+" FROM usuario WHERE cpf = ? limit 1";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, cpf);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                populateUsuarioObject(usuario, rs);
            }

        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
        }
        return usuario;
    }
    
    public Usuario getUsuarioPorLoginSenha(String cpf, String senha) {
        Usuario u = this.getUsuarioPorLogin(cpf);
        if(u.getSenha().equals(senha) && u.getAprovado().equals("S")){
            return u;
        }
        else {
            u = new Usuario();
            u.setId(0);
            return u;
        }
    }
    
    private void populateUsuarioObject(Usuario u, ResultSet rs) throws SQLException {
        u.setId(rs.getInt("id"));
        u.setNome(rs.getString("nome"));
        u.setCpf(rs.getString("cpf"));
        u.setSenha(rs.getString("senha"));
        u.setEmail(rs.getString("email"));
        u.setPapel(rs.getInt("papel"));
        u.setAprovado(rs.getString("cadastro_aprovado"));
    }

}
