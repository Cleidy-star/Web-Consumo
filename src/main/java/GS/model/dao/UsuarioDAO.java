package GS.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import GS.model.beans.Usuario;
import GS.conexao.ConnectionFactory;

/**
 * Classe UsuarioDAO que realiza as operações CRUD para os registros de usuários no banco de dados.
 */
public class UsuarioDAO {

    /**
     * Insere um usuário no banco de dados.
     *
     * @param usuario Objeto Usuario com os dados do usuário.
     * @return Mensagem de sucesso.
     * @throws SQLException
     */
    public String inserir(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO TB_USUARIO (id_usuario, nome, email, senha, data_cadastro) VALUES (?, ?, ?, ?, ?)";

        try (Connection minhaConexao = ConnectionFactory.conexao();
             PreparedStatement stmt = minhaConexao.prepareStatement(sql)) {

            stmt.setInt(1, usuario.getIdUsuario());
            stmt.setString(2, usuario.getNome());
            stmt.setString(3, usuario.getEmail());
            stmt.setString(4, usuario.getSenha());
            stmt.setDate(5, new java.sql.Date(usuario.getDataCadastro().getTime()));
            stmt.execute();
        }

        return "Usuário cadastrado com sucesso!";
    }

    /**
     * Atualiza os dados de um usuário no banco de dados.
     *
     * @param usuario Objeto Usuario com os dados atualizados do usuário.
     * @return Mensagem de sucesso.
     * @throws SQLException
     */
    public String atualizar(Usuario usuario) throws SQLException {
        String sql = "UPDATE TB_USUARIO SET nome = ?, email = ?, senha = ?, data_cadastro = ? WHERE id_usuario = ?";

        try (Connection minhaConexao = ConnectionFactory.conexao();
             PreparedStatement stmt = minhaConexao.prepareStatement(sql)) {

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha());
            stmt.setDate(4, new java.sql.Date(usuario.getDataCadastro().getTime()));
            stmt.setInt(5, usuario.getIdUsuario());
            stmt.executeUpdate();
        }

        return "Usuário atualizado com sucesso!";
    }

    /**
     * Deleta um usuário do banco de dados com base no ID.
     *
     * @param idUsuario ID do usuário a ser deletado.
     * @return Mensagem de sucesso.
     * @throws SQLException
     */
    public String deletar(int idUsuario) throws SQLException {
        String sql = "DELETE FROM TB_USUARIO WHERE id_usuario = ?";

        try (Connection minhaConexao = ConnectionFactory.conexao();
             PreparedStatement stmt = minhaConexao.prepareStatement(sql)) {

            stmt.setInt(1, idUsuario);
            stmt.execute();
        }

        return "Usuário deletado com sucesso!";
    }

    /**
     * Retorna uma lista com todos os usuários do banco de dados.
     *
     * @return Lista de objetos Usuario.
     * @throws SQLException
     */
    public List<Usuario> selecionar() throws SQLException {
        List<Usuario> listaUsuarios = new ArrayList<>();
        String sql = "SELECT * FROM TB_USUARIO";

        try (Connection minhaConexao = ConnectionFactory.conexao();
             PreparedStatement stmt = minhaConexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("id_usuario"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setDataCadastro(rs.getDate("data_cadastro"));
                listaUsuarios.add(usuario);
            }
        }

        return listaUsuarios;
    }

    /**
     * Busca um usuário pelo email.
     *
     * @param email Email do usuário a ser buscado.
     * @return Objeto Usuario, caso seja encontrado.
     * @throws SQLException
     */
    public Usuario buscarPorEmail(String email) throws SQLException {
        String sql = "SELECT * FROM TB_USUARIO WHERE email = ?";

        try (Connection minhaConexao = ConnectionFactory.conexao();
             PreparedStatement stmt = minhaConexao.prepareStatement(sql)) {

            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Usuario usuario = new Usuario();
                    usuario.setIdUsuario(rs.getInt("id_usuario"));
                    usuario.setNome(rs.getString("nome"));
                    usuario.setEmail(rs.getString("email"));
                    usuario.setSenha(rs.getString("senha"));
                    usuario.setDataCadastro(rs.getDate("data_cadastro"));
                    return usuario;
                }
            }
        }

        return null; // Caso não encontre o registro
    }
}
