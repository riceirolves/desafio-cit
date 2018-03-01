package com.desafio.DAO;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.desafio.model.Event;

public class EventDAO {
	
	@SuppressWarnings("serial")
	List<String> validEtapas = new ArrayList<String>() {
		{
			add("Eliminatórias");
			add("Oitavas de Final");
			add("Quartas de Final");
			add("Semifinal");
			add("Final");
		}
	};
	
	public String buildResponse(List<Event> events) {
		String result = "";
		for (int i = 0; i < events.size(); i++) {
			result += "Modalidade: " + events.get(i).getModalidade() + ", ";
			result += "Local: " + events.get(i).getLocal() + ", ";
			result += "Dia: " + events.get(i).getInicio().getDayOfMonth() + "/" + events.get(i).getInicio().getMonthValue() + "/" + events.get(i).getInicio().getYear() + " " ;
			result += "das " + events.get(i).getInicio().getHour() + ":" + events.get(i).getInicio().getMinute() + " ";
			result += "as " + events.get(i).getTermino().getHour() + ":" + events.get(i).getTermino().getMinute() + ", ";
			result += "Paises: " + events.get(i).getPais1() + " VS " + events.get(i).getPais2() + ", ";
			result += "Etapa: " + events.get(i).getEtapa() + ".";
			result += "<br />";
		}

		return result;
	}
	
	public String buildResponse(Event event) {
		String result = "";
			result += "Modalidade: " + event.getModalidade() + ", ";
			result += "Local: " + event.getLocal() + ", ";
			result += "Dia: " + event.getInicio().getDayOfMonth() + "/" + event.getInicio().getMonthValue() + "/" + event.getInicio().getYear() + " " ;
			result += "das " + event.getInicio().getHour() + ":" + event.getInicio().getMinute() + " ";
			result += "as " + event.getTermino().getHour() + ":" + event.getTermino().getMinute() + ", ";
			result += "Paises: " + event.getPais1() + " VS " + event.getPais2() + ", ";
			result += "Etapa: " + event.getEtapa() + ".";
			result += "<br />";
		

		return result;
	}
	
	public boolean verifyConflict(Event event, JdbcTemplate jdbcTemplate) {
		
		return jdbcTemplate.queryForObject("SELECT count(*) FROM event WHERE local = ? AND modalidade = ? AND ((inicio > ? AND inicio < ? OR termino > ? AND termino < ?) OR (inicio = ? AND termino = ?))",
				new Object[] { event.getLocal(), event.getModalidade(), Timestamp.valueOf(event.getInicio()),
						Timestamp.valueOf(event.getTermino()), Timestamp.valueOf(event.getInicio()),
						Timestamp.valueOf(event.getTermino()), Timestamp.valueOf(event.getInicio()),
						Timestamp.valueOf(event.getTermino()) },
				Integer.class) > 0;
	}
	
	public int countEventPerDay(Event event, JdbcTemplate jdbcTemplate) {

		return jdbcTemplate.queryForObject("SELECT count(*) FROM event WHERE local = ? AND inicio > ? and inicio < ?",
				new Object[] { event.getLocal(), Timestamp.valueOf(event.getInicio().withHour(0).withMinute(0).withSecond(0)), Timestamp.valueOf(event.getInicio().withHour(23).withMinute(59).withSecond(59))},
				Integer.class);
	}
	
	public boolean save(Event event, JdbcTemplate jdbcTemplate) {
		jdbcTemplate.batchUpdate("INSERT INTO event(modalidade, local, inicio, termino, pais1, pais2, etapa) VALUES ('"
				+ event.getModalidade() + "','" + event.getLocal() + "','" + Timestamp.valueOf(event.getInicio()) + "','"
				+ Timestamp.valueOf(event.getTermino()) + "','" + event.getPais1() + "','" + event.getPais2() + "','" + event.getEtapa() + "')");
		return true;
	}

	public List<Event> getAll(JdbcTemplate jdbcTemplate) {
		String sql = "SELECT modalidade, local, inicio, termino, pais1, pais2, etapa FROM event ORDER BY inicio";

		List<Event> events = new ArrayList<Event>();
		jdbcTemplate
				.query(sql, (rs, rowNum) -> new Event(rs.getString("modalidade"), rs.getString("local"),
						rs.getTimestamp("inicio").toLocalDateTime(), rs.getTimestamp("termino").toLocalDateTime(),
						rs.getString("pais1"), rs.getString("pais2"), rs.getString("etapa")))
				.forEach(event -> events.add(event));

		return events;
	}

	public List<Event> getAll(String modalidade, JdbcTemplate jdbcTemplate) {
		String sql = "SELECT modalidade, local, inicio, termino, pais1, pais2, etapa FROM event "
				+ " WHERE modalidade = ? ORDER BY inicio";

		List<Event> events = new ArrayList<Event>();
		jdbcTemplate.query(sql, new Object[] { modalidade },
				(rs, rowNum) -> new Event(rs.getString("modalidade"), rs.getString("local"),
						rs.getTimestamp("inicio").toLocalDateTime(), rs.getTimestamp("termino").toLocalDateTime(),
						rs.getString("pais1"), rs.getString("pais2"), rs.getString("etapa")))
				.forEach(event -> events.add(event));

		return events;
	}

	public List<String> getValidEtapas() {
		return validEtapas;
	}

}
