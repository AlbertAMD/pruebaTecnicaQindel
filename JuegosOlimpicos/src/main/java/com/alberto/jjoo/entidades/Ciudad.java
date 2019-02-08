/**
 * 
 */
package com.alberto.jjoo.entidades;

import java.io.Serializable;

/**
 * @author alber
 * 
 * Clase que reprenseta a la entidad Ciudad
 * 
 */
public class Ciudad implements Serializable {

	/**
	 * Serial UID
	 */
	private static final long serialVersionUID = 3571655315657593208L;

	private Integer idCiudad;
	
	private String nombreCiudad;
	
	private Pais pais;
	
	private Integer valorCiudad;
	
	/**
	 * Constructor vacío
	 */
	public Ciudad() {
	}
	
	/**
	 * Constructor con todos los parámetros
	 * 
	 * @param idCiudad Identificador de la ciudad
	 * @param nombreCiudad Nombre de la ciudad
	 * @param pais País al que pertenece la ciudad
	 * @param valorCiudad Valor de la ciudad
	 */
	public Ciudad(Integer idCiudad, String nombreCiudad, Pais pais, Integer valorCiudad) {
		this.idCiudad = idCiudad;
		this.nombreCiudad = nombreCiudad;
		this.pais = pais;
		this.valorCiudad = valorCiudad;
	}
	
	/**
	 * Constructor con los parámetros obligatorios en BBDD
	 * 
	 * @param idCiudad Identificador de la ciudad
	 * @param nombreCiudad Nombre de la ciudad
	 * @param pais País al que pertenece la ciudad
	 */
	public Ciudad(Integer idCiudad, String nombreCiudad, Pais pais) {
		this.idCiudad = idCiudad;
		this.nombreCiudad = nombreCiudad;
		this.pais = pais;
	}

	/**
	 * @return the idCiudad
	 */
	public Integer getIdCiudad() {
		return idCiudad;
	}

	/**
	 * @param idCiudad the idCiudad to set
	 */
	public void setIdCiudad(Integer idCiudad) {
		this.idCiudad = idCiudad;
	}

	/**
	 * @return the nombreCiudad
	 */
	public String getNombreCiudad() {
		return nombreCiudad;
	}

	/**
	 * @param nombreCiudad the nombreCiudad to set
	 */
	public void setNombreCiudad(String nombreCiudad) {
		this.nombreCiudad = nombreCiudad;
	}

	/**
	 * @return the pais
	 */
	public Pais getPais() {
		return pais;
	}

	/**
	 * @param pais the pais to set
	 */
	public void setPais(Pais pais) {
		this.pais = pais;
	}

	/**
	 * @return the valorCiudad
	 */
	public Integer getValorCiudad() {
		return valorCiudad;
	}

	/**
	 * @param valorCiudad the valorCiudad to set
	 */
	public void setValorCiudad(Integer valorCiudad) {
		this.valorCiudad = valorCiudad;
	}

}
