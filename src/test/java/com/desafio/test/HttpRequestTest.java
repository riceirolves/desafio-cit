package com.desafio.test;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class HttpRequestTest {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void successfulTest() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port
				+ "/cadastro?modalidade=Futebol&local=Maracanã&inicio=28/02/2018 20:30&termino=28/02/2018 21:30&pais1=Chile&pais2=Brasil&etapa=Oitavas de Final",
				String.class)).contains("Evento cadastrado com sucesso!");
	}
	
	@Test
	public void conflictTest() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port
				+ "/cadastro?modalidade=Futebol&local=Maracanã&inicio=28/02/2018 20:30&termino=28/02/2018 21:30&pais1=Chile&pais2=Brasil&etapa=Oitavas de Final",
				String.class)).contains("Horário conflitante com outro evento.");
	}

	@Test
	public void sameCountryEliminatoriasTest() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port
				+ "/cadastro?modalidade=Futebol&local=Maracanã&inicio=28/02/2018 20:30&termino=28/02/2018 21:30&pais1=Chile&pais2=Chile&etapa=Eliminatórias",
				String.class)).contains(
						"Somente nas etapas semifinais e finais é permitido ter equipes do mesmo país se enfrentando.");
	}

	@Test
	public void sameCountryOitavasDeFinalTest() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port
				+ "/cadastro?modalidade=Futebol&local=Maracanã&inicio=28/02/2018 20:30&termino=28/02/2018 21:30&pais1=Chile&pais2=Chile&etapa=Oitavas de Final",
				String.class)).contains(
						"Somente nas etapas semifinais e finais é permitido ter equipes do mesmo país se enfrentando.");
	}

	@Test
	public void sameCountryQuartasDeFinalTest() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port
				+ "/cadastro?modalidade=Futebol&local=Maracanã&inicio=28/02/2018 20:30&termino=28/02/2018 21:30&pais1=Chile&pais2=Chile&etapa=Quartas de Final",
				String.class)).contains(
						"Somente nas etapas semifinais e finais é permitido ter equipes do mesmo país se enfrentando.");
	}

	@Test
	public void sameCountrySemifinalTest() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port
				+ "/cadastro?modalidade=Xadrez&local=Maracanã&inicio=28/02/2018 20:30&termino=28/02/2018 21:30&pais1=Chile&pais2=Chile&etapa=Semifinal",
				String.class)).contains("Evento cadastrado com sucesso!");
	}

	@Test
	public void sameCountryFinalTest() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port
				+ "/cadastro?modalidade=100 Metros Rasos&local=Maracanã&inicio=28/02/2018 20:30&termino=28/02/2018 21:30&pais1=Chile&pais2=Chile&etapa=Final",
				String.class)).contains("Evento cadastrado com sucesso!");
	}

	@Test
	public void thirtyminutesAtLeastTest() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port
				+ "/cadastro?modalidade=Futebol&local=Maracanã&inicio=28/02/2018 20:30&termino=28/02/2018 20:45&pais1=Chile&pais2=Brasil&etapa=Oitavas de Final",
				String.class)).contains("A duração do evento deve ser de no mínimo 30 minutos.");
	}

	@Test
	public void missingModalidadeTest() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/cadastro", String.class))
				.contains("Por favor informe a modalidade.");
	}

	@Test
	public void missingLocalTest() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/cadastro?modalidade=Futebol",
				String.class)).contains("Por favor informe o local.");
	}

	@Test
	public void missingInicioTest() throws Exception {
		assertThat(this.restTemplate
				.getForObject("http://localhost:" + port + "/cadastro?modalidade=Futebol&local=Maracanã", String.class))
						.contains("Por favor informe o inicio do evento.");
	}

	@Test
	public void missingTerminoTest() throws Exception {
		assertThat(this.restTemplate.getForObject(
				"http://localhost:" + port + "/cadastro?modalidade=Futebol&local=Maracanã&inicio=28/02/2018 20:30",
				String.class)).contains("Por favor informe o término do evento.");
	}

	@Test
	public void missingPaisesTest1() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port
				+ "/cadastro?modalidade=Futebol&local=Maracanã&inicio=28/02/2018 20:30&termino=28/02/2018 21:30",
				String.class)).contains("Por favor informe os paises participantes.");
	}

	@Test
	public void missingPaisesTest2() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port
				+ "/cadastro?modalidade=Futebol&local=Maracanã&inicio=28/02/2018 20:30&termino=28/02/2018 21:30&pais1=Chile",
				String.class)).contains("Por favor informe os paises participantes.");
	}

	@Test
	public void missingPaisesTest3() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port
				+ "/cadastro?modalidade=Futebol&local=Maracanã&inicio=28/02/2018 20:30&termino=28/02/2018 21:30&pais2=Brasil",
				String.class)).contains("Por favor informe os paises participantes.");
	}

	@Test
	public void missingEtapaTest() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port
				+ "/cadastro?modalidade=Futebol&local=Maracanã&inicio=28/02/2018 20:30&termino=28/02/2018 21:30&pais1=Chile&pais2=Brasil",
				String.class)).contains("Por favor informe a etapa.");
	}

}
