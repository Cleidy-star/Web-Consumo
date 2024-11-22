package GS.model.bo;
import GS.model.beans.Usuario;
import GS.model.dao.UsuarioDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Classe UsuarioBO (Business Object) aplica as regras de negócio para a manipulação de usuários,
 * incluindo validações e acesso ao DAO para inserção, atualização, exclusão e seleção de usuários.
 */
public class UsuarioBO {

    private UsuarioDAO usuarioDAO;

    /**
     * Construtor que inicializa o DAO do usuário.
     *
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public UsuarioBO() throws ClassNotFoundException, SQLException {
        this.usuarioDAO = new UsuarioDAO();
    }

    /**
     * Insere um usuário no banco de dados após aplicar as regras de negócio.
     *
     * @param usuario Objeto Usuario contendo os dados do usuário.
     * @throws IllegalArgumentException se as validações de regras de negócio falharem.
     * @throws SQLException
     * @throws IOException
     */
    public void inserirUsuarioBO(Usuario usuario) throws ClassNotFoundException, SQLException, IOException {
        // Validar nome do usuário
        if (usuario.getNome() == null || usuario.getNome().isEmpty()) {
            throw new IllegalArgumentException("O nome do usuário não pode ser vazio.");
        }

        // Validar email do usuário
        if (usuario.getEmail() == null || usuario.getEmail().isEmpty()) {
            throw new IllegalArgumentException("O email do usuário não pode ser vazio.");
        }
        if (!usuario.getEmail().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            throw new IllegalArgumentException("O email do usuário deve estar em um formato válido.");
        }

        // Validar senha do usuário
        if (usuario.getSenha() == null || usuario.getSenha().isEmpty()) {
            throw new IllegalArgumentException("A senha do usuário não pode ser vazia.");
        }

        // Inserir usuário via DAO
        usuarioDAO.inserir(usuario);
    }

    /**
     * Atualiza um usuário no banco de dados após aplicar as regras de negócio.
     *
     * @param usuario Objeto Usuario contendo os dados do usuário.
     * @throws IllegalArgumentException se as validações de regras de negócio falharem.
     * @throws SQLException
     * @throws IOException
     */
    public void atualizarUsuarioBO(Usuario usuario) throws ClassNotFoundException, SQLException, IOException {
        // Validar nome do usuário
        if (usuario.getNome() == null || usuario.getNome().isEmpty()) {
            throw new IllegalArgumentException("O nome do usuário não pode ser vazio.");
        }

        // Validar email do usuário
        if (usuario.getEmail() == null || usuario.getEmail().isEmpty()) {
            throw new IllegalArgumentException("O email do usuário não pode ser vazio.");
        }

        // Atualizar usuário via DAO
        usuarioDAO.atualizar(usuario);
    }

    /**
     * Deleta um usuário do banco de dados com base no ID.
     *
     * @param idUsuario ID do usuário a ser deletado.
     * @throws IllegalArgumentException se o ID for inválido.
     * @throws SQLException
     */
    public void deletarUsuarioBO(int idUsuario) throws ClassNotFoundException, SQLException {
        // Deletar usuário via DAO
        usuarioDAO.deletar(idUsuario);
    }

    /**
     * Retorna uma lista de todos os usuários disponíveis no banco de dados.
     *
     * @return Lista de objetos Usuario.
     * @throws SQLException
     */
    public List<Usuario> selecionarUsuariosBO() throws ClassNotFoundException, SQLException {
        // Apenas chama o DAO para listar todos os usuários
        return usuarioDAO.selecionar();
    }

    /**
     * Realiza o login do usuário após validar suas credenciais.
     *
     * @param email Email do usuário.
     * @param senha Senha do usuário.
     * @return Objeto Usuario caso as credenciais sejam válidas, caso contrário null.
     * @throws SQLException
     */
    public Usuario fazerLoginBO(String email, String senha) throws ClassNotFoundException, SQLException {
        Usuario usuario = usuarioDAO.buscarPorEmail(email);

        if (usuario != null && usuario.getSenha().equals(senha)) {
            return usuario; // Login bem-sucedido
        } else {
            throw new IllegalArgumentException("Credenciais inválidas.");
        }
    }
}


