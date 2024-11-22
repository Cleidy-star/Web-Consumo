package GS.model.bo;


import GS.model.beans.DadosConsumo;
import GS.model.dao.DadosConsumoDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Classe DadosConsumoBO (Business Object) aplica as regras de negócio para a manipulação dos registros de consumo de energia,
 * incluindo validações e acesso ao DAO para inserção, atualização e seleção dos registros.
 */

public class DadosConsumoBO {
    private DadosConsumoDAO dadosConsumoDAO;

    /**
     * Construtor que inicializa o DAO dos dados de consumo.
     *
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public DadosConsumoBO() throws ClassNotFoundException, SQLException {
        this.dadosConsumoDAO = new DadosConsumoDAO();
    }

    /**
     * Insere um registro de consumo no banco de dados após aplicar as regras de negócio.
     *
     * @param dadosConsumo Objeto DadosConsumo contendo os dados do consumo.
     * @throws IllegalArgumentException se as validações de regras de negócio falharem.
     * @throws SQLException
     * @throws IOException
     */
    public void inserirDadosConsumoBO(DadosConsumo dadosConsumo) throws ClassNotFoundException, SQLException, IOException {
        // Validar consumo de energia
        if (dadosConsumo.getConsumoEnergia() <= 0) {
            throw new IllegalArgumentException("O valor do consumo de energia deve ser positivo.");
        }

        // Inserir dados de consumo via DAO
        dadosConsumoDAO.inserir(dadosConsumo);
    }

    /**
     * Atualiza um registro de consumo no banco de dados após aplicar as regras de negócio.
     *
     * @param dadosConsumo Objeto DadosConsumo contendo os dados atualizados do consumo.
     * @throws IllegalArgumentException se as validações de regras de negócio falharem.
     * @throws SQLException
     * @throws IOException
     */
    public void atualizarDadosConsumoBO(DadosConsumo dadosConsumo) throws ClassNotFoundException, SQLException, IOException {
        // Validar consumo de energia
        if (dadosConsumo.getConsumoEnergia() <= 0) {
            throw new IllegalArgumentException("O valor do consumo de energia deve ser positivo.");
        }

        // Atualizar dados de consumo via DAO
        dadosConsumoDAO.atualizar(dadosConsumo);
    }

    /**
     * Deleta um registro de consumo do banco de dados com base no ID.
     *
     * @param idConsumo ID do registro de consumo a ser deletado.
     * @throws IllegalArgumentException se o ID for inválido.
     * @throws SQLException
     */
    public void deletarDadosConsumoBO(int idConsumo) throws ClassNotFoundException, SQLException {
        if (idConsumo <= 0) {
            throw new IllegalArgumentException("O ID do registro de consumo é inválido.");
        }

        // Deletar registro de consumo via DAO
        dadosConsumoDAO.deletar(idConsumo);
    }

    /**
     * Retorna uma lista de todos os registros de consumo disponíveis no banco de dados.
     *
     * @return Lista de objetos DadosConsumo.
     * @throws SQLException
     */
    public List<DadosConsumo> selecionarDadosConsumoBO() throws ClassNotFoundException, SQLException {
        // Apenas chama o DAO para listar todos os registros de consumo
        return dadosConsumoDAO.selecionar();
    }

    /**
     * Busca um registro de consumo pelo ID.
     *
     * @param idConsumo ID do registro de consumo a ser buscado.
     * @return Objeto DadosConsumo, caso seja encontrado.
     * @throws SQLException
     */
    public DadosConsumo buscarDadosConsumoPorIdBO(int idConsumo) throws ClassNotFoundException, SQLException {
        if (idConsumo <= 0) {
            throw new IllegalArgumentException("O ID do registro de consumo é inválido.");
        }

        return dadosConsumoDAO.buscarPorId(idConsumo);
    }
}


