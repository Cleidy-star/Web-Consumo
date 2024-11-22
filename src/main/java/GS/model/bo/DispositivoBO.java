package GS.model.bo;
import GS.model.beans.Dispositivo;
import GS.model.dao.DispositivoDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Classe DispositivoBO (Business Object) aplica as regras de negócio para a manipulação de dispositivos,
 * incluindo validações e acesso ao DAO para inserção, atualização, exclusão e seleção de dispositivos.
 */
public class DispositivoBO {
    private final DispositivoDAO dispositivoDAO;

    /**
     * Construtor que inicializa o DAO do dispositivo.
     *
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public DispositivoBO() throws ClassNotFoundException, SQLException {
        this.dispositivoDAO = new DispositivoDAO();
    }

    /**
     * Insere um dispositivo no banco de dados após aplicar as regras de negócio.
     *
     * @param dispositivo Objeto Dispositivo contendo os dados do dispositivo.
     * @throws IllegalArgumentException se as validações de regras de negócio falharem.
     * @throws SQLException
     * @throws IOException
     */
    public void inserirDispositivoBO(Dispositivo dispositivo) throws ClassNotFoundException, SQLException, IOException {
        // Validar nome do dispositivo
        if (dispositivo.getNomeDispositivo() == null || dispositivo.getNomeDispositivo().isEmpty()) {
            throw new IllegalArgumentException("O nome do dispositivo não pode ser vazio.");
        }

        // Validar tipo do dispositivo
        if (dispositivo.getTipoDispositivo() == null || dispositivo.getTipoDispositivo().isEmpty()) {
            throw new IllegalArgumentException("O tipo do dispositivo não pode ser vazio.");
        }

        // Inserir dispositivo via DAO
        dispositivoDAO.inserir(dispositivo);
    }

    /**
     * Atualiza um dispositivo no banco de dados após aplicar as regras de negócio.
     *
     * @param dispositivo Objeto Dispositivo contendo os dados do dispositivo.
     * @throws IllegalArgumentException se as validações de regras de negócio falharem.
     * @throws SQLException
     * @throws IOException
     */
    public void atualizarDispositivoBO(Dispositivo dispositivo) throws ClassNotFoundException, SQLException, IOException {
        // Validar nome do dispositivo
        if (dispositivo.getNomeDispositivo() == null || dispositivo.getNomeDispositivo().isEmpty()) {
            throw new IllegalArgumentException("O nome do dispositivo não pode ser vazio.");
        }

        // Validar tipo do dispositivo
        if (dispositivo.getTipoDispositivo() == null || dispositivo.getTipoDispositivo().isEmpty()) {
            throw new IllegalArgumentException("O tipo do dispositivo não pode ser vazio.");
        }

        // Atualizar dispositivo via DAO
        DispositivoDAO.atualizar(dispositivo);
    }

    /**
     * Deleta um dispositivo do banco de dados com base no ID.
     *
     * @param idDispositivo ID do dispositivo a ser deletado.
     * @throws IllegalArgumentException se o ID for inválido.
     * @throws SQLException
     */
    public void deletarDispositivoBO(int idDispositivo) throws ClassNotFoundException, SQLException {
        if (idDispositivo <= 0) {
            throw new IllegalArgumentException("O ID do dispositivo é inválido.");
        }

        // Deletar dispositivo via DAO
        DispositivoDAO.deletar(idDispositivo);
    }

    /**
     * Retorna uma lista de todos os dispositivos disponíveis no banco de dados.
     *
     * @return Lista de objetos Dispositivo.
     * @throws SQLException
     */
    public List<Dispositivo> selecionarDispositivosBO() throws ClassNotFoundException, SQLException {
        // Apenas chama o DAO para listar todos os dispositivos
        return dispositivoDAO.selecionar();
    }

    /**
     * Ativa um dispositivo com base no ID.
     *
     * @param idDispositivo ID do dispositivo a ser ativado.
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void ativarDispositivoBO(int idDispositivo) throws ClassNotFoundException, SQLException {
        Dispositivo dispositivo = dispositivoDAO.buscarPorId(idDispositivo);

        if (dispositivo != null) {
            dispositivo.ativarDispositivo();
            dispositivoDAO.atualizar(dispositivo);
        } else {
            throw new IllegalArgumentException("Dispositivo não encontrado para ativar.");
        }
    }

    /**
     * Desativa um dispositivo com base no ID.
     *
     * @param idDispositivo ID do dispositivo a ser desativado.
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void desativarDispositivoBO(int idDispositivo) throws ClassNotFoundException, SQLException {
        Dispositivo dispositivo = dispositivoDAO.buscarPorId(idDispositivo);

        if (dispositivo != null) {
            dispositivo.desativarDispositivo();
            dispositivoDAO.atualizar(dispositivo);
        } else {
            throw new IllegalArgumentException("Dispositivo não encontrado para desativar.");
        }
    }
}

