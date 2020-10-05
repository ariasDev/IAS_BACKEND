package com.IAS.Calculator.Entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.GenericGenerators;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "time_tracker")
public class TimeTrackerEntity {
	
	@Id
	@Column(name = "id_servicio")
	private String id_servicio;
	
	@Column(name = "id_tecnico")
	private String id_tecnico;
	
	@Column(name = "fecha_inicio")
	private String fecha_inicio;
	
	@Column(name = "fecha_fin")
	private String fecha_fin;
	
	@Column(name = "num_semana")
	private int num_semana;
	
	@Column(name = "horas_normales")
	private int horas_normales;
	
	@Column(name = "horas_dominicales")
	private int horas_dominicales;

	@Column(name = "horas_normales_extra")
	private int horas_normales_extra;
	
	@Column(name = "horas_dominicales_extra")
	private int horas_dominicales_extra;

	public TimeTrackerEntity() {
		super();
	}

	public TimeTrackerEntity(String id_servicio, String id_tecnico, String fecha_inicio, String fecha_fin,
			int num_semana, int horas_normales, int horas_dominicales, int horas_normales_extra,
			int horas_dominicales_extra) {
		super();
		this.id_servicio = id_servicio;
		this.id_tecnico = id_tecnico;
		this.fecha_inicio = fecha_inicio;
		this.fecha_fin = fecha_fin;
		this.num_semana = num_semana;
		this.horas_normales = horas_normales;
		this.horas_dominicales = horas_dominicales;
		this.horas_normales_extra = horas_normales_extra;
		this.horas_dominicales_extra = horas_dominicales_extra;
	}

	public String getId_servicio() {
		return id_servicio;
	}

	public void setId_servicio(String id_servicio) {
		this.id_servicio = id_servicio;
	}

	public String getId_tecnico() {
		return id_tecnico;
	}

	public void setId_tecnico(String id_tecnico) {
		this.id_tecnico = id_tecnico;
	}

	public String getFecha_inicio() {
		return fecha_inicio;
	}

	public void setFecha_inicio(String fecha_inicio) {
		this.fecha_inicio = fecha_inicio;
	}

	public String getFecha_fin() {
		return fecha_fin;
	}

	public void setFecha_fin(String fecha_fin) {
		this.fecha_fin = fecha_fin;
	}

	public int getNum_semana() {
		return num_semana;
	}

	public void setNum_semana(int num_semana) {
		this.num_semana = num_semana;
	}

	public int getHoras_normales() {
		return horas_normales;
	}

	public void setHoras_normales(int horas_normales) {
		this.horas_normales = horas_normales;
	}

	public int getHoras_dominicales() {
		return horas_dominicales;
	}

	public void setHoras_dominicales(int horas_dominicales) {
		this.horas_dominicales = horas_dominicales;
	}

	public int getHoras_normales_extra() {
		return horas_normales_extra;
	}

	public void setHoras_normales_extra(int horas_normales_extra) {
		this.horas_normales_extra = horas_normales_extra;
	}

	public int getHoras_dominicales_extra() {
		return horas_dominicales_extra;
	}

	public void setHoras_dominicales_extra(int horas_dominicales_extra) {
		this.horas_dominicales_extra = horas_dominicales_extra;
	}

	
	
	
}
