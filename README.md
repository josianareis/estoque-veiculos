# Sistema Automotivo — Gestão de Estoque de Veículos

Projeto acadêmico da disciplina de Programação Orientada a Objetos. O sistema
permite gerenciar o estoque de uma loja de veículos por uma interface web
conectada a uma API REST.

## Funcionalidades

- Cadastrar marcas, modelos e veículos.
- Consultar veículos por marca, modelo, ano, faixa de preço e status.
- Atualizar o preço, a quilometragem e o status de um veículo.
- Excluir veículos do estoque com confirmação na tela.

## Tecnologias

- Java 17, Spring Boot e Spring Data JPA
- MySQL
- HTML, CSS e JavaScript
- Maven Wrapper

## Como executar no Windows

1. Tenha um JDK compatível com o projeto e o MySQL Server em execução. O
   `pom.xml` define o Java 17 como versão-alvo.
2. No MySQL Workbench, abra e execute o arquivo [banco/schema.sql](banco/schema.sql).
   Ele cria o banco `estoque_veiculos` e as tabelas `marca`, `modelo` e `veiculo`.
3. Abra o PowerShell na pasta do projeto, onde está o `pom.xml`. A configuração
   padrão usa o MySQL em `localhost:3306` com o usuário `root`. Digite a senha
   desse usuário quando o PowerShell solicitar.

   ```powershell
   $credencialBanco = Read-Host "Digite a senha do MySQL" -AsSecureString
   $env:DB_PASSWORD = [System.Net.NetworkCredential]::new("", $credencialBanco).Password
   .\mvnw.cmd spring-boot:run
   ```

4. Quando a aplicação iniciar, abra a
   [tela de estoque](http://localhost:8080/estoque.html).

Em um banco vazio, cadastre primeiro uma marca, depois um modelo vinculado
a ela e, por fim, um veículo desse modelo.

## Modelo físico do banco

O [diagrama do banco](banco/modelo-fisico.png) mostra como as tabelas de marca,
modelo e veículo se relacionam.
