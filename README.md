Sistema de Monitoramento de Consumo Energético Residencial
Este é um Sistema de Monitoramento de Consumo Energético Residencial desenvolvido em Java, que permite gerenciar dispositivos, monitorar consumo de energia e gerenciar sessões de usuários. O projeto utiliza o banco de dados Oracle para armazenar as informações.

🛠 Funcionalidades
CRUD (Create, Read, Update, Delete) para gerenciar:
Usuários: Criar, atualizar e deletar registros de usuários.
Dispositivos: Adicionar e gerenciar dispositivos residenciais.
Sessões: Monitorar sessões de login e logout.
Dados de Consumo: Registrar e consultar informações de consumo energético.
Integração com Oracle:
Conexão com o banco de dados Oracle para operações persistentes.
Gerenciamento de Tabelas:
Estrutura de tabelas relacionada a usuários, dispositivos, sessões e consumo de energia.
Opção de Relatórios:
Dados podem ser exportados para JSON, Excel ou CSV.
🚀 Tecnologias Utilizadas
Java 17+
Banco de Dados Oracle:
Conexão via Oracle JDBC Driver.
Maven:
Gerenciador de dependências.
Bibliotecas:
ojdbc8.jar (Driver JDBC do Oracle)
Outros frameworks necessários para integração e manipulação.
💾 Estrutura do Banco de Dados
O projeto utiliza as seguintes tabelas no banco de dados Oracle:

TB_USUARIO:

id_usuario: ID único do usuário.
nome: Nome do usuário.
email: Email do usuário.
senha: Senha do usuário.
data_cadastro: Data de cadastro.
TB_SESSAO:

id_sessao: ID da sessão.
inicio_sessao: Timestamp do início da sessão.
fim_sessao: Timestamp do fim da sessão.
id_usuario: Relacionado ao usuário.
TB_DISPOSITIVO:

id_dispositivo: ID único do dispositivo.
nome_dispositivo: Nome do dispositivo.
tipo_dispositivo: Tipo do dispositivo.
status: Status do dispositivo.
data_cadastro: Data de cadastro.
TB_DADOS_DE_CONSUMO:

id_consumo: ID único do consumo.
data_hora: Timestamp do consumo.
consumo_energia: Valor consumido em kWh.
id_dispositivo: Relacionado ao dispositivo.
