- Sistema Automotivo — Gestão de Estoque de Veículos

Projeto acadêmico de Programação Orientada a Objetos. O sistema permite
gerenciar o estoque de uma loja de veículos por uma interface web
conectada a uma API REST.

- Funcionalidades

- Cadastrar marcas, modelos e veículos.
- Consultar veículos por marca, modelo, ano, faixa de preço e status.
- Atualizar preço, quilometragem e status de um veículo.
- Excluir veículos do estoque com confirmação na tela.

- Tecnologias

- Java e Spring Boot
- Spring Data JPA e MySQL
- HTML, CSS e JavaScript
- Maven Wrapper

- Como executar no Windows

1. Tenha um JDK compatível com o projeto e o MySQL em execução.
2. No MySQL Workbench, crie o banco se ele ainda não existir.

   ```sql
   CREATE DATABASE IF NOT EXISTS estoque_veiculos;
   ```

3. No PowerShell, abra a pasta que contém o `pom.xml`. Configure a senha
   do MySQL e inicie a aplicação no mesmo terminal.

   ```powershell
   $credencialBanco = Read-Host "Digite a senha do MySQL" -AsSecureString
   $env:DB_PASSWORD = [System.Net.NetworkCredential]::new("", $credencialBanco).Password
   .\mvnw.cmd spring-boot:run
   ```

4. Quando a aplicação iniciar, abra a
   [tela de estoque](http://localhost:8080/estoque.html).

Em um banco vazio, cadastre primeiro uma marca, depois um modelo
vinculado a ela e, por fim, um veículo desse modelo.
