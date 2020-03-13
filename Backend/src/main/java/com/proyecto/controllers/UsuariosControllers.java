package com.proyecto.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.controllers.dto.UsuarioItem;
import com.proyecto.exceptions.ApiException;
import com.proyecto.services.UsuariosServices;
import com.proyecto.utils.ModelApiResponse;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST })
@RequestMapping("/usuarios")
public class UsuariosControllers {

	public static final Logger logger = LoggerFactory.getLogger(UsuariosControllers.class);

	private static final String error500 = "El servidor encontró una condición inesperada, no se pudo cumplir la solicitud";

	private static final String errorMenssage = "ERROR";

	@Autowired
	UsuariosServices usuariosService;

	@GetMapping(path = "/{idUsuario}")
	public @ResponseBody ResponseEntity<UsuarioItem> getUsuarioByID(@PathVariable("idUsuario") String idUsuario) {
		try {
			return new ResponseEntity<>(usuariosService.getUsuario(Integer.parseInt(idUsuario)), HttpStatus.OK);
		} catch (ApiException e) {
			if (e.getCode() == 404) {
				logger.error(e.getMessage(), e);
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} else {
				logger.error(e.getMessage(), e);
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (Exception e) {
			logger.error(error500, e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(path = "")
	public @ResponseBody ResponseEntity<ModelApiResponse> addUsuarios(@RequestBody UsuarioItem body) {
		ModelApiResponse respuesta = new ModelApiResponse();
		try {
			usuariosService.addUsuario(body);
			respuesta.codigo("OK");
			respuesta.descripcion("Usuario agregado correctamente");
			return new ResponseEntity<>(respuesta, HttpStatus.OK);
		} catch (ApiException e) {
			if (e.getCode() == 404) {
				logger.error(e.getMessage(), e);
				respuesta.codigo(errorMenssage);
				respuesta.descripcion(e.getMessage());
				return new ResponseEntity<>(respuesta, HttpStatus.NOT_FOUND);
			} else {
				logger.error(e.getMessage(), e);
				respuesta.codigo(errorMenssage);
				respuesta.descripcion(e.getMessage());
				return new ResponseEntity<>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (Exception e) {
			logger.error(error500, e);
			respuesta.codigo(errorMenssage);
			respuesta.descripcion(e.getMessage());
			return new ResponseEntity<>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(path = "")
	public @ResponseBody ResponseEntity<List<UsuarioItem>> getUsuarios() {
		try {
			return new ResponseEntity<>(usuariosService.getAllUsuarios(), HttpStatus.OK);
		} catch (Exception e) {
			logger.error(error500, e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping(path = "/{idUsuario}")
	public @ResponseBody ResponseEntity<ModelApiResponse> removeUsuario(@PathVariable("idUsuario") String idUsuario) {
		ModelApiResponse respuesta = new ModelApiResponse();
		try {
			usuariosService.removeUsuario(Integer.parseInt(idUsuario));
			respuesta.codigo("OK");
			respuesta.descripcion("Usuario borrado correctamente");
			return new ResponseEntity<>(respuesta, HttpStatus.OK);
		} catch (ApiException e) {
			if (e.getCode() == 404) {
				logger.error(e.getMessage(), e);
				respuesta.codigo(errorMenssage);
				respuesta.descripcion(e.getMessage());
				return new ResponseEntity<>(respuesta, HttpStatus.NOT_FOUND);
			} else {
				logger.error(e.getMessage(), e);
				respuesta.codigo(errorMenssage);
				respuesta.descripcion(e.getMessage());
				return new ResponseEntity<>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (Exception e) {
			logger.error(error500, e);
			respuesta.codigo(errorMenssage);
			respuesta.descripcion(e.getMessage());
			return new ResponseEntity<>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping(path = "/{idUsuario}")
	public @ResponseBody ResponseEntity<ModelApiResponse> editUsuario(@PathVariable("idUsuario") String idUsuario,
			@RequestBody UsuarioItem body) {
		ModelApiResponse respuesta = new ModelApiResponse();
		try {
			usuariosService.editUsuario(Integer.parseInt(idUsuario), body);
			respuesta.codigo("OK");
			respuesta.descripcion("Usuario editado correctamente");
			return new ResponseEntity<>(respuesta, HttpStatus.OK);
		} catch (ApiException e) {
			if (e.getCode() == 404) {
				logger.error(e.getMessage(), e);
				respuesta.codigo(errorMenssage);
				respuesta.descripcion(e.getMessage());
				return new ResponseEntity<>(respuesta, HttpStatus.NOT_FOUND);
			} else {
				logger.error(e.getMessage(), e);
				respuesta.codigo(errorMenssage);
				respuesta.descripcion(e.getMessage());
				return new ResponseEntity<>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (Exception e) {
			logger.error(error500, e);
			respuesta.codigo(errorMenssage);
			respuesta.descripcion(e.getMessage());
			return new ResponseEntity<>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(path = "/login")
	public @ResponseBody ResponseEntity<Integer> verificarUsuario(@RequestBody UsuarioItem body) {
		try {
			return new ResponseEntity<>(usuariosService.verificarLogin(body.usuario, body.clave), HttpStatus.OK);
		} catch (Exception e) {
			logger.error(error500, e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
