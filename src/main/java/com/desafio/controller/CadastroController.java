package com.desafio.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.DAO.EventDAO;
import com.desafio.model.Event;

@RestController
public class CadastroController {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@RequestMapping("/cadastro")
	public String cadastro(@RequestParam(value = "modalidade", defaultValue = "") String modalidade,
			@RequestParam(value = "local", defaultValue = "") String local,
			@RequestParam(value = "inicio", defaultValue = "") String inicio,
			@RequestParam(value = "termino", defaultValue = "") String termino,
			@RequestParam(value = "pais1", defaultValue = "") String pais1,
			@RequestParam(value = "pais2", defaultValue = "") String pais2,
			@RequestParam(value = "etapa", defaultValue = "") String etapa) {
		LocalDateTime inicioConverted;
		LocalDateTime terminoConverted;
		EventDAO eventDAO = new EventDAO();
		List<String> validEtapas = eventDAO.getValidEtapas();
		String example = "<br /> <br /> Exemplo: /cadastro?modalidade=Futebol&local=Maracanã&inicio=28/02/2018 20:30&termino=28/02/2018 21:30&pais1=Chile&pais2=Brasil&etapa=Oitavas de Final";

		if (modalidade.equals(""))
			return "Por favor informe a modalidade." + example;
		if (local.equals(""))
			return "Por favor informe o local." + example;
		if (inicio.equals(""))
			return "Por favor informe o inicio do evento." + example;
		if (termino.equals(""))
			return "Por favor informe o término do evento." + example;
		if (pais1.equals("") || pais2.equals(""))
			return "Por favor informe os paises participantes." + example;
		if (etapa.equals("")) {
			return "Por favor informe a etapa." + example;
		} else if (!validEtapas.contains(etapa)) {
			return "Por favor informe uma etapa dentre as opções válidas: Eliminatórias, Oitavas de Final, Quartas de Final, Semifinal e Final.";
		}else if(pais1.equalsIgnoreCase(pais2) && !etapa.equals("Semifinal") && !etapa.equals("Final")) {
			return "Somente nas etapas semifinais e finais é permitido ter equipes do mesmo país se enfrentando.";
		}

		
		
		try {
			inicioConverted = LocalDateTime.parse(inicio, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
			terminoConverted = LocalDateTime.parse(termino, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
		} catch (Exception e) {
			return "Por favor informe a data e hora neste formato: "
					+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")) + ".";
		}
		
		

		Event event = new Event(modalidade, local, inicioConverted, terminoConverted, pais1, pais2, etapa);

		if (event.getInicio().until(event.getTermino(), ChronoUnit.MINUTES) < 30)
			return "A duração do evento deve ser de no mínimo 30 minutos.";

		if (eventDAO.verifyConflict(event, jdbcTemplate))
			return "Horário conflitante com outro evento.";

		if (eventDAO.countEventPerDay(event, jdbcTemplate) == 4)
			return "Este local (" + local + ") alcançou seu limite de 4 competições por dia.";

		eventDAO.save(event, jdbcTemplate);

		return "Evento cadastrado com sucesso!<br /> <br />" + eventDAO.buildResponse(event);
	}

}
