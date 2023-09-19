# <h1 align="center"> Desafio da Sys Manager! - CRUD Java </h1>
![GitHub repo size](https://img.shields.io/github/repo-size/PedroQueiroz1/DesafioCRUDSysManagerJDBC?style=plastic)
![GitHub last commit](https://img.shields.io/github/last-commit/PedroQueiroz1/DesafioCRUDSysManagerJDBC?style=plastic)

<p align="center">
   <img src="http://img.shields.io/static/v1?label=STATUS&message=Finalizado&color=RED&style=for-the-badge" #vitrinedev/>
</p>

### Tópicos 

- [Descrição do projeto](#descrição-do-projeto)
- [Funcionalidades](#funcionalidades)
- [Aplicação](#aplicação)
- [Ferramentas utilizadas](#ferramentas-utilizadas)
- [Acesso ao projeto](#acesso-ao-projeto)

## Descrição do projeto 
<br>Explicação de como funciona o projeto:
<br>Nesse projeto temos 5 classes: "Author", "Product", "Book", "Movie", "Review"
<br>
<br>>O autor é quem realiza os comentários(reviews) nos produtos(book,movie).
<br>>Autor não tem relação nenhuma com outra classe a nao ser realizar comentários(review) nos produtos.
<br>>Produto é dividido entre livros e filmes.
<br>Análise é feita em um produto específico. O produto pode receber várias análises.
<br><br>>É possível Criar, listar, atualizar e deletar das tabelas: 'author', 'book', 'movie', 'review'.

<p align="justify">
 Projeto criado durante o Primeiro Workshop da Sys Academy. 2 Projetos em 1.<br>
 Primeiro desafio: Criar um CRUD com o console sendo a interface de interação com o usuário. Obrigatório o uso do banco de dados e de pelo menos um relacionamento de 1..N<br>
 Segundo desafio: Reutilizar o código do primeiro desafio e criar uma interface mais amigável para o usuário.<br>

<br>

### Foi o primeiro projeto que criei com JDBC, não conhecia nem a estrutura de pastas, ESTÁ BAGUNÇADO rsrs

## Funcionalidades

:heavy_check_mark: `Funcionalidade 1:` Cria, atualiza, deleta e lê todos os dados de cada tabela presente no projeto(Autor, Avaliações, Livro e Filme)

:heavy_check_mark: `Funcionalidade 2:` Armazenar os dados de cada tabela no banco de dados MySQL.

:heavy_check_mark: `Curiosidade:` Quando um Autor é deletado, também deleta todos os comentários feitos pelo mesmo autor.


## Aplicação

<div align="center">

![Sistema funcionando](https://github.com/PedroQueiroz1/DesafioCRUDSysManagerJDBC/blob/main/media/DesafioJavaJDBC.gif)

</div>

## Ferramentas utilizadas
[![My Skills](https://skillicons.dev/icons?i=java,html,bootstrap,mysql,eclipse)](https://skillicons.dev)

<br><br>Nesse projeto eu utilizo: Java 1.8, JDBC, JSF, Primefaces, MySQL connector 5.1, Tomcat 9.0

## Acesso ao projeto

Você pode [acessar o código fonte do projeto](https://github.com/PedroQueiroz1/DesafioCRUDSysManagerJDBC) ou [baixá-lo](https://drive.google.com/file/d/1D5_n9I-IZVTz4jHPnp2wsiCNiHTe2IdX/view?usp=share_link).
