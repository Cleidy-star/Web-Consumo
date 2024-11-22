package GS.resources;

import GS.model.beans.Sessao;
import GS.model.bo.SessaoBO;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;
import jakarta.ws.rs.core.UriInfo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@Path("/sessoes") // Definindo o caminho do recurso para sessões
public class SessaoResource {

    private SessaoBO sessaoBO;

    /**
     * Construtor que inicializa o BO de sessões.
     */
    public SessaoResource() {
        try {
            sessaoBO = new SessaoBO();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace(); // Ou lançar RuntimeException conforme necessário
        }
    }

    /**
     * Insere uma nova sessão no banco de dados.
     *
     * @param sessao Objeto Sessao contendo os dados da sessão.
     * @param uriInfo URI do recurso.
     * @return Resposta com o status de criação.
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws IOException
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response inserirSessao(Sessao sessao, @Context UriInfo uriInfo) throws ClassNotFoundException, SQLException, IOException {
        sessaoBO.iniciarSessaoBO(sessao.getUsuario());
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(Integer.toString(sessao.getIdSessao()));
        return Response.created(builder.build()).build();
    }

    /**
     * Atualiza os dados de uma sessão no banco de dados.
     *
     * @param sessao Objeto Sessao com os dados atualizados da sessão.
     * @param id ID da sessão.
     * @return Resposta com o status de atualização.
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws IOException
     */
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarSessao(Sessao sessao, @PathParam("id") int id) throws ClassNotFoundException, SQLException, IOException {
        sessao.setIdSessao(id);
        sessaoBO.finalizarSessaoBO(id);
        return Response.ok().build();
    }

    /**
     * Deleta uma sessão do banco de dados com base no ID.
     *
     * @param id ID da sessão a ser deletada.
     * @return Resposta com o status de exclusão.
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    @DELETE
    @Path("/{id}")
    public Response deletarSessao(@PathParam("id") int id) throws ClassNotFoundException, SQLException {
        sessaoBO.finalizarSessaoBO(id);
        return Response.ok().build();
    }

    /**
     * Retorna uma lista com todas as sessões do banco de dados.
     *
     * @return Lista de objetos Sessao em formato JSON.
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Sessao> listarSessoes() throws ClassNotFoundException, SQLException {
        return (ArrayList<Sessao>) sessaoBO.selecionarSessoesBO();
    }

    /**
     * Busca uma sessão pelo ID.
     *
     * @param id ID da sessão a ser buscada.
     * @return Objeto Sessao em formato JSON.
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Sessao buscarSessaoPorId(@PathParam("id") int id) throws ClassNotFoundException, SQLException {
        return sessaoBO.buscarSessaoPorIdBO(id);
    }
}


