package GS.model.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import GS.model.beans.DadosConsumo;
import GS.conexao.ConnectionFactory;

/**
 * Classe DadosConsumoDAO que realiza as operações CRUD para os registros de consumo de energia no banco de dados.
 */

public class DadosConsumoDAO {

    private Connection minhaConexao;

    /**
     * Construtor que inicializa a conexão com o banco de dados.
     *
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public DadosConsumoDAO() throws ClassNotFoundException, SQLException {
        this.minhaConexao = new ConnectionFactory().conexao();
    }

    /**
     * Insere um registro de consumo no banco de dados.
     *
     * @param dadosConsumo Objeto DadosConsumo com os dados do consumo.
     * @return Mensagem de sucesso.
     * @throws SQLException
     */
    public String inserir(DadosConsumo dadosConsumo) throws SQLException {
        String sql = "INSERT INTO TB_DADOS_CONSUMO (id_consumo, data_hora, consumo_energia) VALUES (?, ?, ?)";
        PreparedStatement stmt = minhaConexao.prepareStatement(sql);
        stmt.setInt(1, dadosConsumo.getIdConsumo());
        stmt.setObject(2, dadosConsumo.getDataHora());
        stmt.setFloat(3, dadosConsumo.getConsumoEnergia());
        stmt.execute();
        stmt.close();
        return "Dados de consumo cadastrados com sucesso!";
    }

    /**
     * Atualiza os dados de consumo no banco de dados.
     *
     * @param dadosConsumo Objeto DadosConsumo com os dados atualizados do consumo.
     * @return Mensagem de sucesso.
     * @throws SQLException
     */
    public String atualizar(DadosConsumo dadosConsumo) throws SQLException {
        String sql = "UPDATE TB_DADOS_CONSUMO SET data_hora = ?, consumo_energia = ? WHERE id_consumo = ?";
        PreparedStatement stmt = minhaConexao.prepareStatement(sql);
        stmt.setObject(1, dadosConsumo.getDataHora());
        stmt.setFloat(2, dadosConsumo.getConsumoEnergia());
        stmt.setInt(3, dadosConsumo.getIdConsumo());
        stmt.executeUpdate();
        stmt.close();
        return "Dados de consumo atualizados com sucesso!";
    }

    /**
     * Deleta um registro de consumo do banco de dados com base no ID.
     *
     * @param idConsumo ID do registro de consumo a ser deletado.
     * @return Mensagem de sucesso.
     * @throws SQLException
     */
    public String deletar(int idConsumo) throws SQLException {
        String sql = "DELETE FROM TB_DADOS_CONSUMO WHERE id_consumo = ?";
        PreparedStatement stmt = minhaConexao.prepareStatement(sql);
        stmt.setInt(1, idConsumo);
        stmt.execute();
        stmt.close();
        return "Dados de consumo deletados com sucesso!";
    }

    /**
     * Retorna uma lista com todos os registros de consumo do banco de dados.
     *
     * @return Lista de objetos DadosConsumo.
     * @throws SQLException
     */
    public List<DadosConsumo> selecionar() throws SQLException {
        List<DadosConsumo> listaDadosConsumo = new ArrayList<>();
        String sql = "SELECT * FROM TB_DADOS_CONSUMO";
        PreparedStatement stmt = minhaConexao.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            DadosConsumo dadosConsumo = new DadosConsumo();
            dadosConsumo.setIdConsumo(rs.getInt("id_consumo"));
            dadosConsumo.setDataHora(rs.getTimestamp("data_hora").toLocalDateTime());
            dadosConsumo.setConsumoEnergia(rs.getFloat("consumo_energia"));
            listaDadosConsumo.add(dadosConsumo);
        }
        stmt.close();
        return listaDadosConsumo;
    }

    /**
     * Busca um registro de consumo pelo ID.
     *
     * @param idConsumo ID do registro de consumo a ser buscado.
     * @return Objeto DadosConsumo, caso seja encontrado.
     * @throws SQLException
     */
    public DadosConsumo buscarPorId(int idConsumo) throws SQLException {
        String sql = "SELECT * FROM TB_DADOS_CONSUMO WHERE id_consumo = ?";
        PreparedStatement stmt = minhaConexao.prepareStatement(sql);
        stmt.setInt(1, idConsumo);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            DadosConsumo dadosConsumo = new DadosConsumo();
            dadosConsumo.setIdConsumo(rs.getInt("id_consumo"));
            dadosConsumo.setDataHora(rs.getTimestamp("data_hora").toLocalDateTime());
            dadosConsumo.setConsumoEnergia(rs.getFloat("consumo_energia"));
            stmt.close();
            return dadosConsumo;
        }
        stmt.close();
        return null;
    }
}

