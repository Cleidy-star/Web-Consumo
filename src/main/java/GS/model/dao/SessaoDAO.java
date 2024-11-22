package GS.model.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import GS.model.beans.Sessao;
import GS.conexao.ConnectionFactory;

import static GS.model.dao.DispositivoDAO.minhaConexao;

/**
 * Classe SessaoDAO que realiza as operações CRUD para os registros de sessão no banco de dados.
 */
public class SessaoDAO {

    private final Connection minhaConexao;

    /**
     * Construtor que inicializa a conexão com o banco de dados.
     *
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public SessaoDAO() throws ClassNotFoundException, SQLException {
        this.minhaConexao = new ConnectionFactory().conexao();
    }

    /**
     * Insere uma sessão no banco de dados.
     *
     * @param sessao Objeto Sessao com os dados da sessão.
     * @return Mensagem de sucesso.
     * @throws SQLException
     */
    public String inserir(Sessao sessao) throws SQLException {
        String sql = "INSERT INTO TB_SESSAO (id_sessao, inicio_sessao, fim_sessao) VALUES (?, ?, ?)";
        PreparedStatement stmt = minhaConexao.prepareStatement(sql);
        stmt.setInt(1, sessao.getIdSessao());
        stmt.setObject(2, sessao.getInicioSessao());
        stmt.setObject(3, sessao.getFimSessao());
        stmt.execute();
        stmt.close();
        return "Sessão cadastrada com sucesso!";
    }

    /**
     * Atualiza os dados de uma sessão no banco de dados.
     *
     * @param sessao Objeto Sessao com os dados atualizados da sessão.
     * @return Mensagem de sucesso.
     * @throws SQLException
     */
    public String atualizar(Sessao sessao) throws SQLException {
        String sql = "UPDATE TB_SESSAO SET inicio_sessao = ?, fim_sessao = ? WHERE id_sessao = ?";
        PreparedStatement stmt = minhaConexao.prepareStatement(sql);
        stmt.setObject(1, sessao.getInicioSessao());
        stmt.setObject(2, sessao.getFimSessao());
        stmt.setInt(3, sessao.getIdSessao());
        stmt.executeUpdate();
        stmt.close();
        return "Sessão atualizada com sucesso!";
    }

    /**
     * Deleta uma sessão do banco de dados com base no ID.
     *
     * @param idSessao ID da sessão a ser deletada.
     * @return Mensagem de sucesso.
     * @throws SQLException
     */
    public String deletar(int idSessao) throws SQLException {
        String sql = "DELETE FROM TB_SESSAO WHERE id_sessao = ?";
        PreparedStatement stmt = minhaConexao.prepareStatement(sql);
        stmt.setInt(1, idSessao);
        stmt.execute();
        stmt.close();
        return "Sessão deletada com sucesso!";
    }

    /**
     * Retorna uma lista com todas as sessões do banco de dados.
     *
     * @return Lista de objetos Sessao.
     * @throws SQLException
     */
    public List<Sessao> selecionar() throws SQLException {
        List<Sessao> listaSessoes = new ArrayList<>();
        String sql = "SELECT * FROM TB_SESSAO";
        PreparedStatement stmt = minhaConexao.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Sessao sessao = new Sessao();
            sessao.setIdSessao(rs.getInt("id_sessao"));
            sessao.setInicioSessao(rs.getTimestamp("inicio_sessao") != null ? rs.getTimestamp("inicio_sessao").toLocalDateTime() : null);
            sessao.setFimSessao(rs.getTimestamp("fim_sessao") != null ? rs.getTimestamp("fim_sessao").toLocalDateTime() : null);
            listaSessoes.add(sessao);
        }
        stmt.close();
        return listaSessoes;
    }

    /**
     * Busca uma sessão pelo ID.
     *
     * @param idSessao ID da sessão a ser buscada.
     * @return Objeto Sessao, caso seja encontrado.
     * @throws SQLException
     */
    public Sessao buscarPorId(int idSessao) throws SQLException {
        String sql = "SELECT * FROM TB_SESSAO WHERE id_sessao = ?";
        PreparedStatement stmt = minhaConexao.prepareStatement(sql);
        stmt.setInt(1, idSessao);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            Sessao sessao = new Sessao();
            sessao.setIdSessao(rs.getInt("id_sessao"));
            sessao.setInicioSessao(rs.getTimestamp("inicio_sessao") != null ? rs.getTimestamp("inicio_sessao").toLocalDateTime() : null);
            sessao.setFimSessao(rs.getTimestamp("fim_sessao") != null ? rs.getTimestamp("fim_sessao").toLocalDateTime() : null);
            stmt.close();
            return sessao;
        }
        stmt.close();
        return null; // Caso não encontre o registro
    }
}

