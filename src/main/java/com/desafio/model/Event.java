package com.desafio.model;

import java.time.LocalDateTime;

public class Event {

	private String modalidade;
	private String local;
	private LocalDateTime inicio;
	private LocalDateTime termino;
	private String pais1;
	private String pais2;
	private String etapa;

	public Event() {
	}

	

	public Event(String modalidade, String local, LocalDateTime inicio, LocalDateTime termino, String pais1,
			String pais2, String etapa) {
		this.modalidade = modalidade;
		this.local = local;
		this.inicio = inicio;
		this.termino = termino;
		this.pais1 = pais1;
		this.pais2 = pais2;
		this.etapa = etapa;
	}



	public String getModalidade() {
		return modalidade;
	}

	public void setModalidade(String modalidade) {
		this.modalidade = modalidade;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public LocalDateTime getInicio() {
		return inicio;
	}



	public void setInicio(LocalDateTime inicio) {
		this.inicio = inicio;
	}



	public LocalDateTime getTermino() {
		return termino;
	}



	public void setTermino(LocalDateTime termino) {
		this.termino = termino;
	}



	public String getPais1() {
		return pais1;
	}

	public void setPais1(String pais1) {
		this.pais1 = pais1;
	}

	public String getPais2() {
		return pais2;
	}

	public void setPais2(String pais2) {
		this.pais2 = pais2;
	}

	public String getEtapa() {
		return etapa;
	}

	public void setEtapa(String etapa) {
		this.etapa = etapa;
	}

}
