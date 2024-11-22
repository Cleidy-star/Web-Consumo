package GS.model.beans;

import javax.xml.bind.annotation.XmlRootElement;
import com.google.gson.annotations.SerializedName;
import java.util.Date;

/**
 * Classe Dispositivo representa um dispositivo com informações de nome, tipo, status e data de cadastro.
 * Esta classe utiliza anotações para serialização XML e JSON.
 */
@XmlRootElement
public class Dispositivo {
    private static int nextId = 1;

    @SerializedName("id_dispositivo")
    private int idDispositivo;

    @SerializedName("nome_dispositivo")
    private String nomeDispositivo;

    @SerializedName("tipo_dispositivo")
    private String tipoDispositivo;

    @SerializedName("status")
    private String status;

    @SerializedName("data_cadastro")
    private Date dataCadastro;

    /**
     * Construtor que inicializa um objeto Dispositivo com nome, tipo e status.
     * O ID é automaticamente definido e incrementado, e a data de cadastro é definida como a data atual.
     *
     * @param nomeDispositivo Nome do dispositivo.
     * @param tipoDispositivo Tipo do dispositivo.
     * @param status          Status do dispositivo.
     */
    public Dispositivo(String nomeDispositivo, String tipoDispositivo, String status) {
        this.idDispositivo = nextId++;
        this.nomeDispositivo = nomeDispositivo;
        this.tipoDispositivo = tipoDispositivo;
        this.status = status;
        this.dataCadastro = new Date();
    }

    /**
     * Construtor vazio que também define um ID automaticamente.
     * Usado principalmente para criação de instâncias sem dados iniciais.
     */
    public Dispositivo() {
        this.idDispositivo = nextId++;
        this.dataCadastro = new Date();
    }

    // Getters e Setters

    public int getIdDispositivo() {
        return idDispositivo;
    }

    public void setIdDispositivo(int idDispositivo) {
        this.idDispositivo = idDispositivo;
    }

    public String getNomeDispositivo() {
        return nomeDispositivo;
    }

    public void setNomeDispositivo(String nomeDispositivo) {
        this.nomeDispositivo = nomeDispositivo;
    }

    public String getTipoDispositivo() {
        return tipoDispositivo;
    }

    public void setTipoDispositivo(String tipoDispositivo) {
        this.tipoDispositivo = tipoDispositivo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    // Métodos adicionais

    /**
     * Ativa o dispositivo, definindo o status como "Ativo".
     */
    public void ativarDispositivo() {
        this.status = "Ativo";
    }

    /**
     * Desativa o dispositivo, definindo o status como "Inativo".
     */
    public void desativarDispositivo() {
        this.status = "Inativo";
    }

    /**
     * Atualiza as informações do dispositivo.
     * Pode ser utilizado para alterar nome, tipo ou outros detalhes relevantes.
     */
    public void atualizarInformacoes(String nomeDispositivo, String tipoDispositivo, String status) {
        this.nomeDispositivo = nomeDispositivo;
        this.tipoDispositivo = tipoDispositivo;
        this.status = status;
    }
}
