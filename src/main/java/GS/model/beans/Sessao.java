package GS.model.beans;

import javax.xml.bind.annotation.XmlRootElement;
import com.google.gson.annotations.SerializedName;
import java.time.LocalDateTime;
@XmlRootElement
public class Sessao {


private static int nextId = 1;

@SerializedName("id_sessao")
private int idSessao;

@SerializedName("sessao")
private int Sessao;

@SerializedName("inicio_sessao")
private LocalDateTime inicioSessao;

@SerializedName("fim_sessao")
private LocalDateTime fimSessao;

@SerializedName("usuario")
private Usuario usuario; // Adicionando um campo para armazenar o usuário

/**
 * Construtor que inicializa um objeto Sessao e define automaticamente o ID.
 */
public Sessao() {
    this.idSessao = nextId++;
}

/**
 * Retorna o ID da sessão.
 *
 * @return ID da sessão.
 */
public int getIdSessao() {
    return idSessao;
}

/**
 * Define o ID da sessão.
 *
 * @param idSessao Novo valor para o ID da sessão.
 */
public void setIdSessao(int idSessao) {
    this.idSessao = idSessao;
}

/**
 * Retorna o início da sessão.
 *
 * @return Início da sessão.
 */
public LocalDateTime getInicioSessao() {
    return inicioSessao;
}

/**
 * Define o início da sessão.
 *
 * @param inicioSessao Data e hora de início da sessão.
 */
public void setInicioSessao(LocalDateTime inicioSessao) {
    this.inicioSessao = inicioSessao;
}

/**
 * Retorna o fim da sessão.
 *
 * @return Fim da sessão.
 */
public LocalDateTime getFimSessao() {
    return fimSessao;
}

/**
 * Define o fim da sessão.
 *
 * @param fimSessao Data e hora de fim da sessão.
 */
public void setFimSessao(LocalDateTime fimSessao) {
    this.fimSessao = fimSessao;
}

/**
 * Retorna o usuário da sessão.
 *
 * @return Objeto Usuario associado à sessão.
 */
public Usuario getUsuario() {
    return usuario;
}

/**
 * Define o usuário da sessão.
 *
 * @param usuario Usuário associado à sessão.
 */
public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
}

/**
 * Inicia a sessão para um usuário especificado.
 *
 * @param usuario Usuário que inicia a sessão.
 */
public void iniciarSessao(Usuario usuario) {
    this.usuario = usuario;
    this.inicioSessao = LocalDateTime.now();
    System.out.println("Sessão iniciada para o usuário: " + usuario.getNome());
}

/**
 * Finaliza a sessão atual.
 */
public void finalizarSessao() {
    this.fimSessao = LocalDateTime.now();
    System.out.println("Sessão finalizada.");
}
}
