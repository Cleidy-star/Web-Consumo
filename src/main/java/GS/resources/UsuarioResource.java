package GS.resources;

import GS.model.beans.Usuario;
import GS.model.bo.UsuarioBO;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;
import jakarta.ws.rs.core.UriInfo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@Path("/usuarios") // Definindo o caminho do recurso para usuários
public class UsuarioResource {

    private UsuarioBO usuarioBO;

    /**
     * Construtor que inicializa o BO de usuários.
     */
    public UsuarioResource() {
        try {
            usuarioBO = new UsuarioBO();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace(); // Ou lançar RuntimeException conforme necessário
        }
    }

    /**
     * Insere um novo usuário no banco de dados.
     *
     * @param usuario Objeto Usuario contendo os dados do usuário.
     * @param uriInfo URI do recurso.
     * @return Resposta com o status de criação.
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws IOException
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response inserirUsuario(Usuario usuario, @Context UriInfo uriInfo) throws ClassNotFoundException, SQLException, IOException {
        usuarioBO.inserirUsuarioBO(usuario);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(Integer.toString(usuario.getIdUsuario()));
        return Response.created(builder.build()).build();
    }

    /**
     * Atualiza os dados de um usuário no banco de dados.
     *
     * @param usuario Objeto Usuario com os dados atualizados do usuário.
     * @param id ID do usuário.
     * @return Resposta com o status de atualização.
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws IOException
     */
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarUsuario(Usuario usuario, @PathParam("id") int id) throws ClassNotFoundException, SQLException, IOException {
        usuario.setIdUsuario(id);
        usuarioBO.atualizarUsuarioBO(usuario);
        return Response.ok().build();
    }

    /**
     * Deleta um usuário do banco de dados com base no ID.
     *
     * @param id ID do usuário a ser deletado.
     * @return Resposta com o status de exclusão.
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    @DELETE
    @Path("/{id}")
    public Response deletarUsuario(@PathParam("id") int id) throws ClassNotFoundException, SQLException {
        usuarioBO.deletarUsuarioBO(id);
        return Response.ok().build();
    }

    /**
     * Retorna uma lista com todos os usuários do banco de dados.
     *
     * @return Lista de objetos Usuario em formato JSON.
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Usuario> listarUsuarios() throws ClassNotFoundException, SQLException {
        return (ArrayList<Usuario>) usuarioBO.selecionarUsuariosBO();
    }

    /**
     * Retorna um usuário com base no email.
     *
     * @param email Email do usuário a ser buscado.
     * @return Objeto Usuario, caso seja encontrado.
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @GET
    @Path("/email/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public Usuario buscarUsuarioPorEmail(@PathParam("email") String email) throws SQLException, ClassNotFoundException {
        return usuarioBO.fazerLoginBO(email, "");
    }
}
