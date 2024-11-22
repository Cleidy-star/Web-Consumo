package GS.model.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import GS.model.beans.Dispositivo;
import GS.conexao.ConnectionFactory;

/**
 * Classe DispositivoDAO que realiza as operações CRUD para os registros de dispositivos no banco de dados.
 */
public class DispositivoDAO {
    static Connection minhaConexao;

    /**
     * Construtor que inicializa a conexão com o banco de dados.
     *
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public DispositivoDAO() throws ClassNotFoundException, SQLException {
        this.minhaConexao = new ConnectionFactory().conexao();
    }

    /**
     * Insere um dispositivo no banco de dados.
     *
     * @param dispositivo Objeto Dispositivo com os dados do dispositivo.
     * @return Mensagem de sucesso.
     * @throws SQLException
     */
    public String inserir(Dispositivo dispositivo) throws SQLException {
        String sql = "INSERT INTO TB_DISPOSITIVO (id_dispositivo, nome_dispositivo, tipo_dispositivo, status, data_cadastro) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement stmt = minhaConexao.prepareStatement(sql);
        stmt.setInt(1, dispositivo.getIdDispositivo());
        stmt.setString(2, dispositivo.getNomeDispositivo());
        stmt.setString(3, dispositivo.getTipoDispositivo());
        stmt.setString(4, dispositivo.getStatus());
        stmt.setDate(5, new java.sql.Date(dispositivo.getDataCadastro().getTime()));
        stmt.execute();
        stmt.close();
        return "Dispositivo cadastrado com sucesso!";
    }

    /**
     * Atualiza os dados de um dispositivo no banco de dados.
     *
     * @param dispositivo Objeto Dispositivo com os dados atualizados do dispositivo.
     * @return Mensagem de sucesso.
     * @throws SQLException
     */
    public static String atualizar(Dispositivo dispositivo) throws SQLException {
        String sql = "UPDATE TB_DISPOSITIVO SET nome_dispositivo = ?, tipo_dispositivo = ?, status = ?, data_cadastro = ? WHERE id_dispositivo = ?";
        PreparedStatement stmt = minhaConexao.prepareStatement(sql);
        stmt.setString(1, dispositivo.getNomeDispositivo());
        stmt.setString(2, dispositivo.getTipoDispositivo());
        stmt.setString(3, dispositivo.getStatus());
        stmt.setDate(4, new java.sql.Date(dispositivo.getDataCadastro().getTime()));
        stmt.setInt(5, dispositivo.getIdDispositivo());
        stmt.executeUpdate();
        stmt.close();
        return "Dispositivo atualizado com sucesso!";
    }

    /**
     * Deleta um dispositivo do banco de dados com base no ID.
     *
     * @param idDispositivo ID do dispositivo a ser deletado.
     * @return Mensagem de sucesso.
     * @throws SQLException
     */
    public static String deletar(int idDispositivo) throws SQLException {
        String sql = "DELETE FROM TB_DISPOSITIVO WHERE id_dispositivo = ?";
        PreparedStatement stmt = minhaConexao.prepareStatement(sql);
        stmt.setInt(1, idDispositivo);
        stmt.execute();
        stmt.close();
        return "Dispositivo deletado com sucesso!";
    }

    /**
     * Retorna uma lista com todos os dispositivos do banco de dados.
     *
     * @return Lista de objetos Dispositivo.
     * @throws SQLException
     */
    public List<Dispositivo> selecionar() throws SQLException {
        List<Dispositivo> listaDispositivos = new ArrayList<>();
        String sql = "SELECT * FROM TB_DISPOSITIVO";
        PreparedStatement stmt = minhaConexao.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Dispositivo dispositivo = new Dispositivo();
            dispositivo.setIdDispositivo(rs.getInt("id_dispositivo"));
            dispositivo.setNomeDispositivo(rs.getString("nome_dispositivo"));
            dispositivo.setTipoDispositivo(rs.getString("tipo_dispositivo"));
            dispositivo.setStatus(rs.getString("status"));
            dispositivo.setDataCadastro(rs.getDate("data_cadastro"));
            listaDispositivos.add(dispositivo);
        }
        stmt.close();
        return listaDispositivos;
    }

    /**
     * Busca um dispositivo pelo ID.
     *
     * @param idDispositivo ID do dispositivo a ser buscado.
     * @return Objeto Dispositivo, caso seja encontrado.
     * @throws SQLException
     */
    public Dispositivo buscarPorId(int idDispositivo) throws SQLException {
        String sql = "SELECT * FROM TB_DISPOSITIVO WHERE id_dispositivo = ?";
        PreparedStatement stmt = minhaConexao.prepareStatement(sql);
        stmt.setInt(1, idDispositivo);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            Dispositivo dispositivo = new Dispositivo();
            dispositivo.setIdDispositivo(rs.getInt("id_dispositivo"));
            dispositivo.setNomeDispositivo(rs.getString("nome_dispositivo"));
            dispositivo.setTipoDispositivo(rs.getString("tipo_dispositivo"));
            dispositivo.setStatus(rs.getString("status"));
            dispositivo.setDataCadastro(rs.getDate("data_cadastro"));
            stmt.close();
            return dispositivo;
        }
        stmt.close();
        return null; // Caso não encontre o registro
    }
}


