package com.desafio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.DAO.EventDAO;
import com.desafio.model.Event;

@RestController
public class ConsultaController {

	@Autowired
	JdbcTemplate jdbcTemplate;

	EventDAO eventDAO = new EventDAO();

	@RequestMapping("/consulta")
	public String consulta(@RequestParam(value = "modalidade", defaultValue = "") String modalidade) {

		String result = "";
		List<Event> events = modalidade.isEmpty() ? eventDAO.getAll(jdbcTemplate) : eventDAO.getAll(modalidade, jdbcTemplate);
		if (events.size() == 0) {
			result = "Nenhum evento encontrado.";
		} else {
			result = eventDAO.buildResponse(events);
		}
		return result;
	}

}
