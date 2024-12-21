# ALURA ONE G7: PRATICANDO SPRING BOOT
## Challenge LiterAlura


### SOBRE O DESAFIO

Desenvolvimento de um Catálogo de livros que oferece
interação textual (ou seja, via console) com os usuários,
nesta versão com 5 opções de interação:

* Buscar livro pelo título
* Listar livros registrados
* Listar autores registrados
* Listar autores vivos em determinado ano
* Listar livros em determinado idioma

#### Buscar livro pelo titulo

Usuário entra com titulo do livro. Os livros são buscados
através da API [Gutendex](https://gutendex.com/). Esta API
conta com catálogo com informações mais de 70 mil livros
presentes no [Project Gutenberg](https://www.gutenberg.org/).

Para mais informações sobre a API Gutendex:
* Link da API:🔗 [Gutendex](https://gutendex.com/)
* Repositório da API:🐙 [Github Gutendex](https://github.com/garethbjohnson/gutendex)

É feita uma requisição a API, que retorna uma lista
de livros, então usuário escolhe qual armazenar no banco de dados.

#### Listar livros registrados

Nessa opção são listados todos os livros armazenados no
banco de dados.

#### Listar autores registrados

Nessa opcão são listadas todos os autores armazenados no banco de dados.
Os autores são salvos ao pesquisar um titulo de livro.

#### Listar autores vivos em determinado ano

Usuário entra com determinado ano, então é retornado
todos os autores vivo daquele ano.

#### Listar livros em determinado Idioma

Retorna todos os livros de um determinado idioma escolhido pelo usuário.


#### CONFIGURAÇÕES DO AMBIENTE

* Java 17
* Maven (Versão 4)
* Spring Boot 
* Postgres

Dependências 

* Spring Data JPA
* Postgres Driver

#### VARIÁVEIS DE AMBIENTE

Para rodar esse projeto, você vai precisar adicionar
as seguintes variáveis de ambiente no seu sistema:

DB_URL

DB_USER

DB_PASSWORD
