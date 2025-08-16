# LiterAlura - Catálogo de Livros

## 📖 Descrição do Projeto

**LiterAlura** é uma aplicação de console desenvolvida como parte do Challenge Alura. O objetivo é criar um catálogo de livros interativo que consome a API pública [Gutendex](https://gutendex.com/) para buscar e registrar informações sobre livros e autores em um banco de dados local.

A aplicação permite ao usuário interagir através de um menu no console para realizar buscas, listar registros e fazer consultas específicas sobre os dados armazenados.

## ✨ Funcionalidades

O projeto oferece as seguintes opções:

1.  **Buscar Livro pelo Título:** Consulta a API Gutendex, exibe os detalhes do primeiro resultado encontrado e salva o livro, seus autores e idiomas no banco de dados.
2.  **Listar Livros Registrados:** Exibe todos os livros que foram salvos no banco de dados.
3.  **Listar Autores Registrados:** Mostra todos os autores que foram salvos, associados aos livros.
4.  **Listar Autores Vivos em Determinado Ano:** Pede um ano ao usuário e exibe uma lista de autores que estavam vivos naquele ano.
5.  **Listar Livros por Idioma:** Permite ao usuário buscar e listar todos os livros registrados em um idioma específico (ex: "pt" para português, "en" para inglês).

## 🛠️ Tecnologias Utilizadas

* **Java 17+**: Linguagem principal do projeto.
* **Spring Boot 3**: Framework para criação da aplicação, gerenciamento de dependências e configuração.
* **Spring Data JPA**: Para persistência de dados e comunicação com o banco de dados.
* **PostgreSQL**: Sistema de gerenciamento de banco de dados relacional utilizado para armazenar os dados.
* **Hibernate**: Implementação do JPA para mapeamento objeto-relacional (ORM).
* **Jackson Databind**: Biblioteca para conversão de dados entre JSON (da API) e objetos Java.
* **Java HTTP Client**: Para realizar as requisições à API Gutendex.
* **Maven**: Gerenciador de dependências e build do projeto.
* **Java Dotenv**: Biblioteca para carregar variáveis de ambiente a partir de um arquivo `.env`.

## ✅ Pré-requisitos

Antes de começar, você vai precisar ter instalado em sua máquina:

* [JDK](https://www.oracle.com/java/technologies/downloads/) (Java Development Kit) - Versão 17 ou superior.
* [Maven](https://maven.apache.org/download.cgi) - Versão 3.8 ou superior.
* [PostgreSQL](https://www.postgresql.org/download/) - Sistema de banco de dados.
* Uma IDE de sua preferência (ex: [IntelliJ IDEA](https://www.jetbrains.com/idea/), [VS Code](https://code.visualstudio.com/) com extensões Java).

## ⚙️ Configuração e Execução

1.  **Clone o repositório:**

    ```bash
    git clone https://Andrevictor20/challenge-LiterAlura.git
    cd challenge-LiterAlura
    ```

2.  **Configure o Banco de Dados:**

    * Crie um banco de dados no PostgreSQL. Por exemplo, `literalura_db`.
    * Não é necessário criar as tabelas; o Spring Data JPA/Hibernate fará isso automaticamente na primeira execução.

3.  **Crie o arquivo de variáveis de ambiente:**

    * Na raiz do projeto, crie um arquivo chamado `.env`.
    * Adicione as credenciais do seu banco de dados a este arquivo. O projeto está configurado para ler estas variáveis e configurar a conexão.

    **Exemplo de conteúdo do arquivo `.env`:**

    ```env
    DB_URL=jdbc:postgresql://localhost:5432/literalura_db
    DB_USER=seu_usuario_postgres
    DB_PASSWORD=sua_senha_postgres
    ```

4.  **Configure a aplicação Spring:**

    * Verifique se o arquivo `application.properties` está configurado para usar as variáveis de ambiente:

    <!-- end list -->

    ```properties
    spring.datasource.url=${DB_URL}
    spring.datasource.username=${DB_USER}
    spring.datasource.password=${DB_PASSWORD}
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true
    spring.jpa.properties.hibernate.format_sql=true
    ```

5.  **Execute a aplicação:**

    * Você pode executar o projeto diretamente pela sua IDE, localizando a classe `ChallengeLiterAluraApplication.java` e rodando o método `main`.
  
