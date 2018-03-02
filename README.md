# Desafio Java [Hands On]

## Como ver o projeto vivo diante dos teus olhos

N�o � s� apertar o play, mas � bem pr�ximo disso

### Prerequisitos

Spring Tool Suite https://spring.io/tools

Importe como projeto Maven e BAAAAMM (Maven � tudo de pom!)

Agora apenas Run As Spring Boot App e vc tera os dois endpoints vivos na porta do seu servidor (classicamente na 8080 do tomcat)

Endpoints: cadastro e consulta

Acesse o endpoint de cadastro e tente cadastrar este evento:

/cadastro?modalidade=Futebol&local=Maracan�&inicio=28/02/2018 20:30&termino=28/02/2018 21:30&pais1=Chile&pais2=Brasil&etapa=Oitavas de Final

Depois este:

/cadastro?modalidade=Xadrez&local=Maracan�&inicio=28/02/2018 20:30&termino=28/02/2018 21:30&pais1=Chile&pais2=Brasil&etapa=Oitavas de Final

Depois acesse o endpoint consulta e veja os eventos cadastrados l�

## Executando os testes

O jeito mais simples e r�pido para executar os testes autom�ticos � clicar no src/test/java/com/desafio/test/HttpRequestTest.java com o bot�o direito e selecionar Run As -> JUnit Test

### Intuito dos testes

Testar ambos endpoints sobre informa��es faltando, regras de neg�cio (como por exemplo um evento menor que 30 minutos) entre outros

## Autor

Ricardo Ribeiro Gon�alves, um programador java  hol�stico

## Licence

This project is licensed under the MIT License

