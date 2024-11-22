package GS.resources;

import GS.model.beans.Dispositivo;
import GS.model.bo.DispositivoBO;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;
import jakarta.ws.rs.core.UriInfo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Classe DispositivoResource para fornecer APIs RESTful para manipulação de dispositivos.
 */
@Path("/dispositivos")
public class DispositivoResource {

    private DispositivoBO dispositivoBO;

    /**
     * Construtor que inicializa o BO de dispositivos.
     *
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public DispositivoResource() throws ClassNotFoundException, SQLException {
        dispositivoBO = new DispositivoBO();
    }

    /**
     * Insere um novo dispositivo no banco de dados.
     *
     * @param dispositivo Objeto Dispositivo contendo os dados do dispositivo.
     * @param uriInfo URI do recurso.
     * @return Resposta com o status de criação.
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws IOException
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response inserirDispositivo(Dispositivo dispositivo, @Context UriInfo uriInfo) throws ClassNotFoundException, SQLException, IOException {
        dispositivoBO.inserirDispositivoBO(dispositivo);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(Integer.toString(dispositivo.getIdDispositivo()));
        return Response.created(builder.build()).build();
    }

    /**
     * Atualiza os dados de um dispositivo no banco de dados.
     *
     * @param dispositivo Objeto Dispositivo com os dados atualizados do dispositivo.
     * @param id ID do dispositivo.
     * @return Resposta com o status de atualização.
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws IOException
     */
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarDispositivo(Dispositivo dispositivo, @PathParam("id") int id) throws ClassNotFoundException, SQLException, IOException {
        dispositivo.setIdDispositivo(id);
        dispositivoBO.atualizarDispositivoBO(dispositivo);
        return Response.ok().build();
    }

    /**
     * Deleta um dispositivo do banco de dados com base no ID.
     *
     * @param id ID do dispositivo a ser deletado.
     * @return Resposta com o status de exclusão.
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    @DELETE
    @Path("/{id}")
    public Response deletarDispositivo(@PathParam("id") int id) throws ClassNotFoundException, SQLException {
        dispositivoBO.deletarDispositivoBO(id);
        return Response.ok().build();
    }

    /**
     * Retorna uma lista com todos os dispositivos do banco de dados.
     *
     * @return Lista de objetos Dispositivo em formato JSON.
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Dispositivo> listarDispositivos() throws ClassNotFoundException, SQLException {
        return (ArrayList<Dispositivo>) dispositivoBO.selecionarDispositivosBO();
    }

    /**
     * Ativa um dispositivo com base no ID.
     *
     * @param id ID do dispositivo a ser ativado.
     * @return Resposta com o status de ativação.
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    @PUT
    @Path("/{id}/ativar")
    public Response ativarDispositivo(@PathParam("id") int id) throws ClassNotFoundException, SQLException {
        dispositivoBO.ativarDispositivoBO(id);
        return Response.ok().build();
    }

    /**
     * Desativa um dispositivo com base no ID.
     *
     * @param id ID do dispositivo a ser desativado.
     * @return Resposta com o status de desativação.
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    @PUT
    @Path("/{id}/desativar")
    public Response desativarDispositivo(@PathParam("id") int id) throws ClassNotFoundException, SQLException {
        dispositivoBO.desativarDispositivoBO(id);
        return Response.ok().build();
    }
}



