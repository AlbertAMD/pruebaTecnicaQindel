/**
 * 
 */
package com.alberto.jjoo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alberto.jjoo.entidades.SedeJuegosOlimpicos;
import com.alberto.jjoo.entidades.dto.JuegosOlimpicosDTO;
import com.alberto.jjoo.services.JuegosOlimpicosService;

/**
 * @author alber
 * 
 * Clase controlador para peticiones REST
 *
 */
@RestController
@RequestMapping("/juegosolimpicos")
public class JuegosOlimpicosController {
	
	@Autowired
	private JuegosOlimpicosService service;
	
	@GetMapping("/listaCiudades")
	public List<JuegosOlimpicosDTO> obtenerListaCiudades() {
		return service.obtenerListaCiudades();
	}
	
	@PostMapping("/crearSede")
	public ResponseEntity<Object> crearNuevaSede(@RequestBody SedeJuegosOlimpicos nuevaSede) {
		return service.crearNuevaSede(nuevaSede);
	}
	
	@GetMapping("/allSedes")
	public List<SedeJuegosOlimpicos> obtenerAllSedesOlimpicas() {
		return service.obtenerAllSedes();
	}
	
	@GetMapping("/sedeById")
	public SedeJuegosOlimpicos obtenerSedeOlimpicaById(@RequestParam(value = "year") Integer year, @RequestParam(value = "tipoJuegos") String tipoJuegos) {
		return service.obtenerSedeById(year, tipoJuegos);
	}
	
	@GetMapping("/sedesByFilters")
	public List<SedeJuegosOlimpicos> obtenerSedesByFilters(@RequestBody SedeJuegosOlimpicos sedeFilters) {
		return service.obtenerSedesByFilters(sedeFilters);
	}
	
	@PutMapping("/actualizarSede")
	public ResponseEntity<Object> actualizarSedeOlimpica(@RequestBody SedeJuegosOlimpicos sedeUpdate, 
			@RequestParam(value = "year") Integer year, @RequestParam(value = "tipoJuegos") String tipoJuegos) {
		return service.actualizarSede(sedeUpdate, year, tipoJuegos);
	}
	
	@DeleteMapping("/eliminarSede")
	public void eliminarSedeOlimpica(@RequestParam(value = "year") Integer year, @RequestParam(value = "tipoJuegos") String tipoJuegos) {
		service.eliminarSedeOlimpica(year, tipoJuegos);
	}

}
