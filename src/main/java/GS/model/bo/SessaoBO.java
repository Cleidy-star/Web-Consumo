package GS.model.bo;

import GS.model.beans.Sessao;
import GS.model.dao.SessaoDAO;
import GS.model.beans.Usuario;

import java.sql.SQLException;
import java.util.List;

/**
 * Classe SessaoBO (Business Object) aplica as regras de negócio para a manipulação de sessões,
 * incluindo validações e acesso ao DAO para inserção, atualização e seleção de sessões.
 */
public class SessaoBO {

    private SessaoDAO sessaoDAO;

    /**
     * Construtor que inicializa o DAO da sessão.
     *
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public SessaoBO() throws ClassNotFoundException, SQLException {
        this.sessaoDAO = new SessaoDAO();
    }

    /**
     * Inicia uma nova sessão para um usuário após aplicar as regras de negócio.
     *
     * @param usuario Objeto Usuario contendo os dados do usuário.
     * @throws IllegalArgumentException se as validações de regras de negócio falharem.
     * @throws SQLException
     */
    public void iniciarSessaoBO(Usuario usuario) throws SQLException {
        if (usuario == null) {
            throw new IllegalArgumentException("Usuário inválido.");
        }

        // Criar nova sessão
        Sessao sessao = new Sessao();

        // Inserir sessão no banco de dados
        sessaoDAO.inserir(sessao);
    }

    /**
     * Finaliza uma sessão no banco de dados com base no ID.
     *
     * @param idSessao ID da sessão a ser finalizada.
     * @throws IllegalArgumentException se o ID for inválido.
     * @throws SQLException
     */
    public void finalizarSessaoBO(int idSessao) throws SQLException {
        if (idSessao <= 0) {
            throw new IllegalArgumentException("O ID da sessão é inválido.");
        }

        // Buscar a sessão no banco de dados
        Sessao sessao = sessaoDAO.buscarPorId(idSessao);

        if (sessao == null) {
            throw new IllegalArgumentException("Sessão não encontrada.");
        }

        // Finalizar a sessão
        sessao.finalizarSessao();

        // Atualizar sessão no banco de dados
        sessaoDAO.atualizar(sessao);
    }

    /**
     * Retorna uma lista de todas as sessões disponíveis no banco de dados.
     *
     * @return Lista de objetos Sessao.
     * @throws SQLException
     */
    public List<Sessao> selecionarSessoesBO() throws SQLException {
        // Apenas chama o DAO para listar todas as sessões
        return sessaoDAO.selecionar();
    }

    /**
     * Busca uma sessão pelo ID.
     *
     * @param idSessao ID da sessão a ser buscada.
     * @return Objeto Sessao, caso seja encontrado.
     * @throws SQLException
     */
    public Sessao buscarSessaoPorIdBO(int idSessao) throws SQLException {
        if (idSessao <= 0) {
            throw new IllegalArgumentException("O ID da sessão é inválido.");
        }

        return sessaoDAO.buscarPorId(idSessao);
    }
}
