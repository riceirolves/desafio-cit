# Desafio Java [Hands On]

## Como ver o projeto vivo diante dos teus olhos

Não é só apertar o play, mas é bem próximo disso

### Prerequisitos

Spring Tool Suite https://spring.io/tools

Importe como projeto Maven e BAAAAMM (Maven é tudo de pom!)

Agora apenas Run As Spring Boot App e vc tera os dois endpoints vivos na porta do seu servidor (classicamente na 8080 do tomcat)

Endpoints: cadastro e consulta

Acesse o endpoint de cadastro e tente cadastrar este evento:

/cadastro?modalidade=Futebol&local=Maracanã&inicio=28/02/2018 20:30&termino=28/02/2018 21:30&pais1=Chile&pais2=Brasil&etapa=Oitavas de Final

Depois este:

/cadastro?modalidade=Xadrez&local=Maracanã&inicio=28/02/2018 20:30&termino=28/02/2018 21:30&pais1=Chile&pais2=Brasil&etapa=Oitavas de Final

Depois acesse o endpoint consulta e veja os eventos cadastrados lá

## Executando os testes

O jeito mais simples e rápido para executar os testes automáticos é clicar no src/test/java/com/desafio/test/HttpRequestTest.java com o botão direito e selecionar Run As -> JUnit Test

### Intuito dos testes

Testar ambos endpoints sobre informações faltando, regras de negócio (como por exemplo um evento menor que 30 minutos) entre outros

## Autor

Ricardo Ribeiro Gonçalves, um programador java  holístico

## Licence

This project is licensed under the MIT License

