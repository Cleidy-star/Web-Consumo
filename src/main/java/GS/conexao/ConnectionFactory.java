package GS.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private static final String URL = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl";
    private static final String USER = "RM557158";
    private static final String PASSWORD = "fiap24";

    // Método para obter uma conexão com o banco de dados
    public static Connection conexao() throws SQLException {
        Connection connection = null;

        try {
            // Registrar o driver do Oracle
            Class.forName("oracle.jdbc.OracleDriver");

            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexão estabelecida com sucesso!");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Erro ao carregar o driver JDBC do Oracle", e);
        } catch (SQLException e) {
            throw new SQLException("Erro ao estabelecer a conexão com o banco de dados", e);
        }
        return connection;
    }

    // Método para fechar a conexão com o banco de dados
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Conexão fechada.");
            } catch (SQLException e) {
                System.err.println("Erro ao fechar a conexão: " + e.getMessage());
            }
        }
    }
}
