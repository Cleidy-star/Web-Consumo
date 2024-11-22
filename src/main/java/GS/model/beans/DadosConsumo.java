package GS.model.beans;
import javax.xml.bind.annotation.XmlRootElement;
import com.google.gson.annotations.SerializedName;
import java.time.LocalDateTime;

    /**
     * Classe DadosConsumo representa o consumo de energia em um determinado momento.
     * Esta classe utiliza anotações para serialização XML e JSON.
     */
    @XmlRootElement
    public class DadosConsumo {

        private static int nextId = 1;

        @SerializedName("id_consumo")
        private int idConsumo;

        @SerializedName("data_hora")
        private LocalDateTime dataHora;

        @SerializedName("consumo_energia")
        private float consumoEnergia;

        /**
         * Construtor que inicializa um objeto DadosConsumo com o consumo de energia.
         * O ID é automaticamente definido e incrementado.
         *
         * @param consumoEnergia Consumo de energia registrado.
         */
        public DadosConsumo(float consumoEnergia) {
            this.idConsumo = nextId++; // Define o ID automaticamente e incrementa
            this.dataHora = LocalDateTime.now(); // Define a data e hora atual
            this.consumoEnergia = consumoEnergia;
        }

        /**
         * Construtor vazio que também define um ID automaticamente.
         * Usado principalmente para criação de instâncias sem dados iniciais.
         */
        public DadosConsumo() {
            this.idConsumo = nextId++;
            this.dataHora = LocalDateTime.now();
        }

        /**
         * Retorna o ID do consumo.
         *
         * @return ID do consumo.
         */
        public int getIdConsumo() {
            return idConsumo;
        }

        /**
         * Define o ID do consumo.
         *
         * @param idConsumo Novo valor para o ID do consumo.
         */
        public void setIdConsumo(int idConsumo) {
            this.idConsumo = idConsumo;
        }

        /**
         * Retorna a data e hora do registro de consumo.
         *
         * @return Data e hora do registro de consumo.
         */
        public LocalDateTime getDataHora() {
            return dataHora;
        }

        /**
         * Define a data e hora do registro de consumo.
         *
         * @param dataHora Nova data e hora do registro de consumo.
         */
        public void setDataHora(LocalDateTime dataHora) {
            this.dataHora = dataHora;
        }

        /**
         * Retorna o consumo de energia registrado.
         *
         * @return Consumo de energia.
         */
        public float getConsumoEnergia() {
            return consumoEnergia;
        }

        /**
         * Define o consumo de energia registrado.
         *
         * @param consumoEnergia Novo valor para o consumo de energia.
         */
        public void setConsumoEnergia(float consumoEnergia) {
            this.consumoEnergia = consumoEnergia;
        }

        /**
         * Registra um novo valor de consumo de energia.
         *
         * @param consumoEnergia Consumo de energia a ser registrado.
         */
        public void registrarConsumo(float consumoEnergia) {
            this.consumoEnergia = consumoEnergia;
            this.dataHora = LocalDateTime.now(); // Atualiza a data e hora para o momento do registro
        }
    }
