package GS.resources;

import GS.model.beans.DadosConsumo;
import GS.model.bo.DadosConsumoBO;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;
import jakarta.ws.rs.core.UriInfo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@Path("/dadosConsumo") // Definindo o caminho do recurso para dados de consumo
public class DadosConsumoResource {

    private DadosConsumoBO dadosConsumoBO;

    /**
     * Construtor que inicializa o BO de dados de consumo.
     */
    public DadosConsumoResource() {
        try {
            dadosConsumoBO = new DadosConsumoBO();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace(); // Ou lançar RuntimeException conforme necessário
        }
    }

    /**
     * Insere um novo registro de consumo no banco de dados.
     *
     * @param dadosConsumo Objeto DadosConsumo contendo os dados do consumo.
     * @param uriInfo URI do recurso.
     * @return Resposta com o status de criação.
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws IOException
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response inserirDadosConsumo(DadosConsumo dadosConsumo, @Context UriInfo uriInfo) throws ClassNotFoundException, SQLException, IOException {
        dadosConsumoBO.inserirDadosConsumoBO(dadosConsumo);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(Integer.toString(dadosConsumo.getIdConsumo()));
        return Response.created(builder.build()).build();
    }

    /**
     * Atualiza os dados de um registro de consumo no banco de dados.
     *
     * @param dadosConsumo Objeto DadosConsumo com os dados atualizados do consumo.
     * @param id ID do registro de consumo.
     * @return Resposta com o status de atualização.
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws IOException
     */
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarDadosConsumo(DadosConsumo dadosConsumo, @PathParam("id") int id) throws ClassNotFoundException, SQLException, IOException {
        dadosConsumo.setIdConsumo(id);
        dadosConsumoBO.atualizarDadosConsumoBO(dadosConsumo);
        return Response.ok().build();
    }

    /**
     * Deleta um registro de consumo do banco de dados com base no ID.
     *
     * @param id ID do registro de consumo a ser deletado.
     * @return Resposta com o status de exclusão.
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    @DELETE
    @Path("/{id}")
    public Response deletarDadosConsumo(@PathParam("id") int id) throws ClassNotFoundException, SQLException {
        dadosConsumoBO.deletarDadosConsumoBO(id);
        return Response.ok().build();
    }

    /**
     * Retorna uma lista com todos os registros de consumo do banco de dados.
     *
     * @return Lista de objetos DadosConsumo em formato JSON.
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<DadosConsumo> listarDadosConsumo() throws ClassNotFoundException, SQLException {
        return (ArrayList<DadosConsumo>) dadosConsumoBO.selecionarDadosConsumoBO();
    }

    /**
     * Busca um registro de consumo pelo ID.
     *
     * @param id ID do registro de consumo a ser buscado.
     * @return Objeto DadosConsumo em formato JSON.
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public DadosConsumo buscarDadosConsumoPorId(@PathParam("id") int id) throws ClassNotFoundException, SQLException {
        return dadosConsumoBO.buscarDadosConsumoPorIdBO(id);
    }
}



