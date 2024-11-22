package GS.model.beans;

import javax.xml.bind.annotation.XmlRootElement;
import com.google.gson.annotations.SerializedName;
import java.util.Date;

/**
 * Classe Usuario representa um usuário com informações de nome, email, senha e data de cadastro.
 * Esta classe utiliza anotações para serialização XML e JSON.
 */
@XmlRootElement
public class Usuario {
    private static int nextId = 1;

    @SerializedName("id_usuario")
    private int idUsuario;

    @SerializedName("nome")
    private String nome;

    @SerializedName("email")
    private String email;

    @SerializedName("senha")
    private String senha;

    @SerializedName("data_cadastro")
    private Date dataCadastro;

    /**
     * Construtor que inicializa um objeto Usuario com nome, email e senha.
     * O ID é automaticamente definido e incrementado, e a data de cadastro é definida como a data atual.
     *
     * @param nome  Nome do usuário.
     * @param email Email do usuário.
     * @param senha Senha do usuário.
     */
    public Usuario(String nome, String email, String senha) {
        this.idUsuario = nextId++;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.dataCadastro = new Date();
    }

    /**
     * Construtor vazio que também define um ID automaticamente e a data de cadastro como a data atual.
     * Usado principalmente para criação de instâncias sem dados iniciais.
     */
    public Usuario() {
        this.idUsuario = nextId++;
        this.dataCadastro = new Date();
    }

    /**
     * Retorna o ID do usuário.
     *
     * @return ID do usuário.
     */
    public int getIdUsuario() {
        return idUsuario;
    }

    /**
     * Define o ID do usuário.
     *
     * @param idUsuario Novo valor para o ID do usuário.
     */
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * Retorna o nome do usuário.
     *
     * @return Nome do usuário.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome do usuário.
     *
     * @param nome Novo nome do usuário.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Retorna o email do usuário.
     *
     * @return Email do usuário.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Define o email do usuário.
     *
     * @param email Novo email do usuário.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Retorna a senha do usuário.
     *
     * @return Senha do usuário.
     */
    public String getSenha() {
        return senha;
    }

    /**
     * Define a senha do usuário.
     *
     * @param senha Nova senha do usuário.
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }

    /**
     * Retorna a data de cadastro do usuário.
     *
     * @return Data de cadastro do usuário.
     */
    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }



    /**
     * Método para realizar login do usuário.
     *
     * @param email Email do usuário.
     * @param senha Senha do usuário.
     * @return True se as credenciais forem válidas, caso contrário false.
     */
    public boolean fazerLogin(String email, String senha) {
        return this.email.equals(email) && this.senha.equals(senha);
    }

    /**
     * Método para cadastrar o usuário.
     */
    public void cadastrar() {
        // Implementação para cadastro do usuário
    }

}

